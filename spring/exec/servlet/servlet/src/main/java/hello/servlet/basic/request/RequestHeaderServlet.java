package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "requestheaderservlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //req.getHeaderNames().asIterator().forEachRemaining(headerName -> System.out.println("h = " + headerName));
        System.out.println("req.getMethod() = " + req.getLocale());
        System.out.println("host = " + req.getHeader("host"));
        req.getLocales().asIterator().
                forEachRemaining(headerName -> System.out.println("h = " + headerName));

    }
}
