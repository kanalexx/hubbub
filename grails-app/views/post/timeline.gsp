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
<div id="newPost">
    <h3>What is ${user.profile.fullName} hacking on right now?</h3>
    <g:if test="${flash.message}">
        <div class="flash">
            ${flash.message}
        </div>
    </g:if>
    <p>
        <g:form action="addPost" id="${params.id}">
            <g:textArea id="postContent" name="content" rows="3" cols="50"/><br>
            <g:submitButton name="post" value="Post"/>
        </g:form>
    </p>
</div>

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