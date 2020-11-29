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

		//�� �� �޴��� ���õǸ� �޸��� ���� �ʱ�ȭ
		if(ae.getSource() == jm.getJmiNew()) {
			jm.getJtaMemo().setText("");
		}//end if
		
		//���⸦ ������ File Loadâ ���� 
		if(ae.getSource() == jm.getJmiOpen()) {
			FileDialog fd = new FileDialog(jm, "����", FileDialog.LOAD);
			fd.setVisible(true);
			if(fd.getFile() != null) {
				jm.setTitle("�ڹ�-�޸���[���� : "+fd.getFile()+"]");
			}//end if
		}//end if
		
		//������ ������ File Saveâ ����
		if(ae.getSource() == jm.getJmiSave()) {
			FileDialog fd = new FileDialog(jm, "����", FileDialog.SAVE);
			fd.setVisible(true);
			if(fd.getFile() != null) {
				jm.setTitle("�ڹ�-�޸���[���� : "+fd.getFile()+"]");
			}//end if
		}//end if
		
		//���̸����� ������ File Saveâ ����
		if(ae.getSource() == jm.getJmiNewSave()) {
			FileDialog fd = new FileDialog(jm, "�� �̸����� ����", FileDialog.SAVE);
			fd.setVisible(true);
			if(fd.getFile() != null) {
				jm.setTitle("�ڹ�-�޸���[���� : "+fd.getFile()+"]");
			}//end if
		}//end if
		
		
		//�ݱ⸦ ������ �޸��� ����
		if(ae.getSource() == jm.getJmiClose()) {
			jm.dispose();
		}//end if
		
		//�۲��� ������ MemoFont Dialog����
		if(ae.getSource() == jm.getJmiFont()) {
			openFont(jm);
		}//end if
		
		//�۲��� ������ MemoHelp Dialog����
		if(ae.getSource() == jm.getJmiHelp()) {
			openHelp(jm);
		}//end if
		
	}//actionPerformed

	//�۲� dialog����
	public void openFont(JavaMemo jm) {
		new JavaMemoFont(jm);
	}//openFont
	
	//���� dialog����
	public void openHelp(JavaMemo jm) {
		new JavaMemoInfo(jm);
	}//openFont
	
	@Override
	public void windowClosing(WindowEvent we) {
			jm.dispose();
	}//windowClosing

}//JavaMemoEvt
