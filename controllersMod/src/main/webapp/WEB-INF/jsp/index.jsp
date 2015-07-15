			<c:forEach items="${products}" var="product" varStatus="indexLoop">
				<div class="item">
					<img src="images/upload/${product.image}" height="300" width="150"/>
					<p><a href="product/show/${product.id}">${product.name}</a></p>
					<strong><spring:message code="products.price"/>: ${product.price} <spring:message code="showProduct.altCurrency"/></strong>
				</div>
			</c:forEach>
