package com.atividade.SpringDataEntity.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.atividade.SpringDataEntity.model.Paciente;
import com.atividade.SpringDataEntity.repository.PacienteRepository;

@Controller
public class PacienteController {

	@Autowired
	private PacienteRepository repository;

	// GET - Carrega a tela
	@RequestMapping(name = "formularioPaciente", value = "/formularioPaciente", method = RequestMethod.GET)
	public ModelAndView pacienteGet(@RequestParam Map<String, String> params, ModelMap model) {
		String acao = params.get("acao");
		Long numBenef = null;
		String numBenefStr = params.get("numBenef");
		if (numBenefStr != null && !numBenefStr.isBlank())
			numBenef = Long.parseLong(numBenefStr);

		Paciente paciente = new Paciente();
		List<Paciente> pacientes = new ArrayList<>();
		String erro = "";
		String saida = "";

		try {
			if (numBenef != null) {
				paciente = repository.getReferenceById(numBenef);
				if ("excluir".equalsIgnoreCase(acao)) {
					repository.deleteById(numBenef);
					saida = "Paciente excluído com sucesso.";
					pacientes = repository.findAll();
					paciente = null;
				} else if ("atualizar".equalsIgnoreCase(acao)) {
					pacientes = null;
				}
			}
		} catch (Exception e) {
			erro = e.getMessage();
		}

		model.addAttribute("erro", erro);
		model.addAttribute("saida", saida);
		model.addAttribute("paciente", paciente);
		model.addAttribute("pacientes", pacientes);

		return new ModelAndView("formularioPaciente");
	}

	// POST - Lida com botões
	@RequestMapping(name = "formularioPaciente", value = "/formularioPaciente", method = RequestMethod.POST)
	public ModelAndView pacientePost(@RequestParam Map<String, String> params, ModelMap model) {
		Long numBenef = null;
		String numBenefStr = params.get("numBenef");
		if (numBenefStr != null && !numBenefStr.isBlank())
			numBenef = Long.parseLong(numBenefStr);
		String nome = params.get("nome");
		String rua = params.get("rua");
		String cep = params.get("cep");
		String complemento = params.get("complemento");
		String telefone = params.get("telefone");
		String cmd = params.get("botao");

		Paciente paciente = new Paciente();
		List<Paciente> pacientes = new ArrayList<>();
		String saida = "";
		String erro = "";

		try {
			if (!"Listar".equalsIgnoreCase(cmd)) {
				paciente.setNumBenef(numBenef);
			}

			if ("Inserir".equalsIgnoreCase(cmd)) {
				paciente.setNome(nome);
				paciente.setRua(rua);
				paciente.setCep(cep);
				paciente.setComplemento(complemento);
				paciente.setTelefone(telefone);
				repository.save(paciente);
				saida = "Paciente cadastrado com sucesso.";
				paciente = null;
			}

			if ("Atualizar".equalsIgnoreCase(cmd)) {
				if (numBenef != null) {
					paciente = repository.getReferenceById(numBenef);
					if (paciente != null) {
						paciente.setNome(nome);
						paciente.setRua(rua);
						paciente.setCep(cep);
						paciente.setComplemento(complemento);
						paciente.setTelefone(telefone);
						repository.save(paciente);
						saida = "Paciente atualizado com sucesso.";
						paciente = null;
					} else {
						erro = "Paciente não encontrado.";
					}
				} else {
					erro = "Número do Beneficiário obrigatório para atualização.";
				}
			}

			if ("Listar".equalsIgnoreCase(cmd)) {
				pacientes = repository.findAll();
			}

			if ("Buscar".equalsIgnoreCase(cmd)) {
				if (nome != null && !nome.isBlank() && (telefone == null || telefone.isBlank())) {
					pacientes = repository.findByNomeContainingIgnoreCaseOrderByNomeAsc(nome);
					if (pacientes == null || pacientes.isEmpty()) {
						erro = "Paciente não encontrado.";
					}
				} else if (telefone != null && !telefone.isBlank() && (nome == null || nome.isBlank())) {
					paciente = repository.findFirstByTelefone(telefone);
					if (paciente == null) {
						erro = "Paciente não encontrado";
					} else {
						pacientes.add(paciente);
					}
				} else {
					erro = "Informe apenas o nome ou telefone para busca.";
				}
			}

		} catch (Exception e) {
			erro = "Erro ao processar: " + e.getMessage();
		}

		if (!"Listar".equalsIgnoreCase(cmd) && !"Buscar".equalsIgnoreCase(cmd)) {
			pacientes = null;
		}

		model.addAttribute("erro", erro);
		model.addAttribute("saida", saida);
		model.addAttribute("paciente", paciente);
		model.addAttribute("pacientes", pacientes);

		return new ModelAndView("formularioPaciente");
	}
}
