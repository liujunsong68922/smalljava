package com.liu.smalljava.v1_1.expression;

import java.util.ArrayList;

import com.liu.smalljava.v1_1.StringFindUtil;

/**
 * ��ASTTreeNode�ڵ����AST�﷨�����Ĺ�����
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
	 * ִ���﷨���ķ���
	 */
	public void doAnalyseTree() {
		if (astnode.syntaxflag) {
			System.out.println("TreeNode has been analysed.");
			System.out.println("�ڵ��Ѿ����﷨������������Ҫ�ٴν��з���");
			return;
		}

		// ���Ӷ�()��������֧��
		if (astnode.nodeString != null && astnode.nodeString.length() > 0) {
			String childstring = astnode.nodeString.trim();
			if (childstring.length() > 0) {
				// ������ʽ��()����������
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

		// ������Ľṹ������﷨�������㷨���£�
		// 1.�ҵ���һ���������ţ�Ȼ�󽫲�����������Ϊһ���ӽڵ㣬����Ҳ��Ϊһ���ӽڵ㣬
		// ������������Ϊ�м�ڵ�
		// ���ǵ������������ȼ����⣬���ҵ����ȼ������Ҹ����ȼ�
		AstOperAndPosV1_1 oap = getFirstOperCode();
		if (oap == null) {
			// û���ҵ��κεķָ���������˵���Ѿ�û�зָ���������
			System.out.println("--->δ�ҵ��κεĲ�����." + astnode.nodeString);
			// ������Ҫ����һ������Ĵ������ǵ��������ĳ��������Ҫ��.��������
			// ������Ҫ���Ӷԡ�.����������֧��
			// ����������з�Ϊ4����
			// 1. ���ö�����ʱ��֧��static ���ã�static���ò��ڱ������У�
			// 2.��.����������
			// 3.�������ƣ���ʱ��֧�ֱ������ʣ�ֻ֧�ֺ������ã��Ȳ����Ƕ�̬ƥ��֧�֣�
			// 4 ������ò���������û�в���
			if (anylyseTree_objectoper()) {
				if (astnode.children == null) {
					System.out.println("������������󡿶�����ý����ɹ�! ��childrenΪnull!");
				} else {
					System.out.println("������ý����ɹ�!" + astnode.children.size());
					astnode.syntaxflag = true;

					// ������Ҫ�ݹ���������з���
					// Ȼ����ѭ��������һ���Ĵ������
					for (ASTTreeNodeV1_1 node : astnode.children) {
						// ������һ���ĵݹ鴦��
						// ������Ҫ����������жϣ������ǰ�ڵ��Ѿ���һ�����������Ͳ�Ҫ��������
						node.analyseTree();
					}
					return;
				}

			} else {
				System.out.println("������ý���ʧ��!");
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
			// ���ⷢ�������ǿ��ַ��������
			astnode.children.add(leftnode);
		}
		astnode.children.add(opernode);
		astnode.children.add(rightnode);

		// ���=����������һ�����⴦��
		if (oap.getOpercode().equals("=")) {
			// ����ı�������Ϊ����
			leftnode.setVarflag(true);
		}

		// Ȼ����ѭ��������һ���Ĵ������
		for (ASTTreeNodeV1_1 node : astnode.children) {
			// ������һ���ĵݹ鴦��
			// ������Ҫ����������жϣ������ǰ�ڵ��Ѿ���һ�����������Ͳ�Ҫ��������
			node.analyseTree();
		}
	}
	
	/**
	 * ִ���﷨���ķ���
	 */
	public void doAnalyseOneNode() {
		if (astnode.syntaxflag) {
			System.out.println("TreeNode has been analysed.");
			System.out.println("�ڵ��Ѿ����﷨������������Ҫ�ٴν��з���");
			return;
		}

		// ���Ӷ�()��������֧��
		if (astnode.nodeString != null && astnode.nodeString.length() > 0) {
			String childstring = astnode.nodeString.trim();
			if (childstring.length() > 0) {
				// ������ʽ��()����������
				if (childstring.startsWith("(") && childstring.endsWith("")) {
					astnode.children = new ArrayList<ASTTreeNodeV1_1>();
					astnode.syntaxflag = true;
					ASTTreeNodeV1_1 childnode = new ASTTreeNodeV1_1(childstring.substring(1, childstring.length() - 1),
							astnode.nodelevel + 1);
					astnode.children.add(childnode);
				}
			}
		}

		// ������Ľṹ������﷨�������㷨���£�
		// 1.�ҵ���һ���������ţ�Ȼ�󽫲�����������Ϊһ���ӽڵ㣬����Ҳ��Ϊһ���ӽڵ㣬
		// ������������Ϊ�м�ڵ�
		// ���ǵ������������ȼ����⣬���ҵ����ȼ������Ҹ����ȼ�
		AstOperAndPosV1_1 oap = getFirstOperCode();
		if (oap == null) {
			// û���ҵ��κεķָ���������˵���Ѿ�û�зָ���������
			System.out.println("--->δ�ҵ��κεĲ�����." + astnode.nodeString);
			// ������Ҫ����һ������Ĵ������ǵ��������ĳ��������Ҫ��.��������
			// ������Ҫ���Ӷԡ�.����������֧��
			// ����������з�Ϊ4����
			// 1. ���ö�����ʱ��֧��static ���ã�static���ò��ڱ������У�
			// 2.��.����������
			// 3.�������ƣ���ʱ��֧�ֱ������ʣ�ֻ֧�ֺ������ã��Ȳ����Ƕ�̬ƥ��֧�֣�
			// 4 ������ò���������û�в���
			if (anylyseTree_objectoper()) {
				if (astnode.children == null) {
					System.out.println("������������󡿶�����ý����ɹ�! ��childrenΪnull!");
				} else {
					System.out.println("������ý����ɹ�!" + astnode.children.size());
					astnode.syntaxflag = true;
					return;
				}
			} else {
				System.out.println("������ý���ʧ��!");
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
			// ���ⷢ�������ǿ��ַ��������
			astnode.children.add(leftnode);
		}
		astnode.children.add(opernode);
		astnode.children.add(rightnode);

		// ���=����������һ�����⴦��
		if (oap.getOpercode().equals("=")) {
			// ����ı�������Ϊ����
			leftnode.setVarflag(true);
		}
	}	
	
	/**
	 * ���룺nodeString ��������ҵ��ĵ�һ�������������ղ����������ȼ��������ң�
	 * 
	 * @return
	 */
	private AstOperAndPosV1_1 getFirstOperCode() {
		// �������Ҫ���ҵ������Ѿ���һ�������������˳�����
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

		// �Ҳ���������������һ����ֵ
		return null;
	}
	
	/**
	 * ��������ý�����AST���Ľڵ�
	 * 
	 * @return �����ɹ�������ture,����ʧ�ܣ�����false
	 */
	private boolean anylyseTree_objectoper() {
		// ȥ��ǰ��Ŀո�
		astnode.nodeString = astnode.nodeString.trim();

		// �����ж��ַ�������û�С�.��������
		StringFindUtil util = new StringFindUtil();

		int ipos = util.findfirstStringForAST(astnode.nodeString, ".");
		if (ipos == -1) {
			// �Ҳ���������������Ƕ�������,����
			System.out.println("�Ҳ����������");
			return true;
		}

		int ileftpos = astnode.nodeString.indexOf("(");
		if (ileftpos == -1) {
			// �Ҳ��������ţ���������
			System.out.println("AST�������󣬶�����ã�ȴû���ҵ�������." + astnode.nodeString);
			astnode.errorinfo = "AST�������󣬶�����ã�ȴû���ҵ�������.";
			return false;
		}

		if (!astnode.nodeString.endsWith(")")) {
			// �Ҳ��������ţ���������
			System.out.println("AST�������󣬶�����ã�ȴû���ҵ�������." + astnode.nodeString);
			astnode.errorinfo = "AST�������󣬶�����ã�ȴû���ҵ�������.";
			return false;
		}

		// ��������
		String objname = astnode.nodeString.substring(0, ipos);

		String methodname = astnode.nodeString.substring(ipos + 1, ileftpos);
		methodname = methodname.trim();

		String args = astnode.nodeString.substring(ileftpos + 1, astnode.nodeString.length() - 1);
		// �����ö��Ž��зָ�
		String arg[] = args.split(",");
		astnode.children = new ArrayList<ASTTreeNodeV1_1>();
		astnode.syntaxflag = true;

		// ��һ���ڵ�
		ASTTreeNodeV1_1 objnode = new ASTTreeNodeV1_1(objname, astnode.nodelevel + 1);
		astnode.children.add(objnode);

		// �ڶ����ڵ�
		ASTTreeNodeV1_1 dotnode = new ASTTreeNodeV1_1(".", astnode.nodelevel + 1);
		astnode.children.add(dotnode);

		// �������ڵ�
		// �����ӽڵ�ʱ��Ϊ�˷�ֹ����ظ����㣬���ַ�����ʽд��
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
