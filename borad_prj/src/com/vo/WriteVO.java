package com.vo;

public class WriteVO {
	private String bbsTitle;
	private String bbsuserID;
	private String bbsContent;
	
	public WriteVO(String bbsTitle, String bbsuserID, String bbsContent) {
		super();
		this.bbsTitle = bbsTitle;
		this.bbsuserID = bbsuserID;
		this.bbsContent = bbsContent;
	}

	public String getBbsTitle() {
		return bbsTitle;
	}

	public String getBbsUserID() {
		return bbsuserID;
	}

	public String getBbsContent() {
		return bbsContent;
	}
	
}
