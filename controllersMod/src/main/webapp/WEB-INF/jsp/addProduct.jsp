		    <h3>${title}</h3>
		    <form:form method="post" role="form" action="${pageContext.request.contextPath}/product/save" modelAttribute="product" enctype="multipart/form-data">
			    <c:if test="${not empty product.id}"><form:input type="hidden" path="id" /></c:if>
			    <div class="form-group">
			        <form:label path="name"><spring:message code="product.name"/>:</form:label>
			        <form:input type="text" class="form-control" path="name" /> 
			        <form:errors path="name" />
			    </div>
			    <div class="form-group"> 
			    	<form:label path="category"><spring:message code="label.category"/>:</form:label>
			        <c:set var="chooseCategory">
			        	<spring:message code="category.chooseCategory"/>
		        	</c:set>
			        <form:select class="form-control" path="category">
			        	<form:option value="0" label="${chooseCategory}"/>
			        	<form:options items="${categories}" itemValue="id" itemLabel="name"/>
			        </form:select>
			        <form:errors path="category" />
			    </div>
			    <div class="form-group">
			    	<form:label path="description"><spring:message code="label.description"/>:</form:label>
					<form:textarea class="form-control" rows="3" path="description"/>
				</div>
				<div class="form-group">
					<form:label path="price"><spring:message code="label.price"/>:</form:label>
					<form:input class="form-control" type="text" path="price"/>
					<form:errors path="price"/>
				</div>
				<div class="form-group">
					<form:label path="image"><spring:message code="label.image"/>:</form:label>
					<form:input type="file" path="image"/>
				</div>
	        	<c:choose>
	        		<c:when test="${empty product.id}">
	        			<c:set var="inputText">
	        				<spring:message code="label.add"/>
	        			</c:set>
	        		</c:when>
	        		<c:when test="${!empty product.id}">
	        			<c:set var="inputText">
	        				<spring:message code="label.update"/>
	        			</c:set>
	        		</c:when>
	        	</c:choose>
			    <button class="btn btn-default" type="submit">${inputText}</button>
			</form:form> 