package com.pb.services.applicationTable;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pb.entity.Feetype;
import com.pb.entity.Institution;
import com.pb.entity.Model;
import com.pb.entity.Modelrow;
import com.pb.entity.Project;
import com.pb.entity.Subproject;
import com.pb.entity.Tabledata;
import com.pb.services.common.CommService;

@Service(value = "tableService")
public class TableService extends CommService {
	
	public List<Project> findAllPro() {
		List<Project> list = baseDAO.findByHQL("from Project");

		return list;
	}
	
	public List<Modelrow> findMRbyIdDesc(int id) {
		List<Modelrow> list = baseDAO.findByHQL("from Modelrow where model.id=? order by sequence desc",
				new Object[] { id });

		return list;
	}
	

	public List<Modelrow> findMRbyId(int id) {
		List<Modelrow> list = baseDAO.findByHQL("from Modelrow where model.id=? order by sequence",
				new Object[] { id });

		return list;
	}

	public List<Tabledata> findTDbyIdAllHaveBranch(int id) {
		List<Tabledata> list = baseDAO.findByHQL(
				"from Tabledata where project.id=? order by sequence,isBranch,institution.id",
				new Object[] { id });

		return list;
	}

	public List<Tabledata> findTDbyIdAllNoBranch(int id) {
		List<Tabledata> list = baseDAO.findByHQL("from Tabledata where project.id=? order by sequence",
				new Object[] { id });

		return list;
	}

	public List<Tabledata> findTDbyIdPartHaveBranch(int project_id, int institution_id) {
		List<Tabledata> list = baseDAO.findByHQL(
				"from Tabledata where project.id=? and institution.id=? order by sequence",
				new Object[] { project_id, institution_id });

		return list;
	}

	public void addModelrow(Modelrow mr) {
		baseDAO.save(mr);
	}
	

	public void updataProjectModel(Project p) {
		baseDAO.update(p);
	}
	

	public void updataTd(Tabledata td) {
		baseDAO.update(td);
	}
	
	public void deleteTd(Tabledata td) {
		baseDAO.delete(td);
	}
	
	public void deleteMr(Modelrow mr) {
		baseDAO.delete(mr);
		//baseDAO.executeHQL("delete from Modelrow where model.id=?", new Object[]{mid});
	}

	public void updataMd(Model md) {
		baseDAO.update(md);
	}

	public List<Subproject> getBranch(int project_id) {
		return baseDAO.findByHQL("from Subproject where project.id=?",new Object[]{project_id});
	}

	public void addModel(Model m) {
		baseDAO.save(m);
	}

	public void addData(Tabledata td) {
		baseDAO.save(td);
	}

	public Model getModel(int id) {
		List<Model> list = baseDAO.findByHQL("from Model where id=?", new Object[] { id });
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public Feetype getFT(int id) {
		List<Feetype> list = baseDAO.findByHQL("from Feetype where id=?", new Object[] { id });
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public Institution getInstitution(int id) {
		List<Institution> list = baseDAO.findByHQL("from Institution where id=?", new Object[] { id });
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public Project getProject(int id) {
		List<Project> list = baseDAO.findByHQL("from Project where id=?", new Object[] { id });
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public Modelrow getModelrow(int id) {
		List<Modelrow> list = baseDAO.findByHQL("from Modelrow where id=?", new Object[] { id });
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
