package com.pb.json;

import java.io.Serializable;

/**
 * 
 * File name：CAInfo
 * Date: 2012-5-18
 * Author: Administrator
 * Description：前台CA验证用JSON结构
 * Modify History:
 */
public class CAInfo implements Serializable,Cloneable {
	
	private String certLenth;
	private String intEncRandomKeyLngth;
	private String arrChrCertificate;
	private String chrRandomKey;
	private String chrmChrPublicKey;
	private String chrmChrCertSrNo;
	private String chrmChrUUID;
	private String arrEncrypedRandonKey;
	private String signature;
	private String signatureLength;
	
	private String serverTime;
	
	public String getCertLenth() {
		return certLenth;
	}

	public void setCertLenth(String certLenth) {
		this.certLenth = certLenth;
	}

	public String getIntEncRandomKeyLngth() {
		return intEncRandomKeyLngth;
	}

	public void setIntEncRandomKeyLngth(String intEncRandomKeyLngth) {
		this.intEncRandomKeyLngth = intEncRandomKeyLngth;
	}

	public String getArrChrCertificate() {
		return arrChrCertificate;
	}

	public void setArrChrCertificate(String arrChrCertificate) {
		this.arrChrCertificate = arrChrCertificate;
	}

	public String getChrRandomKey() {
		return chrRandomKey;
	}

	public void setChrRandomKey(String chrRandomKey) {
		this.chrRandomKey = chrRandomKey;
	}

	public String getChrmChrPublicKey() {
		return chrmChrPublicKey;
	}

	public void setChrmChrPublicKey(String chrmChrPublicKey) {
		this.chrmChrPublicKey = chrmChrPublicKey;
	}

	public String getChrmChrCertSrNo() {
		return chrmChrCertSrNo;
	}

	public void setChrmChrCertSrNo(String chrmChrCertSrNo) {
		this.chrmChrCertSrNo = chrmChrCertSrNo;
	}

	public String getChrmChrUUID() {
		return chrmChrUUID;
	}

	public void setChrmChrUUID(String chrmChrUUID) {
		this.chrmChrUUID = chrmChrUUID;
	}

	public String getArrEncrypedRandonKey() {
		return arrEncrypedRandonKey;
	}

	public void setArrEncrypedRandonKey(String arrEncrypedRandonKey) {
		this.arrEncrypedRandonKey = arrEncrypedRandonKey;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getSignatureLength() {
		return signatureLength;
	}

	public void setSignatureLength(String signatureLength) {
		this.signatureLength = signatureLength;
	}

	public String getServerTime() {
		return serverTime;
	}

	public void setServerTime(String serverTime) {
		this.serverTime = serverTime;
	}

}
