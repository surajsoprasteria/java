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
	<div class="templateContact${idSfx} hidden">
			<div class="form-row row-dynamic">
				<input type="hidden" name="interestedParty.contactList[%INDEX%].id" class="contact-id" />
				<input type="hidden" name="interestedParty.contactList[%INDEX%].execDelete" class="row-deleted" />
				<div class="form-group">
					<label for="">${msg['interestedPartyDetail.contact-type']}</label>
					<input type="text" name="interestedParty.contactList[%INDEX%].contactType" class="form-control CV_CONTACT_TYPE" />
				</div>
				<div class="form-group x-large">
					<label for="">${msg['interestedPartyDetail.contact-value']}</label>
					<input type="text" name="interestedParty.contactList[%INDEX%].value" class="form-control CV_CONTACT_VALUE" />
				</div>
				<div class="form-group auto">
					<button type="button" class="btn btn-xs btn-default contact-remove"><span class="glyphicon glyphicon-minus"></span></button>
				</div>
			</div>
		</div>
		
		  <div class="templateName${idSfx} hidden">
			<div class="form-row row-dynamic">
				<input type="hidden" name="interestedParty.nameList[%INDEX%].id" class="name-id" />
				<input type="hidden" name="interestedParty.nameList[%INDEX%].execDelete" class="row-deleted" />
				<div class="form-group">
					<input type="text" name="interestedParty.nameList[%INDEX%].name" class="form-control name-main CV_LAST_NAME" />
				</div>
				<div class="form-group">
					<input type="text" name="interestedParty.nameList[%INDEX%].firstName" class="form-control name-first CV_FIRST_NAME" />
				</div>
				 <div class="form-group small">
					<select name="interestedParty.nameList[%INDEX%].nameType" class="form-control name-type CV_NAME_TYPE">
						<option value=""></option>
						<c:forEach items="${nameTypeList}" var="item" >
							<option value="${item}">${item}</option>
						</c:forEach>
					</select>
				</div> 
				<div class="form-group">
					<input type="text" name="interestedParty.nameList[%INDEX%].ipin" class="form-control" readonly="readonly"/>
				</div>
				<div class="form-group auto">
					<button type="button" class="btn btn-xs btn-default name-remove"><span class="glyphicon glyphicon-minus"></span></button>
				</div>
			</div>
		</div>

		<div class="templateAddress${idSfx} hidden">
		<div class="form-row row-dynamic">
			<div class="form-row">
				<input type="hidden" name="interestedParty.addressList[%INDEX%].id"  id="interestedParty.addressList%INDEX%.id" class="address-id" />
				<input type="hidden" name="interestedParty.addressList[%INDEX%].execDelete"  id="interestedParty.addressList%INDEX%.execDelete" class="row-deleted" />
				<input name="index%INDEX%" type="hidden"/>
				<div class="form-group auto">
					<label>Address %NEXT_INDEX%</label>
				</div>
				<div class="form-group auto">
					<button type="button" class="btn btn-xs btn-default address-remove">
						<span class="glyphicon glyphicon-minus"></span>
					</button>
				</div>
			</div>
			<div class="form-row">
				<div class="form-group">
					<label>${msg['interestedPartyDetail.address-type']}</label>
					<input type="text" name="interestedParty.addressList[%INDEX%].addressType"   id="interestedParty.addressList%INDEX%.addressType" class="form-control CV_ADDRESS_TYPE" />
				</div>
				<div class="form-group">
					<label>${msg['interestedPartyDetail.address-line-1']}</label>
					<input type="text" name="interestedParty.addressList[%INDEX%].line1" id="interestedParty.addressList%INDEX%.line1" class="form-control CV_ADDRESS_LINE1" />
				</div>
				<div class="form-group">
					<label>${msg['interestedPartyDetail.address-line-2']}</label>
					<input type="text" name="interestedParty.addressList[%INDEX%].line2" id="interestedParty.addressList%INDEX%.line2" class="form-control CV_ADDRESS_LINE2" />
				</div>
				<div class="form-group">
					<label>${msg['interestedPartyDetail.address-line-3']}</label>
					<input type="text" name="interestedParty.addressList[%INDEX%].line3" id="interestedParty.addressList%INDEX%.line3" class="form-control CV_ADDRESS_LINE3" />
				</div>
			</div>
			<div class="form-row">
				<div class="form-group">
					<label>${msg['interestedPartyDetail.address-city']}</label>
					<input type="text" name="interestedParty.addressList[%INDEX%].city" id="interestedParty.addressList%INDEX%.city" class="form-control CV_ADDRESS_CITY" />
				</div>
				<div class="form-group">
					<label>${msg['interestedPartyDetail.address-zip-code']}</label>
					<input type="text" name="interestedParty.addressList[%INDEX%].postcode" id="interestedParty.addressList%INDEX%.postcode" class="form-control CV_ADDRESS_ZIP" />
				</div>
				<div class="form-group">
					<label>${msg['interestedPartyDetail.address-province']}</label>
					<input type="text" name="interestedParty.addressList[%INDEX%].province" id="interestedParty.addressList%INDEX%.province" class="form-control CV_ADDRESS_PROVINCE" />
				</div>
				<div class="form-group">
					<label>${msg['interestedPartyDetail.address-country']}</label>
					<input type="text" name="interestedParty.addressList[%INDEX%].fkCountry" id="interestedParty.addressList%INDEX%.fkCountry" class="form-control CV_ADDRESS_COUNTRY" />
				</div>
			</div>
		</div>
	</div> 
	


	<div class="templateDocument${idSfx} hidden">
		<div class="form-row document-row">
				<input type="hidden" name="idList[%INDEX%]" class="document-id" />
				<input type="hidden" name="execDeleteList[%INDEX%]" class="document-deleted" />
				<div class="form-group big">
					<input type="text" name="descriptionList[%INDEX%]" class="form-control document-file CV_IP_DOCUMENT_DESCRIPTION"/>
				</div>
				<div class="form-group big">
					<%-- <label>${msg['work.document-filename']}</label> --%>
					<input type="file" name="fileList[%INDEX%]" class="form-control document-file dyn-rule-multi CV_IP_DOCUMENT_FILESIZE CV_IP_DOCUMENT_FILENAME"/>
				</div>
				<div class="form-group auto">
					<button type="button" class="btn btn-xs btn-default document-remove"><span class="glyphicon glyphicon-minus"></span></button>
				</div>
		</div>
	</div>
	<div class="templateComment${idSfx} hidden">
		<div class="form-row comment-row">
			<input type="hidden" name="interestedParty.ipCommentList[%COMMENT_INDEX%].id" class="comment-id" />
			<input type="hidden" name="interestedParty.ipCommentList[%COMMENT_INDEX%].execDelete" class="comment-deleted" />
			 <div class="form-row" style="height: 2px;">
				<label for="">${msg['interestedParty.comment']}	</label>
				(<input type="text" name="interestedParty.ipCommentList[%COMMENT_INDEX%].updateDatetimeStr"  class="timestamp comment-updateDatetime" readonly="readonly" style="width: 73px;"/>)
			</div>
			<div class="form-group x-small">
				<!-- <input type="text" name="interestedParty.ipCommentList[%COMMENT_INDEX%].updateDatetimeStr"  class="form-control comment-updateDatetime" readonly="readonly" />  -->
					
			</div>
			<div class="form-group extra">
				<textarea name="interestedParty.ipCommentList[%COMMENT_INDEX%].notes" class="form-control comment-notes CV_IP_COMMENT"></textarea>
			</div>
			
			<div class="form-group auto">
				<button type="button" class="btn btn-xs btn-default comment-remove"><span class="glyphicon glyphicon-minus"></span></button>
			</div>
		</div>
	</div>
</div>