package com.snore.util;

//ÿһ�����ü�����������
public class PageData {

	//�ڼ��μ��
	private int id;
	//˯����ʱ��
	private int sleepTime;
	//����ʱ��
	private int snoreTime;
	//�ϴ�����ʱ��
	private int snoreBigTime;
	//������������ʱ��
	private String snoreMaxMoment;
	//��������ʱ��
	private int snoreNormalTime;
	//���ʱ��
	private String startDetectTime;
	
	public PageData(){
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSleepTime() {
		return sleepTime;
	}
	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}
	public int getSnoreTime() {
		return snoreTime;
	}
	public void setSnoreTime(int snoreTime) {
		this.snoreTime = snoreTime;
	}
	public int getSnoreBigTime() {
		return snoreBigTime;
	}
	public void setSnoreBigTime(int snoreBigTime) {
		this.snoreBigTime = snoreBigTime;
	}
	public String getSnoreMaxMoment() {
		return snoreMaxMoment;
	}
	public void setSnoreMaxMoment(String snoreMaxMoment) {
		this.snoreMaxMoment = snoreMaxMoment;
	}
	public int getSnoreNormalTime() {
		return snoreNormalTime;
	}
	public void setSnoreNormalTime(int snoreNormalTime) {
		this.snoreNormalTime = snoreNormalTime;
	}

	public String getStartDetectTime() {
		return startDetectTime;
	}

	public void setStartDetectTime(String startDetectTime) {
		this.startDetectTime = startDetectTime;
	}
	
}
