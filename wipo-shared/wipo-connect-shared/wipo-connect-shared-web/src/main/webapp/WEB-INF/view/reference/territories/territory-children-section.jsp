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

		<div class="panel panel-default"> 
			<div class="panel-heading">
				<h3 class="panel-title">${msg['territoryChildren.section']}</h3>
			</div>
			<div class="panel-body">
				 <table id="tblTerritoryChildren${idSfx}"></table> 
			</div>
		</div>