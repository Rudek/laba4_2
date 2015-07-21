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
	        	
				<p class="pages">
					<c:forEach var="i" begin="1" end="${countPages}">
			            <a href="${href}?page=${i}${paramsURI}">${i} </a>
			        </c:forEach>
			    </p>
			</c:if>