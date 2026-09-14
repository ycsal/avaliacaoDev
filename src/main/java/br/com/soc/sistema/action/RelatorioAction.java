package br.com.soc.sistema.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.soc.sistema.business.RelatorioBusiness;
import br.com.soc.sistema.vo.RelatorioVo;

public class RelatorioAction {

    private RelatorioBusiness business;

    private List<RelatorioVo> relatorios;

    private String dataInicial;

    private String dataFinal;

    private InputStream arquivoExcel;

    public RelatorioAction() {
        business = new RelatorioBusiness();
    }

    public String consultar() {

        relatorios = business.consultarRelatorioPorPeriodo(dataInicial, dataFinal);

        System.out.println("CONSULTA HTML");
        System.out.println("Data inicial: " + dataInicial);
        System.out.println("Data final: " + dataFinal);
        System.out.println("Quantidade de registros: " + relatorios.size());

        return "success";
    }

    public String exportarExcel() {

        System.out.println("EXPORTAÇÃO EXCEL");
        System.out.println("Data inicial: " + dataInicial);
        System.out.println("Data final: " + dataFinal);

        relatorios = business.consultarRelatorioPorPeriodo(dataInicial, dataFinal);

        System.out.println("Quantidade de registros para Excel: " + relatorios.size());

        try {

            Workbook workbook = new XSSFWorkbook();

            Sheet sheet = workbook.createSheet("Relatorio");

            Row cabecalho = sheet.createRow(0);

            cabecalho.createCell(0).setCellValue("Código funcionário");
            cabecalho.createCell(1).setCellValue("Funcionário");
            cabecalho.createCell(2).setCellValue("Código agenda");
            cabecalho.createCell(3).setCellValue("Agenda");
            cabecalho.createCell(4).setCellValue("Data");
            cabecalho.createCell(5).setCellValue("Horário");

            int linha = 1;

            for (RelatorioVo relatorio : relatorios) {

                System.out.println(
                    "Adicionando registro: "
                    + relatorio.getNomeFuncionario()
                    + " - "
                    + relatorio.getData()
                    + " - "
                    + relatorio.getHorario()
                );

                Row row = sheet.createRow(linha++);

                row.createCell(0).setCellValue(relatorio.getCodigoFuncionario());
                row.createCell(1).setCellValue(relatorio.getNomeFuncionario());
                row.createCell(2).setCellValue(relatorio.getCodigoAgenda());
                row.createCell(3).setCellValue(relatorio.getNomeAgenda());
                row.createCell(4).setCellValue(relatorio.getData());
                row.createCell(5).setCellValue(relatorio.getHorario());
            }

            for (int i = 0; i < 6; i++) {
                sheet.autoSizeColumn(i);
            }

            ByteArrayOutputStream output = new ByteArrayOutputStream();

            workbook.write(output);

            workbook.close();

            arquivoExcel = new ByteArrayInputStream(output.toByteArray());

            System.out.println("Excel gerado com sucesso.");
            System.out.println("Tamanho do arquivo: " + output.size() + " bytes");

            return "excel";

        } catch (IOException e) {

            throw new RuntimeException(
                "Nao foi possivel gerar o arquivo Excel", e
            );
        }
    }

    public List<RelatorioVo> getRelatorios() {
        return relatorios;
    }

    public String getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    public InputStream getArquivoExcel() {
        return arquivoExcel;
    }
}