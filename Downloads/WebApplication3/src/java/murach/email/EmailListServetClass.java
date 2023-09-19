package murach.email;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import murach.business.User;

@WebServlet("/survey")

public class EmailListServetClass extends HttpServlet  {

    @Override 
    protected void doPost(HttpServletRequest request, 
                          HttpServletResponse response) 
                          throws ServletException, IOException {

        String url = "/index.html";

        // Get the state of emailOK and wantsUpdates checkboxes
        boolean emailOK = "on".equals(request.getParameter("emailOK"));
        boolean wantsUpdates = "on".equals(request.getParameter("wantsUpdates"));

        // Perform action and set URL to appropriate page
        if (emailOK && wantsUpdates) {
            request.setAttribute("message", "We will send you email announcements and thank you for joining.");
        } else {
            request.setAttribute("message", "Thank you for joining.");
        }

        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "join";  // default action
        }
// perform action and set URL to appropriate page
        if (action.equals("join")) {
            url = "/index.html";    // the "join" page
        }
        else if (action.equals("Submit")) {                
            // get parameters from the request
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String dateOfBirth  = request.getParameter("dateOfBirth");
            String heardFrom = request.getParameter("heardFrom");
            
            String contactVia = request.getParameter("contactVia");        
            // store data in User object and save User object in db
            User user = new User(firstName, lastName, email, dateOfBirth,heardFrom,contactVia);
            
            
            // set User object in request object and set URL
            request.setAttribute("user", user);
            url = "/thanks.jsp";   // the "thanks" page
        }
        
        // forward request and response objects to specified URL
        getServletContext()
            .getRequestDispatcher(url)
            .forward(request, response);
    }    
    @Override
    protected void doGet(HttpServletRequest request, 
                         HttpServletResponse response) 
                         throws ServletException, IOException {
        doPost(request, response);
    }    
}
