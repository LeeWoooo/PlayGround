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
				JOptionPane.showInternalMessageDialog(lav, "dat파일 생성 도중 오류가 발생했습니다.\n 잠시후 다시 시도해주세요.", "ERROR", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}//end catch
		} // end if
	}//actionPerformed

	//파일 생성
	public void createFile() throws IOException {
		String directoryPath = "c:/dev/report";

		File directory = new File(directoryPath);
		if (!directory.exists()) {
			directory.mkdirs();
		} // end if
		
		String datPath = new StringBuilder(directoryPath).append("/report_").append(laae.getFileName()).append("log(").append(nowTime()).append(").dat").toString();
		File datFile = new File(datPath);
		FileWriter fw = new FileWriter(datFile);
		
		if(datFile.exists()) { //FileNotFoundException 대체
			fw.write(lav.getJtaAllResult().getText());
			fw.flush();
			fw.close();
		}//end if
		
		JOptionPane.showMessageDialog(lav,directoryPath+"위치에 파일이 생성되었습니다.","파일생성",JOptionPane.DEFAULT_OPTION );
		
	}// createFile
	
	//종료 옵션
	public void dialogClose() {
		switch (JOptionPane.showConfirmDialog(lav, "Log분석창을 종료하시겠습니까?")) {
		case JOptionPane.OK_OPTION:
			lav.dispose();
		}// end switch
	}// dialogClose

	// 현재 시간을 구하는 method
	public String nowTime() {
		String time = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일HH시mm분ss초");
		time = sdf.format(new Date());
		return time;
	}// String
}// LogAnalysis_ViewEvt
