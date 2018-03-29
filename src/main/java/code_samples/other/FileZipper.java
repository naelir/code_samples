package code_samples.other;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileZipper {
	private static final Logger LOG = LoggerFactory.getLogger(FileZipper.class);

	public void pack(final Path sourceDirPath, final Path zipFilePath) throws IOException {
		try (OutputStream fos = Files.newOutputStream(zipFilePath); ZipOutputStream zos = new ZipOutputStream(fos)) {
			Files.walk(sourceDirPath)
				.filter(path -> !Files.isDirectory(path))
				.forEach(path -> {
					final String file = sourceDirPath.relativize(path)
						.toString();
					final ZipEntry zipEntry = new ZipEntry(file);
					try {
						zos.putNextEntry(zipEntry);
						Files.copy(path, zos);
						zos.closeEntry();
					} catch (final IOException e) {
						LOG.error("error:", e);
					}
				});
		}
	}
}
