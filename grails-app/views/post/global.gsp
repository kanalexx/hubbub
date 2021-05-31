<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="post.global.timeline.title"/></title>
    <meta name="layout" content="main">
</head>

<body>
<h1><g:message code="post.global.timeline.label"/></h1>

<g:if test="${flash.message}">
    <div class="flash">
        ${flash.message}
    </div>
</g:if>
<g:if test="${session.user}">
    <section id="newPost">
      <h3>What is ${session.user.profile.fullName} hacking on right now?</h3>
    <p>
    <g:form action="addPost" id="${params.id}">
        <g:textArea id="postContent" name="content" rows="3" cols="50"/><br>
        <fieldset class="buttons">
            <g:submitButton name="post" value="${message(code: "action.post.timeline.post")}"/>
        </fieldset>
    </g:form>
    </p>
    </section>
</g:if>

<section id="allPosts">
    <g:each in="${posts}" var="post">
        <article class="postEntry">
            <div class="postText">
                ${post.content}
            </div>

            <div class="postDate">
                <hub:dateFromNow date="${post.dateCreated}"/>
            </div>
        </article>
    </g:each>
    <g:paginate action="global" total="${postCount}" max="5"/>
</section>
</body>
</html>