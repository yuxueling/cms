package com.cloudht.cont.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cloudht.common.domain.Tree;
import com.cloudht.common.utils.BuildTree;
import com.cloudht.cont.dao.ContCategoryDao;
import com.cloudht.cont.domain.ContCategoryDO;
import com.cloudht.cont.service.ContCategoryService;

@Service
public class ContCategoryServiceImpl implements ContCategoryService {
	@Autowired
	private ContCategoryDao contCategoryDao;
	
	@Override
	public ContCategoryDO get(Integer contCategoryId){
		return contCategoryDao.get(contCategoryId);
	}
	
	@Override
	public List<ContCategoryDO> list(Map<String, Object> map){
		return contCategoryDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return contCategoryDao.count(map);
	}
	
	@Override
	public int save(ContCategoryDO contCategory){
		return contCategoryDao.save(contCategory);
	}
	
	@Override
	public int update(ContCategoryDO contCategory){
		return contCategoryDao.update(contCategory);
	}
	
	@Override
	public int remove(Integer contCategoryId){
		return contCategoryDao.remove(contCategoryId);
	}
	
	@Override
	public int batchRemove(Integer[] contCategoryIds){
		return contCategoryDao.batchRemove(contCategoryIds);
	}
	
	public @Override Tree<ContCategoryDO> getTree() {
		List<Tree<ContCategoryDO>> trees = new ArrayList<Tree<ContCategoryDO>>();//创建一个tree的集合
		List<ContCategoryDO> contCategorys = this.contCategoryDao.list(null);//获取所有分类
		for (ContCategoryDO contCategoryDO : contCategorys) {//遍历每一个分类，将每个分类转换为tree
			Tree<ContCategoryDO> tree = new Tree<>();//创建一个tree的实例
			tree.setId(contCategoryDO.getContCategoryId().toString());//设置tree的id
			tree.setParentId(contCategoryDO.getParentCategoryId().toString());//设置tree的父id
			tree.setText(contCategoryDO.getCategoryName());//设置tree的名字
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<ContCategoryDO> t = BuildTree.build(trees);
		return t;
	}
	
}
