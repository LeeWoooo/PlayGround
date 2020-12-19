package logAnalysis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class LogAnalysis_LoginEvt extends WindowAdapter implements ActionListener {

	private final String ID_ONE = "admin";
	private final String PASSWORD_ONE = "1234";
	private final String ID_TWO = "root";
	private final String PASSWORD_TWO = "1111";

	private LogAnalysis_Login lal;

	public LogAnalysis_LoginEvt(LogAnalysis_Login lal) {
		this.lal = lal;
	}// LogAnalysis_LoginEvt

	@Override
	public void windowOpened(WindowEvent we) {
		lal.getJtfId().requestFocus();
	}// windowOpened

	@Override
	public void windowClosing(WindowEvent we) {
		endOption();
	}// windowClosing

	@Override
	public void actionPerformed(ActionEvent ae) {

		String id = lal.getJtfId().getText();
		String password = String.valueOf(lal.getJpfPassword().getPassword());

		if (ae.getSource() == lal.getJbtLogin()) {
			if (ID_ONE.equals(id) && PASSWORD_ONE.equals(password)
					|| ID_TWO.equals(id) && PASSWORD_TWO.equals(password)) {
				JOptionPane.showMessageDialog(lal, "로그인에 성공하였습니다 :)", "환영", JOptionPane.DEFAULT_OPTION);
				openLogAnalysis_Analysis();
			} else {
				JOptionPane.showMessageDialog(lal, "아이디와 비밀번호를 확인해주세요.", "ERROR", JOptionPane.ERROR_MESSAGE);
				lal.getJtfId().requestFocus();
			} // end else
		} // end if

		if (ae.getSource() == lal.getJbtnclose()) {
			endOption();
		} // end if
	}// actionPerformed

	public void openLogAnalysis_Analysis() {
		new LogAnalysis_Analysis(lal);
	}// openLogAnalysis_Analysis
	
	public void endOption() {
		switch (JOptionPane.showConfirmDialog(lal, "정말 종료하시겠습니까?","종료",JOptionPane.YES_NO_OPTION)) {
		case JOptionPane.OK_OPTION:
			lal.dispose();
		}// end switch
	}// endOption

}// class
