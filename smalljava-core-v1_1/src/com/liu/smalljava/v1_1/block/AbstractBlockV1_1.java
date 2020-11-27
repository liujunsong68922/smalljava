package com.liu.smalljava.v1_1.block;

import java.util.ArrayList;

/**
 * �����ĳ�����࣬����������Դ�Ϊ���������չ
 * @author liujunsong
 *
 */
public abstract class AbstractBlockV1_1 {
	
	/**
	 * ���������ͣ�����������������ηֽ�ִ�е�����
	 * ȡֵ��Χ������singleline(������䣬��;����)
	 * simpleblock(��{}�ָ���block�����)
	 * forblock(for(){} or for();)
	 * ifblock(if(){}else{})
	 * whileblock (while(){} or while();)
	 * dowhileblock (do()while())
	 * 
	 */
	private String blocktype="";
	/**
	 * �������ı�����
	 */
	private String blockcontent="";
	
	/**
	 * �����ļ���
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
