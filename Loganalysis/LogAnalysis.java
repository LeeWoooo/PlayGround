package testtest.copy;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class LogAnalysis extends JDialog {

	private JButton jbtnView;
	private JButton jbtnClose;
	private JButton jbtnExprot;
	private JButton jbtnOpen;

	private JTextArea jtaResult;

	private JTextField jtflogPath;

	private JTextField jtfstartLine;
	private JTextField jtfendLine;

	public LogAnalysis(InitialScreen is) {

		super(is, "Log�м�", false);

		jbtnOpen = new JButton("LogFile Open");
		jtflogPath = new JTextField();
		jtflogPath.setBorder(new TitledBorder("���� ���� �ִ� LogFile"));

		jtfstartLine = new JTextField();
		jtfstartLine.setBorder(new TitledBorder("����� ���� StartLine"));
		jtfstartLine.setToolTipText("������ �Է����ּ���");

		jtfendLine = new JTextField();
		jtfendLine.setBorder(new TitledBorder("����� ���� EndLine"));
		jtfendLine.setToolTipText("������ �Է����ּ���");

		jbtnView = new JButton("View");
		jbtnClose = new JButton("�ݱ�");
		jbtnExprot = new JButton("Export");

		jtaResult = new JTextArea();
		jtaResult.setEditable(false);
		Font ft = new Font("Monospaced", Font.PLAIN, 11);
		jtaResult.setFont(ft);
		JScrollPane jsp = new JScrollPane(jtaResult);

		setLayout(null);

		jbtnOpen.setBounds(550, 15, 150, 50);
		add(jbtnOpen);

		jtflogPath.setBounds(15, 15, 500, 50);
		add(jtflogPath);

		jsp.setBounds(15, 70, 450, 380);
		jsp.setBorder(new TitledBorder("Log�м� ���"));
		jtaResult.setLineWrap(true);
		add(jsp);

		jtfstartLine.setBounds(480, 100, 130, 50);
		add(jtfstartLine);

		jtfendLine.setBounds(620, 100, 130, 50);
		add(jtfendLine);

		jbtnView.setBounds(495, 200, 100, 50);
		add(jbtnView);

		jbtnExprot.setBounds(635, 200, 100, 50);
		add(jbtnExprot);

		jbtnClose.setBounds(635, 300, 100, 50);
		add(jbtnClose);

		setVisible(true);
		setLocationRelativeTo(null);
		setSize(800, 500);

		// �̺�Ʈ ���
		LogAnalysisEvt jae = new LogAnalysisEvt(this);
		jbtnOpen.addActionListener(jae);
		jbtnClose.addActionListener(jae);
		jbtnExprot.addActionListener(jae);
		jbtnView.addActionListener(jae);

		addWindowListener(jae);

	}// LogAnalysis

	public JButton getJbtnView() {
		return jbtnView;
	}// getJbtnOpen

	public JButton getJbtnClose() {
		return jbtnClose;
	}// getJbtnView

	public JButton getJbtnExprot() {
		return jbtnExprot;
	}// getJbtnExprot

	public JTextArea getJtaResult() {
		return jtaResult;
	}// getJta

	public JButton getJbtnOpen() {
		return jbtnOpen;
	}// getJbtnOpen

	public JTextField getJtflogPath() {
		return jtflogPath;
	}// getJtflogPath

	public JTextField getJtfstartLine() {
		return jtfstartLine;
	}// getJtfstartLine

	public JTextField getJtfendLine() {
		return jtfendLine;
	}// getJtfendLine

}// class
