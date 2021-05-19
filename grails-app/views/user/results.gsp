<%--
  Created by IntelliJ IDEA.
  User: KanAA
  Date: 19.05.2021
  Time: 19:55
--%>

<%@ page import="com.kanaa.User" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Search Results</title>
    <meta name="layout" content="main"/>
</head>

<body>
<h1>Results</h1>
<p>Searched ${User.count()} records for items matching <em>${term}</em>.
Found <strong>${users.size()}</strong> hits.
</p>
<ul>
    <g:each in="${users}" var="user">
%{--        <li>${user.loginId}</li>--}%
        <li><f:display bean="${user}"/> </li>
    </g:each>
</ul>
<g:link action="search">Search Again</g:link>
</body>
</html>