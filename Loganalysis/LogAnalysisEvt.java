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

	// ���� ��� constant�� ����
	private final String SUCCESS = "200";
	private final String NO_PAGES = "404";
	private final String UNUSERREQUEST = "403";

	private static final int HOUR_START_INDEX = 11;
	private static final int HOUR_END_INDEX = 13;

	private String[] strKey;
	private String strTemp = "";
	HashSet<String> hashset = new HashSet<>();

	// event�� ���õ� component ����
	private LogAnalysis las;
	private LogAnalysisData lad;

	// file������ ���õ� ����
	private String logFilePath;
	private String fileDierctory;
	private String fileName;

	// ��ȿ�� �˻� flag
	private boolean flag;

	// ���� �Է°�
	int userstart;
	int useend;

	// ������ �ۼ� (HAS-A)
	public LogAnalysisEvt(LogAnalysis las) {
		this.las = las;
	}// LogAnalysisEvt

	// Action event ó��
	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == las.getJbtnOpen()) {
			FileDialog fd = new FileDialog(las, "Log File ����", FileDialog.LOAD);
			fd.setVisible(true);

			// ������ ��θ� ����
			logFilePath = fd.getDirectory() + fd.getFile();
			fileDierctory = fd.getDirectory();
			fileName = fd.getFile();

			las.getJtflogPath().setText(logFilePath); // ���� �ҷ��� ���� �̸� ����
		} // end if

		if (ae.getSource() == las.getJbtnView()) {

			try {
				if (fileName != null) {
		
				this.lad = new LogAnalysisData(logFilePath); // �ҷ��� ������ LogAnalyzer class�� data ���� IOExcption�� ������ �ִ�.

				flag = true;

				userInput();

				if (flag) {
					// ������ �����Ѵٸ� -> FileNotFoundException�� ����Ѵ�.

						las.getJtaResult().setText(""); // jtextArea �ʱ�ȭ

						MostUsedKey(); // ����

						countbrowser(); // ���

						executionError(); // ����

						requestTime(); // ����

						unusualRequest(); // �츮

						mostUsedKey(userstart, useend);// �츮 �ι�° mostUsedKey �Ҹ� ���� ����ڰ� ������ ���� ������ key�� //�츮

					} // end if
				
					} else {
						JOptionPane.showMessageDialog(las, "Log File�� ���� �����ּ���", "���", JOptionPane.ERROR_MESSAGE);
					}
				} catch (IOException e) {
					e.printStackTrace();
				} // end catch
		} // end if

		// ���Ϸ� ���� ������ ����.
		if (ae.getSource() == las.getJbtnExprot()) {
			creatLog();
			JOptionPane.showMessageDialog(las, "Log�� ���� ��ο� Report������ �����Ǿ����ϴ�.");
		} // end if

		// �ݱ� ��ư���� â �ݱ�
		if (ae.getSource() == las.getJbtnClose()) {
			int flag = JOptionPane.showConfirmDialog(las, "�����Ͻðڽ��ϱ�?");
			switch (flag) {
			case JOptionPane.OK_OPTION:
				las.dispose();
			}// end switch
		} // end if

	}// actionPerformed

	// x��ư �ݱ�
	@Override
	public void windowClosing(WindowEvent e) {
		las.dispose();
	}

	// ���� ����
	public void creatLog() {

		String path = fileDierctory + "log�м����(" + getTime() + ").bat";
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

//	 ����� ���� ���� �ֱ� ��ȿ�� �˻�
	public void userInput() {
		try {

			int temp1 = Integer.parseInt(las.getJtfstartLine().getText());
			int input1 = 0;
			// ����� ������ ��ȿ�� �˻�
			if (temp1 > 0 && temp1 < lad.urlList.size() + 1) {
				input1 = temp1;
				userstart = temp1;
			} else {
				JOptionPane.showMessageDialog(las, "�������� ���� �ȿ��� �Է� �� �ٽ� �õ��ϼ���", "���", JOptionPane.ERROR_MESSAGE);
				flag = false;
				las.getJtfstartLine().setText("");
			} // end else

			int temp2 = Integer.parseInt(las.getJtfendLine().getText());
			if (temp2 > input1 && temp2 < lad.urlList.size() + 1) {
				useend = temp2;
			} else {
				JOptionPane.showMessageDialog(las, "���������� ���� �ȿ��� �Է��� �ٽ� �õ��ϼ���", "���", JOptionPane.ERROR_MESSAGE);
				flag = false;
			} // end else

		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(las, "���ڸ� �Է��� �ٽ� �õ����ּ���", "���", JOptionPane.ERROR_MESSAGE);
			flag = false;
			las.getJtfendLine().setText("");
		}

	}// userInput

	// ���� �ð� ���
	public String getTime() {

		long lTime = System.currentTimeMillis();
		Date date = new Date(lTime);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��hh��mm��");
		String strTime = sdf.format(date);
		// strTime : ���� �ð� strDate : ���� ��¥
		return strTime;
	}// getTime

	// ������ 1��
	public void MostUsedKey() {

		strKey = new String[lad.logLineList.size()];

		// �ߺ����� ������ Ű�� ���� ��� �޼ҵ�
		getKeyValue();

		// ������ Ű�� ������ ���� �޼ҵ�
		// �� ������ �ִ� ��� Ű�� ���Ѵ�
		cntKeyValue();
	}// MoustUsedKey

	public void getKeyValue() {
		int nIndex = 0;
		int nKey = 0;
		String[] str = new String[lad.logLineList.size() + 1];
		for (int i = 0; i < lad.logLineList.size(); i++) {
			str[i] = lad.logLineList.get(i).toString();

			// Ű�� ��ġ�� nIndex�� ����
			nIndex = str[i].indexOf("key=");

			// key���� ���� ��쵵 �����Ƿ� key�� ���� Ȯ��
			if (nIndex != -1) {
				// "key="�� "&" ���̿� �ִ� Ű�� strKey�� ����
				strKey[nKey] = str[i].substring(nIndex + 4, str[i].indexOf("&"));

				// �ߺ����� ���� ���� ���� ��� ���� hashset
				strTemp = strKey[nKey];
				hashset.add(strTemp);
			} // end if

			nKey++;
		} // end for

	}// getKeyValue

	/**
	 * �������� ������ Ű�� �� ������ ����
	 */
	public void cntKeyValue() {
		String[] strDuplType;
		int nType = 0;
		strTemp = hashset.toString();
		strTemp = strTemp.substring(1, strTemp.length() - 1);

		StringTokenizer stk = new StringTokenizer(strTemp, ", ", false);
		strDuplType = new String[stk.countTokens()];

		// �� ������ Ű�� ���� ���� ���� �迭�� �ٲ��ش�
		while (stk.hasMoreTokens()) {
			for (nType = 0; nType < hashset.size(); nType++) {
				strDuplType[nType] = stk.nextToken();
			} // end for
		} // end while

		// �� Ű ������ ���� ���� ����
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

		// ���� ���� ���� ������ ã�� ���� �޼ҵ�
		mostKeyValue(strDuplType, numDuplValue);
	}// end cntKeyValue

	public void mostKeyValue(String[] strKey, int[] nCnt) {
		int nTemp = 0;
		String strTemp = "";

		// ���� ū ���� ���ϱ� ���� ����
		for (int i = 0; i < nCnt.length; i++) {
			for (int j = 0; j < i; j++) {
				if (nCnt[j] > nCnt[j + 1]) {
					nTemp = nCnt[j];
					nCnt[j] = nCnt[j + 1];
					nCnt[j + 1] = nTemp;

					// Ƚ��(nCnt)�� ������ �ٲ� ��, Ű(strKey)�� ������ �ٲ�
					strTemp = strKey[j];
					strKey[j] = strKey[j + 1];
					strKey[j + 1] = strTemp;
				} // end if
			} // end for
		} // end for

		String mostKey = strKey[nCnt.length - 1];
		int mostKeyCnt = nCnt[nCnt.length - 1];
		las.getJtaResult().append("1) �ִ� ��� Ű ���� �� Ƚ��[ " + mostKey + " , " + mostKeyCnt + "ȸ ]\n\n");
	}// mostKeyValue

	// ��� 2�� ����
	public void countbrowser() {

		// Map�� key���� �ߺ��� ���� �ʱ� ������ log���� key�� ������ �˾Ƴ� �� �ִ�.
		Map<String, Integer> browserMap = new HashMap<String, Integer>();

		for (String browser : lad.browserList) {
			// HashMap�� getOrDefault()�� key�� ������ �� ���� ��ȯ�ϰ� �ƴϸ� ������ default���� ��ȯ�Ѵ�.
			browserMap.put(browser, browserMap.getOrDefault(browser, 0) + 1);
		} // end for

		las.getJtaResult().append("2) �������� ����Ƚ��, ����" + "\n");

		// Map�� ������ ��� jtextarea�� �Է�
		for (String browser : browserMap.keySet()) {
			String browserkinds = browser;
			int browserCnt = browserMap.get(browser);
			String ratio = String.format("%.1f", (browserMap.get(browser) / (double) lad.browserList.size()) * 100);
			las.getJtaResult().append(
					"- ������ ���� [ " + browserkinds + " ] ����Ƚ�� [ " + browserCnt + " ] / ���Ӻ��� [ " + ratio + "% ] " + "\n");

		} // end for
		las.getJtaResult().append("\n");

	}// countbrowser

	// ������ 3������
	/**
	 * 3. ���񽺸� ���������� ������ Ƚ��, ����(404) Ƚ��<br>
	 * �ּ� 1���� �ּ� 2���� ����<br>
	 * �ּ� 1�� : �츮���� ���� ArrayList�� String������ ���Ѱ�<br>
	 * 1������ ����5��(�츮��������)�� ��ġ�� ���� �� ����<br>
	 * �ּ� 2�� : String���� Integer������ ��ȯ�Ͽ� ���� ��<br>
	 * 2������ ���ϴ� �ڵ尡 int�� ����� �����Ͽ��� ������ ����������<br>
	 */
	public void executionError() {

		int numberSuccesses = 0;// ����Ƚ��
		int numberFailures = 0;// ����Ƚ��

		for (int i = 0; i < lad.resultCodeList.size(); i++) {

			if (SUCCESS.equals(lad.resultCodeList.get(i))) {
				numberSuccesses++;
			}
			if (NO_PAGES.equals(lad.resultCodeList.get(i))) {
				numberFailures++;
			}
		}
		String msg = "3) ���ӿ� ������ Ƚ�� [ " + numberSuccesses + "ȸ ] / ���ӿ� ������ Ƚ�� [ " + numberFailures + "ȸ ]\n\n";

		las.getJtaResult().append(msg);

	}// end executionError

	// ������ 4�� ����
	public void requestTime() {

		List<Integer> mostRequestedTime = new ArrayList<Integer>();
		Map<Integer, Integer> timeArr = new HashMap<Integer, Integer>();
		String hour = "";

		for (int i = 0; i < lad.dateTimeList.size(); i++) {
			// �ð��� �ش��ϴ� �ε��� 11~12 ���� 11,13���� �ڸ���
			hour = (String) lad.dateTimeList.get(i).substring(HOUR_START_INDEX, HOUR_END_INDEX);
			// ������ �ڸ� �ð��� Integer�� ArrayList�� ��´�
			mostRequestedTime.add(Integer.parseInt(hour));
		} // end for

		for (int time : mostRequestedTime) {
			// getOrDefault : �ش� key���� �����ϸ� key�� value�� ��ȯ�ϰ� �������� ������ �⺻���� ��ȯ�Ѵ�.
			timeArr.put(time, timeArr.getOrDefault(time, 0) + 1);
			// ó�� key�� ������ 1�� �����ϰ� ���� key���� ������ +1�Ѵ�
		} // end for

		int hourRequestedTime = 0;
		int mostRequestedHour = 0;
		for (int key : timeArr.keySet()) {
			if (hourRequestedTime < timeArr.get(key)) {
				hourRequestedTime = timeArr.get(key);
				mostRequestedHour = key;
			} // end if
		} // end for
		String msg = "4) ��û�� ���� ���� �ð� [ " + mostRequestedHour + " ]��\n\n";
		las.getJtaResult().append(msg);
	}// requestTime

	// �츮�� 5�� ����
	public void unusualRequest() {

		// ���������� ��û�� �߻��� Ƚ�� : count
		int count = 0;
		for (int i = 0; i < lad.resultCodeList.size(); i++) {
			if (UNUSERREQUEST.equals(lad.resultCodeList.get(i))) {
				count++;
			} // if
		} // for

		// ���������� ��û�� �߻��� ���� : rate
		double rate = ((double) count / lad.resultCodeList.size()) * 100;

		String msg_1 = "���������� ��û �߻��� Ƚ�� : " + count + "ȸ";
		String msg_2 = String.format("���������� ��û �߻��� ���� : [%.2f", rate) + ("%]\n\n");
		las.getJtaResult().append("5) " + msg_1 + " / " + msg_2);
	}// unusualRequest

	// �츮�� 6������
	public void mostUsedKey(int startLine, int endLine) {// startLine : ���� ���� ��, endLine : ���� ������ ��

		// Map (key : key�� �̸�, value : key�� ���� Ƚ��)
		Map<String, Integer> countOfUsedKey = new HashMap<String, Integer>();

		// key �̾Ƴ���
		for (int i = startLine - 1; i < endLine; i++) {
			String url = lad.urlList.get(i);

			Map<String, String> keyValueMap = extractQueryForURL(url);

			Set<String> keySet = keyValueMap.keySet();
			Iterator<String> iterator = keySet.iterator();

			while (iterator.hasNext()) {
				String key = iterator.next();
				String value = keyValueMap.get(key);

				if ("key".equals(key)) {
					if (countOfUsedKey.containsKey(value)) {// countOfUsedKey �ȿ� �̹� ���ִٸ�
						// �ش��ϴ� key�� value���� +1
						countOfUsedKey.put(value, countOfUsedKey.get(value) + 1);
					} else {// countOfUsedKey �ȿ� ���� key���
							// key �߰�
						countOfUsedKey.put(value, 1);
					} // end else
				} // end id
			} // end while

		} // end for

		// Map�� key ����
		Set<String> countKeySet = countOfUsedKey.keySet();
		// Set�� �˻��� ���� �ݺ��� Iterator
		Iterator<String> iterator = countKeySet.iterator();
		// ���� ���� ���� Ű : maxKey, maxKey�� ��� Ƚ�� : maxValue
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
		String msg = "6) �Է��Ͻ� �������� ���� ���� ����� key : [" + maxKey + "]  key ��� Ƚ�� : [" + countOfUsedKey.get(maxKey) + "ȸ]";
		las.getJtaResult().append(msg);
	}// mostUsed

	// �츮�� 6�������� �̿�Ǵ� method
	// 6�� ����.url�� �Է��ϸ� value������ key�� ������ ���� keyValueMap �� ��ȯ.
	public Map<String, String> extractQueryForURL(String url) {

		Map<String, String> keyValueMap = new HashMap<>();

		// urlList�� '?' �������� �ڸ�
		// ��� ���� : key=mongodb&query=sist
		int questionIndex = url.indexOf("?");
		String queryString = url.substring(questionIndex + 1);

		// '&' �������� �� �ڸ�
		// ��� ���� : key=mongodb
		String[] keyValues = queryString.split("&");

		// '=' �������� �� �ڸ�
		// ��� ���� : key = key, value = mongodb
		for (int j = 0; j < keyValues.length; j++) {
			String keyValue[] = keyValues[j].split("=");
			String key = keyValue[0];
			String value = keyValue[1];
			keyValueMap.put(key, value);
		} // end for

		return keyValueMap;
	}// end extractQueryForURL

}// LogAnalysisEvt
