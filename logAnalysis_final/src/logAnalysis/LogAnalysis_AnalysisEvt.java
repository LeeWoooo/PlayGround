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

	// ���Ӱ�� �� ���
	private final String ANNORMAL_REQUEST = "403";
	private final String SUCCESS_REQUEST = "200";
	private final String FAIL_REQUEST = "404";

	// �ð��ڸ� �� ����ϴ� ���
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

	// ���� �����ִ� �α����ϸ�
	private String fileName;

	// ����ڰ� �Է��� ���� ��.
	private int startLine;
	private int endLine;
	private boolean flag;

	// log�� ������ �����Ͽ� ���� map,list
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
				JOptionPane.showMessageDialog(laa, "������ ���� ���� ������ �߻��߽��ϴ�.\n��� �� �ٽ� �õ����ּ���.", "ERROR",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} // end catch
		} // end if

		if (ae.getSource() == laa.getJbtnView()) {
			if (fileName != null) {
				try {
					userValidation();
				} catch (NumberFormatException npe) {
					JOptionPane.showMessageDialog(laa, "����� �Է�ĭ�� ���ڸ� �Է� �� ������ּ���.", "ERROR", JOptionPane.ERROR_MESSAGE);
				} // end catch
				if (flag) {
					openLogAnalysis_view();
					flag = false;
				} // end if
			} else {
				JOptionPane.showMessageDialog(laa, "log������ ���� �����Ͽ� �ּ���.", "ERROR", JOptionPane.ERROR_MESSAGE);
			} // end else
		} // end if

		if (ae.getSource() == laa.getJbtnClose()) {
			endOption();
		} // end if
	}// actionPerformed

	// ������ ����
	public void fileopen() throws IOException {
		FileDialog fd = new FileDialog(laa, "Log���� ����");
		fd.setVisible(true);
		fileName = fd.getFile();

		if (fileName != null) { // ������ ���Ȱ�, FileNotFoundException�� ��ü
			if (fileName.endsWith("log")) { // ������ Ȯ���ڰ� log�϶�,Ȯ���� ��ȿ�� �˻�

				String fileDirectory = fd.getDirectory();
				String path = new StringBuilder(fileDirectory).append(fileName).toString();

				File logFile = new File(path); // ������ ������ ��
				BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(logFile)));// ��Ʈ�� ����

				// ���� �����͸� �����ؼ� ���� map,list ����
				responeMap = new HashMap<String, Integer>();
				logList = new ArrayList<String>();
				browserMap = new HashMap<String, Integer>();
				timeList = new ArrayList<String>();

				laa.getJtaLog().setText("");
				laa.getJtfPath().setText("");
				
				// log ���Ͽ� ��� �� �б�
				cntLog = 0; // ������ ������ ���� ���� ������ �ʱ�ȭ
				String oneLineLog = "";
				while ((oneLineLog = bf.readLine()) != null) {
					laa.getJtfPath().setText(path);
					laa.getJtaLog().append(oneLineLog + "\n"); // jtaLog�� �� log�߰�
					dataDivision(oneLineLog);
					cntLog++;
				} // end while

				bf.close(); // ��Ʈ�� �ݱ�

				jlistMouseEvt(); // jList�� ���� mouse �̺�Ʈ ���.
			} else {
				JOptionPane.showMessageDialog(laa, "Ȯ���� log�� �ƴմϴ�.\nȮ�� �� �ٽ� �õ����ּ���", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			} // end else
		} // end if

	}// file open

	// file�� �о���� �� ���� �´� map,list�� �����͸� ����
	public void dataDivision(String oneLineLog) { // ��ū�� �������� ���� NoSuchElementException�� �߻��� ���� �ִ�
		StringTokenizer stk = new StringTokenizer(oneLineLog, "[]", false);
		String respone = ""; // NoSuchElementException�� �����ϱ� ���� ���� ����
		String browser = ""; // NoSuchElementException�� �����ϱ� ���� ���� ����
		while (stk.hasMoreTokens()) {
			respone = stk.nextToken();
			responeMap.put(respone, responeMap.getOrDefault(respone, 0) + 1);
			logList.add(stk.nextToken());
			browser = stk.nextToken();
			browserMap.put(browser, browserMap.getOrDefault(browser, 0) + 1);
			timeList.add(stk.nextToken());
		} // end while

	}// dataDivision

	// jList�� ���� mouse�̺�Ʈ���
	public void jlistMouseEvt() {
		laa.getJlEx().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent me) {
				switch (me.getButton()) {
				case MouseEvent.BUTTON1:
					switch (laa.getJlEx().getSelectedIndex()) {
					case FUNCTION1:
						laa.getJtaresult().setText("");
						laa.getJtaresult().append("\n1.���� ���� ���� key\n");
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
								laa.getJtaresult().append("\n6.�Էµ� �������� ���� ���� ���� key\n");
								laa.getJtaresult().append(key(startLine, endLine));
								flag = false;
							} // end if
						} catch (NumberFormatException npe) {
							JOptionPane.showMessageDialog(laa, "����� �Է�ĭ�� ���ڸ� �Է� �� ������ּ���.", "ERROR",
									JOptionPane.ERROR_MESSAGE);
						} // end catch
					}// end switch
				}// end switch
			}// mouseClicked
		});
	}// jlistMouseEvt

	// �������û Ƚ�� �� ���� ���ϱ�
	public String annormal_Request() {
		int cnt = responeMap.get(ANNORMAL_REQUEST);
		StringBuilder sb = new StringBuilder("\n5. �������û\n�߻�Ƚ�� [ ").append(cnt).append("ȸ]\n�߻����� [").append(ratio(cnt))
				.append("%]\n");
		return sb.toString();
	}// annormalRequest

	// ����, ���� ��û Ƚ�� ���ϱ�
	public String success_fail() {
		int success_Cnt = responeMap.get(SUCCESS_REQUEST);
		int fail_Cnt = responeMap.get(FAIL_REQUEST);
		StringBuilder sb = new StringBuilder("\n3. ����,����\n����Ƚ�� [").append(success_Cnt).append("ȸ]\n����Ƚ�� [")
				.append(fail_Cnt).append("ȸ]\n");
		return sb.toString();
	}// success_fail

	// browser�� Ƚ�� �� �������ϱ�.
	public String browser() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n2.browser �м�\n");
		for (String browser : browserMap.keySet()) {
			sb.append("[").append(browser).append("] ����Ƚ�� [").append(browserMap.get(browser)).append("ȸ] ���� [")
					.append(ratio(browserMap.get(browser))).append("%]\n");
		} // end for
		return sb.toString();
	}// browser

	// ������ ���� ���� �ð� ���ϱ�
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
		StringBuilder sb = new StringBuilder("\n4. ���� ��û�� ���� �ð� [").append(mostTime).append("��]\n");
		return sb.toString();
	}// time

	// ���� ���� ���� key Ƚ�� �� ����
	public String key(int start, int end) { // ����ڰ� ���� �Է����� ���� ���� �ϱ� ���� �Ű����� ���
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

		StringBuilder sb = new StringBuilder("[ ").append(mostKey).append(" ] ���Ƚ�� [ ").append(cnt).append("ȸ ]\n");
		return sb.toString();
	}// key

	// ����� �Է��� �� ��ȿ�� �˻�
	public void userValidation() throws NullPointerException {
		int inputstart = Integer.parseInt(laa.getJtfStrat().getText());// �Է°��� �������� ""�� ����ִ�.
		int inputend = Integer.parseInt(laa.getJtfEnd().getText());// �Է°��� �������� ""�� ����ִ�.
		if (inputstart <= inputend) {
			if (inputstart > 0 && inputend < cntLog + 1) {
				startLine = inputstart - 1;
				endLine = inputend;
				flag = true;
			} else {
				JOptionPane.showMessageDialog(laa, "�Է°��� ������ Ȯ�� �� �ٽ� �õ����ּ���.", "ERROR", JOptionPane.ERROR_MESSAGE);
			} // end else
		} else {
			JOptionPane.showMessageDialog(laa, "StartLine�� EndLine���� Ŭ �� �����ϴ�.\nȮ�� �� �ٽ��Է����ּ���.", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		} // end else
	}// validation

	// ������ ���ϴ� method
	public String ratio(int cnt) {
		String ratio = "";
		ratio = String.format("%.2f", (cnt / (double) cntLog) * 100);
		return ratio;
	}// ratio

	// x��ư�� ������ �� �̺�Ʈ ó��
	@Override
	public void windowClosing(WindowEvent wce) {
		endOption();
	}// windowClosing

	// close�� x��ư�� ���������� �����̺�Ʈ
	public void endOption() {
		switch (JOptionPane.showConfirmDialog(laa, "Log�м�â�� �����ðڽ��ϱ�?", "����", JOptionPane.YES_NO_OPTION)) {
		case JOptionPane.OK_OPTION:
			laa.dispose();
		}// end switch
	}// endOption

	public void openLogAnalysis_view() {
		LogAnalysis_View lav = new LogAnalysis_View(laa, this);
		lav.getJtaAllResult().append("\n1.���� ���� ���� key\n");
		lav.getJtaAllResult().append(key(0, cntLog));
		lav.getJtaAllResult().append(browser());
		lav.getJtaAllResult().append(success_fail());
		lav.getJtaAllResult().append(time());
		lav.getJtaAllResult().append(annormal_Request());
		StringBuilder sb = new StringBuilder("�Է��� ���� [").append(startLine+1).append("~").append(endLine).append("]\n");
		lav.getJtaAllResult().append("\n6.�Էµ� �������� ���� ���� ���� key\n");
		lav.getJtaAllResult().append(sb.toString());
		lav.getJtaAllResult().append(key(startLine, endLine));
	}// openLogAnalysis_Analysis

	public String getFileName() {
		return fileName;
	}//getFileName

}// LogAnalysis_AnalysisEvt
