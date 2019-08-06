<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>

<c:set var="idSfx" value="searchGroup" />

	<div class="panel panel-default panel-class"> 
		<div class="panel-heading">
			<c:if test="${canRender}">
				<button type="button" class="btn btn-xs btn-primary add-group pull-right"><span class="glyphicon glyphicon-plus"></span></button>
			</c:if>
			<h3 class="panel-title">${msg['groupManagement.group-section']}</h3>
		</div>
		<div class="panel-body">
			 <table id="tblGroupManagement${idSfx}"></table> 
		</div>
	</div>