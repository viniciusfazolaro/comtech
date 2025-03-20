package br.edu.ifsp.arq.tsi.arqweb2.assistencia.servlets;

import br.edu.ifsp.arq.tsi.arqweb2.assistencia.model.Address;
import br.edu.ifsp.arq.tsi.arqweb2.assistencia.model.Client;
import br.edu.ifsp.arq.tsi.arqweb2.assistencia.model.dao.ClientDao;
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

@WebServlet("/clientRegister")
public class ClientRegisterServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 1L;

    public ClientRegisterServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // obter dados
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String cpf = req.getParameter("cpf");
        String street = req.getParameter("street");
        String number = req.getParameter("number");
        String complement = req.getParameter("complement");
        String neighborhood = req.getParameter("neighborhood");
        String cep = req.getParameter("cep");
        String city = req.getParameter("city");
        String state = req.getParameter("state");

        // instanciar um objeto Client
        Client client = new Client();
        client.setName(name);
        client.setEmail(email);
        client.setPhone(phone);
        client.setCpf(cpf);

        Address address = new Address();
        address.setStreet(street);
        address.setNumber(number);
        if(!complement.isEmpty()) address.setComplement(complement);
        else address.setComplement(null);
        address.setNeighborhood(neighborhood);
        address.setCep(cep);
        address.setCity(city);
        address.setState(state);

        client.setAddress(address);

        RequestDispatcher dispatcher;

        ClientDao clientDao = new ClientDao(DataSourceSearcher.getInstance().getDataSource());

        // salvar o novo usuário
        if(id == 0) {
            if(!clientDao.save(client)) {
                client.setId(0L);
                req.setAttribute("result", "notRegistered");
                req.setAttribute("client", client);
            } else req.setAttribute("result", "registered");
        } else {
            client.setId(id);
            if(!clientDao.update(client)){
                req.setAttribute("result", "notRegistered");
                req.setAttribute("client", client);
            } else req.setAttribute("result", "updated");
        }
        dispatcher = req.getRequestDispatcher("client-register.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Long id = Long.parseLong(req.getParameter("client-id"));
        String url = null;

        ClientDao clientDao = new ClientDao(DataSourceSearcher.getInstance().getDataSource());
        Client client = clientDao.getClientById(id);
        RequestDispatcher dispatcher;

        if(client != null) {
            switch(action) {
            case "update":
                req.setAttribute("client", client);
                url = "/client-register.jsp";
                dispatcher = req.getRequestDispatcher(url);
                dispatcher.forward(req, resp);
                break;
            case "remove":
                Boolean response = clientDao.delete(client);
                Gson gson = new Gson();
                String json = gson.toJson(response);
                resp.setContentType("application/json");
                resp.getWriter().write(json);
                break;
            }
        } else {
            url = "/clientRegister";
            dispatcher = req.getRequestDispatcher(url);
            dispatcher.forward(req, resp);
        }
    }
}
