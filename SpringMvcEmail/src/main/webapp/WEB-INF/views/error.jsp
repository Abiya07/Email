<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <title>Spring MVC Email Example</title>
	    <style type="text/css">
    		#errorMessage {
    			text-align: center; 
    			font-size: 25px; 
    			padding-top: 17px;
    		}
    		
    		#errorMessage span {
    			color: black;
    		}
    	</style>
	</head>

	<body>
	    <center>
	      <h5>Spring MVC Email Example</h5> 
	    </center>
	    <br /> <br />
	    <div id="errorMessage">
	        <h4>Sorry, The Email Was Not Sent!</h4>
	        <span id="exceptionTrace"><h6>${exception.message}</h6></span>
	    </div>
	</body>
</html>