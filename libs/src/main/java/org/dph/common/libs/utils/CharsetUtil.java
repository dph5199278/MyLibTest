/**
 * org.dph.common.libs.utils   1.00    2016/10/1
 * Public share libs
 * All Rights Reserved,Copyright©dph
 * @author dph
 */
package org.dph.common.libs.utils;

import java.io.UnsupportedEncodingException;

/**
 * CharsetUtil
 * 
 * @author dph
 * @version 1.0
 */
public class CharsetUtil {

	public static final String ISO8859_1 = "ISO8859-1";

	public static final String UTF_8 = "UTF-8";

	public static final String GBK = "GBK";
	public static final String GB2312 = "GB2312";

	public static final String ASCII = "US-ASCII";

	/**
	 * Function: Converts the byte array to a string using the named charset.
	 * 
	 * @author dph
	 * @param bytes
	 *            the convert byte array.
	 * @param charsetName
	 *            the charset's name.
	 * @return the string using the named charset.
	 * @throws UnsupportedEncodingException
	 *             if {@code charsetName} is not supported.
	 */
	public static String changCharset(final byte[] bytes,
			final String charsetName) throws UnsupportedEncodingException {
		return (null == bytes || 0 == bytes.length || StringUtil
				.isEmpty(charsetName)) ? "" : new String(bytes, charsetName);
	}

	/**
	 * Function: Converts the string using the named charset to other charset.
	 * 
	 * @author dph
	 * @param str
	 *            the convert string.
	 * @param srcCharset
	 *            the charset's name.
	 * @param targetCharset
	 *            the other charset's name.
	 * @return the string using the named charset.
	 * @throws UnsupportedEncodingException
	 *             if {@code srcCharset} or {@code targetCharset} is not
	 *             supported.
	 */
	public static String changCharset(final String str,
			final String srcCharset, final String targetCharset)
			throws UnsupportedEncodingException {
		return (StringUtil.isEmpty(str) || StringUtil.isEmpty(srcCharset)) ? ""
				: changCharset(str.getBytes(srcCharset), targetCharset);
	}
}
