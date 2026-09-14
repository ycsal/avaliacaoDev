<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<title><s:text name="label.consulta.relatorio" /></title>
<link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css"
	rel="stylesheet">
</head>

<body style="background-color: #DCEFED;">
	<jsp:include page="/header.jsp" />

	<div class="container mt-4">
		<h2>
			<s:text name="label.consulta.relatorio" />
		</h2>
		<form method="get">
			<div class="row">
				<div class="col-md-3 mb-3">
					<label for="dataInicial" class="form-label"> <s:text
							name="label.data.inicial" />
					</label> <input type="text" name="dataInicial" id="dataInicial"
						class="form-control" placeholder="dd/mm/aaaa" maxlength="10"
						value="<s:property value='dataInicial'/>">
				</div>

				<div class="col-md-3 mb-3">
					<label for="dataFinal" class="form-label"> <s:text
							name="label.data.final" />
					</label> <input type="text" name="dataFinal" id="dataFinal"
						class="form-control" placeholder="dd/mm/aaaa" maxlength="10"
						value="<s:property value='dataFinal'/>">
				</div>
			</div>

			<button type="submit" formaction="consultarRelatorio.action"
				class="btn btn-primary">
				<s:text name="label.pesquisar" />
			</button>

			<button type="submit" formaction="exportarExcelRelatorio.action"
				class="btn btn-success">
				<s:text name="label.exportar.excel" />
			</button>
		</form>

		<hr>

		<table class="table table-bordered table-striped" style="background-color: white;">
			<thead>
				<tr>
					<th><s:text name="label.codigo.funcionario" /></th>
					<th><s:text name="label.nome.funcionario" /></th>
					<th><s:text name="label.codigo.agenda" /></th>
					<th><s:text name="label.nome.agenda" /></th>
					<th><s:text name="label.data" /></th>
					<th><s:text name="label.horario" /></th>
				</tr>
			</thead>

			<tbody>
				<s:iterator value="relatorios">
					<tr>
						<td><s:property value="codigoFuncionario" /></td>
						<td><s:property value="nomeFuncionario" /></td>
						<td><s:property value="codigoAgenda" /></td>
						<td><s:property value="nomeAgenda" /></td>
						<td><s:property value="data" /></td>
						<td><s:property value="horario" /></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>

	<script>
		function formatarData(input) {
			var valor = input.value.replace(/\D/g, '');
			if (valor.length > 2) {
				valor = valor.substring(0, 2) + '/' + valor.substring(2);
			}
			if (valor.length > 5) {
				valor = valor.substring(0, 5) + '/' + valor.substring(5, 9);
			}
			input.value = valor;
		}
		document.getElementById('dataInicial').addEventListener('input',
				function() {
					formatarData(this);
				});
		document.getElementById('dataFinal').addEventListener('input',
				function() {
					formatarData(this);
				});
	</script>
</body>
</html>