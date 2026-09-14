<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><s:text name="label.compromissos"/></title>
    <link rel="stylesheet" href="webjars/bootstrap/5.1.3/css/bootstrap.min.css">
</head>

<body style="background-color: #DCEFED;">
    <jsp:include page="/header.jsp"/>

    <div class="container">
        <div class="row mt-5 mb-2">
            <div class="col-sm p-0">
                <h2><s:text name="label.compromissos"/></h2>
            </div>
        </div>

        <div class="row">
            <table class="table table-light table-striped align-middle">
                <thead>
                    <tr>
                        <th><s:text name="label.id"/></th>
                        <th><s:text name="label.codigo.funcionario"/></th>
                        <th><s:text name="label.codigo.agenda"/></th>
                        <th><s:text name="label.data"/></th>
                        <th><s:text name="label.horario"/></th>
                        <th class="text-end"><s:text name="label.acao"/></th>
                    </tr>
                </thead>

                <tbody>
                    <s:iterator value="compromissos">
                        <tr>
                            <td>${rowid}</td>
                            <td>${codigoFuncionario}</td>
                            <td>${codigoAgenda}</td>
                            <td>${data}</td>
                            <td>${horario}</td>
                            <td class="text-end">
                                <s:url action="editarCompromissos" var="editar">
                                    <s:param name="compromissosVo.rowid" value="rowid"/>
                                </s:url>

                                <a href="${editar}" class="btn btn-warning text-white">
                                    <s:text name="label.editar"/>
                                </a>

                                <a href="#" class="btn btn-danger"
                                   data-bs-toggle="modal"
                                   data-bs-target="#confirmarExclusao"
                                   data-rowid="${rowid}">
                                    <s:text name="label.excluir"/>
                                </a>
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>

                <tfoot class="table-secondary">
                    <tr>
                        <td colspan="6">
                            <s:url action="novoCompromissos" var="novo"/>
                            <a href="${novo}" class="btn btn-success">
                                <s:text name="label.novo"/>
                            </a>
                        </td>
                    </tr>
                </tfoot>
            </table>
        </div>
    </div>

    <div class="modal fade"
         id="confirmarExclusao"
         data-bs-backdrop="static"
         data-bs-keyboard="false"
         tabindex="-1"
         aria-labelledby="staticBackdropLabel"
         aria-hidden="true">

        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">
                        <s:text name="label.modal.titulo"/>
                    </h5>

                    <button type="button"
                            class="btn-close"
                            data-bs-dismiss="modal"
                            aria-label="Close">
                    </button>
                </div>

                <div class="modal-body">
                    <span>
                        <s:text name="label.modal.corpo"/>
                    </span>
                </div>

                <div class="modal-footer">
                    <a class="btn btn-secondary"
                       data-bs-dismiss="modal"
                       aria-label="Close">
                        <s:text name="label.nao"/>
                    </a>

                    <a id="excluir"
                       href="#"
                       class="btn btn-primary"
                       style="width: 75px;">
                        <s:text name="label.sim"/>
                    </a>
                </div>
            </div>
        </div>
    </div>

    <script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>

    <script>
        var botoesExcluir = document.querySelectorAll('[data-rowid]');
        var botaoConfirmar = document.getElementById('excluir');

        botoesExcluir.forEach(function(botao) {
            botao.onclick = function() {
                var rowid = botao.dataset.rowid;
                botaoConfirmar.href =
                    'excluirCompromissos.action?compromissosVo.rowid=' + rowid;
            };
        });
    </script>

</body>
</html>