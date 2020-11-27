package com.liu.smalljava.v1_1.block;

import java.util.ArrayList;

/**
 * 代码块的抽象基类，其他代码块以此为基类进行扩展
 * @author liujunsong
 *
 */
public abstract class AbstractBlockV1_1 {
	
	/**
	 * 代码块的类型，决定了这个代码块如何分解执行的问题
	 * 取值范围包括：singleline(单行语句，以;结束)
	 * simpleblock(以{}分隔的block代码块)
	 * forblock(for(){} or for();)
	 * ifblock(if(){}else{})
	 * whileblock (while(){} or while();)
	 * dowhileblock (do()while())
	 * 
	 */
	private String blocktype="";
	/**
	 * 代码块的文本内容
	 */
	private String blockcontent="";
	
	/**
	 * 代码块的级别
	 */
	private int blocklevel=0;
	

	
	public String getBlocktype() {
		return blocktype;
	}

	public void setBlocktype(String blocktype) {
		this.blocktype = blocktype;
	}

	public String getBlockcontent() {
		return blockcontent;
	}

	public void setBlockcontent(String blockcontent) {
		this.blockcontent = blockcontent;
	}

	public int getBlocklevel() {
		return blocklevel;
	}

	public void setBlocklevel(int blocklevel) {
		this.blocklevel = blocklevel;
	}
	
}
