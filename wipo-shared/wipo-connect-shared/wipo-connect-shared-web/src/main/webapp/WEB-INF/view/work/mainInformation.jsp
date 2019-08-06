
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

<c:set var="WST_IMPORT" value="<%= WorkSourceTypeEnum.WRK_SRC_IMPORT.name() %>" />


<div class="panel panel-default title-panel">
	<div class="panel-heading">
		<h3 class="panel-title pull-left">${msg['work.main-info']}</h3>
		<div class="clear"></div>
	</div>

	<div class="panel-body">
		<div class="form-row">
			<div class="form-group auto pull-right">
				<label>${msg['work.registration-date']}: <small><fmt:formatDate value="${vo.work.registrationDate}" pattern="${msg['config.date-format']}" /></small>
				</label>
			</div>
			<div class="clear"></div>
		</div>
		
		<div class="form-row">
			<div class="form-group">
				<label for="title${idSfx}">${msg['work.creation-classes']}</label>
				<form:hidden path="work.fkCreationClass"/>
				<form:select path="work.fkCreationClass" cssClass="form-control CV_CREATION_CLASS" disabled="true">
					<form:option value=""></form:option>
					<form:options items="${managedCreationClassList}" itemLabel="code" itemValue="id"></form:options>
				</form:select>
			</div>
			<div class="form-group">
				<label for="">${msg['work.category']}</label>
				<form:input path="work.categoryCode" id="categoryCode${idSfx}" cssClass="form-control CV_FK_CATEGORY" />
			</div>
			<div class="form-group small">
				<label for="">${msg['work.source-type']}</label>
				<c:choose>
					<c:when test="${sourceTypeMap[vo.work.fkSourceType].code == WST_IMPORT}">
						<c:set var="stringOut" value="(${vo.importCode})" />
					</c:when>
					<c:otherwise>
						<c:set var="stringOut" value="" />
					</c:otherwise>
				</c:choose>
					
				<input type="text" class="form-control" value="${sourceTypeMap[vo.work.fkSourceType].value} ${stringOut}" readonly>
				<form:hidden path="work.fkSourceType"/>
			</div>
		</div>

		<div class="form-row">
				<div class="form-group">
					<label for="catalogueNumber${idSfx}">${msg['work.catalogue-number']}</label>
					<form:input path="work.catalogueNumber" id="catalogueNumber${idSfx}" cssClass="form-control" />
				</div>
				<div class="form-group">
					<label for="support${idSfx}">${msg['work.support']}</label>
					<form:input path="work.support" id="support${idSfx}" cssClass="form-control" />
				</div>
				<div class="form-group">
					<label for="label${idSfx}">${msg['work.label']}</label>
					<form:input path="work.label" id="label${idSfx}" cssClass="form-control" />
				</div>
				<div class="form-group">
					<label for="">${msg['work.country-production']}</label>
					<form:select path="work.fkCountryOfProduction" cssClass="form-control">
						<form:option value=""></form:option>
						<form:options items="${countryOfProductionList}" itemLabel="name" itemValue="id"></form:options>
                    </form:select>
				</div>
			</div>	

		<div class="form-row">
			<div class="form-group half " style="float: left;">
				<table id="tblTitle${idSfx}">
					<thead>
						<tr>
							<th>${msg['work.title']}</th>
							<th>${msg['work.title-type']}</th>
						</tr>
					</thead>
					<tbody>
						<spring:eval expression="vo.work.titleList.size()"
							var="titleListSize" />
						<c:if test="${titleListSize>0}">
							<c:forEach begin="0" end="${titleListSize - 1}" var="i">
								<tr>
									<td>${vo.work.titleList[i].description}</td>
									<td>${vo.work.titleList[i].typeCode}</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
			
			<div class="form-group half" style="float: right;">
				<table id="tblWorkDate${idSfx}">
					<thead>
						<tr>
							<th>${msg['work.date-type']}</th>
							<th>${msg['work.date-value']}</th>
						</tr>
					</thead>
					<tbody>
						<spring:eval expression="vo.work.workDateList.size()"
							var="workDateListSize" />
						<c:if test="${workDateListSize>0}">
							<c:forEach begin="0" end="${workDateListSize - 1}" var="i">
								<tr>
									<td>${vo.work.workDateList[i].dateTypeCode}</td>
									<td><fmt:formatDate value="${vo.work.workDateList[i].dateValue}" pattern="${msg['config.date-format']}"/></td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
		
		</div>
		
		
	</div>
</div>