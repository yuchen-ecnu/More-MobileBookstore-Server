package com.pb.json;

import java.util.ArrayList;
import java.util.List;
public class ModelTree {
    private Integer id;
    private Integer fid;
    private String sequence;
    private String name; //科目名称
    private Integer feeType; //费用类型
   
    private List<ModelTree> children = new ArrayList<ModelTree>();
    public ModelTree(){
    	
    }
    
    public ModelTree(Integer id, Integer fid,String sequence, String name, Integer feeType) {
        this.id = id;
        this.fid = fid;
        this.sequence = sequence;
        this.name = name;
        this.feeType = feeType;
    }
    public void add(ModelTree node) {//递归添加节点
        if (node.fid==0) {
            this.children.add(node);
        } else if (node.fid==this.id) {
            this.children.add(node);
        } else {
            for (ModelTree tmp_node : children) {
                tmp_node.add(node);
            }
        }
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
	public List<ModelTree> getChildren() {
		return children;
	}
	public void setChildren(List<ModelTree> children) {
        this.children = children;
    }
}