package com.pb.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pb.entity.Institution;
import com.pb.entity.Model;
import com.pb.entity.Modelrow;
import com.pb.entity.Project;
import com.pb.entity.Subproject;
import com.pb.entity.Tabledata;
import com.pb.json.DataNode;
import com.pb.json.ModelDataNode;
import com.pb.json.NavBar;
import com.pb.services.applicationTable.TableService;

@Component
public class ModelDataUtil {

	@Resource
	private TableService tableService;
	private static ModelDataUtil modelTreeUtil;
	private static List<Modelrow> list;
	private static List<DataNode> datas;
	private static ModelDataNode root;
	private static int index;
	private static int amount;
	private static int type;
	private static int p_id;
	private static List<Subproject> subps;

	private ModelDataUtil() {
	}

	@PostConstruct
	public void init() {
		modelTreeUtil = this;
		modelTreeUtil.tableService = this.tableService;
	}

	public static ModelDataUtil getInstance() {
		if (modelTreeUtil == null) {
			modelTreeUtil = new ModelDataUtil();
		}

		return modelTreeUtil;
	}

	/**
	 * 获取模板和数据
	 * 
	 * @return
	 */

	public ModelDataNode getModelData(Integer project_id, Integer institution_id) {
		p_id = project_id;
		Project p = modelTreeUtil.tableService.getProject(project_id);
		List<Tabledata> tds = null;
		// 返回有子单位总表数据
		if (institution_id == null && p.getHaveBranch() == 1) {
			tds = modelTreeUtil.tableService.findTDbyIdAllHaveBranch(project_id);
			type=1;
		}
		// 返回无子单位总表数据
		else if (institution_id == null && p.getHaveBranch() == 0) {
			tds = modelTreeUtil.tableService.findTDbyIdAllNoBranch(project_id);
			type=2;
		}
		// 返无有有单位子单位表数据
		else if (institution_id != null) {
			tds = modelTreeUtil.tableService.findTDbyIdPartHaveBranch(project_id, institution_id);
			type=3;
		}
		index = 0;
		subps =  modelTreeUtil.tableService.getBranch(project_id);
		if(subps==null){
			amount=0;
		}else{
			amount = modelTreeUtil.tableService.getBranch(project_id).size();
		}
		
		List<Modelrow> list = tableService.findMRbyId(p.getModel().getId());
		System.out.println("------list.size()-------"+list.size());
		datas = new ArrayList<DataNode>();
		
		if(tds==null || tds.size()==0){
			
			int size = list.size();
			if(type==1){
				size+=list.size()*amount;
			}
			DataNode d;
			for(int n=0;n<size;n++){
				d = new DataNode();
				d.setIsBranch(0);
				datas.add(d);
			}
		}else{
			for (Tabledata td : tds) {
				datas.add(td2dn(td));
			}
		}

		

		
		ModelDataNode mdn = modelRow2modelTree(list);

		return mdn;

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

	public ModelDataNode modelRow2modelTree(List<Modelrow> modelRows) {
		root = new ModelDataNode();
		Modelrow node;
		if (modelRows != null && modelRows.size() > 0) {
			node = modelRows.get(0);
			root = mr2mt(node, true);

			for (int i = 1; i < modelRows.size(); i++) {
				node = modelRows.get(i);
				root.add(mr2mt(node, false));
			}
		}

		return root;
	}

	private ModelDataNode mr2mt(Modelrow mr, boolean isRoot) {
		ModelDataNode mdn = new ModelDataNode();

		if (isRoot) {
			mdn.setFid(0);
		} else {
			mdn.setFid(mr.getModelrow().getId());
		}
		mdn.setId(mr.getId());
		mdn.setName(mr.getName());
		mdn.setFeeType(mr.getFeetype().getId());
		mdn.setSequence(mr.getSequence());
		mdn.setProject_id(p_id);
		
		//添加主数据行
		DataNode main =datas.get(index);
		main.setSequence(mr.getSequence());
		mdn.setData(main);
		index++;
		
		//如果有子单位，添加子单位数据list
		if(amount!=0 && type==1){
			List<DataNode> subList = new ArrayList<DataNode>();
			for (int i = 0; i < amount; i++) {
				DataNode dn = datas.get(i + index);
				dn.setName(subps.get(i).getUser().getInstitution().getName());
				dn.setBranch_id(subps.get(i).getUser().getInstitution().getId());
				dn.setIsBranch(1);
				dn.setSequence(mr.getSequence());
				subList.add(dn);
			}
			index = index + amount;
			mdn.setSubDatas(subList);
		}
		

		return mdn;
	}

	/**
	 * 保存模板和数据
	 * 
	 * @param jsonModelData
	 * @param institution_id
	 */
	public void saveModelData(String jsonModelData, Integer institution_id) {
		datas = new ArrayList<DataNode>();
		ModelDataNode mdn = (ModelDataNode) JsonUtil.getInstance().json2obj(jsonModelData, ModelDataNode.class);

		Project p = modelTreeUtil.tableService.getProject(mdn.getProject_id());
		// 前端传来的模板结构
		List<Modelrow> newMrs = modelTree2modelRows(mdn);
		// 数据库里的模板结构
		List<Modelrow> oldMrs = modelTreeUtil.tableService.findMRbyId(p.getModel().getId());

		boolean flag = compareList(newMrs, oldMrs);

		if (!flag) {
			Model mm = modelTreeUtil.tableService.getModel(p.getModel().getId());
			if("项目隐藏模板".equals(mm.getType())){
				mm.setName(p.getName());// 项目的隐藏模板名称默认为项目名
				mm.setHaveA(1);
				mm.setHaveB(1);
				for (Modelrow mr : newMrs) {
					mr.setModel(mm);
				}
				List<Modelrow> old = modelTreeUtil.tableService.findMRbyIdDesc(p.getModel().getId());
				for (Modelrow mr : old) {
					modelTreeUtil.tableService.deleteMr(mr);
				}
				
				
				modelTreeUtil.tableService.updataMd(mm);
				for (Modelrow mr : newMrs) {
					modelTreeUtil.tableService.addModelrow(mr);
				}
				
			}else{
				Model m = new Model();
				m.setName(p.getName());// 项目的隐藏模板名称默认为项目名
				m.setType("项目隐藏模板");
				m.setHaveA(1);
				m.setHaveB(1);
				for (Modelrow mr : newMrs) {
					mr.setModel(m);
				}
				p.setModel(m); // 改变项目的模板
				modelTreeUtil.tableService.addModel(m);

				for (Modelrow mr : newMrs) {
					modelTreeUtil.tableService.addModelrow(mr);
				}

				modelTreeUtil.tableService.updataProjectModel(p);// 更新项目	
			}
			
			
		}

		// 保存更新有子单位总表数据
		if (institution_id == null && p.getHaveBranch() == 1) {
			List<Tabledata> newData = tableData2dataNode(datas, p);
			List<Tabledata> oldData = modelTreeUtil.tableService.findTDbyIdAllHaveBranch(p.getId());
			// 如果模板改变，则删除该项目所有的数据，然后重新添加
			if (!flag || oldData==null || oldData.size()==0) {
				// 删除旧数据
				for (Tabledata td : oldData) {
					modelTreeUtil.tableService.deleteTd(td);
				}
				// 保存新数据
				for (Tabledata td : newData) {
					modelTreeUtil.tableService.addData(td);
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
					modelTreeUtil.tableService.updataTd(oldTd);
				}
			}
		}

		// 保存更新无子单位总表数据
		if (institution_id == null && p.getHaveBranch() == 0) {
			List<Tabledata> newData = tableData2dataNode(datas, p);
			List<Tabledata> oldData = modelTreeUtil.tableService.findTDbyIdAllNoBranch(p.getId());
			// 如果模板改变，则删除该项目所有的数据，然后重新添加
			if (!flag || oldData==null || oldData.size()==0) {
				// 删除旧数据
				for (Tabledata td : oldData) {
					modelTreeUtil.tableService.deleteTd(td);
				}
				// 保存新数据
				for (Tabledata td : newData) {
					modelTreeUtil.tableService.addData(td);
				}
			}
			// 如果模板没有改变，则更新数据
			else {

				for (int i = 0; i < newData.size(); i++) {
					Tabledata newTd = newData.get(i);
					Tabledata oldTd = oldData.get(i);
					oldTd.setCalBasis(newTd.getCalBasis());
					// oldTd.setChildSetA(newTd.getChildSetA());
					// oldTd.setChildSetB(newTd.getChildSetB());
					// oldTd.setChildSetT(newTd.getChildSetT());
					oldTd.setDesignA(newTd.getDesignA());
					oldTd.setDesignB(newTd.getDesignB());
					oldTd.setDesignT(newTd.getDesignT());
					// oldTd.setIsLockA(newTd.getIsLockA());
					// oldTd.setIsLockB(newTd.getIsLockB());
					// oldTd.setIsLockT(newTd.getIsLockT());
					// oldTd.setMainSetA(newTd.getMainSetA());
					// oldTd.setMainSetB(newTd.getMainSetB());
					// oldTd.setMainSetT(newTd.getMainSetT());
					oldTd.setRealA(newTd.getRealA());
					oldTd.setRealB(newTd.getRealB());
					oldTd.setRealT(newTd.getRealT());
					modelTreeUtil.tableService.updataTd(oldTd);
				}
			}
		}

		// 保存更新有子单位子表数据
		if (institution_id != null) {
			List<Tabledata> newData = tableData2dataNode(datas, p);
			List<Tabledata> oldData = modelTreeUtil.tableService.findTDbyIdPartHaveBranch(p.getId(), institution_id);

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
				// oldTd.setIsLockA(newTd.getIsLockA());
				// oldTd.setIsLockB(newTd.getIsLockB());
				// oldTd.setIsLockT(newTd.getIsLockT());
				// oldTd.setMainSetA(newTd.getMainSetA());
				// oldTd.setMainSetB(newTd.getMainSetB());
				// oldTd.setMainSetT(newTd.getMainSetT());
				oldTd.setRealA(newTd.getRealA());
				oldTd.setRealB(newTd.getRealB());
				oldTd.setRealT(newTd.getRealT());
				modelTreeUtil.tableService.updataTd(oldTd);

			}
		}

	}

