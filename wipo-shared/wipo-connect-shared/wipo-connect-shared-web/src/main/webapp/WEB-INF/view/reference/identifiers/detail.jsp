<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>
<%@ page import="org.wipo.connect.shared.exchange.enumerator.*" %>

<c:set var="idSfx" value="RefIdentifiersDet" />

<tiles:insertDefinition name="contentTemplate">

	<tiles:putAttribute name="pageScript">
		<script type="text/javascript">
    		$(document).ready(function(){
        		
    			$(document).off(".${idSfx}");
				var thisDialog = getParentDialog(".container${idSfx}");
				var insertMode = "${insertMode}" === "true";

				//Init UI Plugins
				$(".container${idSfx} select.form-control").selectpicker();
            	$(".container${idSfx} .input-group.date").datepicker(); 
            	
            	
    			$.validator.addMethod("duplicateIdentifierCode", function(value, element) {
					if(insertMode) {
						var table = $('#tblResultsRefIdentifiers').dataTable();
	             		var tableData = table.api().rows().data();
             			for(var i = 0; i < tableData.length; ++i) {
             				var regex = new RegExp('^' + value + '$', 'i');
             				if (regex.test(tableData[i].code)) {
             					return false;
             				}
             			}
             			return true;
             		}
					return true;
        		}, "${msg['reference.duplicate-code']}");
    			
            	
				$(".container${idSfx} form").validate({rules:{
					"code" : {required:true /*, duplicateIdentifierCode:true*/},
					"name" : {required:true},
					"acronym" : {required:true},
					"linkedEntity" : {required:false}
				}});
				
				
             	$("#btnSave${idSfx}").click(function(e){
             		if (!$("#form${idSfx}").valid()){
    	    			return;
    	    		}  
             		
             		$("#form${idSfx}").data("validation-type", "LOCAL"); 
           	    	$("#form${idSfx}").attr('action', "${ctx}/mvc/reference/identifiers/saveIdentifier.json")
           	    	$("#form${idSfx}").submit();
             	});
             	
             	function checkIfIsChangedCode(){
             		var codeOrigin = "${vo.code}";
             		var lastCode = $("#code${idSfx}").val();
             		if(codeOrigin !== lastCode){
             			$("#isToCheckCode${idSfx}").val(true);
             		}
             	}
             	
             	//Need for select input
            	if(!insertMode) {
            		$(".container${idSfx} form [name='fkCcList']").blockEdit();
            		$(".container${idSfx} form [name='linkedEntity']").blockEdit();
            	}
             	
             	
        		        		
        		//remember to add validation before init ajax form
        		initAjaxForm(".container${idSfx} form" ,{
            			success:function(data,status,xhr,formObj){
                			if (formObj.attr('action')){
                				goToUrl("${ctx}/mvc/reference/identifiers/search");
                			}
                		}
        		});
				
        		
    		});

    	</script>
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<div class="container${idSfx}">
			<form:form id="form${idSfx}" cssClass="form-inline" modelAttribute="vo" action="" method="POST">
				<form:hidden path="id" id="id${idSfx}"/>
				<form:hidden path="fkName" id="fkName${idSfx}"/>
				<div>						
					<div class="form-row">
						<div class="form-group">
							<label for="code">${msg['identifiers.col-code']}</label>
							<form:input id="code${idSfx}" path="code" cssClass="form-control" readonly="${!insertMode}" />
						</div>
						<div class="form-group">
							<label for="acronym">${msg['identifiers.col-acronym']}</label>
							<form:input id="acronym${idSfx}" path="acronym" cssClass="form-control" />
						</div>
						<div class="form-group big">
							<label for="name">${msg['identifiers.col-name']}</label>
							<form:input id="name${idSfx}" path="name" cssClass="form-control" />
						</div>
					</div>
					<div class="form-row">
						<div class="form-group">
							<label for="fkCcList${idSfx}" >${msg['identifiers.col-creation-classes']}</label>
							<form:select path="fkCcList" id="fkCcList${idSfx}" cssClass="form-control">
								<form:options items="${ccList}" itemValue="id" itemLabel="code"/>
							</form:select>
						</div>
						<div class="form-group">
							<label for="linkedEntity${idSfx}" >${msg['identifiers.col-linked-entity']}</label>
							<form:select path="linkedEntity" id="linkedEntity${idSfx}" cssClass="form-control">
								<option value=""></option>
								<c:forEach items="${entityList}" var="item">
									<form:option value="${item}">${msg[item.msgCode]}</form:option>
								</c:forEach>
							</form:select>
						</div>
					</div>
				</div>
				
				<div class="form-row">
					<div class="pull-right">
						<button type="button" id="btnSave${idSfx}" class="btn btnSave btn-primary">${msg['global.save']}</button>
					</div>
					<div class="clear"></div> 
				</div>
				
			</form:form>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>