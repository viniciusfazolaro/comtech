package br.edu.ifsp.arq.tsi.arqweb2.assistencia.servlets;

import br.edu.ifsp.arq.tsi.arqweb2.assistencia.model.Client;
import br.edu.ifsp.arq.tsi.arqweb2.assistencia.model.PaymentMethod;
import br.edu.ifsp.arq.tsi.arqweb2.assistencia.model.ServiceOrder;
import br.edu.ifsp.arq.tsi.arqweb2.assistencia.model.Status;
import br.edu.ifsp.arq.tsi.arqweb2.assistencia.model.dao.ClientDao;
import br.edu.ifsp.arq.tsi.arqweb2.assistencia.model.dao.PaymentMethodDao;
import br.edu.ifsp.arq.tsi.arqweb2.assistencia.model.dao.ServiceOrderDao;
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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/serviceOrderUpdate")
public class ServiceOrderUpdateServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 1L;

    public ServiceOrderUpdateServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String description = req.getParameter("description");
        LocalDate emissionDate = LocalDate.parse(req.getParameter("emissionDate"));
        LocalDate finishDate = LocalDate.parse(req.getParameter("finishDate"));
        BigDecimal value = BigDecimal.valueOf(Double.parseDouble(req.getParameter("value")));
        String observation = req.getParameter("observation");
        Status status = Status.valueOf(req.getParameter("status"));
        Long clientId = Long.parseLong(req.getParameter("clientId"));
        Long paymentMethodId = Long.parseLong(req.getParameter("paymentMethodId"));

        ServiceOrder so = new ServiceOrder();
        so.setDescription(description);
        so.setEmissionDate(emissionDate);
        so.setFinishDate(finishDate);
        so.setValue(value);
        so.setObservation(observation);
        so.setStatus(status);
        Client client = new Client();
        client.setId(clientId);
        so.setClient(client);
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setId(paymentMethodId);
        so.setPaymentMethod(paymentMethod);

        RequestDispatcher dispatcher;

        ServiceOrderDao serviceOrderDao = new ServiceOrderDao(DataSourceSearcher.getInstance().getDataSource());

        so.setId(id);
        if(serviceOrderDao.update(so)) req.setAttribute("result", "updated");

        String url = "/service-order-update.jsp";

        dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Long id = Long.parseLong(req.getParameter("service-id"));
        String url = null;

        ServiceOrderDao serviceOrderDao = new ServiceOrderDao(DataSourceSearcher.getInstance().getDataSource());
        ClientDao clientDao = new ClientDao(DataSourceSearcher.getInstance().getDataSource());
        PaymentMethodDao paymentMethodDao = new PaymentMethodDao(DataSourceSearcher.getInstance().getDataSource());

        List<Client> clients = clientDao.getAllClients();
        List<PaymentMethod> paymentMethods = paymentMethodDao.getAllPaymentMethods();

        req.setAttribute("clients", clients);
        req.setAttribute("paymentMethods", paymentMethods);

        ServiceOrder serviceOrder = serviceOrderDao.getServiceOrderById(id);
        RequestDispatcher dispatcher;

        switch(action) {
        case "update":
            req.setAttribute("serviceOrder", serviceOrder);
            url = "/service-order-update.jsp";
            dispatcher = req.getRequestDispatcher(url);
            dispatcher.forward(req, resp);
            break;
        case "remove":
            Boolean response = serviceOrderDao.delete(serviceOrder);
            Gson gson = new Gson();
            String json = gson.toJson(response);
            resp.setContentType("application/json");
            resp.getWriter().write(json);
            break;
        }
    }
}
