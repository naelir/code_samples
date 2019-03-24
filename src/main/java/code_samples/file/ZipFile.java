package code_samples.file;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZipFile {
	private static final Logger LOG = LoggerFactory.getLogger(ZipFile.class);

	public void zip(final Path source, final Path zip) throws IOException {
		try (
			OutputStream fos = Files.newOutputStream(zip);
			ZipOutputStream zos = new ZipOutputStream(fos)
		) {
			Files.walk(source)
					.filter(path -> Files.isDirectory(path) == false)
					.forEach(path -> {
						final String file = source.relativize(path)
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
