package com.liu.smalljava.v1_1.expression;

import java.lang.reflect.Method;

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
 * 针对ASTTreeNode上封装eval方法及其实现，从原来ASTTreeNode里面拆分出来
 * 
 * @author liujunsong
 *
 */
public class ASTTreeNodeEval {
	private ASTTreeNodeV1_1 astnode;

	public ASTTreeNodeV1_1 getAstnode() {
		return astnode;
	}

	public void setAstnode(ASTTreeNodeV1_1 _astnode) {
		astnode = _astnode;
	}

	/**
	 * 在AST树分解完成以后，按照布尔表达式的计算规则来依次计算boolean值， 计算结果存放在boolresult里面
	 * 如果计算发生错误和失败，错误信息放在errorinfo里面
	 * 
	 * @return true计算成功计算 false计算发生错误
	 */
	public boolean eval(VarTableNodeV1_1 varmap) {
		if (astnode.stringresult != null || astnode.operdataresult != null) {
			System.out.println("当前节点已经被计算过。" + astnode.nodeString);
			return false;
		}

		// 无下级节点的情况,这个节点已经是一个最基层的节点了
		// 这时计算的结果要存放在stringresult里面
		if (astnode.children == null || astnode.children.size() == 0) {
			return eval_nochild(varmap);
		}

		// 单子节点的情况
		// 如果当前节点只有一个children
		if (astnode.children != null && astnode.children.size() == 1) {
			return eval_singlechild(varmap);
		}

		// 现在处理三个child的情况，这种情况是二元判断操作符号
		// 对象操作符不在其中
		if (astnode.children != null && astnode.children.size() == 3
				&& !astnode.children.get(1).nodeString.equals(".")) {
			return eval_threechild(varmap);
		}

		// 现在要处理其他情况了，children的数量大于3，推测应该是对象调用
		if (astnode.children != null && astnode.children.get(1).getNodeString().equals(".")) {
			return eval__objectcall(varmap);
		}

		// 逻辑上不应该跑到这里来
		System.out.println("【错误】如果你看到这个，程序逻辑就出错了.");
		return false;
	}

