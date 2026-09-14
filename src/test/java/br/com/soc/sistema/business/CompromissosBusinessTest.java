package br.com.soc.sistema.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import br.com.soc.sistema.exception.BusinessException;

import org.junit.Test;

import br.com.soc.sistema.vo.AgendaVo;
import br.com.soc.sistema.vo.CompromissosVo;
import br.com.soc.sistema.vo.FuncionarioVo;

public class CompromissosBusinessTest {

    @Test
    public void deveCadastrarCompromisso() {

        FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();

        FuncionarioVo funcionario = new FuncionarioVo();
        funcionario.setNome("Funcionario Compromisso");

        funcionarioBusiness.salvarFuncionario(funcionario);


        AgendaBusiness agendaBusiness = new AgendaBusiness();

        AgendaVo agenda = new AgendaVo();
        agenda.setNome("Agenda Compromisso");
        agenda.setPeriodo("MANHA");

        agendaBusiness.salvarAgenda(agenda);


        CompromissosBusiness business = new CompromissosBusiness();

        CompromissosVo compromisso = new CompromissosVo();

        compromisso.setCodigoFuncionario(funcionario.getRowid());
        compromisso.setCodigoAgenda(agenda.getRowid());
        compromisso.setData("16/09/2026");
        compromisso.setHorario("10:30");

        business.salvarCompromisso(compromisso);


        CompromissosVo compromissoSalvo =
                business.buscarCompromissoPor(compromisso.getRowid());

        assertNotNull(compromissoSalvo);

        assertEquals(
                funcionario.getRowid(),
                compromissoSalvo.getCodigoFuncionario()
        );

        assertEquals(
                agenda.getRowid(),
                compromissoSalvo.getCodigoAgenda()
        );

        assertEquals(
                "16/09/2026",
                compromissoSalvo.getData()
        );

        assertEquals(
                "10:30",
                compromissoSalvo.getHorario()
        );
    }


    @Test
    public void deveAlterarCompromissoExistente() {

        FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();

        FuncionarioVo funcionario = new FuncionarioVo();
        funcionario.setNome("Funcionario Alteracao Compromisso");

        funcionarioBusiness.salvarFuncionario(funcionario);


        AgendaBusiness agendaBusiness = new AgendaBusiness();

        AgendaVo agenda = new AgendaVo();
        agenda.setNome("Agenda Alteracao Compromisso");
        agenda.setPeriodo("MANHA");

        agendaBusiness.salvarAgenda(agenda);


        CompromissosBusiness business = new CompromissosBusiness();

        CompromissosVo compromisso = new CompromissosVo();

        compromisso.setCodigoFuncionario(funcionario.getRowid());
        compromisso.setCodigoAgenda(agenda.getRowid());
        compromisso.setData("16/09/2026");
        compromisso.setHorario("10:30");

        business.salvarCompromisso(compromisso);


        String rowidOriginal = compromisso.getRowid();


        compromisso.setData("17/09/2026");
        compromisso.setHorario("11:00");

        business.salvarCompromisso(compromisso);


        CompromissosVo compromissoAlterado =
                business.buscarCompromissoPor(rowidOriginal);

        assertNotNull(compromissoAlterado);

        assertEquals(
                "17/09/2026",
                compromissoAlterado.getData()
        );

        assertEquals(
                "11:00",
                compromissoAlterado.getHorario()
        );
    }
    @Test
    public void deveExcluirCompromissoExistente() {

        FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();

        FuncionarioVo funcionario = new FuncionarioVo();
        funcionario.setNome("Funcionario Exclusao Compromisso");

        funcionarioBusiness.salvarFuncionario(funcionario);


        AgendaBusiness agendaBusiness = new AgendaBusiness();

        AgendaVo agenda = new AgendaVo();
        agenda.setNome("Agenda Exclusao Compromisso");
        agenda.setPeriodo("MANHA");

        agendaBusiness.salvarAgenda(agenda);


        CompromissosBusiness business = new CompromissosBusiness();

        CompromissosVo compromisso = new CompromissosVo();

        compromisso.setCodigoFuncionario(funcionario.getRowid());
        compromisso.setCodigoAgenda(agenda.getRowid());
        compromisso.setData("18/09/2026");
        compromisso.setHorario("09:30");

        business.salvarCompromisso(compromisso);


        String rowidOriginal = compromisso.getRowid();


        business.excluirCompromisso(rowidOriginal);


        CompromissosVo compromissoExcluido =
                business.buscarCompromissoPor(rowidOriginal);

        assertNull(compromissoExcluido);
    }
    
    @Test(expected = BusinessException.class)
    public void naoDeveCadastrarCompromissoForaDoPeriodoDaAgenda() {
        FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();

        FuncionarioVo funcionario = new FuncionarioVo();
        funcionario.setNome("Funcionario Fora Periodo");
        funcionarioBusiness.salvarFuncionario(funcionario);

        AgendaBusiness agendaBusiness = new AgendaBusiness();

        AgendaVo agenda = new AgendaVo();
        agenda.setNome("Agenda Manha");
        agenda.setPeriodo("MANHA");
        agendaBusiness.salvarAgenda(agenda);

        CompromissosBusiness business = new CompromissosBusiness();

        CompromissosVo compromisso = new CompromissosVo();
        compromisso.setCodigoFuncionario(funcionario.getRowid());
        compromisso.setCodigoAgenda(agenda.getRowid());
        compromisso.setData("19/09/2026");
        compromisso.setHorario("14:00");

        business.salvarCompromisso(compromisso);
    }
    
