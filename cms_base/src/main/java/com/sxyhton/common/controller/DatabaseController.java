package com.sxyhton.common.controller;

import com.alibaba.fastjson.JSON;
import com.sxyht.common.utils.FileType;
import com.sxyht.common.utils.FileUtil;
import com.sxyht.common.utils.PageUtils;
import com.sxyht.common.utils.Query;
import com.sxyht.common.utils.R;
import com.sxyhton.common.dao.DatabaseDao;
import com.sxyhton.common.domain.SysFileDO;
import com.sxyhton.common.service.DatabaseService;
import com.sxyhton.common.service.FileService;
import com.sxyhton.common.utils.GenUtils;

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

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

@Controller
public class DatabaseController {
	String prefix = "common/database";
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
	List<Map<String, Object>> list(String tablePrefix) {
		List<Map<String, Object>> list = this.databaseService.list();
		List<Map<String, Object>> list2 = new LinkedList<>();
		if(tablePrefix!=null) {//不等于null的时候将不相关的表干掉
			 for(int i=0;i<list.size();i++) {
				 Map<String, Object> map = list.get(i);
				 String string = map.get("tableName").toString();
				 int indexOf = string.indexOf("_");
				 String substring = string.substring(0, indexOf);
				 if(tablePrefix.equals(substring))
					 list2.add(list.get(i));
			 }
			 return list2;
		}
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
	
	@RequestMapping("/common/database/batchBackup")
	public void batchBackup(String tablePrefix,HttpServletRequest request,
			HttpServletResponse response,String tables) throws IOException {
		String[] tableNames = new String[] {};
		if(!"null".equals(tablePrefix)) {
			List<Map<String, Object>> list = this.list(tablePrefix);
			tableNames=new String[list.size()];
			int i=0;
			for (Map<String, Object> map :list) {
				String tableName = map.get("tableName").toString();
				tableNames[i]=tableName;
				i++;
			}
		}else {
			tableNames = JSON.parseArray(tables).toArray(tableNames);//将前端传来的表名转换成数组
		}
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();//字节数组输入流
		ZipOutputStream zip = new ZipOutputStream(outputStream);//zip输入流
		for(String tableName : tableNames){//遍历传来的每一张表
			List<Map<String,Object>> listDatas = this.databaseDao.listDatas(tableName);//查询每一张表中的数据
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
				sbf.append(")孎");
				listStrings.add(sbf.toString());
			}
			for(String liu:listStrings) {
				IOUtils.write(liu, zip, "UTF-8");
			}
		}
		IOUtils.closeQuietly(zip);
		byte[] data = outputStream.toByteArray();
		try{ //保存到本地路径，并且存到数据库
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
			String formatStr =formatter.format(new Date())+".zip";
			FileUtil.uploadFile(data, this.uploadPath, "backup"+formatStr);
			SysFileDO sysFile = new SysFileDO(FileType.fileType("backup"+formatStr),"/files/"+"backup"+formatStr,new Date());
			String bolle=sysFileService.save(sysFile)>0?"成功保存到了数据库":"保存失败";
			System.out.println(bolle);
		}catch(Exception e) {}
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
	/**
     * 需要读取zip文件项的内容时，需要ZipFile类的对象的getInputStream方法取得该项的内容，
     * 然后传递给InputStreamReader的构造方法创建InputStreamReader对象，
     * 最后使用此InputStreamReader对象创建BufferedReader实例
     * 至此已把zip文件项的内容读出到缓存中，可以遍历其内容
     */
	@ResponseBody
	@PostMapping("/common/database/restore")@RequiresPermissions("common:database:restore")
	public R restore(Long sysFileId) {
		if(sysFileId==null)
			return null;
		String url = this.sysFileService.get(sysFileId).getUrl();
		url = url.replace("/files/", "");
		String zipPath=this.uploadPath+url;
		ZipInputStream zipIn =null;
		try {
			zipIn = new ZipInputStream(new FileInputStream(zipPath));
		} catch (Exception e) {
			R.error(404,e.getMessage());
		}
		ZipEntry zipEn = null;
		ZipFile zfil = null;
		try {
            zfil = new ZipFile(zipPath);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
		try {
            while ((zipEn = zipIn.getNextEntry()) != null) {
                if (!zipEn.isDirectory()) { // 判断此zip项是否为目录
                	String tableName = zipEn.getName().substring(0, zipEn.getName().indexOf("."));
                    /**
                     * 把是文件的zip项读出缓存，
                     * zfil.getInputStream(zipEn)：返回输入流读取指定zip文件条目的内容 zfil：new
                     * ZipFile();供阅读的zip文件 zipEn：zip文件中的某一项
                     */
                    BufferedReader buff = new BufferedReader(new InputStreamReader(zfil.getInputStream(zipEn)));
                    String str;
                    this.databaseDao.truncate(tableName);
                    while ((str = buff.readLine()) != null) {
                    	String[] split = str.split("孎", 0);
                    	for(String insert:split) {
                    		this.databaseDao.insert(insert);
                    	}
                    }
                    buff.close();
                }
                zipIn.closeEntry();// 关闭当前打开的项
            }
            return R.ok();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                zfil.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		return R.error();
	}
	@ResponseBody
	@GetMapping("/common/database/list")@RequiresPermissions("common:database:restore")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		params.put("type", FileType.fileType("*.zip"));
		// 查询列表数据
		Query query = new Query(params);
		List<SysFileDO> sysFileList = sysFileService.list(query);
		int total = sysFileService.count(query);
		PageUtils pageUtils = new PageUtils(sysFileList, total);
		return pageUtils;
	}
	/**
	 * 将表摧毁，数据库新数据从0 开始
	 * @param tableName
	 * @return
	 */
	@PostMapping("/common/database/truncate")@RequiresPermissions("common:database:truncate")@ResponseBody
	public R truncate(String tableName) {
		if(this.databaseService.truncate(tableName)==1)
			return R.ok();
		return R.error();
	}
}
