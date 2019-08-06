
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>
<%@ page import="org.wipo.connect.shared.exchange.enumerator.*"%>

<c:choose>
	<c:when test="${readOnlyMode}">
		<c:set var="idSfx" value="WrkDetReadOnly" />
	</c:when>
	<c:otherwise>
		<c:set var="idSfx" value="WrkDet" />
	</c:otherwise>
</c:choose>

<c:set var="L" value="<%=TypeEnum.L.name()%>" />
<c:set var="N" value="<%=TypeEnum.N.name()%>" />
<c:set var="B" value="<%=RoleTypeEnum.B.name()%>" />

<div class="main-contextual-panel">
	<div class="panel panel-default ownership-view-panel">
		<div class="panel-heading">
			
		</div>
		<div class="panel-body x-small-margin">
			
			<div class="form-row">
				<form:hidden path="workViewList[0].id" />
				<div class="form-group">
					<label for="territoryFormula${idSfx}">${msg['work.ownership-view-territory']}</label>
					<div class="input-group">
						<form:input path="workViewList[0].territoryFormula" id="territoryFormula${idSfx}" cssClass="form-control CV_TERRITORY_FORMULA_VALUE" />
						<span id="btnTerritorySearch${idSfx}" class="input-group-addon btn btn-primary"> <i class="glyphicon glyphicon-search"></i>
						</span>
					</div>
				</div>
				<div class="form-group">
					<button type="button" class="btn btn-primary btn-add-dv-name">${msg['work.add-right-owner']}</button>
				</div>
			</div>
			
			<div class="form-row">
				<div class="form-group" style="width: 15px;"></div>
				<div class="form-group work-view-name">
					<label for="">${msg['work.ip-name']}</label>
				</div>
				<div class="form-group work-view-ipin">
					<label for="">${msg['work.ip-number']}</label>
				</div>
				<div class="form-group work-view-role">
					<label for="">${msg['work.cwr-role']}</label>
				</div>
				<div class="form-group work-view-cot">
					<label for="">${msg['work.assignor']}</label>
				</div>
				<div class="form-group auto share-row-label">
					<c:choose>
						<c:when test="${vo.workViewList[0].viewDetailVO.size()<1 && vo.work.id != null}">
							<c:forEach items="${rightTypeList}" var="rightcategory">
							    <div class="form-group work-view-cmo">
									<label for="">${rightcategory.code} CMO</label>
								</div>
								<div class="form-group work-view-share">
									<label for="">${rightcategory.code}</label>
								</div>
							</c:forEach>
					  	</c:when>
					  	<c:otherwise>
					  		<c:forEach items="${rightTypeList}" var="rightcategory">
	  							<c:if test="${vo.workViewList[0].viewDetailVO[0].viewShareVOMap[rightcategory.code]!=null}">
	  								<div class="form-group work-view-cmo">
										<label for="">${vo.workViewList[0].viewDetailVO[0].viewShareVOMap[rightcategory.code].rightTypeCode} CMO</label>
									</div>
									<div class="form-group work-view-share">
										<label for="">${vo.workViewList[0].viewDetailVO[0].viewShareVOMap[rightcategory.code].rightTypeCode}</label>
									</div>
								</c:if>
							</c:forEach>
						</c:otherwise>
				  	</c:choose>
				</div>
				
				<div class="form-group work-view-source">
					<label for="">${msg['work.view-source-type']}</label>
				</div>
			</div>
			
			<spring:eval expression="vo.workViewList.get(0).viewDetailVO.size()" var="viewDetailListSize" />
			<c:if test="${viewDetailListSize > 0}">
				<c:forEach begin="0" end="${viewDetailListSize - 1}" var="detailIndex">
					<div class="form-row share-row">
						<form:hidden path="workViewList[0].viewDetailVO[${detailIndex}].id" id="idWorkViewDetail${idSfx}" cssClass="dv-name-id" />
						<form:hidden path="workViewList[0].viewDetailVO[${detailIndex}].idName" id="idName${idSfx}" cssClass="name-id" />
						<form:hidden path="workViewList[0].viewDetailVO[${detailIndex}].refIndex" cssClass="ref-index" />
						<form:hidden path="workViewList[0].viewDetailVO[${detailIndex}].execDelete" cssClass="dv-name-deleted" /> 
						<form:hidden path="workViewList[0].viewDetailVO[${detailIndex}].interestedPartyType" cssClass="ro-type" />
						<c:set var="currentIdName" value="${vo.workViewList[0].viewDetailVO[detailIndex].idName}"></c:set>

						<div class="form-group auto work-view-index" style="width: 15px;">
							<label>${detailIndex+1}</label>
						</div>
						<div class="form-group work-view-name">
							<form:input path="workViewList[0].viewDetailVO[${detailIndex}].fullName" cssClass="ip-name form-control" readonly="true" />
						</div>

						<div class="form-group work-view-ipin">
							<form:input path="workViewList[0].viewDetailVO[${detailIndex}].nameMainId" cssClass="ip-name-number form-control" readonly="true" />
						</div>

						<div class="form-group work-view-role">
							<form:select path="workViewList[0].viewDetailVO[${detailIndex}].fkRole" id="roleSelection" cssClass="form-control role CV_VIEW_ROLE">
								<form:option value=""></form:option>
								<c:forEach items="${roleMap}" var="item">
									<c:if test="${item.value.type == null or vo.workViewList[0].viewDetailVO[detailIndex].interestedPartyType == null or item.value.type==B or item.value.type == vo.workViewList[0].viewDetailVO[detailIndex].interestedPartyType}">
										<form:option value="${item.value.id}" role-type="${item.value.type}">${item.value.code}</form:option>
									</c:if>
								</c:forEach>
							</form:select>
						</div>

						<div class="form-group work-view-cot">
							<c:set var="oldVal" value="${vo.workViewList[0].viewDetailVO[detailIndex].creatorRefIndex}" />
							<form:select path="workViewList[0].viewDetailVO[${detailIndex}].creatorRefIndex" cssClass="form-control creator-ref-index CV_VIEW_CREATOR" data-old-val="${oldVal}">
								<form:option value=""></form:option>
								<c:forEach items="${vo.workViewList[0].viewDetailVO}" var="creatorItem">
									<c:if test="${creatorItem.idName != currentIdName}">
										<form:option value="${creatorItem.refIndex}">${creatorItem.refIndex}</form:option>
									</c:if>
								</c:forEach>
							</form:select>
						</div>

						<c:forEach items="${rightTypeList}" var="rightcategory">
							<form:hidden path="workViewList[0].viewDetailVO[${detailIndex}].viewShareVOMap[${rightcategory.code}].id"/>
							
							<div class="form-group work-view-cmo">
								<spring:eval expression="vo.workViewList[0].viewDetailVO[detailIndex].viewShareVOMap[rightcategory.code].viewCmoVO.size()" var="cmoListSize" />
								<c:choose>
									<c:when test="${cmoListSize == 1}">
										<c:set var="cmoAcronym" value="${vo.workViewList[0].viewDetailVO[detailIndex].viewShareVOMap[rightcategory.code].viewCmoVO[0].acronym}" />
									</c:when>
									<c:when test="${cmoListSize > 1}">
										<c:set var="cmoAcronym" value="${msg['interestedPartyAffiliation.multiple']}" />
									</c:when>
									<c:otherwise>
										<c:set var="cmoAcronym" value="" />
									</c:otherwise>
								</c:choose>
								<input type="text" id="cmoPerformingShare${idSfx}" readonly="readonly" class="form-control cmo-share${rightcategory.code}" right-type-code="${rightcategory.code}" value="${cmoAcronym}" />
							</div> 
							<div class="form-group work-view-share">
								<form:input path="workViewList[0].viewDetailVO[${detailIndex}].viewShareVOMap[${rightcategory.code}].shareValue"  cssClass="form-control performing-share auto-decimal CV_VIEW_SHARE_VALUE"/>
								<form:hidden path="workViewList[0].viewDetailVO[${detailIndex}].viewShareVOMap[${rightcategory.code}].rightTypeCode" />
							</div>
							
						</c:forEach>
						
						<div class="form-group work-view-source">
							<form:input path="workViewList[0].viewDetailVO[${detailIndex}].sourceType" readonly="true" cssClass="form-control identifier-code" />
						</div>
						<div class="form-group auto">
							<button type="button" class="btn btn-xs btn-default dv-name-remove">
								<span class="glyphicon glyphicon-minus"></span>
							</button>
						</div>
						
					</div>
				</c:forEach>
			</c:if>
		</div>
	</div>

</div>
