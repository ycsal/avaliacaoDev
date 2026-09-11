package br.com.soc.sistema.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import br.com.soc.sistema.vo.FuncionarioVo;

public class FuncionarioBusinessTest {

    @Test
    public void deveAlterarFuncionarioExistente() {

        FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();
        FuncionarioVo funcionario = funcionarioBusiness.buscarFuncionarioPor("1");
        assertNotNull(funcionario);
        
        funcionario.setNome("Joao Alterado");
        funcionarioBusiness.salvarFuncionario(funcionario);

        FuncionarioVo funcionarioAlterado =
                funcionarioBusiness.buscarFuncionarioPor("1");

        assertEquals("Joao Alterado", funcionarioAlterado.getNome());
    }
    
    @Test
    public void deveExcluirFuncionarioExistente() {
        FuncionarioBusiness business = new FuncionarioBusiness();
        FuncionarioVo funcionario = business.buscarFuncionarioPor("1");
        assertNotNull(funcionario);

        business.excluirFuncionario(funcionario.getRowid());

        FuncionarioVo funcionarioExcluido =
                business.buscarFuncionarioPor("1");

        assertNull(funcionarioExcluido);
    }
}