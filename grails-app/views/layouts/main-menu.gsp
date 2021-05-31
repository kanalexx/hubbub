<button class="navbar-toggler navbar-dark m-0" type="button" data-toggle="collapse"
        data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
        aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
</button>

<div class="collapse navbar-collapse" id="navbarSupportedContent">
    <div class="nav" role="navigation">
        <ul class="main-menu navbar-nav navbar-expand-sm">
            <li class="nav-item"><a href="/"><g:message code="default.home.label"/></a>
            <li class="nav-item"><g:link controller="login" action="form"><g:message code="login.form.label"/></g:link>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" type="button" id="menuLink" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    <g:message code="menu.users"/>
                </a>
                <ul class="dropdown-menu" aria-labelledby="menuLink">
                    <li class="dropdown-item"><g:link controller="user" action="search"><g:message
                            code="action.user.search"/></g:link></li>
                    <li class="dropdown-item"><g:link controller="user" action="advSearch"><g:message
                            code="action.user.advSearch"/></g:link></li>
                    <li class="dropdown-item"><g:link controller="user" action="register"><g:message
                            code="action.user.register"/></g:link></li>
                </ul>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" type="button" id="menuLink" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    <g:message code="menu.posts"/>
                </a>
                <ul class="dropdown-menu" aria-labelledby="menuLink">
                    <li class="dropdown-item"><g:link controller="post" action="personal"><g:message
                            code="action.post.timeline"/></g:link></li>
                    <li class="dropdown-item"><g:link controller="post" action="global" params="[max: 5]"><g:message
                            code="action.post.global"/></g:link></li>
                </ul>
            </li>
            <li class="nav-item"><g:link controller="image" action="form"><g:message
                    code="image.form.title"/></g:link></li>
        </ul>
    </div>
</div>