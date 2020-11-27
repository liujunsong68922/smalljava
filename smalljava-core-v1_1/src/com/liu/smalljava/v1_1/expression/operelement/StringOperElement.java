package com.liu.smalljava.v1_1.expression.operelement;

/**
 * 针对整数类型的操作封装符号
 * @author liujunsong
 *
 */
public class StringOperElement extends OperElementData {
	
	public StringOperElement() {
		this.elementdatatype = "String";
	}
	
	public StringOperElement(String s1) {
		this.elementdatatype = "String";
		this.elementStringValue = s1;
	}
	
	/**
	 * 加法
	 * @param s1
	 * @return
	 */
	public StringOperElement doAdd(String s1) {
		String iresult = this.elementStringValue + s1;
		this.elementStringValue = iresult;
		return this;
	}
	/**
	 * 减法
	 * @param s1
	 * @return
	 */
	public StringOperElement doDeAdd(String s1) {
		System.out.println("语法错误，String没有减法运算");
		return this;		
	}
	
	/**
	 * 乘法
	 * @param s1
	 * @return
	 */
	public StringOperElement doMulti(String s1) {
		System.out.println("语法错误，String没有乘法运算");
		return this;		
	}
	
	/**
	 * 除法
	 * @param s1
	 * @return
	 */
	public StringOperElement doDevide(String s1) {
		System.out.println("语法错误，String没有除法运算");
		return this;		
	}
	
	/**
	 * 相等判断
	 * @param s1
	 * @return
	 */
	public boolean doequals(String s1) {
		return (this.elementStringValue.equals(s1));
	}
	
	/**
	 * 不相等判断
	 * @param s1
	 * @return
	 */
	public boolean doNotEquals(String s1) {
		 
		return (! this.elementStringValue.equals(s1));
		
	}

	/**
	 * 大于判断
	 * @param s1
	 * @return
	 */
	public boolean doGreater(String s1) {
		 
		return (this.elementStringValue.compareTo(s1)>0);		
	}
	
	/**
	 * 大于等于
	 * @param s1
	 * @return
	 */
	public boolean doGE(String s1) {
		 
		return (this.elementStringValue.compareTo(s1)>=0);		
	}
	
	/**
	 * 小于
	 * @param s1
	 * @return
	 */
	public boolean doLitter(String s1) {
		 
		return (this.elementStringValue.compareTo(s1)<0);		
		
	}
	
	/**
	 * 小于等于
	 * @param s1
	 * @return
	 */
	public boolean doLE(String s1) {
		 
		return (this.elementStringValue.compareTo(s1)<=0);		
		
	}

}
