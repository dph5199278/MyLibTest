/**
 * org.dph.common.libs.common   1.00    2016/12/7
 * Public share libs
 * All Rights Reserved,Copyright©dph
 * @author dph
 */
package org.dph.common.libs.common;

/**
 * Conast
 * 
 * @author dph
 * @version 1.0
 */
public class Conast {

	/**
	 * http
	 */
	public static final String HTTP = "http://";
	/**
	 * https
	 */
	public static final String HTTPS = "https://";
	/**
	 * protocol
	 */
	public static final String PROTOCOL = Properties.get("protocol", HTTPS);

	/**
	 * base host
	 */
	public static final String BASE_HOST = Properties.get("base.url", "");
	/**
	 * update path
	 */
	public static final String UPDATE_PATH = Properties.get("update.path", "");

	/**
	 * version path
	 */
	public static final String VERSION_PATH = Properties
			.get("version.path", "");
	/**
	 * package(zip) path
	 */
	public static final String PACKAGE_PATH = Properties
			.get("package.path", "");

	/**
	 * base version url
	 */
	public static final String BASE_VERSION_URL = PROTOCOL + BASE_HOST
			+ UPDATE_PATH + VERSION_PATH;
	/**
	 * base update path
	 */
	public static final String BASE_PACKAGE_URL = PROTOCOL + BASE_HOST
			+ UPDATE_PATH;

	/**
	 * package version key
	 */
	public static final String PACKAGE_VERSION_KEY = "app_version";
	/**
	 * web version key
	 */
	public static final String WEB_VERSION_KEY = "web_version";

}
