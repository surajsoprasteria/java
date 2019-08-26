package org.wipo.connect.shared.identifierprocessor.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.wipo.connect.shared.identifierprocessor.entity.ImportStatusFlat;

public interface ImportStatusFlatRepository<P> extends CrudRepository<ImportStatusFlat, Long> { 
		  
	//SELECT external_site.id_external_site AS ES_id_external_site, external_site.host AS ES_host,external_site.port AS ES_port,external_site.user_name AS ES_user_name, external_site.pass AS ES_password, CASE WHEN external_site.code is null THEN 'IP_IMPORT_SFTP' ELSE external_site.code END AS ES_code FROM external_site WHERE external_site.code = 'IP_IMPORT_SFTP'
	//INSERT INTO ip_import (import_code, fk_status, start_date, end_date) VALUES (#{importCode},#{fkStatus}, #{startDate:TIMESTAMP}, #{endDate:TIMESTAMP})
	@Query(value = "SELECT import_status.id_import_status,import_status.code,translation.default_value AS default_value,import_status.sort_order sort_order	FROM import_status INNER JOIN translation ON translation.id_translation = import_status.fk_value WHERE import_status.code = 'QUEUED'" , nativeQuery = true)
	ImportStatusFlat findByCode();

}
