package com.sxyhton.cont.controller;

import java.util.List;
import java.util.Map;

import com.sxyhton.common.service.DictService;
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

import com.sxyht.common.utils.PageUtils;
import com.sxyht.common.utils.Query;
import com.sxyht.common.utils.R;
import com.sxyhton.cont.domain.ContSitemapDO;
import com.sxyhton.cont.service.ContSitemapService;

/**
 * 
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-10-12 15:59:14
 */
 
@Controller
@RequestMapping("/cont/contSitemap")
public class ContSitemapController {
	@Autowired
	private ContSitemapService contSitemapService;
	@Autowired
	private DictService dictService;
	
	@GetMapping()
	@RequiresPermissions("cont:contSitemap:contSitemap")
	String ContSitemap(){
	    return "cont/contSitemap/contSitemap";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cont:contSitemap:contSitemap")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ContSitemapDO> contSitemapList = contSitemapService.list(query);
		int total = contSitemapService.count(query);
		PageUtils pageUtils = new PageUtils(contSitemapList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("cont:contSitemap:add")
	String add(Model model){
		model.addAttribute("langTypes", this.dictService.listByType("CmsLangType"));//将语言类型放到前台
		model.addAttribute("pageAddressList", this.dictService.listByType("cmsPageAddress"));
	    return "cont/contSitemap/add";
	}

	@GetMapping("/edit/{contSitemapId}")
	@RequiresPermissions("cont:contSitemap:edit")
	String edit(@PathVariable("contSitemapId") Integer contSitemapId,Model model){
		ContSitemapDO contSitemap = contSitemapService.get(contSitemapId);
		model.addAttribute("contSitemap", contSitemap);
		model.addAttribute("langTypes", this.dictService.listByType("CmsLangType"));
		model.addAttribute("pageAddressList", this.dictService.listByType("cmsPageAddress"));
	    return "cont/contSitemap/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("cont:contSitemap:add")
	public R save( ContSitemapDO contSitemap){
		if(contSitemapService.save(contSitemap)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cont:contSitemap:edit")
	public R update( ContSitemapDO contSitemap){
		contSitemapService.update(contSitemap);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cont:contSitemap:remove")
	public R remove( Integer contSitemapId){
		if(contSitemapService.remove(contSitemapId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cont:contSitemap:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] contSitemapIds){
		contSitemapService.batchRemove(contSitemapIds);
		return R.ok();
	}
	
}
