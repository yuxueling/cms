package com.cloudht.cont.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloudht.common.controller.BaseController;
import com.cloudht.common.domain.DictDO;
import com.cloudht.common.domain.Tree;
import com.cloudht.common.service.DictService;
import com.cloudht.cont.domain.ContCategoryDO;
import com.cloudht.cont.domain.ContCategoryInfoDO;
import com.cloudht.cont.service.ContCategoryService;
import com.sxyht.common.utils.R;

/**
 * 类别表
 *
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-18 16:27:29
 */

@Controller
@RequestMapping("/cont/contCategory")
public class ContCategoryController extends BaseController {
    @Autowired
    private ContCategoryService contCategoryService;
    @Autowired
    private DictService dictService;

    @GetMapping()
    @RequiresPermissions("cont:contCategory:contCategory")
    String ContCategory() {
        return "cont/contCategory/contCategory";
    }

    @GetMapping("/list")
    @RequiresPermissions("cont:contCategory:contCategory")
    public @ResponseBody
    List<ContCategoryDO> list(@RequestParam Map<String, Object> params) {
        List<DictDO> listDictDO = dictService.listByType("CmsCategoryType");//获取所有的产品类型
        List<ContCategoryDO> list = contCategoryService.list(params);//获取所有的类目
        for (ContCategoryDO contCategoryDO : list) {//遍历所有的类目
            String categoryType = contCategoryDO.getCategoryType();//每个类目的类型
            for (DictDO dictDO : listDictDO) {//遍历所有的产品类型
                if (dictDO.getValue().equals(categoryType)) {
                    contCategoryDO.setCategoryType(dictDO.getName());
                    break;
                }
            }
        }
        return list;
    }

    @RequiresPermissions("cont:contCategory:add")
    @GetMapping("/add")
    String add(Integer parentCategoryId, Model model) {
        List<DictDO> listCmsCategoryType = dictService.listByType("CmsCategoryType");//获取所有的产品类型
        model.addAttribute("listCmsCategoryType", listCmsCategoryType);//所有产品类型的集合
        model.addAttribute("parentCategoryId", parentCategoryId);//父id隐藏到页面，顶级为0
        model.addAttribute("parentCategoryName", "顶级类目");//先假设为顶级类目
        model.addAttribute("categoryType", "");//继承父页面的产品类型
        if (parentCategoryId != null && parentCategoryId > 0) {
            ContCategoryDO contCategoryDO = this.contCategoryService.get(parentCategoryId);//根据父类型的id查询某个类型
            model.addAttribute("parentCategoryName", contCategoryDO.getCategoryName());//设置判断后的上级类目
            model.addAttribute("categoryType", contCategoryDO.getCategoryType());
        }
        return "cont/contCategory/add";
    }

    @GetMapping("/edit/{contCategoryId}")
    @RequiresPermissions("cont:contCategory:edit")
    String edit(@PathVariable("contCategoryId") Integer contCategoryId, Model model) {
        ContCategoryDO contCategory = contCategoryService.get(contCategoryId);
        model.addAttribute("contCategory", contCategory);
        return "cont/contCategory/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("cont:contCategory:add")
    public R save(ContCategoryDO contCategory) {
        contCategory.setCreateBy(getUserId());
        contCategory.setGmtCreate(new Date());
        contCategory.setGmtModified(new Date());
        if (contCategoryService.save(contCategory) > 0)
            return R.ok();
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("cont:contCategory:edit")
    public R update(ContCategoryDO contCategory) {
        contCategory.setGmtModified(new Date());
        contCategoryService.update(contCategory);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("cont:contCategory:remove")
    public R remove(Integer contCategoryId) {
        {//判断是否存在子类目parent_category_id
            Map<String, Object> map = new HashMap<>();
            map.put("parentCategoryId", contCategoryId);
            if (contCategoryService.list(map).size() > 0)
                return R.error("删除子类目后才能删除父级类目哦");
        }
        if (contCategoryService.remove(contCategoryId) > 0)
            return R.ok();
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("cont:contCategory:batchRemove")
    public R remove(@RequestParam("ids[]") Integer[] contCategoryIds) {
        contCategoryService.batchRemove(contCategoryIds);
        return R.ok();
    }

    /**
     * 选择分类
     */
    public @GetMapping("/treeView")
    String treeView() {
        return "/cont/contCategory/categoryTree";
    }

    /**
     * 加载分类
     *
     * @return
     */
    @GetMapping("/tree")
    @ResponseBody
    Tree<ContCategoryDO> tree() {
        return this.contCategoryService.getTree();
    }

    /**
     * 加载分类含详情
     * @param params lang_type
     * @return
     */
    @RequestMapping("/treeInfo")
    @ResponseBody
    Tree<ContCategoryDO> treeInfo(@RequestParam Map<String, Object> params) {
        return this.contCategoryService.getTreeInfo(params);
    }

    /**
     * 分类详情设置页面
     * @param contCategoryId
     * @param model
     * @return
     */
    @GetMapping("/addLangView")
    @RequiresPermissions("cont:contCategory:add")
    public String editLangView( Integer contCategoryId, Model model) {
        Map<String, Object> map=new HashMap<>();
        map.put("contCategoryId",contCategoryId);
        List<ContCategoryInfoDO> categoryDOList = contCategoryService.listInfoByDict(map);
        List<DictDO> langDictList = this.dictService.listByType("CmsLangType");

        model.addAttribute("contCategoryId", contCategoryId);
        model.addAttribute("langDictList", langDictList);
        model.addAttribute("rows", categoryDOList);
        return "cont/contCategory/editLang";
    }


    /**
     * 保存基本信息
     */
    @ResponseBody
    @PostMapping("/saveInfo")
    @RequiresPermissions("cont:contProduct:add")
    public R saveInfo(String categoryInfo,Integer contCategoryId) {
        List<ContCategoryInfoDO> categoryInfoDOList = JSON.parseArray(categoryInfo, ContCategoryInfoDO.class);

        for (ContCategoryInfoDO infoDO : categoryInfoDOList) {
            if (infoDO.getContCategoryInfoId() == null) {//保存
                infoDO.setContCategoryId(contCategoryId);
                contCategoryService.saveInfo(infoDO);
            } else {//修改
                contCategoryService.updateInfo(infoDO);
            }
        }
        return R.ok();
    }

}
