package day210104.work;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Event extends WindowAdapter implements ActionListener {

	private Design design;
	private String inputTableName;
	private String inputColumnName;
	private String inputColumnDataType;
	private String inputColumnSize;
	private boolean createFlag;

	public Event(Design design) {
		this.design = design;
	}

	/**
	 * textarea�� create�� �߰�
	 */
	public boolean createTable() {
		boolean flag = false;
		String inputName = design.getJtfTableName().getText().trim();
		if ("".equals(inputName)) {
			JOptionPane.showMessageDialog(design, "Table�� �Է��� �ʼ��Դϴ�.");
			design.getJtfTableName().requestFocus();
			return flag;
		} // end if
		StringBuilder sbCreate = new StringBuilder();
		sbCreate.append("CREATE TABLE ").append(inputName).append(" (\n");
		design.getJtaQuery().append(sbCreate.toString());
		inputTableName = inputName;
		return true;
	}// createTable

	public boolean colNameNullCheck() {
		boolean flag = false;
		String inputName = design.getJtfColumnName().getText().trim();
		if ("".equals(inputName)) {
			JOptionPane.showMessageDialog(design, "Column���� �ʼ� �Է��Դϴ�.");
			design.getJtfColumnName().requestFocus();
			return flag;
		} // end if
		inputColumnName = inputName;
		return true;
	}// colNullCheck

	public boolean colSizeCheck() {
		boolean flag = false;
		String inputDataType = design.getDcbmDatatype().getElementAt(design.getJcbColumnDatatype().getSelectedIndex());
		String inputSize = design.getJtfColumnDateSize().getText();
		if (!"".equals(inputSize) && (("DATE".equals(inputDataType)) || ("CLOB".equals(inputDataType)))) {
			JOptionPane.showMessageDialog(design, "DataType�� DATE, CLOB�� ��� size�� �Է��� �� �����ϴ�.");
			design.getJtfColumnDateSize().setText("");
			return flag;
		} // end if
		inputColumnDataType = inputDataType;
		inputColumnSize = inputSize;
		return true;
	}// colSizeCheck

	public StringBuilder primaryCheck(StringBuilder sbColumn) {
		StringBuilder sbprimary = sbColumn;
		if (design.getJcbPrimary().isSelected()) {
			sbprimary.append(" CONSTRAINT PK_").append(inputTableName).append(" PRIMARY KEY");
			return sbprimary;
		} // end if
		return sbprimary;
	}// primaryCheck

	public void AddColumn() {
		StringBuilder sbColumn = new StringBuilder();
		if ("DATE".equals(inputColumnDataType) || "CLOB".equals(inputColumnDataType)) {
			sbColumn.append(" ").append(inputColumnName).append(" ").append(inputColumnDataType);
			primaryCheck(sbColumn);
		} else {
			sbColumn.append(" ").append(inputColumnName).append(" ").append(inputColumnDataType).append("(")
					.append(inputColumnSize).append(")");
			primaryCheck(sbColumn);
		}
		design.getJtaQuery().append(sbColumn.toString());
	}// inputColumn

//	public void 

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == design.getJbtnTableName()) {
			createFlag = createTable();
		} // end if

		if (ae.getSource() == design.getJbtnColumn()) {
			if (createFlag) {
				if (colNameNullCheck() && colSizeCheck()) {
					AddColumn();
				} // end if
			} else {
				JOptionPane.showMessageDialog(design, "Create���� �߰� �� ������ּ���.");
				design.getJtfTableName().requestFocus();
			} // end if
		} // end if

		if (ae.getSource() == design.getJbtnCreate()) {
			DAO dao = DAO.getInstance();
			String query = design.getJtaQuery().getText();
			try {
				dao.createTable(query);
				JOptionPane.showMessageDialog(design, "���̺� ������ �Ϸ� �Ǿ����ϴ�.");
			} catch (SQLException e) {
				switch (e.getErrorCode()) {
				case 2260:
					JOptionPane.showMessageDialog(design, "PRIMARY KEY�Ӽ��� ���� column�� �Ѱ��� ������ �� �ֽ��ϴ�.\nȮ�� �� �ٽ� �ۼ����ּ���.");
					break;
				case 955:
					JOptionPane.showMessageDialog(design, "�Է��� ���̺���� �������ִ� ���̺��� �����մϴ�.\nȮ�� �� �ٽ� �ۼ����ּ���.");
					break;
				case 903:
					JOptionPane.showMessageDialog(design, "�Է��� ���̺��� ������ �մϴ�.\nȮ�� �� �ٽ� �ۼ����ּ���.");
					break;
				case 957:
					JOptionPane.showMessageDialog(design, "�Է��� column�� �ߺ� ���� �ֽ��ϴ�.\nȮ�� �� �ٽ� �ۼ����ּ���.");
					break;
				case 907:
					JOptionPane.showMessageDialog(design, "������ ���ȣ�� �ֽ��ϴ�.\nȮ�� �� �ٽ� �ۼ����ּ���.");
					break;
				case 922:
					JOptionPane.showMessageDialog(design, "�����ǰų� �������� �ɼ��� �����մϴ�.\nȮ�� �� �ٽ� �ۼ����ּ���.");
					break;
				case 1727:
					JOptionPane.showMessageDialog(design, "Size �Է��Ͻ� column�Ӽ��� size���� ���ڸ� �Է��ؾ��մϴ�.\nȮ�� �� �ٽ� �ۼ����ּ���.");
					break;
				case 910:
					JOptionPane.showMessageDialog(design, "Size �Է¹����� Ȯ�����ּ���.\nȮ�� �� �ٽ� �ۼ����ּ���.");
					break;
				}// end switch
				e.printStackTrace();
			} // end catch
		} // end if

		if (ae.getSource() == design.getJbtnComma()) {
			design.getJtaQuery().append(",");
		} // end if

		if (ae.getSource() == design.getJbtnEnter()) {
			design.getJtaQuery().append("\n");
		} // end if

		if (ae.getSource() == design.getJbtnEndBracket()) {
			design.getJtaQuery().append(" )");
		} // end if

		if (ae.getSource() == design.getJbtnSemicolon()) {
			design.getJtaQuery().append(";");
		} // end if

	}// actionPerformed

	@Override
	public void windowClosing(WindowEvent we) {
		design.dispose();
	}// windowClosing

}// class
