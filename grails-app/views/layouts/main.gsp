<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        Hubbub &raquo; <g:layoutTitle default="Welcome"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>

    <asset:stylesheet src="application.css"/>
%{--    <asset:stylesheet src="grails.css"/>--}%
%{--    <asset:stylesheet src="main.css"/>--}%
    <asset:stylesheet src="hubbub.css"/>

    <g:layoutHead/>
</head>

<body>

    <header id="hd">
        <asset:image id="logo" dir="images" src="headerlogo.png" alt="hubbub logo"/>
        <div class="nav" role="navigation">
            <ul class="main-menu">
                <li><g:link controller="user" action="search"><g:message code="action.user.search"/></g:link></li>
                <li><g:link controller="user" action="advSearch"><g:message code="action.user.advSearch"/></g:link></li>
                <li><g:link controller="post" action="timeline"><g:message code="action.post.timeline"/></g:link></li>
                <li><g:link controller="user" action="register"><g:message code="action.user.register"/></g:link></li>
            </ul>
        </div>
    </header>
    <section id="bd">
        <g:layoutBody/>
    </section>
    <footer id="ft">
        <div id="footerText">Hubbub - Social Networking on Grails</div>
    </footer>

<asset:javascript src="application.js"/>

</body>
</html>
