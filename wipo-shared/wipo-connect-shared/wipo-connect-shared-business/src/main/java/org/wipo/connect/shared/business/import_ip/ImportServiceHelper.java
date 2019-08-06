package org.wipo.connect.shared.business.import_ip;

import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.shared.exchange.dto.impl.IpImport;

public interface ImportServiceHelper {

	/**
	 * Save output file.
	 *
	 * @param ipImport
	 *            the ip import
	 * @param fileContent
	 *            the file content
	 * @throws WccException
	 *             the wcc exception
	 */
	void saveOutputFile(IpImport ipImport, byte[] fileContent) throws WccException;

	void saveOutputFile(IpImport ipImport, String tempFilePath) throws WccException;

	byte[] readFileContent(String filename) throws WccException;

	String getTempFile(String filename) throws WccException;

}