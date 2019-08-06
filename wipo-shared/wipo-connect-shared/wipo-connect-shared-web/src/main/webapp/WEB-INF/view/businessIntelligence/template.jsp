<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>
<%@ page import="org.wipo.connect.shared.exchange.enumerator.*"%>

<c:set var="idSfx" value="BISrc" />

<div class="template${idSfx} hidden">

	<div class="templateQueryParam${idSfx} form-group INTEGER DECIMAL BOOLEAN STRING">
			<label></label>
			<input type="text" name="queryParameterObject[%KEY%].formValue" id="param-%KEY%-${idSfx}" class="form-control" />
	</div>
	
	<div class="form-group templateQueryParam${idSfx} DATE">
		<label></label>
		<div class="input-group date">
			<input type="text" name="queryParameterObject[%KEY%].formValue" id="param-%KEY%-${idSfx}" class="form-control">
			<span class="input-group-addon btn btn-primary"><i class="glyphicon glyphicon-calendar"></i></span>
		</div>
	</div>
	
</div>		

