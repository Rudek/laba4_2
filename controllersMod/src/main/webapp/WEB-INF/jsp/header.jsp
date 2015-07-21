<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
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
	
	<script src="js/bootstrap.min.js"></script>
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
	<link href="css/jquery-ui.css" rel="stylesheet" type="text/css" />
	<link href="css/bootstrap.css" rel="stylesheet">
	<link href="css/default/style.css" rel="stylesheet" type="text/css" />
	<link href="css/sticky-footer-navbar.css" rel="stylesheet" type="text/css" >
	<link href="css/style.css" type="text/css" rel="stylesheet">
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
		            <span class="sr-only">Toggle navigation</span>
		            <span class="icon-bar"></span>
		            <span class="icon-bar"></span>
		            <span class="icon-bar"></span>
		        </button>
		        <a class="navbar-brand" href="${pageContext.request.contextPath}/"><spring:message code="menu.main"/></a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="category"><spring:message code="category.category"/></a></li>
					<li><a href="product"><spring:message code="menu.products"/></a></li>
					<li><a href="category/add"><spring:message code="menu.addCategory"/></a></li>
					<li><a href="product/add"><spring:message code="menu.addProduct"/></a></li>
					<security:authorize access="!isAuthenticated()">
						<li class="login"><a class="login" onclick="return false;" href="/">Авторизация</a></li>
					</security:authorize>
					<security:authorize access="isAuthenticated()">
						<li ><a href="j_spring_security_logout">Выход</a></li>
					</security:authorize>
				</ul>
			</div>
		</div>
	</div>
		
	<div class="container">	
		<!-- <div class="left-side"> -->
		<div class="col-md-4">
			<h3>Категории</h3>
			<div id="jstree">
			
			</div>
		</div>
		
		<div class="col-md-8">
			<c:if test="${not empty error}">
				<p class="text-danger text-center">${error}</p>
			</c:if>
			<c:if test="${not empty info}">
				<p class="text-info text-center">${info}</p>
			</c:if>
				
			