package com.liu.smalljava.v1_1.classtable;

import java.util.HashMap;

/**
 * 这里的Map保存了在运行时的Class定义
 * 在实际使用中，根据import 语句的解析来实际更新这个列表
 * @author liujunsong
 *
 */
public class ClassTable {
	private HashMap<String,Class> classmap = new HashMap<String,Class>();

	public HashMap<String, Class> getClassmap() {
		return classmap;
	}

	public void setClassmap(HashMap<String, Class> classmap) {
		this.classmap = classmap;
	}
	
	public ClassTable() {
		classmap.put("String", String.class);
		classmap.put("HashMap", HashMap.class);
	}
	
	public Class getClass(String name) {
		if(this.classmap.containsKey(name)) {
			return this.classmap.get(name);
		}else {
			return null;
		}
	}
}
