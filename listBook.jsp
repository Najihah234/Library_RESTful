<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../../layout/adminHeader.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<!-- Code by Haidah-->
     
<style>

.pagination {
  position: absolute;
  right: 200px;
}

#rcorners {
  border: 2px solid #2c3e50;
  padding: 20px; 
  width: 900px;
  height: 150px;  
  margin-left:20%;
  margin-right:20%;
}

h2{
margin-top: 100px;
margin-bottom: 2px;
margin-left: 20%;
}

</style>

    <title>List Books</title>
    
    <h2>List Of Materials</h2>
    
    
    <div style="float: right;margin-right:22%;margin-bottom: 5px;">
    <a type="button" class="btn btn-secondary" href="addBookForm">
    <img src="img/add.png" width="20" height="20" style="float:left;margin-right:10px;">Add Books</a>
    </div>
    
    <div style="float: right;margin-right:5px;margin-bottom: 5px;">
    <a type="button" class="btn btn-secondary" href="materialeBook">
    <img src="img/up-arrow.png" width="20" height="20" style="float:left;margin-right:10px;">Upload Material</a>
    </div>
    
    
<table class="table table-hover" id="rcorners">
  <tbody>
  
  <tr class="table-primary">
      <th>Image</th>
	  <th>Details</th>
	  <th>Type</th>
	  <th>Action</th>
    </tr>
    
    <c:forEach var="material" items="${m}" >
  
  <tr>
      <th scope="row"><img src="img/book.jpg" width="50" height="50"/></th>
      <td><h4>${material.title}</h4><h6>By ${material.author }</h6></td>
      <td>${material.type}</td>
      <td>
      <form action="materialAdminDetails" method="GET">
      <input type="hidden" value="${sid}" name="student">
      <input type="hidden" value="${material.materialid}" name="materialid">
	  <input type="image" src="img/update.png" alt="Submit" width="20" height="20" style="float:left">
      </form>
      
      <form action="deletedMaterial" method="GET" >
      <input type="hidden" value="${material.materialid}" name="materialid">
	  <input type="image" src="img/delete.png" alt="Submit" width="20" height="20" style="float:left">
      </form></td>
    </tr>
    </c:forEach>
    
  </tbody>
</table>

<script>
document.getElementById('addBookForm').addEventListener('submit', function(event) {
    event.preventDefault();
    
    const formData = {
        isbn: document.getElementById('ISBN').value,
        title: document.getElementById('title').value,
        author: document.getElementById('author').value,
        publisher: document.getElementById('publisher').value,
        version: document.getElementById('version').value,
        description: document.getElementById('description').value,
        type: document.getElementById('type').value
    };
    
    fetch('/api/material/adddBook', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
    .then(response => {
        if (response.ok) {
            alert('Book added successfully');
        } else {
            response.text().then(text => {
                alert('Error: ' + text);
            });
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('An error occurred');
    });
});
</script>

<div class="center">
  <ul class="pagination">
    <li class="page-item disabled">
      <a class="page-link" href="#">&laquo;</a>
    </li>
    <li class="page-item active">
      <a class="page-link" href="#">1</a>
    </li>
    <li class="page-item">
      <a class="page-link" href="#">&raquo;</a>
    </li>
  </ul>
</div>           
