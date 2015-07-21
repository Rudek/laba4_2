			<h3>${title}</h3>
			<form:form method="post" role="form" modelAttribute="category" class="category" action="${pageContext.request.contextPath}/category/save">
				<c:if test="${not empty category.id}"><form:input type="hidden" path="id" /></c:if>
				<div class="form-group">
				    <form:label path="name"><spring:message code="category.name"/>:</form:label>
					<form:input type="text" class="form-control" path="name"/>
					<form:errors path="name" />
				</div>
				<div class="form-group">
					<form:label path="parentCategory"><spring:message code="category.parent"/>:</form:label>
					<c:set var="chooseParentCategory">
			        	<spring:message code="category.chooseParentCategory"/>
		        	</c:set>
					<form:select class="form-control" path="parentCategory" >
						<form:option value="0" label="${chooseParentCategory}"/>
						<form:options items="${categories}" itemValue="id" itemLabel="name"/>
					</form:select>
					<form:errors path="parentCategory" />
				</div>
				<c:choose>
	        		<c:when test="${empty category.id}">
	        			<c:set var="action">
	        				<spring:message code="label.add"/>
	        			</c:set>
	        		</c:when>
	        		<c:when test="${!empty category.id}">
	        			<c:set var="action">
	        				<spring:message code="label.update"/>
	        			</c:set>
	        		</c:when>
	        	</c:choose>
				<button class="btn btn-default" type="submit">${action}</button>
			</form:form>