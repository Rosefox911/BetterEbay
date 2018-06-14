<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII" import="api.*, classes.*, java.sql.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Login Successful</title>
<style type="text/css">
body {
    background-image: url('http://crunchify.com/bg.png');
}
</style>
</head>
 
<body>
    <div class="container">
        <%
       		UserDAO dao = UserDAO.getInstance();
        	User curUser = new User();
            String userName = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("User")) {
                        userName = cookie.getValue();
                    	curUser = dao.findUser(userName);
                    	break;
                    }
                }
            }
            
            String targetUser = request.getParameter("targetUser");
           // System.out.println(targetUser);
            User target = dao.findUser(targetUser);
            FollowDAO followDAO = FollowDAO.getInstance();
            Follow f = followDAO.findFollow(userName, targetUser);
            if (userName == null || dao.findUser(targetUser) == null) {
                response.sendRedirect("homepage.jsp");
            }
            if (f == null) {
            	%> 
            	<form method="post" action="FollowUserServlet" name="inputpage">
            	<input name="hidden" type="hidden" value=<%=targetUser%>>
            	<INPUT TYPE="submit" Value="Follow" NAME="submit" title="follow">
            	<%
            } else {
            	%> 
            	<form method="post" action="FollowUserServlet" name="inputpage">
            	<input name="hidden" type="hidden" value=<%=targetUser%>>
            	<INPUT TYPE="submit" Value="unFollow" NAME="submit" title="unFollow">
            	<%
            }
            
            if (userName != null && dao.findUser(targetUser) != null) {
                %> <div class="avatar">
    	        <img src=<%= target.getAvatar()%>>
    	        <p> Name </p> <%= target.getFirstname() %> <%= target.getLastname() %>
    	        <p>Biography: </p> <%= target.getBio() %>
    	        <p></p>
            </div>
            <%
            }
            %>
  
        
        

   <!-- <h3> Hi <%=userName%>, Login successful. </h3> <br> -->     
 
        <form action="LogOutServlet" method="post">
            <input type="submit" value="Logout">
        </form>
    </div>
</body>
</html>