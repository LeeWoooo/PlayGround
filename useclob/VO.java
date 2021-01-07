package useclob;

public class VO {

	private String title;
	private String article;
	private String reporterName;
	private String inputDate;

	public VO() {
		super();
	}

	public VO(String title, String article, String reporterName, String inputDate) {
		super();
		this.title = title;
		this.article = article;
		this.reporterName = reporterName;
		this.inputDate = inputDate;
	}

	public String getTitle() {
		return title;
	}

	public String getArticle() {
		return article;
	}

	public String getReporterName() {
		return reporterName;
	}

	public String getInputDate() {
		return inputDate;
	}

	@Override
	public String toString() {
		return "VO [title=" + title + ", article=" + article + ", reporterName=" + reporterName + ", inputDate="
				+ inputDate + "]";
	}

}
