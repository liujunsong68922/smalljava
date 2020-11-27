package com.liu.smalljava.v1_1.block;

import java.util.ArrayList;
import java.util.HashMap;

import com.liu.smalljava.v1_1.StringFindUtil;
import com.liu.smalljava.v1_1.expression.ASTTreeNodeV1_1;
import com.liu.smalljava.v1_1.vartable.VarTableNodeV1_1;

public class BaseBlockNodeV1_1 extends AbstractBlockV1_1 {
	// 本地变量表
	private VarTableNodeV1_1 varmaps = new VarTableNodeV1_1(null);

	public VarTableNodeV1_1 getVarmaps() {
		return varmaps;
	}

	public void setVarmaps(VarTableNodeV1_1 varmaps) {
		this.varmaps = varmaps;
	}

	public void setParentVarmaps(VarTableNodeV1_1 parentvarmaps) {
		if (this.varmaps == null) {
			System.out.println("程序调用错误，当前节点varmaps is null");
			return;
		} else {
			// 设置当前节点的上级节点
			if (this.varmaps.equals(parentvarmaps)) {
				// 避免产生死循环
				System.out.println("程序调用错误，当前节点varmaps和parent节点重复!");
				return;
			} else {
				// 这里的parentvarmaps可以是一个null值对象
				this.varmaps.setParentnode(parentvarmaps);
			}
		}
	}

	// 实际计算用的computecontent;
	// 原始的blockcontent不可变，计算时使用computecontent
	private String computecontent;

	/**
	 * 分解以后得到的子节点
	 */
	private ArrayList<BaseBlockNodeV1_1> children = null;

	/**
	 * 构造函数
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
	 * 默认的构造函数
	 */
	public BaseBlockNodeV1_1() {

	}

	/**
	 * 将blockcontent分解到children里面去
	 */
	public boolean anylyse() {
		// 首先将blockcontent从左往右进行循环，从中查找符合条件的切分符合进行分隔
		this.computecontent = this.getBlockcontent();
		if (this.computecontent == null) {
			System.out.println("要计算的块字符串为null");
			// 分析计算失败
			return false;
		}
		// 去掉要计算字符串的前后空格
		this.computecontent = this.trimReturnAndSpace(this.computecontent);
		System.out.println("------>" + this.computecontent);
		// 如果剩余的是一个空字符串，则返回
		if (this.computecontent.length() == 0) {
			// 去掉无效字符以后的空白字符串无须进行处理
			this.setBlocktype("emptyblock");
			return true;
		}

		// 循环判断查找下一个代码块
		// 原则上要切分到所有要计算的内容都计算完毕
		while (this.computecontent.length() > 0) {
			// 去掉要计算字符串的前后空格和回车符号
			this.computecontent = this.trimReturnAndSpace(this.computecontent);
			// System.out.println("------>" + this.computecontent);

			// 尝试从computecontent里面拿到一个关键词
			BlockTokenV1_1 firsttoken = this.getNextBlockToken();
			if (firsttoken == null) {
				System.out.println("分析错误：要计算的块字符串中未找到关键词：" + this.computecontent);
				System.out.println("这个字符串下一步要按照表达式来进行处理." + this.computecontent);
				return true;
			}

			if (firsttoken.getIpos() > 0 && !firsttoken.getToken().equals(";")) {
				System.out
						.println("分析错误，第一个token之前有无效数据:" + this.computecontent.substring(0, firsttoken.getIpos() + 1));
				return true;
			}

			// 判断并初始化children
			if (this.getChildren() == null) {
				ArrayList<BaseBlockNodeV1_1> children = new ArrayList<BaseBlockNodeV1_1>();
				this.setChildren(children);
			}

			// ----------------------------------------------
			// 开始进行按照特定分隔符号的处理动作
			// ----------------------------------------------

			// 如果找到的第一个块分隔符号是【;】
			// 调用子程序来按照;进行分隔处理
			if (firsttoken.getToken().equals(";")) {
				this.analyse_semicolon(firsttoken.getIpos());
				continue;
			}

			// 如果找到的第一个块分隔符号是【{】
			if (firsttoken.getToken().equals("{")) {
				System.out.println("开始分析左面的大括号{");
				if (this.analyse_brace()) {
					// do nothing
					System.out.println("分析【{】成功");
				} else {
					System.out.println("分析【{】失败");
					return false;
				}
			}

			// 如果找到的第一个token是【if】
			if (firsttoken.getToken().equals("if")) {
				System.out.println("开始分析if关键词");
				if (this.analyse_if()) {
					// do nothing
					System.out.println("分析IF语句成功.");
				} else {
					// 如果分析if失败
					System.out.println("分析IF语句失败.");
					return false;
				}
			}

			// 如果找到的第一个token是【for】
			if (firsttoken.getToken().equals("for")) {
				System.out.println("开始分析for关键词");
				if (this.analyse_for()) {
					// do nothing
					System.out.println("分析for语句成功.");
				} else {
					// 如果分析if失败
					System.out.println("分析for语句失败.");
					return false;
				}
			}

			// 如果找到的第一个token是【while】
			if (firsttoken.getToken().equals("while")) {
				System.out.println("开始分析while关键词");
				if (this.analyse_while()) {
					// do nothing
					System.out.println("分析while语句成功.");
				} else {
					// 如果分析if失败
					System.out.println("分析while语句失败.");
					return false;
				}
			}

			// 如果找到的第一个token是【do】
			if (firsttoken.getToken().equals("do")) {
				System.out.println("开始分析do关键词");
				if (this.analyse_do()) {
					// do nothing
					System.out.println("分析do语句成功.");
				} else {
					// 如果分析if失败
					System.out.println("分析do语句失败.");
					return false;
				}
			}
		}

		// 所有的字符串已经都计算完毕了，现在需要递归进行调用了
		System.out.println("开始进行子节点块的处理------>length:" + this.getChildren().size());
		for (BaseBlockNodeV1_1 child : this.getChildren()) {

			// 尝试分解计算子节点
			if (child.anylyse()) {
				System.out.println("子节点分析成功");
				// 继续分解计算
				continue;
			} else {
				// 终止计算，分解发生错误，需要人工进行处理
				System.out.println("子节点分析发生错误。。。");
				return false;
			}
		}
		return true;
	}

