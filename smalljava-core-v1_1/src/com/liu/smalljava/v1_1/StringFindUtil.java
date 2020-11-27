package com.liu.smalljava.v1_1;

/**
 * �ַ����ڲ����ض��ַ����ĺ������� ��Ϊ��Ҫ���ǵ�˫���ŵĴ��ڣ���Ҫ���˵�˫���ţ�������֮�ڵ������ַ�
 * 
 * @author liujunsong
 *
 */
public class StringFindUtil {
	// -1����û���ҵ�
	// ��������ʱ���ڿ���̨��ӡ��������Ϣ��������ʱ���׳��쳣
	public int findfirstStringForAST(String s1, String s2) {
		// NULLֵ���
		if (s1 == null) {
			System.out.println("ERROR: StringFindUtil.findfirstString,s1 is null");
			return -1;
		}

		if (s2 == null) {
			System.out.println("ERROR: StringFindUtil.findfirstString,s2 is null");
			return -1;
		}

		// empty string ���
		if (s1.equals("")) {
			System.out.println("ERROR: StringFindUtil.findfirstString,s1 is empty");
			return -1;
		}

		if (s2.equals("")) {
			System.out.println("ERROR: StringFindUtil.findfirstString,s2 is empty");
			return -1;
		}

		// ����˫���ź͵����ŵļ�����
		int doublequotecount = 0;
		int singlequotecount = 0;
		
		// ���������ţ������ŵļ�����
		// �������ڵ���������������ų��⡿���������
		int leftParenthesescount =0;
		int rightParenthesescount = 0;

		// ���忪ʼ����λ��
		int ibeginpos = 0;
		// �����������λ��
		int iendpos = s1.length() - s2.length() ;

		int ipos;
		for (ipos = ibeginpos; ipos < iendpos; ipos++) {
			// �����ǰλ����ת�����������һ�ַ�
			if (s1.substring(ipos, ipos+1).equals("\\")) {
				ipos++;
				// ������ǰ�ַ�������ѭ������ѭ����ֹ������������һ�ַ�
				continue;
			}

			// �жϵ�ǰλ���Ƿ��ǵ����Ż���˫���ţ�����ǣ��������+1
			if (s1.substring(ipos, ipos+1).equals("'")) {
				singlequotecount++;
			} else if (s1.substring(ipos, ipos+1).equals("\"")) {
				doublequotecount++;
			}

			// ��������ţ�˫���ž��Ѿ��պϣ�������ַ�������ж�
			if (singlequotecount % 2 == 0 && doublequotecount % 2 == 0) {
				//�����Ű���֮�⣬���������Ž��м���
				if(s1.substring(ipos, ipos+1).equals("(")) {
					leftParenthesescount++;
				}
				if(s1.substring(ipos, ipos+1).equals(")")) {
					rightParenthesescount++;
				}
				
				//�����ǰ����ģʽ�������ڣ���ô�Ͳ�����ͱȽ�
				if(leftParenthesescount  != rightParenthesescount) {
					continue;
				}
				
				if (s1.substring(ipos, ipos + s2.length()).equals(s2)) {
					// �ҵ�������ַ���,���ش�λ��
					// ��ԭ���Ͻ���Ӧ�ý���Token��׼�з֣�ȷ���ҵ�����һ�������Ĺؼ���
					// ����������һ���ؼ��ʵ�һ����
					
					return ipos;
				}
			}
			// û���ҵ��ַ�����ѭ���ȴ�
			// goto loop condition.
		}

		// not found,return -1;
		return -1;
	}
	