    @Test(expected = BusinessException.class)
    public void naoDeveCadastrarCompromissoDeManhaEmAgendaDaTarde() {
        FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();

        FuncionarioVo funcionario = new FuncionarioVo();
        funcionario.setNome("Funcionario Agenda Tarde");
        funcionarioBusiness.salvarFuncionario(funcionario);

        AgendaBusiness agendaBusiness = new AgendaBusiness();

        AgendaVo agenda = new AgendaVo();
        agenda.setNome("Agenda Tarde");
        agenda.setPeriodo("TARDE");
        agendaBusiness.salvarAgenda(agenda);

        CompromissosBusiness business = new CompromissosBusiness();

        CompromissosVo compromisso = new CompromissosVo();
        compromisso.setCodigoFuncionario(funcionario.getRowid());
        compromisso.setCodigoAgenda(agenda.getRowid());
        compromisso.setData("20/09/2026");
        compromisso.setHorario("10:00");

        business.salvarCompromisso(compromisso);
    }
    
    @Test
    public void deveCadastrarCompromissoEmAgendaAmbos() {
        FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();

        FuncionarioVo funcionario = new FuncionarioVo();
        funcionario.setNome("Funcionario Agenda Ambos");
        funcionarioBusiness.salvarFuncionario(funcionario);

        AgendaBusiness agendaBusiness = new AgendaBusiness();

        AgendaVo agenda = new AgendaVo();
        agenda.setNome("Agenda Ambos");
        agenda.setPeriodo("AMBOS");
        agendaBusiness.salvarAgenda(agenda);

        CompromissosBusiness business = new CompromissosBusiness();

        CompromissosVo compromisso = new CompromissosVo();
        compromisso.setCodigoFuncionario(funcionario.getRowid());
        compromisso.setCodigoAgenda(agenda.getRowid());
        compromisso.setData("21/09/2026");
        compromisso.setHorario("10:00");

        business.salvarCompromisso(compromisso);

        CompromissosVo compromissoSalvo =
                business.buscarCompromissoPor(compromisso.getRowid());

        assertNotNull(compromissoSalvo);
        assertEquals("10:00", compromissoSalvo.getHorario());
    }
    
    @Test
    public void deveExcluirCompromissosAoExcluirFuncionario() {
        FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();

        FuncionarioVo funcionario = new FuncionarioVo();
        funcionario.setNome("Funcionario Com Compromisso");
        funcionarioBusiness.salvarFuncionario(funcionario);

        AgendaBusiness agendaBusiness = new AgendaBusiness();

        AgendaVo agenda = new AgendaVo();
        agenda.setNome("Agenda Funcionario");
        agenda.setPeriodo("MANHA");
        agendaBusiness.salvarAgenda(agenda);

        CompromissosBusiness compromissoBusiness = new CompromissosBusiness();

        CompromissosVo compromisso = new CompromissosVo();
        compromisso.setCodigoFuncionario(funcionario.getRowid());
        compromisso.setCodigoAgenda(agenda.getRowid());
        compromisso.setData("22/09/2026");
        compromisso.setHorario("10:00");

        compromissoBusiness.salvarCompromisso(compromisso);

        String rowidCompromisso = compromisso.getRowid();

        // Exclui o funcionário
        funcionarioBusiness.excluirFuncionario(funcionario.getRowid());

        // O compromisso também deve ter sido excluído
        CompromissosVo compromissoExcluido =
                compromissoBusiness.buscarCompromissoPor(rowidCompromisso);

        assertNull(compromissoExcluido);
    }
    
    @Test(expected = BusinessException.class)
    public void naoDeveExcluirAgendaComCompromisso() {
        FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();

        FuncionarioVo funcionario = new FuncionarioVo();
        funcionario.setNome("Funcionario Agenda Compromisso");
        funcionarioBusiness.salvarFuncionario(funcionario);

        AgendaBusiness agendaBusiness = new AgendaBusiness();

        AgendaVo agenda = new AgendaVo();
        agenda.setNome("Agenda Com Compromisso");
        agenda.setPeriodo("MANHA");
        agendaBusiness.salvarAgenda(agenda);

        CompromissosBusiness compromissoBusiness = new CompromissosBusiness();

        CompromissosVo compromisso = new CompromissosVo();
        compromisso.setCodigoFuncionario(funcionario.getRowid());
        compromisso.setCodigoAgenda(agenda.getRowid());
        compromisso.setData("23/09/2026");
        compromisso.setHorario("10:00");

        compromissoBusiness.salvarCompromisso(compromisso);

        // Deve impedir a exclusão da agenda
        agendaBusiness.excluirAgenda(agenda.getRowid());
    }
    
    @Test
    public void deveExcluirAgendaSemCompromisso() {
        AgendaBusiness agendaBusiness = new AgendaBusiness();

        AgendaVo agenda = new AgendaVo();
        agenda.setNome("Agenda Sem Compromisso");
        agenda.setPeriodo("MANHA");

        agendaBusiness.salvarAgenda(agenda);

        String rowidAgenda = agenda.getRowid();

        agendaBusiness.excluirAgenda(rowidAgenda);

        AgendaVo agendaExcluida =
                agendaBusiness.buscarAgendaPor(rowidAgenda);

        assertNull(agendaExcluida);
    }
}