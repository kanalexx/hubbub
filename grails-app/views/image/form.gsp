<%--
  Created by IntelliJ IDEA.
  User: KanAA
  Date: 26.05.2021
  Time: 13:47
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="image.form.title"/></title>
    <meta name="layout" content="main"/>
</head>

<body>
<h1><g:message code="image.form.label"/></h1>
<g:uploadForm action="upload">
    <fieldset class="form">
        <div class="fieldcontain">
            <label for="loginId"><g:message code="user.loginId.label"/>:</label>
            <g:select name="loginId" from="${userList}" optionKey="loginId" optionValue="loginId"/>
        </div>
        <div class="fieldcontain">
            <label for="photo"><g:message code="profile.photo.label"/>:</label>
            <input name="photo" type="file"/>
        </div>
    </fieldset>
    <fieldset class="buttons">
        <g:submitButton name="upload" value="${message(code: "image.form.submit")}" />
    </fieldset>
</g:uploadForm>
</body>
</html>