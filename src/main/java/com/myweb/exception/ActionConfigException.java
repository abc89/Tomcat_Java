package com.myweb.exception;

/***
 * ��ȡaction ��Ӧ ��Ӧ ·�� �쳣��
 * 
 * @author e7691
 * 
 */
public class ActionConfigException extends Exception {
	public static final int CONFIGERROR_OR_NOACTION = 1;
	public static final int NOACTIONAME = 0;
	private String errorMsg = null;

	public ActionConfigException(int errorType, String msg) {
		switch (errorType) {
		case CONFIGERROR_OR_NOACTION:
			errorMsg = "�����ļ����� :���÷�ʽ�����action��action-urlδ���" + msg;
			break;
		case NOACTIONAME:
			errorMsg = "�����ļ� �����ڸ�action/action-url�ڵ���" + msg;
			break;
		}
	}

	public void printStackTrace() {
		System.out.println(errorMsg);
		super.printStackTrace();
	}

}
