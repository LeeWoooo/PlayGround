package testtest.copy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class InitialScreenEvt extends WindowAdapter implements ActionListener {

	private final String ID_ONE = "admin";
	private final String ID_TWO = "root";
	private final String PASSWORD_ONE = "1234";
	private final String PASSWORD_TWO = "1111";

	private InitialScreen is;

	public InitialScreenEvt(InitialScreen is) {
		this.is = is;
	}// InitialScreenEvt

	@Override
	public void actionPerformed(ActionEvent ae) {

		// �ݱ� ��ư�� ������ �� eventó��
		if (ae.getSource() == is.getJbtnClose()) {
			int flag = JOptionPane.showConfirmDialog(is, "�����Ͻðڽ��ϱ�?");
			switch (flag) {
			case JOptionPane.OK_OPTION:
				is.dispose();
			}// end switch
		} // end if

		// �α��� ��ư�� ������ �� eventó��
		if (ae.getSource() == is.getJbtnLogin()) {
			validation();
		} // end if
	}// actionPerformed

	@Override
	public void windowClosing(WindowEvent we) {
		int flag = JOptionPane.showConfirmDialog(is, "�����Ͻðڽ��ϱ�?");
		switch (flag) {
		case JOptionPane.OK_OPTION:
			is.dispose();
		}// end switch
	}// windowClosing

	public void validation() {
		// ����ڰ� �Է��� id �� ��й�ȣ
		String id = is.getJtfID().getText();
		String password = new String(is.getJpf().getPassword());

		// ��ȿ�� �˻�
		if ((ID_ONE.equals(id) && PASSWORD_ONE.equals(password))
				|| (ID_TWO.equals(id) && PASSWORD_TWO.equals(password))) {
			JOptionPane.showMessageDialog(is, "�α��� ����:) �������!");
			openlogAnalysis();
		} else {
			JOptionPane.showMessageDialog(is, "Id �� Password�� Ȯ�����ּ���", "���", JOptionPane.ERROR_MESSAGE);
			is.getJtfID().setText("");
			is.getJpf().setText("");
			is.getJtfID().requestFocus(); //��� â ������ id�Է�â�� ��Ŀ�� �ֱ�
		} // end else
	}// end validation

	// log �м� dialog ����
	public void openlogAnalysis() {
		new LogAnalysis(is);
	}// openlogAnalysis

}// InitialScreenEvt
