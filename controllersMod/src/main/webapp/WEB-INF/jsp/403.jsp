		<p class="text-danger text-center">
			<spring:message code="access.deny" /><br/>
			<c:if test="${not empty message}">
				${message}
			</c:if>
		</p>
		<img class="img-403" height="328" src="images/403.png" alt="У вас нет прав" width="294"/> 