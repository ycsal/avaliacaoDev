package br.com.soc.sistema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.vo.AgendaVo;

public class AgendaDao extends Dao {

    public List<AgendaVo> findAllAgendas() {
        StringBuilder query = new StringBuilder("SELECT rowid, nm_agenda, periodo_disponivel FROM agenda");

        try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString()); ResultSet rs = ps.executeQuery()) 
        {
            List<AgendaVo> agendas = new ArrayList<>();

            while (rs.next()) {
                AgendaVo agenda = new AgendaVo();

                agenda.setRowid(rs.getString("rowid"));
                agenda.setNome(rs.getString("nm_agenda"));
                agenda.setPeriodo(rs.getString("periodo_disponivel"));

                agendas.add(agenda);
            }

            return agendas;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
    
    public void insertAgenda(AgendaVo agenda) {
        StringBuilder query = new StringBuilder(
            "INSERT INTO agenda (nm_agenda, periodo_disponivel) VALUES (?, ?)"
        );

        try (
            Connection con = getConexao();
            PreparedStatement ps = con.prepareStatement(
                query.toString(),
                java.sql.Statement.RETURN_GENERATED_KEYS
            )
        ) {
            int i = 1;

            ps.setString(i++, agenda.getNome());
            ps.setString(i++, agenda.getPeriodo());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    agenda.setRowid(rs.getString(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public AgendaVo findAgendaPor(String rowid) {
        StringBuilder query = new StringBuilder("SELECT rowid, nm_agenda, periodo_disponivel " + "FROM agenda WHERE rowid = ?");

        try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) 
        {
            ps.setInt(1, Integer.parseInt(rowid));

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    AgendaVo agenda = new AgendaVo();

                    agenda.setRowid(rs.getString("rowid"));
                    agenda.setNome(rs.getString("nm_agenda"));
                    agenda.setPeriodo(rs.getString("periodo_disponivel"));

                    return agenda;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public void updateAgenda(AgendaVo agenda) {
        StringBuilder query = new StringBuilder("UPDATE agenda SET nm_agenda = ?, periodo_disponivel = ? WHERE rowid = ?");

        try (
            Connection con = getConexao();
            PreparedStatement ps = con.prepareStatement(query.toString())
        ) {
            int i = 1;

            ps.setString(i++, agenda.getNome());
            ps.setString(i++, agenda.getPeriodo());
            ps.setInt(i++, Integer.parseInt(agenda.getRowid()));

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteAgenda(String rowid) {
        StringBuilder query = new StringBuilder("DELETE FROM agenda WHERE rowid = ?");

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
}