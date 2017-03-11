<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
<form id="frm" name="frm" enctype="multipart/form-data" action="./test" method="post">
	<input type="file" name="picture">
	<input type="submit">
</form>
<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
