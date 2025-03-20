package br.edu.ifsp.arq.tsi.arqweb2.assistencia.servlets;

import br.edu.ifsp.arq.tsi.arqweb2.assistencia.model.PaymentMethod;
import br.edu.ifsp.arq.tsi.arqweb2.assistencia.model.dao.PaymentMethodDao;
import br.edu.ifsp.arq.tsi.arqweb2.assistencia.utils.DataSourceSearcher;
import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;

@WebServlet("/paymentMethodRegister")
public class PaymentMethodRegisterServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 1L;

    public PaymentMethodRegisterServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");

        PaymentMethod pm = new PaymentMethod();
        pm.setName(name);

        RequestDispatcher dispatcher;

        PaymentMethodDao paymentMethodDao = new PaymentMethodDao(DataSourceSearcher.getInstance().getDataSource());

        if(id == 0) {
            if(paymentMethodDao.save(pm)) req.setAttribute("result", "registered");
        } else {
            pm.setId(id);
            if(paymentMethodDao.update(pm)) req.setAttribute("result", "updated");
        }

        String url = "/payment-method-register.jsp";

        dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Long id = Long.parseLong(req.getParameter("method-id"));
        String url = null;

        PaymentMethodDao paymentMethodDao = new PaymentMethodDao(DataSourceSearcher.getInstance().getDataSource());
        PaymentMethod paymentMethod = paymentMethodDao.getPaymentMethodById(id);
        RequestDispatcher dispatcher;

        if(paymentMethod != null) {
            switch(action) {
            case "update":
                req.setAttribute("paymentMethod", paymentMethod);
                url = "/payment-method-register.jsp";
                dispatcher = req.getRequestDispatcher(url);
                dispatcher.forward(req, resp);
                break;
            case "remove":
                Boolean response = paymentMethodDao.delete(paymentMethod);
                Gson gson = new Gson();
                String json = gson.toJson(response);
                resp.setContentType("application/json");
                resp.getWriter().write(json);
                break;
            }
        } else {
            url = "/paymentMethodRegister";
            dispatcher = req.getRequestDispatcher(url);
            dispatcher.forward(req, resp);
        }
    }
}
