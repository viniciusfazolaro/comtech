package br.edu.ifsp.arq.tsi.arqweb2.assistencia.model.dao;

import br.edu.ifsp.arq.tsi.arqweb2.assistencia.model.PaymentMethod;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentMethodDao {

    private final DataSource dataSource;

    public PaymentMethodDao(DataSource dataSource) {
        super();
        this.dataSource = dataSource;
    }

    public List<PaymentMethod> getAllPaymentMethods() {
        List<PaymentMethod> paymentMethods = new ArrayList<>();

        String sql = "select * from paymentMethod";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            try(ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    PaymentMethod paymentMethod = new PaymentMethod();
                    paymentMethod.setId(rs.getLong("id"));
                    paymentMethod.setName(rs.getString("name"));
                    paymentMethods.add(paymentMethod);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro durante a consulta", e);
        }
        return paymentMethods;
    }

    public PaymentMethod getPaymentMethodById(Long id) {
        String sql = "select * from paymentMethod where id=?";
        PaymentMethod paymentMethod = null;
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    paymentMethod = new PaymentMethod();
                    paymentMethod.setId(rs.getLong("id"));
                    paymentMethod.setName(rs.getString("name"));
                }
                return paymentMethod;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro durante a consulta", e);
        }
    }

    public Boolean save(PaymentMethod pm) {
        String sql = "insert into PaymentMethod (name) values (?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, pm.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro durante a escrita no BD", e);
        }
        return true;
    }

    public Boolean update(PaymentMethod pm) {
        String sql = "update PaymentMethod set name=? where id=?";
        try(Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, pm.getName());
            ps.setLong(2, pm.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar dados", e);
        }
        return true;
    }

    public Boolean delete(PaymentMethod pm) {
        String sql = "select count(*) from ServiceOrder where paymentMethodId=?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, pm.getId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar ordens de serviço associadas", e);
        }


        sql = "delete from PaymentMethod where id=?";
        try(Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, pm.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar dados", e);
        }
    }
}
