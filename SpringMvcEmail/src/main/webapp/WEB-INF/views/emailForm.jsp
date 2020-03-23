 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
 
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	    <title>Spring MVC Email</title>
	    <style type="text/css">
	    	#sendEmailBtn {
				float: left;
    			margin-top: 22px;
    		}
    		
    	</style>
	</head>

	<body>
	    <center>
	
     <h2><b>SEND MAIL</b></h2>
    <b>
    
	        <form id="sendEmailForm" method="post" action="sendEmail" enctype="multipart/form-data">
	            
	            <table id="emailFormBeanTable" border="0" width="80%">
	                <tr>
	                    <td>Email To: </td>
	                    <td><input id="receiverMail" type="text" name="mailTo" size="65" class="form-control" placeholder="Enter Email"/></td>
	                </tr>
	                
	                <tr>
	                    <td>Subject: </td>
	                    <td><input id="mailSubject" type="text" name="subject" size="65" class="form-control" placeholder="Enter Subject" /></td>
	                </tr>
	                
	                <tr>
	                    <td>Message: </td>
	                    <td><textarea id="mailMessage" cols="50" rows="10" name="message" class="form-control" placeholder="Enter Message..."></textarea></td>
	                </tr>
	                
	                <tr>
	                    <td>Attachment: </td>
	                    <td><input id="mailAttachment" type="file" name="attachFileObj" size="60" class="btn btn-info" /></td>
	                </tr>
	                <tr>
	                    <td colspan="2" align="center"><input id="sendEmailBtn" type="submit" class="btn btn-info" value="Send Email" /></td>
	                </tr>
	            </table>
	        </form></b>
	    </center>
	</body>
</html>