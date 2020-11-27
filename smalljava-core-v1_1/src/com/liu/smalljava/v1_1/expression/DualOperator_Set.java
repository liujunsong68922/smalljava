package com.liu.smalljava.v1_1.expression;

import com.liu.smalljava.v1_1.vartable.VarTableNodeV1_1;
import com.liu.smalljava.v1_1.vartable.VarValueV1_1;

public class DualOperator_Set extends DualAbstractOperator {

	@Override
	public boolean evaloperator() {
		//�߼��ϲ�Ӧ���ߵ��������������
		System.out.println("��������󡿣���Ӧ�õ���=�������ŵ��������");
		return false;
	}
	
	/**
	 * ��ֵ��������Ҫ���������������Ѱַ
	 * @param varmap
	 * @return
	 */
	@Override
	public boolean evalset(VarTableNodeV1_1 varmap) {
			System.out.println("��ʼ��ֵ����:" + astnode.children.get(0).stringresult);
			// ���������������
			String s1 = astnode.children.get(0).stringresult;
			double d2 = 0;
			if (astnode.children.get(2).operdataresult == null) {
				System.out.println("��ֵ���ִ��ʧ�ܣ��ұ��ʽ������Ϊnull");
				return false;
			}

			// �ӱ������м�������

			VarValueV1_1 varvalue = varmap.getVarValue(s1);
			if (varvalue == null) {
				System.out.println("��ֵ���ִ��ʧ�ܣ�����δ����:" + s1);
				return false;
			}
			if (astnode.children.get(2).operdataresult.getElementdatatype().equals("int")
					|| astnode.children.get(2).operdataresult.getElementdatatype().equals("long")
					|| astnode.children.get(2).operdataresult.getElementdatatype().equals("float")
					|| astnode.children.get(2).operdataresult.getElementdatatype().equals("double")) {

				varvalue.setVardvalue(Double.parseDouble(astnode.children.get(2).operdataresult.toString()));
			}
			varvalue.setVarsvalue("" + astnode.children.get(2).operdataresult.getElementStringValue());
			varvalue.setVarobjvalue(astnode.children.get(2).operdataresult.getElementObjectValue());

			// varmap.setVarValue(s1, varvalue);
			// ��ֵ�����ɹ��Ժ󣬷�������Ķ���

			astnode.operdataresult = astnode.children.get(2).operdataresult;
			return true;
	}

}
