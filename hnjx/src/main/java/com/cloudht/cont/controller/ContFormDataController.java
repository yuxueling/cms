package com.cloudht.cont.controller;

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

import com.cloudht.cont.domain.ContFormDataDO;
import com.cloudht.cont.service.ContFormDataService;
import com.sxyht.common.utils.PageUtils;
import com.sxyht.common.utils.Query;
import com.sxyht.common.utils.R;

/**
 * 表单数据表
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-25 10:01:26
 */
@Controller @RequestMapping("/cont/contFormData")
public class ContFormDataController {
	@Autowired private ContFormDataService contFormDataService;
	
	@GetMapping() @RequiresPermissions("cont:contFormData:contFormData")
	String ContFormData(){
	    return "cont/contFormData/contFormData";
	}
	
	@ResponseBody @GetMapping("/list") @RequiresPermissions("cont:contFormData:contFormData")
	public PageUtils list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
		List<ContFormDataDO> contFormDataList = contFormDataService.list(query);
		int total = contFormDataService.count(query);
		PageUtils pageUtils = new PageUtils(contFormDataList, total);
		return pageUtils;
	}
	
	@GetMapping("/add") @RequiresPermissions("cont:contFormData:add")
	String add(){
	    return "cont/contFormData/add";
	}

	@GetMapping("/edit/{contFormDataId}") @RequiresPermissions("cont:contFormData:edit")
	String edit(@PathVariable("contFormDataId") Integer contFormDataId,Model model){
		ContFormDataDO contFormData = contFormDataService.get(contFormDataId);
		model.addAttribute("contFormData", contFormData);
	    return "cont/contFormData/edit";
	}
	
	@ResponseBody @PostMapping("/save") @RequiresPermissions("cont:contFormData:add")
	public R save( ContFormDataDO contFormData){
		if(contFormDataService.save(contFormData)>0)
			return R.ok();
		return R.error();
	}
	
	@ResponseBody @RequestMapping("/update") @RequiresPermissions("cont:contFormData:edit")
	public R update( ContFormDataDO contFormData){
		contFormDataService.update(contFormData);
		return R.ok();
	}
	
	@PostMapping( "/remove") @ResponseBody @RequiresPermissions("cont:contFormData:remove")
	public R remove( Integer contFormDataId){
		if(contFormDataService.remove(contFormDataId)>0)
			return R.ok();
		return R.error();
	}
	
	@PostMapping( "/batchRemove") @ResponseBody @RequiresPermissions("cont:contFormData:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] contFormDataIds){
		contFormDataService.batchRemove(contFormDataIds);
		return R.ok();
	}
	
}
