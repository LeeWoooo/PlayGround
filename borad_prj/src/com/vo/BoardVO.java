package com.vo;

public class BoardVO {
	private int bbsID;
	private String bbsTitle;
	private String bbsUserID;
	private String BBSDate;
	private String bbsContent;
	
	public BoardVO() {
		super();
	}

	public BoardVO(int bbsID, String bbsTitle, String bbsUserID, String bBSDate, String bbsContent) {
		super();
		this.bbsID = bbsID;
		this.bbsTitle = bbsTitle;
		this.bbsUserID = bbsUserID;
		BBSDate = bBSDate;
		this.bbsContent = bbsContent;
	}
	
	public int getBbsID() {
		return bbsID;
	}
	public String getBbsTitle() {
		return bbsTitle;
	}
	public String getBbsUserID() {
		return bbsUserID;
	}
	public String getBBSDate() {
		return BBSDate;
	}
	public String getBbsContent() {
		return bbsContent;
	}
	
	
}	
