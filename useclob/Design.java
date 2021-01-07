package useclob;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class Design extends JFrame {

	private JTextField jtftablename;
	private JTextArea jtatitle;
	private JTextArea jtaarticle;
	private JTextField jtfreporter;
	private JTextField jtfinputdate;
	private JButton jbtnsearch;
	
	public Design() {
		super("기사");

		jtftablename = new JTextField(15);
		jtfreporter = new JTextField(15);
		jtfinputdate = new JTextField(15);
		jbtnsearch = new JButton("조회");
		jtatitle = new JTextArea(5,50);
		JScrollPane jsptitle = new JScrollPane(jtatitle);
		jsptitle.setBorder(new TitledBorder("기사제목"));
		jtaarticle = new JTextArea(5,50);
		jtaarticle.setLineWrap(true);
		jtaarticle.setWrapStyleWord(true);
		JScrollPane jsparticle = new JScrollPane(jtaarticle);
		jsparticle.setBorder(new TitledBorder("기사"));
		
		
		JPanel jpNorth = new JPanel();
		jpNorth.add(jtftablename);
		jpNorth.add(jbtnsearch);
		
		JPanel jpSouth = new JPanel();
		jpSouth.add(jtfreporter);
		jpSouth.add(jtfinputdate);
		
		JPanel jp1 = new JPanel();
		
		setLayout(new GridLayout(5,1));
		add(jpNorth);
		add(jsptitle);
		add(jp1);
		add(jsparticle);
		add(jpSouth);
		
		Event event = new Event(this);
		jbtnsearch.addActionListener(event);
		
		setVisible(true);
		setLocationRelativeTo(null);
		setSize(600,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}//Design
	
	
	
	public JTextField getJtftablename() {
		return jtftablename;
	}


	public JTextArea getJtatitle() {
		return jtatitle;
	}


	public JTextArea getJtaarticle() {
		return jtaarticle;
	}


	public JTextField getJtfreporter() {
		return jtfreporter;
	}


	public JTextField getJtfinputdate() {
		return jtfinputdate;
	}


	public JButton getJbtnsearch() {
		return jbtnsearch;
	}


	public static void main(String[] args) {
		new Design();
	}//main

}//class
