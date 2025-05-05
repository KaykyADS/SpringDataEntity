<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<title>Cadastro de Pacientes</title>
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
		<form action="formularioPaciente" method="post"
			class="bg-dark d-flex flex-column align-items-center justify-content-center">
			<fieldset class="p-3 border">
				<legend class="text-light text-center display-6 fw-bold">Cadastro
					de Paciente</legend>

				<div class="mb-3">
					<label for="numBenef" class="form-label text-light">Número
						do Beneficiário</label> <input id="input-numBenef" type="number"
						name="numBenef" class="form-control"
						placeholder="Digite o número do beneficiário"
						value="<c:out value='${paciente.numBenef}'/>"
						onchange="checarInput()" onkeyup="checarInput()" />
				</div>

				<div class="mb-3">
					<label for="nome" class="form-label text-light">Nome</label>
					<div class="d-flex">
						<input id="input-nome" type="text" name="nome"
							class="form-control me-2" placeholder="Digite o nome"
							value="<c:out value='${paciente.nome}'/>"
							onchange="checarInput()" onkeyup="checarInput()" />
						<button type="submit" name="botao" value="Buscar"
							class="btn btn-info">Buscar</button>
					</div>
				</div>

				<div class="mb-3">
					<label for="rua" class="form-label text-light">Rua</label> <input
						id="input-rua" type="text" name="rua" class="form-control"
						placeholder="Digite a rua"
						value="<c:out value='${paciente.rua}'/>" onchange="checarInput()"
						onkeyup="checarInput()" />
				</div>

				<div class="mb-3">
					<label for="cep" class="form-label text-light">CEP</label> <input
						id="input-cep" type="number" name="cep" class="form-control"
						placeholder="Digite o CEP"
						value="<c:out value='${paciente.cep}'/>" onchange="checarInput()"
						onkeyup="checarInput()" />
				</div>

				<div class="mb-3">
					<label for="complemento" class="form-label text-light">Complemento</label>
					<input id="input-complemento" type="text" name="complemento"
						class="form-control" placeholder="Digite o complemento (opcional)"
						value="<c:out value='${paciente.complemento}'/>" />
				</div>

				<div class="mb-3">
					<label for="telefone" class="form-label text-light">Telefone</label>
					<div class="d-flex">
						<input id="input-telefone" type="number" name="telefone"
							class="form-control" placeholder="Digite o telefone"
							value="<c:out value='${paciente.telefone}'/>"
							onchange="checarInput()" onkeyup="checarInput()" />
						<button type="submit" name="botao" value="Buscar"
							class="btn btn-info">Buscar</button>
					</div>
				</div>

				<div class="d-flex gap-2">
					<button id="botao-inserir" type="submit" name="botao"
						value="Inserir" class="btn btn-success w-50">Inserir</button>
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

		<c:if test="${not empty pacientes}">
			<table class="table table-dark table-striped mt-4">
				<thead>
					<tr>
						<th>Nº Beneficiário</th>
						<th>Nome</th>
						<th>Rua</th>
						<th>CEP</th>
						<th>Complemento</th>
						<th>Telefone</th>
						<th>Excluir</th>
						<th>Editar</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="p" items="${pacientes}">
						<tr>
							<td>${p.numBenef}</td>
							<td>${p.nome}</td>
							<td>${p.rua}</td>
							<td>${p.cep}</td>
							<td>${p.complemento}</td>
							<td>${p.telefone}</td>
							<td><a
								href="formularioPaciente?acao=excluir&numBenef=${p.numBenef}"
								class="btn btn-danger btn-sm">Excluir</a></td>
							<td><a
								href="formularioPaciente?acao=atualizar&numBenef=${p.numBenef}"
								class="btn btn-warning btn-sm">Editar</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>

	<script>
		function checarInput() {
			const numBenef = document.getElementById('input-numBenef').value
					.trim();
			const nome = document.getElementById('input-nome').value.trim();
			const rua = document.getElementById('input-rua').value.trim();
			const cep = document.getElementById('input-cep').value.trim();
			const telefone = document.getElementById('input-telefone').value
					.trim();

			if (!numBenef || !nome || !rua || !cep || !telefone) {
				document.getElementById("botao-inserir").disabled = true;
			} else {
				document.getElementById("botao-inserir").disabled = false;
			}
		}
		checarInput();
	</script>
</body>
</html>
