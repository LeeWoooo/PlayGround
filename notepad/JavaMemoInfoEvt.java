package leeproject.notepad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JavaMemoInfoEvt extends WindowAdapter implements ActionListener{

	private JavaMemoInfo jmi;

	public JavaMemoInfoEvt(JavaMemoInfo jmi) {
		this.jmi = jmi;
	}//JavaMemoInfoEvt
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == jmi.getJbtnClose()) {
			jmi.dispose();
		}//end if
	}//actionPerformed

	@Override
	public void windowClosing(WindowEvent we) {
		jmi.dispose();
	}//windowClosing
	
	
	
	
}//JavaMemoInfoEvt
