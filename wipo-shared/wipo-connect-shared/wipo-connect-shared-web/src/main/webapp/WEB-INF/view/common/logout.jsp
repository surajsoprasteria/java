<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>

<c:set var="idSfx" value="LogOut" />

<tiles:insertDefinition name="simpleHeaderTemplate">

	<tiles:putAttribute name="title">${msg['logout.page-title']}</tiles:putAttribute>
	<tiles:putAttribute name="pageInfo" cascade="true">${msg['logout.page-title']}</tiles:putAttribute>

    <tiles:putAttribute name="pageScript">
    	<script type="text/javascript">

    		$(document).ready(function(){
    			
    		});

    	</script>
    </tiles:putAttribute>

    <tiles:putAttribute name="body">
    	<div class="container${idSfx}">
	
			<h3 class="text-center">${msg['logout.success']}</h3>
			
			<p class="text-center">
				<a href="${ctx}/mvc/common/login" class="btn btn-link">${msg['logout.again']}</a>
			</p>
			
		</div>
    </tiles:putAttribute>
</tiles:insertDefinition>

