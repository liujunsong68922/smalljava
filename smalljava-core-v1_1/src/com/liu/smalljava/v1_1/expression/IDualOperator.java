package com.liu.smalljava.v1_1.expression;

import com.liu.smalljava.v1_1.vartable.VarTableNodeV1_1;

/**
 * ���ж�Ԫ�������ĳ�����
 * @author liujunsong
 *
 */
public interface IDualOperator {
	/**
	 * ���ö�������ĳ������
	 * @param astnode
	 */
	public void setAstNode(ASTTreeNodeV1_1 astnode);
	/**
	 * ִ�ж�Ԫ������������
	 * @return
	 */
	public boolean evaloperator();
	
	/**
	 * ��ֵ��������Ҫ���������������Ѱַ
	 * @param varmap
	 * @return
	 */
	public boolean evalset(VarTableNodeV1_1 varmap);
}