	// 当当前节点已经是末级节点，没有子节点时的评估计算
	private boolean eval_nochild(VarTableNodeV1_1 varmap) {
		System.out.println("无子节点计算开始。。。。" + astnode.nodeString);
		String sinfo = astnode.nodeString.trim();
		sinfo = new StringFindUtil().trimReturnAndSpace(sinfo);
		if (sinfo.length() == 0) {
			// 空字符串说明发生了判断错误
			astnode.errorinfo = "计算错误，发现了空节点,级别";
			return false;
		}

		// 如果字符串以"开头,那么这个就代表是一个字符串常量
		//
		if (sinfo.charAt(0) == '\"') {
			astnode.stringresult = sinfo;
			StringOperElement oper1 = new StringOperElement(sinfo);
			astnode.operdataresult = oper1;
			return true;
		}

		// 如果字符串以数字1-9开头，就代表这个是一个数值常量
		if (sinfo.charAt(0) >= '0' && sinfo.charAt(0) <= '9') {
			astnode.stringresult = sinfo;
			// 接下来要判断这个值是int,long,float,double
			sinfo = sinfo.trim();
			if (sinfo.endsWith("L") || sinfo.endsWith("l")) {
				// 按照长整型处理
				LongOperElement oper1 = new LongOperElement(sinfo);
				astnode.operdataresult = oper1;
			} else if (sinfo.endsWith("D") || sinfo.endsWith("d")) {
				// 按照双精度进行处理
				DoubleOperElement oper2 = new DoubleOperElement(sinfo);
				astnode.operdataresult = oper2;
			} else if (sinfo.endsWith("F") || sinfo.endsWith("f")) {
				// 按照单精度进行处理
				FloatOperElement oper3 = new FloatOperElement(sinfo);
				astnode.operdataresult = oper3;
			} else if (sinfo.indexOf(".") >= 0) {
				// 按照单精度进行处理
				FloatOperElement oper4 = new FloatOperElement(sinfo);
				astnode.operdataresult = oper4;
			} else {
				IntegerOperElement oper5 = new IntegerOperElement(sinfo);
				astnode.operdataresult = oper5;
			}
			return true;
		}

		// 这里需要判断是否是变量定义
		sinfo = new StringFindUtil().trimReturnAndSpace(sinfo);

		// 如果sinfo中间由空格分隔，那么就可以认为是变量定义
		String sdata[] = sinfo.split(" ");
		System.out.println(sdata.length);
		if (sdata.length == 2) {
			if (sdata[0].equals("new")) {
				// 根据后续的类定义来创建一个新对象，
				// 并将这个新对象放到变量表里面去，
				// 并将这个新对象放置到对应的varmaps里面去
				return eval_nochild_newobject(sinfo, varmap);
			} else {
				return eval_nochild_vardefine(sinfo, varmap);
			}
		}

		// 其他情况，作为变量来进行处理，这时需要从传入的变量表中进行对应查找替换
		// 如果找不到这个变量，那么就报发生错误
		// 此处暂时不考虑基于数据库的函数定义和调用问题
		// 先做基本的简化处理
		// 所以调用参数需要带有RS，其中包括key-value块映射用来做对应替换
		// 此处仅作最基本的替换，暂时不考虑命名冲突问题
		// 也不考虑大小写问题，要求大小写精确匹配

		// 变量替换有一个前提条件，就是当前节点不是一个变量
		if (astnode.isVarflag()) {
			// do nothing
			System.out.println("---->这是一个待赋值的变量:" + sinfo);
			astnode.stringresult = sinfo;
			return true;
		}

		// 这里需要考虑针对对象的变量调用
		// 在对象调用之前，需要先将操作对象在AST节点分解时，分解成三元操作符
		// 1.类对象（暂时不考虑静态类调用，略微复杂一点，需要后续补充),在varmap中定义获取
		// 2。【.】操作符，暂时只支持一级点操作符，先不考虑联动的问题
		// 3.方法名
		// 4.操作对象数，这里暂时也只考虑常量和变量，不考虑在调用时再进行二次计算的问题
		// 这样的话，在AST分解的时候，已经完成了分解，而不会在计算时进行分解

		// 在对象的变量调用完毕以后，再针对对象进行字符串替换
		System.out.println("查找到疑似变量名:" + sinfo + ",尝试进行字符串替换");
		VarValueV1_1 varvalue = varmap.getVarValue(sinfo);

		if (varvalue != null) {
			// 这里需要判断svalue是不是数值
			// 这里需要根据VarValue返回的数据类型来进行判断组合
			System.out.println("vartype:" + varvalue.getVartype());
			System.out.println("varvalue:" + varvalue.getVardvalue());

			if (varvalue.getVartype().equals("int")) {
				IntegerOperElement intoper = new IntegerOperElement();
				intoper.setValue("" + varvalue.getVardvalue());
				astnode.operdataresult = intoper;
				// astnode.stringresult = "" + varvalue.getVardvalue();
			} else if (varvalue.getVartype().equals("long")) {
				LongOperElement longoper = new LongOperElement();
				longoper.setValue("" + varvalue.getVardvalue());
				astnode.operdataresult = longoper;
				// astnode.stringresult = "" + varvalue.getVardvalue();
			} else if (varvalue.getVartype().equals("float")) {
				FloatOperElement floatoper = new FloatOperElement();
				floatoper.setValue("" + varvalue.getVardvalue());
				astnode.operdataresult = floatoper;
				// astnode.stringresult = "" + varvalue.getVardvalue();
			} else if (varvalue.getVartype().equals("double")) {
				DoubleOperElement doubleoper = new DoubleOperElement();
				doubleoper.setValue("" + varvalue.getVardvalue());
				astnode.operdataresult = doubleoper;
				// astnode.stringresult = "" + varvalue.getVardvalue();
			} else if (varvalue.getVartype().equals("String")) {
				StringOperElement stroper = new StringOperElement();
				stroper.setValue(varvalue.getVarsvalue());
				astnode.operdataresult = stroper;
			} else {
				OperElementData oed = new OperElementData();
				oed.setElementdatatype(varvalue.getVartype());
				oed.setElementObjectValue(varvalue.getVarobjvalue());
				astnode.operdataresult = oed;
			}

			return true;
		} else {
			System.out.println("变量查找失败:" + sinfo);
			astnode.errorinfo = "变量查找失败:" + sinfo;
			return false;
		}

	}

