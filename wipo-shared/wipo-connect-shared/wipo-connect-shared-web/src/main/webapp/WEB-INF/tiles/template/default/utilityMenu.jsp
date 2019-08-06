<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>

<script type="text/javascript">

	$(document).ready(function() {
		var $container = $("<ul class='nav navbar-nav navbar-right'></ul>");
		
			
		var $menu = $("<li class='dropdown screenshot-menu'></li>");
		
		var $menuLink = $("<a href='#' class='dropdown-toggle' data-toggle='dropdown' role='button' aria-expanded='false'></a>");
		$menuLink.append("<span class='main-menu-ico screenshot' title='" + globalParams.messages['global.screenshot'] + "'></span>");
		$menuLink.append("<span class='main-menu-desc'>" + globalParams.messages['global.screenshot'] + "</span>");
		$menuLink.append("<span class='caret'></span>");
		
		$menu.append($menuLink);


		var $menuItem = $("<ul class='dropdown-menu' role='menu'></ul>");
		$menuItem.append("<li role='presentation' class='dropdown-header'>" + globalParams.messages['global.screenshot'] + "</li>");

		menuItemList = [
			{format:'pdf', i18n:'global.screenshot-pdf'},
			{format:'jpg', i18n:'global.screenshot-jpg'},
			{format:'png', i18n:'global.screenshot-png'}
		];
		
		$.each(menuItemList, function( j, menuItem ) {
			var $menuItemLink = $("<li id='" + menuItem.format + "'></li>");
			$menuItemLink.append("<a data-format='" + menuItem.format + "' href='#'>" + globalParams.messages[menuItem.i18n] + "</a>");						
			
			$menuItem.append($menuItemLink);
		});
		
		
		$menu.append($menuItem);
		$container.append($menu);
		$("#navbar-collapse").append($container);
		
	});
	
</script>