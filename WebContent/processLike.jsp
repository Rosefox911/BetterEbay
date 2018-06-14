<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="api.*, classes.*, java.sql.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%String id = request.getParameter("id");

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
LikeDAO likeDao = LikeDAO.getInstance();
//System.out.println("hahaha");
if (likeDao.userLiked(userName, id) == null) {
	likeDao.createLike(userName, id); // create a new like
} else {
	likeDao.unLike(userName, id);
}
response.sendRedirect("QueryResults.jsp");

%>
<p><%=id %></p>
<%System.out.print("Id of the product" + id); %>
</body>
</html>