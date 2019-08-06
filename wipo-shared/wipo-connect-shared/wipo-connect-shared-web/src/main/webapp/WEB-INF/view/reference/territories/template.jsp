<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>

<c:set var="idSfx" value="RefTerritory" />


<div class="template${idSfx}">
	
	<div class="templateTerritoryName hidden">
		<div class="form-row row-dynamic hidden">
			<div class="form-row">
				<input type="text" name="territoryNameList[%INDEX%].id"  id="territoryNameList%INDEX%.id" />
				<input type="text" name="territoryNameList[%INDEX%].tisa"  id="territoryNameList%INDEX%.tisa" />
				<input type="text" name="territoryNameList[%INDEX%].name"  id="territoryNameList%INDEX%.name" />
				<input type="text" name="territoryNameList[%INDEX%].fkName"  id="territoryNameList%INDEX%.fkName" />
				<input type="text" name="territoryNameList[%INDEX%].startDate"  id="territoryNameList%INDEX%.startDate" />
				<input type="text" name="territoryNameList[%INDEX%].endDate"  id="territoryNameList%INDEX%.endDate" />
			</div>
		</div>
	</div>
	
</div>