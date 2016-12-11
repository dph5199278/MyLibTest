/**
 * org.dph.common.libs.common   1.00    2016/12/7
 * Public share libs
 * All Rights Reserved,Copyright©dph
 * @author dph
 */

package org.dph.common.libs.common;

import android.os.AsyncTask;

/**
 * BaseAsyncTask
 * 
 * @author dph
 * @version 1.0
 * @param <Params>
 * @param <Result>
 */
public abstract class BaseAsyncTask<Params, Result> extends
		AsyncTask<Params, Integer, Result> {

	/**
	 * Function: This method can be callback {@link #publishProgress} to publish
	 * updates on the UI thread.
	 *
	 * @author dph
	 * @param values
	 *            The progress values to update the UI with.
	 */
	public void callbackPublishProgress(Integer... values) {
		publishProgress(values);
	}
}
