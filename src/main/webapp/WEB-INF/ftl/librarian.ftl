<#-- @ftlvariable name="librarian" type="com.faost.security.domain.model.Librarian" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Librarian details</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
    </ul>
</nav>

<h1>Library details</h1>

<p>Librarian first name: ${librarian.firstName}</p>
<p>Librarian second name: ${librarian.secondName}</p>
<p>Librarian third name: ${librarian.thirdName}</p>
<p>Library name: ${librarian.library.name}</p>
<p>University name: ${librarian.library.university.name}</p>

</body>
</html>