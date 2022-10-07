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
			<th scope="col">&nbsp;</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${mainDataSPM}" var="card">
			<tr>
				<td><a href=${card.cardsContainerAHref} target=${card.cardsContainerATarget}>${card.title}</a></td>
				<td>${card.price} ${card.priceCurrency}</td>
				<td><a role=${card.cardsStoreARole} href=${card.cardsStoreAHref} target=${card.cardsStoreATarget}>${card.cards}</a></td>
				<td>${card.servicesContainer}</td>
				<td><fmt:formatNumber pattern="0" value="${card.sold}"/></td>
				<td>${card.evaluation}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>


<%@ include file="common/footer.jspf" %>