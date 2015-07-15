<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${title}</title>
	<script type="text/javascript">
		var lang = "ua";
		var titleAuth = "<spring:message code="auth"/>";
		var titleRemove = "<spring:message code="remove"/>";
		var typeOfActivities = ["категорию","товар"];
		var categoryGood = ["ую", "ый"];
		var help = ["В результате будут удалены все подкатегории, а также связанные с ней товары.",""];
		
		var textRemove = "Вы пытаетесь удалить %s \"%s\". %s ";
		var questionRemove = "Вы действительно хотите удалить выбранн%s %s?";
	</script>
	
	<script src="js/jquery-1.11.2.min.js" type="text/javascript"></script>
	<script src="js/jquery-ui.min.js" type="text/javascript"></script>
	<script src="js/jstree.min.js" type="text/javascript"></script>
	<script src="js/sprintf.min.js" type="text/javascript"></script>
	<script src="js/main.js" type="text/javascript"></script>
	
	<script type="text/javascript">
		var data = ${jsonCategories}; 
		
		$(function (){
		    $('#jstree').jstree({ 
		    	'core' : {
					'data' : data,
					'themes' : {"theme" : "default", "icons" : false}
				}			
			}).bind("select_node.jstree", function(e, data) {
				  var href = $("#"+data.node.id+"_anchor").attr("href");
				  if ( href != "#" && href != "" ) {
				  	  location = href;
				  } else {
				  	  //alert(data.node);
				  	  //$("#"+data.node.id);
				  	  //alert(data.node);
				  	  //data.node.toggle_node();
				  	  //data.node.open_node();
				  }
			});
		});
	</script>
	
	<link rel="stylesheet" href="css/jquery-ui.css" type="text/css" />
	<link href="css/style.css" type="text/css" rel="stylesheet" media="all">
	<link rel="stylesheet" href="css/default/style.css" type="text/css" />
	
</head>
<body>
	<div class="page">
		<div class="wrapper">
			<div class="header">
				<ul class="menu">
					<li><a href="${pageContext.request.contextPath}/"><spring:message code="menu.main"/></a></li>
					<li><a href="category"><spring:message code="category.category"/></a></li>
					<li><a href="product"><spring:message code="menu.products"/></a></li>
					<li><a href="category/add"><spring:message code="menu.addCategory"/></a></li>
					<li><a href="product/add"><spring:message code="menu.addProduct"/></a></li>
					<security:authorize access="!isAuthenticated()">
						<li class="last login"><a class="login" onclick="return false;" href="/">Авторизация</a></li>
					</security:authorize>
					<security:authorize access="isAuthenticated()">
						<li class="last"><a href="j_spring_security_logout">Выход</a></li>
					</security:authorize>
				</ul>
			</div>
			
			<div class="left-side">
				<h3>Категории</h3>
				<div id="jstree">
				
				</div>
			</div>
			<div class="content">
				<c:if test="${not empty error}">
					<p class="error">${error}</p>
				</c:if>
				<c:if test="${not empty info}">
					<p class="info">${info}</p>
				</c:if>
			
			