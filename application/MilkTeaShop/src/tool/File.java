package tool;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class File {
	public static void copy(java.io.File sourceFile, java.io.File destFile) throws IOException {
		if (!destFile.exists()) {
			destFile.createNewFile();
		}
		FileChannel source = new RandomAccessFile(sourceFile, "rw").getChannel();
		FileChannel destination = new RandomAccessFile(destFile, "rw").getChannel();
		source.transferTo(0, source.size(), destination);
		source.close();
		destination.close();
	}
}
