			<c:if test="${ not empty products }">
				<%-- <h3 class="title">${title}</h3> --%>
				<c:choose>
					<c:when test="${not empty idCat}">
	        			<c:set var="href">
	        				product/category/${idCat}
	        			</c:set>
	        		</c:when>
	        		<c:when test="${empty idCat}">
	        			<c:set var="href">
	        				product
	        			</c:set>
	        		</c:when>
	        	</c:choose>
	        	<p class="sorting">
	        		Сортировка по имени: <a href="${href}?sortby=name">по возростанию</a> <a href="${href}?sortby=name&order=desc">по убыванию</a>. 
	        		Сортировка по стоимости: <a href="${href}?sortby=price">по возростанию</a> <a href="${href}?sortby=price&order=desc">по убыванию</a>
	        	</p>
	        	<c:forEach items="${products}" var="product" varStatus="indexLoop">
					<div class="item">
						<img src="images/upload/${product.image}" height="300" width="150"/>
						<p><a href="product/show/${product.id}">${product.name}</a></p>
						<strong><spring:message code="products.price"/>: ${product.price} <spring:message code="showProduct.altCurrency"/></strong>
					</div>
				</c:forEach>
	        	
				<!-- table class="table-center">
					<thead>
						<tr>
							<th class="number"><spring:message code="label.number" /></th>
							<th><spring:message code="label.name" /> <a href="${href}?sortby=name">&#9660;</a><a href="${href}?sortby=name&order=desc">&#9650;</a></th>
							<th><spring:message code="products.price" /> <a href="${href}?sortby=price">&#9660;</a><a href="${href}?sortby=price&order=desc">&#9650;</a></th>
							<c:if test="${empty idCat}">
								<th><spring:message code="label.category" /></th>
							</c:if>
							<th colspan="2"></th>
						</tr>
					</thead>
					<tbody>
						<c:set var="index" value="${start}"/>
						<c:forEach items="${products}" var="product">
							<tr>
								<td class="number">${index}</td>
								<td class="name"><a href="product/show/${product.id}">${product.name}</a></td>
								<td class="price">${product.price} <spring:message code="showProduct.altCurrency"/> </td>
								<c:if test="${empty idCat}">
									<td><a href="product/category/${product.category.id}">${product.category.name}</a></td>
								</c:if>
								<td class="edit"><a class="edit" href="product/edit/${product.id}"></a></td>
								<td class="edit"><a class="delete" href="product/delete/${product.id}"></a></td>
							</tr>
							<c:set var="index" value="${index+1}"/>
						</c:forEach>
					</tbody>
				</table -->
				<p class="pages">
					<c:forEach var="i" begin="1" end="${countPages}">
			            <a href="${href}?page=${i}${paramsURI}">${i} </a>
			        </c:forEach>
			    </p>
			</c:if>