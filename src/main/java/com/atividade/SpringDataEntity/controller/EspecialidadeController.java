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

import com.atividade.SpringDataEntity.model.Especialidade;
import com.atividade.SpringDataEntity.repository.EspecialidadeRepository;

@Controller
public class EspecialidadeController {

    @Autowired
    private EspecialidadeRepository repository;

    @RequestMapping(name = "formularioEspecialidade", value = "/formularioEspecialidade", method = RequestMethod.GET)
    public ModelAndView especialidadeGet(@RequestParam Map<String, String> params, ModelMap model) {
        String acao = params.get("acao");
        Long id = null;
        String idStr = params.get("id");
        if (idStr != null && !idStr.isBlank()) id = Long.parseLong(idStr);

        Especialidade especialidade = new Especialidade();
        List<Especialidade> especialidades = new ArrayList<>();
        String erro = "";
        String saida = "";

        try {
            if (id != null) {
                especialidade = repository.getReferenceById(id);
                if ("excluir".equalsIgnoreCase(acao)) {
                    repository.deleteById(id);
                    saida = "Especialidade excluída com sucesso.";
                    especialidades = repository.findAll();
                    especialidade = null;
                } else if ("atualizar".equalsIgnoreCase(acao)) {
                    especialidades = null;
                }
            }
        } catch (Exception e) {
            erro = e.getMessage();
        }

        model.addAttribute("erro", erro);
        model.addAttribute("saida", saida);
        model.addAttribute("especialidade", especialidade);
        model.addAttribute("especialidades", especialidades);

        return new ModelAndView("formularioEspecialidade");
    }

    @RequestMapping(name = "formularioEspecialidade", value = "/formularioEspecialidade", method = RequestMethod.POST)
    public ModelAndView especialidadePost(@RequestParam Map<String, String> params, ModelMap model) {
        Long id = null;
        String idStr = params.get("id");
        if (idStr != null && !idStr.isBlank()) id = Long.parseLong(idStr);
        String nome = params.get("nome");
        String cmd = params.get("botao");

        Especialidade especialidade = new Especialidade();
        List<Especialidade> especialidades = new ArrayList<>();
        String saida = "";
        String erro = "";

        try {
            if (!"Listar".equalsIgnoreCase(cmd)) {
                especialidade.setId(id);
            }

            if ("Inserir".equalsIgnoreCase(cmd)) {
                especialidade.setNome(nome);
                repository.save(especialidade);
                saida = "Especialidade cadastrada com sucesso.";
                especialidade = null;
            }

            if ("Atualizar".equalsIgnoreCase(cmd)) {
                if (id != null) {
                    Especialidade existente = repository.getReferenceById(id);
                    if (existente != null) {
                        existente.setNome(nome);
                        repository.save(existente);
                        saida = "Especialidade atualizada com sucesso.";
                        especialidade = null;
                    } else {
                        erro = "Especialidade não encontrada.";
                    }
                } else {
                    erro = "ID da especialidade obrigatório para atualização.";
                }
            }

            if ("Listar".equalsIgnoreCase(cmd)) {
                especialidades = repository.findAll();
            }
        } catch (Exception e) {
            erro = "Erro ao processar: " + e.getMessage();
        }

        if (!"Listar".equalsIgnoreCase(cmd) && !"Buscar".equalsIgnoreCase(cmd)) {
            especialidades = null;
        }

        model.addAttribute("erro", erro);
        model.addAttribute("saida", saida);
        model.addAttribute("especialidade", especialidade);
        model.addAttribute("especialidades", especialidades);

        return new ModelAndView("formularioEspecialidade");
    }
}
