package logAnalysis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class LogAnalysis_ViewEvt extends WindowAdapter implements ActionListener {

	private logAnalysis.LogAnalysis_View lav;
	private logAnalysis.LogAnalysis_AnalysisEvt laae;

	public LogAnalysis_ViewEvt(LogAnalysis_View lav, LogAnalysis_AnalysisEvt laae) {
		this.lav = lav;
		this.laae = laae;
	}// LogAnalysis_ViewEvt

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == lav.getJbtnClose()) {
			dialogClose();
		} // end if

		if (ae.getSource() == lav.getJtbnReport()) {
			try {
				createFile();
			} catch (IOException e) {
				JOptionPane.showInternalMessageDialog(lav, "dat���� ���� ���� ������ �߻��߽��ϴ�.\n ����� �ٽ� �õ����ּ���.", "ERROR", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}//end catch
		} // end if
	}//actionPerformed

	//���� ����
	public void createFile() throws IOException {
		String directoryPath = "c:/dev/report";

		File directory = new File(directoryPath);
		if (!directory.exists()) {
			directory.mkdirs();
		} // end if
		
		String datPath = new StringBuilder(directoryPath).append("/report_").append(laae.getFileName()).append("log(").append(nowTime()).append(").dat").toString();
		File datFile = new File(datPath);
		FileWriter fw = new FileWriter(datFile);
		
		if(datFile.exists()) { //FileNotFoundException ��ü
			fw.write(lav.getJtaAllResult().getText());
			fw.flush();
			fw.close();
		}//end if
		
		JOptionPane.showMessageDialog(lav,directoryPath+"��ġ�� ������ �����Ǿ����ϴ�.","���ϻ���",JOptionPane.DEFAULT_OPTION );
		
	}// createFile
	
	//���� �ɼ�
	public void dialogClose() {
		switch (JOptionPane.showConfirmDialog(lav, "Log�м�â�� �����Ͻðڽ��ϱ�?")) {
		case JOptionPane.OK_OPTION:
			lav.dispose();
		}// end switch
	}// dialogClose

	// ���� �ð��� ���ϴ� method
	public String nowTime() {
		String time = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��HH��mm��ss��");
		time = sdf.format(new Date());
		return time;
	}// String
}// LogAnalysis_ViewEvt
