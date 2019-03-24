package code_samples.file;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class LineWriter {
	private final Charset encoding;

	private final Path in;

	public LineWriter(final Path in, final Charset encoding) {
		this.in = in;
		this.encoding = encoding;
	}

	public <T> void write(final Collection<T> collection, final OpenOption... openOptions) throws IOException {
		try (
			OutputStream fos = Files.newOutputStream(this.in, openOptions);
			OutputStreamWriter or = new OutputStreamWriter(fos, this.encoding);
			BufferedWriter br = new BufferedWriter(or)
		) {
			for (final Object o : collection) {
				br.write(o.toString());
				br.newLine();
			}
		}
	}

	public <K, V> void write(final Map<K, V> map, final OpenOption... openOptions) throws IOException {
		this.write(map.values(), openOptions);
	}

	public void write(final Object o, final OpenOption... openOptions) throws IOException {
		this.write(Arrays.asList(o), openOptions);
	}

	public void write(final Object[] o, final OpenOption... openOptions) throws IOException {
		this.write(Arrays.asList(o), openOptions);
	}
}
