import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/add-contact")
public class AddContactServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)  {
        System.out.println("enter in servlet remove contact");
        try {
            PrintWriter out =resp.getWriter();

            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            String phone = req.getParameter("phone");

            DBOper.insertContact(firstName, lastName, phone);

            out.println("Contact success add.");

            out.close();
        } catch (Exception e) {
            System.out.println("ups, there is an exception here:");
            e.printStackTrace();
            PrintWriter out = null;
            try {
                out = resp.getWriter();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            out.println("There was an error while computing the result, sorry </b>");
            out.close();
            //   // do not do this :) System.exit(0);
        }

    }

}