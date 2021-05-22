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
    <asset:stylesheet src="hubbub.css"/>
    <asset:stylesheet src="main.css"/>

%{--    <asset:stylesheet src="application.css"/>--}%

    <g:layoutHead/>
</head>

<body>

<div>
    <div id="hd">
        <asset:image id="logo" dir="images" src="headerlogo.png" alt="hubbub logo"/>
        <div class="nav" role="navigation">
            <ul class="main-menu">
                <li><g:link controller="user" action="search">Поиск</g:link></li>
                <li><g:link controller="user" action="advSearch">Расширенный поиск</g:link></li>
                <li><g:link controller="post" action="timeline">Сообщения</g:link></li>
            </ul>
        </div>
    </div>
    <div id="bd">
        <g:layoutBody/>
    </div>
    <div id="ft">
        <div id="footerText">Hubbub - Social Networking on Grails</div>
    </div>
</div>

%{--<g:layoutBody/>--}%

%{--<div class="footer row" role="contentinfo">--}%
%{--    <div class="col">--}%
%{--        <a href="http://guides.grails.org" target="_blank">--}%
%{--            <asset:image src="advancedgrails.svg" alt="Grails Guides" class="float-left"/>--}%
%{--        </a>--}%
%{--        <strong class="centered"><a href="http://guides.grails.org" target="_blank">Grails Guides</a></strong>--}%
%{--        <p>Building your first Grails app? Looking to add security, or create a Single-Page-App? Check out the <a href="http://guides.grails.org" target="_blank">Grails Guides</a> for step-by-step tutorials.</p>--}%

%{--    </div>--}%
%{--    <div class="col">--}%
%{--        <a href="http://docs.grails.org" target="_blank">--}%
%{--            <asset:image src="documentation.svg" alt="Grails Documentation" class="float-left"/>--}%
%{--        </a>--}%
%{--        <strong class="centered"><a href="http://docs.grails.org" target="_blank">Documentation</a></strong>--}%
%{--        <p>Ready to dig in? You can find in-depth documentation for all the features of Grails in the <a href="http://docs.grails.org" target="_blank">User Guide</a>.</p>--}%

%{--    </div>--}%

%{--    <div class="col">--}%
%{--        <a href="https://grails-slack.cfapps.io" target="_blank">--}%
%{--            <asset:image src="slack.svg" alt="Grails Slack" class="float-left"/>--}%
%{--        </a>--}%
%{--        <strong class="centered"><a href="https://grails-slack.cfapps.io" target="_blank">Join the Community</a></strong>--}%
%{--        <p>Get feedback and share your experience with other Grails developers in the community <a href="https://grails-slack.cfapps.io" target="_blank">Slack channel</a>.</p>--}%
%{--    </div>--}%
%{--</div>--}%


%{--<div id="spinner" class="spinner" style="display:none;">--}%
%{--    <g:message code="spinner.alt" default="Loading&hellip;"/>--}%
%{--</div>--}%

<asset:javascript src="application.js"/>

</body>
</html>
