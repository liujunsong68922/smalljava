package com.liu.smalljava.v1_1.expression;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import com.liu.smalljava.v1_1.StringFindUtil;
import com.liu.smalljava.v1_1.classtable.ClassTable;
import com.liu.smalljava.v1_1.expression.operelement.BooleanOperElement;
import com.liu.smalljava.v1_1.expression.operelement.DoubleOperElement;
import com.liu.smalljava.v1_1.expression.operelement.FloatOperElement;
import com.liu.smalljava.v1_1.expression.operelement.IntegerOperElement;
import com.liu.smalljava.v1_1.expression.operelement.LongOperElement;
import com.liu.smalljava.v1_1.expression.operelement.OperElementData;
import com.liu.smalljava.v1_1.expression.operelement.StringOperElement;
import com.liu.smalljava.v1_1.vartable.VarTableNodeV1_1;
import com.liu.smalljava.v1_1.vartable.VarValueV1_1;

/**
 * ����һ���ڵ����
 * 
 * @author liujunsong
 *
 */
public class ASTTreeNodeV1_1 {
	// Ҫ���з������ַ�����Ϣ
	String nodeString = "";

	// �ڵ�ļ���
	int nodelevel = 0;

	// �Ƿ񾭹����﷨���� true �Ѿ�������false δ����
	boolean syntaxflag = false;

	// ����ַ�������﷨�����Ժ�����Ĵ�����Ϣ
	String errorinfo = "";

	// �����Ժ�������ӽڵ㣬Ĭ��Ϊnull����
	ArrayList<ASTTreeNodeV1_1> children = null;

	// �жϵ�ǰ����ڵ��ǲ���һ��Ҫ���õı���������ǣ��Ͳ��ɽ��б����滻
	private boolean varflag = false;

	// stringresult����ֻ��һ���������Ч��������ֵʱ���洢������
	String stringresult = null;

	// ������ǲ�����������һ������������д��operdata;
	OperElementData operdataresult = null;

	public OperElementData getOperdataresult() {
		return operdataresult;
	}

	public void setOperdataresult(OperElementData operdataresult) {
		this.operdataresult = operdataresult;
	}

	public boolean isVarflag() {
		return varflag;
	}

	public void setVarflag(boolean varflag) {
		this.varflag = varflag;
	}

	/**
	 * ���캯��������Ҫ�����Ľڵ�����
	 * 
	 * @param sinfo
	 */
	public ASTTreeNodeV1_1(String sinfo, int inodelevel) {
		this.nodeString = sinfo;
		this.nodelevel = inodelevel;
		this.syntaxflag = false;
		this.errorinfo = "";
	}

	/**
	 * ���캯��������Ҫ�����Ľڵ�����
	 * 
	 * @param sinfo
	 */
	public ASTTreeNodeV1_1(String sinfo) {
		this.nodeString = sinfo;
		this.nodelevel = 0;
		this.syntaxflag = false;
		this.errorinfo = "";
	}

	/**
	 * ִ���﷨���ķ���,����������
	 */
	public void analyseTree() {
		ASTTreeNodeAnalyse anlyse = new ASTTreeNodeAnalyse();
		anlyse.setAstnode(this);

		anlyse.doAnalyseTree();
	}
	
	/**
	 * ִ���﷨���ķ��������������ڵ�
	 */
	public boolean analyseOneNode() {
		ASTTreeNodeV1_1 nextnode = this.nextAstNode();
		if(nextnode == null) {
			//���нڵ㶼�Ѿ��������
			System.out.println("ALL NODE is Aynalazed OK.");
			return false;
		}else {
			//��nextnode����һ��ִ��������
			ASTTreeNodeAnalyse nodeanalyse = new ASTTreeNodeAnalyse();
			nodeanalyse.setAstnode(nextnode);
			nodeanalyse.doAnalyseOneNode();
			return true;
		}
	}
	
	public void show() {
		if (this.syntaxflag) {
			if (children != null) {
				for (ASTTreeNodeV1_1 node : this.children) {
					node.show();
				}
				return;
			} else {
				for (int i = 0; i < this.nodelevel; i++) {
					if (i < nodelevel - 1) {
						System.out.print("-----");
					} else {
						System.out.print("|---->");
					}
				}
				System.out.println("" + this.nodeString);
			}
		} else {
			System.out.println("����ʧ��:" + this.nodeString);
		}
	}

	public String getNodeString() {
		return nodeString;
	}

	public void setNodeString(String nodeString) {
		this.nodeString = nodeString;
	}

	public int getNodelevel() {
		return nodelevel;
	}

	public void setNodelevel(int nodelevel) {
		this.nodelevel = nodelevel;
	}

	public boolean isSyntaxflag() {
		return syntaxflag;
	}

	public void setSyntaxflag(boolean syntaxflag) {
		this.syntaxflag = syntaxflag;
	}

	public String getErrorinfo() {
		return errorinfo;
	}

	public void setErrorinfo(String errorinfo) {
		this.errorinfo = errorinfo;
	}

	public ArrayList<ASTTreeNodeV1_1> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<ASTTreeNodeV1_1> children) {
		this.children = children;
	}

	/**
	 * ��AST���ֽ�����Ժ󣬰��ղ������ʽ�ļ�����������μ���booleanֵ�� �����������boolresult����
	 * ������㷢�������ʧ�ܣ�������Ϣ����errorinfo����
	 * 
	 * @return true����ɹ����� false���㷢������
	 */
	public boolean eval(VarTableNodeV1_1 varmap) {
		// ����Ǩ�ƣ�����eval�Ĵ���Ǩ�Ƶ�ASTTreeNodeEval�Ǹ�������ȥ

		// step1:�������������
		ASTTreeNodeEval nodeeval = new ASTTreeNodeEval();
		// step2�ѵ�ǰ���󴫵ݸ������ڶ���
		nodeeval.setAstnode(this);
		// step3.ִ�м��������������ؼ�����
		return nodeeval.eval(varmap);
	}

	private boolean isnumber(String sdata) {
		if (sdata == null || sdata.length() == 0) {
			return false;
		}
		if (sdata.charAt(0) == '-') {
			return true;
		}
		if (sdata.charAt(0) >= '1' && sdata.charAt(0) <= '9') {
			return true;
		}

		return false;
	}

	public String getStringresult() {
		return stringresult;
	}

	public void setStringresult(String stringresult) {
		this.stringresult = stringresult;
	}

	/**
	 * ���粻��һ���Խ�����ϣ���ô��������������ҵ���һ���������Ľڵ�
	 * 
	 * @return
	 */
	public ASTTreeNodeV1_1 nextAstNode() {
		// �����ǰ�ڵ�δ���������ص�ǰ�ڵ�
		if (!this.syntaxflag) {
			return this;
		} else {
			// ��ǰ�ڵ��Լ������������children�������
			if (this.children == null || this.children.size() == 0) {
				// û��children
				return null;
			} else {
				for (ASTTreeNodeV1_1 child : this.children) {
					// �ݹ����
					ASTTreeNodeV1_1 nextnode = child.nextAstNode();
					if (nextnode != null) {
						return nextnode;
					} else {
						// do nothing and continue loop
					}
				}
				// ѭ��������δ�ҵ��������ڵ�
				return null;
			}
		}
	}
}
