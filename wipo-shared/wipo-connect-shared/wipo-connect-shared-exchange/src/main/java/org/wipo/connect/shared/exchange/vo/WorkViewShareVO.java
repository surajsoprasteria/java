package org.wipo.connect.shared.exchange.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.common.dto.Identifiable;

public class WorkViewShareVO implements Serializable, Identifiable {

    private static final long serialVersionUID = 3894714968354849244L;

    private Long id;
    private String rightTypeCode;
    private BigDecimal shareValue;
    private List<WorkViewCmoVO> viewCmoVO;

    public String getRightTypeCode() {
	return rightTypeCode;
    }

    public void setRightTypeCode(String rightTypeCode) {
	this.rightTypeCode = rightTypeCode;
    }

    public BigDecimal getShareValue() {
	return shareValue;
    }

    public void setShareValue(BigDecimal shareValue) {
	this.shareValue = shareValue;
    }

    public List<WorkViewCmoVO> getViewCmoVO() {
	if (null == viewCmoVO) {
	    viewCmoVO = new ArrayList<WorkViewCmoVO>();
	}
	return viewCmoVO;
    }

    public void setViewCmoVO(List<WorkViewCmoVO> viewCmoVO) {
	this.viewCmoVO = viewCmoVO;
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
