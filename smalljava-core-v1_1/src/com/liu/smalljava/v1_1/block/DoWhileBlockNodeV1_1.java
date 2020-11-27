package com.liu.smalljava.v1_1.block;

import com.liu.smalljava.v1_1.expression.ASTTreeNodeV1_1;
import com.liu.smalljava.v1_1.vartable.VarTableNodeV1_1;

public class DoWhileBlockNodeV1_1 extends BaseBlockNodeV1_1{
	private BaseBlockNodeV1_1 donode;
	private String whilestring;
	public BaseBlockNodeV1_1 getDonode() {
		return donode;
	}
	public void setDonode(BaseBlockNodeV1_1 donode) {
		this.donode = donode;
	}
	public String getWhilestring() {
		return whilestring;
	}
	public void setWhilestring(String whilestring) {
		this.whilestring = whilestring;
	}

	/**
	 * while节点的分析
	 */
	public boolean anylyse() {
		this.donode.anylyse();
		System.out.println("do子句分析成功");
		return true;
	}
	
	public boolean execute() {
		//先执行一次do语句
		this.donode.execute();
		
		// IF NODE的执行过程如下，先计算if表达式的值
		System.out.println("while 条件表达式:" + this.whilestring);
		ASTTreeNodeV1_1 node = new ASTTreeNodeV1_1(this.whilestring, 0);
		node.analyseTree();
		node.show();
		VarTableNodeV1_1 varmap = this.getVarmaps();
		node.eval(varmap);
		System.out.println("while条件计算结果：" + node.getOperdataresult().isElementBooleanValue());
		
		while (node.getOperdataresult().isElementBooleanValue()) {
			// 执行while block
			this.donode.execute();
			node = new ASTTreeNodeV1_1(this.whilestring, 0);
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
