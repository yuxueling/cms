package com.cloudht.common.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * @author Hzof
 *
 * @param <T>
 */
public class Tree<T> {
	/**
	 * 节点ID
	 */
	private String id;
	/**
	 * 显示节点文本
	 */
	private String text;
	/**
	 * 节点状态，open closed
	 */
	private Map<String, Object> state;
	/**
	 * 节点是否被选中 true false
	 */
	private boolean checked = false;
	/**
	 * 节点属性
	 */
	private Map<String, Object> attributes;
	/**
	 * 节点的子节点
	 */
	private List<Tree<T>> children = new ArrayList<Tree<T>>();
	/**父ID*/
	private String parentId;
	/**是否有父节点*/
	private boolean hasParent = false;
	/**是否有子节点*/
	private boolean hasChildren = false;
	
	/**
	 * 空构造函数一枚
	 */
	public Tree() {
		super();
	}
	/**
	 * 构造方法一枚
	 * @param id 节点id
	 * @param text 显示节点文本
	 * @param state 节点状态，是否打开
	 * @param checked 节点是否被选中
	 * @param attributes 节点属性
	 * @param children 节点的子节点
	 * @param isParent 是否有父节点
	 * @param isChildren 是否有子节点
	 * @param parentID 父节点id
	 */
	public Tree(String id, String text, Map<String, Object> state, boolean checked, Map<String, Object> attributes,
			List<Tree<T>> children, boolean isParent, boolean isChildren, String parentID) {
		super();
		this.id = id;
		this.text = text;
		this.state = state;
		this.checked = checked;
		this.attributes = attributes;
		this.children = children;
		this.hasParent = isParent;
		this.hasChildren = isChildren;
		this.parentId = parentID;
	}
	/**
	 * 节点ID
	 * @return
	 */
	public String getId() {
		return id;
	}
	/**
	 * 节点ID
	 * @return
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 显示节点文本
	 */
	public String getText() {
		return text;
	}
	/**
	 * 显示节点文本
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * 节点状态，open closed
	 */
	public Map<String, Object> getState() {
		return state;
	}
	/**
	 * 节点状态，open closed
	 */
	public void setState(Map<String, Object> state) {
		this.state = state;
	}
	/**
	 * 节点是否被选中 true false
	 */
	public boolean isChecked() {
		return checked;
	}
	/**
	 * 节点是否被选中 true false
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	/**
	 * 节点属性
	 */
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	/**
	 * 节点属性
	 */
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	/**
	 * 节点的子节点
	 * @return
	 */
	public List<Tree<T>> getChildren() {
		return children;
	}
	/**
	 * 节点的子节点
	 * @return
	 */
	public void setChildren(List<Tree<T>> children) {
		this.children = children;
	}
	/**
	 * 是否有父节点
	 * @return
	 */
	public boolean isHasParent() {
		return hasParent;
	}
	/**
	 * 是否有父节点
	 * @return
	 */
	public void setHasParent(boolean isParent) {
		this.hasParent = isParent;
	}
	/**
	 * 是否有子节点
	 * @return
	 */
	public boolean isHasChildren() {
		return hasChildren;
	}
	/**
	 * 是否有子节点
	 * @param isChildren
	 */
	public void setChildren(boolean isChildren) {
		this.hasChildren = isChildren;
	}
	/**
	 * 父id
	 * @return
	 */
	public String getParentId() {
		return parentId;
	}
	/**
	 * 父id
	 * @param parentId
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	@Override public String toString() {
		return JSON.toJSONString(this);
	}

}