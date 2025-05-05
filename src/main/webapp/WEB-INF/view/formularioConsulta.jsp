<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<title>Cadastro de Consultas</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="resources/css/style.css" type="text/css" rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body class="bg-dark">
	<div class="d-flex justify-content-start m-3">
		<a href="/AulaSpring/" class="text-decoration-none text-light fw-bold">
			<img style="max-width: 50px;"
			src="${pageContext.request.contextPath}/resources/assets/setinha.png"
			alt="Voltar">
		</a>
	</div>

	<div class="container m-auto">
		<form action="formularioConsulta" method="post"
			class="bg-dark d-flex flex-column align-items-center justify-content-center">
			<fieldset class="p-3 border">
				<legend class="text-light text-center display-6 fw-bold">Cadastro
					de Consulta</legend>

				<input type="hidden" name="idConsulta"
					value="${consulta.idConsulta}" />

				<div class="mb-3">
					<label for="pacienteId" class="form-label text-light">Paciente</label>
					<select id="input-paciente" name="pacienteId" class="form-select"
						onchange="checarInput()">
						<option value="">Selecione</option>
						<c:forEach var="p" items="${pacientes}">
							<option value="${p.numBenef}"
								<c:if
									test="${consulta.paciente != null && consulta.paciente.numBenef == p.numBenef}">selected</c:if>>
								${p.nome}</option>
						</c:forEach>
					</select>
				</div>

				<div class="mb-3">
					<label for="medicoCodigo" class="form-label text-light">Médico</label>
					<select id="input-medico" name="medicoCodigo" class="form-select"
						onchange="checarInput()">
						<option value="">Selecione</option>
						<c:forEach var="m" items="${medicos}">
							<option value="${m.codigo}"
								<c:if test="${consulta.medico != null && consulta.medico.codigo == m.codigo}">selected</c:if>>${m.nome}</option>
						</c:forEach>
					</select>
				</div>

				<div class="mb-3">
					<label for="data" class="form-label text-light">Data</label> <input
						id="input-data" type="date" name="data" class="form-control"
						value="<c:out value='${consulta.data}'/>" onchange="checarInput()" />
				</div>

				<div class="mb-3">
					<label for="hora" class="form-label text-light">Hora</label> <input
						id="input-hora" type="time" name="hora" class="form-control"
						value="<c:out value='${consulta.hora}'/>" onchange="checarInput()" />
				</div>

				<div class="d-flex gap-2">
					<button id="botao-inserir" type="submit" name="botao"
						value="Inserir" class="btn btn-success w-50">Inserir</button>
					<button type="submit" name="botao" value="Atualizar"
						class="btn btn-warning w-50">Atualizar</button>
					<button type="submit" name="botao" value="Listar"
						class="btn btn-secondary w-50">Listar</button>
					<button type="submit" name="botao" value="Buscar"
						class="btn btn-info w-50">Buscar</button>
					<button type="submit" name="botao" value="QTD"
						class="btn btn-info w-50">QTD Consultas</button>
				</div>
			</fieldset>
		</form>

		<c:if test="${not empty saida}">
			<div class="mt-3 alert alert-primary w-100 mx-auto">
				<c:out value="${saida}" />
			</div>
		</c:if>

		<c:if test="${not empty erro}">
			<div class="mt-3 alert alert-danger w-auto mx-auto">
				<c:out value="${erro}" />
			</div>
		</c:if>
		
		<c:if test="${not empty consultasDia}">
			<div class="mt-3 alert alert-primary w-auto mx-auto">
				Quantidade de consultas: 
				<c:out value="${consultasDia}" />
			</div>
		</c:if>

		<c:if test="${not empty consultas}">
			<table class="table table-dark table-striped mt-4">
				<thead>
					<tr>
						<th>Paciente</th>
						<th>Médico</th>
						<th>Data</th>
						<th>Hora</th>
						<th>Excluir</th>
						<th>Editar</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="c" items="${consultas}">
						<tr>
							<td>${c.paciente.nome}</td>
							<td>${c.medico.nome}</td>
							<td>${c.data}</td>
							<td>${c.hora}</td>
							<td><a
								href="formularioConsulta?acao=excluir&idConsulta=${c.idConsulta}"
								class="btn btn-danger btn-sm">Excluir</a></td>
							<td><a
								href="formularioConsulta?acao=atualizar&idConsulta=${c.idConsulta}"
								class="btn btn-warning btn-sm">Editar</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>

	<script>
        function checarInput() {
            const campos = ['input-paciente', 'input-medico', 'input-data', 'input-hora'];
            let preenchido = true;
            campos.forEach(id => {
                if (document.getElementById(id).value.trim() === '') {
                    preenchido = false;
                }
            });
            document.getElementById("botao-inserir").disabled = !preenchido;
        }
        checarInput();
    </script>
</body>
</html>
