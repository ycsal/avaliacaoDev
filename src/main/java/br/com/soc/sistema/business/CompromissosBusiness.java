package br.com.soc.sistema.business;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import br.com.soc.sistema.dao.CompromissosDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.vo.AgendaVo;
import br.com.soc.sistema.vo.CompromissosVo;

public class CompromissosBusiness {

    private CompromissosDao dao;

    public CompromissosBusiness() {
        this.dao = new CompromissosDao();
    }

    public void salvarCompromisso(CompromissosVo compromisso) {

        validarPeriodoAgenda(compromisso);

        if (compromisso.getRowid() == null ||
            compromisso.getRowid().trim().isEmpty()) {

            dao.insertCompromisso(compromisso);

        } else {

            dao.updateCompromisso(compromisso);
        }
    }

    private void validarPeriodoAgenda(CompromissosVo compromisso) {

        AgendaBusiness agendaBusiness = new AgendaBusiness();

        AgendaVo agenda =
                agendaBusiness.buscarAgendaPor(compromisso.getCodigoAgenda());

        LocalTime horario = LocalTime.parse(
                compromisso.getHorario(),
                DateTimeFormatter.ofPattern("HH:mm")
        );

        if ("MANHA".equals(agenda.getPeriodo())
                && !horario.isBefore(LocalTime.NOON)) {

            throw new BusinessException(
                    "O horario do compromisso esta fora do periodo da agenda"
            );
        }

        if ("TARDE".equals(agenda.getPeriodo())
                && horario.isBefore(LocalTime.NOON)) {

            throw new BusinessException(
                    "O horario do compromisso esta fora do periodo da agenda"
            );
        }
    }

    public CompromissosVo buscarCompromissoPor(String rowid) {
        return dao.findCompromissoPor(rowid);
    }

    public void excluirCompromisso(String rowid) {
        dao.deleteCompromisso(rowid);
    }
    
    public void excluirCompromissosPorFuncionario(String codigoFuncionario) {
        dao.deleteCompromissosPorFuncionario(codigoFuncionario);
    }
    
    public boolean existeCompromissoParaAgenda(String codigoAgenda) {
        return dao.existeCompromissoParaAgenda(codigoAgenda);
    }
    
    public List<CompromissosVo> trazerTodosOsCompromissos() {
        return dao.findAllCompromissos();
    }
}