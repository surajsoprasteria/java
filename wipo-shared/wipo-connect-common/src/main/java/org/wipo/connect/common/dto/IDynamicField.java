package org.wipo.connect.common.dto;

public interface IDynamicField {

    String getDefaultValue();

    String getFieldCode();

    String getFieldDescription();

    Long getId();

    Boolean getMandatory();

    Integer getMaxLength();

    Integer getPosition();

    String getTypeCode();

    void setDefaultValue(String defaultValue);

    void setFieldCode(String fieldCode);

    void setFieldDescription(String fieldDescription);

    void setId(Long id);

    void setMandatory(Boolean mandatory);

    void setMaxLength(Integer maxLength);

    void setPosition(Integer position);

    void setTypeCode(String typeCode);

    Long getFkCreationClass();

    Boolean getIsUnique();

    void setFkCreationClass(Long fkCreationClass);

    void setIsUnique(Boolean isUnique);

    Boolean getShowInSearch();

    void setShowInSearch(Boolean showInSearch);

    Boolean getShowInGrid();

    void setShowInGrid(Boolean showInGrid);

    Boolean getShowOnlyOnLocal();

    void setShowOnlyOnLocal(Boolean showOnlyOnLocal);

    String getPossibleValues();

    void setPossibleValues(String possibleValues);

}