	public List<Tabledata> tableData2dataNode(List<DataNode> dns, Project p) {
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
			td.setInstitution(modelTreeUtil.tableService.getInstitution(dn.getBranch_id()));
		}

		td.setProject(p);
		td.setSequence(dn.getSequence());
		return td;
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

	public List<Modelrow> modelTree2modelRows(ModelDataNode mdn) {
		list = new ArrayList<Modelrow>();
		modelTree2modelRow(mdn, null);
		return list;
	}

	private void modelTree2modelRow(ModelDataNode mdn, Modelrow fathar) {

		if (mdn != null) {
			fathar = mr2mt(fathar, mdn);
			if (mdn.getChildren() != null && mdn.getChildren().size() > 0) {
				for (ModelDataNode m : mdn.getChildren()) {
					modelTree2modelRow(m, fathar);
				}
			}
		}

	}

	private Modelrow mr2mt(Modelrow fathar, ModelDataNode child) {
		datas.add(child.getData());
		for (DataNode dn : child.getSubDatas()) {
			datas.add(dn);
		}

		Modelrow mr = new Modelrow();
		mr.setSequence(child.getSequence());
		mr.setName(child.getName());
		mr.setModelrow(fathar);
		mr.setFeetype(modelTreeUtil.tableService.getFT(child.getFeeType()));
		list.add(mr);
		return mr;
	}
	
