package com.liu.smalljava.v1_1.expression;

import com.liu.smalljava.v1_1.expression.operelement.BooleanOperElement;
import com.liu.smalljava.v1_1.expression.operelement.DoubleOperElement;
import com.liu.smalljava.v1_1.expression.operelement.FloatOperElement;
import com.liu.smalljava.v1_1.expression.operelement.IntegerOperElement;
import com.liu.smalljava.v1_1.expression.operelement.LongOperElement;
import com.liu.smalljava.v1_1.expression.operelement.OperElementData;

public class DualOperator_LogicLittle extends DualAbstractOperator{

	@Override
	public boolean evaloperator() {
		// <
		return eval_threechild_litter();
	}

	/**
	 * ��Ԫ���㣬С�ں��㷨
	 * 
	 * @return
	 */
	private boolean eval_threechild_litter() {
		OperElementData leftoper = astnode.children.get(0).operdataresult;
		if (leftoper == null) {
			System.out.println("eval_threechild_litter.�����߼��������������Ϊnull");
			return false;
		}
		if (leftoper.getElementdatatype() == null) {
			System.out.println("eval_threechild_litter.�����߼������������������Ϊnull");
			return false;
		}
		if (leftoper.getElementdatatype().equals("int")) {
			IntegerOperElement intoper = (IntegerOperElement) leftoper;
			// �ѵڶ����ڵ���ַ�������ȥ
			System.out.println("���������:" + astnode.children.get(2).getOperdataresult().toString());
			boolean bresult = intoper.doLitter(astnode.children.get(2).getOperdataresult().toString());
			BooleanOperElement booloper = new BooleanOperElement(bresult);
			astnode.operdataresult = booloper;
			return true;
		}
		if (leftoper.getElementdatatype().equals("long")) {
			LongOperElement longoper = (LongOperElement) leftoper;
			// �ѵڶ����ڵ���ַ�������ȥ
			System.out.println("Long���������:" + astnode.children.get(2).getOperdataresult().toString());
			boolean bresult = longoper.doLitter(astnode.children.get(2).getOperdataresult().toString());
			BooleanOperElement booloper = new BooleanOperElement(bresult);
			astnode.operdataresult = booloper;
			return true;
		}
		if (leftoper.getElementdatatype().equals("float")) {
			FloatOperElement floatoper = (FloatOperElement) leftoper;
			// �ѵڶ����ڵ���ַ�������ȥ
			System.out.println("Float���������:" + astnode.children.get(2).getOperdataresult().toString());
			boolean bresult = floatoper.doLitter(astnode.children.get(2).getOperdataresult().toString());
			BooleanOperElement booloper = new BooleanOperElement(bresult);
			astnode.operdataresult = booloper;
			return true;
		}
		if (leftoper.getElementdatatype().equals("double")) {
			DoubleOperElement doubleoper = (DoubleOperElement) leftoper;
			// �ѵڶ����ڵ���ַ�������ȥ
			System.out.println("Double���������:" + astnode.children.get(2).getOperdataresult().toString());
			boolean bresult = doubleoper.doLitter(astnode.children.get(2).getOperdataresult().toString());
			BooleanOperElement booloper = new BooleanOperElement(bresult);
			astnode.operdataresult = booloper;
			return true;
		}

		System.out.println("��ERROR��С�ںŲ��������˲�֧�ֵ��������ͣ�" + leftoper.getElementdatatype());
		return false;
	}
}
