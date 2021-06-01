<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="post.timeline.title" args="${[user.profile.fullName]}"/></title>
    <meta name="layout" content="main">
    <g:if test="${user?.profile?.skin}">
        <asset:stylesheet src="${user?.profile?.skin}.css"/>
    </g:if>
</head>

<body>
<section id="newPost">
    <h3>What is ${user.profile.fullName} hacking on right now?</h3>
    <g:if test="${flash.message}">
        <div class="flash">
            ${flash.message}
        </div>
    </g:if>
    <p>
        <g:form action="addPost" id="${params.id}">
            <g:textArea id="postContent" name="content" rows="3" cols="50"/><br>
            <fieldset class="buttons">
%{--                <g:submitButton name="post" value="${message(code: "action.post.timeline.post")}"/>--}%
                <g:submitToRemote value="${message(code: "action.post.timeline.post")}"
                                  url="[controller: 'post', action: 'addPostAjax']"
                                  update="allPosts"
                                  onSuccess="clearPost()"
                                  onLoading="showSpinner(true)"
                                  onComplete="showSpinner(false)"
                />
                <asset:image id="spinner" style="display: none" src="spinner.gif"/>
            </fieldset>
        </g:form>
    </p>
</section>

<section id="allPosts">
    <g:each in="${user.posts}" var="post">
        <article class="postEntry">
            <div class="postText">
                ${post.content}
            </div>
            <div class="postDate">
                <hub:dateFromNow date="${post.dateCreated}"/>
            </div>
        </article>
    </g:each>
</section>

<g:javascript>
    function clearPost() {
        $("#postContent").val("")
    }
    function showSpinner(visible) {
        if (visible)
            $("#spinner").show()
        else
            $("#spinner").hide()
    }
</g:javascript>
</body>
</html>