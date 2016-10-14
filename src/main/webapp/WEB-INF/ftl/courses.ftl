<#-- @ftlvariable name="courses" type="java.util.List<com.faost.security.domain.model.Course>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>List of Courses</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
        <li><a href="/course/create">Create a new course</a></li>
    </ul>
</nav>

<h1>List of Courses</h1>

<table>
    <thead>
    <tr>
        <th>Number</th>
    </tr>
    </thead>
    <tbody>
    <#list courses as course>
    <tr>
        <td><a href="/course/${course.courseId}">${course.courseNumb}</a></td>
    </tr>
    </#list>
    </tbody>
</table>
</body>
</html>