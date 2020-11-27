package com.liu.smalljava.v1_1.expression;

/**
 * 所有二元运算符的工厂类定义
 * 
 * @author liujunsong
 *
 */
public class IDualOperatorFactory {
	/**
	 * 根据运算符来返回特定的二元运算符算法逻辑对象
	 * 
	 * @param operator
	 * @return
	 */
	public IDualOperator getIDualOperator(String operator) {
		if (operator.equals("+")) {
			// 返回加法运算符
			return new DualOperator_Add();
		}
		
		if (operator.equals("-")) {
			// 返回减法运算符
			return new DualOperator_DeAdd();
		}

		if (operator.equals("*")) {
			//返回乘法运算符
			return new DualOperator_Multi();
		}
		
		if(operator.equals("/")) {
			//返回除法运算符
			return new DualOperator_Devide();
		}
		
		if(operator.equals("&&")) {
			//返回逻辑与运算符
			return new DualOperator_LogicAnd();
		}
		
		if(operator.equals("||")) {
			//返回逻辑或
			return new DualOperator_LogicOR();
		}
		
		if(operator.equals("==")) {
			//逻辑相等
			return new DualOperator_LogicEqual();
		}
		
		if(operator.equals(">")) {
			// >
			return new DualOperator_LogicGreater();
		}
		
		if(operator.equals(">=")) {
			// >
			return new DualOperator_LogicGreaterEqual();
		}	
		
		if(operator.equals("<")) {
			// >
			return new DualOperator_LogicLittle();
		}
		
		if(operator.equals("<=")) {
			// >
			return new DualOperator_LogicLittleEqual();
		}
		
		if(operator.equals("=")) {
			// = 代表一个变量赋值操作
			return new DualOperator_Set();
		}
		
		System.out.println("【程序调用错误】，无法理解的二元操作符:" + operator);
		return null;
	}
}