	/**
	 * 计算所有位置的Token类型
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
	 * 将字符串开始和结束位置的\r\n ,\r,空格都过滤掉
	 * 
	 * @param strinput
	 * @return
	 */
	private String trimReturnAndSpace(String strinput) {
		return new StringFindUtil().trimReturnAndSpace(strinput);
	}

	// 处理分析【if】关键词
	// 【if】关键词的语法定义如下：
	// if(ifcondition){
	// statement1;
	// }else{
	// statement2;
	// }

	private boolean analyse_if() {
		StringFindUtil util = new StringFindUtil();
		int ipos1 = util.findfirstStringForBlock(this.computecontent, "(");
		if (ipos1 == -1) {
			System.out.println("if语句分析错误，没有找到(");
			return false;
		}
		int ipos2 = util.findfirstStringForBlock(this.computecontent, ")");
		if (ipos2 == -1) {
			System.out.println("if语句分析错误，没有找到)");
			return false;
		}

		IfBlockNodeV1_1 ifnode = new IfBlockNodeV1_1();
		// Ifnode 需要设置一个从属的varmaps,指向本节点作为父变量表
		ifnode.setParentVarmaps(this.varmaps);

		ifnode.setChildren(new ArrayList<BaseBlockNodeV1_1>());
		// 设置IF节点的判断表达式条件
		ifnode.setIfcondition(this.computecontent.substring(ipos1 + 1, ipos2));

		// sinfo是切除if + ifcondition的部分
		String sinfo = this.computecontent.substring(ipos2 + 1);
		// 去掉空格和回车符号
		sinfo = this.trimReturnAndSpace(sinfo);
		if (sinfo.charAt(0) == '{') {
			// 如果if后面跟踪的是{，则查找对应的}字符串
			int ipos3 = util.findfirstStringForBlock(sinfo, "}");
			if (ipos3 == -1) {
				System.out.println("查找}失败:" + sinfo);
				return false;
			}
			String sifblock = sinfo.substring(0, ipos3 + 1);
			BaseBlockNodeV1_1 ifnode1 = new BaseBlockNodeV1_1(sifblock, this.getBlocklevel() + 1, "");
			// 设置ifnode1的varmaps指向IFnode,共享变量集合
			ifnode1.setVarmaps(ifnode.getVarmaps());

			ifnode.setIfblocknode(ifnode1);

			ifnode.getChildren().add(ifnode1);
			sinfo = sinfo.substring(ipos3 + 1);
		} else {
			int ipos4 = util.findfirstStringForBlock(sinfo, ";");
			if (ipos4 == -1) {
				System.out.println("查找;失败" + sinfo);
				return false;
			}
			String sifblock = sinfo.substring(0, ipos4);
			BaseBlockNodeV1_1 ifnode1 = new BaseBlockNodeV1_1(sifblock, this.getBlocklevel() + 1, "");
			// 设置ifnode1的varmaps指向IFnode,共享变量集合
			ifnode1.setVarmaps(ifnode.getVarmaps());

			ifnode.setIfblocknode(ifnode1);
			ifnode.getChildren().add(ifnode1);
			sinfo = sinfo.substring(ipos4 + 1);
		}

		this.computecontent = sinfo;

		// 接下来判断是不是else关键词
		sinfo = this.trimReturnAndSpace(sinfo);
		if (sinfo.length() > 3) {
			if (sinfo.startsWith("else")) {
				// 存在else子句,暂时不考虑不带{}的情况
				// 要求else子句必须{}来进行分隔
				String sinfo2 = sinfo.substring(4);
				int ipos5 = util.findfirstStringForBlock(sinfo2, "{");
				int ipos6 = util.findfirstStringForBlock(sinfo2, "}");

				if (ipos5 == -1) {
					System.out.println("查找{失败:" + sinfo2);
					return false;
				}
				if (ipos6 == -1) {
					System.out.println("查找}失败:" + sinfo2);
					return false;
				}

				String selseblock = sinfo2.substring(ipos5 + 1, ipos6);
				BaseBlockNodeV1_1 elsenode1 = new BaseBlockNodeV1_1(selseblock, this.getBlocklevel() + 1, "");
				// else语句使用的是IF大块的变量块
				elsenode1.setVarmaps(ifnode.getVarmaps());
				ifnode.setElseblocknode(elsenode1);
				ifnode.getChildren().add(elsenode1);
				this.computecontent = sinfo2.substring(ipos6 + 1);

			} else {
				// 没有else子句
				ifnode.setElseblocknode(null);
			}
		}

		// 将ifnode加入到children里面
		this.children.add(ifnode);
		return true;
	}

