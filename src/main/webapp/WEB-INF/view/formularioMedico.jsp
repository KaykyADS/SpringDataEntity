<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<title>Cadastro de Médicos</title>
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
		<form action="formularioMedico" method="post"
			class="bg-dark d-flex flex-column align-items-center justify-content-center">
			<fieldset class="p-3 border">
				<legend class="text-light text-center display-6 fw-bold">Cadastro de Médico</legend>

				<div class="mb-3">
					<label for="codigo" class="form-label text-light">Código</label>
					<input id="input-codigo" type="text" name="codigo" class="form-control"
						placeholder="Código do médico"
						value="<c:out value='${medico.codigo}'/>"
						onchange="checarInput()" onkeyup="checarInput()" />
				</div>

				<div class="mb-3">
					<label for="nome" class="form-label text-light">Nome</label>
					<input id="input-nome" type="text" name="nome" class="form-control"
						placeholder="Nome do médico"
						value="<c:out value='${medico.nome}'/>"
						onchange="checarInput()" onkeyup="checarInput()" />
				</div>

				<div class="mb-3">
					<label for="rua" class="form-label text-light">Rua</label>
					<input id="input-rua" type="text" name="rua" class="form-control"
						placeholder="Rua"
						value="<c:out value='${medico.rua}'/>"
						onchange="checarInput()" onkeyup="checarInput()" />
				</div>

				<div class="mb-3">
					<label for="numero" class="form-label text-light">Número</label>
					<input id="input-numero" type="number" name="numero" class="form-control"
						placeholder="Número"
						value="<c:out value='${medico.numero}'/>"
						onchange="checarInput()" onkeyup="checarInput()" />
				</div>

				<div class="mb-3">
					<label for="cep" class="form-label text-light">CEP</label>
					<input id="input-cep" type="number" name="cep" class="form-control"
						placeholder="CEP"
						value="<c:out value='${medico.cep}'/>"
						onchange="checarInput()" onkeyup="checarInput()" />
				</div>

				<div class="mb-3">
					<label for="complemento" class="form-label text-light">Complemento</label>
					<input id="input-complemento" type="text" name="complemento" class="form-control"
						placeholder="Complemento"
						value="<c:out value='${medico.complemento}'/>"
						onchange="checarInput()" onkeyup="checarInput()" />
				</div>

				<div class="mb-3">
					<label for="contato" class="form-label text-light">Contato</label>
					<input id="input-contato" type="number" name="contato" class="form-control"
						placeholder="Contato"
						value="<c:out value='${medico.contato}'/>"
						onchange="checarInput()" onkeyup="checarInput()" />
				</div>

				<div class="mb-3">
					<label for="especialidadeId" class="form-label text-light">Especialidade</label>
					<select id="input-especialidade" name="idEspecialidade" class="form-select"
						onchange="checarInput()">
						<option value="">Selecione</option>
						<c:forEach var="e" items="${especialidades}">
							<option value="${e.id}"
								<c:if test="${medico.especialidade != null && medico.especialidade.id == e.id}">selected</c:if>>
								${e.nome}</option>
						</c:forEach>
					</select>
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

		<c:if test="${not empty medicos}">
			<table class="table table-dark table-striped mt-4">
				<thead>
					<tr>
						<th>Código</th>
						<th>Nome</th>
						<th>Especialidade</th>
						<th>Excluir</th>
						<th>Editar</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="m" items="${medicos}">
						<tr>
							<td>${m.codigo}</td>
							<td>${m.nome}</td>
							<td>${m.especialidade.nome}</td>
							<td><a href="formularioMedico?acao=excluir&codigo=${m.codigo}" class="btn btn-danger btn-sm">Excluir</a></td>
							<td><a href="formularioMedico?acao=atualizar&codigo=${m.codigo}" class="btn btn-warning btn-sm">Editar</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>

	<script>
		function checarInput() {
			const campos = [
				'input-codigo', 'input-nome', 'input-rua', 'input-numero',
				'input-cep', 'input-complemento', 'input-contato', 'input-especialidade'
			];
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
