package com.liu.smalljava.v1_1.test;

import java.util.HashMap;

import com.liu.smalljava.v1_1.expression.ASTTreeNodeV1_1;
import com.liu.smalljava.v1_1.vartable.VarTableNodeV1_1;

public class TestASTTreeNodeV1_1 {
	public static void main(String args[]) {
		// String sinfo ="1+2+3+4+5+6+7+8+9+10-5.0*6*1.5";
		// String sinfo="int i =10 + 1";
		// String sinfo ="String s1 = \"aaa\"";
//		String sinfo = "HashMap map1=new HashMap()";
//		ASTTreeNodeV1_1 node = new ASTTreeNodeV1_1(sinfo, 0);
//		node.analyseTree();
//		node.show();
//		VarTableNodeV1_1 varmap = new VarTableNodeV1_1(null);
//		varmap.defineVar("a", "int");
//		node.eval(varmap);
//		System.out.println("变量总数:" + varmap.getMyvarmap().size());
//		System.out.println("计算结果：" + node.getOperdataresult().toString());
		
		String sinfo ="1+2+3+4+5+6+7+8+9+10-5.0*6*1.5";
		ASTTreeNodeV1_1 node = new ASTTreeNodeV1_1(sinfo, 0);
		while(node.analyseOneNode()) {
			System.out.println("单步节点分析成功-------------------->");
			node.show();
		}
	}
}
