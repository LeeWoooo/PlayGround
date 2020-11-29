package leeproject.notepad;


import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


@SuppressWarnings("serial")

//Window component ���
public class JavaMemo extends JFrame {

	public static final String[] FONT = {"Dialog","DialogInput","Monospaced","Serif","SansSerif"};
	public static final String[] STYLENAME = {"PLAIN","BOLD","ITALIC","BOLDITALIC"};
	public static final int[] STYLE = {Font.PLAIN,Font.BOLD,Font.ITALIC,Font.BOLD+Font.ITALIC};
	public static final int[] SIZE= new int[100-12+1];

	//�̺�Ʈ�� ���õ� component����
	
	private JTextArea jtaMemo;
	
	private JMenuItem jmiNew;
	private JMenuItem jmiOpen;
	private JMenuItem jmiSave;
	private JMenuItem jmiNewSave;
	private JMenuItem jmiClose;
	private JMenuItem jmiFont;
	private JMenuItem jmiHelp;
	
	private Font ft;
	
	//������ �ۼ�
	public JavaMemo() {
		
		super("JavaNotePad");
		
		//SIZE�� ���Է�
		addSize();
//		//SIZE �Է°� Ȯ��
//		for(int i = 0; i < SIZE.length; i++) {
//		System.out.println(SIZE[i]);
//		}
		
		//component ����
		
		JMenuBar jb = new JMenuBar();
		
		//text area
		jtaMemo = new JTextArea();
		ft = new Font(FONT[0], STYLE[0],SIZE[0]);
		jtaMemo.setFont(ft);
		JScrollPane jsp = new JScrollPane(jtaMemo);
		
		//file
		JMenu jmFile = new JMenu("File");
		jmiNew = new JMenuItem("�� ��");
		jmiOpen = new JMenuItem("����");
		jmiSave = new JMenuItem("����");
		jmiNewSave = new JMenuItem("�� �̸����� ����");
		jmiClose = new JMenuItem("�ݱ�");
		
		jmFile.add(jmiNew);
		jmFile.addSeparator();
		jmFile.add(jmiOpen);
		jmFile.add(jmiSave);
		jmFile.add(jmiNewSave);
		jmFile.addSeparator();
		jmFile.add(jmiClose);
		
		
		//font
		JMenu jmFont = new JMenu("Font");
		jmiFont = new JMenuItem("�۲�");
		jmFont.add(jmiFont);
		
		
		//help
		JMenu jmHelp = new JMenu("Help");
		jmiHelp = new JMenuItem("�޸�������");
		jmHelp.add(jmiHelp);
		
		
		//�̺�Ʈ �߰��ϱ�
		JavaMemoEvt jme = new JavaMemoEvt(this);
		jmiNew.addActionListener(jme);
		jmiOpen.addActionListener(jme);
		jmiSave.addActionListener(jme);
		jmiNewSave.addActionListener(jme);
		jmiClose.addActionListener(jme);
		
		jmiFont.addActionListener(jme);
		
		jmiHelp.addActionListener(jme);
		
		
		addWindowListener(jme);
		
		//menubar�� ���̱�
		jb.add(jmFile);
		jb.add(jmFont);
		jb.add(jmHelp);
		
		//��ġ������
		setLayout(new BorderLayout());
		
		//component ��ġ.
		setJMenuBar(jb);
		add(jsp);
		
		//windowũ�⼳��
		setBounds(50, 50, 500, 250);
		
		//����ڿ��� �����ֱ�
		setVisible(true);
		
	}//JavaMemo
	
	//getter�߰�
	
	public JTextArea getJtaMemo() {
		return jtaMemo;
	}//getJtaMemo

	public JMenuItem getJmiNew() {
		return jmiNew;
	}//getJmiNew

	public JMenuItem getJmiOpen() {
		return jmiOpen;
	}//getJmiOpen

	public JMenuItem getJmiSave() {
		return jmiSave;
	}//getJmiSave

	public JMenuItem getJmiNewSave() {
		return jmiNewSave;
	}//getJmiNewSave

	public JMenuItem getJmiClose() {
		return jmiClose;
	}//getJmiClose

	public JMenuItem getJmiFont() {
		return jmiFont;
	}//getJmiFont

	public JMenuItem getJmiHelp() {
		return jmiHelp;
	}//getJmiHelp

	public Font getFt() {
		return ft;
	}//getFt

	//Size �迭�� 12���� 100���� �߰�
	public void addSize() {
		for(int i = 0; i <89; i++) {
			SIZE[i]=12+i;
		}//end for
	}//addSize
	
}//class
