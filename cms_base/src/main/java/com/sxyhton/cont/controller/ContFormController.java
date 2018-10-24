package com.sxyhton.cont.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.sxyht.common.utils.MailUtils;
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
import com.sxyhton.common.controller.BaseController;
import com.sxyhton.common.domain.DictDO;
import com.sxyhton.common.service.DictService;
import com.sxyhton.cont.domain.ContFormDO;
import com.sxyhton.cont.domain.ContFormDataDO;
import com.sxyhton.cont.service.ContFormDataService;
import com.sxyhton.cont.service.ContFormService;

/**
 * 表单表
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-27 08:56:13
 */
 
@Controller
@RequestMapping("/cont/contForm")
public class ContFormController extends BaseController{
	@Autowired private ContFormService contFormService;

	@Autowired
	private ContFormDataService contFormDataService;

	@Autowired
	private DictService dictService;
	
	@GetMapping() @RequiresPermissions("cont:contForm:contForm")
	String ContForm(){
	    return "cont/contForm/contForm";
	}
	@GetMapping("/list") @RequiresPermissions("cont:contForm:contForm") @ResponseBody
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ContFormDO> contFormList = contFormService.list(query);
		int total = contFormService.count(query);
		PageUtils pageUtils = new PageUtils(contFormList, total);
		return pageUtils;
	}
	
	@GetMapping("/add") @RequiresPermissions("cont:contForm:add")
	String add(Model model){

	    return "cont/contForm/add";
	}

	@GetMapping("/edit/{contFormId}") @RequiresPermissions("cont:contForm:edit")
	String edit(@PathVariable("contFormId") Integer contFormId,Model model){
		ContFormDO contForm = contFormService.get(contFormId);
		model.addAttribute("contForm", contForm);
	    return "cont/contForm/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save") @RequiresPermissions("cont:contForm:add")
	public R save( ContFormDO contForm){
		contForm.setGmtCreate(new Date());
		contForm.setHaveRead(0);
		if(contFormService.save(contForm)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update") @RequiresPermissions("cont:contForm:edit")
	public R update( ContFormDO contForm){
		contFormService.update(contForm);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove") @RequiresPermissions("cont:contForm:remove") @ResponseBody
	public R remove( Integer contFormId){
		if(contFormService.remove(contFormId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("cont:contForm:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] contFormIds){
		contFormService.batchRemove(contFormIds);
		return R.ok();
	}


	//new

	/**
	 * http://loaclhost:8080/cont/contForm/openSave
	 * @param contForm
	 * @param contFormData
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/openSave")
	public R openSave( String contForm,String contFormData){
		Object langType = getSession().getAttribute("langType");
		Date date = new Date();
		ContFormDO formDO = JSON.parseObject(contForm, ContFormDO.class);
		formDO.setGmtCreate(date);
		formDO.setGmtModified(date);
		formDO.setHaveRead(0);
		formDO.setLangType(langType==null?"":langType.toString());
		if(contFormService.save(formDO)>0){
			List<ContFormDataDO> contFormDataDOS = JSON.parseArray(contFormData, ContFormDataDO.class);
			for (ContFormDataDO formDataDO:contFormDataDOS){
				formDataDO.setGmtCreate(date);
				formDataDO.setGmtModified(date);
				formDataDO.setContFormId(formDO.getContFormId());
			}
			if (contFormDataService.batchSave(contFormDataDOS) > 0) {

				try {
					List<DictDO> dictDOList = this.dictService.listByType("mailbox");
					for (DictDO dictDO: dictDOList){
						MailUtils.sendMail(dictDO.getName(), "<h3>"+contFormData+"</h3>", dictDO.getValue());
					}
				}catch (Exception e){

				}

				return R.ok();
			}
		}

		return R.error();
	}


}
