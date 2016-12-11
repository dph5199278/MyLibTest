/**
 * org.dph.common.libs.utils   1.00    2016/10/1
 * Public share libs
 * All Rights Reserved,Copyright©dph
 * @author dph
 */
package org.dph.common.libs.utils;

import org.dph.common.libs.common.BaseAsyncTask;

import java.io.File;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * ZipUtil
 * 
 * @author dph
 * @version 1.0
 */
public class ZipUtil {

	/**
	 * Function: Extract the zip file by zip path.
	 * 
	 * @author dph
	 * @param zipPath
	 *            the zip path.
	 * @param folderPath
	 *            extract to the folder path.
	 * @param isDel
	 *            true if the zip extract finished to delete, false not to
	 *            delete.
	 * @param task
	 *            show progress's task.
	 * @param nowProgress
	 *            now progress.
	 * @param maxProgress
	 *            max progress.
	 */
	public static void upZip(final String zipPath, final String folderPath,
			final boolean isDel, final BaseAsyncTask<?, ?> task,
			final int nowProgress, final int maxProgress) {
		File zipFile = new File(zipPath);
		upZip(zipFile, folderPath, isDel, task, nowProgress, maxProgress);
	}

	/**
	 * Function: Extract the zip file.
	 * 
	 * @author dph
	 * @param zipFile
	 *            the zip file.
	 * @param folderPath
	 *            extract to the folder path.
	 * @param isDel
	 *            true if the zip extract finished to delete, false not to
	 *            delete.
	 * @param task
	 *            show progress's task.
	 * @param nowProgress
	 *            now progress.
	 * @param maxProgress
	 *            max progress.
	 */
	public static void upZip(final File zipFile, final String folderPath,
			final boolean isDel, BaseAsyncTask<?, ?> task, int nowProgress,
			int maxProgress) {
		try {
			// create up zip folder
			FileUtil.mks(folderPath, true);

			// add "/" to end of path
			String tmpFolderPath = folderPath;
			if (!folderPath.endsWith(File.separator)) {
				tmpFolderPath += File.separator;
			}

			// get zip file
			ZipFile zf = new ZipFile(zipFile);
			StringBuffer fileStrSb = new StringBuffer();

			// up zip when zip file has file(s) or folder(s)
			if (null != zf && 0 < zf.size()) {
				int total = zf.size();
				int index = 0;
				int surplusProgress = maxProgress - nowProgress;
				int progress;

				// ergodic zip
				Enumeration<? extends ZipEntry> entrys = zf.entries();
				for (; entrys.hasMoreElements();) {
					ZipEntry entry = entrys.nextElement();

					fileStrSb.delete(0, fileStrSb.length());
					fileStrSb.append(tmpFolderPath).append(entry.getName());

					if (entry.isDirectory()) {
						// skip when it is folder
						continue;
					}

					// create file
					FileUtil.createFile(zf.getInputStream(entry), CharsetUtil
							.changCharset(fileStrSb.toString(),
									CharsetUtil.ISO8859_1, CharsetUtil.UTF_8),
							false);

					// show progress when task is not null
					index++;
					if (null != task) {
						progress = index * surplusProgress / total
								+ nowProgress;
						if (0 == progress % 10)
							task.callbackPublishProgress(progress);
					}
				}
				zf.close();
			}

			// delete zip when isDel is true
			if (isDel) {
				zipFile.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
