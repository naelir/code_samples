package code_samples.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import org.jasypt.util.text.BasicTextEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileEnryptor {
	private static final Logger LOG = LoggerFactory.getLogger(FileEnryptor.class);

	private final Path from;

	private final Charset encoding;

	private final String password;

	private final Path to;

	public FileEnryptor(final Path from, final Path to, final Charset encoding, final String password) {
		this.from = from;
		this.to = to;
		this.encoding = encoding;
		this.password = password;
	}

	public void encrypt() {
		final BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword(this.password);
		try (
			InputStream is = Files.newInputStream(this.from, StandardOpenOption.CREATE, StandardOpenOption.READ);
			InputStreamReader isr = new InputStreamReader(is, this.encoding);
			BufferedReader br = new BufferedReader(isr);
			OutputStream os = Files.newOutputStream(this.to, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
			OutputStreamWriter osw = new OutputStreamWriter(os, this.encoding);
			BufferedWriter bw = new BufferedWriter(osw);
		) {
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				bw.write(textEncryptor.encrypt(line));
				bw.newLine();
			}
		} catch (final Exception e) {
			LOG.error("error during writing questions", e);
		}
	}
}
