import ru.shikhov.product.Product;
import ru.shikhov.product.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns = "/products/*")
public class ProductTable extends HttpServlet {
    private ProductRepository productRepository;

    @Override
    public void init() {
        this.productRepository = new ProductRepository();
        this.productRepository.insert(new Product("Watermelon", 1000000));
        this.productRepository.insert(new Product("Apple", 10));
        this.productRepository.insert(new Product("Pear", 50));
        this.productRepository.insert(new Product("Pepper", 70));
        this.productRepository.insert(new Product("Potato", 70));
        this.productRepository.insert(new Product("Milk", 140));
        this.productRepository.insert(new Product("Egg", 30));
        this.productRepository.insert(new Product("Mango", 65));
        this.productRepository.insert(new Product("Lemon", 20));
        this.productRepository.insert(new Product("Onion", 60));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<table>");
        writer.println("<tr>");
        writer.println("<th>id</th>");
        writer.println("<th>title</th>");
        writer.println("<th>cost</th>");
        writer.println("</tr>");

        if (req.getPathInfo() == null) {
            for (Product product : productRepository.findAll()) {
                writer.println("<tr>");
                writer.println("<td><a href='" + getServletContext().getContextPath() + "/products/" + product.getId() + "'>" + product.getId() + "</a></td>");
                writer.println("<td>" + product.getTitle() + "</td>");
                writer.println("<td>" + product.getCost() + "</td>");
                writer.println("</tr>");
            }
        } else {
           int id = Integer.parseInt(req.getPathInfo().split("/")[1]);
            writer.println("<tr>");
            writer.println("<td>" + productRepository.findById(id).getId() + "</td>");
            writer.println("<td>" + productRepository.findById(id).getTitle() + "</td>");
            writer.println("<td>" + productRepository.findById(id).getCost() + "</td>");
            writer.println("</tr>");
        }
        writer.println("</table>");
    }
}
