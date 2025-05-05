<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<title>Cadastro de Fichas</title>
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
		<form action="formularioFicha" method="post"
			class="bg-dark d-flex flex-column align-items-center justify-content-center">
			<fieldset class="p-3 border">
				<legend class="text-light text-center display-6 fw-bold">Cadastro
					de Ficha</legend>
				<input type="hidden" name="id" value="${ficha.id}" />
				<div class="mb-3">
					<label for="observacoes" class="form-label text-light">Observações</label>
					<textarea id="input-observacoes" name="observacoes"
						class="form-control" rows="3" placeholder="Digite as observações"
						onchange="checarInput()" onkeyup="checarInput()"><c:out value='${ficha.observacoes}'/></textarea>
				</div>

				<div class="mb-3">
					<label for="consulta" class="form-label text-light">Consulta</label>
					<select id="input-consulta" name="consultaId" class="form-select"
						onchange="checarInput()">
						<option value="">Selecione uma consulta</option>
						<c:forEach var="c" items="${consultas}">
							<option value="${c.idConsulta}" <c:if test="${ficha.consulta.idConsulta == c.idConsulta}">selected</c:if>>
								Consulta #${c.idConsulta}
							</option>
						</c:forEach>
					</select>
				</div>

				<div class="d-flex gap-2">
					<button id="botao-inserir" type="submit" name="botao" value="Inserir"
						class="btn btn-success w-50">Inserir</button>
					<button type="submit" name="botao" value="Atualizar"
						class="btn btn-warning w-50">Atualizar</button>
					<button type="submit" name="botao" value="Listar"
						class="btn btn-secondary w-50">Listar</button>
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

		<c:if test="${not empty fichas}">
			<table class="table table-dark table-striped mt-4">
				<thead>
					<tr>
						<th>ID</th>
						<th>Observações</th>
						<th>ID da Consulta</th>
						<th>Excluir</th>
						<th>Editar</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="f" items="${fichas}">
						<tr>
							<td>${f.id}</td>
							<td>${f.observacoes}</td>
							<td>${f.consulta.idConsulta}</td>
							<td><a href="formularioFicha?acao=excluir&id=${f.id}"
								class="btn btn-danger btn-sm">Excluir</a></td>
							<td><a href="formularioFicha?acao=atualizar&id=${f.id}"
								class="btn btn-warning btn-sm">Editar</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>

	<script>
		function checarInput() {
			const observacoes = document.getElementById('input-observacoes').value.trim();
			const consulta = document.getElementById('input-consulta').value;

			document.getElementById("botao-inserir").disabled = !(observacoes && consulta);
		}
		checarInput();
	</script>
</body>
</html>
