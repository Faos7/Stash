<#-- @ftlvariable name="student" type="com.faost.security.domain.model.Student" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Student details</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
    </ul>
</nav>

<h1>Library details</h1>

<p>Student first name: ${student.firstName}</p>
<p>Student second name: ${student.secondName}</p>
<p>Student third name: ${student.thirdName}</p>
<p>Phone number: ${student.phoneNumb}</p>
<p>Group name: ${student.group.name}</p>
<p>Faculty name: ${student.group.faculty.name}</p>
<p>University name: ${student.group.faculty.university.name}</p>

</body>
</html>