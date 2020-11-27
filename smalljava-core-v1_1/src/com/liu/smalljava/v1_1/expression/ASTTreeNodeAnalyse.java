package com.liu.smalljava.v1_1.expression;

import java.util.ArrayList;

import com.liu.smalljava.v1_1.StringFindUtil;

/**
 * 对ASTTreeNode节点进行AST语法分析的功能类
 * @author liujunsong
 *
 */
public class ASTTreeNodeAnalyse {
	private ASTTreeNodeV1_1 astnode;

	public ASTTreeNodeV1_1 getAstnode() {
		return astnode;
	}

	public void setAstnode(ASTTreeNodeV1_1 _astnode) {
		astnode = _astnode;
	}
	
	/**
	 * 执行语法树的分析
	 */
	public void doAnalyseTree() {
		if (astnode.syntaxflag) {
			System.out.println("TreeNode has been analysed.");
			System.out.println("节点已经被语法分析过，不需要再次进行分析");
			return;
		}

		// 增加对()操作符的支持
		if (astnode.nodeString != null && astnode.nodeString.length() > 0) {
			String childstring = astnode.nodeString.trim();
			if (childstring.length() > 0) {
				// 这个表达式被()包含起来了
				if (childstring.startsWith("(") && childstring.endsWith("")) {
					astnode.children = new ArrayList<ASTTreeNodeV1_1>();
					astnode.syntaxflag = true;
					ASTTreeNodeV1_1 childnode = new ASTTreeNodeV1_1(childstring.substring(1, childstring.length() - 1),
							astnode.nodelevel + 1);
					astnode.children.add(childnode);
					childnode.analyseTree();
				}
			}
		}

		// 对输入的结构块进行语法分析的算法如下：
		// 1.找到第一个操作符号，然后将操作符左面作为一个子节点，右面也作为一个子节点，
		// 操作符本身作为中间节点
		// 考虑到操作符的优先级问题，先找低优先级，再找高优先级
		AstOperAndPosV1_1 oap = getFirstOperCode();
		if (oap == null) {
			// 没有找到任何的分隔操作符，说明已经没有分隔操作符了
			System.out.println("--->未找到任何的操作符." + astnode.nodeString);
			// 这里需要增加一段特殊的处理，考虑到面向对象的程序调用需要【.】操作符
			// 这里需要增加对【.】操作符的支持
			// 将对象调用切分为4部分
			// 1. 调用对象（暂时不支持static 调用，static调用不在变量表中）
			// 2.【.】操作符号
			// 3.函数名称（暂时不支持变量访问，只支持函数调用，先不考虑多态匹配支持）
			// 4 多个调用参数，可能没有参数
			if (anylyseTree_objectoper()) {
				if (astnode.children == null) {
					System.out.println("【对象解析错误】对象调用解析成功! 但children为null!");
				} else {
					System.out.println("对象调用解析成功!" + astnode.children.size());
					astnode.syntaxflag = true;

					// 这里需要递归调用来进行分析
					// 然后再循环进行下一级的处理调用
					for (ASTTreeNodeV1_1 node : astnode.children) {
						// 进行下一级的递归处理
						// 这里需要进行特殊的判断，如果当前节点已经是一个操作符，就不要再深入了
						node.analyseTree();
					}
					return;
				}

			} else {
				System.out.println("对象调用解析失败!");
				astnode.syntaxflag = false;
				return;
			}
			astnode.syntaxflag = true;
			return;
		}

		String leftstring = astnode.nodeString.substring(0, oap.ipos);
		leftstring = leftstring.trim();
		String rightString = astnode.nodeString.substring(oap.ipos + oap.opercode.length());
		rightString = rightString.trim();

		ASTTreeNodeV1_1 leftnode = new ASTTreeNodeV1_1(leftstring, astnode.nodelevel + 1);
		ASTTreeNodeV1_1 opernode = new ASTTreeNodeV1_1(oap.opercode, astnode.nodelevel + 1);
		ASTTreeNodeV1_1 rightnode = new ASTTreeNodeV1_1(rightString, astnode.nodelevel + 1);
		astnode.children = new ArrayList<ASTTreeNodeV1_1>();
		astnode.syntaxflag = true;
		if (leftstring != null && leftstring.length() > 0) {
			// 避免发生左面是空字符串的情况
			astnode.children.add(leftnode);
		}
		astnode.children.add(opernode);
		astnode.children.add(rightnode);

		// 针对=操作符进行一次特殊处理
		if (oap.getOpercode().equals("=")) {
			// 左面的变量设置为变量
			leftnode.setVarflag(true);
		}

		// 然后再循环进行下一级的处理调用
		for (ASTTreeNodeV1_1 node : astnode.children) {
			// 进行下一级的递归处理
			// 这里需要进行特殊的判断，如果当前节点已经是一个操作符，就不要再深入了
			node.analyseTree();
		}
	}
	
