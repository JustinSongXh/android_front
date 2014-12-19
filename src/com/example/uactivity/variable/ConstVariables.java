package com.example.uactivity.variable;

import java.util.HashMap;

public class ConstVariables {
	
	private String IPAddress;

	private HashMap<String, Integer> schoolMap;
	private HashMap<String, Integer> gradeMap;
	private HashMap<String, Integer> genderMap;
	private HashMap<String, Integer> interestMap;
	
	private HashMap<Integer, String> schoolRMap;
	private HashMap<Integer, String> gradeRMap;
	private HashMap<Integer, String> genderRMap;
	private HashMap<Integer, String> interestRMap;
	
	
	public ConstVariables(){
		
//		IPAddress = "http://10.0.3.2:8080";
		IPAddress = "http://192.168.191.1:8080";
		
		schoolMap = new HashMap<String, Integer>();
		gradeMap = new HashMap<String, Integer>();
		genderMap = new HashMap<String, Integer>();
		interestMap = new HashMap<String, Integer>();
		
		schoolRMap = new HashMap<Integer, String>();
		gradeRMap = new HashMap<Integer, String>();
		genderRMap = new HashMap<Integer, String>();
		interestRMap = new HashMap<Integer, String>();
		
		schoolMap.put("SchoolA", 1);
		schoolMap.put("SchoolB", 2);
		schoolMap.put("SchoolC", 3);
		schoolMap.put("SchoolD", 4);
		schoolMap.put("SchoolE", 5);
		schoolMap.put("SchoolF", 6);
		
		gradeMap.put("����һ�꼶", 1);
		gradeMap.put("���ƶ��꼶", 2);
		gradeMap.put("�������꼶", 3);
		gradeMap.put("�������꼶", 4);
		gradeMap.put("��˶һ�꼶", 5);
		gradeMap.put("��˶���꼶", 6);
		gradeMap.put("��˶���꼶", 7);
		gradeMap.put("��˶���꼶", 8);
		gradeMap.put("��˶���꼶", 9);
		
		genderMap.put("��", 1);
		genderMap.put("Ů", 2);
		
		interestMap.put("����", 1);
		interestMap.put("����", 2);		
		interestMap.put("�˶�", 3);
		interestMap.put("����", 4);
		interestMap.put("��", 5);
		interestMap.put("���", 6);
		
		schoolRMap.put(1, "SchoolA");
		schoolRMap.put(2, "SchoolB");
		schoolRMap.put(3, "SchoolC");
		schoolRMap.put(4, "SchoolD");
		schoolRMap.put(5, "SchoolE");
		schoolRMap.put(6, "SchoolF");
		
		gradeRMap.put(1, "����һ�꼶");
		gradeRMap.put(2, "���ƶ��꼶");
		gradeRMap.put(3, "�������꼶");
		gradeRMap.put(4, "�������꼶");
		gradeRMap.put(5, "��˶һ�꼶");
		gradeRMap.put(6, "��˶���꼶");
		gradeRMap.put(7, "��˶���꼶");
		gradeRMap.put(8, "��˶���꼶");
		gradeRMap.put(9, "��˶���꼶");
		
		genderRMap.put(1, "��");
		genderRMap.put(2, "Ů");
		
		interestRMap.put(1, "����");
		interestRMap.put(2, "����");		
		interestRMap.put(3, "�˶�");
		interestRMap.put(4, "����");
		interestRMap.put(5, "��");
		interestRMap.put(6, "���");

	}
	
	public String getIPAddress(){
		return this.IPAddress;
	}
	
	public int getSchoolId(String school){
		if(schoolMap.containsKey(school)){
			return schoolMap.get(school);
		}
		
		return 0;
	}
	public String getSchoolName(int schoolId){
		if(schoolRMap.containsKey(schoolId)){
			return schoolRMap.get(schoolId);
		}
		return "������";
	}
	public int getGradeId(String grade){
		if(gradeMap.containsKey(grade)){
			return gradeMap.get(grade);
		}
		
		return 0;
	}
	public String getGradeName(int gradeId){
		if(gradeRMap.containsKey(gradeId)){
			return gradeRMap.get(gradeId);
		}
		return "������";
	}
	public int getInterestId(String interest){
		if(interestMap.containsKey(interest)){
			return interestMap.get(interest);
		}
		
		return -1;
	}
	public String getInterestName(int schoolId){
		if(interestRMap.containsKey(schoolId)){
			return interestRMap.get(schoolId);
		}
		return null;
	}
	public int getGenderId(String gender){
		if(genderMap.containsKey(gender)){
			return genderMap.get(gender);
		}
		
		return 0;
	}	
	public String getGenderName(int genderId){
		if(genderRMap.containsKey(genderId)){
			return genderRMap.get(genderId);
		}
		return "������";
	}
}
