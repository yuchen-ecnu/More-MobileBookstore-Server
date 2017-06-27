package com.pb.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pb.entity.Model;
import com.pb.entity.Modelrow;
import com.pb.entity.Project;
import com.pb.entity.Tabledata;
import com.pb.json.DataNode;
import com.pb.json.TableDataNode;
import com.pb.services.applicationTable.TableService;

@Component
public class TableDataUtil {

	@Resource
	private TableService tableService;
	private static TableDataUtil tdu;

	private TableDataUtil() {
	}

	@PostConstruct
	public void init() {
		tdu = this;
		tdu.tableService = this.tableService;
	}

	public static TableDataUtil getInstance() {
		if (tdu == null) {
			tdu = new TableDataUtil();
		}

		return tdu;
	}

	public TableDataNode dataNode2tableData(Integer project_id, Integer institution_id) {

		Project p = tdu.tableService.getProject(project_id);
		List<Tabledata> tds = null;
		// 返回有子单位总表数据
		if (institution_id == null && p.getHaveBranch() == 1) {
			tds = tdu.tableService.findTDbyIdAllHaveBranch(project_id);
		}
		// 返回无子单位总表数据
		else if (institution_id == null && p.getHaveBranch() == 0) {
			tds = tdu.tableService.findTDbyIdAllNoBranch(project_id);
		}
		// 返无有有单位子单位表数据
		else if (institution_id != null) {
			tds = tdu.tableService.findTDbyIdPartHaveBranch(project_id, institution_id);
		}

		TableDataNode tdn = new TableDataNode();
		if (tds != null && tds.size() > 0) {
			tdn.setProject_id(tds.get(0).getProject().getId());
		}

		List<DataNode> dns = new ArrayList<DataNode>();
		for (Tabledata td : tds) {
			dns.add(td2dn(td));
		}

		tdn.setDatas(dns);
		return tdn;
	}

	public DataNode td2dn(Tabledata td) {
		DataNode dn = new DataNode();
		dn.setCalBasis(td.getCalBasis());
		dn.setChildSetA(td.getChildSetA());
		dn.setChildSetB(td.getChildSetB());
		dn.setChildSetT(td.getChildSetT());
		dn.setDesignA(td.getDesignA());
		dn.setDesignB(td.getDesignB());
		dn.setDesignT(td.getDesignT());
		dn.setId(td.getId());
		dn.setIsBranch(td.getIsBranch());
		dn.setIsLockA(td.getIsLockA());
		dn.setIsLockB(td.getIsLockB());
		dn.setIsLockT(td.getIsLockT());
		dn.setMainSetA(td.getMainSetA());
		dn.setMainSetB(td.getMainSetB());
		dn.setMainSetT(td.getMainSetT());
		dn.setRealA(td.getRealA());
		dn.setRealB(td.getRealB());
		dn.setRealT(td.getRealT());
		if (td.getIsBranch() == 1) {
			dn.setBranch_id(td.getInstitution().getId());
		}

		dn.setSequence(td.getSequence());
		return dn;
	}

