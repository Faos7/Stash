<#-- @ftlvariable name="universities" type="java.util.List<com.faost.security.domain.model.University>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>List of Universities</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
        <li><a href="/university/create">Create a new university</a></li>
    </ul>
</nav>

<h1>List of Universities</h1>

<table>
    <thead>
    <tr>
        <th>Name</th>
    </tr>
    </thead>
    <tbody>
    <#list universities as university>
    <tr>
        <td><a href="/university/${university.univerId}">${university.name}</a></td>
    </tr>
    </#list>
    </tbody>
</table>
</body>
</html>