/**
 * org.dph.common.libs.utils   1.00    2016/12/11
 * Public share libs
 * All Rights Reserved,Copyright©dph
 * @author dph
 */
package org.dph.common.libs.utils;

import java.io.ByteArrayInputStream;
import java.util.Map;

/**
 * StreamUtil 
 * @author dph
 * @version 1.0
 */
public class StreamUtil {

	/**
	 * Function: getPackageStream
	 * 
	 * @author dph
	 * @param url
	 * @param params
	 * @param headers
	 * @return
	 */
	public static ByteArrayInputStream getByteArrayStream(String url,
			String params, Map<String, String> headers) {
		byte[] buffer = URLConnectionUtil.post(url, params, headers);

		return getByteArrayStream(buffer);
	}

	/**
	 * Function: getPackageStream
	 * 
	 * @author dph
	 * @param buffer
	 * @return
	 */
	public static ByteArrayInputStream getByteArrayStream(byte[] buffer) {

		if (null != buffer && 0 < buffer.length) {
			return new ByteArrayInputStream(buffer);
		} else {
			return null;
		}
	}
}
