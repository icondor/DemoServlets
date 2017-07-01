import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/mathOperationsTraditional")
public class MathOperationsTraditional extends HttpServlet {



    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {

//        try {
//            Context env = (Context) new InitialContext().lookup("java:comp/env");
//            String docPath = (String) env.lookup("docPath");
//            System.out.println(docPath);
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }

        try {
            System.out.println(" enter in servlet traditional");
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter out =resp.getWriter();

            String sNr1=req.getParameter("nr1");
            String sNr2=req.getParameter("nr2");
            String sOp=req.getParameter("op");

            int nr1=Integer.parseInt(sNr1);
            int nr2=Integer.parseInt(sNr2);

            double resultValue;
            resultValue = MathOperationsBusinessLogic.getResultValue(sOp, nr1, nr2);

            System.out.println("result in servlet traditional is:"+resultValue);

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Result </title>");
            out.println("</head>");

            out.println("<body>");


            out.println("Result is: <b>"+resultValue+"</b> <br><a href='index_traditional.html'>Back</a>");

            out.close();


            out.println("</body>");
        } catch (IOException e) { // not handling a pretty response, and not treating all the errors here
            System.out.println("ups, there is an exception here :");
            e.printStackTrace();
            // do not do this :) System.exit(0);
        }
    }
}