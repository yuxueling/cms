package com.cloudht.cont.controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.cloudht.common.service.DictService;
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
 * @date 2018-09-25 10:01:26
 */
@Controller
@RequestMapping("/cont/contForm")
public class ContFormController extends BaseController {
	@Autowired private ContFormService contFormService;
	
	@Autowired private DictService dictService;
	
	@GetMapping() @RequiresPermissions("cont:contForm:contForm")
	String ContForm(){
	    return "cont/contForm/contForm";
	}
	
	@GetMapping("/list") @RequiresPermissions("cont:contForm:contForm")
	public @ResponseBody PageUtils list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
		List<ContFormDO> contFormList = contFormService.list(query);
		int total = contFormService.count(query);
		PageUtils pageUtils = new PageUtils(contFormList, total);
		return pageUtils;
	}
	
	@GetMapping("/add") @RequiresPermissions("cont:contForm:add")
	String add(Model model){
		List<DictDO> langDictList = dictService.listByType("CmsLangType");	
		ArrayList<ContFormDO> arrayList = new ArrayList<ContFormDO>(langDictList.size());
		for(int i=0;i<langDictList.size();i++) 
			arrayList.add(new ContFormDO());
		model.addAttribute("rows", arrayList);
		model.addAttribute("langDictList",langDictList);
	    return "cont/contForm/add";
	}

	@GetMapping("/edit/{contFormId}") @RequiresPermissions("cont:contForm:edit")
	String edit(@PathVariable("contFormId") Integer contFormId,Model model){
		ContFormDO contForm = contFormService.get(contFormId);
		model.addAttribute("contForm", contForm);
	    return "cont/contForm/edit";
	}
	
	@PostMapping("/save") @RequiresPermissions("cont:contForm:add") @ResponseBody
	public R save( ContFormDO contForm){
		contForm.setGmtCreate(new Date());
		contForm.setModifiedBy(this.getUserId());
		if(contFormService.save(contForm)>0)
			return R.ok();
		return R.error();
	}
	
	@RequestMapping("/update") @ResponseBody @RequiresPermissions("cont:contForm:edit")
	public  R update( ContFormDO contForm){
		contForm.setGmtModified(new Date());
		contForm.setModifiedBy(getUserId());
		contFormService.update(contForm);
		return R.ok();
	}
	
	@PostMapping( "/remove") @ResponseBody @RequiresPermissions("cont:contForm:remove")
	public R remove( Integer contFormId){
		if(contFormService.remove(contFormId)>0)
			return R.ok();
		return R.error();
	}
	
	@PostMapping("/batchRemove") @RequiresPermissions("cont:contForm:batchRemove") @ResponseBody
	public R remove(@RequestParam("ids[]") Integer[] contFormIds){
		contFormService.batchRemove(contFormIds);
		return R.ok();
	}	
}