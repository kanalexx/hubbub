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
<g:form action="register2">
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
            <label for="passwordRepeat"><g:message code="user.passwordRepeat.label"/></label>
            <g:passwordField name="passwordRepeat" value="${user?.passwordRepeat}"/>
        </div>
        <div class="fieldcontain required">
            <label for="fullName"><g:message code="profile.fullName.label"/></label>
            <g:textField name="fullName" value="${user?.fullName}"/>
        </div>
        <div class="fieldcontain required">
            <label for="bio"><g:message code="profile.bio.label"/></label>
            <g:textArea name="bio" value="${user?.bio}"/>
        </div>
        <div class="fieldcontain required">
            <label for="email"><g:message code="profile.email.label"/></label>
            <g:textField name="email" value="${user?.email}"/>
        </div>
    </fieldset>
    <fieldset class="buttons">
        <g:submitButton name="register" value="${message(code: "action.user.register.submit")}"/>
    </fieldset>
</g:form>
</body>
</html>