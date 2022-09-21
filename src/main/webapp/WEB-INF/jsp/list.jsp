<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<h3>Lista ofert</h3>

<!--<table class="table table-responsive-xl">-->
<table id="example" class="table table-responsive-xl" cellspacing="0" width="100%">
	<thead>
		<tr>
			<th scope="col">Tytuł</th>
			<th scope="col">cena</th>
			<th scope="col">karty</th>
			<th scope="col">serwis</th>
			<th scope="col">Sprzedano</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${mainDataSPM}" var="card">
			<tr>
				<td>${card.title}</td>
				<td>${card.price} ${card.priceCurrency}</td>
				<td>${card.cards}</td>
				<td>${card.servicesContainer}</td>
				<td><fmt:formatNumber pattern="0" value="${card.sold}"/></td>
			</tr>
		</c:forEach>
	</tbody>
</table>


<%@ include file="common/footer.jspf" %>