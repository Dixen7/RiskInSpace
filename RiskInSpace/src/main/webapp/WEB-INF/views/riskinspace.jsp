<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>RiskInSpace</title>

</head>
<body>
<h1>RiskInSpace</h1>
<div id="main">
	<div id="board">
		<c:forEach items="${ planets }" var="planet">
		<div style="display:inline-block;width:50px;margin:5px;background:black;" class="planet <c:out value="${ planet.planetId }"/>">
			<span style="color:white"><c:out value="${ planet.planetName }"/></span>
		</div>
		</c:forEach>
	</div>
    <form method="post" action="">
    	
         <select name="planets-owned">
          <option value="">Choisir une planète attaquante</option>
          <c:forEach items="${ planetsPlayer1 }" var="planetPlayer1">
         	<option value="<c:out value="${ planetPlayer1.planetId }"/>"><c:out value="${ planetPlayer1.planetName }"/></option>
         	 </c:forEach>
         </select>
         
          <select name="planets-defend">
          <option value="">Choisir une planète à attaquer</option>
          <c:forEach items="${ planetsPlayer2 }" var="planetPlayer2">
         	<option value="<c:out value="${ planetPlayer2.planetId }"/>"><c:out value="${ planetPlayer2.planetName }"/></option>
         	 </c:forEach>
         </select>
         
         <input type="submit" />
     </form>

</div>
</body>