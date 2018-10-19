package com.cloudht.cont.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cloudht.common.controller.BaseController;
import com.cloudht.common.domain.FileDO;
import com.cloudht.cont.domain.ContProductImgDO;
import com.cloudht.cont.service.ContProductImgService;
import com.sxyht.common.utils.FileType;
import com.sxyht.common.utils.FileUtil;
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
public class ContProductImgController extends BaseController {
	@Autowired
	private ContProductImgService contProductImgService;
	
	@Value("${uploadPath}") String uploadPath;
	
	@GetMapping()
	@RequiresPermissions("cont:contProductImg:contProductImg")
	String ContProductImg(){
	    return "cont/contProductImg/contProductImg";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cont:contProduct:contProduct")
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
	@RequiresPermissions("cont:contProduct:contProduct")
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
	@ResponseBody
	@PostMapping("/upload/{contProductId}")
	R upload(@RequestParam("file") MultipartFile file,@PathVariable("contProductId") Integer contProductId) {
		String fileName = file.getOriginalFilename();
		fileName = FileUtil.renameToUUID(fileName);
		ContProductImgDO contProductImg=new ContProductImgDO();
		contProductImg.setImgUrl("/files/" + fileName);
		contProductImg.setContProductId(contProductId);
		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date());
		try {
			FileUtil.uploadFile(file.getBytes(), this.uploadPath, fileName);
		} catch (Exception e) {
			return R.error();
		}
		if (this.contProductImgService.save(contProductImg)>0) 
			return R.ok().put("fileName",sysFile.getUrl());
		return R.error();
	}
	
}
