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
 * 树的一个节点对象
 * 
 * @author liujunsong
 *
 */
public class ASTTreeNodeV1_1 {
	// 要进行分析的字符串信息
	String nodeString = "";

	// 节点的级别
	int nodelevel = 0;

	// 是否经过了语法分析 true 已经分析，false 未分析
	boolean syntaxflag = false;

	// 这个字符串存放语法分析以后产生的错误信息
	String errorinfo = "";

	// 分析以后产生的子节点，默认为null对象
	ArrayList<ASTTreeNodeV1_1> children = null;

	// 判断当前这个节点是不是一个要设置的变量，如果是，就不可进行变量替换
	private boolean varflag = false;

	// stringresult现在只在一种情况下有效：变量赋值时，存储变量名
	String stringresult = null;

	// 如果不是操作符，而是一个操作数，则写入operdata;
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
	 * 构造函数，传入要解析的节点数据
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
	 * 构造函数，传入要解析的节点数据
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
	 * 执行语法树的分析,分析整颗树
	 */
	public void analyseTree() {
		ASTTreeNodeAnalyse anlyse = new ASTTreeNodeAnalyse();
		anlyse.setAstnode(this);

		anlyse.doAnalyseTree();
	}
	
	/**
	 * 执行语法树的分析，分析单个节点
	 */
	public boolean analyseOneNode() {
		ASTTreeNodeV1_1 nextnode = this.nextAstNode();
		if(nextnode == null) {
			//所有节点都已经分析完成
			System.out.println("ALL NODE is Aynalazed OK.");
			return false;
		}else {
			//将nextnode构建一个执行器出来
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
			System.out.println("分析失败:" + this.nodeString);
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
	 * 在AST树分解完成以后，按照布尔表达式的计算规则来依次计算boolean值， 计算结果存放在boolresult里面
	 * 如果计算发生错误和失败，错误信息放在errorinfo里面
	 * 
	 * @return true计算成功计算 false计算发生错误
	 */
	public boolean eval(VarTableNodeV1_1 varmap) {
		// 代码迁移：所有eval的代码迁移到ASTTreeNodeEval那个类里面去

		// step1:定义计算器对象
		ASTTreeNodeEval nodeeval = new ASTTreeNodeEval();
		// step2把当前对象传递给计算期对象
		nodeeval.setAstnode(this);
		// step3.执行计算评估，并返回计算结果
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
	 * 假如不是一次性解析完毕，那么利用这个方法来找到下一个待分析的节点
	 * 
	 * @return
	 */
	public ASTTreeNodeV1_1 nextAstNode() {
		// 如果当前节点未分析，返回当前节点
		if (!this.syntaxflag) {
			return this;
		} else {
			// 当前节点以及分析过，则从children里面查找
			if (this.children == null || this.children.size() == 0) {
				// 没有children
				return null;
			} else {
				for (ASTTreeNodeV1_1 child : this.children) {
					// 递归调用
					ASTTreeNodeV1_1 nextnode = child.nextAstNode();
					if (nextnode != null) {
						return nextnode;
					} else {
						// do nothing and continue loop
					}
				}
				// 循环结束，未找到待分析节点
				return null;
			}
		}
	}
}
