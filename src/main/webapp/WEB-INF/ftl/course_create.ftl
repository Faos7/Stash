<#-- @ftlvariable name="form" type="com.faost.security.domain.model.create.CourseCreateForm" -->
<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Create a new course</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
    </ul>
</nav>

<h1>Create a new course</h1>

<form role="form" name="form" action="" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <div>
        <label for="numb">Number</label>
        <input type="number" name="numb" id="numb" required autofocus/>
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