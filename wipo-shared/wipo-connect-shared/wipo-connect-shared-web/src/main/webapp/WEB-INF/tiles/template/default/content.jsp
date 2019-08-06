<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>

<script type="text/javascript">
	$(document).ready(function(){
		$(".content-body.body-${currentMillis} input").attr("autocomplete","off");
		$(".content-body.body-${currentMillis}").collapsiblePanel();

		var dateFormat = $.fn.datepicker.dates[$.fn.datepicker.defaults.language].format;
		$(".content-body.body-${currentMillis} .input-group.date input[type=text]").each(function(){
			if(!$(this).attr("placeholder")){
				$(this).attr("placeholder",dateFormat);
				var formatMask = dateFormat.replace(/dd|mm|yy/gi,"00");
				$(this).mask(formatMask,{placeholder:dateFormat});
			}
		});

		applyAutoNumeric();

	});
</script>

<tiles:insertAttribute name="pageScript" />

<div class="content-body body-${currentMillis}">
	<tiles:insertAttribute name="body" />
</div>