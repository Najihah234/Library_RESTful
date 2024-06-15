<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../../layout/adminHeader.jsp" %>   
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ page import="java.util.List, Model.Material"%>
<!-- Code by Haidah-->

<title>Update Book</title>



<style>

h2{
  padding: 20px;
  margin-left: 30px;
  margin-top: 10px;
}

.place{
  margin-left: 30px;
  padding: 15px;
  
img{
  margin-left: 700px;
  margin-right: auto;
}

</style>
        
       
		  


<h2>Update Details</h2>

<div class="row">
	<div class="col-8">
			<div class="card border-primary mb-3 place" style="max-width: 50rem;">
		
		<div class="card-body" style="margin-top:0px;">
		
	
  <form id="updateForm" method="POST" action="./updateBook/${m.materialid}">
    <fieldset>
        <div class="form-group">
            <input type="text" class="form-control" name="isbn" id="isbn" value="${m.isbn}">
        </div><br>
        <div class="form-group">
            <input type="text" class="form-control" name="title" id="title" value="${m.title}">
        </div><br>
        <div class="form-group">
            <input type="text" class="form-control" name="author" id="author" value="${m.author}">
        </div><br>
        <div class="form-group">
            <input type="text" class="form-control" name="publisher" id="publisher" value="${m.publisher}">
        </div><br>
        <div class="form-group">
            <input type="text" class="form-control" name="version" id="version" value="${m.version}">
        </div><br>
        <div class="form-group">
            <input type="text" class="form-control" name="description" id="description" value="${m.description}">
        </div><br>
    </fieldset>

    <input type="hidden" value="${m.materialid}" name="materialid" id="materialId">
    <input type="submit" class="btn btn-primary" value="Update Details" style="float: left;">
</form>



		  
		 
		
		</div>
		</div>
		 
	</div>

		<div class="col-4" >
		<div class="center">
			<img id="center" src="img/book.jpg" width="250" height="250"/>
		</div>
			
		</div>

</div>