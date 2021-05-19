<%--
  Created by IntelliJ IDEA.
  User: KanAA
  Date: 19.05.2021
  Time: 19:13
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Search Hubbub</title>
    <meta name="layout" content="main"/>
</head>

<body>
<formset>
    <legend>Search for Friends</legend>
    <g:form action="results">
        <label for="loginId">User Id</label>
        <g:textField name="loginId"/>
        <g:submitButton name="search" value="Search"/>
    </g:form>
</formset>

</body>
</html>