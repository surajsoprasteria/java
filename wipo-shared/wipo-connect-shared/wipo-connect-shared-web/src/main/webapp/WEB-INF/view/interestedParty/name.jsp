<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="org.wipo.connect.shared.exchange.enumerator.*"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>

<c:choose>
	<c:when test="${readOnlyMode}">
		<c:set var="idSfx" value="IPDetReadOnly" />
	</c:when>
	<c:otherwise>
		<c:set var="idSfx" value="IPDet" />
	</c:otherwise>
</c:choose>


<div class="panel panel-default name-panel-dynamic">
	<div class="panel-heading">
		<h3 class="panel-title">${vo.interestedParty.paName} ${vo.interestedParty.paFirstName}</h3>
	</div>
	<div class="panel-body">
	<div class="form-row">
		 <div class="form-group">
				<label for="ipib${idSfx}">${msg['interestedPartyDetail.ip-base-number-detail']}</label>
				<form:input path="interestedParty.mainId" id="ipib${idSfx}" class="form-control" readonly="true" />
		</div> 
		<div class="form-group">
			<label>${msg['interestedPartyDetail.affiliations']}</label>
			<c:if test="${vo.interestedParty.doAffiliation==false}">
				<c:set var="cmoDesc" value="<%= null %>" />
			</c:if>
			<input type="text" name="affiliations" id="affiliations${idSfx}" class="form-control" readonly="readonly" value="${cmoDesc}" />
			<form:hidden path="interestedParty.isAffiliated" />
			<form:hidden path="interestedParty.doAffiliation" />
			<%-- <form:hidden path="interestedParty.fkAffiliationTerritory" /> --%>
			<%-- <form:hidden path="interestedParty.affiliationStartDate" /> --%>

		</div>
		<div class="form-group">
			<c:choose>
				<c:when test="${vo.interestedParty.id == null}">
					<button type="button" disabled="disabled" class="btn btn-primary btn-show-affiliation no-block">${msg['interestedPartyDetail.show-affiliation']}</button>
				</c:when>
				<c:otherwise>
					<button type="button" class="btn btn-primary btn-show-affiliation no-block">${msg['interestedPartyDetail.show-affiliation']}</button>
				</c:otherwise>
			</c:choose>
		</div>
		</div>
		<div class="form-row">
			<table id="tblName${idSfx}">
				<thead>
					<tr>
						<th>${msg['interestedPartyDetail.name']}</th>
						<th>${msg['interestedPartyDetail.first-name']}</th>
						<th>${msg['interestedPartyDetail.name-type']}</th>
						<th>${msg['interestedPartyDetail.ip-name-number']}</th>
					</tr>	
				</thead>
				<tbody>
						<spring:eval expression="vo.interestedParty.nameList.size()" var="nameListSize" />
						<c:if test="${nameListSize > 0}">
							<c:forEach begin="0" end="${nameListSize-1}" var="index">
								<tr>
									<td>${vo.interestedParty.nameList[index].name}</td>
									<td>${vo.interestedParty.nameList[index].firstName}</td>
									<td>${vo.interestedParty.nameList[index].nameType}</td>
									<td>${vo.interestedParty.nameList[index].mainId}</td>
								</tr>
							</c:forEach>
						</c:if>
				</tbody>
			</table>
		</div>
	</div>
</div>
