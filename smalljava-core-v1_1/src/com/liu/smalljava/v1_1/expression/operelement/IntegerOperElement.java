package com.liu.smalljava.v1_1.expression.operelement;

/**
 * ����������͵Ĳ�����װ����
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
	 * �ӷ�
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
	 * ����
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
	 * �˷�
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
	 * ����
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
	 * ����ж�
	 * @param s1
	 * @return
	 */
	public boolean doequals(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		return (this.elementIntValue == oper2);
	}
	
	/**
	 * ������ж�
	 * @param s1
	 * @return
	 */
	public boolean doNotEquals(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		return (this.elementIntValue == oper2);
		
	}

	/**
	 * �����ж�
	 * @param s1
	 * @return
	 */
	public boolean doGreater(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		return (this.elementIntValue > oper2);		
	}
	
	/**
	 * ���ڵ���
	 * @param s1
	 * @return
	 */
	public boolean doGE(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		return (this.elementIntValue > oper2);		
	}
	
	/**
	 * С��
	 * @param s1
	 * @return
	 */
	public boolean doLitter(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		return (this.elementIntValue < oper2);		
		
	}
	
	/**
	 * С�ڵ���
	 * @param s1
	 * @return
	 */
	public boolean doLE(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		return (this.elementIntValue <= oper2);		
		
	}

}
