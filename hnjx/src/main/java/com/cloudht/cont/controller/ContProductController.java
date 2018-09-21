package com.cloudht.cont.controller;

import org.springframework.stereotype.Controller;


import com.alibaba.fastjson.JSON;
import com.cloudht.common.service.DictService;
import com.cloudht.cont.domain.ContProductImgDO;
import com.cloudht.cont.domain.ContProductInfoDO;
import com.cloudht.cont.domain.ContProductParamDO;
import com.cloudht.cont.service.ContProductImgService;
import com.cloudht.cont.service.ContProductInfoService;
import com.cloudht.cont.service.ContProductParamService;
import com.cloudht.system.domain.UserDO;
import com.sxyht.common.utils.ShiroUtils;
import org.apache.catalina.User;
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
import com.cloudht.cont.domain.ContProductDO;
import com.cloudht.cont.service.ContProductService;
import com.sxyht.common.utils.PageUtils;
import com.sxyht.common.utils.Query;
import com.sxyht.common.utils.R;

import java.util.*;

/**
 * 
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-18 16:27:29
 */
 
@Controller
@RequestMapping("/cont/contProduct")
public class ContProductController extends BaseController {
	@Autowired
	private ContProductService contProductService;

	@Autowired
	private ContProductImgService contProductImgService;

	@Autowired
	private ContProductInfoService contProductInfoService;

    @Autowired
    private ContProductParamService contProductParamService;

	@Autowired
	private DictService dictService;

	
	@GetMapping()
	@RequiresPermissions("cont:contProduct:contProduct")
	String ContProduct(){
	    return "cont/contProduct/contProduct";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("cont:contProduct:contProduct")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ContProductDO> contProductList = contProductService.list(query);
		int total = contProductService.count(query);
		PageUtils pageUtils = new PageUtils(contProductList, total);
		return pageUtils;
	}
	
	@GetMapping("/leftCategoryList") @RequiresPermissions("cont:contProduct:contProduct")
	public @ResponseBody PageUtils leftCategoryList(@RequestParam Map<String, Object> params){
		return this.contProductService.leftCategoryList(params);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("cont:contProduct:add")
	String add(){
	    return "cont/contProduct/add";
	}

	@GetMapping("/edit/{contProductId}")
	@RequiresPermissions("cont:contProduct:edit")
	String edit(@PathVariable("contProductId") Integer contProductId,Model model){
		ContProductDO contProduct = contProductService.get(contProductId);
		model.addAttribute("contProduct", contProduct);
	    return "cont/contProduct/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("cont:contProduct:add")
	public R save( ContProductDO contProduct){
		contProduct.setCreateBy(getUserId());
		contProduct.setGmtCreate(new Date());
		contProduct.setGmtModified(new Date());
		if(contProductService.save(contProduct)>0)
			return R.ok();
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cont:contProduct:edit")
	public R update( ContProductDO contProduct){
		contProduct.setGmtModified(new Date());
		contProductService.update(contProduct);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("cont:contProduct:remove")
	public R remove( Integer contProductId){
		if(contProductService.remove(contProductId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cont:contProduct:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] contProductIds){
		contProductService.batchRemove(contProductIds);
		return R.ok();
	}


	///new

    @GetMapping("/setInfo/{contProductId}")
    @RequiresPermissions("cont:contProduct:add")
    String setInfo(@PathVariable("contProductId") Integer contProductId,Model model){
		Map<String,Object> map=new HashMap<>();
	    map.put("contProductId",contProductId);
		List<ContProductInfoDO> infoDOList = contProductInfoService.listByDict(map);
		if(infoDOList==null){
			infoDOList=new ArrayList<>();
			model.addAttribute("rows",infoDOList);
		}else {
			model.addAttribute("rows",infoDOList);
		}
		model.addAttribute("langDictList",dictService.listByType("CmsLangType"));
        return "cont/contProduct/setInfo";
    }

    @GetMapping("/setImg/{contProductId}")
    @RequiresPermissions("cont:contProduct:add")
    String setImg(@PathVariable("contProductId") Integer contProductId,Model model){
        Map<String,Object> map=new HashMap<>();
        map.put("contProductId",contProductId);
        List<ContProductImgDO> list = contProductImgService.list(map);
        model.addAttribute("rows",list);
        return "cont/contProduct/setImg";
    }

    @GetMapping("/setParams/{contProductId}")
    @RequiresPermissions("cont:contProduct:add")
    String setParams(@PathVariable("contProductId") Integer contProductId,Model model){
        Map<String,Object> map=new HashMap<>();
        map.put("contProductId",contProductId);
        List<ContProductParamDO> list = contProductParamService.list(map);
        model.addAllAttributes(list);
        return "cont/contProduct/setParams";
    }


	/**
	 * 保存基本信息
	 */
	@ResponseBody
	@PostMapping("/saveInfo")
	@RequiresPermissions("cont:contProduct:add")
	public R saveInfo( String contProductInfo){
		Date date=new Date();
		UserDO user = (UserDO)ShiroUtils.getUser();
		List<ContProductInfoDO> productInfoDOList = JSON.parseArray(contProductInfo, ContProductInfoDO.class);
		for (ContProductInfoDO infoDO:productInfoDOList){
			if(infoDO.getContProductInfoId()==null){//保存
				infoDO.setCreateBy(user.getUserId());
				infoDO.setGmtCreate(date);
				infoDO.setGmtModified(date);
				contProductInfoService.save(infoDO);
			}else{//修改
				infoDO.setGmtModified(date);
				contProductInfoService.update(infoDO);
			}
		}
		return R.ok();
	}

	/**
	 * 保存图片
	 */
	@ResponseBody
	@PostMapping("/saveImg")
	@RequiresPermissions("cont:contProduct:add")
	public R saveImg( ContProductImgDO contProductImg){
		if(contProductImg.getContProductImgId()==null){
			//保存
			if(contProductImgService.save(contProductImg)>0){
				return R.ok();
			}
		}else{//修改
			contProductImgService.update(contProductImg);
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 保存参数
	 */
	@ResponseBody
	@PostMapping("/saveParams")
	@RequiresPermissions("cont:contProduct:add")
	public R saveParams( ContProductParamDO contProductParam){
		if(contProductParam.getContProductParamId()==null){
			//保存
			if(contProductParamService.save(contProductParam)>0){
				return R.ok();
			}
		}else{//修改
            contProductParamService.update(contProductParam);
            return R.ok();
		}
		return R.error();
	}

}
