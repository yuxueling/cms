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

import com.cloudht.cont.domain.ContProductParamDO;
import com.cloudht.cont.service.ContProductParamService;
import com.sxyht.common.utils.PageUtils;
import com.sxyht.common.utils.Query;
import com.sxyht.common.utils.R;

/**
 * 
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-23 13:01:59
 */
 
@Controller
@RequestMapping("/cont/contProductParam")
public class ContProductParamController {
	@Autowired
	private ContProductParamService contProductParamService;
	
	@GetMapping()
	@RequiresPermissions("cont:contProductParam:contProductParam")
	String ContProductParam(){
	    return "cont/contProductParam/contProductParam";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cont:contProductParam:contProductParam")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ContProductParamDO> contProductParamList = contProductParamService.list(query);
		int total = contProductParamService.count(query);
		PageUtils pageUtils = new PageUtils(contProductParamList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("cont:contProductParam:add")
	String add(){
	    return "cont/contProductParam/add";
	}

	@GetMapping("/edit/{contProductParamId}")
	@RequiresPermissions("cont:contProductParam:edit")
	String edit(@PathVariable("contProductParamId") Integer contProductParamId,Model model){
		ContProductParamDO contProductParam = contProductParamService.get(contProductParamId);
		model.addAttribute("contProductParam", contProductParam);
	    return "cont/contProductParam/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("cont:contProductParam:add")
	public R save( ContProductParamDO contProductParam){
		if(contProductParamService.save(contProductParam)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cont:contProductParam:edit")
	public R update( ContProductParamDO contProductParam){
		contProductParamService.update(contProductParam);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cont:contProductParam:remove")
	public R remove( Integer contProductParamId){
		if(contProductParamService.remove(contProductParamId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cont:contProductParam:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] contProductParamIds){
		contProductParamService.batchRemove(contProductParamIds);
		return R.ok();
	}
	
}
