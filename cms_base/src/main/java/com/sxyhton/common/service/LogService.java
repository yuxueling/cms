package com.sxyhton.common.service;

import com.sxyht.common.utils.Query;
import com.sxyhton.common.domain.LogDO;
import com.sxyhton.common.domain.PageDO;

import org.springframework.stereotype.Service;
@Service
public interface LogService {
	void save(LogDO logDO);
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
