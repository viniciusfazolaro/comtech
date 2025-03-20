package br.edu.ifsp.arq.tsi.arqweb2.assistencia.model.dao;

import br.edu.ifsp.arq.tsi.arqweb2.assistencia.model.ServiceOrder;
import br.edu.ifsp.arq.tsi.arqweb2.assistencia.model.Status;
import br.edu.ifsp.arq.tsi.arqweb2.assistencia.utils.DataSourceSearcher;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ServiceOrderDao {

    private final DataSource dataSource;

    public ServiceOrderDao(DataSource dataSource) {
        super();
        this.dataSource = dataSource;
    }

    public List<ServiceOrder> getAllServiceOrders() {
        List<ServiceOrder> orders = new ArrayList<>();

        String sql = "select * from ServiceOrder";
        try(Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            try(ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    ServiceOrder so = new ServiceOrder();
                    setServiceOrder(so, rs);
                    orders.add(so);
                }
            }
        } catch(SQLException e) {
            throw new RuntimeException("Erro durante a consulta", e);
        }
        return orders;
    }

    public Boolean save(ServiceOrder so) {

        String sql = "insert into ServiceOrder (description, emissionDate, finishDate," +
                "value, observation, status, paymentMethodId, clientId) " +
                "values(?,?,?,?,?,?,?,?)";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            setParameters(so, ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro durante a escrita no BD", e);
        }
        return true;
    }

    public Boolean update(ServiceOrder so) {
        String sql = "update ServiceOrder " +
                "set description = ?," +
                "emissionDate = ?," +
                "finishDate = ?," +
                "value = ?," +
                "observation = ?," +
                "status = ?," +
                "paymentMethodId = ?," +
                "clientId = ? " +
                "where id = ?";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            setParameters(so, ps);
            ps.setLong(9, so.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro durante a escrita no BD", e);
        }
        return true;
    }

    public Boolean delete(ServiceOrder so) {
        String sql = "delete from ServiceOrder where id = ?";
        try(Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setLong(1, so.getId());
            ps.executeUpdate();
            return true;
        } catch(SQLException e) {
            throw new RuntimeException("Erro ao deletar dados no BD", e);
        }
    }

    public ServiceOrder getServiceOrderById(Long id) {
        String sql = "select * from ServiceOrder where id=?";
        ServiceOrder so = null;
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    so = new ServiceOrder();
                    setServiceOrder(so, rs);
                }
                return so;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro durante a consulta", e);
        }
    }

    private void setServiceOrder(ServiceOrder so, ResultSet rs) throws SQLException {
        so.setId(rs.getLong("id"));
        so.setDescription(rs.getString("description"));
        so.setEmissionDate(rs.getDate("emissionDate").toLocalDate());
        so.setFinishDate(rs.getDate("finishDate").toLocalDate());
        so.setValue(rs.getBigDecimal("value"));
        so.setObservation(rs.getString("observation"));
        so.setStatus(Status.valueOf(rs.getString("status")));
        PaymentMethodDao pmDao = new PaymentMethodDao(DataSourceSearcher.getInstance().getDataSource());
        so.setPaymentMethod(pmDao.getPaymentMethodById(rs.getLong("paymentMethodId")));
        ClientDao clientDao = new ClientDao(DataSourceSearcher.getInstance().getDataSource());
        so.setClient(clientDao.getClientById(rs.getLong("clientId")));
    }

    private void setParameters(ServiceOrder so, PreparedStatement ps) throws SQLException {
        ps.setString(1, so.getDescription());
        ps.setDate(2, Date.valueOf(so.getEmissionDate()));
        ps.setDate(3, Date.valueOf(so.getFinishDate()));
        ps.setBigDecimal(4, so.getValue());
        ps.setString(5, so.getObservation());
        ps.setString(6, so.getStatus().toString());
        ps.setLong(7, so.getPaymentMethod().getId());
        ps.setLong(8, so.getClient().getId());
    }
}
