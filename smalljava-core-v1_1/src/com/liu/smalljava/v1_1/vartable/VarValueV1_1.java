package com.liu.smalljava.v1_1.vartable;

public class VarValueV1_1 {
	//������
	private String varname=null;
	//������������
	private String vartype=null;
	//����ֵ�����ַ�����ʾ�ַ������͵�ֵ
	private String varsvalue="";
	//����ֵ�������洢��ֵ����ֵ
	private double vardvalue=0;
	//����ֵ�������洢bool����ֵ
	private boolean varbvalue=false;
	//����ֵ�������洢
	private Object varobjvalue=null;
	
	public String getVarname() {
		return varname;
	}
	public void setVarname(String varname) {
		this.varname = varname;
	}
	public String getVartype() {
		return vartype;
	}
	public void setVartype(String vartype) {
		this.vartype = vartype;
	}
	public String getVarsvalue() {
		return varsvalue;
	}
	public void setVarsvalue(String varsvalue) {
		this.varsvalue = varsvalue;
	}
	public double getVardvalue() {
		return vardvalue;
	}
	public void setVardvalue(double vardvalue) {
		this.vardvalue = vardvalue;
	}
	public boolean isVarbvalue() {
		return varbvalue;
	}
	public void setVarbvalue(boolean varbvalue) {
		this.varbvalue = varbvalue;
	}
	public Object getVarobjvalue() {
		return varobjvalue;
	}
	public void setVarobjvalue(Object varobjvalue) {
		this.varobjvalue = varobjvalue;
	}

	
	
}
