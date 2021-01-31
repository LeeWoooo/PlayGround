package com.vo;

public class UpdateVO {
	private int bbsID;
	private String bbsTitle;
	private String bbsContent;
	
	public UpdateVO(int bbsID, String bbsTitle, String bbsContent) {
		super();
		this.bbsID = bbsID;
		this.bbsTitle = bbsTitle;
		this.bbsContent = bbsContent;
	}
	
	public int getBbsID() {
		return bbsID;
	}
	public String getBbsTitle() {
		return bbsTitle;
	}
	public String getBbsContent() {
		return bbsContent;
	}
	
	
}
