package com.sxyhton.cont.controller;

import java.util.List;
import java.util.Map;

import com.sxyhton.common.utils.MailUtils;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sxyht.common.utils.PageUtils;
import com.sxyht.common.utils.Query;
import com.sxyht.common.utils.R;
import com.sxyhton.common.aspect.Log;
import com.sxyhton.common.controller.BaseController;
import com.sxyhton.common.domain.DictDO;
import com.sxyhton.common.domain.Tree;
import com.sxyhton.common.service.DictService;
import com.sxyhton.cont.domain.ContCategoryDO;
import com.sxyhton.cont.domain.ContCategoryInfoDO;
import com.sxyhton.cont.domain.ContSeoDO;
import com.sxyhton.cont.domain.ContentDO;
import com.sxyhton.cont.service.ContCategoryService;
import com.sxyhton.cont.service.ContSeoService;
import com.sxyhton.cont.service.ContXmxService;
import com.sxyhton.cont.service.ContentService;
import com.sxyhton.cont.vo.ContProductVO;

@Controller
@RequestMapping("/yutai")
public class YutaiController extends BaseController {
	@Autowired private DictService dictService;
	@Autowired private ContSeoService contSeoService;
	@Autowired private ContXmxService contXmxService;
	@Autowired private ContCategoryService contCategoryService;
	@Autowired private ContentService contentService;
	//@Autowired private ContProductService contProductService;

	@Log("yutai")
	@GetMapping("/{view}")
	public String yutai(HttpServletRequest request,@RequestParam Map<String,Object> params,@PathVariable("view")String view,Model model) {
		this.commonSesssion(view,request,model);
		params.put("langType", this.getSession().getAttribute("langType"));
		if("Resource-4".equals(view)) {//如果请求的是新闻列表页面
			List<ContentDO> list = contentService.list(params);
			model.addAttribute("contents", list);
			return "yutai/"+view;
		}
		if(params.get("productsClassify")!=null) 
			model.addAttribute("productsClassify", params.get("productsClassify"));
		if(view.equals("products")) {//判断页面是否为产品页面将分类列表放入页面中,页面会根据选中的
			model.addAttribute("categorys", this.contCategoryService.list(null));
			return "yutai/"+view;
		}
		return "yutai/"+view;
	}
	/**
	 * 当页面请求产品详情时的处理逻辑，包含点击下一个详情的逻辑
	 * @param params contProductId当前的id；langType 语言类型
	 * @param model
	 */
	@GetMapping("/product/details")
	public String productDetails(HttpServletRequest request,@RequestParam Map<String,Object> params,Model model) {
		this.commonSesssion("product", request, model);
		if(params.get("contProductId")!=null) {//当页面请求产品详情时此id会有值
			String str = params.get("contProductId").toString();
			model.addAttribute("nextProductId", this.contXmxService.queryNextContProductId(Integer.parseInt(str)));//计算下一款产品的id
			model.addAttribute("prevProductId", this.contXmxService.queryPrevContProductId(Integer.parseInt(str)));//计算上一款产品的id
			params.put("langType", this.getSession().getAttribute("langType"));
			model.addAttribute("product", contXmxService.getProduct(params));//将产品详情置入页面对象中					
			return "yutai/project-1-1";
		}
		return "yutai/index";
		
	}
	/**
	 * 根据前端传来的条件查询对应的产品集合
	 * @param params limit=10&offset=0&contCategoryId=2&langType=en
	 * @return
	 */
	@PostMapping("/products/list")@ResponseBody
	public PageUtils productsList(@RequestParam Map<String,Object> params) {
		if(getSession().getAttribute("langType")!=null)
			params.put("langType", getSession().getAttribute("langType"));
		params.put("categoryType", 0);
		Query query = new Query(params);
		List<ContProductVO> productVOList = contXmxService.listProductByCategory(query);
		int total = contXmxService.countProductByCategory(query);
		PageUtils pageUtils = new PageUtils(productVOList, total);
		return pageUtils;
	}
	/**
	 * 加载分类含详情
	 * @param params lang_type
	 * @return
	 */
	@RequestMapping("/classify/treeInfo")
	@ResponseBody
	public Tree<ContCategoryDO> treeInfo(@RequestParam Map<String, Object> params) {
		params.put("langType", this.getSession().getAttribute("langType").toString());
		params.remove("offset");
		params.remove("limit");
		Tree<ContCategoryDO> treeInfo = this.contCategoryService.getTreeInfo(params);
		return treeInfo;
	}


