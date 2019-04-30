<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav aria-label="Page navigation">
	<ul class="pagination justify-content-center">
		<li class="page-item">
			<a class="page-link" href="?page=${(page==1) ? 1 : page-1}" aria-label="Previous">
				<span aria-hidden="true">&laquo;</span>
				<span class="sr-only">Previous</span>
			</a>
		</li>
		<li class="page-item"><a class="page-link" href="?page=1">1</a></li>
		<li class="page-item disabled"><a class="page-link" href="#">...</a></li>
		<select class="page-item text-primary" onchange="location = this.value;" >
		<c:forEach begin="1" end="68" varStatus="loop">
		<c:choose>
			<c:when test="${loop.index==page}">
				<option selected class="page-link" value="?page=${loop.index}"><c:out value="${loop.index}"/></option>
			</c:when>
			<c:otherwise>
				<option class="page-link" value="?page=${loop.index}"><c:out value="${loop.index}"/></option>
			</c:otherwise>
		</c:choose>
		</c:forEach>
		</select>
		<li class="page-item disabled"><a class="page-link" href="#">...</a></li>
		<li class="page-item"><a class="page-link" href="?page=68">68</a></li>
		<li class="page-item">
			<a class="page-link" href="?page=${(page==68) ? 68 : page+1}" aria-label="Next">
				<span aria-hidden="true">&raquo;</span>
				<span class="sr-only">Next</span>
			</a>
		</li>
	</ul>
</nav>