<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Lab Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
    <jsp:include page="navbar.jsp"/>
    <div class="container">
        <h1 class="mt-4">Lab Dashboard</h1>
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                    List<Lab> labs = (List<Lab>) request.getAttribute("listLabs");
                    for (Lab lab : labs) { 
                    %>
                    <tr>
                        <td><%= lab.getName() %></td>
                        <td><%= lab.getDescription() %></td>
                        <td>
                            <a href="/delete-lab?id=<%= lab.getId() %>" class="btn btn-danger btn-sm">Delete</a>
                            <a href="/soft-delete-lab?id=<%= lab.getId() %>" class="btn btn-warning btn-sm">Soft Delete</a>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
