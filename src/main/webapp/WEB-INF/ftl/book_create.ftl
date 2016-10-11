<#-- @ftlvariable name="form" type="com.faost.security.domain.model.create.BookCreateForm" -->
<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Create a new book</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
    </ul>
</nav>

<h1>Create a new Book</h1>

<form role="form" name="form" action="" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <div>
        <label for="bookName">Book Name</label>
        <input type="text" name="bookName" id="bookName" value="${form.bookName}" required autofocus/>
    </div>

    <div>
        <label for="authorName">Author Name</label>
        <input type="text" name="authorName" id="authorName" value="${form.authorName}" required/>
    </div>

    <div>
        <label for="number">Inner Number</label>
        <input type="number" name="number" id="number" value="${form.number}" required />
    </div>

    <div>
        <label for="publishingYear">Publishing year</label>
        <input type="number" name="" id="publishingYear" value="${form.publishingYear}" required />
    </div>

    <div>
        <label for="libraryName">Faculty</label>
        <input type="text" name="libraryName" id="libraryName" value="${form.libraryName}" required />
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