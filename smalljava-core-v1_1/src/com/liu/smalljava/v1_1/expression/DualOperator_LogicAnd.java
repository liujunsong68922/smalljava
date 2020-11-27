package com.liu.smalljava.v1_1.expression;

import com.liu.smalljava.v1_1.expression.operelement.BooleanOperElement;
import com.liu.smalljava.v1_1.expression.operelement.OperElementData;

public class DualOperator_LogicAnd extends DualAbstractOperator{

	@Override
	public boolean evaloperator() {
		// TODO Auto-generated method stub
		return eval_threechild_and();
	}

	/**
	 * 三元运算，逻辑与算法
	 * 
	 * @return
	 */
	private boolean eval_threechild_and() {
		OperElementData leftoper = astnode.children.get(0).operdataresult;
		if (leftoper == null) {
			System.out.println("eval_threechild_and.程序逻辑错误，左操作对象为null");
			return false;
		}
		if (leftoper.getElementdatatype() == null) {
			System.out.println("eval_threechild_and.程序逻辑错误，左操作对象类型为null");
			return false;
		}
		if (!leftoper.getElementdatatype().equals("boolean")) {
			System.out.println("eval_threechild_and.程序逻辑错误，左操作对象不是boolean类型");
			return false;
		}

		OperElementData rightoper = astnode.children.get(2).operdataresult;
		if (rightoper == null) {
			System.out.println("eval_threechild_and.程序逻辑错误，右操作对象为null");
			return false;
		}
		if (rightoper.getElementdatatype() == null) {
			System.out.println("eval_threechild_and.程序逻辑错误，右操作对象类型为null");
			return false;
		}
		if (!rightoper.getElementdatatype().equals("boolean")) {
			System.out.println("eval_threechild_and.程序逻辑错误，右操作对象不是boolean类型");
			return false;
		}

		if (leftoper.getElementdatatype().equals("boolean")) {
			BooleanOperElement booloper = (BooleanOperElement) leftoper;
			// 把第二个节点的字符串传进去
			System.out.println("右面操作数:" + astnode.children.get(2).getOperdataresult().toString());
			BooleanOperElement bresult = booloper.doAnd(astnode.children.get(2).getOperdataresult().toString());
			astnode.operdataresult = bresult;
			return true;
		}

		System.out.println("【ERROR】 逻辑与 操作遇到了不支持的数据类型：" + leftoper.getElementdatatype());
		return false;
	}
}
