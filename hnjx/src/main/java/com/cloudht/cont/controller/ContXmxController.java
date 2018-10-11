package com.cloudht.cont.controller;

import com.cloudht.blog.domain.ContentDO;
import com.cloudht.blog.service.ContentService;
import com.cloudht.common.controller.BaseController;

import com.cloudht.common.domain.DictDO;
import com.cloudht.common.service.DictService;

import com.cloudht.cont.service.ContXmxService;
import com.cloudht.cont.vo.ContProductVO;
import com.sxyht.common.utils.*;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/contXmx")
public class ContXmxController extends BaseController {

	@Autowired private ContXmxService contXmxService;
	@Autowired private DictService dictService;
	@Autowired
	ContentService bContentService;


	/**
	 * 根据语种及类别查询产品（仅支持到两级类别查询）
	 * http://localhost:8084/contXmx/listProductByCategory?limit=10&offset=0&contCategoryId=2&langType=simChinese
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

	@GetMapping("/showProduct/{contProductId}")
	String showProduct(@PathVariable("contProductId") Integer contProductId){
 		getSession().setAttribute("contProductId",contProductId);
		return "xmx/a-productDetail";
	}


	@RequestMapping("/getProduct")
	@ResponseBody
	public R getProduct(@RequestParam Map<String, Object> params){
		Object contProductId = getSession().getAttribute("contProductId");
		params.put("contProductId",contProductId);
		ContProductVO contProduct = contXmxService.getProduct(params);

		return R.ok().put("row",contProduct);
	}


	@PostMapping("/sendInquiry")
	public String sendInquiry(@RequestParam Map<String, Object> params) {
		DictDO dictDO = this.dictService.listByType("mailbox").get(0);
		MailUtils.sendMail("AM网站客户留言信息", "<h3>"+params.toString()+"</h3>", dictDO.getValue());
		return "/xmx/index";
	}


	/**
	 * http://localhost:8080/contXmx/openViewDetail
	 * @param cid
	 * @return
	 */
	@GetMapping("/openViewDetail/{cid}")
	public String openViewDetail(@PathVariable("cid") Long cid) {
		getSession().setAttribute("cid",cid);
		return "xmx/articleDetails";
	}

	/**
	 * http://localhost:8080/contXmx/openGet
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/openGet")
	public R openGet() {
		Object cidO = getSession().getAttribute("cid");
		if(cidO==null){
			return R.error("Session超时或未发现新闻主键");
		}
		ContentDO contentDO = bContentService.get(Long.parseLong(cidO.toString()));
		return R.ok().put("row",contentDO);
	}


	/**
	 * http://localhost:8080/contXmx/openViewListProduct
	 * @param contCategoryId
	 * @param categoryName
	 * @return
	 */
	@GetMapping("/openViewListProduct/{contCategoryId}/{categoryName}")
	public String openViewListProduct(@PathVariable("contCategoryId") Integer contCategoryId,@PathVariable("categoryName") String categoryName) {
		Session session = getSession();
		session.setAttribute("contCategoryId",contCategoryId);
		session.setAttribute("categoryName",categoryName);
		return "xmx/a-products";
	}


}
