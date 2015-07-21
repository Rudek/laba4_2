			<h3 class="title">${product.name}</h3>
			<div class="product">
				<img src="images/upload/${product.image}" alt="<spring:message code="showProduct.altImage"/>" width="288"/>
				<strong><spring:message code="products.price"/>: ${product.price} <spring:message code="showProduct.altCurrency"/></strong>
				<p class="description">${product.description}</p>
				<div style="clear:both; text-align:right;"><a href="product/edit/${product.id}"><spring:message code="showProduct.edit"/></a> <a class="delete" href="product/delete/${product.id}"><spring:message code="showProduct.delete"/></a></div>
			</div>