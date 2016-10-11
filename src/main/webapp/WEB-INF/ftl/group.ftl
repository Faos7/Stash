<#-- @ftlvariable name="group" type="com.faost.security.domain.model.Group" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Group details</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
    </ul>
</nav>

<h1>Group details</h1>

<p>Group name: ${group.name}</p>
<p>Faculty name: ${group.faculty.name}</p>
<p>University name: ${group.faculty.university.name}</p>

</body>
</html>