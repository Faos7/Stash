<#-- @ftlvariable name="books" type="java.util.List<com.faost.security.domain.model.Book>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>List of Books</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
        <li><a href="/book/create">Create a new book</a></li>
    </ul>
</nav>

<h1>List of Books</h1>

<table>
    <thead>
    <tr>
        <th>Inner number</th>
        <th>Book name</th>
        <th>Author name</th>
        <th>Publishing year</th>
        <th>Library name</th>
        <th>University name</th>
    </tr>
    </thead>
    <tbody>
    <#list books as book>
    <tr>
        <td><a href="/book/${book.bookId}">${book.number}</a></td>
        <td>${book.bookName}</td>
        <td>${book.authorName}</td>
        <td>${book.publYear}</td>
        <td>${book.library.name}</td>
        <td>${book.library.university.name}</td>
    </tr>
    </#list>
    </tbody>
</table>
</body>
</html>