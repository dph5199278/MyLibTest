/**
 * org.dph.common.libs.common   1.00    2016/12/7
 * Public share libs
 * All Rights Reserved,Copyright©dph
 * @author dph
 */
package org.dph.common.libs.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Properties
 * 
 * @author dph
 * @version 1.0
 */
public class Properties {
	private static final Map<Object, Object> p;

	static {
		// get setting properties
		p = new java.util.Properties();
		try {
			InputStream is = Properties.class
					.getResourceAsStream("settings.properties");
			((java.util.Properties) p).load(is);

			if (null != is) {
				is.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Function: Returns the named string property from this.
	 * 
	 * @author dph
	 * @param key
	 *            the name of the property to find.
	 * @param defualtValue
	 *            the default value.
	 * @return the named property value.
	 */
	public static final String get(final String key, final String defualtValue) {
		return ((java.util.Properties) p).getProperty(key, defualtValue);
	}
}