	private boolean eval_nochild_newobject(String sinfo, VarTableNodeV1_1 varmap) {
		System.out.println("开始进行类的实例化。。。");
		String sdata[] = sinfo.split(" ");
		if (sdata.length != 2) {
			System.out.println("程序错误，类实例化语法错误!" + sinfo);
			return false;
		}
		if (!sdata[0].equals("new")) {
			System.out.println("程序错误，类实例化语法错误，找不到【new】关键词");
			return false;
		}
		String classname = sdata[1].trim();
		// 去掉classname后面的()，注意这里暂时只支持无条件的构造函数
		classname = classname.substring(0, classname.length() - 2);

		if (classname.length() == 0) {
			System.out.println("程序错误，类实例化语法错误，找不到要实例化的class");
			return false;
		}

		// 利用classname从classmap里面来查找最原始的class名称
		ClassTable classtable = new ClassTable();
		Class c1 = classtable.getClass(classname);
		if (c1 == null) {
			// 找不到类定义
			System.out.println("程序错误，实例化时找不到指定的类:" + classname);
			return false;
		} else {
			// 完成实例化工作
			try {
				Object objvalue = c1.newInstance();
				// 在执行=赋值操作时负责写入变量表中，此处仅创建并写入resultobject
				OperElementData operdata = new OperElementData();
				operdata.setElementdatatype(classname);
				operdata.setElementObjectValue(objvalue);
				astnode.operdataresult = operdata;

				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// 类实例化时失败
				System.out.println("类实例化时失败." + classname);
				return false;
			}
		}
	}

	/**
	 * 判断是否是变量定义，如果是则在变量表中定义变量
	 * 
	 * @param varmap
	 * @return
	 */
	private boolean eval_nochild_vardefine(String sinfo, VarTableNodeV1_1 varmap) {
		System.out.println("开始进行变量定义。。。");
		String sdata[] = sinfo.split(" ");
		String datatype = sdata[0];
		String varname = sdata[1].trim();
		if (sinfo.startsWith("int")) {
			astnode.stringresult = varname;
			varmap.defineVar(varname, "int");
			IntegerOperElement intoper = new IntegerOperElement("0");
			astnode.operdataresult = intoper;
			return true;
		}
		if (sinfo.startsWith("long")) {
			astnode.stringresult = varname;
			varmap.defineVar(varname, "long");
			LongOperElement longoper = new LongOperElement("0");
			astnode.operdataresult = longoper;
			return true;
		}
		if (sinfo.startsWith("float")) {
			astnode.stringresult = varname;
			varmap.defineVar(varname, "float");
			FloatOperElement floatoper = new FloatOperElement("0");
			astnode.operdataresult = floatoper;
			return true;
		}
		if (sinfo.startsWith("double")) {
			astnode.stringresult = varname;
			varmap.defineVar(varname, "double");
			DoubleOperElement doubleoper = new DoubleOperElement("0");
			astnode.operdataresult = doubleoper;
			return true;
		}
		if (sinfo.startsWith("boolean")) {
			astnode.stringresult = varname;
			varmap.defineVar(varname, "boolean");
			BooleanOperElement booloper = new BooleanOperElement();
			astnode.operdataresult = booloper;
			return true;
		}
		if (sinfo.startsWith("String")) {
			varmap.defineVar(varname, "String");
			StringOperElement stroper = new StringOperElement();
			astnode.operdataresult = stroper;
			astnode.stringresult = varname;

			return true;
		}

		// 其他情况下，需要根据sdata[0]来查找对应的JAVA类，完成变量定义
		// 这里就涉及到非常关键的面向对象的处理特征
		ClassTable classtable = new ClassTable();
		Class c1 = classtable.getClass(sdata[0]);
		if (c1 == null) {
			System.out.println("发现未定义的Java类：" + sdata[0]);
			return false;
		} else {
			astnode.stringresult = varname;
			// 仅在变量表中定义一个新对象名称，尚未分配内存
			// 分配内存需要使用new 关键词来进行分配
			// String 对象例外，可以使用= 关键词来赋值计算
			varmap.defineVar(varname, sdata[0]);
			System.out.println("变量定义完成");
		}
		return true;
	}

