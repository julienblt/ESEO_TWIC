<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liste des villes</title>
</head>
<body>
<div class="container">
<c:set var="rowsToDisplay" value="50" scope="page" />
<c:import url="/inc/navbar.jsp" />
	<br>
	<h1>Villes de France</h1>
	<br>
	<c:import url="/inc/pagination.jsp" />
	<table class="table table-striped">
		<thead>
			<tr>
				<th scope="col">#</th>
				<c:forEach var="key" items="${keys}" varStatus="loopIndexKeys">
					<th scope="col"><c:out value="${key}"/></th>
				</c:forEach>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="ville" items="${listeVilles}" varStatus="loopIndexVilles">
				<c:if test="${rowsToDisplay*(page-1)-1<loopIndexVilles.index && loopIndexVilles.index<rowsToDisplay*page}">
				<tr>
					<td scope="row"><c:out value="${loopIndexVilles.index+1}"/></td>
					<c:forEach var="key" items="${keys}" varStatus="loopIndexKeys">
				  		<td><c:out value="${ville.getVilleDict().get(key)}"/></td>
			  		</c:forEach>
			  		<td>
			  		<a class="btn btn-primary" 
			  		href="ModifierVille?ville=${ville.getVilleDict().get('Code_commune_INSEE')}">
			  		Modifier</a></td>
			  	</tr>
			  	</c:if>
			</c:forEach>
		</tbody>
	</table>
<c:import url="/inc/pagination.jsp" />
</div>
</body>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</html>