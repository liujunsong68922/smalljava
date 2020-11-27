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
	 * while�ڵ�ķ���
	 */
	public boolean anylyse() {
		this.whilenode.anylyse();
		System.out.println("while�Ӿ�����ɹ�");
		return true;
	}
	
	public boolean execute() {
		// IF NODE��ִ�й������£��ȼ���if���ʽ��ֵ
		System.out.println("while �������ʽ:" + this.whilecondition);
		ASTTreeNodeV1_1 node = new ASTTreeNodeV1_1(this.whilecondition, 0);
		node.analyseTree();
		node.show();
		VarTableNodeV1_1 varmap = this.getVarmaps();
		node.eval(varmap);
		System.out.println("while������������" + node.getOperdataresult().isElementBooleanValue());

		while (node.getOperdataresult().isElementBooleanValue()) {
			// ִ��while block
			this.whilenode.execute();
			node = new ASTTreeNodeV1_1(this.whilecondition, 0);
			node.analyseTree();
			node.show();
			varmap = this.getVarmaps();
			node.eval(varmap);
			System.out.println("while������������" + node.getOperdataresult().isElementBooleanValue());
			if(node.getOperdataresult().isElementBooleanValue()) {
				System.out.println("while ����Ϊ��");
			}else {
				System.out.println("while ����Ϊ�٣�ѭ���˳�.");
			}
		}
		return true;
	}
}
