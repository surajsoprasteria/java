package org.wipo.connect.shared.business.import_work;

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
import org.wipo.connect.shared.exchange.dto.impl.ReferenceFlat;
import org.wipo.connect.shared.exchange.dto.impl.RightTypeFlat;
import org.wipo.connect.shared.exchange.dto.impl.RoleFlat;
import org.wipo.connect.shared.exchange.dto.impl.Territory;
import org.wipo.connect.shared.exchange.enumerator.IpiRightTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.ReferenceTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.TerritoryOrderTypeEnum;
import org.wipo.connect.shared.exchange.vo.TerritoryHierarchyVO;
import org.wipo.connect.shared.persistence.dao.CmoDAO;
import org.wipo.connect.shared.persistence.dao.CommonDAO;
import org.wipo.connect.shared.persistence.dao.CreationClassDAO;
import org.wipo.connect.shared.persistence.dao.ReferenceDAO;
import org.wipo.connect.shared.persistence.dao.RightTypeDAO;

@Service
public class WorkCodeMapper {

    @Autowired
    private CommonDAO commonDAOImpl;
    @Autowired
    private RightTypeDAO rightTypeDAOImpl;
    @Autowired
    private CmoDAO cmoDAOImpl;
    @Autowired
    private ReferenceDAO referenceDAOImpl;
    @Autowired
    private CreationClassDAO creationClassDAOImpl;

    private List<Territory> territoryList;

    private List<Territory> territoryCountryList;
    private Map<String, Long> territoryCountryMap;

    private TerritoryHierarchyVO territoryHierarchy;
    private Map<String, Long> creationClassCodMap;
    private Map<String, Long> ipiRightTypeCodMap;
    private Map<String, Long> workTypeCodMap;
    private Map<String, Long> titleTypeCodMap;
    private Map<String, Cmo> cmoCodMap;
    private Map<String, Identifier> identifierCodMap;
    private Map<String, List<String>> allIrtByCcMap;
    private Map<String, RightTypeFlat> rightCategoryMap;
    private Map<String, RoleFlat> workRoleMap;
    private Map<String, Long> importSourceTypeCodMap;

    public void init() {
	territoryList = commonDAOImpl.findAllTerritory(TerritoryOrderTypeEnum.CODE_TISA);

	territoryCountryList = commonDAOImpl.findAllCountriesFromTerritory();
	territoryCountryMap = territoryCountryList.stream().collect(Collectors.toMap(Territory::getTisa, Territory::getId));

	territoryHierarchy = commonDAOImpl.getTerritoryHierarchy(ConstantUtils.WORLD_TISA_CODE);

	List<CreationClassFlat> creationClassList = creationClassDAOImpl.findAllCreationClass();
	creationClassCodMap = creationClassList.stream().collect(Collectors.toMap(CreationClassFlat::getCode, CreationClassFlat::getId));

	List<IpiRightTypeFlat> rightTypeList = rightTypeDAOImpl.findAllIpiRightType();
	ipiRightTypeCodMap = rightTypeList.stream().collect(Collectors.toMap(IpiRightTypeFlat::getCode, IpiRightTypeFlat::getId));

	List<ReferenceFlat> listWorkType = referenceDAOImpl.findReferenceByCode(ReferenceTypeEnum.WORK_TYPE.name());
	workTypeCodMap = listWorkType.stream().collect(Collectors.toMap(ReferenceFlat::getRefCode, ReferenceFlat::getId));

	List<ReferenceFlat> listTitleType = referenceDAOImpl.findReferenceByCode(ReferenceTypeEnum.TITLE_TYPE.name());
	titleTypeCodMap = listTitleType.stream().collect(Collectors.toMap(ReferenceFlat::getRefCode, ReferenceFlat::getId));

	List<ReferenceFlat> importSourceType = commonDAOImpl.findReferenceByCode(ReferenceTypeEnum.WORK_SOURCE_TYPE);
	importSourceTypeCodMap = importSourceType.stream().collect(Collectors.toMap(ReferenceFlat::getCode, ReferenceFlat::getId));

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

	List<RightTypeFlat> rightCategoryList = commonDAOImpl.findRightTypeListByCC(null);
	rightCategoryMap = rightCategoryList.stream().collect(Collectors.toMap(RightTypeFlat::getCode, RightTypeFlat -> RightTypeFlat));

	List<RoleFlat> workRoleList = commonDAOImpl.findWorkRoleListByCC(null);
	workRoleMap = workRoleList.stream().collect(Collectors.toMap(RoleFlat::getCode, RoleFlat -> RoleFlat));
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

    public Long getWorkTypeByCode(String workTypeCode) {
	return workTypeCodMap.get(workTypeCode);
    }

    public Long getTitleTypeByCode(String titleTypeCode) {
	return titleTypeCodMap.get(titleTypeCode);
    }

    public Cmo getCmoByCode(String cmoCode) {
	return cmoCodMap.get(cmoCode);
    }

    public Identifier getIdentifierByCode(String code) {
	return identifierCodMap.get(code);
    }

    public Map<String, RightTypeFlat> getRightCategoryMap() {
	return rightCategoryMap;
    }

    public Map<String, RoleFlat> getWorkRoleMap() {
	return workRoleMap;
    }

    public Long getWorkRoleIdByCode(String code) {
	RoleFlat role = workRoleMap.get(code);
	if (role != null) {
	    return role.getId();
	}
	return null;
    }

    public Long getImportSourceReferenceByCode(String code) {
	return importSourceTypeCodMap.get(code);
    }

}
