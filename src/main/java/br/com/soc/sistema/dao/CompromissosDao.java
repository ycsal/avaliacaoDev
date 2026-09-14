package br.com.soc.sistema.dao;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.soc.sistema.vo.CompromissosVo;

public class CompromissosDao extends Dao {

    public void insertCompromisso(CompromissosVo compromisso) {
        StringBuilder query = new StringBuilder("INSERT INTO compromisso " + "(cd_funcionario, cd_agenda, dt_compromisso, hr_compromisso) " + "VALUES (?, ?, ?, ?)");

        try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(
        	    query.toString(),
        	    java.sql.Statement.RETURN_GENERATED_KEYS
        	)) 
        {
            int i = 1;

            ps.setInt(i++, Integer.parseInt(compromisso.getCodigoFuncionario()));
            ps.setInt(i++, Integer.parseInt(compromisso.getCodigoAgenda()));
            ps.setString(i++, compromisso.getData());
            ps.setString(i++, compromisso.getHorario());

            ps.executeUpdate();
            
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    compromisso.setRowid(rs.getString(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public CompromissosVo findCompromissoPor(String rowid) {
        StringBuilder query = new StringBuilder("SELECT rowid, cd_funcionario, cd_agenda, " + "dt_compromisso, hr_compromisso " + "FROM compromisso WHERE rowid = ?");

        try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) 
        {
            ps.setInt(1, Integer.parseInt(rowid));

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    CompromissosVo compromisso = new CompromissosVo();

                    compromisso.setRowid(rs.getString("rowid"));
                    compromisso.setCodigoFuncionario(
                        rs.getString("cd_funcionario")
                    );
                    compromisso.setCodigoAgenda(
                        rs.getString("cd_agenda")
                    );
                    compromisso.setData(
                        rs.getString("dt_compromisso")
                    );
                    compromisso.setHorario(
                        rs.getString("hr_compromisso")
                    );

                    return compromisso;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public void updateCompromisso(CompromissosVo compromisso) {

        StringBuilder query = new StringBuilder(
            "UPDATE compromisso " +
            "SET cd_funcionario = ?, " +
            "cd_agenda = ?, " +
            "dt_compromisso = ?, " +
            "hr_compromisso = ? " +
            "WHERE rowid = ?"
        );

        try (
            Connection con = getConexao();
            PreparedStatement ps = con.prepareStatement(query.toString())
        ) {
            int i = 1;

            ps.setInt(i++, Integer.parseInt(
                compromisso.getCodigoFuncionario()
            ));

            ps.setInt(i++, Integer.parseInt(
                compromisso.getCodigoAgenda()
            ));

            ps.setString(i++, compromisso.getData());
            ps.setString(i++, compromisso.getHorario());

            ps.setInt(i++, Integer.parseInt(
                compromisso.getRowid()
            ));

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteCompromisso(String rowid) {

        StringBuilder query = new StringBuilder(
            "DELETE FROM compromisso WHERE rowid = ?"
        );

        try (
            Connection con = getConexao();
            PreparedStatement ps = con.prepareStatement(query.toString())
        ) {
            ps.setInt(1, Integer.parseInt(rowid));

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteCompromissosPorFuncionario(String codigoFuncionario) {
        StringBuilder query = new StringBuilder(
            "DELETE FROM compromisso WHERE cd_funcionario = ?"
        );

        try (
            Connection con = getConexao();
            PreparedStatement ps = con.prepareStatement(query.toString())
        ) {
            ps.setInt(1, Integer.parseInt(codigoFuncionario));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean existeCompromissoParaAgenda(String codigoAgenda) {
        StringBuilder query = new StringBuilder(
            "SELECT COUNT(*) FROM compromisso WHERE cd_agenda = ?"
        );

        try (
            Connection con = getConexao();
            PreparedStatement ps = con.prepareStatement(query.toString())
        ) {
            ps.setInt(1, Integer.parseInt(codigoAgenda));

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public List<CompromissosVo> findAllCompromissos() {
        StringBuilder query = new StringBuilder(
            "SELECT rowid, cd_funcionario, cd_agenda, " +
            "dt_compromisso, hr_compromisso " +
            "FROM compromisso"
        );

        try (
            Connection con = getConexao();
            PreparedStatement ps = con.prepareStatement(query.toString());
            ResultSet rs = ps.executeQuery()
        ) {
            List<CompromissosVo> compromissos = new ArrayList<>();

            while (rs.next()) {
                CompromissosVo compromisso = new CompromissosVo();

                compromisso.setRowid(rs.getString("rowid"));
                compromisso.setCodigoFuncionario(
                        rs.getString("cd_funcionario"));
                compromisso.setCodigoAgenda(
                        rs.getString("cd_agenda"));
                compromisso.setData(
                        rs.getString("dt_compromisso"));
                compromisso.setHorario(
                        rs.getString("hr_compromisso"));

                compromissos.add(compromisso);
            }

            return compromissos;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}