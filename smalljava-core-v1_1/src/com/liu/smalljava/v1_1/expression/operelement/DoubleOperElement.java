package com.liu.smalljava.v1_1.expression.operelement;

/**
 * 针对Double类型的操作封装符号
 * 
 * @author liujunsong
 *
 */
public class DoubleOperElement extends OperElementData {

	public DoubleOperElement() {
		this.elementdatatype = "float";
	}

	public DoubleOperElement(String s1) {
		this.elementdatatype = "float";
		this.elementDoubleValue = Float.parseFloat(s1);
	}

	/**
	 * 加法
	 * 
	 * @param s1
	 * @return
	 */
	public DoubleOperElement doAdd(String s1) {
		double oper2 = Double.parseDouble(s1);
		double iresult = this.elementDoubleValue + oper2;
		this.elementDoubleValue = iresult;
		return this;
	}

	/**
	 * 减法
	 * 
	 * @param s1
	 * @return
	 */
	public DoubleOperElement doDeAdd(String s1) {
		double oper2 = Double.parseDouble(s1);
		double iresult = this.elementDoubleValue - oper2;
		this.elementDoubleValue = iresult;
		return this;
	}

	/**
	 * 乘法
	 * 
	 * @param s1
	 * @return
	 */
	public DoubleOperElement doMulti(String s1) {
		double oper2 = Double.parseDouble(s1);
		double iresult = this.elementDoubleValue * oper2;
		this.elementDoubleValue = iresult;
		return this;
	}

	/**
	 * 除法
	 * 
	 * @param s1
	 * @return
	 */
	public DoubleOperElement doDevide(String s1) {
		double oper2 = Double.parseDouble(s1);
		double iresult = this.elementDoubleValue / oper2;
		this.elementDoubleValue = iresult;
		return this;
	}

	/**
	 * 相等判断
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doequals(String s1) {
		double oper2 = Double.parseDouble(s1);
		return (this.elementIntValue == oper2);
	}

	/**
	 * 不相等判断
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doNotEquals(String s1) {
		double oper2 = Double.parseDouble(s1);
		return (this.elementIntValue == oper2);

	}

	/**
	 * 大于判断
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doGreater(String s1) {
		double oper2 = Double.parseDouble(s1);
		return (this.elementIntValue > oper2);
	}

	/**
	 * 大于等于
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doGE(String s1) {
		double oper2 = Double.parseDouble(s1);
		return (this.elementIntValue > oper2);
	}

	/**
	 * 小于
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doLitter(String s1) {
		double oper2 = Double.parseDouble(s1);
		return (this.elementIntValue < oper2);

	}

	/**
	 * 小于等于
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doLE(String s1) {
		double oper2 = Double.parseDouble(s1);
		return (this.elementIntValue <= oper2);

	}

}
