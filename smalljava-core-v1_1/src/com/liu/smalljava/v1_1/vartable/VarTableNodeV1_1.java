package com.liu.smalljava.v1_1.vartable;

import java.util.HashMap;

/**
 * ���JAVA��������Ƶ�VarMap��
 * 
 * @author liujunsong
 *
 */
public class VarTableNodeV1_1 {
	
	public VarTableNodeV1_1(VarTableNodeV1_1 parent) {
		this.parentnode = parent;
	}
	
	/**
	 * �ݹ���ҵĸ����ڵ㣬Ĭ��Ϊnull��������û���ϼ������ڵ��
	 */
	private VarTableNodeV1_1 parentnode = null;

	/**
	 * �ڲ���HashMap�洢������洢Ŀǰֻ�洢�������ͣ����ʹ��String���洢��������
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
	 * ����varname��VarMapNode�л�ȡ��ֵ������Ҳ������򷵻�null,
	 * 
	 * @param varname
	 * @return
	 */
	public VarValueV1_1 getVarValue(String varname) {

		if (this.myvarmap.containsKey(varname)) {
			// ���varname������Ϊһ�����ر�������ӱ���HashMap�л�ȡ��ֵ
			VarValueV1_1 varvalue = this.myvarmap.get(varname);
			return varvalue;
		}

		// �����������ζ��varname����һ�����ر�������ô���Ե����丸�ڵ�����ȡ����
		VarTableNodeV1_1 pnode = this.getParentnode();
		while (pnode != null) {
			if (pnode.getMyvarmap().containsKey(varname)) {
				// ���varname������Ϊһ�����ر�������ӱ���HashMap�л�ȡ��ֵ
				VarValueV1_1 varvalue = pnode.myvarmap.get(varname);
				return varvalue;
			}
			// �ƶ������洢�ڵ������д���
			pnode = pnode.parentnode;
		}

		// ѭ�����ң�ָ���Ҳ���varname,�򷵻�null�����Ҳ����������
		return null;
	}

	// �ڱ��ر������ж���һ������
	private boolean defineVar(String varname, VarValueV1_1 varvalue) {
		if (this.myvarmap.containsKey(varname)) {
			System.out.println("����������󣬴˴����ظ�����ı�����:" + varname);
			return false;
		}
		if (varvalue == null) {
			System.out.println("����������󣬶����������Ϊnull");
			return false;
		}
		this.myvarmap.put(varname, varvalue);
		return true;
	}

	public boolean defineVar(String varname, String vartype) {
		if (this.myvarmap.containsKey(varname)) {
			System.out.println("����������󣬴˴����ظ�����ı�����:" + varname);
			return false;
		}
		if (vartype == null) {
			System.out.println("����������󣬱�������ʱ����Ϊ��." + varname);
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
	 * ������ֵ�������ҵ����������Ȼ���ٸ�ֵ
	 * 
	 * @param varname
	 * @param varvalue
	 * @return
	 */
	public boolean setVarValue(String varname, VarValueV1_1 varvalue) {
		if (varname == null || varname.length() == 0) {
			System.out.println("setVarValue����������󣬱�����Ϊ�ջ���Ϊ���ַ���");
			return false;
		}
		if (varvalue == null) {
			System.out.println("setVarValue����������󣬱������ò���Ϊnull");
			return false;
		}
		VarValueV1_1 vvalue = this.getVarValue(varname);
		if (vvalue == null) {
			System.out.println("setVarValue����ִ������û���ҵ��������壺" + varname);
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
