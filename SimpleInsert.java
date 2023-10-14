import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Info;
import util.UtilDBSchweikert;

@WebServlet("/SimpleInsert")
public class SimpleInsert extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public SimpleInsert() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String prodName = request.getParameter("prodName").trim();
      String prodSize = request.getParameter("prodSize").trim();
      String prodTime = request.getParameter("prodTime").trim();
      String prodCost = request.getParameter("prodCost").trim();
      UtilDBSchweikert.createProducts(prodName, prodSize, prodTime, prodCost);

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      out.println("<ul>");
      out.println("<li> Product name: " + prodName);
      out.println("<li> Size: " + prodSize);
      out.println("<li> Hours required to crochet: " + prodTime);
      out.println("<li> Cost (in $): " + prodCost);
      out.println("</ul>");
      out.println("<a href=/" + projectName + "/" + searchWebName + ">Search Data</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
