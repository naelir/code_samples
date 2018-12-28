package code_samples.file;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LineWriter {
	private static final Logger LOG = LoggerFactory.getLogger(LineWriter.class);

	private final String encoding;

	private final String fileName;

	public LineWriter(final String fileName, final String encoding) {
		this.fileName = fileName;
		this.encoding = encoding;
	}

	public <T> void write(final Collection<T> collection, final boolean append) {
		try (
			FileOutputStream fos = new FileOutputStream(this.fileName, append);
			OutputStreamWriter or = new OutputStreamWriter(fos, this.encoding);
			BufferedWriter br = new BufferedWriter(or)) {
			for (final Object o : collection) {
				br.write(o.toString());
				br.write("\n");
			}
		} catch (final IOException e) {
			LOG.error("error:", e);
		}
	}

	public <K, V> void write(final Map<K, V> map, final boolean append) {
		this.write(map.values(), append);
	}

	public void write(final Object o, final boolean append) {
		this.write(Arrays.asList(o), append);
	}

	public void write(final Object[] o, final boolean append) {
		this.write(Arrays.asList(o), append);
	}
}
