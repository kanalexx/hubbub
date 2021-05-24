<%--
  Created by IntelliJ IDEA.
  User: KanAA
  Date: 24.05.2021
  Time: 13:43
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="action.user.register"/></title>
    <meta name="layout" content="main">
</head>

<body>
<h1><g:message code="action.user.register"/></h1>
<g:hasErrors>
    <div class="errors">
        <g:renderErrors bean="${user}" as="list"/>
    </div>
</g:hasErrors>
<g:if test="${flash.message}">
    <div class="falsh">${flash.message}</div>
</g:if>
<g:form action="register">
    <fieldset class="form">
        <div class="fieldcontain required">
            <label for="loginId"><g:message code="user.loginId.label"/></label>
            <g:textField name="loginId" value="${user?.loginId}"/>
        </div>
        <div class="fieldcontain required">
            <label for="password"><g:message code="user.password.label"/></label>
            <g:passwordField name="password" value="${user?.password}"/>
        </div>
        <div class="fieldcontain required">
            <label for="profile.fullName"><g:message code="profile.fullName.label"/></label>
            <g:textField name="profile.fullName" value="${user?.profile?.fullName}"/>
        </div>
        <div class="fieldcontain required">
            <label for="profile.bio"><g:message code="profile.bio.label"/></label>
            <g:textArea name="profile.bio" value="${user?.profile?.bio}"/>
        </div>
        <div class="fieldcontain required">
            <label for="profile.email"><g:message code="profile.email.label"/></label>
            <g:textField name="profile.email" value="${user?.profile?.email}"/>
        </div>
    </fieldset>
    <fieldset class="buttons">
        <g:submitButton name="register" value="${message(code: "action.user.register.submit")}"/>
    </fieldset>
</g:form>
</body>
</html>