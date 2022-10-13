<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<c:if test="${success != null}">
	<div class="alert alert-success" role="alert">
		${success}
	</div>
</c:if>

<form method="post">
	<div class="form-group">
		<label for="title">Tytuł</label>
		<input type="text" class="form-control" id="title" name="title" placeholder="Podaj tytuł" value="${title}">
	</div>
	<button type="submit" class="btn btn-primary">Szukaj</button>
	<c:if test="${searchTitle != null}">
	    <div class="form-group">
	        <label for="title">Szukany tytuł</label>
	        <input class="form-control" type="text" placeholder="${searchTitle}" readonly>
	    </div>
	</c:if>
</form>

<h3>dropshipping oferty</h3>

<table id="example" class="table table-responsive-xl" cellspacing="0" width="100%">
	<thead>
		<tr>
			<th scope="col">Produkt</th>
			<th scope="col">&nbsp;</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${mainDataSPM}" var="card">
			<tr>
				<td>
				    ${card.title}<br/>
				    <b>Karty:</b> ${card.cards}<br/>
				    <b>Serwisy:</b> ${card.servicesContainer}
				    <c:if test="${card.savesContainer.length() > 0}">
                        <br/><b>Oszczędności:</b> ${card.savesContainer}
                    </c:if>
                    <c:if test="${card.salesContainer.length() > 0}">
                        <br/><b>Wyprzedaże:</b> ${card.salesContainer}
                    </c:if>
                    <c:if test="${card.placeHolder.length() > 0}">
                        <br/><b>Przechowanie:</b> ${card.placeHolder}
                    </c:if>
				</td>
				<td>
				    <b>cena:</b> ${card.price} ${card.priceCurrency}<br/>
				    <b>Sprzedano:</b> <fmt:formatNumber pattern="0" value="${card.sold}"/>
				    <c:if test="${card.evaluation.length() > 0}">
                    	<br/><b>Ocena:</b> ${card.evaluation}
                    </c:if>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<h3>dropshipping 2022 produkty do sprzedania</h3>

<table id="example" class="table table-responsive-xl" cellspacing="0" width="100%">
	<thead>
		<tr>
			<th scope="col">Produkt</th>
			<th scope="col">&nbsp;</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${productToSell}" var="product">
			<tr>
			    <td>
                    ${product.title}<br/>
                    <b>Karty:</b> ${product.cards}<br/>
                    <b>Serwisy:</b> ${product.servicesContainer}
                    <c:if test="${product.savesContainer.length() > 0}">
                        <br/><b>Oszczędności:</b> ${product.savesContainer}
                    </c:if>
                    <c:if test="${product.salesContainer.length() > 0}">
                        <br/><b>Wyprzedaże:</b> ${product.salesContainer}
                    </c:if>
                    <c:if test="${product.placeHolder.length() > 0}">
                        <br/><b>Przechowanie:</b> ${product.placeHolder}
                    </c:if>
                </td>
				<td>
				    <b>Cena:</b> ${product.price} ${product.priceCurrency}<br/>
				    <b>Sprzedano:</b> <fmt:formatNumber pattern="0" value="${product.sold}"/>
				    <c:if test="${product.evaluation.length() > 0}">
                        <br/><b>Ocena:</b> ${product.evaluation}
                    </c:if>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>


<%@ include file="common/footer.jspf" %>