package logAnalysis;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class LogAnalysis_Analysis extends JDialog {

	private JButton jbtnOpen;
	private JButton jbtnView;
	private JButton jbtnClose;

	private JTextArea jtaLog;
	private JTextArea jtaresult;

	private JTextField jtfPath;
	private JTextField jtfStrat;
	private JTextField jtfEnd;

	private DefaultListModel<String> dlmEx;
	private JScrollPane jspEx;
	private JList<String> jlEx;

	public LogAnalysis_Analysis(LogAnalysis_Login lal) {

		super(lal, "LogAnalysis", false);

		jbtnOpen = new JButton("Log Open");
		jbtnView = new JButton("View");
		jbtnClose = new JButton("Close");

		jtaLog = new JTextArea();
		JScrollPane jspLog = new JScrollPane(jtaLog);
		jspLog.setBorder(new TitledBorder("Log"));

		jtaresult = new JTextArea();
		jtaresult.setBorder(new TitledBorder("Result"));

		jtfPath = new JTextField();
		jtfPath.setBorder(new TitledBorder("Path"));

		jtfStrat = new JTextField();
		jtfStrat.setBorder(new TitledBorder("Start Line"));

		jtfEnd = new JTextField();
		jtfEnd.setBorder(new TitledBorder("End Line"));

		dlmEx = new DefaultListModel<String>();
		jlEx = new JList<String>(dlmEx);
		jspEx = new JScrollPane(jlEx);
		jspEx.setBorder(new TitledBorder("Function"));
		dlmEx.addElement("1. �ִ� ��� key�м� (����, Ƚ��)");
		dlmEx.addElement("2. Browser�м� (����Ƚ��, ����)");
		dlmEx.addElement("3. ���� �������κм� (����Ƚ��, ����Ƚ��)");
		dlmEx.addElement("4. ��û�� ���� ���� �ð��м�");
		dlmEx.addElement("5. ���������� ��û�м� (�߻��� Ƚ��, ����)");
		dlmEx.addElement("6. ����� ���� key�м�(����, Ƚ��)");
		
		LogAnalysis_AnalysisEvt laae = new LogAnalysis_AnalysisEvt(this); 
		jbtnOpen.addActionListener(laae);
		jbtnClose.addActionListener(laae);
		jbtnView.addActionListener(laae);
		addWindowListener(laae);
		
		
		setLayout(null);

		jtfPath.setBounds(30, 30, 450, 50);
		add(jtfPath);

		jbtnOpen.setBounds(500, 30, 150, 50);
		add(jbtnOpen);

		jspLog.setBounds(30, 100, 620, 200);
		add(jspLog);

		jspEx.setBounds(30, 320, 300, 200);
		add(jspEx);

		jtfStrat.setBounds(30, 530, 120, 40);
		add(jtfStrat);

		jtfEnd.setBounds(210, 530, 120, 40);
		add(jtfEnd);

		jtaresult.setBounds(350, 320, 300, 200);
		add(jtaresult);

		jbtnView.setBounds(480, 530, 80, 40);
		add(jbtnView);


		jbtnClose.setBounds(570, 530, 80, 40);
		add(jbtnClose);

		setVisible(true);
		setSize(700, 620);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

	}// LogAnalysis_Analysis

	public JButton getJbtnOpen() {
		return jbtnOpen;
	}// getJbtnOpen

	public JButton getJbtnView() {
		return jbtnView;
	}// getJbtnView

	public JButton getJbtnClose() {
		return jbtnClose;
	}// getJbtnClose

	public JTextArea getJtaLog() {
		return jtaLog;
	}// getJtaLog

	public JTextArea getJtaresult() {
		return jtaresult;
	}// getJtaresult

	public JTextField getJtfPath() {
		return jtfPath;
	}// getJtfPath

	public JTextField getJtfStrat() {
		return jtfStrat;
	}// getJtfStrat

	public JTextField getJtfEnd() {
		return jtfEnd;
	}// getJtfEnd

	public DefaultListModel<String> getDlmEx() {
		return dlmEx;
	}// getDlmEx

	public JList<String> getJlEx() {
		return jlEx;
	}// getJlEx

	public JScrollPane getJspEx() {
		return jspEx;
	}// getJspEx

}// class
