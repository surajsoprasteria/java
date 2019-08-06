<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>

<c:choose>
	<c:when test="${readOnlyMode}">
		<c:set var="idSfx" value="WrkDetReadOnly" />
	</c:when>
	<c:otherwise>
		<c:set var="idSfx" value="WrkDet" />
	</c:otherwise>
</c:choose>

<div class="template${idSfx}">
		<div class="templateRO${idSfx} hidden">
			<div class="form-row ro-row">
				<input type="hidden" class="index" value="%INDEX%"/>
				<input type="hidden" name="rightOwnerList[%INDEX%].nameMainId" class="nameMainId"/>
				<input type="hidden" name="rightOwnerList[%INDEX%].execDelete" class="ro-deleted" />
				<div class="form-group small">
					<div class="input-group">
						<input type="text" name="rightOwnerList[%INDEX%].rightOwnerValue" class="form-control CV_RO_VALUE rightOwnerValue" />
						<span class="input-group-addon btn btn-primary ro-add"><i class="glyphicon glyphicon-search"></i></span>
					</div>
				</div>
				<div class="form-group">
					<button type="button" class="btn btn-xs btn-default ro-remove"><span class="glyphicon glyphicon-minus"></span></button>
				</div>
			</div>
		</div>
		
</div>