	/**
	 * 执行语法树的分析
	 */
	public void doAnalyseOneNode() {
		if (astnode.syntaxflag) {
			System.out.println("TreeNode has been analysed.");
			System.out.println("节点已经被语法分析过，不需要再次进行分析");
			return;
		}

		// 增加对()操作符的支持
		if (astnode.nodeString != null && astnode.nodeString.length() > 0) {
			String childstring = astnode.nodeString.trim();
			if (childstring.length() > 0) {
				// 这个表达式被()包含起来了
				if (childstring.startsWith("(") && childstring.endsWith("")) {
					astnode.children = new ArrayList<ASTTreeNodeV1_1>();
					astnode.syntaxflag = true;
					ASTTreeNodeV1_1 childnode = new ASTTreeNodeV1_1(childstring.substring(1, childstring.length() - 1),
							astnode.nodelevel + 1);
					astnode.children.add(childnode);
				}
			}
		}

		// 对输入的结构块进行语法分析的算法如下：
		// 1.找到第一个操作符号，然后将操作符左面作为一个子节点，右面也作为一个子节点，
		// 操作符本身作为中间节点
		// 考虑到操作符的优先级问题，先找低优先级，再找高优先级
		AstOperAndPosV1_1 oap = getFirstOperCode();
		if (oap == null) {
			// 没有找到任何的分隔操作符，说明已经没有分隔操作符了
			System.out.println("--->未找到任何的操作符." + astnode.nodeString);
			// 这里需要增加一段特殊的处理，考虑到面向对象的程序调用需要【.】操作符
			// 这里需要增加对【.】操作符的支持
			// 将对象调用切分为4部分
			// 1. 调用对象（暂时不支持static 调用，static调用不在变量表中）
			// 2.【.】操作符号
			// 3.函数名称（暂时不支持变量访问，只支持函数调用，先不考虑多态匹配支持）
			// 4 多个调用参数，可能没有参数
			if (anylyseTree_objectoper()) {
				if (astnode.children == null) {
					System.out.println("【对象解析错误】对象调用解析成功! 但children为null!");
				} else {
					System.out.println("对象调用解析成功!" + astnode.children.size());
					astnode.syntaxflag = true;
					return;
				}
			} else {
				System.out.println("对象调用解析失败!");
				astnode.syntaxflag = true;
				return;
			}
			astnode.syntaxflag = true;
			return;
		}

		String leftstring = astnode.nodeString.substring(0, oap.ipos);
		leftstring = leftstring.trim();
		String rightString = astnode.nodeString.substring(oap.ipos + oap.opercode.length());
		rightString = rightString.trim();

		ASTTreeNodeV1_1 leftnode = new ASTTreeNodeV1_1(leftstring, astnode.nodelevel + 1);
		ASTTreeNodeV1_1 opernode = new ASTTreeNodeV1_1(oap.opercode, astnode.nodelevel + 1);
		ASTTreeNodeV1_1 rightnode = new ASTTreeNodeV1_1(rightString, astnode.nodelevel + 1);
		astnode.children = new ArrayList<ASTTreeNodeV1_1>();
		astnode.syntaxflag = true;
		if (leftstring != null && leftstring.length() > 0) {
			// 避免发生左面是空字符串的情况
			astnode.children.add(leftnode);
		}
		astnode.children.add(opernode);
		astnode.children.add(rightnode);

		// 针对=操作符进行一次特殊处理
		if (oap.getOpercode().equals("=")) {
			// 左面的变量设置为变量
			leftnode.setVarflag(true);
		}
	}	
	
