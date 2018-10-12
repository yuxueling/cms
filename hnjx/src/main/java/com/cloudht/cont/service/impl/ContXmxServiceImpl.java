package com.cloudht.cont.service.impl;

import com.cloudht.cont.dao.ContXmxDao;
import com.cloudht.cont.service.ContXmxService;
import com.cloudht.cont.vo.ContProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ContXmxServiceImpl implements ContXmxService {
	@Autowired
	private ContXmxDao contXmxDao;

	@Override
	public List<ContProductVO> listProductByCategory(Map<String,Object> map) {
		return contXmxDao.listProductByCategory(map);
	}

	@Override
	public int countProductByCategory(Map<String, Object> map) {
		return contXmxDao.countProductByCategory(map);
	}

	@Override
	public ContProductVO getProduct(Map<String, Object> map) {
		return contXmxDao.getProduct(map);
	}
}
