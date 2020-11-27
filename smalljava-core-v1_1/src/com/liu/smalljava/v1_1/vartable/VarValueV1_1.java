package com.liu.smalljava.v1_1.vartable;

public class VarValueV1_1 {
	//变量名
	private String varname=null;
	//变量基础类型
	private String vartype=null;
	//变量值，用字符串表示字符串类型的值
	private String varsvalue="";
	//变量值，用来存储数值型数值
	private double vardvalue=0;
	//变量值，用来存储bool型数值
	private boolean varbvalue=false;
	//变量值，用来存储
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
