package com.cloudht.common.controller;

import com.alibaba.fastjson.JSON;
import com.cloudht.common.dao.DatabaseDao;
import com.cloudht.common.domain.SysFileDO;
import com.cloudht.common.service.DatabaseService;
import com.cloudht.common.service.FileService;
import com.cloudht.common.utils.GenUtils;
import com.sxyht.common.utils.FileType;
import com.sxyht.common.utils.PageUtils;
import com.sxyht.common.utils.Query;
import com.sxyht.common.utils.R;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
public class DatabaseController {
	String prefix = "common/generator";
	@Autowired private DatabaseService databaseService;
	@Autowired private FileService sysFileService;
	@Value("${uploadPath}") private String uploadPath;
	@Autowired private DatabaseDao databaseDao;

	@GetMapping("/common/generator")
	String generator() {
		return prefix + "/list";
	}
	
	@GetMapping("/common/generator/backupRestore")
	String backupRestoreView() {
		return prefix + "/backupRestore";
	}

	@ResponseBody
	@GetMapping("/common/generator/list")
	List<Map<String, Object>> list() {
		List<Map<String, Object>> list = this.databaseService.list();
		return list;
	};

	@RequestMapping("/common/generator/code/{tableName}")
	public void code(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("tableName") String tableName) throws IOException {
		String[] tableNames = new String[] { tableName };
		byte[] data = this.databaseService.generatorCode(tableNames);
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=\"cloudht.zip\"");
		response.addHeader("Content-Length", "" + data.length);
		response.setContentType("application/octet-stream; charset=UTF-8");

		IOUtils.write(data, response.getOutputStream());
	}

	@RequestMapping("/common/generator/batchCode")
	public void batchCode(HttpServletRequest request, HttpServletResponse response, String tables) throws IOException {
		String[] tableNames = new String[] {};
		tableNames = JSON.parseArray(tables).toArray(tableNames);
		byte[] data = this.databaseService.generatorCode(tableNames);
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=\"cloudht.zip\"");
		response.addHeader("Content-Length", "" + data.length);
		response.setContentType("application/octet-stream; charset=UTF-8");

		IOUtils.write(data, response.getOutputStream());
	}
	
	@RequestMapping("/common/generator/batchBackup")
	public void batchBackup(HttpServletRequest request, HttpServletResponse response, String tables) throws IOException {
		String[] tableNames = new String[] {};
		tableNames = JSON.parseArray(tables).toArray(tableNames);//将前端传来的表名转换成数组
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();//字节数组输入流
		ZipOutputStream zip = new ZipOutputStream(outputStream);//zip输入流
		for(String tableName : tableNames){//遍历传来的每一张表
			List<Map<String,Object>> listDatas = this.databaseDao.listDatas(tableName);
			List<String> listStrings=new ArrayList<String>();
			zip.putNextEntry(new ZipEntry(tableName+".sql"));
			for(Map<String,Object> map:listDatas) {
				StringBuffer sbf= new StringBuffer();
				sbf.append("insert into " +tableName+" (" );
				Set<String> keySet = map.keySet();
				for(String str3:keySet) {
					sbf.append(str3+",");
				}
				sbf.deleteCharAt(sbf.lastIndexOf(","));//删除最后一个逗号
				sbf.append(") values (");
				for(String str3:keySet) {
					try {
						sbf.append("'"+map.get(str3).toString()+"',");
					} catch (Exception e) {
						sbf.append("''"+",");
					}
				}
				sbf.deleteCharAt(sbf.lastIndexOf(","));//删除最后一个逗号
				sbf.append(");");
				listStrings.add(sbf.toString());
			}
			for(String liu:listStrings) {
				IOUtils.write(liu, zip, "UTF-8");
			}
		}
		IOUtils.closeQuietly(zip);
		byte[] data = outputStream.toByteArray();
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=\"sxyhton.zip\"");
		response.addHeader("Content-Length", "" + data.length);
		response.setContentType("application/octet-stream; charset=UTF-8");
		IOUtils.write(data, response.getOutputStream());
	}

	@GetMapping("/common/generator/edit")
	public String edit(Model model) {
		Configuration conf = GenUtils.getConfig();
		Map<String, Object> property = new HashMap<>(16);
		property.put("author", conf.getProperty("author"));
		property.put("email", conf.getProperty("email"));
		property.put("package", conf.getProperty("package"));
		property.put("autoRemovePre", conf.getProperty("autoRemovePre"));
		property.put("tablePrefix", conf.getProperty("tablePrefix"));
		model.addAttribute("property", property);
		return prefix + "/edit";
	}

	@ResponseBody
	@PostMapping("/common/generator/update")
	R update(@RequestParam Map<String, Object> map) {
		try {
			PropertiesConfiguration conf = new PropertiesConfiguration("generator.properties");
			conf.setProperty("author", map.get("author"));
			conf.setProperty("email", map.get("email"));
			conf.setProperty("package", map.get("package"));
			conf.setProperty("autoRemovePre", map.get("autoRemovePre"));
			conf.setProperty("tablePrefix", map.get("tablePrefix"));
			conf.save();
		} catch (ConfigurationException e) {
			return R.error("保存配置文件出错");
		}
		return R.ok();
	}
	/** 
	 * /common/database/restore
	 * 跳转到还原视图
	 * @return
	 */
	@GetMapping("/common/database/restore")@RequiresPermissions("common:database:restore")
	public String restoreView() {
		return "common/database/restore";
	}
	@ResponseBody
	@PostMapping("/common/database/restore")@RequiresPermissions("common:database:restore")
	public R restore(Long sysFileId) {
		try {
			if(sysFileId==null)
				return null;
			Boolean bool = this.databaseService.tableToSqlByTableUrl(this.sysFileService.get(sysFileId).getUrl());
			return R.ok(bool+"");
		} catch (Exception e) {
			R.error(404,e.getMessage());
		}
		return R.error();
	}
	@ResponseBody
	@GetMapping("/common/database/list")@RequiresPermissions("common:database:restore")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		params.put("type", FileType.fileType("*.sql"));
		// 查询列表数据
		Query query = new Query(params);
		List<SysFileDO> sysFileList = sysFileService.list(query);
		int total = sysFileService.count(query);
		PageUtils pageUtils = new PageUtils(sysFileList, total);
		return pageUtils;
	}
}
