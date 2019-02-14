package com.admin.VO;

import java.util.List;

public class ProductVO {

	private int pSeq; // ��ǰ��ȣ
	private String pName; // ��ǰ�̸�
	private int pPrice; // ����
	private String pDetail; // ��ǰ��
	private int pLev;
	private int pRef;
	private int pStep;
	
	private int deliveryCharge;
	
	private String productstate;
	public ProductVO() {}
	
	public ProductVO(int pSeq,String pName, int pPrice, String pDetail, int deliveryCharge) {
		super();
		this.pSeq = pSeq;
		this.pName = pName;
		this.pPrice = pPrice;
		this.pDetail = pDetail;
		this.deliveryCharge = deliveryCharge;
	}
	
	public ProductVO(String pName, int pPrice, String pDetail) {
		super();
		this.pName = pName;
		this.pPrice = pPrice;
		this.pDetail = pDetail;
	}

	public ProductVO(int pSeq, String pName, int pPrice, int pStock, String pDetail, int pLev, int pRef, int pStep) {
		super();
		this.pSeq = pSeq;
		this.pName = pName;
		this.pPrice = pPrice;
		this.pDetail = pDetail;
		this.pLev = pLev;
		this.pRef = pRef;
		this.pStep = pStep;
	}
	
	
	public String getProductstate() {
		return productstate;
	}

	public void setProductstate(String productstate) {
		this.productstate = productstate;
	}

	public int getDeliveryCharge() {
		return deliveryCharge;
	}

	public void setDeliveryCharge(int deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}

	public int getpLev() {
		return pLev;
	}
	public void setpLev(int pLev) {
		this.pLev = pLev;
	}
	public int getpRef() {
		return pRef;
	}
	public void setpRef(int pRef) {
		this.pRef = pRef;
	}
	public int getpStep() {
		return pStep;
	}
	public void setpStep(int pStep) {
		this.pStep = pStep;
	}
	public void setpSeq(int pSeq) {
		this.pSeq = pSeq;
	}
	public int getpSeq() {
		return pSeq;
	}
	public void setpSEq(int pSEq) {
		this.pSeq = pSEq;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public int getpPrice() {
		return pPrice;
	}
	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}

	public String getpDetail() {
		return pDetail;
	}
	public void setpDetail(String pDetail) {
		this.pDetail = pDetail;
	}
	
	
}
