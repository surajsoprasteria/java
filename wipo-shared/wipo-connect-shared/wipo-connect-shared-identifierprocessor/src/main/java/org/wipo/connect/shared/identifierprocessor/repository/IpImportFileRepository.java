package org.wipo.connect.shared.identifierprocessor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.wipo.connect.shared.identifierprocessor.entity.IpImportFile;

public interface IpImportFileRepository<P> extends CrudRepository<IpImportFile, Long> {

	// @Query("SELECT t.title FROM Todo t where t.id = :id")
	@Query(value = "SELECT * FROM ip_import_file WHERE filename = :fileName",
			countQuery= "SELECT COUNT(*) FROM ip_import_file WHERE filename = :fileName",
			nativeQuery = true)
	List<IpImportFile> findByFileName(@Param("fileName") String fileName);

	@Query(value = "INSERT INTO ip_import_file (fk_ip_import,file_type,filename,filesize,content_type) VALUES (:fkIpImport,:fileType,:fileName,:fileSize,:contentType)", nativeQuery = true)
	void insertIpImortFile(@Param("fkIpImport") Long fkIpImport, @Param("fileType") String fileType,
			@Param("fileName") String fileName, @Param("fileSize") Long fileSize,
			@Param("contentType") String contentType);

}
