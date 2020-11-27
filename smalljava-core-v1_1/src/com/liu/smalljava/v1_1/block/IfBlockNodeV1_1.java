package com.liu.smalljava.v1_1.block;

import com.liu.smalljava.v1_1.expression.ASTTreeNodeV1_1;
import com.liu.smalljava.v1_1.vartable.VarTableNodeV1_1;

public class IfBlockNodeV1_1 extends BaseBlockNodeV1_1 {
	/**
	 * IF的判断条件，这个判断条件用一个字符串来描述
	 */
	private String ifcondition;
	/**
	 * if条件为真时的条件块
	 */
	private BaseBlockNodeV1_1 ifblocknode;
	/**
	 * if条件为假时的条件块
	 */
	private BaseBlockNodeV1_1 elseblocknode;

	public String getIfcondition() {
		return ifcondition;
	}

	public void setIfcondition(String ifcondition) {
		this.ifcondition = ifcondition;
	}

	public BaseBlockNodeV1_1 getIfblocknode() {
		return ifblocknode;
	}

	public void setIfblocknode(BaseBlockNodeV1_1 ifblocknode) {
		this.ifblocknode = ifblocknode;

	}

	public BaseBlockNodeV1_1 getElseblocknode() {
		return elseblocknode;
	}

	public void setElseblocknode(BaseBlockNodeV1_1 elseblocknode) {
		this.elseblocknode = elseblocknode;
	}

	/**
	 * if节点的分析
	 */
	public boolean anylyse() {
		if (this.ifblocknode != null) {
			this.ifblocknode.anylyse();
			System.out.println("if子句分析成功");
		}
		if (this.elseblocknode != null) {
			this.elseblocknode.anylyse();
			System.out.println("else子句分析成功.");
		}
		return true;
	}
	
	public boolean execute() {
		//IF NODE的执行过程如下，先计算if表达式的值
		System.out.println("IF 条件表达式:"+this.ifcondition);
		ASTTreeNodeV1_1 node = new ASTTreeNodeV1_1(this.ifcondition,0);
		node.analyseTree();
		node.show();
		VarTableNodeV1_1 varmap = this.getVarmaps();
		node.eval(varmap);
		System.out.println("计算结果："+node.getOperdataresult().isElementBooleanValue());
		
		if(node.getOperdataresult().isElementBooleanValue()) {
			//执行IF block
			this.ifblocknode.execute();
		}else {
			if (this.elseblocknode !=null) {
				this.elseblocknode.execute();
			}
		}
		return true;
	}

}
