<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>

<c:set var="idSfx" value="RefOther" />


<div class="template${idSfx}">
	
	<div class="templateReferenceValue hidden">
		<div class="form-row row-dynamic hidden">
			<div class="form-row">
				<input type="text" name="referenceValueList[%INDEX%].id"  id="referenceValueList%INDEX%.id" />
				<input type="text" name="referenceValueList[%INDEX%].code"  id="referenceValueList%INDEX%.code" />
				<input type="text" name="referenceValueList[%INDEX%].fkName"  id="referenceValueList%INDEX%.fkName" />
				<input type="text" name="referenceValueList[%INDEX%].name"  id="referenceValueList%INDEX%.name" />
			</div>
		</div>
	</div>
	
</div>