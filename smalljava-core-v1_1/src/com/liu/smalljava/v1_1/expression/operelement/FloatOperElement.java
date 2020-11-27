package com.liu.smalljava.v1_1.expression.operelement;

/**
 * 针对Float类型的操作封装符号
 * 
 * @author liujunsong
 *
 */
public class FloatOperElement extends OperElementData {

	public FloatOperElement() {
		this.elementdatatype = "float";
	}

	public FloatOperElement(String s1) {
		this.elementdatatype = "float";
		this.elementFloatValue = Float.parseFloat(s1);
	}

	/**
	 * 加法
	 * 
	 * @param s1
	 * @return
	 */
	public FloatOperElement doAdd(String s1) {
		float oper2 = Float.parseFloat(s1);
		float iresult = this.elementFloatValue + oper2;
		this.elementFloatValue = iresult;
		return this;
	}

	/**
	 * 减法
	 * 
	 * @param s1
	 * @return
	 */
	public FloatOperElement doDeAdd(String s1) {
		float oper2 = Float.parseFloat(s1);
		float iresult = this.elementFloatValue - oper2;
		this.elementFloatValue = iresult;
		return this;
	}

	/**
	 * 乘法
	 * 
	 * @param s1
	 * @return
	 */
	public FloatOperElement doMulti(String s1) {
		float oper2 = Float.parseFloat(s1);
		float iresult = this.elementFloatValue * oper2;
		this.elementFloatValue = iresult;
		return this;
	}

	/**
	 * 除法
	 * 
	 * @param s1
	 * @return
	 */
	public FloatOperElement doDevide(String s1) {
		float oper2 = Float.parseFloat(s1);
		float iresult = this.elementFloatValue / oper2;
		this.elementFloatValue = iresult;
		return this;
	}

	/**
	 * 相等判断
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doequals(String s1) {
		float oper2 = Float.parseFloat(s1);
		return (this.elementFloatValue == oper2);
	}

	/**
	 * 不相等判断
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doNotEquals(String s1) {
		float oper2 = Float.parseFloat(s1);
		return (this.elementFloatValue == oper2);

	}

	/**
	 * 大于判断
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doGreater(String s1) {
		float oper2 = Float.parseFloat(s1);
		return (this.elementFloatValue > oper2);
	}

	/**
	 * 大于等于
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doGE(String s1) {
		float oper2 = Float.parseFloat(s1);
		return (this.elementFloatValue > oper2);
	}

	/**
	 * 小于
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doLitter(String s1) {
		float oper2 = Float.parseFloat(s1);
		return (this.elementFloatValue < oper2);

	}

	/**
	 * 小于等于
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doLE(String s1) {
		float oper2 = Float.parseFloat(s1);
		return (this.elementFloatValue <= oper2);

	}

}
