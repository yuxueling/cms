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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloudht.cont.domain.ContProductPkDO;
import com.cloudht.cont.service.ContProductPkService;
import com.sxyht.common.utils.PageUtils;
import com.sxyht.common.utils.Query;
import com.sxyht.common.utils.R;

/**
 * 
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-18 16:27:29
 */
 
@Controller
@RequestMapping("/cont/contProductPk")
public class ContProductPkController {
	@Autowired
	private ContProductPkService contProductPkService;
	
	@GetMapping()
	@RequiresPermissions("cont:contProductPk:contProductPk")
	String ContProductPk(){
	    return "cont/contProductPk/contProductPk";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cont:contProductPk:contProductPk")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ContProductPkDO> contProductPkList = contProductPkService.list(query);
		int total = contProductPkService.count(query);
		PageUtils pageUtils = new PageUtils(contProductPkList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("cont:contProductPk:add")
	String add(){
	    return "cont/contProductPk/add";
	}

	@GetMapping("/edit/{contProductPkId}")
	@RequiresPermissions("cont:contProductPk:edit")
	String edit(@PathVariable("contProductPkId") Integer contProductPkId,Model model){
		ContProductPkDO contProductPk = contProductPkService.get(contProductPkId);
		model.addAttribute("contProductPk", contProductPk);
	    return "cont/contProductPk/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("cont:contProductPk:add")
	public R save( ContProductPkDO contProductPk){
		if(contProductPkService.save(contProductPk)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cont:contProductPk:edit")
	public R update( ContProductPkDO contProductPk){
		contProductPkService.update(contProductPk);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cont:contProductPk:remove")
	public R remove( Integer contProductPkId){
		if(contProductPkService.remove(contProductPkId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cont:contProductPk:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] contProductPkIds){
		contProductPkService.batchRemove(contProductPkIds);
		return R.ok();
	}
	
}
