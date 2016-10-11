<#-- @ftlvariable name="library" type="com.faost.security.domain.model.Library" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Library details</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
    </ul>
</nav>

<h1>Library details</h1>

<p>Library name: ${library.name}</p>
<p>University name: ${library.university.name}</p>

</body>
</html>