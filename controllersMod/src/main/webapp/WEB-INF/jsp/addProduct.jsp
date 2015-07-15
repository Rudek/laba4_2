		    <form:form method="post" class="product" action="${pageContext.request.contextPath}/product/save" modelAttribute="product" enctype="multipart/form-data">
			    <c:if test="${not empty product.id}"><form:input type="hidden" path="id" /></c:if>
			    <div>
			        <form:label path="name"><spring:message code="product.name"/>:</form:label>
			        <form:input path="name" /> 
			        <form:errors path="name" />
			    </div>
			    <div> 
			    	<form:label path="category"><spring:message code="label.category"/>:</form:label>
			        <c:set var="chooseCategory">
			        	<spring:message code="category.chooseCategory"/>
		        	</c:set>
			        <form:select path="category">
			        	<form:option value="0" label="${chooseCategory}"/>
			        	<form:options items="${categories}" itemValue="id" itemLabel="name"/>
			        </form:select>
			        <form:errors path="category" />
			    </div>
			    <div>
			    	<form:label path="description"><spring:message code="label.description"/>:</form:label>
					<form:textarea path="description"/>
				</div>
				<div>
					<form:label path="price"><spring:message code="label.price"/>:</form:label>
					<form:input path="price"/>
						<!-- <input type="text" name="price" id="price"/> -->
					<form:errors path="price"/>
				</div>
				<div>
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
			    <input class="add" type="submit" value="${inputText}"/>
			</form:form> 