	public void jsonData2entityDataSave(String jsonData, String jsonModel, Integer institution_id) {
		TableDataNode tdn = (TableDataNode) JsonUtil.getInstance().json2obj(jsonData, TableDataNode.class);
		Project p = tdu.tableService.getProject(tdn.getProject_id());
		List<Modelrow> newMrs = ModelTreeUtil.getInstance().jsonModel2entityModel(jsonModel);
		List<Modelrow> oldMrs = tdu.tableService.findMRbyId(p.getModel().getId());
		boolean flag = compareList(newMrs, oldMrs);

		if (!flag) {
			Model m = new Model();
			m.setName(p.getName());// 项目的隐藏模板名称默认为项目名
			m.setType("项目隐藏模板");
			m.setHaveA(1);
			m.setHaveB(1);
			for (Modelrow mr : newMrs) {
				mr.setModel(m);
			}
			p.setModel(m); // 改变项目的模板
			tdu.tableService.addModel(m);

			for (Modelrow mr : newMrs) {
				tdu.tableService.addModelrow(mr);
			}

			tdu.tableService.updataProjectModel(p);// 更新项目

		}

		// 保存更新有子单位总表数据
		if (institution_id == null && p.getHaveBranch() == 1) {
			List<Tabledata> newData = TableDataUtil.getInstance().tableData2dataNode(tdn, p);
			List<Tabledata> oldData = tdu.tableService.findTDbyIdAllHaveBranch(p.getId());
			// 如果模板改变，则删除该项目所有的数据，然后重新添加
			if (!flag) {
				// 删除旧数据
				for (Tabledata td : oldData) {
					tdu.tableService.deleteTd(td);
				}
				// 保存新数据
				for (Tabledata td : newData) {
					tdu.tableService.addData(td);
				}
			}
			// 如果模板没有改变，则更新数据
			else {

				for (int i = 0; i < newData.size(); i++) {
					Tabledata newTd = newData.get(i);
					Tabledata oldTd = oldData.get(i);
					oldTd.setCalBasis(newTd.getCalBasis());
					oldTd.setChildSetA(newTd.getChildSetA());
					oldTd.setChildSetB(newTd.getChildSetB());
					oldTd.setChildSetT(newTd.getChildSetT());
					// oldTd.setDesignA(newTd.getDesignA());
					// oldTd.setDesignB(newTd.getDesignB());
					// oldTd.setDesignT(newTd.getDesignT());
					oldTd.setIsLockA(newTd.getIsLockA());
					oldTd.setIsLockB(newTd.getIsLockB());
					oldTd.setIsLockT(newTd.getIsLockT());
					oldTd.setMainSetA(newTd.getMainSetA());
					oldTd.setMainSetB(newTd.getMainSetB());
					oldTd.setMainSetT(newTd.getMainSetT());
					// oldTd.setRealA(newTd.getRealA());
					// oldTd.setRealB(newTd.getRealB());
					// oldTd.setRealT(newTd.getRealT());
					tdu.tableService.updataTd(oldTd);
				}
			}
		}

		// 保存更新无子单位总表数据
		if (institution_id == null && p.getHaveBranch() == 0) {
			List<Tabledata> newData = TableDataUtil.getInstance().tableData2dataNode(tdn, p);
			List<Tabledata> oldData = tdu.tableService.findTDbyIdAllNoBranch(p.getId());
			// 如果模板改变，则删除该项目所有的数据，然后重新添加
			if (!flag) {
				// 删除旧数据
				for (Tabledata td : oldData) {
					tdu.tableService.deleteTd(td);
				}
				// 保存新数据
				for (Tabledata td : newData) {
					tdu.tableService.addData(td);
				}
			}
			// 如果模板没有改变，则更新数据
			else {

				for (int i = 0; i < newData.size(); i++) {
					Tabledata newTd = newData.get(i);
					Tabledata oldTd = oldData.get(i);
					oldTd.setCalBasis(newTd.getCalBasis());
					//oldTd.setChildSetA(newTd.getChildSetA());
					//oldTd.setChildSetB(newTd.getChildSetB());
					//oldTd.setChildSetT(newTd.getChildSetT());
					oldTd.setDesignA(newTd.getDesignA());
					oldTd.setDesignB(newTd.getDesignB());
					oldTd.setDesignT(newTd.getDesignT());
					//oldTd.setIsLockA(newTd.getIsLockA());
					//oldTd.setIsLockB(newTd.getIsLockB());
					//oldTd.setIsLockT(newTd.getIsLockT());
					//oldTd.setMainSetA(newTd.getMainSetA());
					//oldTd.setMainSetB(newTd.getMainSetB());
					//oldTd.setMainSetT(newTd.getMainSetT());
					oldTd.setRealA(newTd.getRealA());
					oldTd.setRealB(newTd.getRealB());
					oldTd.setRealT(newTd.getRealT());
					tdu.tableService.updataTd(oldTd);
				}
			}
		}

		// 保存更新有子单位子表数据
		if (institution_id != null) {
			List<Tabledata> newData = TableDataUtil.getInstance().tableData2dataNode(tdn, p);
			List<Tabledata> oldData = tdu.tableService.findTDbyIdPartHaveBranch(p.getId(),institution_id);
			
				for (int i = 0; i < newData.size(); i++) {
					Tabledata newTd = newData.get(i);
					Tabledata oldTd = oldData.get(i);
					oldTd.setCalBasis(newTd.getCalBasis());
					oldTd.setChildSetA(newTd.getDesignA());
					oldTd.setChildSetB(newTd.getDesignA());
					oldTd.setChildSetT(newTd.getDesignA());
					oldTd.setDesignA(newTd.getDesignA());
					oldTd.setDesignB(newTd.getDesignB());
					oldTd.setDesignT(newTd.getDesignT());
					//oldTd.setIsLockA(newTd.getIsLockA());
					//oldTd.setIsLockB(newTd.getIsLockB());
					//oldTd.setIsLockT(newTd.getIsLockT());
					//oldTd.setMainSetA(newTd.getMainSetA());
					//oldTd.setMainSetB(newTd.getMainSetB());
					//oldTd.setMainSetT(newTd.getMainSetT());
					oldTd.setRealA(newTd.getRealA());
					oldTd.setRealB(newTd.getRealB());
					oldTd.setRealT(newTd.getRealT());
					tdu.tableService.updataTd(oldTd);
				
			}
		}

	}

