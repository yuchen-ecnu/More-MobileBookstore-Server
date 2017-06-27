package com.pb.services.template;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pb.entity.Model;
import com.pb.entity.Modelrow;
import com.pb.entity.Tabledata;
import com.pb.services.common.CommService;

@Service(value = "templateService")
public class TemplateService extends CommService {
	
	
	public List<Modelrow> findModelrow() {
		List<Modelrow> list = baseDAO.findByHQL(" from Modelrow mr");
		return list;
	}
	
	public List<Model> findModel(){
		List<Model> list = baseDAO.findByHQL(" from Model m");
		return list;
	}
	
	public List<Model> findModelByName(String name){
		List<Model> list = baseDAO.findByHQL("from Model m where m.name = ? ",new Object[] {name } );
		return list;
	}
	
	public List<Model> findModelById(int id){
		List<Model> list = baseDAO.findByHQL("from Model m where m.id = ? ",new Object[] {id } );
		return list;
	}
	
	public List<Modelrow> selectByModelId(int Model_id){
		List<Model> tmp  = this.findModelById(Model_id);
		Model model = tmp.get(0);
		List<Modelrow> list = baseDAO.findByHQL("from Modelrow mr where mr.model = ?", new Object[]{ model });
		return list;
	}
	
	public void addModel(String name,String type,int haveA,int haveB){
		Model new_one = new Model(name, type);
		new_one.setHaveA(haveA);
		new_one.setHaveB(haveB);
		baseDAO.save(new_one);
	}
	
	public void updateModel(int id,Model new_one){
		new_one.setName("UpdateFun");
		baseDAO.update(new_one);
	}
}
