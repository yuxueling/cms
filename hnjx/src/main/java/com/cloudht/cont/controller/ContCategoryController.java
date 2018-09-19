package com.cloudht.cont.controller;

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
import com.cloudht.cont.domain.ContCategoryDO;
import com.cloudht.cont.service.ContCategoryService;
import com.sxyht.common.utils.PageUtils;
import com.sxyht.common.utils.Query;
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
	@GetMapping()
	@RequiresPermissions("cont:contCategory:contCategory")
	String ContCategory(){
	    return "cont/contCategory/contCategory";
	}
	@GetMapping("/list") @ResponseBody
	@RequiresPermissions("cont:contCategory:contCategory")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ContCategoryDO> contCategoryList = contCategoryService.list(query);
		int total = contCategoryService.count(query);
		PageUtils pageUtils = new PageUtils(contCategoryList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("cont:contCategory:add")
	String add(){
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
	
}
