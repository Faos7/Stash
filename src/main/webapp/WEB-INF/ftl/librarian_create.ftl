<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="form" type="com.faost.security.domain.model.create.LibrarianCreateForm" -->
<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Create a new librarian</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
    </ul>
</nav>

<h1>Create a new librarian</h1>

<form role="form" name="form" action="" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <div>
        <label for="firstName">Email address</label>
        <input type="text" name="firstName" id="firstName" value="${form.firstName}" required autofocus/>
    </div>
    <div>
        <label for="secondName">Email address</label>
        <input type="text" name="secondName" id="secondName" value="${form.secondName}" required />
    </div>
    <div>
        <label for="thirdName">Email address</label>
        <input type="text" name="thirdName" id="thirdName" value="${form.thirdName}" required />
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