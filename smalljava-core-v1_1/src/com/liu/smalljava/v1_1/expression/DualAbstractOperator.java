package com.liu.smalljava.v1_1.expression;

import com.liu.smalljava.v1_1.vartable.VarTableNodeV1_1;

/**
 * ������Ԫ�������ͨ�÷���ʵ�֣������������Լ��ٴ���һ�׹����㷨
 * @author liujunsong
 *
 */
public abstract class DualAbstractOperator implements IDualOperator{
	ASTTreeNodeV1_1 astnode;
	
	/**
	 * ����Ҫ�����astnode
	 */
	@Override
	public void setAstNode(ASTTreeNodeV1_1 astnode) {
		this.astnode = astnode;
	}

	/**
	 * ��ֵ��������Ҫ���������������Ѱַ
	 * @param varmap
	 * @return
	 */
	@Override
	public boolean evalset(VarTableNodeV1_1 varmap) {
		System.out.println("������ִ���߼�����You Should Not Read this information.");
		//Ĭ�Ϸ���һ��false
		return false;
	}
}
