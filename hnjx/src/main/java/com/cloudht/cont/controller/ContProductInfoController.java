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

import com.cloudht.common.controller.BaseController;
import com.cloudht.cont.domain.ContProductInfoDO;
import com.cloudht.cont.service.ContProductInfoService;
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
@RequestMapping("/cont/contProductInfo")
public class ContProductInfoController extends BaseController {
	@Autowired
	private ContProductInfoService contProductInfoService;
	
	@GetMapping()
	@RequiresPermissions("cont:contProductInfo:contProductInfo")
	String ContProductInfo(){
	    return "cont/contProductInfo/contProductInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cont:contProductInfo:contProductInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ContProductInfoDO> contProductInfoList = contProductInfoService.list(query);
		int total = contProductInfoService.count(query);
		PageUtils pageUtils = new PageUtils(contProductInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("cont:contProductInfo:add")
	String add(){
	    return "cont/contProductInfo/add";
	}

	@GetMapping("/edit/{contProductInfoId}")
	@RequiresPermissions("cont:contProductInfo:edit")
	String edit(@PathVariable("contProductInfoId") Integer contProductInfoId,Model model){
		ContProductInfoDO contProductInfo = contProductInfoService.get(contProductInfoId);
		model.addAttribute("contProductInfo", contProductInfo);
	    return "cont/contProductInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("cont:contProductInfo:add")
	public R save( ContProductInfoDO contProductInfo){
		if(contProductInfoService.save(contProductInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cont:contProductInfo:edit")
	public R update( ContProductInfoDO contProductInfo){
		contProductInfoService.update(contProductInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cont:contProductInfo:remove")
	public R remove( Integer contProductInfoId){
		if(contProductInfoService.remove(contProductInfoId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cont:contProductInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] contProductInfoIds){
		contProductInfoService.batchRemove(contProductInfoIds);
		return R.ok();
	}
	
}
