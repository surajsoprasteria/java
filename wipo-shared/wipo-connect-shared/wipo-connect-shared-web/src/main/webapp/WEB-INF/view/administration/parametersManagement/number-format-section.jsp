<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>
<%@ page import="org.wipo.connect.shared.exchange.enumerator.*"%>

	
<c:set var="idSfx" value="AdmNumberFormat" />
<c:set var="FT_STRING" value="<%= FieldTypeEnum.STRING %>"/>
<c:set var="FT_INTEGER" value="<%= FieldTypeEnum.INTEGER %>"/>
<c:set var="FT_DECIMAL" value="<%= FieldTypeEnum.DECIMAL %>"/>
<c:set var="FT_DATE" value="<%= FieldTypeEnum.DATE %>"/>
<c:set var="FT_LIST" value="<%= FieldTypeEnum.LIST %>"/>
<c:set var="FT_FILE" value="<%= FieldTypeEnum.FILE %>"/>
<tiles:insertDefinition name="contentTemplate">

	<tiles:putAttribute name="pageScript">
		<script type="text/javascript">
			
		$(document).ready(function(){
			$(".container${idSfx} .input-group.date").datepicker();
    		$(".container${idSfx} select.form-control").selectpicker();

    		jQuery.validator.addMethod("validSeparatorChar", function(value, element) { 
				return value == "" || (!!value && value.length == 1 && (new RegExp("[^\\w\\+\\-\\&\\<\\>]|\\_")).test(value));      		     
    		}, "${msg['jsPlugin.validation.pattern']}");
  		  
			$(".container${idSfx} form").validate({rules:{
				"decimalSeparator" : {required:true, maxlength:1, validSeparatorChar:true},
				"groupingSeparator" : {maxlength:1, notequal:"#decimalSeparator${idSfx}", validSeparatorChar:true},
				"currencyPosition" : {required:true},
				"currencySymbol" : {required:true, maxlength:3}
			}});

				initAjaxForm(".container${idSfx} form" ,
                 		{	
             			success:function(data,status,xhr,formObj){
             				if (data !== Object(data) || (data === Object(data) && data.type === "ERROR")) {
                				showErrorMessage({message:"${msg['global.result-ko']}"});
                			}else{
                				showSuccessMessage({message:"${msg['global.result-ok']}"});
                    		}
                 		}
         		});

				$(".container${idSfx} #btnSaveNumberFormat${idSfx}").click(function(){
					$(".container${idSfx} form").submit();
				});
        		
    		});
        	 	
    	</script>
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<div class="container${idSfx}">
			<form:form id="formTerritoryWork${idSfx}" cssClass="form-inline" modelAttribute="numberFormatVO" action="${ctx}/mvc/administration/parametersManagement/saveNumberFormat.json" method="POST" enctype="multipart/form-data">
				<div class="panel panel-default territories-operation-panel">
				<div class="panel-heading">
						<h3 class="panel-title">${msg['paramManagment.number-format-panel']}</h3>
					</div>
					<div class="panel-body">
						<div class="form-row">
							<div class="form-group">
								<label>${msg['paramManagment.number-format-decimal-sep']}</label>
								<form:input path="decimalSeparator" id="decimalSeparator${idSfx}" cssClass="form-control" />
							</div>
							<div class="form-group">
								<label>${msg['paramManagment.number-format-grouping-sep']}</label>
								<form:input path="groupingSeparator" id="groupingSeparator${idSfx}" cssClass="form-control" />
							</div>
						</div>	
						<div class="form-row">
							<div class="form-group">
								<label>${msg['paramManagment.number-format-currency-symbol']}</label>
								<form:input path="currencySymbol" id="currencySymbol${idSfx}" cssClass="form-control" />
							</div>
							<div class="form-group">
								<label>${msg['paramManagment.number-format-currency-position']}</label>
								<form:select path="currencyPosition" id="currencyPosition${idSfx}" cssClass="form-control">
									<form:option value=""></form:option>
									<c:forEach items="${currencyPositionList}" var="item">
										<form:option value="${item.code}">${msg[item.msgCode]}</form:option>
									</c:forEach>
								</form:select>
							</div>
							<div class="form-group auto pull-right">
								<label>&nbsp;</label>
								<button type="button" id="btnSaveNumberFormat${idSfx}" class="btn btn-primary">${msg['global.save']}</button> 
							</div>
						</div>	
			  		</div>
		  		</div>
		</form:form>
	</div>	
	</tiles:putAttribute>
</tiles:insertDefinition>