	/**
	 * 当当前节点具有唯一一个child的时候的处理方式
	 * 
	 * @param varmap
	 * @return
	 */
	private boolean eval_singlechild(VarTableNodeV1_1 varmap) {
		System.out.println("单子节点计算开始。。。。");
		// 计算下级计算节点，并将其计算结果保存到本级的结果里面来
		if (astnode.children.get(0).operdataresult == null && astnode.children.get(0).stringresult == null) {
			// 将变量表传入，递归进行变量替换处理
			if (astnode.children.get(0).eval(varmap)) {
				// 保存结果，返回true
				astnode.operdataresult = astnode.children.get(0).operdataresult;
				astnode.stringresult = astnode.children.get(0).stringresult;
				return true;
			} else {
				// 计算失败，返回false;
				astnode.errorinfo = astnode.children.get(0).errorinfo;
			}
		} else {
			System.out.println("下属子节点已经计算完成.,");
			astnode.operdataresult = astnode.children.get(0).operdataresult;
			astnode.stringresult = astnode.children.get(0).stringresult;
			astnode.errorinfo = astnode.children.get(0).errorinfo;
		}
		return true;

	}

	/**
	 * 三元运算符的处理情况
	 * 
	 * @param varmap
	 * @return
	 */
	private boolean eval_threechild(VarTableNodeV1_1 varmap) {
		IDualOperatorFactory operfactory = new IDualOperatorFactory();

		System.out.println("3个下属子节点的计算开始。。。");
		boolean leftflag = astnode.children.get(0).eval(varmap);
		if (!leftflag) {
			// 左操作符计算失败
			System.out.println("左操作符计算失败");
			astnode.errorinfo = astnode.children.get(0).errorinfo;
			return false;
		}

		boolean rightflag = astnode.children.get(2).eval(varmap);
		if (!rightflag) {
			// 右操作符计算失败
			System.out.println("右操作符计算失败：-->" + astnode.children.get(2).nodeString);
			astnode.errorinfo = astnode.children.get(2).errorinfo;
			return false;
		}

		if (astnode.children.get(0).stringresult == null && astnode.children.get(0).operdataresult == null) {
			astnode.errorinfo = "左操作符计算结果不正确:" + astnode.children.get(0).nodeString;
			return false;
		}

		if (astnode.children.get(2).stringresult == null && astnode.children.get(2).operdataresult == null) {
			astnode.errorinfo = "右操作符计算结果不正确:" + astnode.children.get(2).nodeString;
			return false;
		}

		if (astnode.children.get(1).nodeString.equals("+") 
				|| astnode.children.get(1).nodeString.equals("-")
				|| astnode.children.get(1).nodeString.equals("*") 
				|| astnode.children.get(1).nodeString.equals("/")
				|| astnode.children.get(1).nodeString.equals("&&")
				|| astnode.children.get(1).nodeString.equals("||")
				|| astnode.children.get(1).nodeString.equals("==")
				|| astnode.children.get(1).nodeString.equals(">")
				|| astnode.children.get(1).nodeString.equals(">=")
				|| astnode.children.get(1).nodeString.equals("<")
				|| astnode.children.get(1).nodeString.equals("<=")				
				) {
			IDualOperator oper = operfactory.getIDualOperator(astnode.children.get(1).nodeString);
			if (oper != null) {
				// 找到了操作符对象
				oper.setAstNode(astnode);
				return oper.evaloperator();
			}
		}

		// 执行一个=号代表的赋值操作
		if (astnode.children.get(1).nodeString.equals("=")) {
			IDualOperator oper = operfactory.getIDualOperator(astnode.children.get(1).nodeString);
			if (oper != null) {
				// 找到了操作符对象,执行赋值操作
				oper.setAstNode(astnode);
				return oper.evalset(varmap);
			}
		}

		System.out.println("发现未定义的操作符:" + astnode.children.get(1).nodeString);
		astnode.errorinfo = ("发现未定义的操作符:" + astnode.children.get(1).nodeString);
		return false;
	}

