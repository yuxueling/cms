package com.sxyhton.cont.controller;


import com.alibaba.fastjson.JSON;
import com.sxyhton.common.aspect.Log;

import com.sxyhton.common.controller.BaseController;
import com.sxyhton.common.domain.DictDO;
import com.sxyhton.common.domain.MailSiteDO;
import com.sxyhton.common.domain.Tree;
import com.sxyhton.common.service.DictService;
import com.sxyhton.common.utils.MailUtils;
import com.sxyhton.cont.domain.*;
import com.sxyhton.cont.service.*;
import com.sxyhton.cont.vo.ContProductVO;
import com.sxyht.common.utils.*;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/contTpc")
public class ContTpcController extends BaseController {

    @Autowired
    private ContXmxService contXmxService;
    @Autowired
    private ContentService contentService;
    @Autowired
    private ContCategoryService contCategoryService;
    @Autowired
    private ContFormDataService contFormDataService;
    @Autowired
    private ContFormService contFormService;
    @Autowired
    private DictService dictService;

    /**
     * 初始化首页
     *
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/initIndex")
    public String initIndex(HttpServletRequest request, Model model) {
        String pageAddress = "index";
        this.commonSesssion(pageAddress, request, model);

        //导航栏产品分类
        Map<String, Object> p0 = new HashMap<>();
        p0.put("langType", getSession().getAttribute("langType"));
        Tree<ContCategoryDO> treeInfo = this.contCategoryService.getTreeInfo(p0);
        model.addAttribute("productCateTree", treeInfo);

        //首页推荐产品
        Map<String, Object> p1 = new HashMap<>();
        p1.put("langType", getSession().getAttribute("langType"));
        p1.put("level", 11);
        List<ContProductVO> contProductVOList = contXmxService.listRecProduct(p1);
        model.addAttribute("recProductList", contProductVOList);

        //首页关于我们
        Map<String, Object> p2 = new HashMap<>();
        p2.put("langType", getSession().getAttribute("langType"));
        p2.put("type", "companyProfile");
        List<ContentDO> contentDOList = this.contentService.list(p2);
        if (contentDOList != null && !contentDOList.isEmpty()) {
            model.addAttribute("aboutUsCont", contentDOList.get(0));
        } else {
            model.addAttribute("aboutUsCont", new ContentDO());
        }

        //首页新闻
        Map<String, Object> p3 = new HashMap<>();
        p3.put("langType", getSession().getAttribute("langType"));
        p3.put("type", "companyNew");
        p3.put("limit", 3);
        p3.put("offset", 0);
        Query query = new Query(p3);
        List<ContentDO> newsList = contentService.list(query);
        if (newsList != null && !newsList.isEmpty()) {
            model.addAttribute("newsList", newsList);
            model.addAttribute("newsFirst", newsList.get(0));
        } else {
            model.addAttribute("newsList", new ArrayList());
            model.addAttribute("newsFirst", new ContentDO());
        }

        return "tpc/" + pageAddress;
    }


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

        long cid = 1L;
        Object cidObj = getSession().getAttribute("cid");
        if (cidObj != null) {
            cid = Long.parseLong(cidObj.toString());
        } else {
            return R.error();
        }
        return R.ok().put("row", contentService.get(cid));

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

        //导航栏产品分类
        Map<String, Object> p0 = new HashMap<>();
        p0.put("langType", getSession().getAttribute("langType"));
        Tree<ContCategoryDO> treeInfo = this.contCategoryService.getTreeInfo(p0);
        model.addAttribute("productCateTree", treeInfo);

        return "tpc/" + pageAddress;
    }

    /**
     * OK
     * 打开新闻详情页面
     * http://localhost:8080/contTpc/openViewNewsDetail
     *
     * @param cid
     * @return
     */
    @GetMapping("/openViewNewsDetail/{cid}")
    public String openViewNewsDetail(@PathVariable("cid") Long cid, Model model, HttpServletRequest request) {
        getSession().setAttribute("cid", cid);
        String pageAddress = "a-newsDetail";
        commonSesssion(pageAddress, request, model);

        //导航栏产品分类
        Map<String, Object> p0 = new HashMap<>();
        p0.put("langType", getSession().getAttribute("langType"));
        Tree<ContCategoryDO> treeInfo = this.contCategoryService.getTreeInfo(p0);
        model.addAttribute("productCateTree", treeInfo);

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

        //导航栏产品分类
        Map<String, Object> p0 = new HashMap<>();
        p0.put("langType", getSession().getAttribute("langType"));
        Tree<ContCategoryDO> treeInfo = this.contCategoryService.getTreeInfo(p0);
        model.addAttribute("productCateTree", treeInfo);

        return "tpc/" + target;
    }


    /**
     * 导航栏跳转产品页
     * http://localhost:8080/viewProductCatalog/1
     *
     * @param request
     * @param productActIndex
     * @param model
     * @return
     */
    @Log("/xmx/*")
    @GetMapping("/viewProductCatalog/{contCategoryId}")
    public String viewProductCatalog(HttpServletRequest request, @PathVariable("contCategoryId") Integer contCategoryId, Model model) {
        String pageAddress = "a-productCatalog";
        this.commonSesssion(pageAddress, request, model);
        getSession().setAttribute("contCategoryId", contCategoryId);

        //导航栏产品分类
        Map<String, Object> p0 = new HashMap<>();
        p0.put("langType", getSession().getAttribute("langType"));
        Tree<ContCategoryDO> treeInfo = this.contCategoryService.getTreeInfo(p0);
        model.addAttribute("productCateTree", treeInfo);

        return "tpc/" + pageAddress;
    }


