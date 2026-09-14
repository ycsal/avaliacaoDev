package br.com.soc.sistema.business;
import br.com.soc.sistema.exception.BusinessException;

import java.util.List;

import br.com.soc.sistema.dao.AgendaDao;
import br.com.soc.sistema.vo.AgendaVo;

public class AgendaBusiness {

    private AgendaDao dao;

    public AgendaBusiness() {
        this.dao = new AgendaDao();
    }

    public List<AgendaVo> trazerTodasAsAgendas() {
        return dao.findAllAgendas();
    }
    
    public void salvarAgenda(AgendaVo agenda) {
        if (agenda.getRowid() == null || agenda.getRowid().trim().isEmpty()) {
            dao.insertAgenda(agenda);
        } else {
            dao.updateAgenda(agenda);
        }
    }
    
    public AgendaVo buscarAgendaPor(String rowid) {
        return dao.findAgendaPor(rowid);
    }
    
    public void excluirAgenda(String rowid) {
        CompromissosBusiness compromissoBusiness =
                new CompromissosBusiness();

        if (compromissoBusiness.existeCompromissoParaAgenda(rowid)) {
            throw new BusinessException(
                    "Nao e possivel excluir uma agenda que possui compromissos"
            );
        }

        dao.deleteAgenda(rowid);
    }
}