	/**
	 * 为表达式解释器提供面向对象的对象调用功能
	 * 
	 * @return
	 */
	private boolean eval__objectcall(VarTableNodeV1_1 varmap) {
		// 有一个操作符是【.】，说明这是一个对象调用
		// 先调用第一个操作数，这个操作数是一个代表对象实例的变量名
		astnode.children.get(0).eval(varmap);
		Object callobj = astnode.children.get(0).operdataresult.getElementObjectValue();
		if (callobj == null) {
			System.out.println("【程序错误】对象调用出错，要调用的对象为null");
			return false;
		}
		// 拿到了callobj，这个对象现在不是null值了
		String methodname = astnode.children.get(2).nodeString;
		// 把methodname前后的引号去掉
		methodname = methodname.replaceAll("\"", "");
		methodname = methodname.replaceAll("\"", "");

		Method m = getCallMethod(callobj, methodname);
		if (m == null) {
			System.out.println("【程序错误】对象调用出错，查找不到执行方法指针.");
			return false;
		} else {
			System.out.println("找到了可以调用的方法指针");
			Object retObject;
			Object args[] = new Object[m.getParameterCount()];
			// 将调用参数压入到args[]
			// 这里需要进行判断

			for (int i = 0; i < args.length; i++) {
				OperElementData result = astnode.children.get(i + 3).operdataresult;
				args[i] = result.getObject();
			}

			try {
				// 开始进行JAVA类的动态调用
				retObject = m.invoke(callobj, args);
				System.out.println("【OK】JAVA类的动态调用成功了.");

				if (retObject != null) {
					System.out.println(methodname + "->" + retObject);
					// 把调用结果更新到operdataresult里面去
					Class retclass = retObject.getClass();
					System.out.println(retclass);
					if (retclass.equals(int.class) || retclass.equals(Integer.class)) {
						IntegerOperElement intoper = new IntegerOperElement();
						intoper.setValue(retObject.toString());
						astnode.operdataresult = intoper;
						return true;
					}
					if (retclass.equals(long.class) || retclass.equals(Long.class)) {
						LongOperElement longoper = new LongOperElement();
						longoper.setValue(retObject.toString());
						astnode.operdataresult = longoper;
						return true;
					}
					if (retclass.equals(float.class) || retclass.equals(Float.class)) {
						FloatOperElement floatoper = new FloatOperElement();
						floatoper.setValue(retObject.toString());
						astnode.operdataresult = floatoper;
						return true;
					}
					if (retclass.equals(double.class) || retclass.equals(Double.class)) {
						DoubleOperElement doubleoper = new DoubleOperElement();
						doubleoper.setValue(retObject.toString());
						astnode.operdataresult = doubleoper;
						return true;
					}
					if (retclass.equals(boolean.class) || retclass.equals(Boolean.class)) {
						BooleanOperElement booloper = new BooleanOperElement();
						booloper.setValue(retObject.toString());
						astnode.operdataresult = booloper;
						return true;
					}
					if (retclass.equals(String.class)) {
						StringOperElement stringoper = new StringOperElement();
						stringoper.setValue(retObject.toString());
						astnode.operdataresult = stringoper;
						return true;
					}
					OperElementData oed = new OperElementData();
					oed.setElementdatatype(retclass.toString());
					oed.setElementObjectValue(retObject);
					return true;
				} else {
					// 得到一个null值
					OperElementData oed = new OperElementData();
					oed.setElementdatatype("Class");
					oed.setElementObjectValue(null);
					return true;
				}

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("【错误】JAVA类的动态调用出错了.");
				return false;
			}
		}

	}

	/**
	 * 从指定的对象里面来通过反射方式获取所有的方法 然后检索所有的方法来和调用参数进行比较，查找匹配的方法
	 * 暂时采用相对比较简单的算法，只检索参数的数量保持一致即可 下一步针对方法的签名和所有的参数的返回类型进行匹配处理
	 * 
	 * @param callobj
	 * @return
	 */
	private Method getCallMethod(Object callobj, String methodname) {
		// 拿到了callobj，这个对象现在不是null值了
		Method[] publie_Method = callobj.getClass().getMethods();
		// TODO：暂时使用参数的数量进行匹配，
		// 数据类型的匹配下一步再做具体实现
		// 第一步先实现无参数的支持
		int argnum = astnode.children.size() - 3;
		for (Method m : publie_Method) {
			System.out.println("--------------->" + m.getName());
			if (m.getName().equals(methodname)) {
				if (m.getParameters().length == argnum) {
					return m;
				}
			}
		}
		return null;
	}

}
