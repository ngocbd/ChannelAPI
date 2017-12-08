package com.javamonday;

import java.io.IOException;
import javax.servlet.http.*;

import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class ChannelAPIServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		UserService userService = UserServiceFactory.getUserService();
		
		
		String thisURL = req.getRequestURI();
		if (req.getUserPrincipal() != null) {
            resp.getWriter().println("<p>Hello, " +
                                     req.getUserPrincipal().getName() +
                                     "!  You can <a href=\"" +
                                     userService.createLogoutURL(thisURL) +
                                     "\">sign out</a>.</p>");
            

   		 // Game creation, user sign-in, etc. omitted for brevity.
   		
   	    String userId = userService.getCurrentUser().getUserId();
   	    System.out.println(userId);

   	    ChannelService channelService = ChannelServiceFactory.getChannelService();

   	    // The 'Game' object exposes a method which creates a unique string based on the game's key
   	    // and the user's id.
   	    String token = channelService.createChannel(userService.getCurrentUser().getEmail());
   	 
   	    // Index is the contents of our index.html resource, details omitted for brevity.
   	    
   	    req.setAttribute("token", token);
   	    try {
   			req.getRequestDispatcher("/index.jsp").forward(req, resp);
   		} catch (Exception e) {
   			// TODO: handle exception
   			e.printStackTrace();
   		}
   	
        } else {
            resp.getWriter().println("<p>Please <a href=\"" +
                                     userService.createLoginURL(thisURL) +
                                     "\">sign in</a>.</p>");
        }
    }
		
}
