package br.edu.ifsp.arq.tsi.arqweb2.assistencia.model.dao;

import br.edu.ifsp.arq.tsi.arqweb2.assistencia.model.User;
import br.edu.ifsp.arq.tsi.arqweb2.assistencia.utils.PasswordEncoder;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

public class UserDao {

    private DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Boolean save(User user){
        Optional<User> optional = getUserByEmail(user.getEmail());
        if(optional.isPresent()) {
            return false;
        }
        String sql = "insert into user (name, email, password, "
                + "dateOfBirth, gender, active) values (?,?,?,?,?,?)";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setDate(4, Date.valueOf(user.getDateOfBirth()));
            ps.setString(5, user.getGender().toString());
            ps.setBoolean(6, true);
            ps.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException("Erro durante a escrita no BD", e);
        }
        return true;
    }

    public Optional<User> getUserByEmail(String email){
        String sql = "select id,name,email from user where email=?";
        Optional<User> optional = Optional.empty();
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, email);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    User user = new User();
                    user.setId(rs.getLong(1));
                    user.setName(rs.getString(2));
                    user.setEmail(rs.getString(3));
                    optional = Optional.of(user);
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException("Erro durante a consulta no BD", e);
        }
        return optional;
    }

    public Optional<User> getUserByEmailAndPassword(String email, String password) {
        String passwordEncripted = PasswordEncoder.encode(password);

        String sql = "select id,name,email from user where email=? and password=?";
        Optional<User> optional = Optional.empty();
        try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, passwordEncripted);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getLong(1));
                    user.setName(rs.getString(2));
                    user.setEmail(rs.getString(3));
                    optional = Optional.of(user);
                }
            }
            return optional;
        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro durante a consulta no BD", sqlException);
        }
    }

}