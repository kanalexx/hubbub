<%--
  Created by IntelliJ IDEA.
  User: KanAA
  Date: 21.05.2021
  Time: 16:49
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="post.timeline.title" args="${[user.profile.fullName]}"/></title>
    <meta name="layout" content="main">
</head>

<body>
<h1><g:message code="post.timeline.title" args="${[user.profile.fullName]}"/></h1>
<div id="allPosts">
    <g:each in="${user.posts}" var="post">
        <div class="postEntry">
            <div class="postText">
                ${post.content}
            </div>
            <div class="postDate">
                ${post.dateCreated}
            </div>
        </div>
    </g:each>
</div>
</body>
</html>