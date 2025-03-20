package br.edu.ifsp.arq.tsi.arqweb2.assistencia.model.dao;

import br.edu.ifsp.arq.tsi.arqweb2.assistencia.model.Address;
import br.edu.ifsp.arq.tsi.arqweb2.assistencia.model.Client;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDao {

    private final DataSource dataSource;

    public ClientDao(DataSource dataSource) {
        super();
        this.dataSource = dataSource;
    }

    public Optional<Client> getClientByCpf(String cpf, Long id) {
        String sql = null;

        if(id == null) sql = "select * from client where cpf = ?";
        else sql = "select cpf from client where cpf=? and id != ?";
        Optional<Client> optional = Optional.empty();
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, cpf);
            if(id != null) ps.setLong(2, id);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    Client client = new Client();
                    client.setCpf(rs.getString(1));

                    optional = Optional.of(client);
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException("Erro durante a consulta no BD", e);
        }
        return optional;
    }

    public Client getClientById(Long id) {
        String sql = "select * from Client where id=?";
        Client client = null;
        try(Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    client = new Client();
                    getClient(client, rs);
                }
                return client;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro durante a consulta", e);
        }
    }

    private void getClient(Client client, ResultSet rs) throws SQLException {
        client.setId(rs.getLong("id"));
        client.setName(rs.getString("name"));
        client.setEmail(rs.getString("email"));
        client.setPhone(rs.getString("phone"));
        client.setCpf(rs.getString("cpf"));

        Address address = new Address();
        address.setStreet(rs.getString("addressStreet"));
        address.setNumber(rs.getString("addressNumber"));
        address.setComplement(rs.getString("addressComplement"));
        address.setNeighborhood(rs.getString("addressNeighborhood"));
        address.setCep(rs.getString("addressCep"));
        address.setCity(rs.getString("addressCity"));
        address.setState(rs.getString("addressState"));
        client.setAddress(address);
    }

    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();

        String sql = "select * from Client";
        try(Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            try(ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    Client client = new Client();
                    getClient(client, rs);
                    clients.add(client);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro durante a consulta", e);
        }
        return clients;
    }

    public Boolean save(Client client){
        Optional<Client> optional = getClientByCpf(client.getCpf(), client.getId());
        if(optional.isPresent()) return false;

        String sql = "insert into Client (name, email, phone, cpf, enabled, addressStreet, addressNumber, " +
                "addressComplement, addressNeighborhood, addressCep, addressCity, addressState)" +
                " values (?,?,?,?,?,?,?,?,?,?,?,?)";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, client.getName());
            ps.setString(2, client.getEmail());
            ps.setString(3, client.getPhone());
            ps.setString(4, client.getCpf());
            ps.setBoolean(5, true);
            ps.setString(6, client.getAddress().getStreet());
            ps.setString(7, client.getAddress().getNumber());
            ps.setString(8, client.getAddress().getComplement());
            ps.setString(9, client.getAddress().getNeighborhood());
            ps.setString(10, client.getAddress().getCep());
            ps.setString(11, client.getAddress().getCity());
            ps.setString(12, client.getAddress().getState());
            ps.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException("Erro durante a escrita no BD", e);
        }
        return true;
    }

    public Boolean update(Client client) {
        Optional<Client> optional = getClientByCpf(client.getCpf(), client.getId());
        if(optional.isPresent()) return false;
        String sql = "update Client " +
                "set name = ?," +
                "email = ?," +
                "phone = ?," +
                "cpf = ?," +
                "enabled = true," +
                "addressStreet = ?," +
                "addressNumber = ?," +
                "addressComplement = ?," +
                "addressNeighborhood = ?," +
                "addressCep = ?," +
                "addressCity = ?," +
                "addressState = ?" +
                "where id = ?";

        try(Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, client.getName());
            ps.setString(2, client.getEmail());
            ps.setString(3, client.getPhone());
            ps.setString(4, client.getCpf());
            ps.setString(5, client.getAddress().getStreet());
            ps.setString(6, client.getAddress().getNumber());
            ps.setString(7, client.getAddress().getComplement());
            ps.setString(8, client.getAddress().getNeighborhood());
            ps.setString(9, client.getAddress().getCep());
            ps.setString(10, client.getAddress().getCity());
            ps.setString(11, client.getAddress().getState());
            ps.setLong(12, client.getId());
            ps.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException("Erro ao atualizar dados", e);
        }
        return true;
    }

    public Boolean delete(Client client) {
        String sql = "delete from ServiceOrder where clientId = ?";
        try(Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, client.getId());
            ps.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException("Erro ao remover dados", e);
        }

        sql = "delete from Client where id = ?";
        try(Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, client.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover dados", e);
        }

    }
}
