package com.liu.smalljava.v1_1.block;

import java.util.ArrayList;
import java.util.HashMap;

import com.liu.smalljava.v1_1.StringFindUtil;
import com.liu.smalljava.v1_1.expression.ASTTreeNodeV1_1;
import com.liu.smalljava.v1_1.vartable.VarTableNodeV1_1;

public class BaseBlockNodeV1_1 extends AbstractBlockV1_1 {
	// ���ر�����
	private VarTableNodeV1_1 varmaps = new VarTableNodeV1_1(null);

	public VarTableNodeV1_1 getVarmaps() {
		return varmaps;
	}

	public void setVarmaps(VarTableNodeV1_1 varmaps) {
		this.varmaps = varmaps;
	}

	public void setParentVarmaps(VarTableNodeV1_1 parentvarmaps) {
		if (this.varmaps == null) {
			System.out.println("������ô��󣬵�ǰ�ڵ�varmaps is null");
			return;
		} else {
			// ���õ�ǰ�ڵ���ϼ��ڵ�
			if (this.varmaps.equals(parentvarmaps)) {
				// ���������ѭ��
				System.out.println("������ô��󣬵�ǰ�ڵ�varmaps��parent�ڵ��ظ�!");
				return;
			} else {
				// �����parentvarmaps������һ��nullֵ����
				this.varmaps.setParentnode(parentvarmaps);
			}
		}
	}

	// ʵ�ʼ����õ�computecontent;
	// ԭʼ��blockcontent���ɱ䣬����ʱʹ��computecontent
	private String computecontent;

	/**
	 * �ֽ��Ժ�õ����ӽڵ�
	 */
	private ArrayList<BaseBlockNodeV1_1> children = null;

	/**
	 * ���캯��
	 * 
	 * @param blockcontent
	 * @param blocklevel
	 * @param blocktype
	 */
	public BaseBlockNodeV1_1(String blockcontent, int blocklevel, String blocktype) {
		this.setBlockcontent(blockcontent);
		this.setBlocklevel(blocklevel);
		this.setBlocktype(blocktype);
		this.setChildren(null);
	}

	/**
	 * Ĭ�ϵĹ��캯��
	 */
	public BaseBlockNodeV1_1() {

	}

