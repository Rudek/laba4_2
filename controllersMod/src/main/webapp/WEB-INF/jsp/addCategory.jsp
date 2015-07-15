			<form:form method="post" modelAttribute="category" class="category" action="${pageContext.request.contextPath}/category/save">
				<h3>${title}</h3>
				<c:if test="${not empty category.id}"><form:input type="hidden" path="id" /></c:if>
				<div>
				    <form:label path="name"><spring:message code="category.name"/>:</form:label>
					<form:input path="name"/>
					<form:errors path="name" />
				</div>
				<div>
					<form:label path="parentCategory"><spring:message code="category.parent"/>:</form:label>
					<c:set var="chooseParentCategory">
			        	<spring:message code="category.chooseParentCategory"/>
		        	</c:set>
					<form:select path="parentCategory" class="select">
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
				<input class="add" type="submit" value="${action}"/>
			</form:form>