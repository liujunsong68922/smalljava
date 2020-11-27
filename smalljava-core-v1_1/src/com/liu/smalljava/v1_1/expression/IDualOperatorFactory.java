package com.liu.smalljava.v1_1.expression;

/**
 * ���ж�Ԫ������Ĺ����ඨ��
 * 
 * @author liujunsong
 *
 */
public class IDualOperatorFactory {
	/**
	 * ����������������ض��Ķ�Ԫ������㷨�߼�����
	 * 
	 * @param operator
	 * @return
	 */
	public IDualOperator getIDualOperator(String operator) {
		if (operator.equals("+")) {
			// ���ؼӷ������
			return new DualOperator_Add();
		}
		
		if (operator.equals("-")) {
			// ���ؼ��������
			return new DualOperator_DeAdd();
		}

		if (operator.equals("*")) {
			//���س˷������
			return new DualOperator_Multi();
		}
		
		if(operator.equals("/")) {
			//���س��������
			return new DualOperator_Devide();
		}
		
		if(operator.equals("&&")) {
			//�����߼��������
			return new DualOperator_LogicAnd();
		}
		
		if(operator.equals("||")) {
			//�����߼���
			return new DualOperator_LogicOR();
		}
		
		if(operator.equals("==")) {
			//�߼����
			return new DualOperator_LogicEqual();
		}
		
		if(operator.equals(">")) {
			// >
			return new DualOperator_LogicGreater();
		}
		
		if(operator.equals(">=")) {
			// >
			return new DualOperator_LogicGreaterEqual();
		}	
		
		if(operator.equals("<")) {
			// >
			return new DualOperator_LogicLittle();
		}
		
		if(operator.equals("<=")) {
			// >
			return new DualOperator_LogicLittleEqual();
		}
		
		if(operator.equals("=")) {
			// = ����һ��������ֵ����
			return new DualOperator_Set();
		}
		
		System.out.println("��������ô��󡿣��޷����Ķ�Ԫ������:" + operator);
		return null;
	}
}
