package com.liu.smalljava.v1_1.expression;

import com.liu.smalljava.v1_1.vartable.VarTableNodeV1_1;
import com.liu.smalljava.v1_1.vartable.VarValueV1_1;

public class DualOperator_Set extends DualAbstractOperator {

	@Override
	public boolean evaloperator() {
		//逻辑上不应该走到这个方法里面来
		System.out.println("【程序错误】，不应该调用=操作符号的这个方法");
		return false;
	}
	
	/**
	 * 赋值操作，需要传入变量表来进行寻址
	 * @param varmap
	 * @return
	 */
	@Override
	public boolean evalset(VarTableNodeV1_1 varmap) {
			System.out.println("开始赋值操作:" + astnode.children.get(0).stringresult);
			// 如果两方都是数字
			String s1 = astnode.children.get(0).stringresult;
			double d2 = 0;
			if (astnode.children.get(2).operdataresult == null) {
				System.out.println("赋值语句执行失败，右表达式计算结果为null");
				return false;
			}

			// 从变量表中检索数据

			VarValueV1_1 varvalue = varmap.getVarValue(s1);
			if (varvalue == null) {
				System.out.println("赋值语句执行失败，变量未定义:" + s1);
				return false;
			}
			if (astnode.children.get(2).operdataresult.getElementdatatype().equals("int")
					|| astnode.children.get(2).operdataresult.getElementdatatype().equals("long")
					|| astnode.children.get(2).operdataresult.getElementdatatype().equals("float")
					|| astnode.children.get(2).operdataresult.getElementdatatype().equals("double")) {

				varvalue.setVardvalue(Double.parseDouble(astnode.children.get(2).operdataresult.toString()));
			}
			varvalue.setVarsvalue("" + astnode.children.get(2).operdataresult.getElementStringValue());
			varvalue.setVarobjvalue(astnode.children.get(2).operdataresult.getElementObjectValue());

			// varmap.setVarValue(s1, varvalue);
			// 赋值操作成功以后，返回左面的对象

			astnode.operdataresult = astnode.children.get(2).operdataresult;
			return true;
	}

}
