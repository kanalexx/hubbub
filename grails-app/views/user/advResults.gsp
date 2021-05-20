<%--
  Created by IntelliJ IDEA.
  User: kanal
  Date: 20.05.2021
  Time: 23:40
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Advanced Search Result</title>
  <meta name="layout" content="main"/>
</head>

<body>
<h1>Advanced Search Result</h1>
<p>Searched for items matching. Found <strong>${profiles.size()}</strong> hits.
</p>
<ul>
  <f:table collection="${profiles}" properties="fullName, email, homepage"/>
</ul>
<g:link action="advSearch">Search Again</g:link>
</body>
</html>