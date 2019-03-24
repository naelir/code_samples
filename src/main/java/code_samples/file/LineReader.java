package code_samples.file;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LineReader {
	private final Charset encoding;

	private final Path in;

	public LineReader(final Path in, final Charset encoding) {
		this.in = in;
		this.encoding = encoding;
	}

	public List<String> getLines() throws IOException {
		final List<String> list = new ArrayList<>();
		try (
			Stream<String> stream = Files.lines(this.in, this.encoding)
		) {
			stream.forEach(element -> {
				if (element.isEmpty() == false)
					list.add(element);
			});
		}
		return list;
	}
}
