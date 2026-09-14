package br.com.soc.sistema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.vo.RelatorioVo;

public class RelatorioDao extends Dao {

    public List<RelatorioVo> consultarRelatorio() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT f.rowid AS codigo_funcionario, f.nm_funcionario AS nome_funcionario, ");
        query.append("a.rowid AS codigo_agenda, a.nm_agenda AS nome_agenda, ");
        query.append("c.dt_compromisso AS data, c.hr_compromisso AS horario ");
        query.append("FROM compromisso c INNER JOIN funcionario f ON c.cd_funcionario = f.rowid ");
        query.append("INNER JOIN agenda a ON c.cd_agenda = a.rowid");

        try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString()); ResultSet rs = ps.executeQuery()) {
            List<RelatorioVo> resultado = new ArrayList<RelatorioVo>();
            while (rs.next()) {
                RelatorioVo relatorio = new RelatorioVo();
                relatorio.setCodigoFuncionario(rs.getString("codigo_funcionario"));
                relatorio.setNomeFuncionario(rs.getString("nome_funcionario"));
                relatorio.setCodigoAgenda(rs.getString("codigo_agenda"));
                relatorio.setNomeAgenda(rs.getString("nome_agenda"));
                relatorio.setData(rs.getString("data"));
                relatorio.setHorario(rs.getString("horario"));
                resultado.add(relatorio);
            }
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<RelatorioVo>();
    }

    public List<RelatorioVo> consultarRelatorioPorPeriodo(String dataInicial, String dataFinal) {

        StringBuilder query = new StringBuilder();

        query.append("SELECT f.rowid AS codigo_funcionario, f.nm_funcionario AS nome_funcionario, ");
        query.append("a.rowid AS codigo_agenda, a.nm_agenda AS nome_agenda, ");
        query.append("c.dt_compromisso AS data, c.hr_compromisso AS horario ");
        query.append("FROM compromisso c INNER JOIN funcionario f ON c.cd_funcionario = f.rowid ");
        query.append("INNER JOIN agenda a ON c.cd_agenda = a.rowid ");
        query.append("WHERE PARSEDATETIME(c.dt_compromisso, 'dd/MM/yyyy') >= ");
        query.append("PARSEDATETIME(?, 'dd/MM/yyyy') ");
        query.append("AND PARSEDATETIME(c.dt_compromisso, 'dd/MM/yyyy') <= ");
        query.append("PARSEDATETIME(?, 'dd/MM/yyyy')");

        try (Connection con = getConexao();
             PreparedStatement ps = con.prepareStatement(query.toString())) {

            ps.setString(1, dataInicial);
            ps.setString(2, dataFinal);

            try (ResultSet rs = ps.executeQuery()) {

                List<RelatorioVo> resultado = new ArrayList<RelatorioVo>();

                while (rs.next()) {

                    RelatorioVo relatorio = new RelatorioVo();

                    relatorio.setCodigoFuncionario(rs.getString("codigo_funcionario"));
                    relatorio.setNomeFuncionario(rs.getString("nome_funcionario"));
                    relatorio.setCodigoAgenda(rs.getString("codigo_agenda"));
                    relatorio.setNomeAgenda(rs.getString("nome_agenda"));
                    relatorio.setData(rs.getString("data"));
                    relatorio.setHorario(rs.getString("horario"));

                    resultado.add(relatorio);
                }

                return resultado;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<RelatorioVo>();
    }
}