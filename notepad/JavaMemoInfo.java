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
		
		super(jm, "�޸��嵵��", false);
		
		ImageIcon ii = new ImageIcon("D:/workspace/leeproject/src/leeproject/notepad/img/splash.png");
		
		//�̹����� ���� JLabel
		JLabel jbImg = new JLabel(ii);
		//���� ����
		jbImg.setOpaque(true);
		
		//�޸��� ���� JLable
		JPanel jpText = new JPanel();
		JLabel jbMemo = new JLabel("�ڹ� �޸���");
		JLabel jbMaker = new JLabel("������ : Leewooo");
		JLabel jbLicense = new JLabel("License is Free");
		jpText.add(jbMemo);
		jpText.add(jbMaker);
		jpText.add(jbLicense);
		
		//�޸��� ���� �ݱ� JButton
		jbtnClose = new JButton("�ݱ�");
		
		//�̺�Ʈ ����
		JavaMemoInfoEvt jmie = new JavaMemoInfoEvt(this);
		jbtnClose.addActionListener(jmie);
		
		addWindowListener(jmie);
		
		//component ��ġ
		setLayout(null);
		
		jbImg.setBounds(0, 0, 300, 300);
		jpText.setBounds(320,0,100,100);
		jbtnClose.setBounds(320,150,100,50);
		
		add(jbImg);
		add(jpText);
		add(jbtnClose);
		
		//��ġ �� ũ��
		setBounds(jm.getX()+50, jm.getY()+50, 470, 330);
		
		//�����ֱ�
		setVisible(true);
		
	}//MemoHelp

	public JButton getJbtnClose() {
		return jbtnClose;
	}//getJbtnClose
	
	
}//class
