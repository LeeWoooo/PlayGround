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

		// 닫기 버튼이 눌렸을 때 event처리
		if (ae.getSource() == is.getJbtnClose()) {
			int flag = JOptionPane.showConfirmDialog(is, "종료하시겠습니까?");
			switch (flag) {
			case JOptionPane.OK_OPTION:
				is.dispose();
			}// end switch
		} // end if

		// 로그인 버튼이 눌렸을 때 event처리
		if (ae.getSource() == is.getJbtnLogin()) {
			validation();
		} // end if
	}// actionPerformed

	@Override
	public void windowClosing(WindowEvent we) {
		int flag = JOptionPane.showConfirmDialog(is, "종료하시겠습니까?");
		switch (flag) {
		case JOptionPane.OK_OPTION:
			is.dispose();
		}// end switch
	}// windowClosing

	public void validation() {
		// 사용자가 입력한 id 및 비밀번호
		String id = is.getJtfID().getText();
		String password = new String(is.getJpf().getPassword());

		// 유효성 검사
		if ((ID_ONE.equals(id) && PASSWORD_ONE.equals(password))
				|| (ID_TWO.equals(id) && PASSWORD_TWO.equals(password))) {
			JOptionPane.showMessageDialog(is, "로그인 성공:) 어서오세요!");
			openlogAnalysis();
		} else {
			JOptionPane.showMessageDialog(is, "Id 및 Password를 확인해주세요", "경고", JOptionPane.ERROR_MESSAGE);
			is.getJtfID().setText("");
			is.getJpf().setText("");
			is.getJtfID().requestFocus(); //경고 창 생성후 id입력창에 포커스 주기
		} // end else
	}// end validation

	// log 분석 dialog 열기
	public void openlogAnalysis() {
		new LogAnalysis(is);
	}// openlogAnalysis

}// InitialScreenEvt
