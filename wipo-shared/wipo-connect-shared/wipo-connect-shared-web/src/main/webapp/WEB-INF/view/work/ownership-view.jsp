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

<div class="main-contextual-panel">
	<div class="panel panel-default ownership-view-panel">
		<div class="panel-heading">
			<h3 class="panel-title">${msg['work.ownership-view-section']}</h3>
		</div>
		<div class="panel-body x-small-margin">
			<div class="form-row">
				<form:hidden path="workViewList[0].id" />
				<div class="form-group">
					<label for="territoryFormula${idSfx}">${msg['work.ownership-view-territory']}</label>
					<div class="input-group">
						<form:input path="workViewList[0].territoryFormula" id="territoryFormula${idSfx}" cssClass="form-control CV_TERRITORY_FORMULA_VALUE" />
					</div>
				</div>
			</div>
			<div class="form-row">
			<table id="tblOwnership${idSfx}">
			<thead>
				<tr>
					<th>${msg['work.col-id']}</th>
					<th>${msg['work.ip-name']}</th>
					<th>${msg['work.ip-number']}</th>
					<th>${msg['work.cwr-role']}</th>
					<th>${msg['work.assignor']}</th>
					<c:forEach items="${rightTypeList}" var="rightcategory">
						<th>${rightcategory.code} ${msg['work.cmo']}</th>
					</c:forEach>
				</tr>
			</thead>
			<tbody>
			<spring:eval expression="vo.workViewList.get(0).viewDetailVO.size()" var="shareListSize" />
			<c:if test="${shareListSize > 0}">
				<c:forEach begin="0" end="${shareListSize - 1}" var="shareIndex">
						<tr>
							<td>${shareIndex+1}</td>
							<td>${vo.workViewList[0].viewDetailVO[shareIndex].fullName}</td>
							<td>${vo.workViewList[0].viewDetailVO[shareIndex].nameMainId}</td>
							<td>${roleMap.get(vo.workViewList[0].viewDetailVO[shareIndex].fkRole).code}</td>
							<td>${vo.workViewList[0].viewDetailVO[shareIndex].creatorRefMainId}</td>
							
							<c:forEach items="${rightTypeList}" var="rightcategory">
								<c:choose>
									<c:when test="${vo.workViewList[0].viewDetailVO[shareIndex].viewShareVOMap[rightcategory.code]!=null}">
										<spring:eval expression="vo.workViewList[0].viewDetailVO[shareIndex].viewShareVOMap[rightcategory.code].viewCmoVO.size()" var="cmoListSize" />
										<td>
									 		<c:choose>																
												<c:when test="${cmoListSize == 1}"><c:set var="cmoAcronym" value="${vo.workViewList[0].viewDetailVO[shareIndex].viewShareVOMap[rightcategory.code].viewCmoVO[0].acronym}" /></c:when>
												<c:when test="${cmoListSize > 1}"><c:set var="cmoAcronym" value="${msg['interestedPartyAffiliation.multiple']}" /></c:when>
												<c:otherwise><c:set var="cmoAcronym" value="" /></c:otherwise> 
											</c:choose>
											<div class="col-sm-6 cmo-side"><c:out value="${cmoAcronym}" /></div>
											<div class="col-sm-6 share-side">${csf:format(vo.workViewList[0].viewDetailVO[shareIndex].viewShareVOMap[rightcategory.code].shareValue)}</div>
										</td>
									</c:when>
									
									<c:otherwise>
										<td></td>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						
						</tr>
				</c:forEach>
			</c:if>
			</tbody>
		</table>
		</div>
		</div>
		
	</div>
</div>
