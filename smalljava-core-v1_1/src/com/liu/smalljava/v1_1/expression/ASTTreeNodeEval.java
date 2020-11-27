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
 * ���ASTTreeNode�Ϸ�װeval��������ʵ�֣���ԭ��ASTTreeNode�����ֳ���
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
	 * ��AST���ֽ�����Ժ󣬰��ղ������ʽ�ļ�����������μ���booleanֵ�� �����������boolresult����
	 * ������㷢�������ʧ�ܣ�������Ϣ����errorinfo����
	 * 
	 * @return true����ɹ����� false���㷢������
	 */
	public boolean eval(VarTableNodeV1_1 varmap) {
		if (astnode.stringresult != null || astnode.operdataresult != null) {
			System.out.println("��ǰ�ڵ��Ѿ����������" + astnode.nodeString);
			return false;
		}

		// ���¼��ڵ�����,����ڵ��Ѿ���һ�������Ľڵ���
		// ��ʱ����Ľ��Ҫ�����stringresult����
		if (astnode.children == null || astnode.children.size() == 0) {
			return eval_nochild(varmap);
		}

		// ���ӽڵ�����
		// �����ǰ�ڵ�ֻ��һ��children
		if (astnode.children != null && astnode.children.size() == 1) {
			return eval_singlechild(varmap);
		}

		// ���ڴ�������child���������������Ƕ�Ԫ�жϲ�������
		// �����������������
		if (astnode.children != null && astnode.children.size() == 3
				&& !astnode.children.get(1).nodeString.equals(".")) {
			return eval_threechild(varmap);
		}

		// ����Ҫ������������ˣ�children����������3���Ʋ�Ӧ���Ƕ������
		if (astnode.children != null && astnode.children.get(1).getNodeString().equals(".")) {
			return eval__objectcall(varmap);
		}

		// �߼��ϲ�Ӧ���ܵ�������
		System.out.println("����������㿴������������߼��ͳ�����.");
		return false;
	}

	// ����ǰ�ڵ��Ѿ���ĩ���ڵ㣬û���ӽڵ�ʱ����������
	private boolean eval_nochild(VarTableNodeV1_1 varmap) {
		System.out.println("���ӽڵ���㿪ʼ��������" + astnode.nodeString);
		String sinfo = astnode.nodeString.trim();
		sinfo = new StringFindUtil().trimReturnAndSpace(sinfo);
		if (sinfo.length() == 0) {
			// ���ַ���˵���������жϴ���
			astnode.errorinfo = "������󣬷����˿սڵ�,����";
			return false;
		}

		// ����ַ�����"��ͷ,��ô����ʹ�����һ���ַ�������
		//
		if (sinfo.charAt(0) == '\"') {
			astnode.stringresult = sinfo;
			StringOperElement oper1 = new StringOperElement(sinfo);
			astnode.operdataresult = oper1;
			return true;
		}

		// ����ַ���������1-9��ͷ���ʹ��������һ����ֵ����
		if (sinfo.charAt(0) >= '0' && sinfo.charAt(0) <= '9') {
			astnode.stringresult = sinfo;
			// ������Ҫ�ж����ֵ��int,long,float,double
			sinfo = sinfo.trim();
			if (sinfo.endsWith("L") || sinfo.endsWith("l")) {
				// ���ճ����ʹ���
				LongOperElement oper1 = new LongOperElement(sinfo);
				astnode.operdataresult = oper1;
			} else if (sinfo.endsWith("D") || sinfo.endsWith("d")) {
				// ����˫���Ƚ��д���
				DoubleOperElement oper2 = new DoubleOperElement(sinfo);
				astnode.operdataresult = oper2;
			} else if (sinfo.endsWith("F") || sinfo.endsWith("f")) {
				// ���յ����Ƚ��д���
				FloatOperElement oper3 = new FloatOperElement(sinfo);
				astnode.operdataresult = oper3;
			} else if (sinfo.indexOf(".") >= 0) {
				// ���յ����Ƚ��д���
				FloatOperElement oper4 = new FloatOperElement(sinfo);
				astnode.operdataresult = oper4;
			} else {
				IntegerOperElement oper5 = new IntegerOperElement(sinfo);
				astnode.operdataresult = oper5;
			}
			return true;
		}

		// ������Ҫ�ж��Ƿ��Ǳ�������
		sinfo = new StringFindUtil().trimReturnAndSpace(sinfo);

		// ���sinfo�м��ɿո�ָ�����ô�Ϳ�����Ϊ�Ǳ�������
		String sdata[] = sinfo.split(" ");
		System.out.println(sdata.length);
		if (sdata.length == 2) {
			if (sdata[0].equals("new")) {
				// ���ݺ������ඨ��������һ���¶���
				// ��������¶���ŵ�����������ȥ��
				// ��������¶�����õ���Ӧ��varmaps����ȥ
				return eval_nochild_newobject(sinfo, varmap);
			} else {
				return eval_nochild_vardefine(sinfo, varmap);
			}
		}

		// �����������Ϊ���������д�����ʱ��Ҫ�Ӵ���ı������н��ж�Ӧ�����滻
		// ����Ҳ��������������ô�ͱ���������
		// �˴���ʱ�����ǻ������ݿ�ĺ�������͵�������
		// ���������ļ򻯴���
		// ���Ե��ò�����Ҫ����RS�����а���key-value��ӳ����������Ӧ�滻
		// �˴�������������滻����ʱ������������ͻ����
		// Ҳ�����Ǵ�Сд���⣬Ҫ���Сд��ȷƥ��

		// �����滻��һ��ǰ�����������ǵ�ǰ�ڵ㲻��һ������
		if (astnode.isVarflag()) {
			// do nothing
			System.out.println("---->����һ������ֵ�ı���:" + sinfo);
			astnode.stringresult = sinfo;
			return true;
		}

		// ������Ҫ������Զ���ı�������
		// �ڶ������֮ǰ����Ҫ�Ƚ�����������AST�ڵ�ֽ�ʱ���ֽ����Ԫ������
		// 1.�������ʱ�����Ǿ�̬����ã���΢����һ�㣬��Ҫ��������),��varmap�ж����ȡ
		// 2����.������������ʱֻ֧��һ������������Ȳ���������������
		// 3.������
		// 4.������������������ʱҲֻ���ǳ����ͱ������������ڵ���ʱ�ٽ��ж��μ��������
		// �����Ļ�����AST�ֽ��ʱ���Ѿ�����˷ֽ⣬�������ڼ���ʱ���зֽ�

		// �ڶ���ı�����������Ժ�����Զ�������ַ����滻
		System.out.println("���ҵ����Ʊ�����:" + sinfo + ",���Խ����ַ����滻");
		VarValueV1_1 varvalue = varmap.getVarValue(sinfo);

		if (varvalue != null) {
			// ������Ҫ�ж�svalue�ǲ�����ֵ
			// ������Ҫ����VarValue���ص����������������ж����
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
			System.out.println("��������ʧ��:" + sinfo);
			astnode.errorinfo = "��������ʧ��:" + sinfo;
			return false;
		}

	}

	private boolean eval_nochild_newobject(String sinfo, VarTableNodeV1_1 varmap) {
		System.out.println("��ʼ�������ʵ����������");
		String sdata[] = sinfo.split(" ");
		if (sdata.length != 2) {
			System.out.println("���������ʵ�����﷨����!" + sinfo);
			return false;
		}
		if (!sdata[0].equals("new")) {
			System.out.println("���������ʵ�����﷨�����Ҳ�����new���ؼ���");
			return false;
		}
		String classname = sdata[1].trim();
		// ȥ��classname�����()��ע��������ʱֻ֧���������Ĺ��캯��
		classname = classname.substring(0, classname.length() - 2);

		if (classname.length() == 0) {
			System.out.println("���������ʵ�����﷨�����Ҳ���Ҫʵ������class");
			return false;
		}

		// ����classname��classmap������������ԭʼ��class����
		ClassTable classtable = new ClassTable();
		Class c1 = classtable.getClass(classname);
		if (c1 == null) {
			// �Ҳ����ඨ��
			System.out.println("�������ʵ����ʱ�Ҳ���ָ������:" + classname);
			return false;
		} else {
			// ���ʵ��������
			try {
				Object objvalue = c1.newInstance();
				// ��ִ��=��ֵ����ʱ����д��������У��˴���������д��resultobject
				OperElementData operdata = new OperElementData();
				operdata.setElementdatatype(classname);
				operdata.setElementObjectValue(objvalue);
				astnode.operdataresult = operdata;

				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// ��ʵ����ʱʧ��
				System.out.println("��ʵ����ʱʧ��." + classname);
				return false;
			}
		}
	}

	/**
	 * �ж��Ƿ��Ǳ������壬��������ڱ������ж������
	 * 
	 * @param varmap
	 * @return
	 */
	private boolean eval_nochild_vardefine(String sinfo, VarTableNodeV1_1 varmap) {
		System.out.println("��ʼ���б������塣����");
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

		// ��������£���Ҫ����sdata[0]�����Ҷ�Ӧ��JAVA�࣬��ɱ�������
		// ������漰���ǳ��ؼ����������Ĵ�������
		ClassTable classtable = new ClassTable();
		Class c1 = classtable.getClass(sdata[0]);
		if (c1 == null) {
			System.out.println("����δ�����Java�ࣺ" + sdata[0]);
			return false;
		} else {
			astnode.stringresult = varname;
			// ���ڱ������ж���һ���¶������ƣ���δ�����ڴ�
			// �����ڴ���Ҫʹ��new �ؼ��������з���
			// String �������⣬����ʹ��= �ؼ�������ֵ����
			varmap.defineVar(varname, sdata[0]);
			System.out.println("�����������");
		}
		return true;
	}

	/**
	 * ����ǰ�ڵ����Ψһһ��child��ʱ��Ĵ���ʽ
	 * 
	 * @param varmap
	 * @return
	 */
	private boolean eval_singlechild(VarTableNodeV1_1 varmap) {
		System.out.println("���ӽڵ���㿪ʼ��������");
		// �����¼�����ڵ㣬��������������浽�����Ľ��������
		if (astnode.children.get(0).operdataresult == null && astnode.children.get(0).stringresult == null) {
			// ���������룬�ݹ���б����滻����
			if (astnode.children.get(0).eval(varmap)) {
				// ������������true
				astnode.operdataresult = astnode.children.get(0).operdataresult;
				astnode.stringresult = astnode.children.get(0).stringresult;
				return true;
			} else {
				// ����ʧ�ܣ�����false;
				astnode.errorinfo = astnode.children.get(0).errorinfo;
			}
		} else {
			System.out.println("�����ӽڵ��Ѿ��������.,");
			astnode.operdataresult = astnode.children.get(0).operdataresult;
			astnode.stringresult = astnode.children.get(0).stringresult;
			astnode.errorinfo = astnode.children.get(0).errorinfo;
		}
		return true;

	}

	/**
	 * ��Ԫ������Ĵ������
	 * 
	 * @param varmap
	 * @return
	 */
	private boolean eval_threechild(VarTableNodeV1_1 varmap) {
		IDualOperatorFactory operfactory = new IDualOperatorFactory();

		System.out.println("3�������ӽڵ�ļ��㿪ʼ������");
		boolean leftflag = astnode.children.get(0).eval(varmap);
		if (!leftflag) {
			// �����������ʧ��
			System.out.println("�����������ʧ��");
			astnode.errorinfo = astnode.children.get(0).errorinfo;
			return false;
		}

		boolean rightflag = astnode.children.get(2).eval(varmap);
		if (!rightflag) {
			// �Ҳ���������ʧ��
			System.out.println("�Ҳ���������ʧ�ܣ�-->" + astnode.children.get(2).nodeString);
			astnode.errorinfo = astnode.children.get(2).errorinfo;
			return false;
		}

		if (astnode.children.get(0).stringresult == null && astnode.children.get(0).operdataresult == null) {
			astnode.errorinfo = "�����������������ȷ:" + astnode.children.get(0).nodeString;
			return false;
		}

		if (astnode.children.get(2).stringresult == null && astnode.children.get(2).operdataresult == null) {
			astnode.errorinfo = "�Ҳ���������������ȷ:" + astnode.children.get(2).nodeString;
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
				// �ҵ��˲���������
				oper.setAstNode(astnode);
				return oper.evaloperator();
			}
		}

		// ִ��һ��=�Ŵ���ĸ�ֵ����
		if (astnode.children.get(1).nodeString.equals("=")) {
			IDualOperator oper = operfactory.getIDualOperator(astnode.children.get(1).nodeString);
			if (oper != null) {
				// �ҵ��˲���������,ִ�и�ֵ����
				oper.setAstNode(astnode);
				return oper.evalset(varmap);
			}
		}

		System.out.println("����δ����Ĳ�����:" + astnode.children.get(1).nodeString);
		astnode.errorinfo = ("����δ����Ĳ�����:" + astnode.children.get(1).nodeString);
		return false;
	}

	/**
	 * Ϊ���ʽ�������ṩ�������Ķ�����ù���
	 * 
	 * @return
	 */
	private boolean eval__objectcall(VarTableNodeV1_1 varmap) {
		// ��һ���������ǡ�.����˵������һ���������
		// �ȵ��õ�һ���������������������һ���������ʵ���ı�����
		astnode.children.get(0).eval(varmap);
		Object callobj = astnode.children.get(0).operdataresult.getElementObjectValue();
		if (callobj == null) {
			System.out.println("��������󡿶�����ó���Ҫ���õĶ���Ϊnull");
			return false;
		}
		// �õ���callobj������������ڲ���nullֵ��
		String methodname = astnode.children.get(2).nodeString;
		// ��methodnameǰ�������ȥ��
		methodname = methodname.replaceAll("\"", "");
		methodname = methodname.replaceAll("\"", "");

		Method m = getCallMethod(callobj, methodname);
		if (m == null) {
			System.out.println("��������󡿶�����ó������Ҳ���ִ�з���ָ��.");
			return false;
		} else {
			System.out.println("�ҵ��˿��Ե��õķ���ָ��");
			Object retObject;
			Object args[] = new Object[m.getParameterCount()];
			// �����ò���ѹ�뵽args[]
			// ������Ҫ�����ж�

			for (int i = 0; i < args.length; i++) {
				OperElementData result = astnode.children.get(i + 3).operdataresult;
				args[i] = result.getObject();
			}

			try {
				// ��ʼ����JAVA��Ķ�̬����
				retObject = m.invoke(callobj, args);
				System.out.println("��OK��JAVA��Ķ�̬���óɹ���.");

				if (retObject != null) {
					System.out.println(methodname + "->" + retObject);
					// �ѵ��ý�����µ�operdataresult����ȥ
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
					// �õ�һ��nullֵ
					OperElementData oed = new OperElementData();
					oed.setElementdatatype("Class");
					oed.setElementObjectValue(null);
					return true;
				}

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("������JAVA��Ķ�̬���ó�����.");
				return false;
			}
		}

	}

	/**
	 * ��ָ���Ķ���������ͨ�����䷽ʽ��ȡ���еķ��� Ȼ��������еķ������͵��ò������бȽϣ�����ƥ��ķ���
	 * ��ʱ������ԱȽϼ򵥵��㷨��ֻ������������������һ�¼��� ��һ����Է�����ǩ�������еĲ����ķ������ͽ���ƥ�䴦��
	 * 
	 * @param callobj
	 * @return
	 */
	private Method getCallMethod(Object callobj, String methodname) {
		// �õ���callobj������������ڲ���nullֵ��
		Method[] publie_Method = callobj.getClass().getMethods();
		// TODO����ʱʹ�ò�������������ƥ�䣬
		// �������͵�ƥ����һ����������ʵ��
		// ��һ����ʵ���޲�����֧��
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
