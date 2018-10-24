package com.sxyhton.cont.controller;

import com.sxyht.common.utils.PageUtils;
import com.sxyht.common.utils.Query;
import com.sxyht.common.utils.R;
import com.sxyhton.common.controller.BaseController;
import com.sxyhton.common.service.DictService;
import com.sxyhton.cont.domain.ContentDO;
import com.sxyhton.cont.service.ContentService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 文章内容
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2017-09-09 10:03:34
 */
@Controller@RequestMapping("/blog/bContent")
public class ContentController extends BaseController {
	@Autowired ContentService bContentService;
	@Autowired private DictService dictService;
	
	@GetMapping()
	@RequiresPermissions("blog:bContent:bContent")
	String bContent() {
		return "blog/bContent/bContent";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("blog:bContent:bContent")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<ContentDO> bContentList = bContentService.list(query);
		int total = bContentService.count(query);
		PageUtils pageUtils = new PageUtils(bContentList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("blog:bContent:add")
	String add(Model model) {
		model.addAttribute("langTypes", this.dictService.listByType("CmsLangType"));//将语言类型放到前台
		model.addAttribute("newsTypes", this.dictService.listByType("cmsNewsType"));
		return "blog/bContent/add";
	}

	@GetMapping("/edit/{cid}")
	@RequiresPermissions("blog:bContent:edit")
	String edit(@PathVariable("cid") Long cid, Model model) {
		ContentDO bContentDO = bContentService.get(cid);
		model.addAttribute("bContent", bContentDO);
		return "blog/bContent/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@RequiresPermissions("blog:bContent:add")
	@PostMapping("/save")
	public R save(ContentDO bContent) {
		if (bContent.getAllowComment() == null)
			bContent.setAllowComment(0);
		if (bContent.getAllowFeed() == null) 
			bContent.setAllowFeed(0);
		if(null==bContent.getType()) 
			bContent.setType("article");
		bContent.setGtmCreate(new Date());
		bContent.setGtmModified(new Date());
		int count;
		if (bContent.getCid() == null || "".equals(bContent.getCid().toString()))
			count = bContentService.save(bContent);
		else 
			count = bContentService.update(bContent);
		if (count > 0) 
			return R.ok().put("cid", bContent.getCid());
		return R.error();
	}

	/**
	 * 修改
	 */
	@RequiresPermissions("blog:bContent:edit")
	@ResponseBody
	@RequestMapping("/update")
	public R update( ContentDO bContent) {
		bContent.setGtmCreate(new Date());
		bContentService.update(bContent);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequiresPermissions("blog:bContent:remove")
	@PostMapping("/remove")
	@ResponseBody
	public R remove(Long id) {
		if (bContentService.remove(id) > 0)
			return R.ok();
		return R.error();
	}

	/**
	 * 删除
	 */
	@RequiresPermissions("blog:bContent:batchRemove")
	@PostMapping("/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") Long[] cids) {
		bContentService.batchRemove(cids);
		return R.ok();
	}



}
