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

	List<String> logLineList; // file ���� �����͸� �� ������ ���� ����Ʈ

	List<String> resultCodeList; // ������ ����Ʈ
	List<String> urlList; // URL ����Ʈ
	List<String> browserList; // ������ ���� ����Ʈ
	List<String> dateTimeList; // ���� �ð� ����Ʈ

	public LogAnalysisData(String logFilePath) throws IOException {

		//���� �о� ���̱�
		this.file = new File(logFilePath);
		
		//���Ͽ� Stream�� �����Ͽ� file�� ������ �о���� �غ� �մϴ�.
		br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

		// file���� �����͸� �� �� ������ �о���̱�
		logLineList = new ArrayList<String>();
		String temp = "";
		while ((temp = br.readLine()) != null) {
			logLineList.add(temp);
		}// end while

		// Stream �ݱ�
		br.close();

		// file ���� �����͸� (1)������, (2)URL, (3)������ ����, (4)���� �ð� �� �κ����� ������ List�� ���
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
