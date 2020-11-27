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
	 * ��Ԫ���㣬�߼����㷨
	 * 
	 * @return
	 */
	private boolean eval_threechild_and() {
		OperElementData leftoper = astnode.children.get(0).operdataresult;
		if (leftoper == null) {
			System.out.println("eval_threechild_and.�����߼��������������Ϊnull");
			return false;
		}
		if (leftoper.getElementdatatype() == null) {
			System.out.println("eval_threechild_and.�����߼������������������Ϊnull");
			return false;
		}
		if (!leftoper.getElementdatatype().equals("boolean")) {
			System.out.println("eval_threechild_and.�����߼����������������boolean����");
			return false;
		}

		OperElementData rightoper = astnode.children.get(2).operdataresult;
		if (rightoper == null) {
			System.out.println("eval_threechild_and.�����߼������Ҳ�������Ϊnull");
			return false;
		}
		if (rightoper.getElementdatatype() == null) {
			System.out.println("eval_threechild_and.�����߼������Ҳ�����������Ϊnull");
			return false;
		}
		if (!rightoper.getElementdatatype().equals("boolean")) {
			System.out.println("eval_threechild_and.�����߼������Ҳ���������boolean����");
			return false;
		}

		if (leftoper.getElementdatatype().equals("boolean")) {
			BooleanOperElement booloper = (BooleanOperElement) leftoper;
			// �ѵڶ����ڵ���ַ�������ȥ
			System.out.println("���������:" + astnode.children.get(2).getOperdataresult().toString());
			BooleanOperElement bresult = booloper.doAnd(astnode.children.get(2).getOperdataresult().toString());
			astnode.operdataresult = bresult;
			return true;
		}

		System.out.println("��ERROR�� �߼��� ���������˲�֧�ֵ��������ͣ�" + leftoper.getElementdatatype());
		return false;
	}
}
