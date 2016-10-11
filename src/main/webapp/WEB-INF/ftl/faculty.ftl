<#-- @ftlvariable name="faculty" type="com.faost.security.domain.model.Faculty" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Faculty details</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
    </ul>
</nav>

<h1>Faculty details</h1>

<p>Faculty name: ${faculty.name}</p>
<p>University name: ${faculty.university.name}</p>

</body>
</html>