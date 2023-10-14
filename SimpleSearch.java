import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Product;
import util.Info;
import util.UtilDBSchweikert;

@WebServlet("/SimpleSearch")
public class SimpleSearch extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public SimpleSearch() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String keyword = request.getParameter("keyword").trim();

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

      List<Product> listProducts = null;
      if (keyword != null && !keyword.isEmpty()) {
         listProducts = UtilDBSchweikert.listProducts(keyword);
      } else {
         listProducts = UtilDBSchweikert.listProducts();
      }
      display(listProducts, out);
      out.println("</ul>");
      out.println("<a href=/" + projectName + "/" + searchWebName + ">Search Data</a> <br>");
      out.println("</body></html>");
   }

   void display(List<Product> listProducts, PrintWriter out) {
      for (Product product : listProducts) {
         System.out.println("[DBG] " + product.getId() + ", " //
               + product.getName() + ", " //
               + product.getSize() + ", " //
               + product.getTime() + ", " //
               + product.getCost()); 

         out.println("<li>" + product.getId() + ", " //
               + product.getName() + ", " //
               + product.getSize() + ", " //
               + product.getTime() + ", " // 
               + product.getCost() + "</li>");
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
