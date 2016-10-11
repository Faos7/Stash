<#-- @ftlvariable name="librarians" type="java.util.List<com.faost.security.domain.model.Librarian>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>List of librarians</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
        <!--<li><a href="/book/create">Create a new book</a></li>-->
    </ul>
</nav>

<h1>List of Librarians</h1>

<table>
    <thead>
    <tr>
        <th>First name</th>
        <th>Second name</th>
        <th>Third name</th>
        <th>Library name</th>
        <th>University name</th>
    </tr>
    </thead>
    <tbody>
    <#list librarians as librarian>
    <tr>
        <td><a href="/librarian/${librarian.getLibrarianid()}">${librarian.firstName}</a></td>
        <td>${librarian.secondName}</td>
        <td>${librarian.thirdName}</td>
        <td>${librarian.library.name}</td>
        <td>${librarian.library.university.name}</td>
    </tr>
    </#list>
    </tbody>
</table>
</body>
</html>