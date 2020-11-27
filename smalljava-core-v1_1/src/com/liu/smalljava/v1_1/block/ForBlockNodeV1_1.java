package com.liu.smalljava.v1_1.block;

import com.liu.smalljava.v1_1.expression.ASTTreeNodeV1_1;
import com.liu.smalljava.v1_1.vartable.VarTableNodeV1_1;

/**
 * for����Ӧ��Block�ڵ�
 * @author liujunsong
 *
 */
public class ForBlockNodeV1_1 extends BaseBlockNodeV1_1 {
	private BaseBlockNodeV1_1 beginNode;
	private String forcondition;
	private BaseBlockNodeV1_1 loopNode;
	private BaseBlockNodeV1_1 fornode;
	public BaseBlockNodeV1_1 getBeginNode() {
		return beginNode;
	}
	public void setBeginNode(BaseBlockNodeV1_1 beginNode) {
		this.beginNode = beginNode;
	}
	public String getForcondition() {
		return forcondition;
	}
	public void setForcondition(String forcondition) {
		this.forcondition = forcondition;
	}
	public BaseBlockNodeV1_1 getLoopNode() {
		return loopNode;
	}
	public void setLoopNode(BaseBlockNodeV1_1 loopNode) {
		this.loopNode = loopNode;
	}
	public BaseBlockNodeV1_1 getFornode() {
		return fornode;
	}
	public void setFornode(BaseBlockNodeV1_1 fornode) {
		this.fornode = fornode;
	}
	
	private String forstring;
	public String getForstring() {
		return forstring;
	}
	public void setForstring(String forstring) {
		this.forstring = forstring;
	}
	
	/**
	 * for�ڵ�ķ���
	 */
	public boolean anylyse() {
		this.beginNode.anylyse();
		this.loopNode.anylyse();
		this.fornode.anylyse();
		System.out.println("for�Ӿ�����ɹ�");
		return true;
	}
	
	public boolean execute() {
		System.out.println("---------->ִ��for�ڵ�");

		//step1:ִ�п�ʼ�ڵ�
		this.beginNode.execute();
		//step2:ִ���ж�����
		System.out.println("while �������ʽ:" + this.forcondition);
		ASTTreeNodeV1_1 node = new ASTTreeNodeV1_1(this.forcondition, 0);
		node.analyseTree();
		node.show();
		VarTableNodeV1_1 varmap = this.getVarmaps();
		node.eval(varmap);
		System.out.println("for������������" + node.getOperdataresult().isElementBooleanValue());
		
		while (node.getOperdataresult().isElementBooleanValue()) {
			this.fornode.execute();
			//ִ��for�Ժ��ѭ���ݹ����
			this.loopNode.execute();
			
			//���¼����ж�����
			node = new ASTTreeNodeV1_1(this.forcondition, 0);
			node.analyseTree();
			node.show();
			varmap = this.getVarmaps();
			node.eval(varmap);
			System.out.println("for������������" +node.getOperdataresult().isElementBooleanValue());
		}
		
		return true;
	}
}
