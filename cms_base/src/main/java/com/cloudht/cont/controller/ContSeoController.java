package com.cloudht.cont.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import com.cloudht.common.controller.BaseController;
import com.cloudht.common.service.DictService;
import com.cloudht.cont.domain.ContSeoDO;
import com.cloudht.cont.service.ContSeoService;
import com.sxyht.common.utils.PageUtils;
import com.sxyht.common.utils.Query;
import com.sxyht.common.utils.R;

/**
 * 
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-10-18 09:15:37
 */
 
@Controller
@RequestMapping("/cont/contSeo")
public class ContSeoController extends BaseController {
	@Autowired private ContSeoService contSeoService;
	@Autowired private DictService dictService;
	
	@GetMapping()
	@RequiresPermissions("cont:contSeo:contSeo")
	String contSeo(){
	    return "cont/contSeo/contSeo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cont:contSeo:contSeo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ContSeoDO> contSeoList = contSeoService.list(query);
		int total = contSeoService.count(query);
		PageUtils pageUtils = new PageUtils(contSeoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("cont:contSeo:add")
	String add(Model model){
		model.addAttribute("listLangType",this.dictService.listByType("CmsLangType"));
	    return "cont/contSeo/add";
	}

	@GetMapping("/edit/{contSeoId}")
	@RequiresPermissions("cont:contSeo:edit")
	String edit(@PathVariable("contSeoId") Integer contSeoId,Model model){
		ContSeoDO contSeo = contSeoService.get(contSeoId);
		model.addAttribute("contSeo", contSeo);
		model.addAttribute("langTypes", this.dictService.listByType("CmsLangType"));
	    return "cont/contSeo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("cont:contSeo:add")
	public R save( ContSeoDO contSeo){
		contSeo.setCreateBy(this.getUserId());
		contSeo.setGmtModified(new Date());
		if(contSeoService.save(contSeo)>0)
			return R.ok();
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cont:contSeo:edit")
	public R update( ContSeoDO contSeo){
		contSeo.setGmtModified(new Date());
		contSeoService.update(contSeo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cont:contSeo:remove")
	public R remove( Integer contSeoId){
		if(contSeoService.remove(contSeoId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cont:contSeo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] contSeoIds){
		contSeoService.batchRemove(contSeoIds);
		return R.ok();
	}
	
}
