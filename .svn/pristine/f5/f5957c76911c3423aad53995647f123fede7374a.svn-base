package com.pb.core;

/**
 * 
 * File name：SysInfo
 * Date: 2013-3-13
 * Author: Admin
 * 系统配置信息类
 * Modify History:    Date            Programmer           Notes
 *                   ------          ------------         -------
 *                    2013-3-13      Admin        Initial
 */
public class SysInfo {
	private static String projectName;
	private static String projectVersion;
	private static String baseRoot;
	private static String systemStyle;
	private static String refreshTime;
	private static String pageSize;
	private static int pageNumber;
	private static int singleUser;// 是否启用单点登录 【0不启用 ，1启用】
	private static int pwDuration;// 密码最长持续天数
	private static int alertDate;// 密码失效提前报警天数
	private static String uploadTemplatePath;//交易确认书路径
	private static String khShortCutMenu;//客户快捷菜单组
	private static String cwShortCutMenu;//场务快捷菜单组
	private static String storeName;//私钥路径
	private static String privateKeyPassword;//私钥密码
	private static String certChainPath;//证书链
	private static String ukeyStatus;//UKey是否使用【0不启用，1启用】
	private static String fileUpldPath; //上传文件落地路径
	private static String svcStartTime; //交易确认开始时间
	private static String svcEndTime;   //交易确认截止时间
	private static String sgeDataExportFileName; //导出金交所数据文件名
	private static String fxExportFileName="fxExport.csv";
	private static String fxTotalExportFileName="fxTotalExport.csv";
	
	private static String ETLUSER_SCHEMA = "ETLUSER";//PTRD etluser schema
	
	private static String jasperReportsPath;//文件模板路径
	private static String xsdRulePath;//XSD规则文件路径
	private static String verCodeStatus;//是否使用验证码 0不使用 1使用
	private static int outSysActiveTime;//外部系统登录有效时间
	
	private static int exportExcelCount;	//导出Excel文件的最大行数
	private static String webAppRootKey;
	
	public static String getWebAppRootKey() {
		return webAppRootKey;
	}

	public static void setWebAppRootKey(String webAppRootKey) {
		SysInfo.webAppRootKey = webAppRootKey;
	}

	/*
	 * 临时增加
	 */
	private static int vlDtChngYearStart=2013;	//起息日变更年份其实年份
	
	/**
	 * 外汇期权业务日期变更查询起始年份
	 */
	private static int bizDtChngYearStart = 2014;
	
	/**
	 * 自贸区版本打开标记
	 */
	private static boolean ftzOpenFlag = false;
	

	public static int getVlDtChngYearStart() {
		return vlDtChngYearStart;
	}

	public static String getFxExportFileName() {
		return fxExportFileName;
	}

	public static void setFxExportFileName(String fxExportFileName) {
		SysInfo.fxExportFileName = fxExportFileName;
	}

	public static int getExportExcelCount() {
		return exportExcelCount;
	}

	public static String getXsdRulePath() {
		return xsdRulePath;
	}

	public void setXsdRulePath(String xsdRulePath) {
		SysInfo.xsdRulePath = xsdRulePath;
	}

	public static String getFileUpldPath() {
		return fileUpldPath;
	}

	public  void setFileUpldPath(String fileUpldPath) {
		SysInfo.fileUpldPath = fileUpldPath;
	}

	public static String getUploadTemplatePath() {
		return uploadTemplatePath;
	}
	
	public void setUploadTemplatePath(String uploadTemplatePath) {
		SysInfo.uploadTemplatePath = uploadTemplatePath;
	}

	public static String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public static String getBaseRoot() {
		return baseRoot;
	}

	public void setBaseRoot(String baseRoot) {
		this.baseRoot = baseRoot;
	}

	public static String getSystemStyle() {
		return systemStyle;
	}

	public void setSystemStyle(String systemStyle) {
		this.systemStyle = systemStyle;
	}

	public static String getRefreshTime() {
		return refreshTime;
	}

	public void setRefreshTime(String refreshTime) {
		this.refreshTime = refreshTime;
	}

