<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../../assets/stylesheets/hubbub.css"/><title><g:message code="post.timeline.title" args="${[user.profile.fullName]}"/></title>
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
                <a href="#" id="showHideUrl" onclick="toggleTinyUrl(); return false;">
                    Show TinyURL
                </a>
                <asset:image id="spinner" style="display: none" src="spinner.gif"/>
            </fieldset>
        </g:form>
        <div id="tinyUrl" style="display: none;">
            <g:formRemote name="tinyUrlForm" url="[action: 'tinyUrl']" onSuccess="addTinyUrl(data);">
                TinyUrl: <g:textField name="fullUrl" class="m-1"/>
                <g:submitButton name="submit" value="Make Tiny" class="btn m-1"/>
            </g:formRemote>
        </div>
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
    function toggleTinyUrl() {
        let toggleText = $("#showHideUrl")
        let tinyUrl = $("#tinyUrl")
        if (tinyUrl.is(":visible")) {
            tinyUrl.slideUp(300)
            toggleText.text("Show TinyURL")
        } else {
            tinyUrl.slideDown(300)
            toggleText.text("Hide TinyURL")
        }
    }
    function addTinyUrl(data) {
        let tinyUrl = data.urls.small
        let postBox = $("#postContent")
        postBox.val(postBox.val() + tinyUrl)
        toggleTinyUrl()
        $("#tinyUrl input[name='fullUrl']").val("")
    }
</g:javascript>
</body>
</html>