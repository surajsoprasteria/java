/*
 * Copyright (C) 2015 World Intellectual Property Organization (WIPO).
 * All Rights Reserved.
 *
 * This file is part of WIPO Connect.
 *
 *
 * @author Fincons
 *
 */
package org.wipo.connect.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;

/**
 * The Class ZipUtils.
 */
public class ZipUtils {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(ZipUtils.class);

    private ZipUtils() {
	super();
    }

    public static void zip(final String outFilename, final String folder) throws IOException {

	(new File(outFilename)).delete();

	Path p = Files.createFile(Paths.get(outFilename));
	try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(p))) {
	    Path pp = Paths.get(folder);
	    Files.walk(pp).filter(path -> !Files.isDirectory(path)).forEach(path -> {
		ZipEntry zipEntry = new ZipEntry(pp.relativize(path).toString());
		try {
		    zs.putNextEntry(zipEntry);
		    Files.copy(path, zs);
		    zs.closeEntry();
		} catch (IOException e) {
		    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
		}
	    });
	}

    }

    public static void zip(final String outFilename, final List<String> filenames) throws IOException {
	ZipOutputStream out = null;
	// Create a buffer for reading the files
	final byte[] buf = new byte[1024];
	try {
	    // Create the ZIP file
	    // String outFilename = "outfile.zip";
	    out = new ZipOutputStream(new FileOutputStream(outFilename));

	    // Compress the files
	    for (final String filename : filenames) {

		try (FileInputStream in = new FileInputStream(filename)) {
		    Path path = Paths.get(filename);
		    // Add ZIP entry to output stream without directories.
		    out.putNextEntry(new ZipEntry(path.getFileName().toString()));

		    // Transfer bytes from the file to the ZIP file
		    int len;
		    while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		    }

		    // Complete the entry
		    out.closeEntry();

		    // Complete the ZIP file
		    in.close();
		} catch (final Exception e) {
		    LOGGER.error("Error compressing " + filename, e);
		}
	    }
	} catch (final IOException e) {
	    throw e;
	} finally {
	    try {
		// Complete the ZIP file
		if (out != null) {
		    out.close();
		}
	    } catch (final Exception e) {
		LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    }
	}
    }

    /**
     * aggiunge file ad un archivio zip.
     *
     * @param zipFile
     *            the zip file
     * @param files
     *            the files
     * @param tmpDirectory
     *            the tmp directory
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public static void addFilesToZip(final File zipFile, final List<File> files, String tmpDirectory) throws IOException {
	if (!zipFile.exists()) {
	    zipFile.createNewFile();
	}
	// logger.debug("Directory temporanea "+tmpDirectory);
	// get a temp file
	final File tempFile = File.createTempFile(zipFile.getName(), null, new File(tmpDirectory));
	tempFile.deleteOnExit();
	// logger.debug("File temporaneo creato nella cartella "+tmpDirectory);
	final boolean renameOk = renameTo(zipFile, tempFile);
	if (!renameOk) {
	    throw new RuntimeException("could not rename the file " + zipFile.getAbsolutePath() + " to " + tempFile.getAbsolutePath());
	}
	final byte[] buf = new byte[1024];
	final ZipInputStream zin = new ZipInputStream(new FileInputStream(tempFile));
	final ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));

	ZipEntry entry = zin.getNextEntry();
	while (entry != null) {
	    final String name = entry.getName();
	    boolean notInFiles = true;
	    for (final File f : files) {
		if (f.getName().equals(name)) {
		    notInFiles = false;
		    break;
		}
	    }
	    if (notInFiles) {
		// Add ZIP entry to output stream.
		out.putNextEntry(new ZipEntry(name));
		// Transfer bytes from the ZIP file to the output file
		int len;
		while ((len = zin.read(buf)) > 0) {
		    out.write(buf, 0, len);
		}
	    }
	    entry = zin.getNextEntry();
	}
	// Close the streams
	zin.close();
	// Compress the files
	for (final File file : files) {
	    final InputStream in = new FileInputStream(file);
	    // Add ZIP entry to output stream.
	    out.putNextEntry(new ZipEntry(file.getName()));
	    // Transfer bytes from the file to the ZIP file
	    int len;
	    while ((len = in.read(buf)) > 0) {
		out.write(buf, 0, len);
	    }
	    // Complete the entry
	    out.closeEntry();
	    in.close();
	}
	// Complete the ZIP file
	out.close();
	tempFile.delete();
    }

    /**
     * rinomina un file in un altro. Questo metodo e' da preferire a File.renameTo in quento garantisce compatibilita' con i sistemi operativi windows e unix. Viene prima fatto un controllo sul file
     * di destinazione: se esiste viene rimosso. Viene poi rinominato il file utilizzando File.renameTo e quindi viene rimosso il file originale
     *
     * @param orig
     *            il file originale
     * @param dest
     *            il file di destinazione
     * @return true se il file e' stato rinominato, false altrimenti
     * @see java.io.File.renameTo
     */
    public static boolean renameTo(final File orig, final File dest) {

	boolean ret = false;

	if (dest.exists()) {
	    dest.delete();
	}

	ret = orig.renameTo(dest);

	if (ret && orig.exists()) {
	    orig.delete();
	}

	return ret;
    }
}
