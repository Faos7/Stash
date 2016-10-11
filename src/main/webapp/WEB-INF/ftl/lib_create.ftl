<#-- @ftlvariable name="form" type="com.faost.security.domain.model.create.LibraryCreateForm" -->
<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Create a new library</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
    </ul>
</nav>

<h1>Create a new library</h1>

<form role="form" name="form" action="" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <div>
        <label for="name">Name</label>
        <input type="text" name="name" id="name" value="${form.name}" required autofocus/>
    </div>
    <div>
        <label for="universityName">University</label>
        <input type="text" name="universityName" id="universityName" value="${form.universityName}" required />
    </div>
    <button type="submit">Save</button>
</form>

<@spring.bind "form" />
<#if spring.status.error>
<ul>
    <#list spring.status.errorMessages as error>
        <li>${error}</li>
    </#list>
</ul>
</#if>

</body>
</html>