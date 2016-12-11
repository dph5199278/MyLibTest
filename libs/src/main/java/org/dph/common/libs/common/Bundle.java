/**
 * org.dph.common.libs.common   1.00    2016/12/7
 * Public share libs
 * All Rights Reserved,Copyright©dph
 * @author dph
 */
package org.dph.common.libs.common;

import java.util.ResourceBundle;

/**
 * Bundle
 * 
 * @author dph
 * @version 1.0
 */
public class Bundle {
	private static final ResourceBundle bundle;

	static {
		// get i18n bundle
		bundle = ResourceBundle.getBundle("application");
	}

	/**
	 * Function: Returns the named string resource from this.
	 * 
	 * @author dph
	 * @param key
	 *            the name of the resource.
	 * @param defaultValue
	 *            the default value.
	 * @return the resource string.
	 */
	public static final String get(final String key, final String defaultValue) {
		String value = defaultValue;
		try {
			value = bundle.getString(key);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return value;
	}
}
