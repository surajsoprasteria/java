<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>
<%@ page import="org.wipo.connect.shared.exchange.enumerator.*"%>

<c:choose>
	<c:when test="${readOnlyMode}">
		<c:set var="idSfx" value="RefTerritoryDetReadOnly" />
	</c:when>
	<c:otherwise>
		<c:set var="idSfx" value="RefTerritoryDet" />
	</c:otherwise>
</c:choose>

<div class="panel panel-default general-info-panel">
		
		<div class="panel-heading">
			<h3 class="panel-title">${msg['reference.territories']}</h3>
		</div>
		
		<div class="panel-body">
			
			<div class="form-row">
					<div class="form-group auto">
						<label for="tisnCode${idSfx}">${msg['territory.tisn-code']}</label>
						<form:input path="code" id="code${idSfx}" cssClass="form-control" readonly="${!insertMode}" />
					</div>
					
					 <div class="form-group">
						<label for="fkSourceType${idSfx}" >${msg['territory.type']}</label>
						<form:select path="fkType" id="fkType${idSfx}" cssClass="form-control" readonly="${!insertMode}" >
							<form:option value=""></form:option>
							<form:options items="${territoryTypeList}" itemValue="id" itemLabel="value" />
						</form:select>
					</div> 
					
					<div class="form-group">
						<label for="startDate${idSfx}">${msg['territory.col-start-date']}</label>
						<div class="input-group date">
							<form:input path="startDate" id="startDate${idSfx}" cssClass="form-control" />
							<span class="input-group-addon btn btn-primary"><i class="glyphicon glyphicon-calendar"></i></span>
						</div>
					</div>
					
					<div class="form-group">
						<label for="endDate${idSfx}">${msg['territory.col-end-date']}</label>
						<div class="input-group date">
							<form:input path="endDate" id="endDate${idSfx}" cssClass="form-control" />
							<span class="input-group-addon btn btn-primary"><i class="glyphicon glyphicon-calendar"></i></span>
						</div>
					</div>
			</div>
			
		</div>						
</div>