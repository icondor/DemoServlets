import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/mathOperationsAjax")
public class MathOperationsAjax extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)  {


        try {
            System.out.println(" enter in servlet ajax");
            PrintWriter out =resp.getWriter();

            String sNr1=req.getParameter("nr1");
            String sNr2=req.getParameter("nr2");
            String sOp=req.getParameter("op");

            int nr1=Integer.parseInt(sNr1);
            int nr2=Integer.parseInt(sNr2);

            double resultValue;
            resultValue = MathOperationsBusinessLogic.getResultValue(sOp, nr1, nr2);

            System.out.println("result in servlet ajax is:"+resultValue);
            out.println("Result is: <b>"+resultValue+"</b>");

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