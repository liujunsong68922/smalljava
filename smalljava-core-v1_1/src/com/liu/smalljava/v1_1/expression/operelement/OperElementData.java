package com.liu.smalljava.v1_1.expression.operelement;

/**
 * 操作符对应的操作元素的数据存储定义
 * @author liujunsong
 *
 */
public class OperElementData {
	//操作元素的数据类型,包装基础数据类型
	String elementdatatype;
	
	int elementIntValue=0;
	long elementLongValue=0;
	float elementFloatValue=0;
	double elementDoubleValue=0;
	boolean elementBooleanValue=false;
	String elementStringValue=null;
	
	//操作元素的对象类型，这里是对类名的存储
	String elementClassName;
	String elementFullClassName;	
	Object elementObjectValue=null;
	
	public String getElementdatatype() {
		return elementdatatype;
	}
	public void setElementdatatype(String elementdatatype) {
		this.elementdatatype = elementdatatype;
	}
	public int getElementIntValue() {
		return elementIntValue;
	}
	public void setElementIntValue(int elementIntValue) {
		this.elementIntValue = elementIntValue;
	}
	public long getElementLongValue() {
		return elementLongValue;
	}
	public void setElementLongValue(long elementLongValue) {
		this.elementLongValue = elementLongValue;
	}
	public float getElementFloatValue() {
		return elementFloatValue;
	}
	public void setElementFloatValue(float elementFloatValue) {
		this.elementFloatValue = elementFloatValue;
	}
	public double getElementDoubleValue() {
		return elementDoubleValue;
	}
	public void setElementDoubleValue(double elementDoubleValue) {
		this.elementDoubleValue = elementDoubleValue;
	}
	public boolean isElementBooleanValue() {
		return elementBooleanValue;
	}
	public void setElementBooleanValue(boolean elementBooleanValue) {
		this.elementBooleanValue = elementBooleanValue;
	}
	public String getElementStringValue() {
		return elementStringValue;
	}
	public void setElementStringValue(String elementStringValue) {
		this.elementStringValue = elementStringValue;
	}
	public String getElementClassName() {
		return elementClassName;
	}
	public void setElementClassName(String elementClassName) {
		this.elementClassName = elementClassName;
	}
	public String getElementFullClassName() {
		return elementFullClassName;
	}
	public void setElementFullClassName(String elementFullClassName) {
		this.elementFullClassName = elementFullClassName;
	}
	public Object getElementObjectValue() {
		return elementObjectValue;
	}
	public void setElementObjectValue(Object elementObjectValue) {
		this.elementObjectValue = elementObjectValue;
	}
	
	public String toString() {
		if(this.elementdatatype.equals("int")) {
			return ""+this.elementIntValue;
		}
		if(this.elementdatatype.equals("long")) {
			return ""+this.elementLongValue;
		}
		if(this.elementdatatype.equals("float")) {
			return ""+this.elementFloatValue;
		}
		if(this.elementdatatype.equals("double")) {
			return ""+this.elementDoubleValue;
		}
		if(this.elementdatatype.equals("boolean")) {
			return ""+this.elementBooleanValue;
		}
		if(this.elementdatatype.equals("String")) {
			return ""+this.elementStringValue;
		}
		System.out.println("toString():unknown datatype:"+this.elementdatatype);
		return this.elementObjectValue.toString();
	}
	
	public void setValue(String svalue) {
		if(this.elementdatatype.equals("String")) {
			this.elementStringValue = svalue;
			return ;
		}
		if (this.elementdatatype.equals("int")
				|| this.elementdatatype.equals("long")
				|| this.elementdatatype.equals("float")
				|| this.elementdatatype.equals("double")) {
			double d = Double.parseDouble(svalue);
			this.elementIntValue = (int)d;
			this.elementLongValue = (long)d;
			this.elementFloatValue = (float)d;
			this.elementDoubleValue = d;
			return ;
		}
		if (this.elementdatatype.equals("boolean")) {
			this.elementBooleanValue = svalue.equalsIgnoreCase("true");
			return;
		}
	}
	
	public Object getObject() {
		if(this.elementdatatype == null) {
			return null;
		}
		if(this.elementdatatype.equals("int")) {
			return this.elementIntValue;
		}else if(this.elementdatatype.equals("long")) {
			return this.elementLongValue;
		}else if(this.elementdatatype.equals("float")) {
			return this.elementFloatValue;
		}else if(this.elementdatatype.equals("double")) {
			return this.elementDoubleValue;
		}else if(this.elementdatatype.equals("String")) {
			return this.elementStringValue;
		}else {
			return this.elementObjectValue;
		}
	}
}
