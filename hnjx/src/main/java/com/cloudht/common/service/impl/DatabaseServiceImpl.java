package com.cloudht.common.service.impl;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cloudht.common.dao.DatabaseDao;
import com.cloudht.common.service.DatabaseService;
import com.cloudht.common.utils.GenUtils;

@Service
public class DatabaseServiceImpl implements DatabaseService {
	@Autowired
	DatabaseDao generatorMapper;
	@Value("${uploadPath}") private String uploadPath;

	@Override
	public List<Map<String, Object>> list() {
		List<Map<String, Object>> list = generatorMapper.list();
		return list;
	}

	@Override
	public byte[] generatorCode(String[] tableNames) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();//字节数组输入流
		ZipOutputStream zip = new ZipOutputStream(outputStream);//zip输入流
		for(String tableName : tableNames){//遍历传来的每一张表
			//查询表信息
			Map<String, String> table = generatorMapper.get(tableName);
			//查询列信息
			List<Map<String, String>> columns = generatorMapper.listColumns(tableName);
			//生成代码
			GenUtils.generatorCode(table, columns, zip);
		}
		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}

	@Override
	public Boolean tableToSqlByTableUrl(String tableUrl) throws Exception {
		String sqlName = tableUrl.replace("/files/", "");
		String sqlPath=this.uploadPath+sqlName;
		File sql =new File(sqlPath);
		StringBuilder stringBuilder = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(sql));
			String s=null;
			while((s=br.readLine())!=null) {//使用readline方法，一次读取一行
				stringBuilder.append(System.lineSeparator()+s);
			}
			br.close();
		} catch (Exception e) {}
		String[] sqls = stringBuilder.toString().split("孎", 0);
		String tableName = sqlName.substring(0, sqlName.indexOf("."));
		this.generatorMapper.truncate(tableName);
		for(String sqli:sqls) {
			try {
				this.generatorMapper.insert(sqli);
			} catch (Exception e) {
				throw new RuntimeException("部分语句还原失败");
			}
		}
		return true;
	}

}
