<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modifier ville</title>
</head>
<body>
<div class="container">
<c:set var="rowsToDisplay" value="50" scope="page" />
<c:import url="/inc/navbar.jsp" />
	<br>
	<h1>Villes de France</h1>
	<br>
	<form action="ModifierVille?ville=${villeFrance.get('Code_commune_INSEE')}" method="post">
		<table class="table table-striped">
			<thead>
				<tr>
					<c:forEach var="key" items="${keys}" varStatus="loopIndexKeys">
						<th scope="col"><c:out value="${key}"/></th>
					</c:forEach>
				</tr>
			</thead>
			<tbody>
				<tr>
					<c:forEach var="key" items="${keys}" varStatus="loopIndexKeys">
				  		<td><input type="text" class="form-control" name="${key}" value="${villeFrance.get(key)}"></td>
			  		</c:forEach>
				</tr>
			</tbody>
		</table>
		<br>
		<div class="form-row">
			<div class="form-group col-md-2">
				<label>Sélectionnez une action:</label>
			</div>
			<div class="form-group col-md-5">
				<select name="action" class="form-control">
					<option selected value="Modifier">Modifier</option>
					<option value="Supprimer">Supprimer</option>
					<option value="Ajouter">Ajouter en tant que nouvelle ville</option>
				</select>
			</div>
			<div class="form-group col-md-2">
				<button type="submit" class="btn btn-primary">Valider</button>
			</div>
		</div>
	</form>
	<br>
	<c:choose>
		<c:when test="${resultSuppr=='1'}">
			<div class="alert alert-success" role="alert">
				Ville supprimée
			</div>
		</c:when>
		<c:when test="${resultAjout=='1'}">
			<div class="alert alert-success" role="alert">
				Ville ajoutée
			</div>
		</c:when>
		<c:when test="${resultModif=='1'}">
			<div class="alert alert-success" role="alert">
				Ville modifiée
			</div>
		</c:when>
		<c:when test="${error!=null}">
			<div class="alert alert-danger" role="alert">
				<c:out value="${error}"/>
			</div>
		</c:when>
	</c:choose>
</div>
</body>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</html>