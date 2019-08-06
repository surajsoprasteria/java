<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>
<%@ page import="org.wipo.connect.shared.exchange.enumerator.*" %>

<c:choose>
	<c:when test="${readOnlyMode}">
		<c:set var="idSfx" value="IPDetReadOnly" />
	</c:when>
	<c:otherwise>
		<c:set var="idSfx" value="IPDet" />
	</c:otherwise>
</c:choose>


<div class="panel panel-default general-panel-dynamic">
	<div class="panel-heading">
		<div class="float-left">
			<h3 class="panel-title">${msg['interestedPartyDetail.general-information-panel-title']} </h3>
		</div>
		<div class="clear"></div>
	</div>
	<div class="panel-body">
		<div class="form-row">
			<div class="form-group full">
				<div class="pull-right">
					<label>${msg['interestedPartyDetail.creation_date']}: <small><fmt:formatDate value="${vo.interestedParty.creationDate}"
							pattern="${msg['config.date-format']}" /></small>
					</label>
				</div>
			</div>
		</div>
		<div class="form-row">
			<div class="form-group small">
				<label for="ipib${idSfx}">${msg['interestedPartyDetail.ip-base-number-detail']}</label>
				<form:input path="interestedParty.mainId" id="ipib${idSfx}" class="form-control" readonly="true" />
			</div> 
			<div class="form-group small">
				<label for="ipType${idSfx}">${msg['interestedPartyDetail.interestedPartyType-panel-title']}</label>
				<form:select path="interestedParty.type" id="ipType${idSfx}" class="form-control">
					<c:forEach items="${interestedPartyTypeList}" var="item">
						<form:option value="${item}">${msg[item.msgCode]}</form:option>
					</c:forEach>
				</form:select>
			</div>
			<div class="form-group small">
				<label for="sex${idSfx}">${msg['interestedPartyDetail.gender']}</label>
				<form:select path="interestedParty.sex" id="sex${idSfx}" cssClass="form-control CV_GENDER">
					<form:option value=""></form:option>
					<form:options items="${genderList}"></form:options>
				</form:select>
			</div>
			<div class="form-group">
				<label>${msg['interestedPartyDetail.creation-classes-detail']}</label>
				<form:input path="interestedParty.creationClassCodeList" cssClass="form-control" readOnly="true"/>
			</div>
		</div>
		<div class="form-row">
			<div class="form-group small">
				<label class="typeL" for="birthDate${idSfx}">${msg['interestedPartyDetail.foundation-date']}</label> 
				<label class="typeN" for="birthDate${idSfx}">${msg['interestedPartyDetail.birth-date']}</label>
				<div class="input-group date">
					<form:input path="interestedParty.birthDate" id="birthDate${idSfx}" class="form-control CV_BIRTH_DATE" />
					<span class="input-group-addon btn btn-primary"><i class="glyphicon glyphicon-calendar"></i></span>
				</div>
			</div>
			<div class="form-group small">
				<label class="typeL" for="deathDate${idSfx}">${msg['interestedPartyDetail.dissolution-date']}</label>
				<label class="typeN" for="deathDate${idSfx}">${msg['interestedPartyDetail.death-date']}</label>
				<div class="input-group date">
					<form:input path="interestedParty.deathDate" id="deathDate${idSfx}" class="form-control CV_DEATH_DATE" />
					<span class="input-group-addon btn btn-primary"><i class="glyphicon glyphicon-calendar"></i></span>
				</div>
			</div>
			<div class="form-group small">
				<label for="birthPlace${idSfx}">${msg['interestedPartyDetail.birth-place']}</label>
				<form:input path="interestedParty.birthPlace" id="birthPlace${idSfx}" class="form-control CV_BIRTH_PLACE" />
			</div>
			<div class="form-group">
				<label class="typeL" for="countryOfBirth${idSfx}">${msg['interestedPartyDetail.foundation-country']}</label>
				<label class="typeN" for="countryOfBirth${idSfx}">${msg['interestedPartyDetail.birth-country']}</label>
				<form:select path="interestedParty.fkBirthCountry" id="countryOfBirth${idSfx}" class="form-control CV_BIRTH_COUNTRY">
					<form:option value=""></form:option>
					<form:options items="${territoryList}" itemLabel="name" itemValue="id"></form:options>
				</form:select>
			</div>
			<div class="form-group">
				<label class="typeL" for="countryOfCitizenship${idSfx}">${msg['interestedPartyDetail.corporate-citizenship']}</label>
				<label class="typeN" for="countryOfCitizenship${idSfx}">${msg['interestedPartyDetail.countryOfCitizenship']}</label>
				<form:select path="interestedParty.citizenshipIdList" id="countryOfCitizenship${idSfx}" class="form-control CV_CITIZENSHIP">
					<form:options items="${territoryList}" itemLabel="name" itemValue="id"></form:options>
				</form:select>
			</div>

		</div>
		<div class="form-row">
			<table id="tblName${idSfx}">
				<thead>
					<tr>
						<th></th>
						<th></th>
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
									<td>${index}</td>
									<td>${vo.interestedParty.nameList[index].id}</td>
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