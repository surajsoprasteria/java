<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>

<!-- Navigation -->
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="navbar-first-row">
		<div class="container">
	        <!-- Brand and toggle get grouped for better mobile display -->
	        <div class="navbar-header">
	             <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse,#user-info-collapse">
	                <span class="sr-only">Toggle navigation</span>
	                <span class="icon-bar"></span>
	                <span class="icon-bar"></span>
	                <span class="icon-bar"></span>
	            </button>
	            
            	<c:set var="homeUrl" value="${ctx}" />
            	<sec:authorize url="${ctxMvc}/common/home">
					<c:set var="homeUrl" value="${ctxMvc}/common/home" />
				</sec:authorize>
				
	            <p class="navbar-text brand">
		            <a href="${homeUrl}">
			            <img alt="wipo-wcc" class="logo-brand" src="${ctx}/res/img/shuffle.png" />
			        </a>
	            	<a href="${homeUrl}">${msg['global.application-name']}</a>
	            </p>
	            <div class="clear"></div>
	        </div>
	        <div class="collapse navbar-collapse" id="user-info-collapse">
	        	<ul class="nav navbar-nav navbar-right">
	        		<tiles:insertAttribute name="topRightMenu" />
	        		<li>
						<a href="${ctxMvc}/terms/terms-and-conditions.html" target="_blank">
							${msg['global.terms-and-conditions']}
						</a>
					</li>
	        	</ul>
	        </div>
			<div class="clear"></div>
	    </div>
	</div>
	<div class="navbar-second-row">
		<div class="container">
			<div class="collapse navbar-collapse" id="navbar-collapse">
				<p class="navbar-text page-info"><tiles:insertAttribute name="pageInfo" /></p>
	        	<tiles:insertAttribute name="mainMenu" />
	        </div>
		</div>
	</div>
    
</nav>