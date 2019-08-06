package org.wipo.connect.shared.business.import_ip;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wipo.connect.common.utils.ConstantUtils;
import org.wipo.connect.shared.exchange.dto.impl.Cmo;
import org.wipo.connect.shared.exchange.dto.impl.CreationClassFlat;
import org.wipo.connect.shared.exchange.dto.impl.Identifier;
import org.wipo.connect.shared.exchange.dto.impl.IpiRightTypeFlat;
import org.wipo.connect.shared.exchange.dto.impl.IpiRoleFlat;
import org.wipo.connect.shared.exchange.dto.impl.Territory;
import org.wipo.connect.shared.exchange.enumerator.IpiRightTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.TerritoryOrderTypeEnum;
import org.wipo.connect.shared.exchange.vo.TerritoryHierarchyVO;
import org.wipo.connect.shared.persistence.dao.CmoDAO;
import org.wipo.connect.shared.persistence.dao.CommonDAO;
import org.wipo.connect.shared.persistence.dao.CreationClassDAO;
import org.wipo.connect.shared.persistence.dao.RightTypeDAO;

@Service
public class IpCodeMapper {

    @Autowired
    private CommonDAO commonDAOImpl;
    @Autowired
    private RightTypeDAO rightTypeDAOImpl;
    @Autowired
    private CmoDAO cmoDAOImpl;
    @Autowired
    private CreationClassDAO creationClassDAOImpl;

    private List<Territory> territoryList;

    private List<Territory> territoryCountryList;
    private Map<String, Long> territoryCountryMap;

    private TerritoryHierarchyVO territoryHierarchy;
    private Map<String, Long> creationClassCodMap;
    private Map<String, Long> ipiRightTypeCodMap;
    private Map<String, Long> ipiRoleCodMap;
    private Map<Long, IpiRightTypeFlat> ipiRightTypeMap;
    private Map<Long, IpiRoleFlat> ipiRoleMap;
    private Map<String, Cmo> cmoCodMap;
    private Map<String, Identifier> identifierCodMap;
    private Map<String, List<String>> allIrtByCcMap;

    public void init() {
	territoryList = commonDAOImpl.findAllTerritory(TerritoryOrderTypeEnum.CODE_TISA);

	territoryCountryList = commonDAOImpl.findAllCountriesFromTerritory();
	territoryCountryMap = territoryCountryList.stream().collect(Collectors.toMap(Territory::getTisa, Territory::getId));
	territoryHierarchy = commonDAOImpl.getTerritoryHierarchy(ConstantUtils.WORLD_TISA_CODE);

	List<CreationClassFlat> creationClassList = creationClassDAOImpl.findAllCreationClass();
	creationClassCodMap = creationClassList.stream().collect(Collectors.toMap(CreationClassFlat::getCode, CreationClassFlat::getId));

	List<IpiRightTypeFlat> rightTypeList = rightTypeDAOImpl.findAllIpiRightType();
	ipiRightTypeMap = rightTypeList.stream().collect(Collectors.toMap(IpiRightTypeFlat::getId, ipi -> ipi));
	ipiRightTypeCodMap = rightTypeList.stream().collect(Collectors.toMap(IpiRightTypeFlat::getCode, IpiRightTypeFlat::getId));

	List<IpiRoleFlat> ipRoleList = commonDAOImpl.findAllIpiRole();
	ipiRoleMap = ipRoleList.stream().collect(Collectors.toMap(IpiRoleFlat::getId, ipi -> ipi));
	ipiRoleCodMap = ipRoleList.stream().collect(Collectors.toMap(IpiRoleFlat::getCode, IpiRoleFlat::getId));

	List<Cmo> cmoList = cmoDAOImpl.findAll();
	cmoCodMap = cmoList.stream().collect(Collectors.toMap(Cmo::getCode, cmo -> cmo));

	List<Identifier> identifierList = commonDAOImpl.selectAllIdentifier();
	identifierCodMap = identifierList.stream().collect(Collectors.toMap(Identifier::getCode, identifier -> identifier));

	allIrtByCcMap = new HashMap<String, List<String>>();
	for (CreationClassFlat cc : creationClassList) {
	    List<IpiRightTypeFlat> irtList = commonDAOImpl.findAllIpiRightTypeByCc(cc.getId());
	    List<String> codeList = irtList.stream().map(IpiRightTypeFlat::getCode).collect(Collectors.toList());
	    codeList.remove(IpiRightTypeEnum.ALL.name());
	    allIrtByCcMap.put(cc.getCode(), codeList);
	}
    }

    public List<Territory> getTerritoryList() {
	return territoryList;
    }

    public Long getTerritoryCountryIdByCode(String tisaCode) {
	return territoryCountryMap.get(tisaCode);
    }

    public TerritoryHierarchyVO getTerritoryHierarchy() {
	return territoryHierarchy;
    }

    public Long getCreationClassIdByCode(String creationClassCode) {
	return creationClassCodMap.get(creationClassCode);
    }

    public Long getIpiRightTypeByCode(String ipiRightTypeCode) {
	return ipiRightTypeCodMap.get(ipiRightTypeCode);
    }

    public Long getIpiRoleByCode(String ipiRoleCode) {
	return ipiRoleCodMap.get(ipiRoleCode);
    }

    public IpiRoleFlat getIpiRoleById(Long id) {
	return ipiRoleMap.get(id);
    }

    public IpiRightTypeFlat getIpiRightTypeById(Long id) {
	return ipiRightTypeMap.get(id);
    }

    public Long getIdIpiRightTypeAll() {
	return getIpiRightTypeByCode(IpiRightTypeEnum.ALL.name());
    }

    public Long getCmoIdByCode(String cmoCode) {
	Cmo cmo = cmoCodMap.get(cmoCode);
	if (null != cmo) {
	    return cmo.getId();
	} else {
	    return null;
	}
    }

    public Cmo getCmoByCode(String cmoCode) {
	return cmoCodMap.get(cmoCode);
    }

    public Identifier getIdentifierByCode(String code) {
	return identifierCodMap.get(code);
    }

    public List<String> getIrtCodeListByCc(String creationClassCode) {
	return allIrtByCcMap.get(creationClassCode);
    }

}
