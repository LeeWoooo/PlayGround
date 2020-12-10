package testtest.copy;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
//windows component를 상속
public class InitialScreen extends JFrame {

	// 이벤트에 이용될 component 선언

	private JButton jbtnLogin;
	private JButton jbtnClose;
	private JTextField jtfID;
	private JPasswordField jpf;

	// 생성자 작성
	public InitialScreen() {

		super("Log분석-로그인 화면");

		jbtnLogin = new JButton("Login");
		jbtnClose = new JButton("Exit");

		jtfID = new JTextField();
		jtfID.setBorder(new TitledBorder("ID"));
		jtfID.setToolTipText("아이디를 입력해주세요");

		jpf = new JPasswordField();
		jpf.setBorder(new TitledBorder("Password"));
		jpf.setToolTipText("비밀번호를 입력해주세요");

		JPanel jpbtn = new JPanel();
		jpbtn.add(jbtnLogin);
		jpbtn.add(jbtnClose);

		JPanel jpinput = new JPanel(new GridLayout(2,1));
		jpinput.add(jtfID);
		jpinput.add(jpf);
		
		add("Center",jpinput);
		add("South",jpbtn);

		setVisible(true);
		setLocationRelativeTo(null); // 프로그램 실행 시 중앙에 위치
		setSize(350, 200);
		setResizable(false);
		//window x버튼 이벤트를 추가하기 위해서 설정
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);


		//event등록
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
