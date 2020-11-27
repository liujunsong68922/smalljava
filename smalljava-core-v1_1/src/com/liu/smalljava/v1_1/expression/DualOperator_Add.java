package com.liu.smalljava.v1_1.expression;

import com.liu.smalljava.v1_1.expression.operelement.DoubleOperElement;
import com.liu.smalljava.v1_1.expression.operelement.FloatOperElement;
import com.liu.smalljava.v1_1.expression.operelement.IntegerOperElement;
import com.liu.smalljava.v1_1.expression.operelement.LongOperElement;
import com.liu.smalljava.v1_1.expression.operelement.OperElementData;

public class DualOperator_Add extends DualAbstractOperator {
	
	
	/**
	 * ��Ԫ���㣬�ӷ�
	 * 
	 * @return
	 */
	private boolean eval_threechild_add() {
		OperElementData leftoper = astnode.children.get(0).operdataresult;
		if (leftoper == null) {
			System.out.println("eval_threechild_add.�����߼��������������Ϊnull");
			return false;
		}
		if (leftoper.getElementdatatype() == null) {
			System.out.println("eval_threechild_add.�����߼������������������Ϊnull");
			return false;
		}
		if (leftoper.getElementdatatype().equals("int")) {
			IntegerOperElement intoper = (IntegerOperElement) leftoper;
			// �ѵڶ����ڵ���ַ�������ȥ
			System.out.println("���������:" + astnode.children.get(2).getOperdataresult().toString());
			intoper.doAdd(astnode.children.get(2).getOperdataresult().toString());
			astnode.operdataresult = intoper;
			return true;
		}
		if (leftoper.getElementdatatype().equals("long")) {
			LongOperElement longoper = (LongOperElement) leftoper;
			// �ѵڶ����ڵ���ַ�������ȥ
			System.out.println("Long���������:" + astnode.children.get(2).getOperdataresult().toString());
			longoper.doAdd(astnode.children.get(2).getOperdataresult().toString());
			astnode.operdataresult = longoper;
			return true;
		}
		if (leftoper.getElementdatatype().equals("float")) {
			FloatOperElement floatoper = (FloatOperElement) leftoper;
			// �ѵڶ����ڵ���ַ�������ȥ
			System.out.println("Float���������:" + astnode.children.get(2).getOperdataresult().toString());
			floatoper.doAdd(astnode.children.get(2).getOperdataresult().toString());
			astnode.operdataresult = floatoper;
			return true;
		}
		if (leftoper.getElementdatatype().equals("double")) {
			DoubleOperElement doubleoper = (DoubleOperElement) leftoper;
			// �ѵڶ����ڵ���ַ�������ȥ
			System.out.println("Double���������:" + astnode.children.get(2).getOperdataresult().toString());
			doubleoper.doAdd(astnode.children.get(2).getOperdataresult().toString());
			astnode.operdataresult = doubleoper;
			return true;
		}
		System.out.println("��ERROR���ӺŲ��������˲�֧�ֵ��������ͣ�" + leftoper.getElementdatatype());
		return false;
	}

	@Override
	public boolean evaloperator() {
		//ִ�мӷ�����
		return eval_threechild_add();
	}
}
