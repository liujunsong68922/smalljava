package com.liu.smalljava.v1_1.vartable;

import java.util.HashMap;

/**
 * 针对JAVA解释器设计的VarMap，
 * 
 * @author liujunsong
 *
 */
public class VarTableNodeV1_1 {
	
	public VarTableNodeV1_1(VarTableNodeV1_1 parent) {
		this.parentnode = parent;
	}
	
	/**
	 * 递归查找的父级节点，默认为null，代表是没有上级变量节点的
	 */
	private VarTableNodeV1_1 parentnode = null;

	/**
	 * 内部的HashMap存储，这个存储目前只存储基础类型，因此使用String来存储具体数据
	 */
	private HashMap<String, VarValueV1_1> myvarmap = new HashMap<String, VarValueV1_1>();

	public VarTableNodeV1_1 getParentnode() {
		return parentnode;
	}

	public void setParentnode(VarTableNodeV1_1 parentnode) {
		this.parentnode = parentnode;
	}

	public HashMap<String, VarValueV1_1> getMyvarmap() {
		return myvarmap;
	}

	public void setMyvarmap(HashMap<String, VarValueV1_1> myvarmap) {
		this.myvarmap = myvarmap;
	}

	/**
	 * 根据varname从VarMapNode中获取数值，如果找不到，则返回null,
	 * 
	 * @param varname
	 * @return
	 */
	public VarValueV1_1 getVarValue(String varname) {

		if (this.myvarmap.containsKey(varname)) {
			// 如果varname被定义为一个本地变量，则从本地HashMap中获取其值
			VarValueV1_1 varvalue = this.myvarmap.get(varname);
			return varvalue;
		}

		// 其他情况，意味着varname不是一个本地变量，那么尝试调用其父节点来获取数据
		VarTableNodeV1_1 pnode = this.getParentnode();
		while (pnode != null) {
			if (pnode.getMyvarmap().containsKey(varname)) {
				// 如果varname被定义为一个本地变量，则从本地HashMap中获取其值
				VarValueV1_1 varvalue = pnode.myvarmap.get(varname);
				return varvalue;
			}
			// 移动到父存储节点来进行处理
			pnode = pnode.parentnode;
		}

		// 循环查找，指到找不到varname,则返回null代表找不到这个变量
		return null;
	}

	// 在本地变量表中定义一个变量
	private boolean defineVar(String varname, VarValueV1_1 varvalue) {
		if (this.myvarmap.containsKey(varname)) {
			System.out.println("程序代码有误，此处有重复定义的变量名:" + varname);
			return false;
		}
		if (varvalue == null) {
			System.out.println("程序代码有误，定义变量调用为null");
			return false;
		}
		this.myvarmap.put(varname, varvalue);
		return true;
	}

	public boolean defineVar(String varname, String vartype) {
		if (this.myvarmap.containsKey(varname)) {
			System.out.println("程序代码有误，此处有重复定义的变量名:" + varname);
			return false;
		}
		if (vartype == null) {
			System.out.println("程序代码有误，变量定义时类型为空." + varname);
			return false;
		}
		VarValueV1_1 varvalue = new VarValueV1_1();
		varvalue.setVarname(varname);
		varvalue.setVartype(vartype);
		varvalue.setVardvalue(0.0);
		varvalue.setVarsvalue(null);
		varvalue.setVarbvalue(false);
		varvalue.setVarobjvalue(null);
		this.myvarmap.put(varname, varvalue);
		return true;
	}

	/**
	 * 变量赋值，首先找到这个变量，然后再赋值
	 * 
	 * @param varname
	 * @param varvalue
	 * @return
	 */
	public boolean setVarValue(String varname, VarValueV1_1 varvalue) {
		if (varname == null || varname.length() == 0) {
			System.out.println("setVarValue程序代码有误，变量名为空或者为空字符串");
			return false;
		}
		if (varvalue == null) {
			System.out.println("setVarValue程序代码有误，变量设置参数为null");
			return false;
		}
		VarValueV1_1 vvalue = this.getVarValue(varname);
		if (vvalue == null) {
			System.out.println("setVarValue程序执行有误，没有找到变量定义：" + varname);
			return false;
		} else {
			vvalue.setVarname(varname);
			vvalue.setVartype(varvalue.getVartype());
			vvalue.setVardvalue(varvalue.getVardvalue());
			vvalue.setVarsvalue(varvalue.getVarsvalue());
			vvalue.setVarbvalue(varvalue.isVarbvalue());
			vvalue.setVarobjvalue(varvalue.getVarobjvalue());
			return true;
		}
	}
}