	// 处理for关键词
	// for语句的语法定义如下
	// for(forstring){
	// for statement;
	// }

	private boolean analyse_for() {
		StringFindUtil util = new StringFindUtil();
		int ipos1 = util.findfirstStringForBlock(this.computecontent, "(");
		if (ipos1 == -1) {
			System.out.println("for语句分析错误，没有找到(");
			return false;
		}
		int ipos2 = util.findfirstStringForBlock(this.computecontent, ")");
		if (ipos2 == -1) {
			System.out.println("for语句分析错误，没有找到)");
			return false;
		}

		ForBlockNodeV1_1 fornode = new ForBlockNodeV1_1();
		//挂载fornode的变量表的父变量表到当前block的变量表
		fornode.setParentVarmaps(this.varmaps);
		fornode.setChildren(new ArrayList<BaseBlockNodeV1_1>());
		// 设置IF节点的判断表达式条件
		String forstring = this.computecontent.substring(ipos1 + 1, ipos2);
		fornode.setForstring(forstring);

		// 将for()括号里面的部分切分成三个node，然后设置到fornode里面去
		// 用【;】 进行分隔
		String forstr[] = forstring.split(";");
		if (forstr.length != 3) {
			System.out.println("for语句解析失败，条件表达式:" + forstring);
			return false;
		}
		BaseBlockNodeV1_1 beginNode = new BaseBlockNodeV1_1(forstr[0], this.getBlocklevel() + 1, "");
		//开始节点和for节点共享同一个变量表
		beginNode.setVarmaps(fornode.getVarmaps());
		BaseBlockNodeV1_1 loopNode = new BaseBlockNodeV1_1(forstr[2], this.getBlocklevel() + 1, "");
		//循环节点和for节点共享同一个变量表
		loopNode.setVarmaps(fornode.getVarmaps());
		
		fornode.setBeginNode(beginNode);
		fornode.setLoopNode(loopNode);
		fornode.setForcondition(forstr[1]);

		fornode.getChildren().add(beginNode);
		fornode.getChildren().add(loopNode);

		// sinfo是切除if + ifcondition的部分
		String sinfo = this.computecontent.substring(ipos2 + 1);
		// 去掉空格和回车符号
		sinfo = this.trimReturnAndSpace(sinfo);
		if (sinfo.charAt(0) == '{') {
			// 如果if后面跟踪的是{，则查找对应的}字符串
			int ipos3 = util.findfirstStringForBlock(sinfo, "}");
			if (ipos3 == -1) {
				System.out.println("查找}失败:" + sinfo);
				return false;
			}
			String sifblock = sinfo.substring(0, ipos3 + 1);
			BaseBlockNodeV1_1 loopnode1 = new BaseBlockNodeV1_1(sifblock, this.getBlocklevel() + 1, "");
			//loopnode1设置父变量表为for节点对应的变量表
			loopnode1.setParentVarmaps(fornode.getVarmaps());
			
			fornode.setFornode(loopnode1);
			fornode.getChildren().add(loopnode1);
			sinfo = sinfo.substring(ipos3 + 1);
		} else {
			int ipos4 = util.findfirstStringForBlock(sinfo, ";");
			if (ipos4 == -1) {
				System.out.println("查找;失败" + sinfo);
				return false;
			}
			String sifblock = sinfo.substring(0, ipos4 + 1);
			BaseBlockNodeV1_1 loopnode1 = new BaseBlockNodeV1_1(sifblock, this.getBlocklevel() + 1, "");
			//loopnode1设置父变量表为for节点对应的变量表
			loopnode1.setParentVarmaps(fornode.getVarmaps());
			
			fornode.setFornode(loopnode1);
			fornode.getChildren().add(loopnode1);
			sinfo = sinfo.substring(ipos4 + 1);
		}

		this.computecontent = sinfo;

		// 将fornode加入到children里面
		this.children.add(fornode);
		return true;
	}

