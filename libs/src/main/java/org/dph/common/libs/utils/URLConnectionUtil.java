/**
 * org.dph.common.libs.utils   1.00    2016/10/1
 * Public share libs
 * All Rights Reserved,Copyright©dph
 * @author dph
 */
package org.dph.common.libs.utils;

import org.dph.common.libs.common.Conast;
import org.dph.common.libs.common.Properties;

import android.annotation.SuppressLint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * URLConnectionUtil
 * 
 * @author dph
 * @version 1.0
 */
public class URLConnectionUtil {
	public static final String POST = "POST";
	public static final String GET = "GET";

	private static final String AND_MARK = "&";

	private static final int CONNECT_TIMEOUT = Integer.valueOf(Properties.get(
			"connect.timeout", "10000"));
	private static final int READ_TIMEOUT = Integer.valueOf(Properties.get(
			"read.timeout", "25000"));

	/**
	 * Function: post
	 * 
	 * @author dph
	 * @param url
	 * @return
	 */
	public static byte[] post(String url) {
		return post(url, "");
	}

	/**
	 * Function: get
	 * 
	 * @author dph
	 * @param url
	 * @return
	 */
	public static byte[] get(String url) {
		return get(url, "");
	}

	/**
	 * Function: post
	 * 
	 * @author dph
	 * @param url
	 * @param params
	 * @return
	 */
	public static byte[] post(String url, Map<String, Object> params) {
		return post(url, params, null);
	}

	/**
	 * Function: get
	 * 
	 * @author dph
	 * @param url
	 * @param params
	 * @return
	 */
	public static byte[] get(String url, Map<String, Object> params) {
		return get(url, params, null);
	}

	/**
	 * Function: post
	 * 
	 * @author dph
	 * @param url
	 * @param params
	 * @return
	 */
	public static byte[] post(String url, String params) {
		return post(url, params, null);
	}

	/**
	 * Function: get
	 * 
	 * @author dph
	 * @param url
	 * @param params
	 * @return
	 */
	public static byte[] get(String url, String params) {
		return get(url, params, null);
	}

	/**
	 * Function: post
	 * 
	 * @author dph
	 * @param url
	 * @param params
	 * @param headers
	 * @return
	 */
	public static byte[] post(String url, Map<String, Object> params,
			Map<String, String> headers) {
		return connect(url, POST, params, headers);
	}

	/**
	 * Function: get
	 * 
	 * @author dph
	 * @param url
	 * @param params
	 * @param headers
	 * @return
	 */
	public static byte[] get(String url, Map<String, Object> params,
			Map<String, String> headers) {
		return connect(url, GET, params, headers);
	}

	/**
	 * Function: post
	 * 
	 * @author dph
	 * @param url
	 * @param params
	 * @param headers
	 * @return
	 */
	public static byte[] post(String url, String params,
			Map<String, String> headers) {
		return connect(url, POST, params, headers);
	}

	/**
	 * Function: get
	 * 
	 * @author dph
	 * @param url
	 * @param params
	 * @param headers
	 * @return
	 */
	public static byte[] get(String url, String params,
			Map<String, String> headers) {
		return connect(url, GET, params, headers);
	}

	/**
	 * Function: connect
	 * 
	 * @author dph
	 * @param url
	 * @param method
	 * @param params
	 * @param headers
	 * @return
	 */
	private static byte[] connect(String url, String method,
			Map<String, Object> params, Map<String, String> headers) {
		return connect(url, method, initParams(params), headers);
	}

	/**
	 * Function: connect
	 * 
	 * @author dph
	 * @param url
	 * @param method
	 * @param params
	 * @param headers
	 * @return
	 */
	private static byte[] connect(String url, String method, String params,
			Map<String, String> headers) {
		byte[] data = new byte[0];
		try {
			HttpURLConnection request = initHttps(url, method, headers);
			setParams(request, params);

			InputStream in = request.getInputStream();
			BufferedReader read = new BufferedReader(new InputStreamReader(in));
			int item = 0;
			List<Byte> byteList = new ArrayList<Byte>();
			while (-1 < (item = in.read())) {
				byteList.add((byte) item);
			}

			read.close();
			if (null != request) {
				request.disconnect();
			}

			data = new byte[byteList.size()];
			for (int i = 0; i < byteList.size(); i++) {
				data[i] = byteList.get(i);
			}
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}

	/**
	 * Function: initParams
	 * 
	 * @author dph
	 * @param params
	 * @return
	 */
	private static String initParams(Map<String, Object> params) {
		StringBuffer sb = new StringBuffer();

		if (null != params) {
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				if (null != entry.getValue() && StringUtil.isNotEmpty(entry.getKey())) {
					sb.append(AND_MARK).append(buildParamPair(entry));
				}
			}
		}

		if (0 < sb.length())
			sb.delete(0, 1);
		return sb.toString();
	}

	/**
	 * Function: buildParamPair
	 * 
	 * @author dph
	 * @param entry
	 * @return
	 */
	private static String buildParamPair(Map.Entry<String, Object> entry) {
		Object value = entry.getValue();
		if (value instanceof Date) {
			value = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
					Locale.getDefault()).format(value);
		}
		try {
			return String.format("%s=%s", entry.getKey(),
					URLEncoder.encode(value.toString(), CharsetUtil.UTF_8));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * Function: setParams
	 * 
	 * @author dph
	 * @param request
	 * @param params
	 * @throws IOException
	 */
	private static void setParams(HttpURLConnection request, String params)
			throws IOException {
		OutputStream out = request.getOutputStream();

		if (StringUtil.isNotEmpty(params)) {
			out.write(params.getBytes(CharsetUtil.UTF_8));
			out.flush();
		}
		out.close();
	}

	/**
	 * Function: initHttps
	 * 
	 * @author dph
	 * @param url
	 * @param method
	 * @param headers
	 * @return
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	@SuppressLint("TrulyRandom")
	private static HttpURLConnection initHttps(String url, String method,
			Map<String, String> headers) throws KeyManagementException,
			NoSuchAlgorithmException, IOException {
		TrustManager[] tms = { new BlankX509TrustManager() };
		SSLContext context = SSLContext.getInstance("SSL");
		context.init(null, tms, new SecureRandom());

		SSLSocketFactory ssf = context.getSocketFactory();
		URL _url = new URL(url);

		HttpURLConnection request = (HttpURLConnection) _url.openConnection();
		if (url.startsWith(Conast.HTTPS)) {
			HttpsURLConnection requests = (HttpsURLConnection) request;
			requests.setHostnameVerifier(new BlankHostnameVerifier());
			requests.setSSLSocketFactory(ssf);
		}

		request.setConnectTimeout(URLConnectionUtil.CONNECT_TIMEOUT);

		request.setReadTimeout(URLConnectionUtil.READ_TIMEOUT);
		request.setRequestMethod(method);

		if (null != headers && !headers.isEmpty()) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				request.setRequestProperty(entry.getKey(), entry.getValue());
			}
		}

		request.setDoOutput(true);
		request.setDoInput(true);
		request.connect();

		return request;
	}

	/**
	 * BlankHostnameVerifier
	 * 
	 * @author dph
	 * @version 1.0
	 */
	private static class BlankHostnameVerifier implements HostnameVerifier {

		@Override
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	/**
	 * BlankX509TrustManager
	 * 
	 * @author dph
	 * @version 1.0
	 */
	private static class BlankX509TrustManager implements X509TrustManager {

		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {

		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {

		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	}
}