	public NavBar getNavBar(Integer project_id,Integer institution_id){
		NavBar root = new NavBar();
		Institution ins = modelTreeUtil.tableService.getInstitution(institution_id);
		Project p = modelTreeUtil.tableService.getProject(project_id);
		
		int createId = p.getUser().getInstitution().getId();
		int type=0;
		if(p.getHaveBranch()==1){
			
			if(createId==institution_id){
				type=1;
			}else{
				type=3;
			}
		}else{
			if(createId==institution_id){
				type=2;
			}
		}
		root.setName(p.getName());
		root.setIsMain(0);
		List<NavBar> nbs  = new ArrayList<NavBar>();;
		//如果是有子单位项目
		if(type==1){
			
			NavBar main = new NavBar(); //主单位
			main.setInstitution_id(institution_id);
			main.setIsMain(1);
			main.setName("主单位");
			List<NavBar> mainNbs = new ArrayList<NavBar>();
			NavBar nb1 = new NavBar(); //主单位
			nb1.setIsMain(0);
			nb1.setName("基础信息");
			NavBar nb2 = new NavBar(); 
			nb2.setIsMain(0);
			nb2.setName("子单位管理");
			NavBar nb3 = new NavBar();
			nb3.setIsMain(0);
			nb3.setName("模板选择");
			NavBar nb4 = new NavBar();
			nb4.setIsMain(0);
			nb4.setName("总表编辑");
			mainNbs.add(nb1);
			mainNbs.add(nb2);
			mainNbs.add(nb3);
			mainNbs.add(nb4);
			main.setChildren(mainNbs);
			nbs.add(main);
			
			//主单位本单位
			NavBar subMain = new NavBar(); //主单位
			subMain.setIsMain(0);
			subMain.setName("本单位单位:"+ins.getName());
			subMain.setInstitution_id(institution_id);
			List<NavBar> subMainNbs = new ArrayList<NavBar>();
			NavBar nb5 = new NavBar(); 
			nb5.setIsMain(0);
			nb5.setName("总表编辑");
			subMainNbs.add(nb5);
			subMain.setChildren(subMainNbs);
			nbs.add(subMain);
			
			//子单位
			List<Subproject> sps = modelTreeUtil.tableService.getBranch(project_id);
			for(int i=0;i<sps.size();i++){
				if(sps.get(i).getUser().getInstitution().getId()!=createId){
					NavBar sub = new NavBar(); //主单位
					sub.setIsMain(0);
					sub.setName("单位"+(i+2)+":"+sps.get(i).getUser().getInstitution().getName());
					sub.setInstitution_id(sps.get(i).getUser().getInstitution().getId());
					List<NavBar> subNbs = new ArrayList<NavBar>();
					NavBar nb6 = new NavBar(); 
					nb5.setIsMain(0);
					nb5.setName("总表编辑");
					subNbs.add(nb6);
					sub.setChildren(subNbs);
					nbs.add(sub);
				}
				
			}
			
			
			
		}else if(type==2){
			NavBar main = new NavBar(); //主单位
			main.setInstitution_id(institution_id);
			main.setIsMain(1);
			main.setName("主单位");
			List<NavBar> mainNbs = new ArrayList<NavBar>();
			NavBar nb1 = new NavBar(); //主单位
			nb1.setIsMain(0);
			nb1.setName("基础信息");
			NavBar nb2 = new NavBar(); 
			nb2.setIsMain(0);
			nb2.setName("子单位管理");
			NavBar nb3 = new NavBar();
			nb3.setIsMain(0);
			nb3.setName("模板选择");
			NavBar nb4 = new NavBar();
			nb4.setIsMain(0);
			nb4.setName("总表编辑");
			mainNbs.add(nb1);
			mainNbs.add(nb2);
			mainNbs.add(nb3);
			mainNbs.add(nb4);
			main.setChildren(mainNbs);
			nbs.add(main);
		}else if(type==3){
			NavBar sub = new NavBar(); //子单位
			sub.setIsMain(0);
			sub.setName("单位:"+ins.getName());
			sub.setInstitution_id(institution_id);
			List<NavBar> subNbs = new ArrayList<NavBar>();
			NavBar nb7 = new NavBar(); 
			nb7.setIsMain(0);
			nb7.setName("总表编辑");
			subNbs.add(nb7);
			sub.setChildren(subNbs);
			nbs.add(sub);
		}
		
		root.setChildren(nbs);
		
		return root;
		
	}

}
