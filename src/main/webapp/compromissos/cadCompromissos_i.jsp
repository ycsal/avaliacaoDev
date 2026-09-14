<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><s:text name="label.compromisso"/></title>
    <link rel="stylesheet" href="webjars/bootstrap/5.1.3/css/bootstrap.min.css">
</head>

<body style="background-color: #DCEFED;">
    <jsp:include page="/header.jsp"/>

    <div class="container">
        <s:form action="/novoCompromissos.action" acceptcharset="UTF-8">
            <div class="card mt-5">

                <div class="card-header">
                    <div class="row">
                        <div class="col-sm-5">
                            <s:url action="todosCompromissos" var="todos"/>
                            <a href="${todos}" class="btn btn-success">
                                <s:text name="label.compromissos"/>
                            </a>
                        </div>

                        <div class="col-sm">
                            <h5 class="card-title">
                                <s:if test="compromissosVo.rowid != null">
                                    <s:text name="label.editar.compromisso"/>
                                </s:if>
                                <s:else>
                                    <s:text name="label.novo.compromisso"/>
                                </s:else>
                            </h5>
                        </div>
                    </div>
                </div>

                <div class="card-body">

                    <div class="row align-items-center">
                        <label for="id" class="col-sm-2 col-form-label text-center">
                            <s:text name="label.id"/>:
                        </label>
                        <div class="col-sm-2">
                            <s:textfield
                                cssClass="form-control"
                                id="id"
                                name="compromissosVo.rowid"
                                readonly="true"/>
                        </div>
                    </div>

                    <div class="row align-items-center mt-3">
                        <label for="funcionario" class="col-sm-2 col-form-label text-center">
                            <s:text name="label.codigo.funcionario"/>:
                        </label>
                        <div class="col-sm-5">
                            <s:textfield
                                cssClass="form-control"
                                id="funcionario"
                                name="compromissosVo.codigoFuncionario"/>
                        </div>
                    </div>

                    <div class="row align-items-center mt-3">
                        <label for="agenda" class="col-sm-2 col-form-label text-center">
                            <s:text name="label.codigo.agenda"/>:
                        </label>
                        <div class="col-sm-5">
                            <s:textfield
                                cssClass="form-control"
                                id="agenda"
                                name="compromissosVo.codigoAgenda"/>
                        </div>
                    </div>

                    <div class="row align-items-center mt-3">
                        <label for="data" class="col-sm-2 col-form-label text-center">
                            <s:text name="label.data"/>:
                        </label>
                        <div class="col-sm-5">
                            <s:textfield
                                cssClass="form-control"
                                id="data"
                                name="compromissosVo.data"
                                placeholder="dd/mm/yyyy"
                                maxlength="10"/>
                        </div>
                    </div>

                    <div class="row align-items-center mt-3">
                        <label for="horario" class="col-sm-2 col-form-label text-center">
                            <s:text name="label.horario"/>:
                        </label>
                        <div class="col-sm-5">
                            <s:textfield
                                cssClass="form-control"
                                id="horario"
                                name="compromissosVo.horario"
                                placeholder="hh:mm"
                                maxlength="5"/>
                        </div>
                    </div>

                </div>

                <div class="card-footer">
                    <div class="form-row">
                        <button class="btn btn-primary col-sm-4 offset-sm-1">
                            <s:text name="label.salvar"/>
                        </button>

                        <button type="reset" class="btn btn-secondary col-sm-4 offset-sm-2">
                            <s:text name="label.limpar.formulario"/>
                        </button>
                    </div>
                </div>

            </div>
        </s:form>
    </div>

    <script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>

    <script>
        function formatarData(input) {
            var valor = input.value.replace(/\D/g, '');

            if (valor.length > 2) {
                valor = valor.substring(0, 2) + '/'
                    + valor.substring(2);
            }

            if (valor.length > 5) {
                valor = valor.substring(0, 5) + '/'
                    + valor.substring(5, 9);
            }

            input.value = valor;
        }

        function formatarHorario(input) {
            var valor = input.value.replace(/\D/g, '');

            if (valor.length > 2) {
                valor = valor.substring(0, 2) + ':'
                    + valor.substring(2, 4);
            }

            input.value = valor;
        }

        document.getElementById('data').addEventListener('input', function() {
            formatarData(this);
        });

        document.getElementById('horario').addEventListener('input', function() {
            formatarHorario(this);
        });
    </script>

</body>
</html>