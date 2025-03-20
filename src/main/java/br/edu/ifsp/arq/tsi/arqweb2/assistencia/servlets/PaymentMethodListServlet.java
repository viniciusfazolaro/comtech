package br.edu.ifsp.arq.tsi.arqweb2.assistencia.servlets;

import br.edu.ifsp.arq.tsi.arqweb2.assistencia.model.PaymentMethod;
import br.edu.ifsp.arq.tsi.arqweb2.assistencia.model.dao.PaymentMethodDao;
import br.edu.ifsp.arq.tsi.arqweb2.assistencia.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;
import java.util.List;

@WebServlet("/paymentMethodList")
public class PaymentMethodListServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 1L;

    public PaymentMethodListServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url;

        PaymentMethodDao pmDao = new PaymentMethodDao(DataSourceSearcher.getInstance().getDataSource());

        List<PaymentMethod> methods = pmDao.getAllPaymentMethods();

        if(methods.isEmpty()) req.setAttribute("methods", null);
        else req.setAttribute("methods", methods);

        url = "/payment-method-list.jsp";

        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
