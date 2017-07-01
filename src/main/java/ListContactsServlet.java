import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/get-contacts")
public class ListContactsServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)  {
        System.out.println("enter in servlet remove contact");
        try {
            PrintWriter out =resp.getWriter();

            List<Conctact> contacts = DBOper.getContacts();
            System.out.println(contacts);

            String json = "[";

            // iter
            for (Conctact contact : contacts) {
                json += "{\"id\": "+ contact.getId() +
                        ", \"phone\": \""+contact.getPhone() +
                        "\", \"nume\": \"" + contact.getLastName() +
                        "\", \"prenume\": \"" + contact.getFirstName() +
                        "\"},";
            }
            json = json.substring(0, json.length() - 1);

            json += "]";

            out.println(json);

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