package testtest.copy;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class LogAnalysisEvt extends WindowAdapter implements ActionListener {

	// 응답 결과 constant로 선언
	private final String SUCCESS = "200";
	private final String NO_PAGES = "404";
	private final String UNUSERREQUEST = "403";

	private static final int HOUR_START_INDEX = 11;
	private static final int HOUR_END_INDEX = 13;

	private String[] strKey;
	private String strTemp = "";
	HashSet<String> hashset = new HashSet<>();

	// event와 관련된 component 선언
	private LogAnalysis las;
	private LogAnalysisData lad;

	// file생성에 관련된 변수
	private String logFilePath;
	private String fileDierctory;
	private String fileName;

	// 유효성 검사 flag
	private boolean flag;

	// 유저 입력값
	int userstart;
	int useend;

	// 생성자 작성 (HAS-A)
	public LogAnalysisEvt(LogAnalysis las) {
		this.las = las;
	}// LogAnalysisEvt

	// Action event 처리
	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == las.getJbtnOpen()) {
			FileDialog fd = new FileDialog(las, "Log File 열기", FileDialog.LOAD);
			fd.setVisible(true);

			// 파일의 경로를 저장
			logFilePath = fd.getDirectory() + fd.getFile();
			fileDierctory = fd.getDirectory();
			fileName = fd.getFile();

			las.getJtflogPath().setText(logFilePath); // 현재 불러온 파일 이름 저장
		} // end if

		if (ae.getSource() == las.getJbtnView()) {

			try {
				if (fileName != null) {
		
				this.lad = new LogAnalysisData(logFilePath); // 불러온 파일을 LogAnalyzer class로 data 정리 IOExcption을 던지고 있다.

				flag = true;

				userInput();

				if (flag) {
					// 파일이 존재한다면 -> FileNotFoundException을 대신한다.

						las.getJtaResult().setText(""); // jtextArea 초기화

						MostUsedKey(); // 혜림

						countbrowser(); // 우길

						executionError(); // 혜진

						requestTime(); // 혜진

						unusualRequest(); // 우리

						mostUsedKey(userstart, useend);// 우리 두번째 mostUsedKey 불릴 때는 사용자가 지정한 범위 에서의 key값 //우리

					} // end if
				
					} else {
						JOptionPane.showMessageDialog(las, "Log File을 열고 눌러주세요", "경고", JOptionPane.ERROR_MESSAGE);
					}
				} catch (IOException e) {
					e.printStackTrace();
				} // end catch
		} // end if

		// 파일로 저장 누르면 종료.
		if (ae.getSource() == las.getJbtnExprot()) {
			creatLog();
			JOptionPane.showMessageDialog(las, "Log가 열리 경로에 Report파일이 생성되었습니다.");
		} // end if

		// 닫기 버튼으로 창 닫기
		if (ae.getSource() == las.getJbtnClose()) {
			int flag = JOptionPane.showConfirmDialog(las, "종료하시겠습니까?");
			switch (flag) {
			case JOptionPane.OK_OPTION:
				las.dispose();
			}// end switch
		} // end if

	}// actionPerformed

	// x버튼 닫기
	@Override
	public void windowClosing(WindowEvent e) {
		las.dispose();
	}

	// 파일 생성
	public void creatLog() {

		String path = fileDierctory + "log분석결과(" + getTime() + ").bat";
		System.out.println(path);
		try {
			File report = new File(path);

			BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(report)));

			if (report.exists()) {

				String msg = las.getJtaResult().getText().replaceAll("\n", "\r\n");

				bfw.write(msg);

				bfw.flush();

				bfw.close();

			} // end if
		} catch (IOException ie) {
			ie.printStackTrace();
		} // end catch
	}// creatLog