	/**
	 * ��blockcontent�ֽ⵽children����ȥ
	 */
	public boolean anylyse() {
		// ���Ƚ�blockcontent�������ҽ���ѭ�������в��ҷ����������зַ��Ͻ��зָ�
		this.computecontent = this.getBlockcontent();
		if (this.computecontent == null) {
			System.out.println("Ҫ����Ŀ��ַ���Ϊnull");
			// ��������ʧ��
			return false;
		}
		// ȥ��Ҫ�����ַ�����ǰ��ո�
		this.computecontent = this.trimReturnAndSpace(this.computecontent);
		System.out.println("------>" + this.computecontent);
		// ���ʣ�����һ�����ַ������򷵻�
		if (this.computecontent.length() == 0) {
			// ȥ����Ч�ַ��Ժ�Ŀհ��ַ���������д���
			this.setBlocktype("emptyblock");
			return true;
		}

		// ѭ���жϲ�����һ�������
		// ԭ����Ҫ�зֵ�����Ҫ��������ݶ��������
		while (this.computecontent.length() > 0) {
			// ȥ��Ҫ�����ַ�����ǰ��ո�ͻس�����
			this.computecontent = this.trimReturnAndSpace(this.computecontent);
			// System.out.println("------>" + this.computecontent);

			// ���Դ�computecontent�����õ�һ���ؼ���
			BlockTokenV1_1 firsttoken = this.getNextBlockToken();
			if (firsttoken == null) {
				System.out.println("��������Ҫ����Ŀ��ַ�����δ�ҵ��ؼ��ʣ�" + this.computecontent);
				System.out.println("����ַ�����һ��Ҫ���ձ��ʽ�����д���." + this.computecontent);
				return true;
			}

			if (firsttoken.getIpos() > 0 && !firsttoken.getToken().equals(";")) {
				System.out
						.println("�������󣬵�һ��token֮ǰ����Ч����:" + this.computecontent.substring(0, firsttoken.getIpos() + 1));
				return true;
			}

			// �жϲ���ʼ��children
			if (this.getChildren() == null) {
				ArrayList<BaseBlockNodeV1_1> children = new ArrayList<BaseBlockNodeV1_1>();
				this.setChildren(children);
			}

			// ----------------------------------------------
			// ��ʼ���а����ض��ָ����ŵĴ�����
			// ----------------------------------------------

			// ����ҵ��ĵ�һ����ָ������ǡ�;��
			// �����ӳ���������;���зָ�����
			if (firsttoken.getToken().equals(";")) {
				this.analyse_semicolon(firsttoken.getIpos());
				continue;
			}

			// ����ҵ��ĵ�һ����ָ������ǡ�{��
			if (firsttoken.getToken().equals("{")) {
				System.out.println("��ʼ��������Ĵ�����{");
				if (this.analyse_brace()) {
					// do nothing
					System.out.println("������{���ɹ�");
				} else {
					System.out.println("������{��ʧ��");
					return false;
				}
			}

			// ����ҵ��ĵ�һ��token�ǡ�if��
			if (firsttoken.getToken().equals("if")) {
				System.out.println("��ʼ����if�ؼ���");
				if (this.analyse_if()) {
					// do nothing
					System.out.println("����IF���ɹ�.");
				} else {
					// �������ifʧ��
					System.out.println("����IF���ʧ��.");
					return false;
				}
			}

			// ����ҵ��ĵ�һ��token�ǡ�for��
			if (firsttoken.getToken().equals("for")) {
				System.out.println("��ʼ����for�ؼ���");
				if (this.analyse_for()) {
					// do nothing
					System.out.println("����for���ɹ�.");
				} else {
					// �������ifʧ��
					System.out.println("����for���ʧ��.");
					return false;
				}
			}

			// ����ҵ��ĵ�һ��token�ǡ�while��
			if (firsttoken.getToken().equals("while")) {
				System.out.println("��ʼ����while�ؼ���");
				if (this.analyse_while()) {
					// do nothing
					System.out.println("����while���ɹ�.");
				} else {
					// �������ifʧ��
					System.out.println("����while���ʧ��.");
					return false;
				}
			}

			// ����ҵ��ĵ�һ��token�ǡ�do��
			if (firsttoken.getToken().equals("do")) {
				System.out.println("��ʼ����do�ؼ���");
				if (this.analyse_do()) {
					// do nothing
					System.out.println("����do���ɹ�.");
				} else {
					// �������ifʧ��
					System.out.println("����do���ʧ��.");
					return false;
				}
			}
		}

		// ���е��ַ����Ѿ�����������ˣ�������Ҫ�ݹ���е�����
		System.out.println("��ʼ�����ӽڵ��Ĵ���------>length:" + this.getChildren().size());
		for (BaseBlockNodeV1_1 child : this.getChildren()) {

			// ���Էֽ�����ӽڵ�
			if (child.anylyse()) {
				System.out.println("�ӽڵ�����ɹ�");
				// �����ֽ����
				continue;
			} else {
				// ��ֹ���㣬�ֽⷢ��������Ҫ�˹����д���
				System.out.println("�ӽڵ�����������󡣡���");
				return false;
			}
		}
		return true;
	}

	/**
	 * ��������λ�õ�Token����
	 */
	private BlockTokenV1_1 getNextBlockToken() {
		StringFindUtil util = new StringFindUtil();
		int ipos1 = util.findfirstStringForBlock(computecontent, ";");
		int ipos2 = util.findfirstStringForBlock(computecontent, "if");
		int ipos3 = util.findfirstStringForBlock(computecontent, "for");
		int ipos4 = util.findfirstStringForBlock(computecontent, "do");
		int ipos5 = util.findfirstStringForBlock(computecontent, "while");
		int ipos6 = util.findfirstStringForBlock(computecontent, "{");

		int ipos = -1;
		String strtoken = "";
		if (ipos1 >= 0 && (ipos1 < ipos || ipos == -1)) {
			ipos = ipos1;
			strtoken = ";";
		}
		if (ipos2 >= 0 && (ipos2 < ipos || ipos == -1)) {
			ipos = ipos2;
			strtoken = "if";
		}
		if (ipos3 >= 0 && (ipos3 < ipos || ipos == -1)) {
			ipos = ipos3;
			strtoken = "for";
		}
		if (ipos4 >= 0 && (ipos4 < ipos || ipos == -1)) {
			ipos = ipos4;
			strtoken = "do";
		}
		if (ipos5 >= 0 && (ipos5 < ipos || ipos == -1)) {
			ipos = ipos5;
			strtoken = "while";
		}
		if (ipos6 >= 0 && (ipos6 < ipos || ipos == -1)) {
			ipos = ipos6;
			strtoken = "{";
		}

		if (ipos == -1) {
			return null;
		} else {
			BlockTokenV1_1 token = new BlockTokenV1_1();
			token.setIpos(ipos);
			token.setToken(strtoken);
			return token;
		}
	}

