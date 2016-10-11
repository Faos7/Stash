<#-- @ftlvariable name="libraries" type="java.util.List<com.faost.security.domain.model.Library>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>List of Libraries</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
        <li><a href="/library/create">Create a new library</a></li>
    </ul>
</nav>

<h1>List of Libraries</h1>

<table>
    <thead>
    <tr>
        <th>Library name</th>
        <th>University name</th>
    </tr>
    </thead>
    <tbody>
    <#list libraries as library>
    <tr>
        <td><a href="/library/${library.libraryId}">${library.name}</a></td>
        <td>${library.university.name}</td>
    </tr>
    </#list>
    </tbody>
</table>
</body>
</html>