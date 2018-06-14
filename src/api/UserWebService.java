package api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import classes.*;


@Path("/users")
public class UserWebService {
	
	UserDAO dao = UserDAO.getInstance();
	
	@GET
	@Produces("application/json")
	@Path("/{username}")
	public User getUserbyUsername(@PathParam("username") String username) {
		User user = new User();
		user = dao.findUser(username);
		return user;
	}

}
