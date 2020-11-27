package com.liu.smalljava.v1_1.expression;

import com.liu.smalljava.v1_1.expression.operelement.DoubleOperElement;
import com.liu.smalljava.v1_1.expression.operelement.FloatOperElement;
import com.liu.smalljava.v1_1.expression.operelement.IntegerOperElement;
import com.liu.smalljava.v1_1.expression.operelement.LongOperElement;
import com.liu.smalljava.v1_1.expression.operelement.OperElementData;

public class DualOperator_DeAdd extends DualAbstractOperator {


@Override
public boolean evaloperator() {
	// ִ�м�������
	return this.eval_threechild_deadd();
}

/**
 * ��Ԫ���㣬����
 * 
 * @return
 */
private boolean eval_threechild_deadd() {
	OperElementData leftoper = astnode.children.get(0).operdataresult;
	if (leftoper == null) {
		System.out.println("eval_threechild_deadd.�����߼��������������Ϊnull");
		return false;
	}
	if (leftoper.getElementdatatype() == null) {
		System.out.println("eval_threechild_deadd.�����߼������������������Ϊnull");
		return false;
	}
	if (leftoper.getElementdatatype().equals("int")) {
		IntegerOperElement intoper = (IntegerOperElement) leftoper;
		// �ѵڶ����ڵ���ַ�������ȥ
		System.out.println("���������:" + astnode.children.get(2).getOperdataresult().toString());
		intoper.doDeAdd(astnode.children.get(2).getOperdataresult().toString());
		astnode.operdataresult = intoper;
		return true;
	}
	if (leftoper.getElementdatatype().equals("long")) {
		LongOperElement longoper = (LongOperElement) leftoper;
		// �ѵڶ����ڵ���ַ�������ȥ
		System.out.println("Long���������:" + astnode.children.get(2).getOperdataresult().toString());
		longoper.doDeAdd(astnode.children.get(2).getOperdataresult().toString());
		astnode.operdataresult = longoper;
		return true;
	}
	if (leftoper.getElementdatatype().equals("float")) {
		FloatOperElement floatoper = (FloatOperElement) leftoper;
		// �ѵڶ����ڵ���ַ�������ȥ
		System.out.println("Float���������:" + astnode.children.get(2).getOperdataresult().toString());
		floatoper.doDeAdd(astnode.children.get(2).getOperdataresult().toString());
		astnode.operdataresult = floatoper;
		return true;
	}
	if (leftoper.getElementdatatype().equals("double")) {
		DoubleOperElement doubleoper = (DoubleOperElement) leftoper;
		// �ѵڶ����ڵ���ַ�������ȥ
		System.out.println("Double���������:" + astnode.children.get(2).getOperdataresult().toString());
		doubleoper.doDeAdd(astnode.children.get(2).getOperdataresult().toString());
		astnode.operdataresult = doubleoper;
		return true;
	}
	System.out.println("��ERROR�����Ų��������˲�֧�ֵ��������ͣ�" + leftoper.getElementdatatype());
	return false;
}

}