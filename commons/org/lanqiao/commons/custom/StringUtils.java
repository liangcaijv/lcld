package org.lanqiao.commons.custom;

import java.io.UnsupportedEncodingException;
import java.lang.Character.UnicodeBlock;

public class StringUtils {
	/**
	 * 编码为unicode
	 * 
	 * @param inStr
	 * @return
	 */
	public static String encodeUnicode(String inStr) {

		char[] myBuffer = inStr.toCharArray();

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < inStr.length(); i++) {
			char ch = myBuffer[i];

			if ((int) ch < 10) {

				sb.append("\\" + (int) ch);

				continue;

			}

			UnicodeBlock ub = UnicodeBlock.of(ch);

			if (ub == UnicodeBlock.BASIC_LATIN) {

				// 英文及数字等

				sb.append(myBuffer[i]);

			} else if (ub == UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {

				// 全角半角字符

				int j = (int) myBuffer[i] - 65248;

				sb.append((char) j);

			} else {

				// 汉字

				short s = (short) myBuffer[i];

				String hexS = Integer.toHexString(s);

				String unicode = "\\u" + hexS;

				sb.append(unicode.toLowerCase());

			}

		}

		return sb.toString();

	}

	/**
	 * 从unicode解码
	 * 
	 * @param theString
	 * @return
	 */
	public static String decodeUnicode(String theString) {

		char aChar;

		int len = theString.length();

		StringBuffer outBuffer = new StringBuffer(len);

		for (int x = 0; x < len;) {

			aChar = theString.charAt(x++);

			if (aChar == '\\') {

				aChar = theString.charAt(x++);

				if (aChar == 'u') {

					// Read the xxxx

					int value = 0;

					for (int i = 0; i < 4; i++) {

						aChar = theString.charAt(x++);

						switch (aChar) {

						case '0':

						case '1':

						case '2':

						case '3':

						case '4':

						case '5':

						case '6':

						case '7':

						case '8':

						case '9':

							value = (value << 4) + aChar - '0';

							break;

						case 'a':

						case 'b':

						case 'c':

						case 'd':

						case 'e':

						case 'f':

							value = (value << 4) + 10 + aChar - 'a';

							break;

						case 'A':

						case 'B':

						case 'C':

						case 'D':

						case 'E':

						case 'F':

							value = (value << 4) + 10 + aChar - 'A';

							break;

						default:

							throw new IllegalArgumentException(

							"Malformed   \\uxxxx   encoding.");

						}

					}

					outBuffer.append((char) value);

				} else {

					if (aChar == 't')

						aChar = '\t';

					else if (aChar == 'r')

						aChar = '\r';

					else if (aChar == 'n')

						aChar = '\n';

					else if (aChar == 'f')

						aChar = '\f';

					outBuffer.append(aChar);

				}

			} else

				outBuffer.append(aChar);

		}

		return outBuffer.toString();

	}

	


	public static boolean isHanZi(char ch) {
		// 判断是否汉字
		return (ch >= 0x4E00 && ch <= 0x9FA5);

	}

	/**
	 * 根据输入的Unicode字符，获取它的GB2312编码或者ascii编码，
	 * 
	 * @param ch
	 *            输入的GB2312中文字符或者ASCII字符(128个)
	 * @return ch在GB2312中的位置，-1表示该字符不认识
	 */
	public static short getGB2312Id(char ch) {
		try {
			byte[] buffer = Character.toString(ch).getBytes("GB2312");
			if (buffer.length != 2) {
				// 正常情况下buffer应该是两个字节，否则说明ch不属于GB2312编码，故返回'?'，此时说明不认识该字符
				return -1;
			}
			int b0 = (int) (buffer[0] & 0x0FF) - 161; // 编码从A1开始，因此减去0xA1=161
			int b1 = (int) (buffer[1] & 0x0FF) - 161; // 第一个字符和最后一个字符没有汉字，因此每个区只收16*6-2=94个汉字
			return (short) (b0 * 94 + b1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return -1;
	}


	/**
	 * 处理空值，如果第一个参数的值为空，则返回第二个参数
	 * @param src
	 * @param rep
	 * @return
	 */
	public static String deNull(String src,String rep){
	    if (src!=null&&src.length()>0) {
            return src;
        }else {
            return rep;
        }
	}

	/**
	 * 为null或者全空白或者空白字符串时返回false
	 * @param focus
	 * @return
	 */
	public static boolean hasLength(String focus) {
		if (focus==null) {
			return false;
		}
		if (focus.length()==0) {
			return false;
		}
		if (focus.trim().length()==0) {
			return false;
		}
		return true;
	}

	public static boolean hasLength(Object focus) {
		if (focus==null) {
			return false;
		}
		return hasLength(focus.toString());
	}

	public static boolean hasText(CharSequence str) {
        if (!hasLength(str)) {
            return false;
        }
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether the given String has actual text.
     * More specifically, returns <code>true</code> if the string not <code>null</code>,
     * its length is greater than 0, and it contains at least one non-whitespace character.
     * @param str the String to check (may be <code>null</code>)
     * @return <code>true</code> if the String is not <code>null</code>, its length is
     * greater than 0, and it does not contain whitespace only
     * @see #hasText(CharSequence)
     */
    public static boolean hasText(String str) {
        return hasText((CharSequence) str);
    }
	
	
	
	
}
