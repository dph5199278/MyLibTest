/**
 * org.dph.common.libs.utils   1.00    2016/10/1
 * Public share libs
 * All Rights Reserved,Copyright©dph
 * @author dph
 */
package org.dph.common.libs.utils;

import android.annotation.SuppressLint;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * StringUtils
 * 
 * @author dph
 * @version 1.0
 */
public class StringUtil {

	/**
	 * Function: clean
	 * 
	 * @author dph
	 * @param str
	 * @return
	 */
	public static String clean(String str) {
		return ((str == null) ? "" : str.trim());
	}

	/**
	 * Function: trim
	 * 
	 * @author dph
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		return ((str == null) ? null : str.trim());
	}

	/**
	 * Function: defaultString
	 * 
	 * @author dph
	 * @param str
	 * @return
	 */
	public static String defaultString(String str) {
		return defaultString(str, "");
	}

	/**
	 * Function: defaultString
	 * 
	 * @author dph
	 * @param str
	 * @param defaultString
	 * @return
	 */
	public static String defaultString(String str, String defaultString) {
		return ((str == null) ? defaultString : str);
	}

	/**
	 * Function: equals
	 * 
	 * @author dph
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean equals(String str1, String str2) {
		return (str1 != null && ((str2 == null) || str1
				.equals(str2)));
	}

	/**
	 * Function: isEmpty
	 * 
	 * @author dph
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return ((str == null) || (str.trim().length() == 0));
	}

	/**
	 * Function: isNotEmpty
	 * 
	 * @author dph
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return ((str != null) && (str.length() > 0));
	}

	/**
	 * Function: isNumeric
	 * 
	 * @author dph
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if (str == null) {
			return false;
		}
		int sz = str.length();
		for (int i = 0; i < sz; ++i) {
			if (!(Character.isDigit(str.charAt(i)))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Function: isNumericSpace
	 * 
	 * @author dph
	 * @param str
	 * @return
	 */
	public static boolean isNumericSpace(String str) {
		if (str == null) {
			return false;
		}
		int sz = str.length();
		for (int i = 0; i < sz; ++i) {
			if ((!(Character.isDigit(str.charAt(i)))) && (str.charAt(i) != ' ')) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Function: join
	 * 
	 * @author dph
	 * @param iterator
	 * @param separator
	 * @return
	 */
	public static String join(Iterator<?> iterator, String separator) {
		if (separator == null) {
			separator = "";
		}
		StringBuffer buf = new StringBuffer(256);
		while (iterator.hasNext()) {
			buf.append(iterator.next());
			if (iterator.hasNext()) {
				buf.append(separator);
			}
		}
		return buf.toString();
	}

	/**
	 * Function: join
	 * 
	 * @author dph
	 * @param array
	 * @param separator
	 * @return
	 */
	public static String join(Object[] array, String separator) {
		if (separator == null) {
			separator = "";
		}
		int arraySize = array.length;
		int bufSize = (arraySize == 0) ? 0
				: (array[0].toString().length() + separator.length())
						* arraySize;

		StringBuffer buf = new StringBuffer(bufSize);

		for (int i = 0; i < arraySize; ++i) {
			if (i > 0) {
				buf.append(separator);
			}
			buf.append(array[i]);
		}
		return buf.toString();
	}

	/**
	 * Function: repeat
	 * 
	 * @author dph
	 * @param str
	 * @param repeat
	 * @return
	 */
	public static String repeat(String str, int repeat) {
		StringBuffer buffer = new StringBuffer(repeat * str.length());
		for (int i = 0; i < repeat; ++i) {
			buffer.append(str);
		}
		return buffer.toString();
	}

	/**
	 * Function: replace
	 * 
	 * @author dph
	 * @param text
	 * @param repl
	 * @param with
	 * @return
	 */
	public static String replace(String text, String repl, String with) {
		return replace(text, repl, with, -1);
	}

	/**
	 * Function: replace
	 * 
	 * @author dph
	 * @param text
	 * @param repl
	 * @param with
	 * @param max
	 * @return
	 */
	public static String replace(String text, String repl, String with, int max) {
		if (text == null) {
			return null;
		}

		StringBuffer buf = new StringBuffer(text.length());
		int start = 0;
		int end = 0;
		while ((end = text.indexOf(repl, start)) != -1) {
			buf.append(text.substring(start, end)).append(with);
			start = end + repl.length();

			if (--max == 0) {
				break;
			}
		}
		buf.append(text.substring(start));
		return buf.toString();
	}

	/**
	 * Function: replaceOnce
	 * 
	 * @author dph
	 * @param text
	 * @param repl
	 * @param with
	 * @return
	 */
	public static String replaceOnce(String text, String repl, String with) {
		return replace(text, repl, with, 1);
	}

	/**
	 * Function: reverse
	 * 
	 * @author dph
	 * @param str
	 * @return
	 */
	public static String reverse(String str) {
		if (str == null) {
			return null;
		}
		return new StringBuffer(str).reverse().toString();
	}

	/**
	 * Function: reverseArray
	 * 
	 * @author dph
	 * @param array
	 */
	private static void reverseArray(Object[] array) {
		int i = 0;
		int j = array.length - 1;

		while (j > i) {
			Object tmp = array[j];
			array[j] = array[i];
			array[i] = tmp;
			--j;
			++i;
		}
	}

	/**
	 * Function: reverseDelimitedString
	 * 
	 * @author dph
	 * @param str
	 * @param delimiter
	 * @return
	 */
	public static String reverseDelimitedString(String str, String delimiter) {
		String[] strs = split(str, delimiter);
		reverseArray(strs);
		return join(strs, delimiter);
	}

	/**
	 * Function: split
	 * 
	 * @author dph
	 * @param str
	 * @return
	 */
	public static String[] split(String str) {
		return split(str, null, -1);
	}

	/**
	 * Function: split
	 * 
	 * @author dph
	 * @param text
	 * @param separator
	 * @return
	 */
	public static String[] split(String text, String separator) {
		return split(text, separator, -1);
	}

	/**
	 * Function: split
	 * 
	 * @author dph
	 * @param str
	 * @param separator
	 * @param max
	 * @return
	 */
	public static String[] split(String str, String separator, int max) {
		StringTokenizer tok = null;
		if (separator == null) {
			tok = new StringTokenizer(str);
		} else
			tok = new StringTokenizer(str, separator);

		int listSize = tok.countTokens();
		if ((max > 0) && (listSize > max)) {
			listSize = max;
		}

		String[] list = new String[listSize];
		int i = 0;
		int lastTokenBegin = 0;
		int lastTokenEnd = 0;
		while (tok.hasMoreTokens()) {
			if ((max > 0) && (i == listSize - 1)) {
				String endToken = tok.nextToken();
				lastTokenBegin = str.indexOf(endToken, lastTokenEnd);
				list[i] = str.substring(lastTokenBegin);
				break;
			}
			list[i] = tok.nextToken();
			lastTokenBegin = str.indexOf(list[i], lastTokenEnd);
			lastTokenEnd = lastTokenBegin + list[i].length();

			++i;
		}
		return list;
	}

	/**
	 * Function: substring
	 * 
	 * @author dph
	 * @param str
	 * @param start
	 * @return
	 */
	public static String substring(String str, int start) {
		if (str == null) {
			return null;
		}

		if (start < 0) {
			start = str.length() + start;
		}

		if (start < 0) {
			start = 0;
		}
		if (start > str.length()) {
			return "";
		}

		return str.substring(start);
	}

	/**
	 * Function: substring
	 * 
	 * @author dph
	 * @param str
	 * @param start
	 * @param end
	 * @return
	 */
	public static String substring(String str, int start, int end) {
		if (str == null) {
			return null;
		}

		if (end < 0) {
			end = str.length() + end;
		}
		if (start < 0) {
			start = str.length() + start;
		}

		if (end > str.length()) {
			end = str.length();
		}

		if (start > end) {
			return "";
		}

		if (start < 0) {
			start = 0;
		}
		if (end < 0) {
			end = 0;
		}

		return str.substring(start, end);
	}

	/**
	 * Function: lowerCase
	 * 
	 * @author dph
	 * @param str
	 * @return
	 */
	@SuppressLint("DefaultLocale")
	public static String lowerCase(String str) {
		if (str == null) {
			return null;
		}
		return str.toLowerCase();
	}

	/**
	 * Function: upperCase
	 * 
	 * @author dph
	 * @param str
	 * @return
	 */
	@SuppressLint("DefaultLocale")
	public static String upperCase(String str) {
		if (str == null) {
			return null;
		}
		return str.toUpperCase();
	}
}
