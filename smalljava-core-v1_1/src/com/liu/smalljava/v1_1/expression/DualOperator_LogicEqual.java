package com.liu.smalljava.v1_1.expression;

import com.liu.smalljava.v1_1.expression.operelement.BooleanOperElement;
import com.liu.smalljava.v1_1.expression.operelement.DoubleOperElement;
import com.liu.smalljava.v1_1.expression.operelement.FloatOperElement;
import com.liu.smalljava.v1_1.expression.operelement.IntegerOperElement;
import com.liu.smalljava.v1_1.expression.operelement.LongOperElement;
import com.liu.smalljava.v1_1.expression.operelement.OperElementData;

public class DualOperator_LogicEqual extends DualAbstractOperator{

	@Override
	public boolean evaloperator() {
		// 逻辑相等判断
		return eval_threechild_equal();
	}
	
	/**
	 * 三元运算，逻辑相等算法
	 * 
	 * @return
	 */
	private boolean eval_threechild_equal() {
		System.out.println("执行相等判断...");
		OperElementData leftoper = astnode.children.get(0).operdataresult;
		if (leftoper == null) {
			System.out.println("eval_threechild_or.程序逻辑错误，左操作对象为null");
			return false;
		}
		if (leftoper.getElementdatatype() == null) {
			System.out.println("eval_threechild_or.程序逻辑错误，左操作对象类型为null");
			return false;
		}

		OperElementData rightoper = astnode.children.get(2).operdataresult;
		if (rightoper == null) {
			System.out.println("eval_threechild_or.程序逻辑错误，右操作对象为null");
			return false;
		}
		if (rightoper.getElementdatatype() == null) {
			System.out.println("eval_threechild_or.程序逻辑错误，右操作对象类型为null");
			return false;
		}

		if (leftoper.getElementdatatype().equals("int")) {
			IntegerOperElement intoper = (IntegerOperElement) leftoper;
			// 把第二个节点的字符串传进去
			System.out.println("右面操作数:" + astnode.children.get(2).getOperdataresult().toString());
			boolean bresult = intoper.doequals(astnode.children.get(2).getOperdataresult().toString());
			BooleanOperElement booloper = new BooleanOperElement(bresult);
			astnode.operdataresult = booloper;
			return true;
		}
		if (leftoper.getElementdatatype().equals("long")) {
			LongOperElement longoper = (LongOperElement) leftoper;
			// 把第二个节点的字符串传进去
			System.out.println("Long右面操作数:" + astnode.children.get(2).getOperdataresult().toString());
			boolean bresult = longoper.doequals(astnode.children.get(2).getOperdataresult().toString());
			BooleanOperElement booloper = new BooleanOperElement(bresult);
			astnode.operdataresult = booloper;
			return true;
		}
		if (leftoper.getElementdatatype().equals("float")) {
			FloatOperElement floatoper = (FloatOperElement) leftoper;
			// 把第二个节点的字符串传进去
			System.out.println("Float右面操作数:" + astnode.children.get(2).getOperdataresult().toString());
			boolean bresult = floatoper.doequals(astnode.children.get(2).getOperdataresult().toString());
			BooleanOperElement booloper = new BooleanOperElement(bresult);
			astnode.operdataresult = booloper;
			return true;
		}
		if (leftoper.getElementdatatype().equals("double")) {
			DoubleOperElement doubleoper = (DoubleOperElement) leftoper;
			// 把第二个节点的字符串传进去
			System.out.println("Double右面操作数:" + astnode.children.get(2).getOperdataresult().toString());
			boolean bresult = doubleoper.doequals(astnode.children.get(2).getOperdataresult().toString());
			BooleanOperElement booloper = new BooleanOperElement(bresult);
			astnode.operdataresult = booloper;
			return true;
		}

		if (leftoper.getElementdatatype().equals("boolean")) {
			BooleanOperElement booloper = (BooleanOperElement) leftoper;
			// 把第二个节点的字符串传进去
			System.out.println("右面操作数:" + astnode.children.get(2).getOperdataresult().toString());
			boolean bresult = booloper.doequals(astnode.children.get(2).getOperdataresult().toString());
			BooleanOperElement booloper2 = new BooleanOperElement(bresult);
			astnode.operdataresult = booloper2;

			return true;
		}

		System.out.println("【ERROR】 逻辑或 操作遇到了不支持的数据类型：" + leftoper.getElementdatatype());
		return false;
	}

}
