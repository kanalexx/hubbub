<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="login.form.title"/></title>
    <meta name="layout" content="main">
</head>
<body>
<section class="login-form">
    <h1><g:message code="login.form.label"/></h1>
    <g:if test="${flash.error}">
        <div class="alert alert-danger">${flash.error}</div>
    </g:if>
    <g:form action="signIn">
        <fieldset class="form">
            <div class="form-group required">
                <label for="loginId"><g:message code="user.loginId.label"/></label>
                <g:textField name="loginId" value="${loginId}" class="form-control"/>
            </div>

            <div class="form-group required">
                <label for="password"><g:message code="user.password.label"/></label>
                <g:passwordField name="password" class="form-control"/>
            </div>

            <g:submitButton name="signIn" value="${message(code: "login.form.submit")}" class="btn btn-primary"/>
        </fieldset>
    </g:form>
</section>
</body>
</html>