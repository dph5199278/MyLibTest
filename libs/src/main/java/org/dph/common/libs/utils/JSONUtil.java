/**
 * JSONUtil.java   1.00    2016/12/11
 * Public share libs
 * All Rights Reserved,Copyright©dph
 * @author dph
 */
package org.dph.common.libs.utils;

import org.json.JSONObject;

/**
 * JSONUtil
 * 
 * @author dph
 * @version 1.0
 */
public class JSONUtil {

	/**
	 * Function: Returns the value mapped by {@code key} if it exists, coercing
	 * it if necessary, or the empty string if no such mapping exists.
	 * 
	 * @author dph
	 * @param data
	 *            JSONObject
	 * @param key
	 *            mapped name
	 * @return mapped string value
	 */
	public static String optString(final JSONObject data, final String key) {
		if (null != data)
			return data.optString(key);
		else
			return "";
	}

	/**
	 * Function: Returns the value mapped by {@code key} if it exists and is a
	 * {@code JSONObject}, or null otherwise.
	 * 
	 * @author dph
	 * @param data
	 *            JSONObject
	 * @param key
	 *            mapped name
	 * @return mapped JSONObject value
	 */
	public static JSONObject optJSONObject(final JSONObject data,
			final String key) {
		if (null != data)
			return data.optJSONObject(key);
		else
			return null;
	}
}
