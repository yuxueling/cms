package com.cloudht.cont.controller;

import com.cloudht.common.controller.BaseController;
import com.cloudht.common.domain.Tree;
import com.cloudht.cont.domain.ContCategoryDO;
import com.cloudht.cont.domain.ContProductPkDO;
import com.cloudht.cont.service.ContCategoryService;
import com.cloudht.cont.service.ContXmxService;
import com.cloudht.cont.vo.ContProductVO;
import com.sxyht.common.utils.PageUtils;
import com.sxyht.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/contXmx")
public class ContXmxController extends BaseController {

	@Autowired
	private ContXmxService contXmxService;

	@Autowired
	private ContCategoryService contCategoryService;


	/**
	 * 根据语种及类别查询产品（仅支持到两级类别查询）
	 * http://localhost:8080/xmx/listProductByCategory?limit=10&offset=0&contCategoryId=2&langType=simChinese
	 * @param params
	 * @return
	 */
	@RequestMapping("/listProductByCategory")
	@ResponseBody
	public PageUtils listProductByCategory(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		query.put("categoryType",0);
		List<ContProductVO> productVOList = contXmxService.listProductByCategory(query);
		int total = contXmxService.countProductByCategory(query);
		PageUtils pageUtils = new PageUtils(productVOList, total);
		return pageUtils;
	}


}
