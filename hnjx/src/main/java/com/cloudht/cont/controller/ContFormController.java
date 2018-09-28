package com.cloudht.cont.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cloudht.common.domain.DictDO;
import com.cloudht.cont.service.ContFormDataService;
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
import com.cloudht.cont.service.ContFormService;
import com.sxyht.common.utils.PageUtils;
import com.sxyht.common.utils.Query;
import com.sxyht.common.utils.R;

/**
 * 表单表
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-27 08:56:13
 */
 
@Controller
@RequestMapping("/cont/contForm")
public class ContFormController {
	@Autowired private ContFormService contFormService;

	@Autowired
	private ContFormDataService contFormDataService;
	
	@GetMapping() @RequiresPermissions("cont:contForm:contForm")
	String ContForm(){
	    return "cont/contForm/contForm";
	}
	@GetMapping("/list") @RequiresPermissions("cont:contForm:contForm") @ResponseBody
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ContFormDO> contFormList = contFormService.list(query);
		int total = contFormService.count(query);
		PageUtils pageUtils = new PageUtils(contFormList, total);
		return pageUtils;
	}
	
	@GetMapping("/add") @RequiresPermissions("cont:contForm:add")
	String add(Model model){

	    return "cont/contForm/add";
	}

	@GetMapping("/edit/{contFormId}") @RequiresPermissions("cont:contForm:edit")
	String edit(@PathVariable("contFormId") Integer contFormId,Model model){
		ContFormDO contForm = contFormService.get(contFormId);
		model.addAttribute("contForm", contForm);
	    return "cont/contForm/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save") @RequiresPermissions("cont:contForm:add")
	public R save( ContFormDO contForm){
		contForm.setGmtCreate(new Date());
		contForm.setHaveRead(0);
		if(contFormService.save(contForm)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update") @RequiresPermissions("cont:contForm:edit")
	public R update( ContFormDO contForm){
		contFormService.update(contForm);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove") @RequiresPermissions("cont:contForm:remove") @ResponseBody
	public R remove( Integer contFormId){
		if(contFormService.remove(contFormId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cont:contForm:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] contFormIds){
		contFormService.batchRemove(contFormIds);
		return R.ok();
	}

}
