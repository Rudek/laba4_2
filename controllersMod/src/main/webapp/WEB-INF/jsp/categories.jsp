			<h3 class="title"><spring:message code="category.category"/></h3>
			<table class="table table-bordered">
				
				<thead>
					<tr>
						<th class="number"><spring:message code="label.number"/></th>
						<th colspan="3"><spring:message code="label.name"/></th>
					</tr>
				</thead>
				<c:forEach items="${categories}" var="category" varStatus="indexLoop">
					<tr>
						<td class="number">${indexLoop.index+1}</td>
						<td class="name">${category.name}</td>
						<td class="edit"><a class="edit" href="category/edit/${category.id}"><span class="glyphicon glyphicon-edit"></span></a></td>
						<td class="edit"><a class="delete" href="category/delete/${category.id}"><span class="glyphicon glyphicon-remove"></span></a></td>
					</tr>
				</c:forEach>
			</table>