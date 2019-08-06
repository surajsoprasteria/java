	<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	<%@ include file="/WEB-INF/tags/tags.jspf"%>
	<%@ page import="org.wipo.connect.shared.exchange.enumerator.*"%>
	
	<c:set var="idSfx" value="RefRoles" />

		<div class="panel panel-default default-affiliation-panel"> 
			<div class="panel-heading">
				<button type="button" class="btn btn-xs btn-primary add-work-role pull-right"><span class="glyphicon glyphicon-plus"></span></button>
				<h3 class="panel-title">${msg['roles.work-role']}</h3>
			</div>
			<div class="panel-body">
				 <table id="tblRole${idSfx}"></table> 
			</div>
		</div>