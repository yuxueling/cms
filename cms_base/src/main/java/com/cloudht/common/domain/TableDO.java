package com.cloudht.common.domain;

import java.util.List;

/**
 * 表数据
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月20日 上午12:02:55
 */
public class TableDO {
    //表的名称
    private String tableName;
    //表的备注
    private String comments;
    //表的主键
    private ColumnDO pk;
    //表的列名(不包含主键)
    private List<ColumnDO> columns;
    //类名(第一个字母大写)，如：sys_user => SysUser
    private String className;
    //类名(第一个字母小写)，如：sys_user => sysUser
    private String classname;
    
    /**
     * @return 表名
     */
    public String getTableName() {
        return tableName;
    }
    /**
     * @param tableName 表名
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    /**
     * @return 表的备注
     */
    public String getComments() {
        return comments;
    }
    /**
     * 设置表的备注
     * @param comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }
    /**
     * @return 主键
     */
    public ColumnDO getPk() {
        return pk;
    }
    /**
     * 设置主键
     * @param pk 主键
     */
    public void setPk(ColumnDO pk) {
        this.pk = pk;
    }
    /**
     * 获取列的集合
     * @return
     */
    public List<ColumnDO> getColumns() {
        return columns;
    }
    /**
     * 设置表的列
     * @param columns
     */
    public void setColumns(List<ColumnDO> columns) {
        this.columns = columns;
    }
    /**
     * 类名(第一个字母大写)
     * @return
     */
    public String getClassName() {
        return className;
    }
    /**
     * 类名(第一个字母大写)
     * @param className
     */
    public void setClassName(String className) {
        this.className = className;
    }
    /**
     * 类名(第一个字母小写)，如：sys_user => sysUser
     * @return
     */
    public String getClassname() {
        return classname;
    }
    /**
     * 类名(第一个字母小写)，如：sys_user => sysUser
     * @param classname
     */
    public void setClassname(String classname) {
        this.classname = classname;
    }

    @Override
    public String toString() {
        return "TableDO{" +
                "tableName='" + tableName + '\'' +
                ", comments='" + comments + '\'' +
                ", pk=" + pk +
                ", columns=" + columns +
                ", className='" + className + '\'' +
                ", classname='" + classname + '\'' +
                '}';
    }
}
