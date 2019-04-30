<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Calculer distance</title>
</head>
<body>
<div class="container">
<c:import url="/inc/navbar.jsp" />
	<br>
	<h1>Calculer la distance entre 2 villes</h1>
	<br>

		<form action="<c:url value="/CalculDistance"/>" method="post">
		  	<div class="form-group">
		  		<div class="row">
			  		<div class="col-sm">
						<select class="custom-select" name="ville1">
						  <option selected>Choisissez une ville</option>
						  <c:forEach var="ville" items="${listeVilles}" varStatus="loopIndexVilles">
						  		<option value="<c:out value="${ville.getVilleDict().get('Code_commune_INSEE')}"/>">
						  		<c:out value="${ville.getVilleDict().get('Nom_commune')}"/></option>
							</c:forEach>
						</select>
						</div>
					<div class="col-sm">
						<select class="custom-select" name="ville2">
						  <option selected>Choisissez une deuxième ville</option>
						  <c:forEach var="ville" items="${listeVilles}" varStatus="loopIndexVilles">
						  		<option value="<c:out value="${ville.getVilleDict().get('Code_commune_INSEE')}"/>">
						  		<c:out value="${ville.getVilleDict().get('Nom_commune')}"/></option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
		  <button type="submit" class="btn btn-primary">Calculer</button>
		</form>
		<br>
		
		<c:choose>
			<c:when test="${distance!=null}">
				<div class="alert alert-success" role="alert">
					La distance entre <c:out value="${nomVille1}"/> et <c:out value="${nomVille2}"/> 
					est de <c:out value="${distance}"/> km(s)
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