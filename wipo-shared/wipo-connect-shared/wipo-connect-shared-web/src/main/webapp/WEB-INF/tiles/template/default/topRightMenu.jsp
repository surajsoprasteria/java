<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>


	<li>
		<a href="${ctx}/mvc/common/home">
			<span class="header-menu-ico home" title="${msg['functions.home']}"></span>
			<span class="header-menu-desc">${msg['functions.home']}</span>
		</a>
	</li>
	<li class="dropdown">
		<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><span class="glyphicon glyphicon-question-sign"></span></a>
		<ul class="dropdown-menu help-menu" role="menu">
			
			<li ><a href="#" id="btnUserManual">${msg['global.help-user-manual']}</a></li>
			<li ><a href="${ctx}/mvc/common/goToHelpUrl" target="_blank">${msg['global.help-url']}</a></li>
		</ul>
	</li>
	<li class="dropdown">
		<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">${securityUtils.loggedUsername}<span class="caret"></span></a>
		<ul class="dropdown-menu logout-btn" role="menu">
			<li><a href="${ctx}/mvc/common/executeLogout">${msg['global.logout']}</a></li>
		</ul>
	</li>

	
	