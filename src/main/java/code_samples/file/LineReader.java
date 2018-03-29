package code_samples.file;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LineReader {
	private static final Logger LOG = LoggerFactory.getLogger(LineReader.class);

	private final String encoding;

	private final String fileName;

	public LineReader(final String fileName, final String encoding) {
		this.fileName = fileName;
		this.encoding = encoding;
	}

	public List<String> getLines() {
		final List<String> list = new ArrayList<>();
		final Path file = Paths.get(this.fileName);
		try (Stream<String> stream = Files.lines(file, Charset.forName(this.encoding))) {
			stream.forEach(element -> {
				if (!element.isEmpty())
					list.add(element);
			});
		} catch (final IOException e) {
			LOG.error("error:", e);
		}
		return list;
	}
}
