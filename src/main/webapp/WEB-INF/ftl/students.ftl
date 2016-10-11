<#-- @ftlvariable name="students" type="java.util.List<com.faost.security.domain.model.Student>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>List of students</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
        <!--<li><a href="/book/create">Create a new book</a></li>-->
    </ul>
</nav>

<h1>List of Students</h1>

<table>
    <thead>
    <tr>
        <th>First name</th>
        <th>Second name</th>
        <th>Third name</th>
        <th>Phone number</th>
        <th>Group name</th>
        <th>Faculty name</th>
        <th>University name</th>
    </tr>
    </thead>
    <tbody>
    <#list students as student>
    <tr>
        <td><a href="/student/${student.getStudentId()}">${student.firstName}</a></td>
        <td>${student.secondName}</td>
        <td>${student.thirdName}</td>
        <td>${student.phoneNumb}</td>
        <td>${student.group.name}</td>
        <td>${student.group.faculty.name}</td>
        <td>${student.group.faculty.university.name}</td>
    </tr>
    </#list>
    </tbody>
</table>
</body>
</html>