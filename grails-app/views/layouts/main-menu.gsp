<div class="nav" role="navigation">
    <ul class="main-menu">
        <li><a href="/"><g:message code="default.home.label"/></a>
        <li>
            <div class="dropdown">
                <a class="btn dropdown-toggle" href="#" type="button" id="menuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <g:message code="menu.users"/>
                </a>
                <ul class="dropdown-menu" aria-labelledby="menuLink">
                    <li class="dropdown-item"><g:link controller="user" action="search"><g:message code="action.user.search"/></g:link></li>
                    <li class="dropdown-item"><g:link controller="user" action="advSearch"><g:message code="action.user.advSearch"/></g:link></li>
                    <li class="dropdown-item"><g:link controller="user" action="register"><g:message code="action.user.register"/></g:link></li>
                </ul>
            </div>
        </li>
        <li><g:link controller="post" action="index"><g:message code="action.post.timeline"/></g:link></li>
        <li><g:link controller="image" action="form"><g:message code="image.form.title"/></g:link></li>
    </ul>
</div>