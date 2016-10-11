<#-- @ftlvariable name="book" type="com.faost.security.domain.model.Book" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Book details</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
    </ul>
</nav>

<h1>Book details</h1>

<p>Book name: ${book.bookName}</p>
<p>Author name: ${book.authorName}</p>
<p>Publishing year: ${book.publYear}</p>
<p>Inner number: ${book.number}</p>
<p>Library name: ${book.library.name}</p>
<p>University name: ${book.library.university.name}</p>

</body>
</html>