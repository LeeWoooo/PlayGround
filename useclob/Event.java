package useclob;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class Event implements ActionListener{

	private Design design;
	
	public Event(Design desing) {
		this.design = desing;
	}
	
	
	public void print() {
		DAO dao = DAO.getInstance();
		int inputNum = Integer.parseInt(design.getJtftablename().getText());
		try {
			List<VO> list = dao.selectclob();
			String title = list.get(inputNum).getTitle();
			String article = list.get(inputNum).getArticle();
			String repoter = list.get(inputNum).getReporterName();
			String inputDate = list.get(inputNum).getInputDate();
			design.getJtatitle().setText(title);
			design.getJtaarticle().setText(article);
			design.getJtfreporter().setText(repoter);
			design.getJtfinputdate().setText(inputDate);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == design.getJbtnsearch()) {
			print();
		}//end if
	}//actionPerformed
	
}//class
