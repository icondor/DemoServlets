import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/demoSecurity")
public class DemoSecurity extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {

        try {
            System.out.println(" enter in security demo");
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter out = resp.getWriter();

            String killTomcat = req.getParameter("killTomcat");
            String inPath = req.getParameter("inPath");
            String inFile = req.getParameter("inFile");
            String outPath = req.getParameter("outPath");
            String outFile = req.getParameter("outFile");


            System.out.println("killTomcat:"+killTomcat);
            if(killTomcat!=null && killTomcat.equals("true"))
            {
                System.out.println("killing ... ");
                System.exit(0);
            }


            out.println("<html>");
            out.println("<head>");
            out.println("<title>Demo Security</title>");
            out.println("</head>");

            out.println("<body>");
            List<String> lines = new ArrayList<>();



            Path pathI = Paths.get(inPath, inFile); // refer o cale spre un fisier de pe disc 2

            Charset charset = Charset.forName("UTF-8"); // definesc un set de charactere 3

            lines = Files.readAllLines(pathI, charset);


            for (String s : lines) {
                System.out.println(s);
                out.println(" <b>" + s + "</b>");
            }


            Path pathO = Paths.get(outPath, outFile);


            if (Files.exists(pathO)) {
                Files.write(pathO, lines, StandardOpenOption.APPEND);
                out.println(" <b>" + "append file written correctly" + "</b>");
                System.out.println("entered in append");

            } else {

                Files.write(pathO, lines); // asa scriu in fisier o lista

                out.println(" <b>" + "new file written correctly" + "</b>");
                //Files.write(pathO, "ana are mere".getBytes()); // asa scriu un String

                System.out.println("entered on new ");
            }


            out.close();


            out.println("</body>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}