<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>

<c:set var="idSfx" value="ParamManagDet" />

<div class="panel panel-default performers-config-panel">
	<div class="panel-heading">
		<h3 class="panel-title">${msg['paramManagment.performers-configuration']}</h3>
	</div>
	<div class="panel-body">
		<table id="tblPerformersConfig${idSfx}"></table>
	</div>
</div>