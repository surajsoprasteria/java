<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>

<c:set var="idSfx" value="Index" />

<tiles:insertDefinition name="simpleHeaderTemplate">

	<tiles:putAttribute name="title">${msg['index.page-title']}</tiles:putAttribute>
	<tiles:putAttribute name="pageInfo" cascade="true">${msg['index.page-info']}</tiles:putAttribute>

    <tiles:putAttribute name="pageScript">
    	<script type="text/javascript">

    		$(document).ready(function(){

    		
    		});

    	</script>
    </tiles:putAttribute>

    <tiles:putAttribute name="body">
		<div class="container${idSfx} home-menu-container">
			
			<div class="sec-list">
				<div class="row">
					<div class="col-sm-3">
					</div>
					<div class="col-sm-3">
						<div class="sec login">
							<ul>
								<li class="sec-func single"><a href="${ctx}/mvc/common/home">${msg['functions.log-in']}</a></li>
							</ul>
						</div>
						<label>${msg['functions.login']}</label>
					</div>
					<div class="col-sm-3">
						<div class="sec web">
							<ul>
								<li class="sec-func single"><a href="${webSiteLink}">${msg['functions.open-web-site']}</a></li>
							</ul>
						</div>
						<label>${msg['functions.web']}</label>
					</div>
				</div>
			</div>
		
		</div>
    </tiles:putAttribute>
</tiles:insertDefinition>