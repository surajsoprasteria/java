<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>
<%@ page import="org.wipo.connect.shared.exchange.enumerator.*"%>

<c:choose>
	<c:when test="${readOnlyMode}">
		<c:set var="idSfx" value="WrkDetReadOnly" />
	</c:when>
	<c:otherwise>
		<c:set var="idSfx" value="WrkDet" />
	</c:otherwise>
</c:choose>

<div class="panel panel-default general-info-panel">
	<div class="panel-heading">
		<h3 class="panel-title">${msg['work.general-information']}</h3>
	</div>
	<div class="panel-body">
		<div class="form-row">
			<div class="form-group">
				<label for="email${idSfx}">${msg['work.duration']}</label>
				<input name="work.duration" id="duration${idSfx}" type="text" class="form-control auto-time" value="${csf:secondsToHMs(vo.work.duration)}"/>
			</div>
			<div class="form-group">
				<label for="genreCode${idSfx}" >${msg['work.genre']}</label>
				<form:input path="work.genreCode" id="genreCode${idSfx}" cssClass="form-control CV_GENRE" />
			</div>
			<div class="form-group">
				<label for="language${idSfx}">${msg['work.language']}</label>
				<form:input path="work.language" id="language${idSfx}" cssClass="form-control CV_LANGUAGE" />
			</div>
			  <div class="form-group">
				<label for="instrumentCodeList${idSfx}">${msg['work.instrument-section']}</label>
				<form:select path="work.instrumentCodeList" id="instrumentCodeList${idSfx}" cssClass="form-control tag-input CV_INSTRUMENT">
					<form:options items="${vo.work.instrumentCodeList}" />
				</form:select>
			</div>   
		</div>						
	</div>						
</div>