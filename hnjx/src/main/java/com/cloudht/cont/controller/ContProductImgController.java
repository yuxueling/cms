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

import com.cloudht.cont.domain.ContProductImgDO;
import com.cloudht.cont.service.ContProductImgService;
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
@RequestMapping("/cont/contProductImg")
public class ContProductImgController {
	@Autowired
	private ContProductImgService contProductImgService;
	
	@GetMapping()
	@RequiresPermissions("cont:contProductImg:contProductImg")
	String ContProductImg(){
	    return "cont/contProductImg/contProductImg";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cont:contProductImg:contProductImg")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ContProductImgDO> contProductImgList = contProductImgService.list(query);
		int total = contProductImgService.count(query);
		PageUtils pageUtils = new PageUtils(contProductImgList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("cont:contProductImg:add")
	String add(){
	    return "cont/contProductImg/add";
	}

	@GetMapping("/edit/{contProductImgId}")
	@RequiresPermissions("cont:contProductImg:edit")
	String edit(@PathVariable("contProductImgId") Integer contProductImgId,Model model){
		ContProductImgDO contProductImg = contProductImgService.get(contProductImgId);
		model.addAttribute("contProductImg", contProductImg);
	    return "cont/contProductImg/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("cont:contProductImg:add")
	public R save( ContProductImgDO contProductImg){
		if(contProductImgService.save(contProductImg)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cont:contProductImg:edit")
	public R update( ContProductImgDO contProductImg){
		contProductImgService.update(contProductImg);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cont:contProductImg:remove")
	public R remove( Integer contProductImgId){
		if(contProductImgService.remove(contProductImgId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cont:contProductImg:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] contProductImgIds){
		contProductImgService.batchRemove(contProductImgIds);
		return R.ok();
	}
	
}
