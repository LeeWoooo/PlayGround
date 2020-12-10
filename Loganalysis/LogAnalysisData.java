package testtest.copy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LogAnalysisData {

	File file;
	BufferedReader br;

	List<String> logLineList; // file 안의 데이터를 줄 단위로 넣은 리스트

	List<String> resultCodeList; // 응답결과 리스트
	List<String> urlList; // URL 리스트
	List<String> browserList; // 브라우저 종류 리스트
	List<String> dateTimeList; // 일자 시간 리스트

	public LogAnalysisData(String logFilePath) throws IOException {

		//파일 읽어 들이기
		this.file = new File(logFilePath);
		
		//파일에 Stream을 연결하여 file의 내용을 읽어들일 준비를 합니다.
		br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

		// file안의 데이터를 한 줄 단위로 읽어들이기
		logLineList = new ArrayList<String>();
		String temp = "";
		while ((temp = br.readLine()) != null) {
			logLineList.add(temp);
		}// end while

		// Stream 닫기
		br.close();

		// file 안의 데이터를 (1)응답결과, (2)URL, (3)브라우저 종류, (4)일자 시간 네 부분으로 나누어 List에 담기
		resultCodeList = new ArrayList<String>();
		urlList = new ArrayList<String>();
		browserList = new ArrayList<String>();
		dateTimeList = new ArrayList<String>();

		for (int i = 0; i < logLineList.size(); i++) {
			StringTokenizer tokenizer = new StringTokenizer(logLineList.get(i), "[]");

			while (tokenizer.hasMoreTokens()) {
				resultCodeList.add(tokenizer.nextToken());
				urlList.add(tokenizer.nextToken());
				browserList.add(tokenizer.nextToken());
				dateTimeList.add(tokenizer.nextToken());
			}// end while
		}// end for

	}// LogAnalyzer

}// class
