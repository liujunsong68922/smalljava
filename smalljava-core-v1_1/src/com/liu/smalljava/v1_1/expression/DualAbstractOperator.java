package com.liu.smalljava.v1_1.expression;

import com.liu.smalljava.v1_1.vartable.VarTableNodeV1_1;

/**
 * 公共二元运算符的通用方法实现，避免各运算符自己再创造一套公用算法
 * @author liujunsong
 *
 */
public abstract class DualAbstractOperator implements IDualOperator{
	ASTTreeNodeV1_1 astnode;
	
	/**
	 * 设置要计算的astnode
	 */
	@Override
	public void setAstNode(ASTTreeNodeV1_1 astnode) {
		this.astnode = astnode;
	}

	/**
	 * 赋值操作，需要传入变量表来进行寻址
	 * @param varmap
	 * @return
	 */
	@Override
	public boolean evalset(VarTableNodeV1_1 varmap) {
		System.out.println("【程序执行逻辑错误】You Should Not Read this information.");
		//默认返回一个false
		return false;
	}
}
