<#-- @ftlvariable name="faculties" type="java.util.List<com.faost.security.domain.model.Faculty>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>List of Faculties</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
        <li><a href="/faculty/create">Create a new faculty</a></li>
    </ul>
</nav>

<h1>List of Faculties</h1>

<table>
    <thead>
    <tr>
        <th>Faculty name</th>
        <th>University name</th>
    </tr>
    </thead>
    <tbody>
    <#list faculties as faculty>
    <tr>
        <td><a href="/faculty/${faculty.id}">${faculty.name}</a></td>
        <td>${faculty.university.name}</td>
    </tr>
    </#list>
    </tbody>
</table>
</body>
</html>