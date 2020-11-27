package com.liu.smalljava.v1_1.test;

import com.liu.smalljava.v1_1.block.BaseBlockNodeV1_1;

public class TestBaseBlockNodeV1_1 {
	public static void main(String args[]) {
		String scode="";
		scode +="int i=1.5;int j=1*i;";
		//scode += " if(2>1) {i=100*4;} else { i=200;}";
		//scode += " int j=i*2;";
		//scode += " i = 0;";
		//scode += "for(i=1;i<100;i++) { i = i+1;}";
		//scode += "while(i<100) { j=i*i;i=i+1;}";
		//scode += "do { j=j+i;i=i+1;  } while(i<10000)";
		//scode += ";;;;";
		//scode += "for(i=i;i<20;i=i+1){j=j*i;}";
		BaseBlockNodeV1_1 node = new BaseBlockNodeV1_1(scode, 0, "normal");
		node.anylyse();
		node.execute();
	}
}
