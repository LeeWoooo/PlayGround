package day210104.work;

import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Design extends JFrame {

	private JTextField jtfTableName;
	private JTextField jtfColumnName;
	private DefaultComboBoxModel<String> dcbmDatatype;
	private JComboBox<String> jcbColumnDatatype;
	private JTextField jtfColumnDateSize;
	
	private JTextArea jtaQuery;
	private JScrollPane jspQuery;

	private JButton jbtnTableName;
	private JButton jbtnColumn;
	private JButton jbtnCreate;
	private JButton jbtnComma;
	private JButton jbtnEndBracket;
	private JButton jbtnSemicolon;
	private JButton jbtnEnter;
	private JCheckBox jcbPrimary;

	public Design() {
		super("테이블 생성");

		JLabel jlTable = new JLabel("Table명");
		jtfTableName = new JTextField(10);
		JLabel jlColumn = new JLabel("Column명");
		jtfColumnName = new JTextField(10);
		JLabel jlData = new JLabel("Data Type");
		dcbmDatatype = new DefaultComboBoxModel<String>();
		String dataType[] = new String[] { "NUMBER", "CHAR", "VARCHAR2", "DATE","CLOB" };
		for (String data : dataType) {
			dcbmDatatype.addElement(data);
		} // end for
		jcbColumnDatatype = new JComboBox<String>(dcbmDatatype);
		JLabel jlSize = new JLabel("Size");
		jtfColumnDateSize = new JTextField(10);
		jcbPrimary = new JCheckBox("Primary Key");
		jtaQuery = new JTextArea();
		jtaQuery.setLineWrap(true);
		jspQuery = new JScrollPane(jtaQuery);

		jbtnTableName = new JButton("테이블명 추가");
		jbtnColumn = new JButton("column 추가");
		jbtnCreate = new JButton("테이블 추가");
		jbtnComma = new JButton(",");
		jbtnEndBracket = new JButton(")");
		jbtnSemicolon = new JButton(";");
		jbtnEnter = new JButton("ENTER");

		JPanel jpNorth1 = new JPanel();
		JPanel jpNorth2 = new JPanel();
		JPanel jpNorth3 = new JPanel();
		JPanel jpNorth4 = new JPanel();

		jpNorth1.setLayout(new GridLayout(3, 1));
		jpNorth2.add(jlTable);
		jpNorth2.add(jtfTableName);
		jpNorth2.add(jbtnTableName);
		jpNorth1.add(jpNorth2);
		jpNorth3.add(jlColumn);
		jpNorth3.add(jtfColumnName);
		jpNorth3.add(jlData);
		jpNorth3.add(jcbColumnDatatype);
		jpNorth3.add(jlSize);
		jpNorth3.add(jtfColumnDateSize);
		jpNorth3.add(jcbPrimary);
		jpNorth3.add(jbtnColumn);
		jpNorth1.add(jpNorth3);
		jpNorth4.add(jbtnComma);
		jpNorth4.add(jbtnEndBracket);
		jpNorth4.add(jbtnSemicolon);
		jpNorth4.add(jbtnEnter);
		jpNorth1.add(jpNorth4);

		Event event2 = new Event(this);
		addWindowListener(event2);
		jbtnTableName.addActionListener(event2);
		jbtnColumn.addActionListener(event2);
		jbtnComma.addActionListener(event2);
		jbtnEndBracket.addActionListener(event2);
		jbtnSemicolon.addActionListener(event2);
		jbtnEnter.addActionListener(event2);
		jbtnCreate.addActionListener(event2);
		
		add("North", jpNorth1);
		add("Center", jspQuery);
		add("South", jbtnCreate);

		setLocationRelativeTo(null);
		setSize(850, 400);
		setVisible(true);

	}// Design

	public JTextField getJtfTableName() {
		return jtfTableName;
	}

	public JTextField getJtfColumnName() {
		return jtfColumnName;
	}

	public DefaultComboBoxModel<String> getDcbmDatatype() {
		return dcbmDatatype;
	}

	public JComboBox<String> getJcbColumnDatatype() {
		return jcbColumnDatatype;
	}

	public JTextField getJtfColumnDateSize() {
		return jtfColumnDateSize;
	}

	public JTextArea getJtaQuery() {
		return jtaQuery;
	}

	public JButton getJbtnTableName() {
		return jbtnTableName;
	}

	public JButton getJbtnColumn() {
		return jbtnColumn;
	}

	public JButton getJbtnCreate() {
		return jbtnCreate;
	}

	public JCheckBox getJcbPrimary() {
		return jcbPrimary;
	}
	
	public JButton getJbtnComma() {
		return jbtnComma;
	}
	
	public JButton getJbtnEndBracket() {
		return jbtnEndBracket;
	}

	public JButton getJbtnSemicolon() {
		return jbtnSemicolon;
	}

	public JButton getJbtnEnter() {
		return jbtnEnter;
	}

	public static void main(String[] args) {
		new Design();
	}// main

}// class
