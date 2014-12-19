package com.example.uactivity.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ActivityBean {
	private int id;
	private String title;
	private int orgniserID;
	private Date actTime;
	private Date startTime;
	private Date endTime;
	private Date applyTime;
	
	private int status;
	private int applyNum;
	private int numLimit;
	private String address;
	private int fee;
	private String benefit;
	private String feature;
	private String description;
	private int type;
	private int collectNum;
	private int schoolLimit;
	private int gradeLimit;
	private int genderLimit;
	private String contact;
	private String head;
	private ArrayList<Candidate> candidateList;
	
	class Candidate{
		String name;
		String head;
		int id;
		String cApplytime;
		public Candidate(int id,String name, String head, String applytime){
			this.name = name;
			this.head = head;
			this.id = id;
			this.cApplytime = applytime;
		}
		public String getName(){
			return name;
		}
		public String getHead(){
			return head;
		}
		public int getId(){
			return id;
		}
		public String getApplyTime(){
			return cApplytime;
		}
	}
	
	public ActivityBean(){
		candidateList = new ArrayList<Candidate>();
	}
	
	
	public String outputListString(){
		String listString = "" + title + "\t" + orgniserID + "\t" + "startTime" + "\t" + head;
		return listString;
	}
	
	public String outputActivityString(String orgniserName){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String activityString = "����:\t" + id + "\n";
		activityString +="�����:\t" + title + "\n";
		activityString +="��֯��:\t" + orgniserName + "\n";
		activityString +="��ʼʱ��:\t" + sdf.format(actTime) + "\n";
		activityString +="����ʱ��:\t" + sdf.format(startTime) + "\n";
		activityString +="����ʱ��:\t" + sdf.format(endTime) + "\n";
		activityString +="����ʱ��:\t" + sdf.format(applyTime) + "\n";
		activityString +="��ǰ״̬:\t" + status + "\n";
		activityString +="������:\t" + applyNum + "\n";
		activityString +="��������:\t" + numLimit + "\n";
		activityString +="�ص�:\t" + address + "\n";
		activityString +="����:\t" + fee + "\n";
		activityString +="�ô�:\t" + benefit + "\n";
		activityString +="��ɫ:\t" + feature + "\n";
		activityString +="�����:\t" + description + "\n";
		activityString +="�����:\t" + type + "\n";
		activityString +="�Ѳμ�����:\t" +  collectNum + "\n";
		activityString +="ѧУ����:\t" + schoolLimit + "\n";
		activityString +="�꼶����:\t" + gradeLimit + "\n";
		activityString +="�Ա�����:\t" + genderLimit + "\n";
		activityString +="��ϵ��ʽ:\t" + contact;
		return activityString;
	}
	
	
	public ActivityBean(String classData) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String []elements = classData.split("\t");
		this.id = Integer.parseInt(elements[0]);
		title = elements[1];
		this.orgniserID = Integer.parseInt(elements[2]);
		actTime = sdf.parse(elements[3]);
		startTime = sdf.parse(elements[4]);
		endTime = sdf.parse(elements[5]);
		applyTime = sdf.parse(elements[6]);
		status = Integer.parseInt(elements[7]);
		applyNum = Integer.parseInt(elements[8]);
		numLimit = Integer.parseInt(elements[9]);
		address = elements[10];
		fee = Integer.parseInt(elements[11]);
		benefit = elements[12];
		feature = elements[13];
		description = elements[14];
		type = Integer.parseInt(elements[15]);
		collectNum = Integer.parseInt(elements[16]);
		schoolLimit = Integer.parseInt(elements[17]);
		gradeLimit = Integer.parseInt(elements[18]);
		genderLimit = Integer.parseInt(elements[19]);
		contact = elements[20];
	}
	
	public ArrayList<Candidate> getCandidateList(){
		return candidateList;
	}
	
	public int getId(){
		return id; 
	}
	public String getTitle(){
		return title;
	}
	public int getOrgniserID(){
		return orgniserID;
	}
	public Date getActTime(){
		return actTime;
	}	
	public String getStartTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(startTime);
	}
	public String getEndTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(endTime);
	}
	public String getApplyTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(applyTime);
	}
	public int getStatus(){
		return status;
	}
	public int getApplyNum(){
		return applyNum;
	}
	public int getNumLimit(){
		return numLimit;
	}
	public String getAddress(){
		return address;
	}
	public int getFee(){
		return fee;
	}
	public String getBenefit(){
		return benefit;
	}
	public String getFeature(){
		return feature;
	}
	public String getDescription(){
		return description;
	}
	public int getType(){
		return type;
	}
	public int getCollectNum(){
		return collectNum;
	}
	public int getSchoolLimit(){
		return schoolLimit;
	}
	public int getGradeLimit(){
		return gradeLimit;
	}
	public String getContact(){
		return contact;
	}
	public String getHead(){
		return head;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public void setOneCandidate(int id, String name, String head, String applyTime){
		Candidate candidate = new Candidate(id, name, head, applyTime);
		candidateList.add(candidate);
	}
	public void setOrgniserID(int orgniserID){
		this.orgniserID = orgniserID;
	}
	public void setActTime(Date actTime){
		this.actTime = actTime;
	}
	public void setStartTime(String startTime) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.startTime = sdf.parse(startTime);
	}
	public void setEndTime(String endTime) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.endTime = sdf.parse(endTime);
	}
	public void setApplyTime(String applyTime) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.applyTime = sdf.parse(applyTime);
	}
	public void setStatus(int status){
		this.status = status;
	}
	public void setApplyNum(int applyNum){
		this.applyNum = applyNum;
	}
	public void setNumLimit(int numLimit){
		this.numLimit = numLimit;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public void setFee(int fee){
		this.fee = fee;
	}
	public void setBenefit(String benefit){
		this.benefit = benefit;
	}
	public void setFeature(String feature){
		this.feature = feature;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public void setType(int type){
		this.type = type;
	}
	public void setCollectNum(int collectNum){
		this.collectNum = collectNum;
	}
	public void setGradeLimit(int gradeLimit){
		this.gradeLimit = gradeLimit;
	}
	public void setSchoolLimit(int schoolLimit){
		this.schoolLimit = schoolLimit;
	}
	public void setGenderLimit(int genderLimit){
		this.genderLimit = genderLimit;
	}
	public void setContact(String contact){
		this.contact = contact;
	}
	public void setHead(String head){
		this.head = head;
	}
	
}
