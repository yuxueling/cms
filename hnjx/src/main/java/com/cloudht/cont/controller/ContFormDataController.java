package com.cloudht.cont.controller;

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

import com.cloudht.cont.domain.ContFormDO;
import com.cloudht.cont.domain.ContFormDataDO;
import com.cloudht.cont.service.ContFormDataService;
import com.cloudht.cont.service.ContFormService;
import com.sxyht.common.utils.PageUtils;
import com.sxyht.common.utils.Query;
import com.sxyht.common.utils.R;

/**
 * 表单数据表
 *
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-27 08:56:14
 */

@Controller
@RequestMapping("/cont/contFormData")
public class ContFormDataController {
    @Autowired
    private ContFormDataService contFormDataService;
    @Autowired
    private ContFormService contFormService;

    @GetMapping()
    @RequiresPermissions("cont:contForm:contForm")
    String ContFormData() {
        return "cont/contFormData/contFormData";
    }

    @GetMapping("/list")
    @RequiresPermissions("cont:contForm:contForm")
    @ResponseBody
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<ContFormDataDO> contFormDataList = contFormDataService.list(query);
        int total = contFormDataService.count(query);
        PageUtils pageUtils = new PageUtils(contFormDataList, total);
        return pageUtils;
    }

    @GetMapping("/listBycontFormId")
    @RequiresPermissions("cont:contForm:contForm")
    public String listBycontFormId(@RequestParam Map<String, Object> params, Model model) {
        //查询列表数据
        List<ContFormDataDO> contFormDataList = contFormDataService.list(params);
        model.addAttribute("contFormDataList", contFormDataList);
        {//设置为已读
            ContFormDO contFormDO = new ContFormDO();
            contFormDO.setContFormId(Integer.parseInt(params.get("contFormId").toString()));
            contFormDO.setHaveRead(1);
            this.contFormService.update(contFormDO);
        }
        return "cont/contFormData/formDetails";
    }

    @GetMapping("/add")
    @RequiresPermissions("cont:contForm:add")
    String add() {
        return "cont/contFormData/add";
    }

    @GetMapping("/edit/{contFormDataId}")
    @RequiresPermissions("cont:contForm:edit")
    String edit(@PathVariable("contFormDataId") Integer contFormDataId, Model model) {
        ContFormDataDO contFormData = contFormDataService.get(contFormDataId);
        model.addAttribute("contFormData", contFormData);
        return "cont/contFormData/edit";
    }


    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("cont:contForm:add")
    @ResponseBody
    public R save(ContFormDataDO contFormData) {
        if (contFormDataService.save(contFormData) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cont:contForm:edit")
    @ResponseBody
    public R update(ContFormDataDO contFormData) {
        contFormDataService.update(contFormData);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @RequiresPermissions("cont:contForm:remove")
    @ResponseBody
    public R remove(Integer contFormDataId) {
        if (contFormDataService.remove(contFormDataId) > 0)
            return R.ok();
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @RequiresPermissions("cont:contForm:batchRemove")
    @ResponseBody
    public R remove(@RequestParam("ids[]") Integer[] contFormDataIds) {
        contFormDataService.batchRemove(contFormDataIds);
        return R.ok();
    }
}
