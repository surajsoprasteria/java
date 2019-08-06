	<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	<%@ include file="/WEB-INF/tags/tags.jspf"%>
	
	<c:set var="idSfx" value="RefRoles" />

		<div class="panel panel-default"> 
		
			<div class="panel-heading">
				<button type="button" class="btn btn-xs btn-primary add-ipi-role pull-right"><span class="glyphicon glyphicon-plus"></span></button>
				<h3 class="panel-title">${msg['roles.ipi-role-section']}</h3>
			</div>
			<div class="panel-body">
				 <table id="tblIpiRole${idSfx}"></table> 
			</div>
		</div>