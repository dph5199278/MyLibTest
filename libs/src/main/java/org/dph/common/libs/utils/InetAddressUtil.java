/**
 * org.dph.common.libs.utils   1.00    2016/10/1
 * Public share libs
 * All Rights Reserved,Copyright©dph
 * @author dph
 */
package org.dph.common.libs.utils;

import java.util.regex.Pattern;

/**
 * InetAddressUtils
 * 
 * @author dph
 * @version 1.0
 */
public class InetAddressUtil {

	private InetAddressUtil() {
	}

	private static final Pattern IPV4_PATTERN = Pattern
			.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");

	private static final Pattern IPV6_STD_PATTERN = Pattern
			.compile("^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$");

	private static final Pattern IPV6_HEX_COMPRESSED_PATTERN = Pattern
			.compile("^((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)$");

	/**
	 * Function: Check {@code input} is ipv4 address or not.
	 * 
	 * @author dph
	 * @param input
	 * 
	 * @return true if the {@code input} is ipv4 address, false if the
	 *         {@code input} is not ipv4 address.
	 */
	public static boolean isIPv4Address(final String input) {
		return !StringUtil.isEmpty(input) && IPV4_PATTERN.matcher(input)
				.matches();
	}

	/**
	 * Function: Check {@code input} is standard ipv6 address or not.
	 * 
	 * @author dph
	 * @param input
	 * 
	 * @return true if the {@code input} is standard ipv6 address, false if the
	 *         {@code input} is not standard ipv6 address.
	 */
	public static boolean isIPv6StdAddress(final String input) {
		return !StringUtil.isEmpty(input) && IPV6_STD_PATTERN.matcher(
				input).matches();
	}

	/**
	 * Function: Check {@code input} is hex compressed ipv6 address or not.
	 * 
	 * @author dph
	 * @param input
	 * 
	 * @return true if the {@code input} is hex compressed ipv6 address, false
	 *         if the {@code input} is not hex compressed ipv6 address.
	 */
	public static boolean isIPv6HexCompressedAddress(final String input) {
		return !StringUtil.isEmpty(input) && IPV6_HEX_COMPRESSED_PATTERN
				.matcher(input).matches();
	}

	/**
	 * Function: Check {@code input} is ipv6 address or not.
	 * 
	 * @author dph
	 * @param input
	 * 
	 * @return true if the {@code input} is ipv6 address, false if the
	 *         {@code input} is not ipv6 address.
	 */
	public static boolean isIPv6Address(final String input) {
		return !StringUtil.isEmpty(input) && (isIPv6StdAddress(input)
				|| isIPv6HexCompressedAddress(input));
	}

}