    /**
     * OK
     * 根据等级查询商品信息
     * http://localhost:8080/contTpc/listRecProduct?limit=10&offset=0&level=11&langType=english
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
     * 根据语种及类别查询产品（仅支持到两级类别查询）
     * http://localhost:8084/contTpc/listProductByCategory?limit=10&offset=0&contCategoryId=2
     *
     * @param params
     * @return
     */
    @RequestMapping("/listProductByCategory")
    @ResponseBody
    public PageUtils listProductByCategory(@RequestParam Map<String, Object> params) {

        Object categoryIdObj = params.get("contCategoryId");
        if (categoryIdObj != null) {
            getSession().setAttribute("contCategoryId", categoryIdObj);
        } else {
            categoryIdObj = getSession().getAttribute("contCategoryId");
        }
        params.put("contCategoryId", categoryIdObj);
        params.put("langType", getSession().getAttribute("langType"));

        //查询列表数据
        Query query = new Query(params);
        query.put("categoryType", 0);
        List<ContProductVO> productVOList = contXmxService.listProductByCategory(query);
        int total = contXmxService.countProductByCategory(query);
        PageUtils pageUtils = new PageUtils(productVOList, total);

        return pageUtils;
    }


    /**
     * http://loaclhost:8080/contTpc/saveInquery
     * 询盘信息保存并发送邮件
     * @param contForm
     * @param contFormData
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveInquery")
    public R saveInquery(String contForm, String contFormData) {
        Object langType = getSession().getAttribute("langType");
        Date date = new Date();
        ContFormDO formDO = JSON.parseObject(contForm, ContFormDO.class);
        formDO.setGmtCreate(date);
        formDO.setGmtModified(date);
        formDO.setHaveRead(0);
        formDO.setLangType(langType == null ? "" : langType.toString());
        //保存询盘
        if (contFormService.save(formDO) > 0) {
            List<ContFormDataDO> contFormDataDOS = JSON.parseArray(contFormData, ContFormDataDO.class);
            for (ContFormDataDO formDataDO : contFormDataDOS) {
                formDataDO.setGmtCreate(date);
                formDataDO.setGmtModified(date);
                formDataDO.setContFormId(formDO.getContFormId());
            }
            //保存询盘详情
            if (contFormDataService.batchSave(contFormDataDOS) > 0) {
                //发送邮件
                try {
                    List<DictDO> recipientList = this.dictService.listByType("mailbox");
                    List<DictDO> mailSite = dictService.listByType("cmsMail");
                    MailSiteDO mailSiteDO = new MailSiteDO();
                    //邮件配置
                    for (DictDO dictDO : mailSite) {
                        if ("smtp_host".equals(dictDO.getName()))
                            mailSiteDO.setSmtpHost(dictDO.getValue());
                        if ("username".equals(dictDO.getName()))
                            mailSiteDO.setUsername(dictDO.getValue());
                        if ("password".equals(dictDO.getName()))
                            mailSiteDO.setPassword(dictDO.getValue());
                        if ("from".equals(dictDO.getName()))
                            mailSiteDO.setFrom(dictDO.getValue());
                    }
                    //多收件人发送
                    for (DictDO dictDO : recipientList) {
                        mailSiteDO.setSubject(dictDO.getName());
                        mailSiteDO.setContent(genMailContent(contFormDataDOS));
                        mailSiteDO.setRecipient(dictDO.getValue());
                        MailUtils.sendMail(mailSiteDO);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return R.ok();
            }
        }

        return R.error();

    }

    /**
     * 重新组装询盘信息
     *
     * @param formDataDOList
     * @return
     */
    private String genMailContent(List<ContFormDataDO> formDataDOList) {
        StringBuffer sb = new StringBuffer();
        sb.append("<h4>消息正文：</h4>");
        for (ContFormDataDO formData : formDataDOList) {
            sb.append("<strong>" + formData.getTitle() + "：</strong>" + formData.getValue() + "</br>");
        }
        return sb.toString();
    }


    /**
     * 获取分类详情
     * http://localhost:8080/conTpc/getCategoryInfo
     *
     * @param params conCategoryId
     * @return
     */
    @RequestMapping("/getCategoryInfo")
    @ResponseBody
    public R getCategoryInfo(@RequestParam Map<String, Object> params) {
        params.put("langType", getSession().getAttribute("langType"));
        List<ContCategoryInfoDO> categoryInfoDOList = contXmxService.listCategoryInfo(params);
        if (categoryInfoDOList != null && !categoryInfoDOList.isEmpty()) {
            if (categoryInfoDOList.size() == 1) {
                return R.ok().put("row", categoryInfoDOList.get(0));
            } else {
                return R.ok().put("rows", categoryInfoDOList);
            }
        }
        return R.error();
    }


}
