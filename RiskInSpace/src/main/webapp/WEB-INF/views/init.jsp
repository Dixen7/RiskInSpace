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
    <form method="post" action="/">
     	<label for="name">Nom Joueur 1 : </label>
        <input type="text" name="name" id="name" />
        <select name="species">
          <option value="">Choisir une planète à attaquer</option>
          <c:forEach items="${ species }" var="species">
         	<option value="<c:out value="${species.speciesId }"/>"><c:out value="${ species.speciesName }"/></option>
         	 </c:forEach>
         </select>
  
         <input type="submit" />
     </form>

</div>
</body>