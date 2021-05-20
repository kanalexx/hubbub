<%--
  Created by IntelliJ IDEA.
  User: kanal
  Date: 20.05.2021
  Time: 23:47
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Advanced Search</title>
    <meta name="layout" content="main"/>
</head>

<body>
<formset>
    <legend>Advanced Search for Friends</legend>
    <table>
        <g:form action="advResults">
            <tr>
                <td><g:message code="profile.fullName.label"/></td>
                <td><g:textField name="fullName"/></td>
            </tr>
            <tr>
                <td><g:message code="profile.email.label"/></td>
                <td><g:textField name="email"/></td>
            </tr>
            <tr>
                <td><g:message code="profile.homepage.label"/></td>
                <td><g:textField name="homepage"/></td>
            </tr>
            <tr>
                <td><g:message code="default.queryType.label"/></td>
                <td><g:radioGroup name="queryType" values="['and', 'or', 'not']" labels="['And', 'Or', 'Not']"
                                  value="and">
                    ${it.radio} ${it.label}
                </g:radioGroup></td>
            </tr>
            <tr>
                <td></td>
                <td><g:submitButton name="search" value="Search"/></td>
            </tr>
        </g:form>
    </table>
</formset>
</body>
</html>