package br.com.soc.sistema.business;

import java.util.List;

import br.com.soc.sistema.dao.RelatorioDao;
import br.com.soc.sistema.vo.RelatorioVo;

public class RelatorioBusiness {
    private RelatorioDao dao;

    public RelatorioBusiness() {
        this.dao = new RelatorioDao();
    }

    public List<RelatorioVo> consultarRelatorio() {
        return dao.consultarRelatorio();
    }

    public List<RelatorioVo> consultarRelatorioPorPeriodo(String dataInicial, String dataFinal) {
        return dao.consultarRelatorioPorPeriodo(dataInicial, dataFinal);
    }
}