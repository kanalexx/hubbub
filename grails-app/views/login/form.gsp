<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Sign into Hubbub</title>
    <meta name="layout" content="main">
</head>
<body>
<h1>Sign in</h1>
<g:form action="signIn">
    <fieldset class="form">
        <div class="form-group required">
            <label for="loginId">Login ID</label>
            <g:textField name="loginId" value="${loginId}" class="form-control" />
        </div>
        <div class="form-group required">
            <label for="password">Password</label>
            <g:passwordField name="password" class="form-control"/>
        </div>
    </fieldset>
    <fieldset class="buttons">
        <g:submitButton name="signIn" value="Sign in"/>
    </fieldset>
</g:form>

</body>
</html>