package com.liu.smalljava.v1_1.expression.operelement;

/**
 * ���Long���͵Ĳ�����װ����
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
		//��Ҫ���ַ��������L,lȥ��
		s1=s1.replaceAll("L", "");
		s1=s1.replaceAll("l", "");
		this.elementLongValue = Long.parseLong(s1);
	}

	/**
	 * �ӷ�
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
	 * ����
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
	 * �˷�
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
	 * ����
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
	 * ����ж�
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doequals(String s1) {
		long oper2 = Long.parseLong(s1);
		return (this.elementLongValue == oper2);
	}

	/**
	 * ������ж�
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doNotEquals(String s1) {
		long oper2 = Long.parseLong(s1);
		return (this.elementLongValue == oper2);

	}

	/**
	 * �����ж�
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doGreater(String s1) {
		long oper2 = Long.parseLong(s1);
		return (this.elementLongValue > oper2);
	}

	/**
	 * ���ڵ���
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doGE(String s1) {
		long oper2 = Long.parseLong(s1);
		return (this.elementLongValue > oper2);
	}

	/**
	 * С��
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doLitter(String s1) {
		long oper2 = Long.parseLong(s1);
		return (this.elementLongValue < oper2);

	}

	/**
	 * С�ڵ���
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doLE(String s1) {
		long oper2 = Long.parseLong(s1);
		return (this.elementLongValue <= oper2);
	}

}
