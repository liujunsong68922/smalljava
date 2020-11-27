package com.liu.smalljava.v1_1.expression.operelement;

/**
 * 针对Long类型的操作封装符号
 * 
 * @author liujunsong
 *
 */
public class LongOperElement extends OperElementData {

	public LongOperElement() {
		this.elementdatatype = "long";
	}

	public LongOperElement(String s1) {
		this.elementdatatype = "long";
		//需要把字符串里面的L,l去掉
		s1=s1.replaceAll("L", "");
		s1=s1.replaceAll("l", "");
		this.elementLongValue = Long.parseLong(s1);
	}

	/**
	 * 加法
	 * 
	 * @param s1
	 * @return
	 */
	public LongOperElement doAdd(String s1) {
		long oper2 = Long.parseLong(s1);
		long iresult = this.elementLongValue + oper2;
		this.elementLongValue = iresult;
		return this;
	}

	/**
	 * 减法
	 * 
	 * @param s1
	 * @return
	 */
	public LongOperElement doDeAdd(String s1) {
		long oper2 = Long.parseLong(s1);
		long iresult = this.elementLongValue - oper2;
		this.elementLongValue = iresult;
		return this;
	}

	/**
	 * 乘法
	 * 
	 * @param s1
	 * @return
	 */
	public LongOperElement doMulti(String s1) {
		long oper2 = Long.parseLong(s1);
		long iresult = this.elementLongValue * oper2;
		this.elementLongValue = iresult;
		return this;
	}

	/**
	 * 除法
	 * 
	 * @param s1
	 * @return
	 */
	public LongOperElement doDevide(String s1) {
		long oper2 = Long.parseLong(s1);
		long iresult = this.elementLongValue / oper2;
		this.elementLongValue = iresult;
		return this;
	}

	/**
	 * 相等判断
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doequals(String s1) {
		long oper2 = Long.parseLong(s1);
		return (this.elementLongValue == oper2);
	}

	/**
	 * 不相等判断
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doNotEquals(String s1) {
		long oper2 = Long.parseLong(s1);
		return (this.elementLongValue == oper2);

	}

	/**
	 * 大于判断
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doGreater(String s1) {
		long oper2 = Long.parseLong(s1);
		return (this.elementLongValue > oper2);
	}

	/**
	 * 大于等于
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doGE(String s1) {
		long oper2 = Long.parseLong(s1);
		return (this.elementLongValue > oper2);
	}

	/**
	 * 小于
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doLitter(String s1) {
		long oper2 = Long.parseLong(s1);
		return (this.elementLongValue < oper2);

	}

	/**
	 * 小于等于
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doLE(String s1) {
		long oper2 = Long.parseLong(s1);
		return (this.elementLongValue <= oper2);
	}

}
