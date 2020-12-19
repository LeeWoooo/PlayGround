package logAnalysis;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class LogAnalysis_AnalysisEvt extends WindowAdapter implements ActionListener {

	// 접속결과 비교 상수
	private final String ANNORMAL_REQUEST = "403";
	private final String SUCCESS_REQUEST = "200";
	private final String FAIL_REQUEST = "404";

	// 시간자를 때 사용하는 상수
	private final int HOUR_START_INDEX = 11;
	private final int HOUR_END_INDEX = 13;

	// list index
	private final int FUNCTION1 = 0;
	private final int FUNCTION2 = 1;
	private final int FUNCTION3 = 2;
	private final int FUNCTION4 = 3;
	private final int FUNCTION5 = 4;
	private final int FUNCTION6 = 5;

	private LogAnalysis_Analysis laa;

	// 현재 열려있는 로그파일명
	private String fileName;

	// 사용자가 입력한 라인 값.
	private int startLine;
	private int endLine;
	private boolean flag;

	// log의 한줄을 분할하여 넣을 map,list
	private HashMap<String, Integer> responeMap;
	private List<String> logList;
	private HashMap<String, Integer> browserMap;
	private List<String> timeList;
	private int cntLog;

	public LogAnalysis_AnalysisEvt(LogAnalysis_Analysis laa) {
		this.laa = laa;
	}// LogAnalysis_AnalysisEvt

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == laa.getJbtnOpen()) {
			try {
				fileopen();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(laa, "파일을 여는 도중 문제가 발생했습니다.\n잠시 후 다시 시도해주세요.", "ERROR",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} // end catch
		} // end if

		if (ae.getSource() == laa.getJbtnView()) {
			if (fileName != null) {
				try {
					userValidation();
				} catch (NumberFormatException npe) {
					JOptionPane.showMessageDialog(laa, "사용자 입력칸에 숫자를 입력 후 사용해주세요.", "ERROR", JOptionPane.ERROR_MESSAGE);
				} // end catch
				if (flag) {
					openLogAnalysis_view();
					flag = false;
				} // end if
			} else {
				JOptionPane.showMessageDialog(laa, "log파일을 열고 실행하여 주세요.", "ERROR", JOptionPane.ERROR_MESSAGE);
			} // end else
		} // end if

		if (ae.getSource() == laa.getJbtnClose()) {
			endOption();
		} // end if
	}// actionPerformed

	// 파일을 열기
	public void fileopen() throws IOException {
		FileDialog fd = new FileDialog(laa, "Log파일 열기");
		fd.setVisible(true);
		fileName = fd.getFile();

		if (fileName != null) { // 파일이 열렸고, FileNotFoundException을 대체
			if (fileName.endsWith("log")) { // 파일의 확장자가 log일때,확장자 유효성 검사

				String fileDirectory = fd.getDirectory();
				String path = new StringBuilder(fileDirectory).append(fileName).toString();

				File logFile = new File(path); // 파일을 생성한 후
				BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(logFile)));// 스트림 연결

				// 각각 데이터를 분할해서 넣을 map,list 선언
				responeMap = new HashMap<String, Integer>();
				logList = new ArrayList<String>();
				browserMap = new HashMap<String, Integer>();
				timeList = new ArrayList<String>();

				laa.getJtaLog().setText("");
				laa.getJtfPath().setText("");
				
				// log 파일에 모든 줄 읽기
				cntLog = 0; // 파일이 여러번 열릴 수도 있으니 초기화
				String oneLineLog = "";
				while ((oneLineLog = bf.readLine()) != null) {
					laa.getJtfPath().setText(path);
					laa.getJtaLog().append(oneLineLog + "\n"); // jtaLog에 각 log추가
					dataDivision(oneLineLog);
					cntLog++;
				} // end while

				bf.close(); // 스트림 닫기

				jlistMouseEvt(); // jList에 대한 mouse 이벤트 등록.
			} else {
				JOptionPane.showMessageDialog(laa, "확장자 log가 아닙니다.\n확인 후 다시 시도해주세요", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			} // end else
		} // end if

	}// file open

	// file을 읽어들일 때 각각 맞는 map,list에 데이터를 분할
	public void dataDivision(String oneLineLog) { // 토큰을 가져오는 도중 NoSuchElementException이 발생할 수도 있다
		StringTokenizer stk = new StringTokenizer(oneLineLog, "[]", false);
		String respone = ""; // NoSuchElementException를 방지하기 위해 변수 선언
		String browser = ""; // NoSuchElementException를 방지하기 위해 변수 선언
		while (stk.hasMoreTokens()) {
			respone = stk.nextToken();
			responeMap.put(respone, responeMap.getOrDefault(respone, 0) + 1);
			logList.add(stk.nextToken());
			browser = stk.nextToken();
			browserMap.put(browser, browserMap.getOrDefault(browser, 0) + 1);
			timeList.add(stk.nextToken());
		} // end while

	}// dataDivision

	// jList에 대한 mouse이벤트등록
	public void jlistMouseEvt() {
		laa.getJlEx().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent me) {
				switch (me.getButton()) {
				case MouseEvent.BUTTON1:
					switch (laa.getJlEx().getSelectedIndex()) {
					case FUNCTION1:
						laa.getJtaresult().setText("");
						laa.getJtaresult().append("\n1.가장 많이 사용된 key\n");
						laa.getJtaresult().append(key(0, cntLog));
						break;
					case FUNCTION2:
						laa.getJtaresult().setText("");
						laa.getJtaresult().append(browser());
						break;
					case FUNCTION3:
						laa.getJtaresult().setText("");
						laa.getJtaresult().append(success_fail());
						break;
					case FUNCTION4:
						laa.getJtaresult().setText("");
						laa.getJtaresult().append(time());
						break;
					case FUNCTION5:
						laa.getJtaresult().setText("");
						laa.getJtaresult().append(annormal_Request());
						break;
					case FUNCTION6:
						laa.getJtaresult().setText("");
						try {
							userValidation();
							if (flag) {
								laa.getJtaresult().append("\n6.입력된 범위에서 가장 많이 사용된 key\n");
								laa.getJtaresult().append(key(startLine, endLine));
								flag = false;
							} // end if
						} catch (NumberFormatException npe) {
							JOptionPane.showMessageDialog(laa, "사용자 입력칸에 숫자를 입력 후 사용해주세요.", "ERROR",
									JOptionPane.ERROR_MESSAGE);
						} // end catch
					}// end switch
				}// end switch
			}// mouseClicked
		});
	}// jlistMouseEvt

	// 비정상요청 횟수 및 비율 구하기
	public String annormal_Request() {
		int cnt = responeMap.get(ANNORMAL_REQUEST);
		StringBuilder sb = new StringBuilder("\n5. 비정상요청\n발생횟수 [ ").append(cnt).append("회]\n발생비율 [").append(ratio(cnt))
				.append("%]\n");
		return sb.toString();
	}// annormalRequest

	// 성공, 실패 요청 횟수 구하기
	public String success_fail() {
		int success_Cnt = responeMap.get(SUCCESS_REQUEST);
		int fail_Cnt = responeMap.get(FAIL_REQUEST);
		StringBuilder sb = new StringBuilder("\n3. 성공,실패\n성공횟수 [").append(success_Cnt).append("회]\n실패횟수 [")
				.append(fail_Cnt).append("회]\n");
		return sb.toString();
	}// success_fail

	// browser별 횟수 및 비율구하기.
	public String browser() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n2.browser 분석\n");
		for (String browser : browserMap.keySet()) {
			sb.append("[").append(browser).append("] 접속횟수 [").append(browserMap.get(browser)).append("회] 비율 [")
					.append(ratio(browserMap.get(browser))).append("%]\n");
		} // end for
		return sb.toString();
	}// browser

	// 접속이 가장 많은 시간 구하기
	public String time() {
		HashMap<String, Integer> timeMap = new HashMap<String, Integer>();
		String temp = "";
		for (String time : timeList) {
			temp = time.substring(HOUR_START_INDEX, HOUR_END_INDEX);
			timeMap.put(temp, timeMap.getOrDefault(temp, 0) + 1);
		} // end for

		int cnt = 0;
		String mostTime = "";
		for (String time : timeMap.keySet()) {
			if (timeMap.get(time) > cnt) {
				cnt = timeMap.get(time);
				mostTime = time;
			} // end if
		} // end for
		StringBuilder sb = new StringBuilder("\n4. 가장 요청이 많은 시간 [").append(mostTime).append("시]\n");
		return sb.toString();
	}// time

	// 가장 많이 사용된 key 횟수 및 비율
	public String key(int start, int end) { // 사용자가 값을 입력했을 때도 재사용 하기 위해 매개변수 사용
		HashMap<String, Integer> keyMap = new HashMap<String, Integer>();
		String temp = "";
		int keyStartIndex = 0;
		int keyEndIndex = 0;

		for (int i = start; i < end; i++) {
			keyStartIndex = logList.get(i).indexOf("key=") + 4;
			keyEndIndex = logList.get(i).indexOf("&query", keyStartIndex);
			if (keyStartIndex != -1 && keyEndIndex != -1) {
				temp = logList.get(i).substring(keyStartIndex, keyEndIndex);
				keyMap.put(temp, keyMap.getOrDefault(temp, 0) + 1);
			} // end if
		} // end for

		int cnt = 0;
		String mostKey = "";
		for (String key : keyMap.keySet()) {
			if (keyMap.get(key) > cnt) {
				cnt = keyMap.get(key);
				mostKey = key;
			} // end if
		} // end for

		StringBuilder sb = new StringBuilder("[ ").append(mostKey).append(" ] 사용횟수 [ ").append(cnt).append("회 ]\n");
		return sb.toString();
	}// key

	// 사용자 입력한 값 유효성 검사
	public void userValidation() throws NullPointerException {
		int inputstart = Integer.parseInt(laa.getJtfStrat().getText());// 입력값이 없을때는 ""가 들어있다.
		int inputend = Integer.parseInt(laa.getJtfEnd().getText());// 입력값이 없을때는 ""가 들어있다.
		if (inputstart <= inputend) {
			if (inputstart > 0 && inputend < cntLog + 1) {
				startLine = inputstart - 1;
				endLine = inputend;
				flag = true;
			} else {
				JOptionPane.showMessageDialog(laa, "입력값의 범위를 확인 후 다시 시도해주세요.", "ERROR", JOptionPane.ERROR_MESSAGE);
			} // end else
		} else {
			JOptionPane.showMessageDialog(laa, "StartLine이 EndLine보다 클 수 없습니다.\n확인 후 다시입력해주세요.", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		} // end else
	}// validation

	// 비율을 구하는 method
	public String ratio(int cnt) {
		String ratio = "";
		ratio = String.format("%.2f", (cnt / (double) cntLog) * 100);
		return ratio;
	}// ratio

	// x버튼이 눌렸을 때 이벤트 처리
	@Override
	public void windowClosing(WindowEvent wce) {
		endOption();
	}// windowClosing

	// close나 x버튼이 눌렸을때의 종료이벤트
	public void endOption() {
		switch (JOptionPane.showConfirmDialog(laa, "Log분석창을 닫으시겠습니까?", "종료", JOptionPane.YES_NO_OPTION)) {
		case JOptionPane.OK_OPTION:
			laa.dispose();
		}// end switch
	}// endOption

	public void openLogAnalysis_view() {
		LogAnalysis_View lav = new LogAnalysis_View(laa, this);
		lav.getJtaAllResult().append("\n1.가장 많이 사용된 key\n");
		lav.getJtaAllResult().append(key(0, cntLog));
		lav.getJtaAllResult().append(browser());
		lav.getJtaAllResult().append(success_fail());
		lav.getJtaAllResult().append(time());
		lav.getJtaAllResult().append(annormal_Request());
		StringBuilder sb = new StringBuilder("입력한 범위 [").append(startLine+1).append("~").append(endLine).append("]\n");
		lav.getJtaAllResult().append("\n6.입력된 범위에서 가장 많이 사용된 key\n");
		lav.getJtaAllResult().append(sb.toString());
		lav.getJtaAllResult().append(key(startLine, endLine));
	}// openLogAnalysis_Analysis

	public String getFileName() {
		return fileName;
	}//getFileName

}// LogAnalysis_AnalysisEvt
