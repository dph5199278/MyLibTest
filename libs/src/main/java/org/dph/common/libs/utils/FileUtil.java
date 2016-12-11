/**
 * org.dph.common.libs.utils   1.00    2016/10/1
 * Public share libs
 * All Rights Reserved,Copyright©dph
 * @author dph
 */
package org.dph.common.libs.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * FileUtil
 * 
 * @author dph
 * @version 1.0
 */
public class FileUtil {

	public static final int BUFF_SIZE = 1024 * 1024; // 1M Byte

	/**
	 * Function: The {@code is} that writes to {@code path}. The file will be
	 * truncated if it exists, and created if it doesn't exist.
	 * 
	 * @author dph
	 * @param is
	 *            input stream.
	 * @param path
	 *            file or folder's path.
	 * @param isDir
	 *            when true, the path is a folder. when false, the path is a
	 *            file.
	 * @return a be wrote file or folder.
	 * @throws IOException
	 *             if an error occurs while closing the stream or the path file
	 *             cannot be opened for writing.
	 */
	public static File createFile(final InputStream is, final String path,
			final boolean isDir) throws IOException {
		File file = FileUtil.mks(path, isDir);
		FileOutputStream os = new FileOutputStream(file);

		byte[] buffs = new byte[FileUtil.BUFF_SIZE];
		int len;

		while ((len = is.read(buffs)) > 0) {
			os.write(buffs, 0, len);
		}

		is.close();
		os.close();

		return file;
	}

	/**
	 * Function: Creates the directory named by this file, assuming its parents
	 * exist.
	 * 
	 * @author dph
	 * @param path
	 *            file or folder's path.
	 * @param isDir
	 *            when true, the path is a folder. when false, the path is a
	 *            file.
	 * @return a new file or folder. And the path exist.
	 * @throws IOException
	 *             if it's not possible to create the file or folder.
	 */
	public static File mk(final String path, final boolean isDir)
			throws IOException {
		File file = new File(path);
		mk(file, isDir);
		return file;
	}

	/**
	 * Function: Creates the directory named by this file, assuming its parents
	 * exist.
	 * 
	 * @author dph
	 * @param file
	 * 
	 * @param isDir
	 *            when true, the path is a folder. when false, the path is a
	 *            file.
	 * @return a new file or folder. And the path exist.
	 * @throws IOException
	 *             if it's not possible to create the file or folder.
	 */
	public static boolean mk(final File file, final boolean isDir)
			throws IOException {
		if (!file.exists()) {
			if (isDir) {
				return file.mkdir();
			} else {
				File parent = file.getParentFile();
				if (!parent.exists()) {
					parent.mkdir();
				}
				return file.createNewFile();
			}
		}
		return true;
	}

	/**
	 * Function: Creates the directory named by this file, creating missing
	 * parent directories if necessary.
	 * 
	 * @author dph
	 * @param path
	 *            file or folder's path.
	 * @param isDir
	 *            when true, the path is a folder. when false, the path is a
	 *            file.
	 * @return a new file or folder. And the path exist.
	 * @throws IOException
	 *             if it's not possible to create the file or folder.
	 */
	public static File mks(final String path, final boolean isDir)
			throws IOException {
		File file = new File(path);
		mks(file, isDir);
		return file;
	}

	/**
	 * Function: Creates the directory named by this file, creating missing
	 * parent directories if necessary.
	 * 
	 * @author dph
	 * @param file
	 * 
	 * @param isDir
	 *            when true, the path is a folder. when false, the path is a
	 *            file.
	 * @return a new file or folder. And the path exist.
	 * @throws IOException
	 *             if it's not possible to create the file or folder.
	 */
	public static boolean mks(final File file, final boolean isDir)
			throws IOException {
		if (!file.exists()) {
			if (isDir) {
				return file.mkdirs();
			} else {
				File parent = file.getParentFile();
				if (!parent.exists()) {
					parent.mkdirs();
				}
				return file.createNewFile();
			}
		}
		return true;
	}
}
