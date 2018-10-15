package com.cloudht.cont.controller;

import com.cloudht.blog.domain.ContentDO;
import com.cloudht.blog.service.ContentService;
import com.cloudht.common.annotation.Log;
import com.cloudht.common.controller.BaseController;

import com.cloudht.common.domain.DictDO;
import com.cloudht.common.service.DictService;

import com.cloudht.cont.domain.ContCategoryDO;
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

		Object categoryIdObj = params.get("contCategoryId");
		if(categoryIdObj != null){
			getSession().setAttribute("contCategoryId",categoryIdObj);
		}else {
			categoryIdObj = getSession().getAttribute("contCategoryId");
		}
		params.put("contCategoryId",categoryIdObj);


		//查询列表数据
		Query query = new Query(params);
		query.put("categoryType",0);
		List<ContProductVO> productVOList = contXmxService.listProductByCategory(query);
		int total = contXmxService.countProductByCategory(query);
		PageUtils pageUtils = new PageUtils(productVOList, total);
		
		return pageUtils;
	}
	@Log("/xmx/*")
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

	@RequestMapping("/listCateProdsByProdId")
	@ResponseBody
	public R listCateProdsByProdId(@RequestParam Map<String, Object> params){

		Object productIdObj = params.get("contProductId");
		if(productIdObj!=null){
			getSession().setAttribute("contProductId",productIdObj);
		}else {
			productIdObj = getSession().getAttribute("contProductId");
		}
		params.put("contProductId",productIdObj);

		List<ContProductVO> contProductList = contXmxService.listCateProdsByProdId(params);
		return R.ok().put("rows",contProductList).put("contProductId",productIdObj);
	}


	@PostMapping("/sendInquiry")
	public String sendInquiry(@RequestParam Map<String, Object> params) {
		DictDO dictDO = this.dictService.listByType("mailbox").get(0);
		MailUtils.sendMail("AM网站客户留言信息", "<h3>"+params.toString()+"</h3>", dictDO.getValue());
		return "/xmx/index";
	}


	/**
	 * 打开新闻详情页面
	 * http://localhost:8080/contXmx/openViewNewsDetail
	 * @param cid
	 * @return
	 */
	@GetMapping("/openViewNewsDetail/{cid}")
	public String openViewNewsDetail(@PathVariable("cid") Long cid) {
		getSession().setAttribute("cid",cid);
		return "xmx/articleDetails";
	}

	/**
	 *	获取新闻详情
	 * http://localhost:8080/contXmx/openGetNewsDetail
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/openGetNewsDetail")
	public R openGetNewsDetail() {
		Object cidO = getSession().getAttribute("cid");
		if(cidO==null){
			return R.error("Session超时或未发现新闻主键");
		}
		ContentDO contentDO = bContentService.get(Long.parseLong(cidO.toString()));
		return R.ok().put("row",contentDO);
	}


	/**
	 * 打开产品主页面
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


	/**
	 * 跳转视图
	 * http://localhost:8080/view/viewInquiry
	 * @param target
	 * @return
	 */
	@Log("/xmx/*") 
	@GetMapping("/view/{target}")
	public String view(@PathVariable("target") String target) {
		return "xmx/"+target;
	}


	/**
	 * 查询热门产品
	 * http://localhost:8080/contXmx/listProductByCategoryCode
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listProductByCategoryCode")
	public R listProductByCategoryCode(@RequestParam Map<String, Object> params) {
		List<ContProductVO> contProductVOList = contXmxService.listProductByCategoryCode(params);
		return R.ok().put("rows",contProductVOList);
	}

	/**
	 * 根据等级查询商品信息
	 * http://localhost:8080/contXmx/listRecProduct?limit=10&offset=0&level=11&langType=english
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listRecProduct")
	public R listRecProduct(@RequestParam Map<String, Object> params) {
		List<ContProductVO> contProductVOList = contXmxService.listRecProduct(params);
		return R.ok().put("rows",contProductVOList);
	}



	/**
	 * 获取产品详情页的NavBar列表
	 * http://localhost:8080/contXmx/listProductDetailNavBar
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listProductDetailNavBar")
	public R listProductDetailNavBar(@RequestParam Map<String, Object> params) {

		Object productIdObj = params.get("contProductId");
		if(productIdObj!=null){
			getSession().setAttribute("contProductId",productIdObj);
		}else {
			productIdObj = getSession().getAttribute("contProductId");
		}
		params.put("contProductId",productIdObj);

		List<ContCategoryDO> contCategoryDOList = contXmxService.listProductDetailNavBar(params);
		return R.ok().put("rows",contCategoryDOList);
	}

}
