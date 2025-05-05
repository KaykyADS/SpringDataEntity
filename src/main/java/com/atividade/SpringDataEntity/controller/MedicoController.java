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

import com.atividade.SpringDataEntity.model.Medico;
import com.atividade.SpringDataEntity.repository.EspecialidadeRepository;
import com.atividade.SpringDataEntity.repository.MedicoRepository;
import com.atividade.SpringDataEntity.model.Especialidade;

@Controller
public class MedicoController {

    @Autowired
    private MedicoRepository repository;
    
    @Autowired 
    private EspecialidadeRepository eRepository;

    @RequestMapping(name = "formularioMedico", value = "/formularioMedico", method = RequestMethod.GET)
    public ModelAndView medicoGet(@RequestParam Map<String, String> params, ModelMap model) {
        String acao = params.get("acao");
        Long codigo = null;
        String codigoStr = params.get("codigo");
        if (codigoStr != null && !codigoStr.isBlank()) codigo = Long.parseLong(codigoStr);
        Medico medico = new Medico();
        List<Medico> medicos = new ArrayList<>();
        String erro = "";
        String saida = "";

        try {
            if (codigo != null) {
                medico = repository.getReferenceById(codigo);
                if ("excluir".equalsIgnoreCase(acao)) {
                    repository.deleteById(codigo);
                    saida = "Médico excluído com sucesso.";
                    medicos = repository.findAll();
                    medico = null;
                } else if ("atualizar".equalsIgnoreCase(acao)) {
                    medicos = null;
                }
            }
        } catch (Exception e) {
            erro = e.getMessage();
        }

        model.addAttribute("erro", erro);
        model.addAttribute("saida", saida);
        model.addAttribute("medico", medico);
        model.addAttribute("medicos", medicos);
        model.addAttribute("especialidades", eRepository.findAll());

        return new ModelAndView("formularioMedico");
    }

    @RequestMapping(name = "formularioMedico", value = "/formularioMedico", method = RequestMethod.POST)
    public ModelAndView medicoPost(@RequestParam Map<String, String> params, ModelMap model) {
    	Long codigo = null;
        String codigoStr = params.get("codigo");
        if (codigoStr != null && !codigoStr.isBlank()) codigo = Long.parseLong(codigoStr);
        String nome = params.get("nome");
        String rua = params.get("rua");
        String numero = params.get("numero");
        String cep = params.get("cep");
        String complemento = params.get("complemento");
        String contato = params.get("contato");
        String idEspecialidadeStr = params.get("idEspecialidade");
        Long idEspecialidade = null;
        if (idEspecialidadeStr != null && !idEspecialidadeStr.isBlank()) idEspecialidade = Long.parseLong(idEspecialidadeStr);
        String cmd = params.get("botao");

        Medico medico = new Medico();
        List<Medico> medicos = new ArrayList<>();
        String saida = "";
        String erro = "";

        try {
            if (!"Listar".equalsIgnoreCase(cmd)) {
                medico.setCodigo(codigo);
            }
            Especialidade especialidade = new Especialidade();
            if (idEspecialidade != null) {
                especialidade = eRepository.getReferenceById(idEspecialidade);
                medico.setEspecialidade(especialidade);
            }

            if ("Inserir".equalsIgnoreCase(cmd)) {
                medico.setNome(nome);
                medico.setRua(rua);
                medico.setNumero(numero);
                medico.setCep(cep);
                medico.setComplemento(complemento);
                medico.setContato(contato);
                medico.setEspecialidade(especialidade);
                repository.save(medico);
                saida = "Médico cadastrado com sucesso.";
                medico = null;
            }

            if ("Atualizar".equalsIgnoreCase(cmd)) {
                if (codigo != null) {
                    Medico existente = repository.getReferenceById(codigo);
                    if (existente != null) {
                        existente.setNome(nome);
                        existente.setRua(rua);
                        existente.setNumero(numero);
                        existente.setCep(cep);
                        existente.setComplemento(complemento);
                        existente.setContato(contato);
                        existente.setEspecialidade(especialidade);
                        repository.save(existente);
                        saida = "Médico atualizado com sucesso.";
                        medico = null;
                    } else {
                        erro = "Médico não encontrado.";
                    }
                } else {
                    erro = "Código do médico obrigatório para atualização.";
                }
            }

            if ("Listar".equalsIgnoreCase(cmd)) {
                medicos = repository.findAll();
            }

            if ("Buscar".equalsIgnoreCase(cmd)) {
                if (idEspecialidade != null) {
                    medicos = repository.findByEspecialidade(idEspecialidade);
                    if (medicos == null) {
                        erro = "Médico não encontrado.";
                    }
                } else {
                    erro = "Informe o nome do médico para busca.";
                }
            }

        } catch (Exception e) {
            erro = "Erro ao processar: " + e.getMessage();
        }

        if (!"Listar".equalsIgnoreCase(cmd) && !"Buscar".equalsIgnoreCase(cmd)) {
            medicos = null;
        }

        model.addAttribute("erro", erro);
        model.addAttribute("saida", saida);
        model.addAttribute("medico", medico);
        model.addAttribute("medicos", medicos);
        model.addAttribute("especialidades", eRepository.findAll());

        return new ModelAndView("formularioMedico");
    }
}
