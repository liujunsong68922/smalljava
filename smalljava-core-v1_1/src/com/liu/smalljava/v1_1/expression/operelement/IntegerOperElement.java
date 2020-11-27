package com.liu.smalljava.v1_1.expression.operelement;

/**
 * 针对整数类型的操作封装符号
 * @author liujunsong
 *
 */
public class IntegerOperElement extends OperElementData {
	
	public IntegerOperElement() {
		this.elementdatatype = "int";
	}
	
	public IntegerOperElement(String s1) {
		this.elementdatatype = "int";
		this.elementIntValue = Integer.parseInt(s1);
	}
	
	/**
	 * 加法
	 * @param s1
	 * @return
	 */
	public IntegerOperElement doAdd(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		int iresult = this.elementIntValue + oper2;
		this.elementIntValue = iresult;
		return this;
	}
	/**
	 * 减法
	 * @param s1
	 * @return
	 */
	public IntegerOperElement doDeAdd(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		int iresult = this.elementIntValue - oper2;
		this.elementIntValue = iresult;
		return this;		
	}
	
	/**
	 * 乘法
	 * @param s1
	 * @return
	 */
	public IntegerOperElement doMulti(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		int iresult = this.elementIntValue * oper2;
		this.elementIntValue = iresult;
		return this;		
	}
	
	/**
	 * 除法
	 * @param s1
	 * @return
	 */
	public IntegerOperElement doDevide(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		int iresult = this.elementIntValue / oper2;
		this.elementIntValue = iresult;
		return this;		
	}
	
	/**
	 * 相等判断
	 * @param s1
	 * @return
	 */
	public boolean doequals(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		return (this.elementIntValue == oper2);
	}
	
	/**
	 * 不相等判断
	 * @param s1
	 * @return
	 */
	public boolean doNotEquals(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		return (this.elementIntValue == oper2);
		
	}

	/**
	 * 大于判断
	 * @param s1
	 * @return
	 */
	public boolean doGreater(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		return (this.elementIntValue > oper2);		
	}
	
	/**
	 * 大于等于
	 * @param s1
	 * @return
	 */
	public boolean doGE(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		return (this.elementIntValue > oper2);		
	}
	
	/**
	 * 小于
	 * @param s1
	 * @return
	 */
	public boolean doLitter(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		return (this.elementIntValue < oper2);		
		
	}
	
	/**
	 * 小于等于
	 * @param s1
	 * @return
	 */
	public boolean doLE(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		return (this.elementIntValue <= oper2);		
		
	}

}
