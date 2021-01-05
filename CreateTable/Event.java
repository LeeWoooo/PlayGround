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
	 * textarea에 create문 추가
	 */
	public boolean createTable() {
		boolean flag = false;
		String inputName = design.getJtfTableName().getText().trim();
		if ("".equals(inputName)) {
			JOptionPane.showMessageDialog(design, "Table명 입력은 필수입니다.");
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
			JOptionPane.showMessageDialog(design, "Column명은 필수 입력입니다.");
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
			JOptionPane.showMessageDialog(design, "DataType이 DATE, CLOB일 경우 size를 입력할 수 없습니다.");
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
				JOptionPane.showMessageDialog(design, "Create문을 추가 후 사용해주세요.");
				design.getJtfTableName().requestFocus();
			} // end if
		} // end if

		if (ae.getSource() == design.getJbtnCreate()) {
			DAO dao = DAO.getInstance();
			String query = design.getJtaQuery().getText();
			try {
				dao.createTable(query);
				JOptionPane.showMessageDialog(design, "테이블 생성이 완료 되었습니다.");
			} catch (SQLException e) {
				switch (e.getErrorCode()) {
				case 2260:
					JOptionPane.showMessageDialog(design, "PRIMARY KEY속성을 가진 column은 한개만 지정할 수 있습니다.\n확인 후 다시 작성해주세요.");
					break;
				case 955:
					JOptionPane.showMessageDialog(design, "입력한 테이블명을 가지고있는 테이블이 존재합니다.\n확인 후 다시 작성해주세요.");
					break;
				case 903:
					JOptionPane.showMessageDialog(design, "입력한 테이블이 부적합 합니다.\n확인 후 다시 작성해주세요.");
					break;
				case 957:
					JOptionPane.showMessageDialog(design, "입력한 column중 중복 값이 있습니다.\n확인 후 다시 작성해주세요.");
					break;
				case 907:
					JOptionPane.showMessageDialog(design, "누락된 우괄호가 있습니다.\n확인 후 다시 작성해주세요.");
					break;
				case 922:
					JOptionPane.showMessageDialog(design, "누락되거나 부적합한 옵션이 존재합니다.\n확인 후 다시 작성해주세요.");
					break;
				case 1727:
					JOptionPane.showMessageDialog(design, "Size 입력하신 column속성중 size값은 숫자를 입력해야합니다.\n확인 후 다시 작성해주세요.");
					break;
				case 910:
					JOptionPane.showMessageDialog(design, "Size 입력범위를 확인해주세요.\n확인 후 다시 작성해주세요.");
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
