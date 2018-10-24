package com.sxyhton.cont.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sxyhton.common.domain.Tree;
import com.sxyhton.common.utils.BuildTree;
import com.sxyhton.cont.dao.ContCategoryDao;
import com.sxyhton.cont.dao.ContCategoryInfoDao;
import com.sxyhton.cont.domain.ContCategoryDO;
import com.sxyhton.cont.domain.ContCategoryInfoDO;
import com.sxyhton.cont.service.ContCategoryService;

@Service
public class ContCategoryServiceImpl implements ContCategoryService {
	@Autowired
	private ContCategoryDao contCategoryDao;
	@Autowired
	private ContCategoryInfoDao contCategoryInfoDao;


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

	public @Override Tree<ContCategoryDO> getTreeInfo(Map<String, Object> map) {
		List<Tree<ContCategoryDO>> trees = new ArrayList<Tree<ContCategoryDO>>();//创建一个tree的集合
		List<ContCategoryDO> contCategorys = this.contCategoryDao.listInfo(map);//获取所有分类
		for (ContCategoryDO contCategoryDO : contCategorys) {//遍历每一个分类，将每个分类转换为tree
			Tree<ContCategoryDO> tree = new Tree<>();//创建一个tree的实例
			tree.setId(contCategoryDO.getContCategoryId().toString());//设置tree的id
			tree.setParentId(contCategoryDO.getParentCategoryId().toString());//设置tree的父id
			if(contCategoryDO.getContCategoryInfoDO()!=null && contCategoryDO.getContCategoryInfoDO().getCategoryName()!=null){
				tree.setText(contCategoryDO.getContCategoryInfoDO().getCategoryName());//设置tree的名字
			}else {
				tree.setText(contCategoryDO.getCategoryName());
			}
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<ContCategoryDO> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public List<ContCategoryInfoDO> listInfoByDict(Map<String, Object> map) {
		return contCategoryDao.listInfoByDict(map);
	}

	@Override
	public int saveInfo(ContCategoryInfoDO contCategoryInfo) {
		return contCategoryInfoDao.save(contCategoryInfo);
	}

	@Override
	public int updateInfo(ContCategoryInfoDO contCategoryInfo) {
		return contCategoryInfoDao.update(contCategoryInfo);
	}

}
