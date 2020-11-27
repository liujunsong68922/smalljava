package com.liu.smalljava.v1_1;

/**
 * 字符串内查找特定字符串的函数方法 因为需要考虑到双引号的存在，需要过滤掉双引号，单引号之内的特殊字符
 * 
 * @author liujunsong
 *
 */
public class StringFindUtil {
	// -1代表没有找到
	// 参数错误时，在控制台打印出错误信息出来，暂时不抛出异常
	public int findfirstStringForAST(String s1, String s2) {
		// NULL值检查
		if (s1 == null) {
			System.out.println("ERROR: StringFindUtil.findfirstString,s1 is null");
			return -1;
		}

		if (s2 == null) {
			System.out.println("ERROR: StringFindUtil.findfirstString,s2 is null");
			return -1;
		}

		// empty string 检查
		if (s1.equals("")) {
			System.out.println("ERROR: StringFindUtil.findfirstString,s1 is empty");
			return -1;
		}

		if (s2.equals("")) {
			System.out.println("ERROR: StringFindUtil.findfirstString,s2 is empty");
			return -1;
		}

		// 定义双引号和单引号的计数器
		int doublequotecount = 0;
		int singlequotecount = 0;
		
		// 定义左括号，右括号的计数器
		// 在括号内的运算符【左右括号除外】不参与查找
		int leftParenthesescount =0;
		int rightParenthesescount = 0;

		// 定义开始查找位置
		int ibeginpos = 0;
		// 定义结束查找位置
		int iendpos = s1.length() - s2.length() ;

		int ipos;
		for (ipos = ibeginpos; ipos < iendpos; ipos++) {
			// 如果当前位置是转义符，跳过下一字符
			if (s1.substring(ipos, ipos+1).equals("\\")) {
				ipos++;
				// 跳过当前字符，继续循环，在循环终止条件将跳过下一字符
				continue;
			}

			// 判断当前位置是否是单引号或者双引号，如果是，则计数器+1
			if (s1.substring(ipos, ipos+1).equals("'")) {
				singlequotecount++;
			} else if (s1.substring(ipos, ipos+1).equals("\"")) {
				doublequotecount++;
			}

			// 如果单引号，双引号均已经闭合，则进行字符串检查判断
			if (singlequotecount % 2 == 0 && doublequotecount % 2 == 0) {
				//在引号包含之外，对左右括号进行计数
				if(s1.substring(ipos, ipos+1).equals("(")) {
					leftParenthesescount++;
				}
				if(s1.substring(ipos, ipos+1).equals(")")) {
					rightParenthesescount++;
				}
				
				//如果当前处理模式在括号内，那么就不处理和比较
				if(leftParenthesescount  != rightParenthesescount) {
					continue;
				}
				
				if (s1.substring(ipos, ipos + s2.length()).equals(s2)) {
					// 找到了这个字符串,返回此位置
					// 从原理上讲，应该进行Token标准切分，确保找到的是一个独立的关键词
					// 而不是另外一个关键词的一部分
					
					return ipos;
				}
			}
			// 没有找到字符串，循环等待
			// goto loop condition.
		}

		// not found,return -1;
		return -1;
	}
	
