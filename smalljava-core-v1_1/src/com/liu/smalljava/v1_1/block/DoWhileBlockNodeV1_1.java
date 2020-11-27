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
	 * while�ڵ�ķ���
	 */
	public boolean anylyse() {
		this.donode.anylyse();
		System.out.println("do�Ӿ�����ɹ�");
		return true;
	}
	
	public boolean execute() {
		//��ִ��һ��do���
		this.donode.execute();
		
		// IF NODE��ִ�й������£��ȼ���if���ʽ��ֵ
		System.out.println("while �������ʽ:" + this.whilestring);
		ASTTreeNodeV1_1 node = new ASTTreeNodeV1_1(this.whilestring, 0);
		node.analyseTree();
		node.show();
		VarTableNodeV1_1 varmap = this.getVarmaps();
		node.eval(varmap);
		System.out.println("while������������" + node.getOperdataresult().isElementBooleanValue());
		
		while (node.getOperdataresult().isElementBooleanValue()) {
			// ִ��while block
			this.donode.execute();
			node = new ASTTreeNodeV1_1(this.whilestring, 0);
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
