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

import classes.FollowDAO;
import classes.UserDAO;
import classes.User;
 

/**
 * Servlet implementation class LoginServlet
 */
 
@WebServlet("/FollowUserServlet")
public class FollowUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final String userID = "User";
    private final String password = "Password";
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
    	FollowDAO followDAO = FollowDAO.getInstance();
    	UserDAO dao = UserDAO.getInstance();
    	
        // get request parameters for userID and password
        String targetUser = request.getParameter("hidden");
        System.out.println(targetUser);
        String userName = null;
        User curUser = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(userID)) {
                    userName = cookie.getValue();
                	curUser = dao.findUser(userName);
                	break;
                }
            }
        }
        
        if (userName == null || curUser == null) {
        	RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>User timmed out!</font>\n");
            rd.include(request, response);
        }
        
        if (userName.equals(targetUser)) {
        	ServletContext sc = getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher("/homepage.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=blue>User cannot follow himself!</font>\n");
            rd.forward(request, response);
        }
        
        if (followDAO.findFollow(userName, targetUser) != null) {
        	
        	followDAO.unFollow(userName, targetUser);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/homepage.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>User unFollowed.</font>\n");
            rd.forward(request, response);
        } else {
        	
        	followDAO.createFollow(userName, targetUser);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/homepage.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>User followed!</font>\n");
            rd.include(request, response);
        }
 
    }
}