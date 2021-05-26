<%--
  Created by IntelliJ IDEA.
  User: KanAA
  Date: 26.05.2021
  Time: 15:10
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="user.profile.title" args="[profile.fullName]"/></title>
    <meta name="layout" content="main"/>
</head>

<body>
<div class="profilePic">
    <g:if test="${profile.photo}">
        <img src="${createLink(controller: "image", action: "renderImage", id: profile.user.loginId)}" alt="Photo"/>
    </g:if>
    <p><g:message code="user.profileFor.label"/> <strong>${profile.fullName}</strong></p>
    <p><g:message code="profile.bio.label"/>: ${profile.bio}</p>
</div>
</body>
</html>