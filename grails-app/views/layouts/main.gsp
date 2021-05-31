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
    <asset:stylesheet src="hubbub.css"/>

    <g:layoutHead/>
</head>

<body>

    <header id="hd">
        <asset:image id="logo" dir="images" src="headerlogo.png" alt="hubbub logo"/>
        <g:applyLayout name="main-menu"/>
    </header>
    <section id="bd">
        <g:layoutBody/>
    </section>
    <footer id="ft">
        <div id="footerText">Hubbub - Social Networking on Grails</div>
        Version <g:meta name="info.app.version"/>
        on Grails <g:meta name="info.app.grailsVersion"/>
    </footer>

%{--<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" crossorigin="anonymous"></script>--}%
<asset:javascript src="application.js"/>

</body>
</html>
