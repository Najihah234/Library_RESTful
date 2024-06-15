<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../../layout/adminHeader.jsp"%>    
<!-- Code by Haidah-->

<title>Add New Book</title>

<style>

h2{
  padding: 20px;
  text-align:center;
  margin-top: 10px;
}

.center{
  margin: auto;
  padding: 15px;
  
}

</style>

<h2>Add New Book</h2>

<div class="card border-primary mb-3 center" style="max-width: 50rem;">

<div class="card-body" style="margin-top:0px;">
<form action = "addBook" method="post">
  <fieldset>
    <div class="form-group">
      <input type="text" class="form-control" name="isbn" id="ISBN" placeholder="Input ISBN Number" required>
    </div><br>
    <div class="form-group">
      <input type="text" class="form-control" name="title" id="title" placeholder="Input Tittle" required>
    </div><br>
    <div class="form-group">
      <input type="text" class="form-control" name="author" id="author" placeholder="Input Author" required>
    </div><br>
    <div class="form-group">
      <input type="text" class="form-control" name="publisher" id="publisher" placeholder="Input Publisher" required>
    </div><br>
    <div class="form-group">
      <input type="text" class="form-control" name="version" id="version" placeholder="Input Version" required>
    </div><br>
    <div class="form-group">
      <input type="text" class="form-control" name="description" id="description" placeholder="Input Description" required>
    </div><br>
    <div class="form-group">
      <input type="hidden" class="form-control" name="type" id="type" value="Book" required>
    </div><br>
    
    <input type="submit" class="btn btn-primary" value = "Submit">
  </fieldset>
  
 
</form>
</div>
</div>

