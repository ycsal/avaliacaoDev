package br.com.soc.sistema.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import br.com.soc.sistema.vo.AgendaVo;

public class AgendaBusinessTest {

    @Test
    public void deveConsultarTodasAsAgendas() {

        AgendaBusiness business = new AgendaBusiness();

        List<AgendaVo> agendas = business.trazerTodasAsAgendas();

        assertNotNull(agendas);
        assertEquals(3, agendas.size());
    }
    
    @Test
    public void deveCadastrarAgenda() {
        AgendaBusiness business = new AgendaBusiness();

        AgendaVo agenda = new AgendaVo();
        agenda.setNome("Agenda Nova");
        agenda.setPeriodo("MANHA");

        business.salvarAgenda(agenda);

        List<AgendaVo> agendas = business.trazerTodasAsAgendas();

        boolean encontrou = false;

        for (AgendaVo agendaEncontrada : agendas) {
            if ("Agenda Nova".equals(agendaEncontrada.getNome())) {
                encontrou = true;
                break;
            }
        }

        assertEquals(true, encontrou);
    }
    
    @Test
    public void deveAlterarAgendaExistente() {
        AgendaBusiness business = new AgendaBusiness();

        AgendaVo agenda = new AgendaVo();
        agenda.setNome("Agenda Original");
        agenda.setPeriodo("MANHA");

        business.salvarAgenda(agenda);

        List<AgendaVo> agendas = business.trazerTodasAsAgendas();

        AgendaVo agendaCriada = null;

        for (AgendaVo agendaEncontrada : agendas) {
            if ("Agenda Original".equals(agendaEncontrada.getNome())) {
                agendaCriada = agendaEncontrada;
                break;
            }
        }

        assertNotNull(agendaCriada);

        agendaCriada.setNome("Agenda Alterada");
        agendaCriada.setPeriodo("TARDE");

        business.salvarAgenda(agendaCriada);

        AgendaVo agendaAlterada =
                business.buscarAgendaPor(agendaCriada.getRowid());

        assertEquals("Agenda Alterada", agendaAlterada.getNome());
        assertEquals("TARDE", agendaAlterada.getPeriodo());
    }
    
    @Test
    public void deveExcluirAgendaExistente() {
        AgendaBusiness business = new AgendaBusiness();

        AgendaVo agenda = new AgendaVo();
        agenda.setNome("Agenda Para Excluir");
        agenda.setPeriodo("MANHA");

        business.salvarAgenda(agenda);

        List<AgendaVo> agendas = business.trazerTodasAsAgendas();

        AgendaVo agendaCriada = null;

        for (AgendaVo agendaEncontrada : agendas) {
            if ("Agenda Para Excluir".equals(agendaEncontrada.getNome())) {
                agendaCriada = agendaEncontrada;
                break;
            }
        }

        assertNotNull(agendaCriada);

        business.excluirAgenda(agendaCriada.getRowid());

        AgendaVo agendaExcluida =
                business.buscarAgendaPor(agendaCriada.getRowid());

        assertNull(agendaExcluida);
    }
}