package leeproject.notepad;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class JavaMemoInfo extends JDialog {
	
	private JButton jbtnClose;

	public JavaMemoInfo(JavaMemo jm) {
		
		super(jm, "메모장도움말", false);
		
		ImageIcon ii = new ImageIcon("D:/workspace/leeproject/src/leeproject/notepad/img/splash.png");
		
		//이미지를 넣을 JLabel
		JLabel jbImg = new JLabel(ii);
		//투명도 해제
		jbImg.setOpaque(true);
		
		//메모장 정보 JLable
		JPanel jpText = new JPanel();
		JLabel jbMemo = new JLabel("자바 메모장");
		JLabel jbMaker = new JLabel("제작자 : Leewooo");
		JLabel jbLicense = new JLabel("License is Free");
		jpText.add(jbMemo);
		jpText.add(jbMaker);
		jpText.add(jbLicense);
		
		//메모장 정보 닫기 JButton
		jbtnClose = new JButton("닫기");
		
		//이벤트 할인
		JavaMemoInfoEvt jmie = new JavaMemoInfoEvt(this);
		jbtnClose.addActionListener(jmie);
		
		addWindowListener(jmie);
		
		//component 배치
		setLayout(null);
		
		jbImg.setBounds(0, 0, 300, 300);
		jpText.setBounds(320,0,100,100);
		jbtnClose.setBounds(320,150,100,50);
		
		add(jbImg);
		add(jpText);
		add(jbtnClose);
		
		//위치 및 크기
		setBounds(jm.getX()+50, jm.getY()+50, 470, 330);
		
		//보여주기
		setVisible(true);
		
	}//MemoHelp

	public JButton getJbtnClose() {
		return jbtnClose;
	}//getJbtnClose
	
	
}//class