	// -1代表没有找到
	// 参数错误时，在控制台打印出错误信息出来，暂时不抛出异常
	// 代码块分析时提供的字符串查找算法
	// 规则如下：
	// 1. 单引号内跳过
	// 2.双引号内跳过
	// 3.（）内跳过
	// 4 {}内跳过
	public int findfirstStringForBlock(String s1, String s2) {
		// NULL值检查
		if (s1 == null) {
			System.out.println("ERROR: StringFindUtil.findfirstStringForBlock,s1 is null");
			return -1;
		}

		if (s2 == null) {
			System.out.println("ERROR: StringFindUtil.findfirstStringForBlock,s2 is null");
			return -1;
		}

		// empty string 检查
		if (s1.equals("")) {
			System.out.println("ERROR: StringFindUtil.findfirstStringForBlock,s1 is empty");
			return -1;
		}

		if (s2.equals("")) {
			System.out.println("ERROR: StringFindUtil.findfirstStringForBlock,s2 is empty");
			return -1;
		}

		// 定义双引号和单引号的计数器
		int doublequotecount = 0;
		int singlequotecount = 0;
		
		// 定义左括号，右括号的计数器
		// 在括号内的运算符【左右括号除外】不参与查找
		int leftParenthesescount =0;
		int rightParenthesescount = 0;

		// 定义左大括号，右大括号的计数器
		int leftBracketcount=0;
		int rightBracketcount=0;
		
		// 定义开始查找位置
		int ibeginpos = 0;
		// 定义结束查找位置
		int iendpos = s1.length() - s2.length() ;

		int ipos;
		for (ipos = ibeginpos; ipos <= iendpos; ipos++) {
			// 如果当前位置是转义符，跳过下一字符
			if (s1.substring(ipos, ipos+1).equals("\\")) {
				ipos++;
				// 跳过当前字符，继续循环，在循环终止条件将跳过下一字符
				continue;
			}

			// 判断当前位置是否是单引号或者双引号，如果是，则计数器+1
			if (s1.substring(ipos, ipos+1).equals("'")) {
				singlequotecount++;
			} else if (s1.substring(ipos, ipos+1).equals("\"")) {
				doublequotecount++;
			}

			// 如果单引号，双引号均已经闭合，则进行字符串检查判断
			if (singlequotecount % 2 == 0 && doublequotecount % 2 == 0) {
				//在引号包含之外，对左右括号进行计数
				if(s1.substring(ipos, ipos+1).equals("(")) {
					leftParenthesescount++;
				}
				if(s1.substring(ipos, ipos+1).equals(")")) {
					rightParenthesescount++;
				}
				if(s1.substring(ipos, ipos+1).equals("{")) {
					leftBracketcount++;
				}
				if(s1.substring(ipos, ipos+1).equals("}")) {
					rightBracketcount++;
				}
				
				//如果当前处理模式在括号内，那么就不处理和比较
				if(leftParenthesescount  != rightParenthesescount) {
					//第一个左括号是可以返回的
					if(leftParenthesescount==1 && rightParenthesescount==0 && s2.equals("(")) {
						return ipos;
					}
					continue;
				}
				
				//如果当前处理模式在大括号内，那么就不处理和比较
				if(leftBracketcount != rightBracketcount) {
					//第一个左大括号是可以返回的
					if(rightBracketcount==0 && leftBracketcount==1 && s2.equals("{") ) {
						return ipos;
					}
					continue;
				}
				
				
				if (s1.substring(ipos, ipos + s2.length()).equals(s2)) {
					// 找到了这个字符串,返回此位置
					// TODO：此处需要做额外的判断，确定当前查找到的位置是一个独立的位置节点，而不是一个变量的一部分
					// 例如要查找 and ,那么要排除land,hand,and1 之类
					// 因此可能还是需要对原始字符串先进行一次分词操作，以确保找到的位置是一个有效位置
					// 这部分代码待下一步补充。
					return ipos;
				}
			}
			// 没有找到字符串，循环等待
			// goto loop condition.
		}

		// not found,return -1;
		return -1;
	}

	/**
	 * 将字符串开始和结束位置的\r\n ,\r,空格都过滤掉
	 * 
	 * @param strinput
	 * @return
	 */
	public String trimReturnAndSpace(String strinput) {
		String sout = "";
		// 先查找第一个不是\r\n \r 空格的位置
		int ipos = -1;
		for (int i = 0; i < strinput.length(); i++) {
			if (strinput.charAt(i) == '\r' || strinput.charAt(i) == '\n' || strinput.charAt(i) == ' ') {
				// 继续循环
				continue;
			} else {
				ipos = i;
				break;
			}
		}

		if (ipos == -1) {
			// 没有找到有效字符
			return "";
		}

		// 开始从后往前查找第一个有效字符
		int ipos2 = -1;
		for (int i = strinput.length() - 1; i >= 0; i--) {
			if (strinput.charAt(i) == '\r' || strinput.charAt(i) == '\n' || strinput.charAt(i) == ' ') {
				// 继续循环
				continue;
			} else {
				ipos2 = i;
				break;
			}
		}

		// 由于ipos有效，所以ipos2一定也是有效的
		if (ipos2 >= ipos) {
			return strinput.substring(ipos, ipos2 + 1);
		} else {
			System.out.println("程序执行出现错误，需要查找问题所在.ipos,ipos2=" + ipos + "," + ipos2);
			return "";
		}
	}

}