	/**
	 * 根据语种\类别\产品名称模糊查询产品（仅支持到两级类别查询）
	 * http://localhost:8084/contXmx/searchProductByCategory?limit=10&offset=0&contCategoryId=2&langType=simChinese&searchKey=yuthf
	 * @param params
	 * @return
	 */
	@RequestMapping("/classify/searchProductByCategory")
	@ResponseBody
	public PageUtils searchProductByCategory(@RequestParam Map<String, Object> params){

		Object categoryIdObj = params.get("contCategoryId");
		Object searchKeyObj = params.get("searchKey");
		if(categoryIdObj == null || categoryIdObj.toString().equals("")){
			categoryIdObj = getSession().getAttribute("contCategoryId");
		}else {
			getSession().setAttribute("contCategoryId",categoryIdObj);
		}
		if(searchKeyObj==null || searchKeyObj.toString().equals("")){
			searchKeyObj = getSession().getAttribute("searchKey");
		}

		params.put("contCategoryId",categoryIdObj);
		params.put("searchKey",searchKeyObj);

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
	String showProduct(HttpServletRequest request, @PathVariable("contProductId") Integer contProductId,Map<String,Object> map,Model model){
		String pageAddress="showProduct";
		this.commonSesssion(pageAddress, request, model);
 		getSession().setAttribute("contProductId",contProductId);
 		map.clear();map.put("pageAddress", "showProduct");
 		//map.put("langType", "langType");//如有需要传入语言类型
 		List<ContSeoDO> list = this.contSeoService.list(map);
 		if(list.size()!=0) {
 			for(ContSeoDO contSeoDO:list) {
 				String seoTitle = contSeoDO.getSeoTitle();
 				if(!("null".equals(seoTitle)&&"".equals(seoTitle))) {
 					model.addAttribute("seoTitle", seoTitle);
 				}
 			}
 			model.addAttribute("metaSeo", list);
 		}
		return "xmx/a-productDetail";
	}

	@GetMapping("/classify/viewSearch")
	String viewSearch(HttpServletRequest request,@RequestParam Map<String, Object> params,Model model){
		String pageAddress="viewSearch";
		this.commonSesssion(pageAddress, request, model);
		getSession().setAttribute("contCategoryId",params.get("contCategoryId"));
		getSession().setAttribute("searchKey",params.get("searchKey"));
		params.clear();params.put("pageAddress", pageAddress);
 		//map.put("langType", "langType");//如有需要传入语言类型
 		List<ContSeoDO> list = this.contSeoService.list(params);
 		if(list.size()!=0) {
 			for(ContSeoDO contSeoDO:list) {
 				String seoTitle = contSeoDO.getSeoTitle();
 				if(!("null".equals(seoTitle)&&"".equals(seoTitle))) {
 					model.addAttribute("seoTitle", seoTitle);
 				}
 			}
 			model.addAttribute("metaSeo", list);
 		}
		return "xmx/a-search";
	}


	@RequestMapping("/classify/getProduct")
	@ResponseBody
	public R getProduct(@RequestParam Map<String, Object> params){
		Object contProductId = getSession().getAttribute("contProductId");
		params.put("contProductId",contProductId);
		ContProductVO contProduct = contXmxService.getProduct(params);

		return R.ok().put("row",contProduct);
	}

	@RequestMapping("/classify/listCateProdsByProdId")
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


	/**
	 * 打开新闻详情页面
	 * http://localhost:8080/contXmx/openViewNewsDetail
	 * @param cid
	 * @return
	 */
	@GetMapping("/openViewNewsDetail/{cid}")
	public String openViewNewsDetail(@PathVariable("cid") Long cid,Map<String,Object> map,Model model) {
		getSession().setAttribute("cid",cid);
		map.clear();map.put("pageAddress", "openViewNewsDetail");
 		//map.put("langType", "langType");//如有需要传入语言类型
 		List<ContSeoDO> list = this.contSeoService.list(map);
 		if(list.size()!=0) {
 			for(ContSeoDO contSeoDO:list) {
 				String seoTitle = contSeoDO.getSeoTitle();
 				if(!("null".equals(seoTitle)&&"".equals(seoTitle))) {
 					model.addAttribute("seoTitle", seoTitle);
 				}
 			}
 			model.addAttribute("metaSeo", list);
 		}
		return "xmx/a-articleDetails";
	}

	/**
	 *	获取新闻详情
	 * http://localhost:8080/contXmx/openGetNewsDetail
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/classify/openGetNewsDetail")
	public R openGetNewsDetail() {
		Object cidO = getSession().getAttribute("cid");
		if(cidO==null){
			return R.error("Session超时或未发现新闻主键");
		}
		ContentDO contentDO = contentService.get(Long.parseLong(cidO.toString()));
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
	public String openViewListProduct(@PathVariable("contCategoryId") Integer contCategoryId,
			@PathVariable("categoryName") String categoryName,Map<String,Object> map,Model model) {
		map.clear();map.put("pageAddress", "openViewListProduct");
 		//map.put("langType", "langType");//如有需要传入语言类型
 		List<ContSeoDO> list = this.contSeoService.list(map);
 		if(list.size()!=0) {
 			for(ContSeoDO contSeoDO:list) {
 				String seoTitle = contSeoDO.getSeoTitle();
 				if(!("null".equals(seoTitle)&&"".equals(seoTitle))) {
 					model.addAttribute("seoTitle", seoTitle);
 				}
 			}
 			model.addAttribute("metaSeo", list);
 		}
		Session session = getSession();
		session.setAttribute("contCategoryId",contCategoryId);
		session.setAttribute("categoryName",categoryName);
		return "xmx/a-products";
	}

	/**
	 * 查询热门产品
	 * http://localhost:8080/contXmx/listProductByCategoryCode
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/classify/listProductByCategoryCode")
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
	@RequestMapping("/classify/listRecProduct")
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
	@RequestMapping("/classify/listProductDetailNavBar")
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


	/**
	 * 获取文章列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/classify/listContent")
	@ResponseBody
	public R listContent(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<ContentDO> contentDOList = contentService.list(query);
		int total = contentService.count(query);
		return R.ok().put("rows",contentDOList).put("total",total);
	}

	/**
	 * 获取分类详情
	 * http://localhost:8080/contXmx/getCategoryInfo
	 * @param params langType  conCategoryId
	 * @return
	 */
	@RequestMapping("/classify/getCategoryInfo")
	@ResponseBody
	public R getCategoryInfo(@RequestParam Map<String, Object> params){
		List<ContCategoryInfoDO> categoryInfoDOList = contXmxService.listCategoryInfo(params);
		if(categoryInfoDOList!=null && !categoryInfoDOList.isEmpty()){
			return R.ok().put("row",categoryInfoDOList.get(0));
		}
		return R.error();
	}
}
