package br.edu.ifsp.arq.tsi.arqweb2.assistencia.servlets;

import br.edu.ifsp.arq.tsi.arqweb2.assistencia.model.Client;
import br.edu.ifsp.arq.tsi.arqweb2.assistencia.model.dao.ClientDao;
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

@WebServlet("/clientList")
public class ClientListServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 1L;

    public ClientListServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url;

        ClientDao clientDao = new ClientDao(DataSourceSearcher.getInstance().getDataSource());

        List<Client> clients = clientDao.getAllClients();

        if (clients.isEmpty()) req.setAttribute("clients", null);
        else req.setAttribute("clients", clients);

        url = "/client-list.jsp";

        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
