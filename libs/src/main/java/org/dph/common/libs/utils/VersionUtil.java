/**
 * org.dph.common.libs.utils   1.00    2016/10/1
 * Public share libs
 * All Rights Reserved,Copyright©dph
 * @author dph
 */
package org.dph.common.libs.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import org.dph.common.libs.common.Conast;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Map;

/**
 * VersionUtil
 * 
 * @author dph
 * @version 1.0
 */
public class VersionUtil {

	/**
	 * Function: getRemoteVersionAndPath
	 * 
	 * @author dph
	 * @param params
	 * @param headers
	 * @return
	 * @throws JSONException
	 */
	public static String[] getRemoteVersionAndPath(final String params,
			final Map<String, String> headers) throws JSONException {
		String[] rtn = { "", "" };

		JSONObject datas = getRemotePackageData(params, headers);

		// get version
		rtn[0] = JSONUtil.optString(datas, "version");

		// get path
		StringBuffer path = new StringBuffer();
		JSONObject updater = JSONUtil.optJSONObject(datas, "auto-update");
		String repo = JSONUtil.optString(updater, "repo");
		String branch = JSONUtil.optString(updater, "branch");
		if (StringUtil.isNotEmpty(repo) && StringUtil.isEmpty(branch)) {
			path.append(repo);
		} else if (StringUtil.isEmpty(repo) && StringUtil.isNotEmpty(branch)) {
			path.append(branch);
		} else if (StringUtil.isNotEmpty(repo) && StringUtil.isNotEmpty(branch)) {
			if (repo.endsWith(File.separator)
					|| branch.startsWith(File.separator)) {
				path.append(repo).append(branch);
			} else {
				path.append(repo).append(File.separator).append(branch);
			}
		}
		rtn[1] = path.toString();

		return rtn;
	}

	/**
	 * Function: Get the server's package info data.
	 * 
	 * @author dph
	 * @param params
	 * @param headers
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject getRemotePackageData(final String params,
			final Map<String, String> headers) throws JSONException {
		byte[] byteData = URLConnectionUtil.post(Conast.BASE_VERSION_URL,
				params, headers);
		return new JSONObject(new String(byteData));
	}

	/**
	 * Function: Get the application's version name.
	 * 
	 * @author dph
	 * @param context
	 *            the application context.
	 * @return version name.
	 */
	public static String getVersionName(final Context context) {
		String versionName = null;
		PackageInfo pi = getPackageInfo(context);
		if (null != pi) {
			versionName = pi.versionName;
		}
		return versionName;
	}

	/**
	 * Function: Get the application's version code.
	 * 
	 * @author dph
	 * @param context
	 *            the application context.
	 * @return version code.
	 */
	public static int getVersionCode(final Context context) {
		int versionCode = -1;
		PackageInfo pi = getPackageInfo(context);
		if (null != pi) {
			versionCode = pi.versionCode;
		}
		return versionCode;
	}

	/**
	 * Function: Get the application package info.
	 * 
	 * @author dph
	 * @param context
	 *            the application context.
	 * @return application package info.
	 */
	private static PackageInfo getPackageInfo(final Context context) {
		PackageInfo pi = null;
		try {
			PackageManager pm = context.getPackageManager();
			pi = pm.getPackageInfo(context.getPackageName(),
					PackageManager.GET_CONFIGURATIONS);
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}

		return pi;
	}
}
