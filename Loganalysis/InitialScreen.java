package testtest.copy;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
//windows component�� ���
public class InitialScreen extends JFrame {

	// �̺�Ʈ�� �̿�� component ����

	private JButton jbtnLogin;
	private JButton jbtnClose;
	private JTextField jtfID;
	private JPasswordField jpf;

	// ������ �ۼ�
	public InitialScreen() {

		super("Log�м�-�α��� ȭ��");

		jbtnLogin = new JButton("Login");
		jbtnClose = new JButton("Exit");

		jtfID = new JTextField();
		jtfID.setBorder(new TitledBorder("ID"));
		jtfID.setToolTipText("���̵� �Է����ּ���");

		jpf = new JPasswordField();
		jpf.setBorder(new TitledBorder("Password"));
		jpf.setToolTipText("��й�ȣ�� �Է����ּ���");

		JPanel jpbtn = new JPanel();
		jpbtn.add(jbtnLogin);
		jpbtn.add(jbtnClose);

		JPanel jpinput = new JPanel(new GridLayout(2,1));
		jpinput.add(jtfID);
		jpinput.add(jpf);
		
		add("Center",jpinput);
		add("South",jpbtn);

		setVisible(true);
		setLocationRelativeTo(null); // ���α׷� ���� �� �߾ӿ� ��ġ
		setSize(350, 200);
		setResizable(false);
		//window x��ư �̺�Ʈ�� �߰��ϱ� ���ؼ� ����
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);


		//event���
		InitialScreenEvt ise = new InitialScreenEvt(this);
		addWindowListener(ise);
		jbtnLogin.addActionListener(ise);
		jbtnClose.addActionListener(ise);
		
	}// InitialScreen

	public JButton getJbtnLogin() {
		return jbtnLogin;
	}//getJbtnLogin

	public JButton getJbtnClose() {

		return jbtnClose;
	}//getJbtnClose
	
	public JTextField getJtfID() {
		return jtfID;
	}//getJtfID
	
	public JPasswordField getJpf() {
		return jpf;
	}//getJpf
	
	
}// InitialScreen
