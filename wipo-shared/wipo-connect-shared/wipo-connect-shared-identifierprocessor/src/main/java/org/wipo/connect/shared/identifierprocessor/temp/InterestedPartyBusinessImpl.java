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

package org.wipo.connect.shared.identifierprocessor.temp;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.exception.WccExceptionCodeEnum;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.shared.identifierprocessor.entity.ImportStatusFlat;
import org.wipo.connect.shared.identifierprocessor.repository.ImportStatusFlatRepository;
import org.wipo.connect.shared.identifierprocessor.service.ImportStatusFlatService;
import org.wipo.connect.shared.identifierprocessor.service.SequenceService;

import net.bull.javamelody.MonitoredWithSpring;

@Service
@MonitoredWithSpring
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class InterestedPartyBusinessImpl implements InterestedPartyBusiness {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = WipoLoggerFactory.getLogger(InterestedPartyBusinessImpl.class);

    /*@Value("#{configProperties['workGroup.nameType'].split(',')}")
    private List<String> groupNameTypeList;

    @Value("#{configProperties['solr.rebuild-page-size']}")
    private Integer solrRebuildPageSize;

    @Value("#{configProperties['export.rightOwner.pageSize']}")
    private Integer exportRightOwnerPageSize;*/

   /* *//** The interested party DAO. *//*
    @Autowired
    private InterestedPartyDAO interestedPartyDAO;

    *//** The affiliation DAO. *//*
    @Autowired
    private AffiliationDAO affiliationDAO;

    *//** The ip task DAO. *//*
    @Autowired
    private IpTaskDAO ipTaskDAO;

    *//** The name DAO. *//*
    @Autowired
    private NameDAO nameDAO;*/

    /** The common DAO. */
  /*  @Autowired
    private CommonDAO commonDAO;

    *//** The ip import DAO. *//*
    @Autowired
    private IpImportDAO ipImportDAO;*/

    /** The ip index queue dao. *//*
    @Autowired
    private IpIndexQueueDAO ipIndexQueueDAO;

    *//** The interested party index dao. *//*
    @Autowired
    private InterestedPartyIndexDao interestedPartyIndexDao;

    *//** The dozer helper. *//*
    @Autowired
    private DozerHelper dozerHelper;

    *//** The derived view dao. *//*
    @Autowired
    private DerivedViewDAO derivedViewDAO;

    @Autowired
    private IdentifierManagerDAO identifierManagerDAO;

    @Autowired
    private ImportExportHelper importExportHelper;

    *//** The dozer utility. *//*
    @Autowired
    private DozerUtility dozerUtility;

    @Autowired
    private CmoDAO cmoDAO;

    @Autowired
    private RightTypeDAO rightTypeDAO;

    @Autowired
    private CreationClassDAO creationClassDAO;

    @Autowired
    private Properties configProperties;

    @Autowired
    private IpSftpClient ipSftpClient;*/
    
    
    
    @Autowired
    ImportStatusFlatService importStatusFlatService;
    
    @Autowired
    SequenceService sequenceService;

    ///////////////////////////////////////////////////// CRUD Interested Party/////////////////////////////////////////////////////
    public IpImport insertNewImportFile(IpImport ipImport) throws WccException {
	try {
	    String filename = ipImport.getInputFile().getFileName();
	    //Long count = ipImportDAO.countIpImportFileByName(filename);   IpImportMapper.xml  >>> countIpImportFileByName
	    //Long count = (long) 1; //hardcode
	    
	    Long count = sequenceService.findByFileName(filename);
	    
	    System.out.println( "count ::" + count);
	    System.out.println("ipImport.getFkStatus() :: "+ipImport.getFkStatus());
	    if (Long.compare(count, 0L) == 0) {
		if (ipImport.getFkStatus() == null) {
		    //ImportStatusFlat status = commonDAO.findImportStatusByCode(ImportStatusEnum.QUEUED);   ImportStatusFlatMapper.xml >> findByCode
			
			System.out.println("calling ImportStatusFlatService");						
			ImportStatusFlat status = importStatusFlatService.findByName();
			System.out.println("status Id ::"+ status.getIdImportStatus());
			ipImport.setFkStatus(status.getIdImportStatus());
		}
		
		/*ipImport = new IpImport();
		
		ipImport.setId(23L);*/
		
		LOGGER.debug("Skipping insertIpImport DAO call");
		System.out.println("Skipping insertIpImport DAO call");
		//return ipImportDAO.insertIpImport(ipImport);  IpImportMapper.xml>> insertIpImport  
		
		return ipImport;

	    } else {
		LOGGER.debug("The file {} is already present... skipping", filename);
		System.out.println("The file {} is already present... skipping" + filename);
	    }

	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
	return null;
    }

}
