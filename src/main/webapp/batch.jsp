<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<h1>Batch</h1>

    <form method="POST" action="${pageContext.request.contextPath}/batch/tickets/book" enctype="multipart/form-data">
        <input type="file" name="file" />
        <br/>
        <br/>
        <br/>
        <br/>
        <input type="submit" value="Upload" />
    </form>

    <button onclick="location.href='/'" type="button">Back</button>
</body>
</html>
