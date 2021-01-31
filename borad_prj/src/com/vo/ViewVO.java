package com.vo;

public class ViewVO {
	private String bbsTitle;
	private String bbsuserID; 
	private String bbsDate; 
	private String bbsContent;
	
	
	public ViewVO(String bbsTitle, String bbsuserID, String bbsDate, String bbsContent) {
		super();
		this.bbsTitle = bbsTitle;
		this.bbsuserID = bbsuserID;
		this.bbsDate = bbsDate;
		this.bbsContent = bbsContent;
	}
	
	public String getBbsTitle() {
		return bbsTitle;
	}
	public String getBbsuserID() {
		return bbsuserID;
	}
	public String getBbsDate() {
		return bbsDate;
	}
	public String getBbsContent() {
		return bbsContent;
	}

	@Override
	public String toString() {
		return "ViewVO [bbsTitle=" + bbsTitle + ", bbsuserID=" + bbsuserID + ", bbsDate=" + bbsDate + ", bbsContent="
				+ bbsContent + "]";
	}
	
	
	
}
	
