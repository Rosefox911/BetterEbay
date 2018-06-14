package api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.UserDAO;
import classes.User;
 

/**
 * Servlet implementation class LoginServlet
 */
 
@WebServlet("/SearchUserServlet")
public class SearchUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final String userID = "User";
    private final String password = "Password";
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
    	UserDAO dao = UserDAO.getInstance();
    	 
        // get request parameters for userID and password
        String targetUser = request.getParameter("targetUser");
 
     //   if (userID.equals(User) && password.equals(pwd)) {
        if (dao.findUser(targetUser) != null) {
            ServletContext sc = getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher("searchUser.jsp");
            request.setAttribute("targetUser", request.getParameter("targetUser"));
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Username not found!</font>\n");
            rd.include(request, response);
        }
 
    }
}