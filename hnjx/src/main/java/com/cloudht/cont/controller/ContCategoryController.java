package com.cloudht.cont.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.cloudht.cont.dao.ContCategoryInfoDao;
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
public class ContCategoryController extends BaseController{
	@Autowired private ContCategoryService contCategoryService;
	@Autowired private DictService dictService;
	@Autowired private ContCategoryInfoDao contCategoryInfoDao;
	@GetMapping()
	@RequiresPermissions("cont:contCategory:contCategory")
	String ContCategory(){
	    return "cont/contCategory/contCategory";
	}
	
	@GetMapping("/list") @RequiresPermissions("cont:contCategory:contCategory")
	public @ResponseBody List<ContCategoryDO> list(@RequestParam Map<String, Object> params){
		List<DictDO> listDictDO = dictService.listByType("CmsCategoryType");//获取所有的产品类型
		List<ContCategoryDO> list = contCategoryService.list(params);//获取所有的类目
		for(ContCategoryDO contCategoryDO:list) {//遍历所有的类目
			String categoryType = contCategoryDO.getCategoryType();//每个类目的类型
			for(DictDO dictDO:listDictDO) {//遍历所有的产品类型
				if(dictDO.getValue().equals(categoryType)) {
					contCategoryDO.setCategoryType(dictDO.getName());
					break;
				}
			}
		}
        return list;
	}
	
	@RequiresPermissions("cont:contCategory:add")@GetMapping("/add") 
	String add(Integer parentCategoryId,Model model){
		List<DictDO> listCmsCategoryType = dictService.listByType("CmsCategoryType");//获取所有的产品类型
		model.addAttribute("listCmsCategoryType", listCmsCategoryType);//所有产品类型的集合
		model.addAttribute("parentCategoryId", parentCategoryId);//父id隐藏到页面，顶级为0
		model.addAttribute("parentCategoryName", "顶级类目");//先假设为顶级类目
		model.addAttribute("categoryType", "");//继承父页面的产品类型
		if(parentCategoryId!=null&&parentCategoryId>0) {
			ContCategoryDO contCategoryDO = this.contCategoryService.get(parentCategoryId);//根据父类型的id查询某个类型
			model.addAttribute("parentCategoryName", contCategoryDO.getCategoryName());//设置判断后的上级类目
			model.addAttribute("categoryType", contCategoryDO.getCategoryType());
		}
	    return "cont/contCategory/add";
	}

	@GetMapping("/edit/{contCategoryId}")
	@RequiresPermissions("cont:contCategory:edit")
	String edit(@PathVariable("contCategoryId") Integer contCategoryId,Model model){
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
	public R save( ContCategoryDO contCategory){
		contCategory.setCreateBy(getUserId());
		contCategory.setGmtCreate(new Date());
		contCategory.setGmtModified(new Date());
		if(contCategoryService.save(contCategory)>0)
			return R.ok();
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cont:contCategory:edit")
	public R update( ContCategoryDO contCategory){
		contCategory.setGmtModified(new Date());
		contCategoryService.update(contCategory);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cont:contCategory:remove")
	public R remove( Integer contCategoryId){
		{//判断是否存在子类目parent_category_id
			Map<String,Object> map =new HashMap<>();
			map.put("parentCategoryId", contCategoryId);
			if(contCategoryService.list(map).size()>0)
				return R.error("删除子类目后才能删除父级类目哦");
		}
		if(contCategoryService.remove(contCategoryId)>0)
			return R.ok();
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cont:contCategory:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] contCategoryIds){
		contCategoryService.batchRemove(contCategoryIds);
		return R.ok();
	}
	/**
	 * 选择分类
	 */
	public @GetMapping("/treeView") String treeView() {
		return  "/cont/contCategory/categoryTree";
	}
	/**
	 * 加载分类
	 * @return
	 */
	@GetMapping("/tree") @ResponseBody Tree<ContCategoryDO> tree() {
		return this.contCategoryService.getTree();
	}
	@GetMapping("/addLangView")@RequiresPermissions("cont:contCategory:add")
	public String editLangView(Map<String, Object> map,Integer contCategoryId,Model model) {
		List<DictDO> cmsLangType = this.dictService.listByType("CmsLangType");
		map.clear();map.put("contCategoryId", contCategoryId);
		List<ContCategoryInfoDO> contCategoryInfoList = this.contCategoryInfoDao.list(map);
		if(contCategoryInfoList.size()<1) {//创建
			int w=0;
			for(int i=0;i<cmsLangType.size();i++) {
				ContCategoryInfoDO contCategoryInfoDO = new ContCategoryInfoDO();
				contCategoryInfoDO.setLangType(cmsLangType.get(w).getValue());
				w++;
				contCategoryInfoList.add(contCategoryInfoDO);
			}
		}
		for(ContCategoryInfoDO contCategoryInfoDO:contCategoryInfoList) {
			for(DictDO dictDO:cmsLangType) {
				if(contCategoryInfoDO.getLangType().equals(dictDO.getValue()))
					contCategoryInfoDO.setLangName(dictDO.getName());
			}
		}
		model.addAttribute("contCategoryInfoList", contCategoryInfoList);
		return "cont/contCategory/editLang";
	}
}
