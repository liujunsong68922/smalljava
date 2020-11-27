package com.liu.smalljava.v1_1.block;

import com.liu.smalljava.v1_1.expression.ASTTreeNodeV1_1;
import com.liu.smalljava.v1_1.vartable.VarTableNodeV1_1;

public class IfBlockNodeV1_1 extends BaseBlockNodeV1_1 {
	/**
	 * IF���ж�����������ж�������һ���ַ���������
	 */
	private String ifcondition;
	/**
	 * if����Ϊ��ʱ��������
	 */
	private BaseBlockNodeV1_1 ifblocknode;
	/**
	 * if����Ϊ��ʱ��������
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
	 * if�ڵ�ķ���
	 */
	public boolean anylyse() {
		if (this.ifblocknode != null) {
			this.ifblocknode.anylyse();
			System.out.println("if�Ӿ�����ɹ�");
		}
		if (this.elseblocknode != null) {
			this.elseblocknode.anylyse();
			System.out.println("else�Ӿ�����ɹ�.");
		}
		return true;
	}
	
	public boolean execute() {
		//IF NODE��ִ�й������£��ȼ���if���ʽ��ֵ
		System.out.println("IF �������ʽ:"+this.ifcondition);
		ASTTreeNodeV1_1 node = new ASTTreeNodeV1_1(this.ifcondition,0);
		node.analyseTree();
		node.show();
		VarTableNodeV1_1 varmap = this.getVarmaps();
		node.eval(varmap);
		System.out.println("��������"+node.getOperdataresult().isElementBooleanValue());
		
		if(node.getOperdataresult().isElementBooleanValue()) {
			//ִ��IF block
			this.ifblocknode.execute();
		}else {
			if (this.elseblocknode !=null) {
				this.elseblocknode.execute();
			}
		}
		return true;
	}

}
