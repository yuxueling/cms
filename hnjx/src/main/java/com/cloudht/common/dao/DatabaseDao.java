package com.cloudht.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface DatabaseDao {
	/**
	 * 到库中查看当前使用库的所有表
	 * @return
	 */
	@Select("select table_name tableName, engine, table_comment tableComment, create_time createTime"
			+ " from information_schema.tables where table_schema = (select database())")
	List<Map<String, Object>> list();
	/**
	 * 查询当前使用库的表总数
	 * @param map
	 * @return
	 */
	@Select("select count(*) from information_schema.tables where table_schema = (select database())")
	int count(Map<String, Object> map);

	/**
	 * 查看指定表的表信息
	 * @param tableName
	 * @return
	 */
	@Select("select table_name tableName, engine, table_comment tableComment, create_time createTime from information_schema.tables \r\n"
			+ "	where table_schema = (select database()) and table_name = #{tableName}")
	Map<String, String> get(String tableName);

	/**
	 * 查看指定表的所有字段
	 * @param tableName
	 * @return
	 */
	@Select("select column_name columnName, data_type dataType, column_comment columnComment, column_key columnKey, extra from information_schema.columns\r\n"
			+ " where table_name = #{tableName} and table_schema = (select database()) order by ordinal_position")
	List<Map<String, String>> listColumns(String tableName);
	/**
	 * 将指定表的所有数据返回
	 * @param tableName
	 * @return
	 */
	@Select("SELECT * FROM ${tableName}")
	List<Map<String, Object>> listDatas(@Param("tableName") String tableName);
	
	/**
	 * 向任意表中插入数据
	 * @param insert 数据插入语句
	 * @return
	 */
	@Insert("${insert}")
	int insert( @Param("insert") String insert);
	
	/**
	 * 将表摧毁,新插入数据主键将从新开始
	 * @param tableName 摧毁表的名称
	 * @return
	 */
	@Delete("TRUNCATE TABLE ${tableName}")
	int truncate( @Param("tableName") String tableName);
}
