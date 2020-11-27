package com.liu.smalljava.v1_1.expression.operelement;

/**
 * ����������͵Ĳ�����װ����
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
	 * �ӷ�
	 * @param s1
	 * @return
	 */
	public StringOperElement doAdd(String s1) {
		String iresult = this.elementStringValue + s1;
		this.elementStringValue = iresult;
		return this;
	}
	/**
	 * ����
	 * @param s1
	 * @return
	 */
	public StringOperElement doDeAdd(String s1) {
		System.out.println("�﷨����Stringû�м�������");
		return this;		
	}
	
	/**
	 * �˷�
	 * @param s1
	 * @return
	 */
	public StringOperElement doMulti(String s1) {
		System.out.println("�﷨����Stringû�г˷�����");
		return this;		
	}
	
	/**
	 * ����
	 * @param s1
	 * @return
	 */
	public StringOperElement doDevide(String s1) {
		System.out.println("�﷨����Stringû�г�������");
		return this;		
	}
	
	/**
	 * ����ж�
	 * @param s1
	 * @return
	 */
	public boolean doequals(String s1) {
		return (this.elementStringValue.equals(s1));
	}
	
	/**
	 * ������ж�
	 * @param s1
	 * @return
	 */
	public boolean doNotEquals(String s1) {
		 
		return (! this.elementStringValue.equals(s1));
		
	}

	/**
	 * �����ж�
	 * @param s1
	 * @return
	 */
	public boolean doGreater(String s1) {
		 
		return (this.elementStringValue.compareTo(s1)>0);		
	}
	
	/**
	 * ���ڵ���
	 * @param s1
	 * @return
	 */
	public boolean doGE(String s1) {
		 
		return (this.elementStringValue.compareTo(s1)>=0);		
	}
	
	/**
	 * С��
	 * @param s1
	 * @return
	 */
	public boolean doLitter(String s1) {
		 
		return (this.elementStringValue.compareTo(s1)<0);		
		
	}
	
	/**
	 * С�ڵ���
	 * @param s1
	 * @return
	 */
	public boolean doLE(String s1) {
		 
		return (this.elementStringValue.compareTo(s1)<=0);		
		
	}

}
