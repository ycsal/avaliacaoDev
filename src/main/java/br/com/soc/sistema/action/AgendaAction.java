package br.com.soc.sistema.action;
import java.util.List;

import br.com.soc.sistema.business.AgendaBusiness;
import br.com.soc.sistema.vo.AgendaVo;

public class AgendaAction {

    private AgendaVo agendaVo;
    private AgendaBusiness business;

    public AgendaAction() {
        this.agendaVo = new AgendaVo();
        this.business = new AgendaBusiness();
    }

    public String novo() {
        if (agendaVo.getNome() == null) {
            return "input";
        }

        business.salvarAgenda(agendaVo);
        return "redirect";
    }

    public String editar() {
        if (agendaVo.getRowid() == null) {
            return "redirect";
        }

        agendaVo = business.buscarAgendaPor(agendaVo.getRowid());
        return "input";
    }

    public String excluir() {
        if (agendaVo.getRowid() == null) {
            return "redirect";
        }

        business.excluirAgenda(agendaVo.getRowid());
        return "redirect";
    }

    public AgendaVo getAgendaVo() {
        return agendaVo;
    }

    public void setAgendaVo(AgendaVo agendaVo) {
        this.agendaVo = agendaVo;
    }
    
    private List<AgendaVo> agendas;

    public String todas() {
        agendas = business.trazerTodasAsAgendas();
        return "success";
    }

    public List<AgendaVo> getAgendas() {
        return agendas;
    }
}