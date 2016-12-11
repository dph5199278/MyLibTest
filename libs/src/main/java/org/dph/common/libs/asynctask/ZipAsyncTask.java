package org.dph.common.libs.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;

import org.dph.common.libs.R;
import org.dph.common.libs.common.BaseAsyncTask;
import org.dph.common.libs.common.Conast;
import org.dph.common.libs.utils.FileUtil;
import org.dph.common.libs.utils.StreamUtil;
import org.dph.common.libs.utils.URLConnectionUtil;
import org.dph.common.libs.utils.VersionUtil;
import org.dph.common.libs.utils.ZipUtil;

import java.io.File;

/**
 * ZipAsyncTask
 * 
 * @author dph
 * @version 1.0
 */
public class ZipAsyncTask extends BaseAsyncTask<Object, Boolean> {

	private Activity context = null;
	private ProgressDialog pd = null;
	private Class<? extends Activity> nextActivityClass = null;
	private Resources res = null;

	public ZipAsyncTask(Activity context,
			Class<? extends Activity> nextActivityClass) {
		super();
		this.context = context;
		this.nextActivityClass = nextActivityClass;
		this.res = context.getResources();

	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		pd = new ProgressDialog(context);
		pd.setTitle(this.res.getString(R.string.doing_message));
		pd.setMessage(this.res.getString(R.string.progress_message, 0));
		pd.setCancelable(false);
		pd.setMax(100);
		pd.setProgress(0);
		pd.show();
	}

	@Override
	protected Boolean doInBackground(Object... params) {
		SharedPreferences sp = context.getPreferences(Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();

		// add "/" to end of path
		String folderPath = context.getFilesDir().getAbsolutePath()
				+ File.separator;
		String cachePath = context.getCacheDir().getAbsolutePath()
				+ File.separator;

		try {
			// get app version flag
			int flag = sp.getInt(Conast.PACKAGE_VERSION_KEY, 0);
			// get app version code
			int code = VersionUtil.getVersionCode(context);

			// compare app version
			if (flag < code) {
				// write stream to cache file
				File zipFile = FileUtil.createFile(
						context.getAssets().open("app.dph"), cachePath
								+ "localTmp.dph", false);

				// up zip and show progress
				ZipUtil.upZip(zipFile, folderPath, true, this, 0, 80);

				editor.putInt(Conast.PACKAGE_VERSION_KEY, code).commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		try {
			String localVersion = sp.getString(Conast.WEB_VERSION_KEY, "");
			String[] packageArray = VersionUtil.getRemoteVersionAndPath(
					null, null);
			String remoteVersion = packageArray[0];

			// download update data
			byte[] datas = new byte[0];
			if (0 > localVersion.compareToIgnoreCase(remoteVersion)) {
				datas = URLConnectionUtil.post(Conast.BASE_PACKAGE_URL
						+ packageArray[1]
						+ Conast.PACKAGE_PATH);
			}

			// update data to folder
			if (0 < datas.length) {
				// write stream to cache file
				File zipFile = FileUtil.createFile(
						StreamUtil.getByteArrayStream(datas), cachePath
								+ "remoteTmp.dph", false);

				// up zip and show progress
				ZipUtil.upZip(zipFile, folderPath, true, this, 80, 100);

				editor.putString(Conast.WEB_VERSION_KEY, remoteVersion)
						.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);

		// update progress
		pd.setProgress(values[0]);
		if (100 > values[0]) {
			pd.setMessage(this.res.getString(R.string.progress_message,
					values[0]));
		} else {
			pd.setMessage(this.res.getString(R.string.finished_message));
		}
	}

	@Override
	protected void onPostExecute(Boolean aBoolean) {
		super.onPostExecute(aBoolean);

		// go to next activity when context is not null
		if (null != context) {
			Intent intent = new Intent();
			intent.setClass(context, this.nextActivityClass);

			context.startActivity(intent);
			pd.dismiss();
			context.finish();
		}
	}
}
