package br.edu.ifsp.arq.tsi.arqweb2.assistencia.servlets;

import br.edu.ifsp.arq.tsi.arqweb2.assistencia.model.ServiceOrder;
import br.edu.ifsp.arq.tsi.arqweb2.assistencia.model.dao.ServiceOrderDao;
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

@WebServlet("/serviceOrderList")
public class ServiceOrderListServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 1L;

    public ServiceOrderListServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url;

        ServiceOrderDao soDao = new ServiceOrderDao(DataSourceSearcher.getInstance().getDataSource());

        List<ServiceOrder> orders = soDao.getAllServiceOrders();

        if(orders.isEmpty()) req.setAttribute("orders", null);
        else req.setAttribute("orders", orders);

        url = "/service-order-list.jsp";

        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
