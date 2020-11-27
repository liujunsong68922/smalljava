package com.liu.smalljava.v1_1.expression.operelement;

/**
 * ���Double���͵Ĳ�����װ����
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
	 * �ӷ�
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
	 * ����
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
	 * �˷�
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
	 * ����
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
	 * ����ж�
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doequals(String s1) {
		double oper2 = Double.parseDouble(s1);
		return (this.elementIntValue == oper2);
	}

	/**
	 * ������ж�
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doNotEquals(String s1) {
		double oper2 = Double.parseDouble(s1);
		return (this.elementIntValue == oper2);

	}

	/**
	 * �����ж�
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doGreater(String s1) {
		double oper2 = Double.parseDouble(s1);
		return (this.elementIntValue > oper2);
	}

	/**
	 * ���ڵ���
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doGE(String s1) {
		double oper2 = Double.parseDouble(s1);
		return (this.elementIntValue > oper2);
	}

	/**
	 * С��
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doLitter(String s1) {
		double oper2 = Double.parseDouble(s1);
		return (this.elementIntValue < oper2);

	}

	/**
	 * С�ڵ���
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doLE(String s1) {
		double oper2 = Double.parseDouble(s1);
		return (this.elementIntValue <= oper2);

	}

}
