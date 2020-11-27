package com.liu.smalljava.v1_1.expression;

import com.liu.smalljava.v1_1.vartable.VarTableNodeV1_1;

/**
 * 所有二元操作符的抽象定义
 * @author liujunsong
 *
 */
public interface IDualOperator {
	/**
	 * 设置对象操作的抽象对象
	 * @param astnode
	 */
	public void setAstNode(ASTTreeNodeV1_1 astnode);
	/**
	 * 执行二元操作符的运算
	 * @return
	 */
	public boolean evaloperator();
	
	/**
	 * 赋值操作，需要传入变量表来进行寻址
	 * @param varmap
	 * @return
	 */
	public boolean evalset(VarTableNodeV1_1 varmap);
}
