<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"> </script>
<script>
function validateForm() {
    var x = document.forms["form"]["name"].value;
    if (x == null || x == "") {
        alert("Name must be selected!!!");
        return false;
    }
    var file = document.forms["form"]["file"].value
    if (file == null || file == "") {
        alert("File must be choosen");
        return false;
    }
}


</script>
<style>
.flex-container {
    display: -webkit-flex;
    display: flex;
    -webkit-flex-flow: row wrap;
    flex-flow: row wrap;
    text-align: center;
}

.flex-container > * {
    padding: 15px;
    -webkit-flex: 1 100%;
    flex: 1 100%;
}

.article {
    text-align: left;
}

header {background: black;color:white;}
footer {background: #aaa;color:white;}
.nav {background:#eee;}

.nav ul {
    list-style-type: none;
 padding: 0;
}
   
.nav ul a {
 text-decoration: none;
}

@media all and (min-width: 768px) {
    .nav {text-align:left;-webkit-flex: 1 auto;flex:1 auto;-webkit-order:1;order:1;}
    .article {-webkit-flex:5 0px;flex:5 0px;-webkit-order:2;order:2;}
    footer {-webkit-order:3;order:3;}
}

#contact label{
	display: inline-block;
	width: 100px;
	text-align: right;
}
#submit-div{
	padding-left: 100px;
}
#contact div{
	margin-top: 1em;
}
</style>
</head>
<body>

<div >
<header>
  <h1>File upload Application</h1>
</header>

	<form id = "form" method="POST" action="uploadFile" enctype="multipart/form-data" onsubmit ="return validateForm()">
	
	<div id = "submit-div">
	<label>File to upload:</label>
	 <input type="file" name="file" id="file"><br />
	</div>
		 
	<div id = "submit-div">	 
	<label>Name: </label>
	<input type="text" name="name" id ="name"><br /> <br /> 
	</div>
	 
	<div id = "submit-div">
	<label>Press here to upload the file! </label><input type="submit" value="Upload"> 
	</div>
	
	</form>
	<footer>footer @FileUpload A11pplication</footer>
</div>

</body>
</html>