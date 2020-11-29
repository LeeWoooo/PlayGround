package leeproject.notepad;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class JavaMemoFontEvt extends WindowAdapter implements ActionListener,ListSelectionListener {

	private JavaMemoFont jmf;
	private JavaMemo jm;
	
	private String font;
	private int style;
	private int size;
	
	public JavaMemoFontEvt(JavaMemo jm, JavaMemoFont jmf) {
		this.jmf = jmf;
		this.jm = jm;
	}//JavaMemoFontEvt
	
	private boolean flag;
	@Override
	public void valueChanged(ListSelectionEvent le) {

		font = jmf.getPreviewFont();
		style = jmf.getPreviewStyle();
		size = jmf.getPreviewSize();
		
		if(le.getSource() == jmf.getJlFont()) {
			font = JavaMemo.FONT[jmf.getJlFont().getSelectedIndex()];
			jmf.getJtfFont().setText(jmf.getDlmFont().get(jmf.getJlFont().getSelectedIndex()));
			jmf.getJiblPreview().setFont(new Font(font,style,size));
		}//end if

		if(le.getSource() == jmf.getJlStyle()) {
			style = JavaMemo.STYLE[jmf.getJlStyle().getSelectedIndex()];
			jmf.getJtfStyle().setText(jmf.getDlmStyle().get(jmf.getJlStyle().getSelectedIndex()));
			jmf.getJiblPreview().setFont(new Font(font,style,size));
		}//end if
		
		if(le.getSource() == jmf.getJlSize()) {
			size = JavaMemo.SIZE[(int)(jmf.getJlSize().getSelectedIndex())];
			jmf.getJtfSize().setText(String.valueOf(jmf.getDlmSize().get(jmf.getJlSize().getSelectedIndex())));
			jmf.getJiblPreview().setFont(new Font(font,style,size));
		}//end if
			flag = !flag;
	}//valueChanged

	
	@Override
	public void actionPerformed(ActionEvent ae) {

		if(ae.getSource() == jmf.getJcbScript()) {
			String flag = jmf.getDjcbScript().getElementAt(jmf.getJcbScript().getSelectedIndex());
			if("한글".equals(flag)) {
				jmf.getJiblPreview().setText("AaBbCc가나다");
			}//end if
			if("영어".equals(flag)) {
				jmf.getJiblPreview().setText("AaBbCc");
			}//end if
		}//end if
		
		if(ae.getSource() == jmf.getJbtnClose()) {
			jmf.dispose();
		}//end if
		
		if(ae.getSource() == jmf.getJbtnApply()) {
			jm.getJtaMemo().setFont(new Font(font, style, size));
			jmf.dispose();
		}//end if
		
	}//actionPerformed

	@Override
	public void windowClosing(WindowEvent we) {
		jmf.dispose();
	}//windowClosing

	
}//JavaMemoFontEvt
