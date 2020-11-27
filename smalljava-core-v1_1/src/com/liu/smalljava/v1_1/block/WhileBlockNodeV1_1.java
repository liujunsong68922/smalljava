package com.liu.smalljava.v1_1.block;

import com.liu.smalljava.v1_1.expression.ASTTreeNodeV1_1;
import com.liu.smalljava.v1_1.vartable.VarTableNodeV1_1;

public class WhileBlockNodeV1_1 extends BaseBlockNodeV1_1 {
	private String whilecondition;
	private BaseBlockNodeV1_1 whilenode;

	public String getWhilecondition() {
		return whilecondition;
	}

	public void setWhilecondition(String whilecondition) {
		this.whilecondition = whilecondition;
	}

	public BaseBlockNodeV1_1 getWhilenode() {
		return whilenode;
	}

	public void setWhilenode(BaseBlockNodeV1_1 whilenode) {
		this.whilenode = whilenode;
	}

	/**
	 * while节点的分析
	 */
	public boolean anylyse() {
		this.whilenode.anylyse();
		System.out.println("while子句分析成功");
		return true;
	}
	
	public boolean execute() {
		// IF NODE的执行过程如下，先计算if表达式的值
		System.out.println("while 条件表达式:" + this.whilecondition);
		ASTTreeNodeV1_1 node = new ASTTreeNodeV1_1(this.whilecondition, 0);
		node.analyseTree();
		node.show();
		VarTableNodeV1_1 varmap = this.getVarmaps();
		node.eval(varmap);
		System.out.println("while条件计算结果：" + node.getOperdataresult().isElementBooleanValue());

		while (node.getOperdataresult().isElementBooleanValue()) {
			// 执行while block
			this.whilenode.execute();
			node = new ASTTreeNodeV1_1(this.whilecondition, 0);
			node.analyseTree();
			node.show();
			varmap = this.getVarmaps();
			node.eval(varmap);
			System.out.println("while条件计算结果：" + node.getOperdataresult().isElementBooleanValue());
			if(node.getOperdataresult().isElementBooleanValue()) {
				System.out.println("while 条件为真");
			}else {
				System.out.println("while 条件为假，循环退出.");
			}
		}
		return true;
	}
}
