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
<h1>Main</h1>
<div id="main">
    <form method="post" action="">
    
         <select name="planets-owned">
          <option value="">Choisir une planète attaquante</option>
          <c:forEach items="${ planets }" var="planet">
         	<option value="<c:out value="${ planet.id }"/>"><c:out value="${ planet.name }"/></option>
         	 </c:forEach>
         </select>
         
          <select name="planets-defend">
          <option value="">Choisir une planète à attaquer</option>
          <c:forEach items="${ planets2 }" var="planet">
         	<option value="<c:out value="${ planet2.id }"/>"><c:out value="${ planet2.name }"/></option>
         	 </c:forEach>
         </select>
         
         <input type="submit" />
     </form>

</div>
</body>