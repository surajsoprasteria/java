<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>

<c:set var="idSfx" value="LogIn" />

<tiles:insertDefinition name="simpleHeaderTemplate">

	<tiles:putAttribute name="title">${msg['login.page-title']}</tiles:putAttribute>
	<tiles:putAttribute name="pageInfo" cascade="true">${msg['login.page-title']}</tiles:putAttribute>

    <tiles:putAttribute name="pageScript">
    	<script type="text/javascript">

    		<c:if test="${!isBackendDown and configProperties.showDebugInfo}">
				console.info("Application version: ${applicationProperties['application.version']}.${applicationProperties['application.build']}");
			</c:if>
	    	
			if($(".main-body").size() > 1){ 
				redirectToLogin();
			}
    	
    		$(document).ready(function(){

    			$(".form-signin").validate();
    			
    		});

    	</script>
    </tiles:putAttribute>

    <tiles:putAttribute name="body">
    	<div class="container container-login">
			<input type="hidden" class="login-check" value=true />
			
			<form class="form-signin" action="${ctx}/mvc/common/executeAuthenticate" method="post">
				<h3 class="form-signin-heading">
					${msg['login.please-login']}
				</h3>
	
				<div class="row-email">
					<label for="inputEmail" class="sr-only">${msg['login.email']}</label>
					<input type="email" id="inputEmail" class="form-control" name="email" placeholder="${msg['login.email']}" required autofocus>
				</div>
				<div class="row-password">
					<label for="inputPassword" class="sr-only">${msg['login.password']}</label>
					<input type="password" id="inputPassword" class="form-control" name="password" placeholder="${msg['login.password']}" required>
				</div>
				<div class="row-error">
					<c:if test="${loginError}">
						<label class="text-danger">${msg['login.invalid-credentials']}</label>
					</c:if>
					<c:if test="${userDisabled}">
						<label class="text-danger">${msg['login.user-disabled']}</label>
					</c:if>
				</div>
				<div class="row-error">
					<c:if test="${isBackendDown}">
						<label class="text-danger">Shared Back-End is not running. Please contact your administrator</label>
					</c:if>
				</div>
	
				<div class="checkbox">
					<label> <input type="checkbox" name="remember-me"> ${msg['login.remember']}
					</label>
				</div>
				<button class="btn btn-lg btn-primary btn-block" type="submit">${msg['login.log-in']}</button>
			</form>
			
			<c:if test="${configProperties.showDebugInfo}">
				<div class="app-info">
					<div class="row">
					  <div class="title"><h3>Debug Info</h3></div>
					</div>
					<div class="row">
					  <div class="key">CMO Code</div>
					  <div class="value">${configProperties['cmo.code']}</div>
					</div>
					<div class="row">
					  <div class="key">Application Version</div>
					  <div class="value">${applicationProperties['application.version']}</div>
					</div>
					<div class="row">
					  <div class="key">Application Build</div>
					  <div class="value">${applicationProperties['application.build']}</div>
					</div>
					<div class="row">
					  <div class="key">OS Version</div>
					  <div class="value">${osVersion}</div>
					</div>
					<div class="row">
					  <div class="key">Java Version</div>
					  <div class="value">${javaVersion}</div>
					</div>
					<div class="row">
					  <div class="key">DB Username</div>
					  <div class="value">${dbInfo.username}</div>
					</div>
					<div class="row">
					  <div class="key">DB Catalog</div>
					  <div class="value">${dbInfo.catalog}</div>
					</div>
					<div class="row">
					  <div class="key">Datasource URL</div>
					  <div class="value">${dbInfo.url}</div>
					</div>
					<div class="row">
					  <div class="key">DB Name</div>
					  <div class="value">${dbInfo.productName}</div>
					</div>
					<div class="row">
					  <div class="key">DB Version</div>
					  <div class="value">${dbInfo.productVersion}</div>
					</div>
					<div class="row">
					  <div class="key">Driver Name</div>
					  <div class="value">${dbInfo.driverName}</div>
					</div>
					<div class="row">
					  <div class="key">Driver Version</div>
					  <div class="value">${dbInfo.driverVersion}</div>
					</div>
				</div>
			</c:if>
		</div>
		<!-- /container -->

    </tiles:putAttribute>
</tiles:insertDefinition>