//	 사용자 지정 범위 넣기 유효성 검사
	public void userInput() {
		try {

			int temp1 = Integer.parseInt(las.getJtfstartLine().getText());
			int input1 = 0;
			// 사용자 시작줄 유효성 검사
			if (temp1 > 0 && temp1 < lad.urlList.size() + 1) {
				input1 = temp1;
				userstart = temp1;
			} else {
				JOptionPane.showMessageDialog(las, "시작줄의 범위 안에서 입력 후 다시 시도하세요", "경고", JOptionPane.ERROR_MESSAGE);
				flag = false;
				las.getJtfstartLine().setText("");
			} // end else

			int temp2 = Integer.parseInt(las.getJtfendLine().getText());
			if (temp2 > input1 && temp2 < lad.urlList.size() + 1) {
				useend = temp2;
			} else {
				JOptionPane.showMessageDialog(las, "마지막줄의 범위 안에서 입력후 다시 시도하세요", "경고", JOptionPane.ERROR_MESSAGE);
				flag = false;
			} // end else

		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(las, "숫자를 입력후 다시 시도해주세요", "경고", JOptionPane.ERROR_MESSAGE);
			flag = false;
			las.getJtfendLine().setText("");
		}

	}// userInput

	// 현재 시간 얻기
	public String getTime() {

		long lTime = System.currentTimeMillis();
		Date date = new Date(lTime);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일hh시mm분");
		String strTime = sdf.format(date);
		// strTime : 생성 시간 strDate : 생성 날짜
		return strTime;
	}// getTime

	// 혜림씨 1번
	public void MostUsedKey() {

		strKey = new String[lad.logLineList.size()];

		// 중복값을 제거해 키의 종류 얻는 메소드
		getKeyValue();

		// 종류별 키의 갯수를 세는 메소드
		// 그 값으로 최다 사용 키를 구한다
		cntKeyValue();
	}// MoustUsedKey

	public void getKeyValue() {
		int nIndex = 0;
		int nKey = 0;
		String[] str = new String[lad.logLineList.size() + 1];
		for (int i = 0; i < lad.logLineList.size(); i++) {
			str[i] = lad.logLineList.get(i).toString();

			// 키의 위치를 nIndex에 대입
			nIndex = str[i].indexOf("key=");

			// key값이 없는 경우도 있으므로 key의 여부 확인
			if (nIndex != -1) {
				// "key="과 "&" 사이에 있는 키를 strKey에 대입
				strKey[nKey] = str[i].substring(nIndex + 4, str[i].indexOf("&"));

				// 중복되지 않은 고유 값을 얻기 위한 hashset
				strTemp = strKey[nKey];
				hashset.add(strTemp);
			} // end if

			nKey++;
		} // end for

	}// getKeyValue

	/**
	 * 종류별로 나눠진 키의 각 갯수를 센다
	 */
	public void cntKeyValue() {
		String[] strDuplType;
		int nType = 0;
		strTemp = hashset.toString();
		strTemp = strTemp.substring(1, strTemp.length() - 1);

		StringTokenizer stk = new StringTokenizer(strTemp, ", ", false);
		strDuplType = new String[stk.countTokens()];

		// 각 종류별 키의 값을 세기 위해 배열로 바꿔준다
		while (stk.hasMoreTokens()) {
			for (nType = 0; nType < hashset.size(); nType++) {
				strDuplType[nType] = stk.nextToken();
			} // end for
		} // end while

		// 각 키 종류가 가진 수를 센다
		int[] numDuplValue = new int[strDuplType.length];
		for (int j = 0; j < nType; j++) {
			int nCnt = 0;
			for (int i = 0; i < strKey.length; i++) {

				if (strDuplType[j].equals(strKey[i]) == true) {
					nCnt++;
				} // end if
			} // end for
			numDuplValue[j] = nCnt;
		} // end for

		// 가장 많이 쓰인 종류를 찾기 위한 메소드
		mostKeyValue(strDuplType, numDuplValue);
	}// end cntKeyValue

	public void mostKeyValue(String[] strKey, int[] nCnt) {
		int nTemp = 0;
		String strTemp = "";

		// 제일 큰 수를 구하기 위한 정렬
		for (int i = 0; i < nCnt.length; i++) {
			for (int j = 0; j < i; j++) {
				if (nCnt[j] > nCnt[j + 1]) {
					nTemp = nCnt[j];
					nCnt[j] = nCnt[j + 1];
					nCnt[j + 1] = nTemp;

					// 횟수(nCnt)의 순서가 바뀔 때, 키(strKey)의 순서도 바꿈
					strTemp = strKey[j];
					strKey[j] = strKey[j + 1];
					strKey[j + 1] = strTemp;
				} // end if
			} // end for
		} // end for

		String mostKey = strKey[nCnt.length - 1];
		int mostKeyCnt = nCnt[nCnt.length - 1];
		las.getJtaResult().append("1) 최다 사용 키 종류 및 횟수[ " + mostKey + " , " + mostKeyCnt + "회 ]\n\n");
	}// mostKeyValue

	// 우길 2번 과제
	public void countbrowser() {

		// Map는 key값이 중복이 되지 않기 때문에 log에서 key의 종류를 알아낼 수 있다.
		Map<String, Integer> browserMap = new HashMap<String, Integer>();

		for (String browser : lad.browserList) {
			// HashMap의 getOrDefault()는 key가 있으면 그 값을 반환하고 아니면 설정한 default값을 반환한다.
			browserMap.put(browser, browserMap.getOrDefault(browser, 0) + 1);
		} // end for

		las.getJtaResult().append("2) 브라우저별 접속횟수, 비율" + "\n");

		// Map의 값을을 얻어 jtextarea에 입력
		for (String browser : browserMap.keySet()) {
			String browserkinds = browser;
			int browserCnt = browserMap.get(browser);
			String ratio = String.format("%.1f", (browserMap.get(browser) / (double) lad.browserList.size()) * 100);
			las.getJtaResult().append(
					"- 브라우저 종류 [ " + browserkinds + " ] 접속횟수 [ " + browserCnt + " ] / 접속비율 [ " + ratio + "% ] " + "\n");

		} // end for
		las.getJtaResult().append("\n");

	}// countbrowser

	// 혜진씨 3번과제
	/**
	 * 3. 서비스를 성공적으로 수행한 횟수, 실패(404) 횟수<br>
	 * 주석 1번과 주석 2번중 선택<br>
	 * 주석 1번 : 우리씨가 만든 ArrayList를 String형으로 비교한것<br>
	 * 1번사용시 문제5번(우리씨가맡은)과 합치기 쉬울 수 있음<br>
	 * 주석 2번 : String형을 Integer형으로 변환하여 비교한 것<br>
	 * 2번사용시 비교하는 코드가 int형 상수로 선언하였기 때문에 가독성증가<br>
	 */
	public void executionError() {

		int numberSuccesses = 0;// 성공횟수
		int numberFailures = 0;// 실패횟수

		for (int i = 0; i < lad.resultCodeList.size(); i++) {

			if (SUCCESS.equals(lad.resultCodeList.get(i))) {
				numberSuccesses++;
			}
			if (NO_PAGES.equals(lad.resultCodeList.get(i))) {
				numberFailures++;
			}
		}
		String msg = "3) 접속에 성공한 횟수 [ " + numberSuccesses + "회 ] / 접속에 실패한 횟수 [ " + numberFailures + "회 ]\n\n";

		las.getJtaResult().append(msg);

	}// end executionError

	// 혜진씨 4번 과제
	public void requestTime() {

		List<Integer> mostRequestedTime = new ArrayList<Integer>();
		Map<Integer, Integer> timeArr = new HashMap<Integer, Integer>();
		String hour = "";

		for (int i = 0; i < lad.dateTimeList.size(); i++) {
			// 시간에 해당하는 인덱스 11~12 따라서 11,13으로 자른다
			hour = (String) lad.dateTimeList.get(i).substring(HOUR_START_INDEX, HOUR_END_INDEX);
			// 위에서 자른 시간을 Integer형 ArrayList에 담는다
			mostRequestedTime.add(Integer.parseInt(hour));
		} // end for

		for (int time : mostRequestedTime) {
			// getOrDefault : 해당 key값이 존재하면 key의 value를 반환하고 존재하지 않으면 기본값을 반환한다.
			timeArr.put(time, timeArr.getOrDefault(time, 0) + 1);
			// 처음 key의 값에는 1을 저장하고 같은 key값이 나오면 +1한다
		} // end for

		int hourRequestedTime = 0;
		int mostRequestedHour = 0;
		for (int key : timeArr.keySet()) {
			if (hourRequestedTime < timeArr.get(key)) {
				hourRequestedTime = timeArr.get(key);
				mostRequestedHour = key;
			} // end if
		} // end for
		String msg = "4) 요청이 가장 많은 시간 [ " + mostRequestedHour + " ]시\n\n";
		las.getJtaResult().append(msg);
	}// requestTime

	// 우리씨 5번 과제
	public void unusualRequest() {

		// 비정상적인 요청이 발생한 횟수 : count
		int count = 0;
		for (int i = 0; i < lad.resultCodeList.size(); i++) {
			if (UNUSERREQUEST.equals(lad.resultCodeList.get(i))) {
				count++;
			} // if
		} // for

		// 비정상적인 요청이 발생한 비율 : rate
		double rate = ((double) count / lad.resultCodeList.size()) * 100;

		String msg_1 = "비정상적인 요청 발생한 횟수 : " + count + "회";
		String msg_2 = String.format("비정상적인 요청 발생한 비율 : [%.2f", rate) + ("%]\n\n");
		las.getJtaResult().append("5) " + msg_1 + " / " + msg_2);
	}// unusualRequest

	// 우리씨 6번과제
	public void mostUsedKey(int startLine, int endLine) {// startLine : 범위 시작 줄, endLine : 범위 마지막 줄

		// Map (key : key의 이름, value : key의 사용된 횟수)
		Map<String, Integer> countOfUsedKey = new HashMap<String, Integer>();

		// key 뽑아내기
		for (int i = startLine - 1; i < endLine; i++) {
			String url = lad.urlList.get(i);

			Map<String, String> keyValueMap = extractQueryForURL(url);

			Set<String> keySet = keyValueMap.keySet();
			Iterator<String> iterator = keySet.iterator();

			while (iterator.hasNext()) {
				String key = iterator.next();
				String value = keyValueMap.get(key);

				if ("key".equals(key)) {
					if (countOfUsedKey.containsKey(value)) {// countOfUsedKey 안에 이미 들어가있다면
						// 해당하는 key의 value값을 +1
						countOfUsedKey.put(value, countOfUsedKey.get(value) + 1);
					} else {// countOfUsedKey 안에 없는 key라면
							// key 추가
						countOfUsedKey.put(value, 1);
					} // end else
				} // end id
			} // end while

		} // end for

		// Map의 key 모음
		Set<String> countKeySet = countOfUsedKey.keySet();
		// Set의 검색을 위한 반복자 Iterator
		Iterator<String> iterator = countKeySet.iterator();
		// 가장 많이 사용된 키 : maxKey, maxKey의 사용 횟수 : maxValue
		String maxKey = "";
		int maxValue = 0;
		while (iterator.hasNext()) {
			String key = iterator.next();
			int value = countOfUsedKey.get(key);

			if (value > maxValue) {
				maxKey = key;
				maxValue = value;
			} // end if

		} // end while
		String msg = "6) 입력하신 범위에서 가장 많이 사용한 key : [" + maxKey + "]  key 사용 횟수 : [" + countOfUsedKey.get(maxKey) + "회]";
		las.getJtaResult().append(msg);
	}// mostUsed

	// 우리씨 6번과제에 이용되는 method
	// 6번 문제.url을 입력하면 value값으로 key의 종류를 담은 keyValueMap 을 반환.
	public Map<String, String> extractQueryForURL(String url) {

		Map<String, String> keyValueMap = new HashMap<>();

		// urlList를 '?' 기준으로 자름
		// 결과 예시 : key=mongodb&query=sist
		int questionIndex = url.indexOf("?");
		String queryString = url.substring(questionIndex + 1);

		// '&' 기준으로 더 자름
		// 결과 예시 : key=mongodb
		String[] keyValues = queryString.split("&");

		// '=' 기준으로 더 자름
		// 결과 예시 : key = key, value = mongodb
		for (int j = 0; j < keyValues.length; j++) {
			String keyValue[] = keyValues[j].split("=");
			String key = keyValue[0];
			String value = keyValue[1];
			keyValueMap.put(key, value);
		} // end for

		return keyValueMap;
	}// end extractQueryForURL

}// LogAnalysisEvt
