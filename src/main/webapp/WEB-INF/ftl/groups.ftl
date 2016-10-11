<#-- @ftlvariable name="groups" type="java.util.List<com.faost.security.domain.model.Group>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>List of Groups</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
        <li><a href="/group/create">Create a new group</a></li>
    </ul>
</nav>

<h1>List of Groups</h1>

<table>
    <thead>
    <tr>
        <th>Group name</th>
        <th>Faculty name</th>
        <th>University name</th>
    </tr>
    </thead>
    <tbody>
    <#list groups as group>
    <tr>
        <td><a href="/group/${group.groupId}">${group.name}</a></td>
        <td>${group.faculty.name}</td>
        <td>${group.faculty.university.name}</td>
    </tr>
    </#list>
    </tbody>
</table>
</body>
</html>