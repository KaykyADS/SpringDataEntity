<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<title>Cadastro de Especialidades</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/css/style.css" type="text/css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
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
		<form action="formularioEspecialidade" method="post"
			class="bg-dark d-flex flex-column align-items-center justify-content-center">
			<fieldset class="p-3 border">
				<legend class="text-light text-center display-6 fw-bold">Cadastro de Especialidade</legend>

				<div class="mb-3">
					<label for="id" class="form-label text-light">ID</label>
					<input id="input-id" type="number" name="id" class="form-control"
						placeholder="Digite o ID da especialidade"
						value="<c:out value='${especialidade.id}'/>"
						onchange="checarInput()" onkeyup="checarInput()" />
				</div>

				<div class="mb-3">
					<label for="nome" class="form-label text-light">Nome</label>
					<input id="input-nome" type="text" name="nome" class="form-control"
						placeholder="Digite o nome da especialidade"
						value="<c:out value='${especialidade.nome}'/>"
						onchange="checarInput()" onkeyup="checarInput()" />
				</div>

				<div class="d-flex gap-2">
					<button id="botao-inserir" type="submit" name="botao" value="Inserir" class="btn btn-success w-50">Inserir</button>
					<button type="submit" name="botao" value="Atualizar" class="btn btn-warning w-50">Atualizar</button>
					<button type="submit" name="botao" value="Listar" class="btn btn-secondary w-50">Listar</button>
					<button type="submit" name="botao" value="Buscar" class="btn btn-info w-50">Buscar</button>
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

		<c:if test="${not empty especialidades}">
			<table class="table table-dark table-striped mt-4">
				<thead>
					<tr>
						<th>ID</th>
						<th>Nome</th>
						<th>Excluir</th>
						<th>Editar</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="e" items="${especialidades}">
						<tr>
							<td>${e.id}</td>
							<td>${e.nome}</td>
							<td><a href="formularioEspecialidade?acao=excluir&id=${e.id}" class="btn btn-danger btn-sm">Excluir</a></td>
							<td><a href="formularioEspecialidade?acao=atualizar&id=${e.id}" class="btn btn-warning btn-sm">Editar</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>

	<script>
		function checarInput() {
			const id = document.getElementById('input-id').value.trim();
			const nome = document.getElementById('input-nome').value.trim();

			document.getElementById("botao-inserir").disabled = !(id && nome);
		}
		checarInput();
	</script>
</body>
</html>
