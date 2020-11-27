package com.liu.smalljava.v1_1.expression.operelement;

/**
 * ���boolean���͵Ĳ�����װ����
 * @author liujunsong
 *
 */
public class BooleanOperElement extends OperElementData {
	
	public BooleanOperElement() {
		this.elementdatatype = "boolean";
	}
	
	public BooleanOperElement(String s1) {
		this.elementdatatype = "boolean";
		s1 = s1.trim();
		this.elementBooleanValue = s1.equalsIgnoreCase("true");
	}
	
	public BooleanOperElement(boolean b1) {
		this.elementdatatype = "boolean";
		this.elementBooleanValue = b1;
	}
	
	/**
	 * �߼���
	 * @param s1
	 * @return
	 */
	public BooleanOperElement doAnd(String s1) {
		s1 = s1.trim();
		boolean b1 = s1.equalsIgnoreCase("true");
		this.elementBooleanValue = this.elementBooleanValue && b1;
		return this;
	}
	
	/**
	 * �߼���
	 * @param s1
	 * @return
	 */
	public BooleanOperElement doOr(String s1) {
		s1 = s1.trim();
		boolean b1 = s1.equalsIgnoreCase("true");
		this.elementBooleanValue = this.elementBooleanValue || b1;
		return this;
	}
	
	/**
	 * �߼���
	 * @return
	 */
	public BooleanOperElement doNot() {
		this.elementBooleanValue =! this.elementBooleanValue ;
		return this;
	}
	
	/**
	 * ����ж�
	 * @param s1
	 * @return
	 */
	public boolean doequals(String s1) {
		boolean oper2 = s1.equalsIgnoreCase("true");
		
		if (this.elementBooleanValue ) {
			return oper2;
		}else {
			return ! oper2;
		}
	}

}