	/**
	 * 输入：nodeString 输出：查找到的第一个操作符（按照操作符的优先级别来查找）
	 * 
	 * @return
	 */
	private AstOperAndPosV1_1 getFirstOperCode() {
		// 如果本身要查找的内容已经是一个操作符，则退出查找
		if (astnode.nodeString.equals("&&") 
				|| astnode.nodeString.equals("||") 
				|| astnode.nodeString.equals("==") 
				|| astnode.nodeString.equals(">=")
				|| astnode.nodeString.equals("<=")) {
			return null;
		}

		String opers[] = new String[] { "&&", "||", "==", ">=", "<=", ">", "<", "=", "+", "-", "*", "/" };

		int i = 0;
		StringFindUtil util = new StringFindUtil();
		for (i = 0; i < opers.length; i++) {
			int ipos = util.findfirstStringForAST(astnode.nodeString, opers[i]);
			if (ipos >= 0) {
				AstOperAndPosV1_1 oap = new AstOperAndPosV1_1();
				oap.setIpos(ipos);
				oap.setOpercode(opers[i]);
				return oap;
			}
		}

		// 找不到操作符，返回一个空值
		return null;
	}
	
	/**
	 * 将对象调用解析成AST树的节点
	 * 
	 * @return 解析成功，返回ture,解释失败，返回false
	 */
	private boolean anylyseTree_objectoper() {
		// 去掉前后的空格
		astnode.nodeString = astnode.nodeString.trim();

		// 首先判断字符串中有没有【.】操作符
		StringFindUtil util = new StringFindUtil();

		int ipos = util.findfirstStringForAST(astnode.nodeString, ".");
		if (ipos == -1) {
			// 找不到点操作符，不是对象引用,返回
			System.out.println("找不到点操作符");
			return true;
		}

		int ileftpos = astnode.nodeString.indexOf("(");
		if (ileftpos == -1) {
			// 找不到左括号，按错误处理
			System.out.println("AST分析错误，对象调用，却没有找到左括号." + astnode.nodeString);
			astnode.errorinfo = "AST分析错误，对象调用，却没有找到左括号.";
			return false;
		}

		if (!astnode.nodeString.endsWith(")")) {
			// 找不到右括号，按错误处理
			System.out.println("AST分析错误，对象调用，却没有找到右括号." + astnode.nodeString);
			astnode.errorinfo = "AST分析错误，对象调用，却没有找到右括号.";
			return false;
		}

		// 对象名称
		String objname = astnode.nodeString.substring(0, ipos);

		String methodname = astnode.nodeString.substring(ipos + 1, ileftpos);
		methodname = methodname.trim();

		String args = astnode.nodeString.substring(ileftpos + 1, astnode.nodeString.length() - 1);
		// 参数用逗号进行分隔
		String arg[] = args.split(",");
		astnode.children = new ArrayList<ASTTreeNodeV1_1>();
		astnode.syntaxflag = true;

		// 第一个节点
		ASTTreeNodeV1_1 objnode = new ASTTreeNodeV1_1(objname, astnode.nodelevel + 1);
		astnode.children.add(objnode);

		// 第二个节点
		ASTTreeNodeV1_1 dotnode = new ASTTreeNodeV1_1(".", astnode.nodelevel + 1);
		astnode.children.add(dotnode);

		// 第三个节点
		// 生成子节点时，为了防止多次重复计算，以字符串格式写入
		methodname = "\"" + methodname + "\"";
		ASTTreeNodeV1_1 methodnode = new ASTTreeNodeV1_1(methodname, astnode.nodelevel + 1);
		astnode.children.add(methodnode);

		for (String arg1 : arg) {
			if (arg1 != null && arg1.length() > 0) {
				ASTTreeNodeV1_1 argnode = new ASTTreeNodeV1_1(arg1, astnode.nodelevel + 1);
				astnode.children.add(argnode);
			}
		}

		return true;
	}		
}
