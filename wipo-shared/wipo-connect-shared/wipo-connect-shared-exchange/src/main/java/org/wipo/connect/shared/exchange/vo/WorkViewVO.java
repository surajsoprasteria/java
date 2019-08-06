package org.wipo.connect.shared.exchange.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.common.dto.Deletable;
import org.wipo.connect.common.dto.Identifiable;

/**
 * 
 * @author pasquale.minervini
 *
 */
public class WorkViewVO implements Serializable, Deletable, Identifiable {

    private static final long serialVersionUID = -1849313553031219239L;

    private Long id;
    private String territoryFormula;
    private Boolean execDelete = false;
    private List<WorkViewDetailVO> viewDetailVO;

    public String getTerritoryFormula() {
	return territoryFormula;
    }

    public void setTerritoryFormula(String territoryFormula) {
	this.territoryFormula = territoryFormula;
    }

    public List<WorkViewDetailVO> getViewDetailVO() {
	if (null == viewDetailVO) {
	    viewDetailVO = new ArrayList<WorkViewDetailVO>();
	}
	return viewDetailVO;
    }

    public void setViewDetailVO(List<WorkViewDetailVO> viewDetailVO) {
	this.viewDetailVO = viewDetailVO;
    }

    @Override
    public Boolean getExecDelete() {
	return execDelete;
    }

    @Override
    public void setExecDelete(Boolean execDelete) {
	this.execDelete = execDelete;
    }

    @Override
    public Long getId() {
	return id;
    }

    @Override
    public void setId(Long id) {
	this.id = id;
    }

    @Override
    public String toString() {
	return ReflectionToStringBuilder.toString(this);
    }
}