	public String getComputecontent() {
		return computecontent;
	}

	public void setComputecontent(String computecontent) {
		this.computecontent = computecontent;
	}

	public ArrayList<BaseBlockNodeV1_1> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<BaseBlockNodeV1_1> children) {
		this.children = children;
	}

	/**
	 * ���ַ�����ʼ�ͽ���λ�õ�\r\n ,\r,�ո񶼹��˵�
	 * 
	 * @param strinput
	 * @return
	 */
	private String trimReturnAndSpace(String strinput) {
		return new StringFindUtil().trimReturnAndSpace(strinput);
	}

	// ���������if���ؼ���
	// ��if���ؼ��ʵ��﷨�������£�
	// if(ifcondition){
	// statement1;
	// }else{
	// statement2;
	// }

	private boolean analyse_if() {
		StringFindUtil util = new StringFindUtil();
		int ipos1 = util.findfirstStringForBlock(this.computecontent, "(");
		if (ipos1 == -1) {
			System.out.println("if����������û���ҵ�(");
			return false;
		}
		int ipos2 = util.findfirstStringForBlock(this.computecontent, ")");
		if (ipos2 == -1) {
			System.out.println("if����������û���ҵ�)");
			return false;
		}

		IfBlockNodeV1_1 ifnode = new IfBlockNodeV1_1();
		// Ifnode ��Ҫ����һ��������varmaps,ָ�򱾽ڵ���Ϊ��������
		ifnode.setParentVarmaps(this.varmaps);

		ifnode.setChildren(new ArrayList<BaseBlockNodeV1_1>());
		// ����IF�ڵ���жϱ��ʽ����
		ifnode.setIfcondition(this.computecontent.substring(ipos1 + 1, ipos2));

		// sinfo���г�if + ifcondition�Ĳ���
		String sinfo = this.computecontent.substring(ipos2 + 1);
		// ȥ���ո�ͻس�����
		sinfo = this.trimReturnAndSpace(sinfo);
		if (sinfo.charAt(0) == '{') {
			// ���if������ٵ���{������Ҷ�Ӧ��}�ַ���
			int ipos3 = util.findfirstStringForBlock(sinfo, "}");
			if (ipos3 == -1) {
				System.out.println("����}ʧ��:" + sinfo);
				return false;
			}
			String sifblock = sinfo.substring(0, ipos3 + 1);
			BaseBlockNodeV1_1 ifnode1 = new BaseBlockNodeV1_1(sifblock, this.getBlocklevel() + 1, "");
			// ����ifnode1��varmapsָ��IFnode,�����������
			ifnode1.setVarmaps(ifnode.getVarmaps());

			ifnode.setIfblocknode(ifnode1);

			ifnode.getChildren().add(ifnode1);
			sinfo = sinfo.substring(ipos3 + 1);
		} else {
			int ipos4 = util.findfirstStringForBlock(sinfo, ";");
			if (ipos4 == -1) {
				System.out.println("����;ʧ��" + sinfo);
				return false;
			}
			String sifblock = sinfo.substring(0, ipos4);
			BaseBlockNodeV1_1 ifnode1 = new BaseBlockNodeV1_1(sifblock, this.getBlocklevel() + 1, "");
			// ����ifnode1��varmapsָ��IFnode,�����������
			ifnode1.setVarmaps(ifnode.getVarmaps());

			ifnode.setIfblocknode(ifnode1);
			ifnode.getChildren().add(ifnode1);
			sinfo = sinfo.substring(ipos4 + 1);
		}

		this.computecontent = sinfo;

		// �������ж��ǲ���else�ؼ���
		sinfo = this.trimReturnAndSpace(sinfo);
		if (sinfo.length() > 3) {
			if (sinfo.startsWith("else")) {
				// ����else�Ӿ�,��ʱ�����ǲ���{}�����
				// Ҫ��else�Ӿ����{}�����зָ�
				String sinfo2 = sinfo.substring(4);
				int ipos5 = util.findfirstStringForBlock(sinfo2, "{");
				int ipos6 = util.findfirstStringForBlock(sinfo2, "}");

				if (ipos5 == -1) {
					System.out.println("����{ʧ��:" + sinfo2);
					return false;
				}
				if (ipos6 == -1) {
					System.out.println("����}ʧ��:" + sinfo2);
					return false;
				}

				String selseblock = sinfo2.substring(ipos5 + 1, ipos6);
				BaseBlockNodeV1_1 elsenode1 = new BaseBlockNodeV1_1(selseblock, this.getBlocklevel() + 1, "");
				// else���ʹ�õ���IF���ı�����
				elsenode1.setVarmaps(ifnode.getVarmaps());
				ifnode.setElseblocknode(elsenode1);
				ifnode.getChildren().add(elsenode1);
				this.computecontent = sinfo2.substring(ipos6 + 1);

			} else {
				// û��else�Ӿ�
				ifnode.setElseblocknode(null);
			}
		}

		// ��ifnode���뵽children����
		this.children.add(ifnode);
		return true;
	}

	// ����for�ؼ���
	// for�����﷨��������
	// for(forstring){
	// for statement;
	// }

	private boolean analyse_for() {
		StringFindUtil util = new StringFindUtil();
		int ipos1 = util.findfirstStringForBlock(this.computecontent, "(");
		if (ipos1 == -1) {
			System.out.println("for����������û���ҵ�(");
			return false;
		}
		int ipos2 = util.findfirstStringForBlock(this.computecontent, ")");
		if (ipos2 == -1) {
			System.out.println("for����������û���ҵ�)");
			return false;
		}

		ForBlockNodeV1_1 fornode = new ForBlockNodeV1_1();
		//����fornode�ı�����ĸ���������ǰblock�ı�����
		fornode.setParentVarmaps(this.varmaps);
		fornode.setChildren(new ArrayList<BaseBlockNodeV1_1>());
		// ����IF�ڵ���жϱ��ʽ����
		String forstring = this.computecontent.substring(ipos1 + 1, ipos2);
		fornode.setForstring(forstring);

		// ��for()��������Ĳ����зֳ�����node��Ȼ�����õ�fornode����ȥ
		// �á�;�� ���зָ�
		String forstr[] = forstring.split(";");
		if (forstr.length != 3) {
			System.out.println("for������ʧ�ܣ��������ʽ:" + forstring);
			return false;
		}
		BaseBlockNodeV1_1 beginNode = new BaseBlockNodeV1_1(forstr[0], this.getBlocklevel() + 1, "");
		//��ʼ�ڵ��for�ڵ㹲��ͬһ��������
		beginNode.setVarmaps(fornode.getVarmaps());
		BaseBlockNodeV1_1 loopNode = new BaseBlockNodeV1_1(forstr[2], this.getBlocklevel() + 1, "");
		//ѭ���ڵ��for�ڵ㹲��ͬһ��������
		loopNode.setVarmaps(fornode.getVarmaps());
		
		fornode.setBeginNode(beginNode);
		fornode.setLoopNode(loopNode);
		fornode.setForcondition(forstr[1]);

		fornode.getChildren().add(beginNode);
		fornode.getChildren().add(loopNode);

		// sinfo���г�if + ifcondition�Ĳ���
		String sinfo = this.computecontent.substring(ipos2 + 1);
		// ȥ���ո�ͻس�����
		sinfo = this.trimReturnAndSpace(sinfo);
		if (sinfo.charAt(0) == '{') {
			// ���if������ٵ���{������Ҷ�Ӧ��}�ַ���
			int ipos3 = util.findfirstStringForBlock(sinfo, "}");
			if (ipos3 == -1) {
				System.out.println("����}ʧ��:" + sinfo);
				return false;
			}
			String sifblock = sinfo.substring(0, ipos3 + 1);
			BaseBlockNodeV1_1 loopnode1 = new BaseBlockNodeV1_1(sifblock, this.getBlocklevel() + 1, "");
			//loopnode1���ø�������Ϊfor�ڵ��Ӧ�ı�����
			loopnode1.setParentVarmaps(fornode.getVarmaps());
			
			fornode.setFornode(loopnode1);
			fornode.getChildren().add(loopnode1);
			sinfo = sinfo.substring(ipos3 + 1);
		} else {
			int ipos4 = util.findfirstStringForBlock(sinfo, ";");
			if (ipos4 == -1) {
				System.out.println("����;ʧ��" + sinfo);
				return false;
			}
			String sifblock = sinfo.substring(0, ipos4 + 1);
			BaseBlockNodeV1_1 loopnode1 = new BaseBlockNodeV1_1(sifblock, this.getBlocklevel() + 1, "");
			//loopnode1���ø�������Ϊfor�ڵ��Ӧ�ı�����
			loopnode1.setParentVarmaps(fornode.getVarmaps());
			
			fornode.setFornode(loopnode1);
			fornode.getChildren().add(loopnode1);
			sinfo = sinfo.substring(ipos4 + 1);
		}

		this.computecontent = sinfo;

		// ��fornode���뵽children����
		this.children.add(fornode);
		return true;
	}

	// ����while�ؼ���
	// while�����﷨��������
	// while(whilestring){
	// while statement;
	// }

	private boolean analyse_while() {
		StringFindUtil util = new StringFindUtil();
		int ipos1 = util.findfirstStringForBlock(this.computecontent, "(");
		if (ipos1 == -1) {
			System.out.println("while����������û���ҵ�(");
			return false;
		}
		int ipos2 = util.findfirstStringForBlock(this.computecontent, ")");
		if (ipos2 == -1) {
			System.out.println("while����������û���ҵ�)");
			return false;
		}

		WhileBlockNodeV1_1 whilenode = new WhileBlockNodeV1_1();
		whilenode.setParentVarmaps(this.varmaps);
		
		whilenode.setChildren(new ArrayList<BaseBlockNodeV1_1>());
		// ����IF�ڵ���жϱ��ʽ����
		String whilestring = this.computecontent.substring(ipos1 + 1, ipos2);
		whilenode.setWhilecondition(whilestring);

		// sinfo���г�if + ifcondition�Ĳ���
		String sinfo = this.computecontent.substring(ipos2 + 1);
		// ȥ���ո�ͻس�����
		sinfo = this.trimReturnAndSpace(sinfo);
		if (sinfo.charAt(0) == '{') {
			// ���if������ٵ���{������Ҷ�Ӧ��}�ַ���
			int ipos3 = util.findfirstStringForBlock(sinfo, "}");
			if (ipos3 == -1) {
				System.out.println("����}ʧ��:" + sinfo);
				return false;
			}
			String sifblock = sinfo.substring(0, ipos3 + 1);
			BaseBlockNodeV1_1 loopnode1 = new BaseBlockNodeV1_1(sifblock, this.getBlocklevel() + 1, "");
			loopnode1.setParentVarmaps(whilenode.getVarmaps());
			whilenode.setWhilenode(loopnode1);
			whilenode.getChildren().add(loopnode1);
			sinfo = sinfo.substring(ipos3 + 1);
		} else {
			int ipos4 = util.findfirstStringForBlock(sinfo, ";");
			if (ipos4 == -1) {
				System.out.println("����;ʧ��" + sinfo);
				return false;
			}
			String sifblock = sinfo.substring(0, ipos4 + 1);
			BaseBlockNodeV1_1 loopnode1 = new BaseBlockNodeV1_1(sifblock, this.getBlocklevel() + 1, "");
			loopnode1.setParentVarmaps(whilenode.getVarmaps());
			whilenode.setWhilenode(loopnode1);

			whilenode.getChildren().add(loopnode1);
			sinfo = sinfo.substring(ipos4 + 1);
		}

		this.computecontent = sinfo;

		// ��fornode���뵽children����
		this.children.add(whilenode);
		return true;
	}

	// ����do...while�ؼ���
	// do...while�����﷨��������
	// do{
	// dostatement;
	// }while(whilestring)

	private boolean analyse_do() {
		StringFindUtil util = new StringFindUtil();
		int ipos1 = util.findfirstStringForBlock(this.computecontent, "{");
		if (ipos1 == -1) {
			System.out.println("do����������û���ҵ�{");
			return false;
		}
		int ipos2 = util.findfirstStringForBlock(this.computecontent, "}");
		if (ipos2 == -1) {
			System.out.println("do����������û���ҵ�}");
			return false;
		}

		DoWhileBlockNodeV1_1 dowhilenode = new DoWhileBlockNodeV1_1();
		//dowhilenode���ñ�����ĸ�������Ϊ��ǰblock�ı�����
		dowhilenode.setParentVarmaps(this.varmaps);
		dowhilenode.setChildren(new ArrayList<BaseBlockNodeV1_1>());

		String dowhilestring = this.computecontent.substring(ipos1 + 1, ipos2);
		BaseBlockNodeV1_1 donode = new BaseBlockNodeV1_1(dowhilestring, this.getBlocklevel() + 1, "");
		//do node���ñ�����ĸ�������Ϊ�´����ı�����
		donode.setParentVarmaps(dowhilenode.getVarmaps());
		dowhilenode.setDonode(donode);

		// sinfo���г�do()�Ĳ���
		String sinfo = this.computecontent.substring(ipos2 + 1);
		// ȥ���ո�ͻس�����
		sinfo = this.trimReturnAndSpace(sinfo);
		// �ж�sinfo�Ƿ��� while��ͷ
		if (!sinfo.startsWith("while")) {
			System.out.println("dowhile����ʧ�ܣ�û���ҵ�while�ؼ��ʡ�" + sinfo);
			return false;
		}

		// �г�while�ؼ���
		sinfo = sinfo.substring(5);
		sinfo = this.trimReturnAndSpace(sinfo);
		if (sinfo.charAt(0) != '(') {
			System.out.println("dowhile����ʧ�ܣ�whileû���ҵ�(" + sinfo);
			return false;
		}
		int ipos3 = util.findfirstStringForBlock(sinfo, ")");
		if (ipos3 == -1) {
			System.out.println("dowhile����ʧ�ܣ�whileû���ҵ�)" + sinfo);
			return false;
		} else {
			String strwhile = sinfo.substring(1, ipos3);
			dowhilenode.setWhilestring(strwhile);
		}
		this.computecontent = sinfo.substring(ipos3 + 1);
		// ��dowhilenode���뵽children����
		this.children.add(dowhilenode);
		return true;
	}

	/**
	 * ִ�д����
	 * 
	 * @return
	 */
	public boolean execute() {
		// �����ж����node��û��child
		if (this.children == null || this.children.size() == 0) {
			// ����һ��û���¼��ڵ�Ľڵ㣬�Ѿ�����ײ�Ľڵ���
			// ������ڵ�ת����һ��ASTTree
			ASTTreeNodeV1_1 node = new ASTTreeNodeV1_1(this.getBlockcontent(), 0);
			node.analyseTree();
			node.show();
			node.eval(this.varmaps);
			System.out.println("��������" + node.getOperdataresult().toString());
			return true;
		}

		// �����BaseBlockNode�Ľڵ����ͣ���ô��˳��ִ�и��ӽڵ�
		// ������ͬ���͵Ľڵ��ִ�з������ڶ�Ӧ���͵Ľڵ��н��ж���
		for (BaseBlockNodeV1_1 child : children) {
			// child.setParent(this);
			child.execute();
		}
		return true;
	}

	/**
	 * ����������ֵĴ����š�{��
	 * 
	 * @return
	 */
	private boolean analyse_brace() {
		StringFindUtil util = new StringFindUtil();
		int ipos2 = util.findfirstStringForBlock(this.computecontent, "}");
		if (ipos2 == -1) {
			// �������{����û�������}
			// �������Ų���ƥ��Ĵ���
			System.out.println("����ʧ�ܣ��Ҳ���ƥ���}:" + this.computecontent);
			return false;
		} else {
			// ���ж��ǲ���һ���յ�{}
			if (ipos2 > 2) {
				// ����һ���µ��ӽڵ㣬��{}ȥ��
				String sinfo = this.computecontent.substring(1, ipos2);
				BaseBlockNodeV1_1 child1 = new BaseBlockNodeV1_1(sinfo, this.getBlocklevel() + 1, "block");
				child1.setParentVarmaps(this.varmaps);
				this.children.add(child1);
				// ����computecontent
				this.computecontent = this.computecontent.substring(ipos2 + 1);
				return true;
			} else {
				BaseBlockNodeV1_1 child1 = new BaseBlockNodeV1_1("", this.getBlocklevel() + 1, "emptyblock");
				this.children.add(child1);
				child1.setParentVarmaps(this.varmaps);
				// ����computecontent
				this.computecontent = this.computecontent.substring(ipos2 + 1);
				return true;
			}
		}
	}

	/**
	 * �����ȡ���Ŀ�ָ����ǡ�;��
	 * 
	 * @param ipos
	 * @return
	 */
	private boolean analyse_semicolon(int ipos) {
		String leftstring = this.computecontent.substring(0, ipos);
		BaseBlockNodeV1_1 child0 = new BaseBlockNodeV1_1(leftstring, this.getBlocklevel() + 1, "singlestatement");
		// ������Ҫ����child0��varmapsָ��ǰ��varmaps
		child0.setVarmaps(this.getVarmaps());

		this.children.add(child0);
		// ����computecontent���г���벿�֣�ֻ�����Ұ벿��
		this.computecontent = this.computecontent.substring(ipos + 1);

		return true;
	}
}
