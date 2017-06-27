package com.pb.json;

import java.util.ArrayList;
import java.util.List;

public class ModelDataNode {
	private Integer project_id;
	
	private Integer id;
	private Integer fid;
	private String sequence;
	private String name; // 科目名称
	private Integer feeType; // 费用类型
	private DataNode data; // 行数据

	private List<DataNode> subDatas; // 子单位行数据

	private List<ModelDataNode> children = new ArrayList<ModelDataNode>();

	  public void add(ModelDataNode node) {//递归添加节点
	        if (node.fid==0) {
	            this.children.add(node);
	        } else if (node.fid==this.id) {
	            this.children.add(node);
	        } else {
	            for (ModelDataNode tmp_node : children) {
	                tmp_node.add(node);
	            }
	        }
	    }

	  
	public Integer getProject_id() {
		return project_id;
	}

	public void setProject_id(Integer project_id) {
		this.project_id = project_id;
	}
	
	

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getFid() {
		return fid;
	}


	public void setFid(Integer fid) {
		this.fid = fid;
	}


	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getFeeType() {
		return feeType;
	}

	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}

	public DataNode getData() {
		return data;
	}

	public void setData(DataNode data) {
		this.data = data;
	}

	public List<DataNode> getSubDatas() {
		return subDatas;
	}

	public void setSubDatas(List<DataNode> subDatas) {
		this.subDatas = subDatas;
	}

	public List<ModelDataNode> getChildren() {
		return children;
	}

	public void setChildren(List<ModelDataNode> children) {
		this.children = children;
	}
	
	
	
}
