package com.liu.smalljava.v1_1.expression.operelement;

/**
 * ���Float���͵Ĳ�����װ����
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
	 * �ӷ�
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
	 * ����
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
	 * �˷�
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
	 * ����
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
	 * ����ж�
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doequals(String s1) {
		float oper2 = Float.parseFloat(s1);
		return (this.elementFloatValue == oper2);
	}

	/**
	 * ������ж�
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doNotEquals(String s1) {
		float oper2 = Float.parseFloat(s1);
		return (this.elementFloatValue == oper2);

	}

	/**
	 * �����ж�
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doGreater(String s1) {
		float oper2 = Float.parseFloat(s1);
		return (this.elementFloatValue > oper2);
	}

	/**
	 * ���ڵ���
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doGE(String s1) {
		float oper2 = Float.parseFloat(s1);
		return (this.elementFloatValue > oper2);
	}

	/**
	 * С��
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doLitter(String s1) {
		float oper2 = Float.parseFloat(s1);
		return (this.elementFloatValue < oper2);

	}

	/**
	 * С�ڵ���
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doLE(String s1) {
		float oper2 = Float.parseFloat(s1);
		return (this.elementFloatValue <= oper2);

	}

}