	public static String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public static int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public static int getSingleUser() {
		return singleUser;
	}

	public void setSingleUser(int singleUser) {
		SysInfo.singleUser = singleUser;
	}

	public static int getPwDuration() {
		return pwDuration;
	}

	public void setPwDuration(int pwDuration) {
		SysInfo.pwDuration = pwDuration;
	}

	public static int getAlertDate() {
		return alertDate;
	}

	public void setAlertDate(int alertDate) {
		SysInfo.alertDate = alertDate;
	}

	public static String getKhShortCutMenu() {
		return khShortCutMenu;
	}

	public void setKhShortCutMenu(String khShortCutMenu) {
		SysInfo.khShortCutMenu = khShortCutMenu;
	}

	public static String getCwShortCutMenu() {
		return cwShortCutMenu;
	}

	public void setCwShortCutMenu(String cwShortCutMenu) {
		SysInfo.cwShortCutMenu = cwShortCutMenu;
	}

	public static String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		SysInfo.storeName = storeName;
	}

	public static String getPrivateKeyPassword() {
		return privateKeyPassword;
	}

	public void setPrivateKeyPassword(String privateKeyPassword) {
		SysInfo.privateKeyPassword = privateKeyPassword;
	}

	public static String getCertChainPath() {
		return certChainPath;
	}

	public void setCertChainPath(String certChainPath) {
		SysInfo.certChainPath = certChainPath;
	}

	public static String getUkeyStatus() {
		return ukeyStatus;
	}

	public void setUkeyStatus(String ukeyStatus) {
		SysInfo.ukeyStatus = ukeyStatus;
	}

	public static String getSvcStartTime() {
		return svcStartTime;
	}

	public void setSvcStartTime(String svcStartTime) {
		SysInfo.svcStartTime = svcStartTime;
	}

	public static String getSvcEndTime() {
		return svcEndTime;
	}

	public void setSvcEndTime(String svcEndTime) {
		SysInfo.svcEndTime = svcEndTime;
	}

	public static String getSgeDataExportFileName() {
		return sgeDataExportFileName;
	}

	public void setSgeDataExportFileName(String sgeDataExportFileName) {
		SysInfo.sgeDataExportFileName = sgeDataExportFileName;
	}

	public static String getJasperReportsPath() {
		return jasperReportsPath;
	}

	public void setJasperReportsPath(String jasperReportsPath) {
		SysInfo.jasperReportsPath = jasperReportsPath;
	}

	public static String getProjectVersion() {
		return projectVersion;
	}

	public void setProjectVersion(String projectVersion) {
		SysInfo.projectVersion = projectVersion;
	}

	public static String getVerCodeStatus() {
		return verCodeStatus;
	}

	public void setVerCodeStatus(String verCodeStatus) {
		SysInfo.verCodeStatus = verCodeStatus;
	}

	public static int getOutSysActiveTime() {
		return outSysActiveTime;
	}

	public void setOutSysActiveTime(int outSysActiveTime) {
		SysInfo.outSysActiveTime = outSysActiveTime;
	}

	public void setExportExcelCount(int exportExcelCount) {
		SysInfo.exportExcelCount = exportExcelCount;
	}

	public static String getFxTotalExportFileName() {
		return fxTotalExportFileName;
	}

	public void setFxTotalExportFileName(String fxTotalExportFileName) {
		SysInfo.fxTotalExportFileName = fxTotalExportFileName;
	}

	public static String getETLUSER_SCHEMA() {
		return ETLUSER_SCHEMA;
	}

	public void setETLUSER_SCHEMA(String eTLUSER_SCHEMA) {
		ETLUSER_SCHEMA = eTLUSER_SCHEMA;
	}

	public static int getBizDtChngYearStart() {
		return bizDtChngYearStart;
	}

	public static boolean isFtzOpenFlag() {
		return ftzOpenFlag;
	}

	public void setFtzOpenFlag(boolean ftzOpenFlag) {
		SysInfo.ftzOpenFlag = ftzOpenFlag;
	}

}
