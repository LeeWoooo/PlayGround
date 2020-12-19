package logAnalysis;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class LogAnalysis_View extends JDialog {

	private LogAnalysis_AnalysisEvt laae;

	private JButton jtbnReport;
	private JButton jbtnClose;

	private JTextArea jtaAllResult;
	private JScrollPane jspAllResult;

	public LogAnalysis_View(LogAnalysis_Analysis laa, LogAnalysis_AnalysisEvt laae) {

		super(laa, "View", false);
		this.laae = laae;
		
		LogAnalysis_ViewEvt lave = new LogAnalysis_ViewEvt(this, this.laae);

		jtbnReport = new JButton("Report");
		jbtnClose = new JButton("Close");

		jtaAllResult = new JTextArea();

		jspAllResult = new JScrollPane(jtaAllResult);
		jspAllResult.setBorder(new TitledBorder("결과창"));
		jtaAllResult.append("분석결과\n");
		jtaAllResult.append(lave.nowTime());
		jtaAllResult.append("\n============================\n");

		JPanel jpSouth = new JPanel();
		jpSouth.add(jtbnReport);
		jpSouth.add(jbtnClose);

		add("Center", jspAllResult);
		add("South", jpSouth);

		setVisible(true);
		setLocationRelativeTo(null);
		setSize(500, 610);
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		jtbnReport.addActionListener(lave);
		jbtnClose.addActionListener(lave);
		addWindowListener(lave);

	}// LogAnalysis_View

	

	public JButton getJtbnReport() {
		return jtbnReport;
	}// getJtbnReport

	public JButton getJbtnClose() {
		return jbtnClose;
	}// getJbtnClose

	public JTextArea getJtaAllResult() {
		return jtaAllResult;
	}// getJtaAllResult

	public JScrollPane getJspAllResult() {
		return jspAllResult;
	}// getJspAllResult

}// LogAnalysis_View