	public boolean compareList(List<Modelrow> newMrs, List<Modelrow> oldMrs) {
		boolean flag = true;
		if (newMrs.size() != oldMrs.size()) {
			flag = false;
		} else {
			Modelrow newMr;
			Modelrow oldMr;
			for (int i = 0; i < newMrs.size(); i++) {
				newMr = newMrs.get(i);
				oldMr = oldMrs.get(i);
				if (!newMr.getSequence().equals(oldMr.getSequence())) {
					flag = false;
				} else if (!newMr.getName().equals(oldMr.getName())) {
					flag = false;
				} else if (!newMr.getFeetype().equals(oldMr.getFeetype())) {
					flag = false;
				}
			}
		}

		return flag;
	}

	public List<Tabledata> tableData2dataNode(TableDataNode tdn, Project p) {
		List<DataNode> dns = tdn.getDatas();
		List<Tabledata> tds = new ArrayList<Tabledata>();
		for (DataNode dn : dns) {
			tds.add(dn2td(dn, p));
		}

		return tds;
	}

	public Tabledata dn2td(DataNode dn, Project p) {

		Tabledata td = new Tabledata();
		td.setCalBasis(dn.getCalBasis());
		td.setChildSetA(dn.getChildSetA());
		td.setChildSetB(dn.getChildSetB());
		td.setChildSetT(dn.getChildSetT());
		td.setDesignA(dn.getDesignA());
		td.setDesignB(dn.getDesignB());
		td.setDesignT(dn.getDesignT());
		td.setId(dn.getId());
		// Project p = tdu.tableService.getProject(project_id);
		if (p.getHaveBranch() == 0) {
			td.setIsBranch(0);
		} else {
			td.setIsBranch(dn.getIsBranch());
		}
		td.setIsLockA(dn.getIsLockA());
		td.setIsLockB(dn.getIsLockB());
		td.setIsLockT(dn.getIsLockT());
		td.setMainSetA(dn.getMainSetA());
		td.setMainSetB(dn.getMainSetB());
		td.setMainSetT(dn.getMainSetT());
		td.setRealA(dn.getRealA());
		td.setRealB(dn.getRealB());
		td.setRealT(dn.getRealT());

		if (dn.getBranch_id() != null) {
			td.setInstitution(tdu.tableService.getInstitution(dn.getBranch_id()));
		}

		td.setProject(p);
		td.setSequence(dn.getSequence());
		return td;
	}
}