	// -1����û���ҵ�
	// ��������ʱ���ڿ���̨��ӡ��������Ϣ��������ʱ���׳��쳣
	// ��������ʱ�ṩ���ַ��������㷨
	// �������£�
	// 1. ������������
	// 2.˫����������
	// 3.����������
	// 4 {}������
	public int findfirstStringForBlock(String s1, String s2) {
		// NULLֵ���
		if (s1 == null) {
			System.out.println("ERROR: StringFindUtil.findfirstStringForBlock,s1 is null");
			return -1;
		}

		if (s2 == null) {
			System.out.println("ERROR: StringFindUtil.findfirstStringForBlock,s2 is null");
			return -1;
		}

		// empty string ���
		if (s1.equals("")) {
			System.out.println("ERROR: StringFindUtil.findfirstStringForBlock,s1 is empty");
			return -1;
		}

		if (s2.equals("")) {
			System.out.println("ERROR: StringFindUtil.findfirstStringForBlock,s2 is empty");
			return -1;
		}

		// ����˫���ź͵����ŵļ�����
		int doublequotecount = 0;
		int singlequotecount = 0;
		
		// ���������ţ������ŵļ�����
		// �������ڵ���������������ų��⡿���������
		int leftParenthesescount =0;
		int rightParenthesescount = 0;

		// ����������ţ��Ҵ����ŵļ�����
		int leftBracketcount=0;
		int rightBracketcount=0;
		
		// ���忪ʼ����λ��
		int ibeginpos = 0;
		// �����������λ��
		int iendpos = s1.length() - s2.length() ;

		int ipos;
		for (ipos = ibeginpos; ipos <= iendpos; ipos++) {
			// �����ǰλ����ת�����������һ�ַ�
			if (s1.substring(ipos, ipos+1).equals("\\")) {
				ipos++;
				// ������ǰ�ַ�������ѭ������ѭ����ֹ������������һ�ַ�
				continue;
			}

			// �жϵ�ǰλ���Ƿ��ǵ����Ż���˫���ţ�����ǣ��������+1
			if (s1.substring(ipos, ipos+1).equals("'")) {
				singlequotecount++;
			} else if (s1.substring(ipos, ipos+1).equals("\"")) {
				doublequotecount++;
			}

			// ��������ţ�˫���ž��Ѿ��պϣ�������ַ�������ж�
			if (singlequotecount % 2 == 0 && doublequotecount % 2 == 0) {
				//�����Ű���֮�⣬���������Ž��м���
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
				
				//�����ǰ����ģʽ�������ڣ���ô�Ͳ�����ͱȽ�
				if(leftParenthesescount  != rightParenthesescount) {
					//��һ���������ǿ��Է��ص�
					if(leftParenthesescount==1 && rightParenthesescount==0 && s2.equals("(")) {
						return ipos;
					}
					continue;
				}
				
				//�����ǰ����ģʽ�ڴ������ڣ���ô�Ͳ�����ͱȽ�
				if(leftBracketcount != rightBracketcount) {
					//��һ����������ǿ��Է��ص�
					if(rightBracketcount==0 && leftBracketcount==1 && s2.equals("{") ) {
						return ipos;
					}
					continue;
				}
				
				
				if (s1.substring(ipos, ipos + s2.length()).equals(s2)) {
					// �ҵ�������ַ���,���ش�λ��
					// TODO���˴���Ҫ��������жϣ�ȷ����ǰ���ҵ���λ����һ��������λ�ýڵ㣬������һ��������һ����
					// ����Ҫ���� and ,��ôҪ�ų�land,hand,and1 ֮��
					// ��˿��ܻ�����Ҫ��ԭʼ�ַ����Ƚ���һ�ηִʲ�������ȷ���ҵ���λ����һ����Чλ��
					// �ⲿ�ִ������һ�����䡣
					return ipos;
				}
			}
			// û���ҵ��ַ�����ѭ���ȴ�
			// goto loop condition.
		}

		// not found,return -1;
		return -1;
	}

	/**
	 * ���ַ�����ʼ�ͽ���λ�õ�\r\n ,\r,�ո񶼹��˵�
	 * 
	 * @param strinput
	 * @return
	 */
	public String trimReturnAndSpace(String strinput) {
		String sout = "";
		// �Ȳ��ҵ�һ������\r\n \r �ո��λ��
		int ipos = -1;
		for (int i = 0; i < strinput.length(); i++) {
			if (strinput.charAt(i) == '\r' || strinput.charAt(i) == '\n' || strinput.charAt(i) == ' ') {
				// ����ѭ��
				continue;
			} else {
				ipos = i;
				break;
			}
		}

		if (ipos == -1) {
			// û���ҵ���Ч�ַ�
			return "";
		}

		// ��ʼ�Ӻ���ǰ���ҵ�һ����Ч�ַ�
		int ipos2 = -1;
		for (int i = strinput.length() - 1; i >= 0; i--) {
			if (strinput.charAt(i) == '\r' || strinput.charAt(i) == '\n' || strinput.charAt(i) == ' ') {
				// ����ѭ��
				continue;
			} else {
				ipos2 = i;
				break;
			}
		}

		// ����ipos��Ч������ipos2һ��Ҳ����Ч��
		if (ipos2 >= ipos) {
			return strinput.substring(ipos, ipos2 + 1);
		} else {
			System.out.println("����ִ�г��ִ�����Ҫ������������.ipos,ipos2=" + ipos + "," + ipos2);
			return "";
		}
	}

}
