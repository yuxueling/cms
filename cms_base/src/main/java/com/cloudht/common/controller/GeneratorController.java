package com.cloudht.common.controller;

import com.alibaba.fastjson.JSON;
import com.cloudht.common.dao.GeneratorMapper;
import com.cloudht.common.service.GeneratorService;
import com.cloudht.common.utils.GenUtils;
import com.sxyht.common.utils.R;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

@RequestMapping("/common/generator")
@Controller
public class GeneratorController {
	String prefix = "common/generator";
	@Autowired
	GeneratorService generatorService;
	@Autowired  GeneratorMapper generatorMapper;

	@GetMapping()
	String generator() {
		return prefix + "/list";
	}
	
	@GetMapping("/backupRestore")
	String backupRestoreView() {
		return prefix + "/backupRestore";
	}

	@ResponseBody
	@GetMapping("/list")
	List<Map<String, Object>> list() {
		List<Map<String, Object>> list = generatorService.list();
		return list;
	};

	@RequestMapping("/code/{tableName}")
	public void code(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("tableName") String tableName) throws IOException {
		String[] tableNames = new String[] { tableName };
		byte[] data = generatorService.generatorCode(tableNames);
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=\"cloudht.zip\"");
		response.addHeader("Content-Length", "" + data.length);
		response.setContentType("application/octet-stream; charset=UTF-8");

		IOUtils.write(data, response.getOutputStream());
	}

	@RequestMapping("/batchCode")
	public void batchCode(HttpServletRequest request, HttpServletResponse response, String tables) throws IOException {
		String[] tableNames = new String[] {};
		tableNames = JSON.parseArray(tables).toArray(tableNames);
		byte[] data = generatorService.generatorCode(tableNames);
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=\"cloudht.zip\"");
		response.addHeader("Content-Length", "" + data.length);
		response.setContentType("application/octet-stream; charset=UTF-8");

		IOUtils.write(data, response.getOutputStream());
	}
	
	@RequestMapping("/batchBackup")
	public void batchBackup(HttpServletRequest request, HttpServletResponse response, String tables) throws IOException {
		String[] tableNames = new String[] {};
		tableNames = JSON.parseArray(tables).toArray(tableNames);//将前端传来的表名转换成数组
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();//字节数组输入流
		ZipOutputStream zip = new ZipOutputStream(outputStream);//zip输入流
		for(String tableName : tableNames){//遍历传来的每一张表
			List<Map<String,Object>> listDatas = generatorMapper.listDatas(tableName);
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

	@GetMapping("/edit")
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
	@PostMapping("/update")
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
}