	// 处理while关键词
	// while语句的语法定义如下
	// while(whilestring){
	// while statement;
	// }

	private boolean analyse_while() {
		StringFindUtil util = new StringFindUtil();
		int ipos1 = util.findfirstStringForBlock(this.computecontent, "(");
		if (ipos1 == -1) {
			System.out.println("while语句分析错误，没有找到(");
			return false;
		}
		int ipos2 = util.findfirstStringForBlock(this.computecontent, ")");
		if (ipos2 == -1) {
			System.out.println("while语句分析错误，没有找到)");
			return false;
		}

		WhileBlockNodeV1_1 whilenode = new WhileBlockNodeV1_1();
		whilenode.setParentVarmaps(this.varmaps);
		
		whilenode.setChildren(new ArrayList<BaseBlockNodeV1_1>());
		// 设置IF节点的判断表达式条件
		String whilestring = this.computecontent.substring(ipos1 + 1, ipos2);
		whilenode.setWhilecondition(whilestring);

		// sinfo是切除if + ifcondition的部分
		String sinfo = this.computecontent.substring(ipos2 + 1);
		// 去掉空格和回车符号
		sinfo = this.trimReturnAndSpace(sinfo);
		if (sinfo.charAt(0) == '{') {
			// 如果if后面跟踪的是{，则查找对应的}字符串
			int ipos3 = util.findfirstStringForBlock(sinfo, "}");
			if (ipos3 == -1) {
				System.out.println("查找}失败:" + sinfo);
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
				System.out.println("查找;失败" + sinfo);
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

		// 将fornode加入到children里面
		this.children.add(whilenode);
		return true;
	}

	// 处理do...while关键词
	// do...while语句的语法定义如下
	// do{
	// dostatement;
	// }while(whilestring)

	private boolean analyse_do() {
		StringFindUtil util = new StringFindUtil();
		int ipos1 = util.findfirstStringForBlock(this.computecontent, "{");
		if (ipos1 == -1) {
			System.out.println("do语句分析错误，没有找到{");
			return false;
		}
		int ipos2 = util.findfirstStringForBlock(this.computecontent, "}");
		if (ipos2 == -1) {
			System.out.println("do语句分析错误，没有找到}");
			return false;
		}

		DoWhileBlockNodeV1_1 dowhilenode = new DoWhileBlockNodeV1_1();
		//dowhilenode设置变量表的父变量表为当前block的变量表
		dowhilenode.setParentVarmaps(this.varmaps);
		dowhilenode.setChildren(new ArrayList<BaseBlockNodeV1_1>());

		String dowhilestring = this.computecontent.substring(ipos1 + 1, ipos2);
		BaseBlockNodeV1_1 donode = new BaseBlockNodeV1_1(dowhilestring, this.getBlocklevel() + 1, "");
		//do node设置变量表的父变量表为新创建的变量表
		donode.setParentVarmaps(dowhilenode.getVarmaps());
		dowhilenode.setDonode(donode);

		// sinfo是切除do()的部分
		String sinfo = this.computecontent.substring(ipos2 + 1);
		// 去掉空格和回车符号
		sinfo = this.trimReturnAndSpace(sinfo);
		// 判断sinfo是否以 while开头
		if (!sinfo.startsWith("while")) {
			System.out.println("dowhile解析失败，没有找到while关键词。" + sinfo);
			return false;
		}

		// 切除while关键词
		sinfo = sinfo.substring(5);
		sinfo = this.trimReturnAndSpace(sinfo);
		if (sinfo.charAt(0) != '(') {
			System.out.println("dowhile解析失败，while没有找到(" + sinfo);
			return false;
		}
		int ipos3 = util.findfirstStringForBlock(sinfo, ")");
		if (ipos3 == -1) {
			System.out.println("dowhile解析失败，while没有找到)" + sinfo);
			return false;
		} else {
			String strwhile = sinfo.substring(1, ipos3);
			dowhilenode.setWhilestring(strwhile);
		}
		this.computecontent = sinfo.substring(ipos3 + 1);
		// 将dowhilenode加入到children里面
		this.children.add(dowhilenode);
		return true;
	}

	/**
	 * 执行代码块
	 * 
	 * @return
	 */
	public boolean execute() {
		// 首先判断这个node有没有child
		if (this.children == null || this.children.size() == 0) {
			// 这是一个没有下级节点的节点，已经是最底层的节点了
			// 把这个节点转换成一个ASTTree
			ASTTreeNodeV1_1 node = new ASTTreeNodeV1_1(this.getBlockcontent(), 0);
			node.analyseTree();
			node.show();
			node.eval(this.varmaps);
			System.out.println("计算结果：" + node.getOperdataresult().toString());
			return true;
		}

		// 如果是BaseBlockNode的节点类型，那么就顺序执行各子节点
		// 其他不同类型的节点的执行方法，在对应类型的节点中进行定义
		for (BaseBlockNodeV1_1 child : children) {
			// child.setParent(this);
			child.execute();
		}
		return true;
	}

	/**
	 * 处理左面出现的大括号【{】
	 * 
	 * @return
	 */
	private boolean analyse_brace() {
		StringFindUtil util = new StringFindUtil();
		int ipos2 = util.findfirstStringForBlock(this.computecontent, "}");
		if (ipos2 == -1) {
			// 有左面的{，但没有右面的}
			// 这是括号不能匹配的错误
			System.out.println("分析失败，找不到匹配的}:" + this.computecontent);
			return false;
		} else {
			// 先判断是不是一个空的{}
			if (ipos2 > 2) {
				// 生成一个新的子节点，把{}去掉
				String sinfo = this.computecontent.substring(1, ipos2);
				BaseBlockNodeV1_1 child1 = new BaseBlockNodeV1_1(sinfo, this.getBlocklevel() + 1, "block");
				child1.setParentVarmaps(this.varmaps);
				this.children.add(child1);
				// 更新computecontent
				this.computecontent = this.computecontent.substring(ipos2 + 1);
				return true;
			} else {
				BaseBlockNodeV1_1 child1 = new BaseBlockNodeV1_1("", this.getBlocklevel() + 1, "emptyblock");
				this.children.add(child1);
				child1.setParentVarmaps(this.varmaps);
				// 更新computecontent
				this.computecontent = this.computecontent.substring(ipos2 + 1);
				return true;
			}
		}
	}

	/**
	 * 如果读取到的块分隔符是【;】
	 * 
	 * @param ipos
	 * @return
	 */
	private boolean analyse_semicolon(int ipos) {
		String leftstring = this.computecontent.substring(0, ipos);
		BaseBlockNodeV1_1 child0 = new BaseBlockNodeV1_1(leftstring, this.getBlocklevel() + 1, "singlestatement");
		// 这里需要设置child0的varmaps指向当前的varmaps
		child0.setVarmaps(this.getVarmaps());

		this.children.add(child0);
		// 更新computecontent，切除左半部分，只保留右半部分
		this.computecontent = this.computecontent.substring(ipos + 1);

		return true;
	}
}
