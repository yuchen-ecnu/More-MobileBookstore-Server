package com.pb.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pb.entity.Model;
import com.pb.entity.Modelrow;
import com.pb.entity.Project;
import com.pb.json.ModelNode;
import com.pb.json.ModelTree;
import com.pb.services.applicationTable.TableService;

@Component
public class ModelTreeUtil {
	
	@Resource
	private TableService tableService;
	private static ModelTreeUtil modelTreeUtil;
	private static List<Modelrow> list;
	private static ModelTree root;
	
	private ModelTreeUtil(){}
	
	
	@PostConstruct
	public void init(){
		modelTreeUtil = this;
		modelTreeUtil.tableService = this.tableService;
	}
	public static ModelTreeUtil getInstance(){
		if(modelTreeUtil == null){
			modelTreeUtil = new ModelTreeUtil();
		}
		
		return modelTreeUtil;
	}
	
	public void jsonModel2entityModelSave(String jsonModel){
		ModelNode mn = (ModelNode)JsonUtil.getInstance().json2obj(jsonModel, ModelNode.class);
		
		Model m = new Model();
		m.setName(mn.getName());
		m.setType(mn.getType());
		m.setHaveA(mn.getHaveA());
		m.setHaveB(mn.getHaveB());
		List<Modelrow> mrs = modelTree2modelRows(mn.getSubjects());
		for(Modelrow mr:mrs){
			mr.setModel(m);
		}
		modelTreeUtil.tableService.addModel(m);
		
		for(Modelrow mr:list){
			modelTreeUtil.tableService.addModelrow(mr);
		}
		
	}
	
	public void jsonModel2entityModelSaveNew(String jsonModel,String project_id){
		ModelNode mn = (ModelNode)JsonUtil.getInstance().json2obj(jsonModel, ModelNode.class);
		
		Model m = new Model();
		m.setName(mn.getName());
		m.setType(mn.getType());
		m.setHaveA(mn.getHaveA());
		m.setHaveB(mn.getHaveB());
		List<Modelrow> mrs = modelTree2modelRows(mn.getSubjects());
		for(Modelrow mr:mrs){
			mr.setModel(m);
		}
		modelTreeUtil.tableService.addModel(m);
		
		for(Modelrow mr:list){
			modelTreeUtil.tableService.addModelrow(mr);
		}
		
		Project p = modelTreeUtil.tableService.getProject(Integer.parseInt(project_id));
		p.setModel(m);
		modelTreeUtil.tableService.updataProjectModel(p);
	}
	
	public List<Modelrow> jsonModel2entityModel(String jsonModel){
		ModelNode mn = (ModelNode)JsonUtil.getInstance().json2obj(jsonModel, ModelNode.class);
		
		List<Modelrow> mrs = modelTree2modelRows(mn.getSubjects());
		
		return mrs;
		
	}
	
	public  List<Modelrow> modelTree2modelRows(ModelTree mt){
		list = new ArrayList<Modelrow>();
		modelTree2modelRow(mt,null);
		return list;
	}
	
	private  void modelTree2modelRow(ModelTree mt,Modelrow fathar){
		
		if(mt!=null){
			fathar = mr2mt(fathar,mt);
			if(mt.getChildren()!=null && mt.getChildren().size()>0){
				for(ModelTree m:mt.getChildren()){
					modelTree2modelRow(m,fathar);
				}
			}
		}
		
	}
	
	private  Modelrow mr2mt(Modelrow fathar,ModelTree child){
		Modelrow mr = new Modelrow();
		mr.setSequence(child.getSequence());
		mr.setName(child.getName());
		mr.setModelrow(fathar);
		mr.setFeetype(modelTreeUtil.tableService.getFT(child.getFeeType()));
		list.add(mr);
		return mr;
	}
	
	public ModelNode entityModel2jsonModelget(Integer model_id){
		ModelNode mn = new ModelNode();
		Model m = modelTreeUtil.tableService.getModel(model_id);
		mn.setId(m.getId());
		mn.setName(m.getName());
		mn.setType(m.getType());
		mn.setHaveA(m.getHaveA());
		mn.setHaveB(m.getHaveB());
		List<Modelrow> list = tableService.findMRbyId(model_id);
		ModelTree mt = modelRow2modelTree(list);
		mn.setSubjects(mt);
		
		return mn;
		
	}
	
	public  ModelTree modelRow2modelTree(List<Modelrow> modelRows){
		root = new ModelTree();
		Modelrow node;
		if(modelRows != null && modelRows.size()>0){
			node = modelRows.get(0);
			root = mr2mt(node,true);
			
			
			for(int i=1;i<modelRows.size();i++){
				node = modelRows.get(i);
				root.add(mr2mt(node,false));
			}
		}
		
		
		return root;
	}
	
	
	private  ModelTree mr2mt(Modelrow mr,boolean isRoot){
		ModelTree mt = new ModelTree();
		
		if(isRoot){
			mt.setFid(0);
		}else{
			mt.setFid(mr.getModelrow().getId());
		}
		mt.setId(mr.getId());
		mt.setName(mr.getName());
		mt.setFeeType(mr.getFeetype().getId());
		mt.setSequence(mr.getSequence());
		return mt;
	}
	
}
