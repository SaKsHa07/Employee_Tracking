<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Employee Tracking System</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>

<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: SlateBlue">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"> Employee Tracking System </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link"></a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${emp != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${emp == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${emp != null}">
            			Edit Employee
            		</c:if>
						<c:if test="${emp == null}">
            			Add New Employee
            		</c:if>
					</h2>
				</caption>

				<c:if test="${emp != null}">
					<input type="hidden" name="eid" value="<c:out value='${emp.eid}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Employee No</label> <input type="text"
						value="<c:out value='${emp.empno}' />" class="form-control"
						name="empno" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Employee Name</label> <input type="text"
						value="<c:out value='${emp.ename}' />" class="form-control"
						name="ename">
				</fieldset>

				<fieldset class="form-group">
					<label>Designation</label> <input type="text"
						value="<c:out value='${emp.designation}' />" class="form-control"
						name="designation">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Base Location</label> <input type="text"
						value="<c:out value='${emp.baselocation}' />" class="form-control"
						name="baselocation">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Skill Set</label> <input type="text"
						value="<c:out value='${emp.skillset}' />" class="form-control"
						name="skillset">
				</fieldset>

				<button type="button" class="btn btn-outline-success">Search</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>