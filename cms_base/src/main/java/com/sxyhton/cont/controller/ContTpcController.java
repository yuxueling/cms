package com.sxyhton.cont.controller;

import com.sxyhton.common.annotation.Log;
import com.sxyhton.common.controller.BaseController;
import com.sxyhton.common.domain.Tree;
import com.sxyhton.cont.domain.ContCategoryDO;
import com.sxyhton.cont.domain.ContentDO;
import com.sxyhton.cont.service.ContCategoryService;
import com.sxyhton.cont.service.ContXmxService;
import com.sxyhton.cont.service.ContentService;
import com.sxyhton.cont.vo.ContProductVO;
import com.sxyht.common.utils.*;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/contTpc")
public class ContTpcController extends BaseController {

    @Autowired
    private ContXmxService contXmxService;
    @Autowired
    private ContentService contentService;
    @Autowired
    private ContCategoryService contCategoryService;


    /**
     * OK
     * 获取文章
     *
     * @param params
     * @return
     */
    @RequestMapping("/getContent")
    @ResponseBody
    public R getContent(@RequestParam Map<String, Object> params) {
        params.put("langType", getSession().getAttribute("langType"));
        List<ContentDO> contentDOList = this.contentService.list(params);
        if (contentDOList != null && !contentDOList.isEmpty()) {
            return R.ok().put("aboutUsCont", contentDOList.get(0));
        } else {
            return R.ok().put("aboutUsCont", new ContentDO());
        }
    }

    /**
     * OK
     * 展示产品页
     *
     * @param request
     * @param contProductId
     * @param model
     * @return
     */
    @Log("/xmx/*")
    @GetMapping("/showProduct/{contProductId}")
    String showProduct(HttpServletRequest request, @PathVariable("contProductId") Integer contProductId, Model model) {
        String pageAddress = "a-productCatalog";
        this.commonSesssion(pageAddress, request, model);
        getSession().setAttribute("contProductId", contProductId);
        return "tpc/" + pageAddress;
    }

    /**
     * OK
     * 打开新闻详情页面
     * http://localhost:8080/contXmx/openViewNewsDetail
     *
     * @param cid
     * @return
     */
    @GetMapping("/openViewNewsDetail/{cid}")
    public String openViewNewsDetail(@PathVariable("cid") Long cid, Model model, HttpServletRequest request) {
        getSession().setAttribute("cid", cid);
        String pageAddress = "a-articleDetails";
        commonSesssion(pageAddress, request, model);
        return "tpc/" + pageAddress;
    }

    /**
     * OK
     * 跳转视图
     * http://localhost:8080/view/viewInquiry
     *
     * @param target
     * @return
     */
    @Log("/xmx/*")
    @GetMapping("/view/{target}")
    public String view(HttpServletRequest request, @PathVariable("target") String target, Model model) {
        this.commonSesssion(target, request, model);
        return "tpc/" + target;
    }

    /**
     * 加载分类含详情
     *
     * @param params lang_type
     * @return
     */
    @RequestMapping("/treeInfo")
    @ResponseBody
    private Tree<ContCategoryDO> treeInfo(@RequestParam Map<String, Object> params) {
        params.put("langType", getSession().getAttribute("langType"));
        Tree<ContCategoryDO> treeInfo = contCategoryService.getTreeInfo(params);
        return treeInfo;
    }


    /**
     * OK
     * 根据等级查询商品信息
     * http://localhost:8080/contXmx/listRecProduct?limit=10&offset=0&level=11&langType=english
     *
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping("/listRecProduct")
    public R listRecProduct(@RequestParam Map<String, Object> params) {
        List<ContProductVO> contProductVOList = contXmxService.listRecProduct(params);
        return R.ok().put("rows", contProductVOList);
    }

    /**
     * OK
     * 获取文章列表
     *
     * @param params
     * @return
     */
    @RequestMapping("/listContent")
    @ResponseBody
    public R listContent(@RequestParam Map<String, Object> params) {
        params.put("langType", getSession().getAttribute("langType"));
        //查询列表数据
        Query query = new Query(params);
        List<ContentDO> contentDOList = contentService.list(query);
        int total = contentService.count(query);
        return R.ok().put("rows", contentDOList).put("total", total);
    }


    /**
     * 打开产品主页面
     * http://localhost:8080/contXmx/openViewListProduct
     *
     * @param contCategoryId
     * @param categoryName
     * @return
     */
    @GetMapping("/openViewListProduct/{contCategoryId}/{categoryName}")
    public String openViewListProduct(@PathVariable("contCategoryId") Integer contCategoryId,
                                      @PathVariable("categoryName") String categoryName, Model model, HttpServletRequest request) {

        String pageAddress = "a-productCatalog";
        this.commonSesssion(pageAddress, request, model);

        Session session = getSession();
        session.setAttribute("contCategoryId", contCategoryId);
        session.setAttribute("categoryName", categoryName);
        return "tpc/" + pageAddress;
    }


}
