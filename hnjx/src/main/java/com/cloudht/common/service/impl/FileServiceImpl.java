package com.cloudht.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.cloudht.common.dao.SysFileDao;
import com.cloudht.common.domain.SysFileDO;
import com.cloudht.common.service.FileService;
import org.springframework.util.StringUtils;


@Service
public class FileServiceImpl implements FileService {
	@Autowired
	private SysFileDao sysFileMapper;

	@Value("${uploadPath}") String uploadPath;
	@Override
	public SysFileDO get(Long id){
		return sysFileMapper.get(id);
	}
	
	@Override
	public List<SysFileDO> list(Map<String, Object> map){
		return sysFileMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return sysFileMapper.count(map);
	}
	
	@Override
	public int save(SysFileDO sysFile){
		return sysFileMapper.save(sysFile);
	}
	
	@Override
	public int update(SysFileDO sysFile){
		return sysFileMapper.update(sysFile);
	}
	
	@Override
	public int remove(Long id){
		return sysFileMapper.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return sysFileMapper.batchRemove(ids);
	}

    @Override
    public Boolean isExist(String url) {
		Boolean isExist = false;
		if (!StringUtils.isEmpty(url)) {
			String filePath = url.replace("/files/", "");
			filePath = this.uploadPath + filePath;
			File file = new File(filePath);
			if (file.exists()) {
				isExist = true;
			}
		}
		return isExist;
	}
	}
