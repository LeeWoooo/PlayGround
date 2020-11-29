package leeproject.notepad;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class JavaMemoEvt extends WindowAdapter implements ActionListener {

	private JavaMemo jm;
	
	public JavaMemoEvt(JavaMemo jm) {
		this.jm = jm;
	}//JavaMemoEvt
	
	@Override
	public void actionPerformed(ActionEvent ae) {

		//새 글 메뉴가 선택되면 메모장 영역 초기화
		if(ae.getSource() == jm.getJmiNew()) {
			jm.getJtaMemo().setText("");
		}//end if
		
		//열기를 누르면 File Load창 열기 
		if(ae.getSource() == jm.getJmiOpen()) {
			FileDialog fd = new FileDialog(jm, "열기", FileDialog.LOAD);
			fd.setVisible(true);
			if(fd.getFile() != null) {
				jm.setTitle("자바-메모장[열기 : "+fd.getFile()+"]");
			}//end if
		}//end if
		
		//저장을 누르면 File Save창 열기
		if(ae.getSource() == jm.getJmiSave()) {
			FileDialog fd = new FileDialog(jm, "저장", FileDialog.SAVE);
			fd.setVisible(true);
			if(fd.getFile() != null) {
				jm.setTitle("자바-메모장[저장 : "+fd.getFile()+"]");
			}//end if
		}//end if
		
		//새이름으로 누르면 File Save창 열기
		if(ae.getSource() == jm.getJmiNewSave()) {
			FileDialog fd = new FileDialog(jm, "새 이름으로 저장", FileDialog.SAVE);
			fd.setVisible(true);
			if(fd.getFile() != null) {
				jm.setTitle("자바-메모장[저장 : "+fd.getFile()+"]");
			}//end if
		}//end if
		
		
		//닫기를 누르면 메모장 종료
		if(ae.getSource() == jm.getJmiClose()) {
			jm.dispose();
		}//end if
		
		//글꼴을 누르면 MemoFont Dialog열기
		if(ae.getSource() == jm.getJmiFont()) {
			openFont(jm);
		}//end if
		
		//글꼴을 누르면 MemoHelp Dialog열기
		if(ae.getSource() == jm.getJmiHelp()) {
			openHelp(jm);
		}//end if
		
	}//actionPerformed

	//글꼴 dialog생성
	public void openFont(JavaMemo jm) {
		new JavaMemoFont(jm);
	}//openFont
	
	//도움말 dialog생성
	public void openHelp(JavaMemo jm) {
		new JavaMemoInfo(jm);
	}//openFont
	
	@Override
	public void windowClosing(WindowEvent we) {
			jm.dispose();
	}//windowClosing

}//JavaMemoEvt
