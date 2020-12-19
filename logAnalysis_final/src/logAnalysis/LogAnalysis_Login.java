package logAnalysis;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class LogAnalysis_Login extends JFrame {

	// 이벤트 관련 컴포넌트
	private JTextField jtfId;
	private JPasswordField jpfPassword;

	private JButton jbtLogin;
	private JButton jbtnclose;

	public LogAnalysis_Login() {

		super("LogAnalysis Login");

		jtfId = new JTextField();
		jtfId.setBorder(new TitledBorder("ID"));
		jpfPassword = new JPasswordField();
		jpfPassword.setBorder(new TitledBorder("PassWord"));

		jbtLogin = new JButton("Login");
		jbtnclose = new JButton("Close");

		JPanel jplogin = new JPanel(new GridLayout(2, 1));
		jplogin.add(jtfId);
		jplogin.add(jpfPassword);

		JPanel jpbtn = new JPanel();
		jpbtn.add(jbtLogin);
		jpbtn.add(jbtnclose);

		add("Center", jplogin);
		add("South", jpbtn);

		setVisible(true);
		setSize(300, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		LogAnalysis_LoginEvt lale = new LogAnalysis_LoginEvt(this);
		jtfId.addActionListener(lale);
		jpfPassword.addActionListener(lale);
		jbtLogin.addActionListener(lale);
		jbtnclose.addActionListener(lale);
		
		addWindowListener(lale);

	}// LogAnalysis_Login

	public JTextField getJtfId() {
		return jtfId;
	}// getJtfId

	public JPasswordField getJpfPassword() {
		return jpfPassword;
	}// getJpfPassword

	public JButton getJbtLogin() {
		return jbtLogin;
	}// getJbtLogin

	public JButton getJbtnclose() {
		return jbtnclose;
	}// getJbtnclose

}// end class
