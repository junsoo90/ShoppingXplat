package com.admin.VO;

public class OptionVO {

	private int pSeq;
	private String pOption;
	private int optionCnt;
	private int optionStock;
	private int optionPrice;
	
	public OptionVO() {}
	
	public OptionVO(int pSeq, String pOption, int optionCnt, int optionStock, int optionPrice) {
		super();
		this.pSeq=pSeq;
		this.pOption = pOption;
		this.optionCnt = optionCnt;
		this.optionStock = optionStock;
		this.optionPrice = optionPrice;
	}
	
	public OptionVO(String pOption, int optionCnt, int optionStock, int optionPrice) {
		super();
		this.pOption = pOption;
		this.optionCnt = optionCnt;
		this.optionStock = optionStock;
		this.optionPrice = optionPrice;
	}
	
	
	public int getOptionPrice() {
		return optionPrice;
	}
	public void setOptionPrice(int optionPrice) {
		this.optionPrice = optionPrice;
	}
	public int getOptionStock() {
		return optionStock;
	}
	public void setOptionStock(int optionStock) {
		this.optionStock = optionStock;
	}
	public int getOptionCnt() {
		return optionCnt;
	}
	public void setOptionCnt(int optionCnt) {
		this.optionCnt = optionCnt;
	}
	public int getpSeq() {
		return pSeq;
	}
	public void setpSeq(int pSeq) {
		this.pSeq = pSeq;
	}
	public String getpOption() {
		return pOption;
	}
	public void setpOption(String pOption) {
		this.pOption = pOption;
	}
	
	
}