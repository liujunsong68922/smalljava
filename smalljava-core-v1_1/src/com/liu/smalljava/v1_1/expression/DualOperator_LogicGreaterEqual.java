package com.liu.smalljava.v1_1.expression;

import com.liu.smalljava.v1_1.expression.operelement.BooleanOperElement;
import com.liu.smalljava.v1_1.expression.operelement.DoubleOperElement;
import com.liu.smalljava.v1_1.expression.operelement.FloatOperElement;
import com.liu.smalljava.v1_1.expression.operelement.IntegerOperElement;
import com.liu.smalljava.v1_1.expression.operelement.LongOperElement;
import com.liu.smalljava.v1_1.expression.operelement.OperElementData;

public class DualOperator_LogicGreaterEqual extends DualAbstractOperator{

	@Override
	public boolean evaloperator() {
		// >=
		return eval_threechild_greaterequal();
	}

	/**
	 * ��Ԫ���㣬���ڵ��ں��㷨
	 * 
	 * @return
	 */
	private boolean eval_threechild_greaterequal() {
		OperElementData leftoper = astnode.children.get(0).operdataresult;
		if (leftoper == null) {
			System.out.println("eval_threechild_greaterequal.�����߼��������������Ϊnull");
			return false;
		}
		if (leftoper.getElementdatatype() == null) {
			System.out.println("eval_threechild_greaterequal.�����߼������������������Ϊnull");
			return false;
		}
		if (leftoper.getElementdatatype().equals("int")) {
			IntegerOperElement intoper = (IntegerOperElement) leftoper;
			// �ѵڶ����ڵ���ַ�������ȥ
			System.out.println("���������:" + astnode.children.get(2).getOperdataresult().toString());
			boolean bresult = intoper.doGE(astnode.children.get(2).getOperdataresult().toString());
			BooleanOperElement booloper = new BooleanOperElement(bresult);
			astnode.operdataresult = booloper;
			return true;
		}
		if (leftoper.getElementdatatype().equals("long")) {
			LongOperElement longoper = (LongOperElement) leftoper;
			// �ѵڶ����ڵ���ַ�������ȥ
			System.out.println("Long���������:" + astnode.children.get(2).getOperdataresult().toString());
			boolean bresult = longoper.doGE(astnode.children.get(2).getOperdataresult().toString());
			BooleanOperElement booloper = new BooleanOperElement(bresult);
			astnode.operdataresult = booloper;
			return true;
		}
		if (leftoper.getElementdatatype().equals("float")) {
			FloatOperElement floatoper = (FloatOperElement) leftoper;
			// �ѵڶ����ڵ���ַ�������ȥ
			System.out.println("Float���������:" + astnode.children.get(2).getOperdataresult().toString());
			boolean bresult = floatoper.doGE(astnode.children.get(2).getOperdataresult().toString());
			BooleanOperElement booloper = new BooleanOperElement(bresult);
			astnode.operdataresult = booloper;
			return true;
		}
		if (leftoper.getElementdatatype().equals("double")) {
			DoubleOperElement doubleoper = (DoubleOperElement) leftoper;
			// �ѵڶ����ڵ���ַ�������ȥ
			System.out.println("Double���������:" + astnode.children.get(2).getOperdataresult().toString());
			boolean bresult = doubleoper.doGE(astnode.children.get(2).getOperdataresult().toString());
			BooleanOperElement booloper = new BooleanOperElement(bresult);
			astnode.operdataresult = booloper;
			return true;
		}

		System.out.println("��ERROR�����Ų��������˲�֧�ֵ��������ͣ�" + leftoper.getElementdatatype());
		return false;
	}
}
