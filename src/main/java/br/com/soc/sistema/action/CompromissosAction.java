package br.com.soc.sistema.action;

import java.util.List;

import br.com.soc.sistema.business.CompromissosBusiness;
import br.com.soc.sistema.vo.CompromissosVo;

public class CompromissosAction {

    private CompromissosVo compromissosVo;
    private CompromissosBusiness business;
    private List<CompromissosVo> compromissos;

    public CompromissosAction() {
        this.compromissosVo = new CompromissosVo();
        this.business = new CompromissosBusiness();
    }

    public String todos() {
        compromissos = business.trazerTodosOsCompromissos();
        return "success";
    }

    public String novo() {
        if (compromissosVo.getCodigoFuncionario() == null) {
            return "input";
        }

        business.salvarCompromisso(compromissosVo);
        return "redirect";
    }

    public String editar() {
        if (compromissosVo.getRowid() == null) {
            return "redirect";
        }

        compromissosVo =
                business.buscarCompromissoPor(compromissosVo.getRowid());

        return "input";
    }

    public String excluir() {
        if (compromissosVo.getRowid() == null) {
            return "redirect";
        }

        business.excluirCompromisso(compromissosVo.getRowid());
        return "redirect";
    }

    public CompromissosVo getCompromissosVo() {
        return compromissosVo;
    }

    public void setCompromissosVo(CompromissosVo compromissosVo) {
        this.compromissosVo = compromissosVo;
    }

    public List<CompromissosVo> getCompromissos() {
        return compromissos;
    }
}