package org.wipo.connect.shared.identifierprocessor.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.wipo.connect.shared.identifierprocessor.entity.ImportStatusFlat;
import org.wipo.connect.shared.identifierprocessor.entity.IpImport;

public interface IpImportRepository<P> extends CrudRepository<IpImport, Long> {

	// @Query("SELECT t.title FROM Todo t where t.id = :id")
	@Query(value = "SELECT import_status.id_import_status,import_status.code,translation.default_value AS default_value,import_status.sort_order sort_order	FROM import_status INNER JOIN translation ON translation.id_translation = import_status.fk_value WHERE import_status.code = 'QUEUED'", nativeQuery = true)
	ImportStatusFlat findByCode();

	@Query(value = "INSERT INTO ip_import (import_code, fk_status, start_date, end_date) VALUES (:importCode,:fkStatus,:startDate,:endDate)", nativeQuery = true)
	void insertIpImort(@Param("importCode") String importCode, @Param("fkStatus") Long fkStatus,
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
