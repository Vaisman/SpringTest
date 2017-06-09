<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>
<h1>Booking Web Application</h1>
<table>
    <tr>
        <th>
            <p><a href="user/">Users</a></p>
        </th>
    </tr>
    <tr>
        <td>
            <p><a href="event/">Events</a></p>
        </td>
    </tr>
    <tr>
        <td>
            <p><a href="discount.jsp">Discount</a></p>
        </td>
    </tr>
    <tr>
        <td>
            <p><a href="booking/">Booking</a></p>
        </td>
    </tr>
    <tr>
        <td>
            <p><a href="auditorium/">Auditorium</a></p>
        </td>
    </tr>
    <tr>
        <td>
            <p><a href="batch.jsp">Batch</a></p>
        </td>
    </tr>
</table>
</body>
</html>
