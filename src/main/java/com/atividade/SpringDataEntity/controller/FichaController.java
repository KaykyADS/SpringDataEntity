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

import com.atividade.SpringDataEntity.model.Ficha;
import com.atividade.SpringDataEntity.model.Consulta;
import com.atividade.SpringDataEntity.repository.FichaRepository;
import com.atividade.SpringDataEntity.repository.ConsultaRepository;

@Controller
public class FichaController {

    @Autowired
    private FichaRepository fichaRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @RequestMapping(name = "formularioFicha", value = "/formularioFicha", method = RequestMethod.GET)
    public ModelAndView fichaGet(@RequestParam Map<String, String> params, ModelMap model) {
        String acao = params.get("acao");
        Integer id = null;
        String idStr = params.get("id");
        if (idStr != null && !idStr.isBlank()) id = Integer.parseInt(idStr);

        Ficha ficha = new Ficha();
        List<Ficha> fichas = new ArrayList<>();
        String erro = "";
        String saida = "";

        try {
            if (id != null) {
                ficha = fichaRepository.getReferenceById(id);
                if ("excluir".equalsIgnoreCase(acao)) {
                    fichaRepository.deleteById(id);
                    saida = "Ficha excluída com sucesso.";
                    fichas = fichaRepository.findAll();
                    ficha = null;
                } else if ("atualizar".equalsIgnoreCase(acao)) {
                	ficha = fichaRepository.getReferenceById(id);
                    fichas = null;
                }
            }
        } catch (Exception e) {
            erro = "Erro: " + e.getMessage();
        }

        model.addAttribute("erro", erro);
        model.addAttribute("saida", saida);
        model.addAttribute("ficha", ficha);
        model.addAttribute("fichas", fichas);
        model.addAttribute("consultas", consultaRepository.findAll());

        return new ModelAndView("formularioFicha");
    }

    @RequestMapping(name = "formularioFicha", value = "/formularioFicha", method = RequestMethod.POST)
    public ModelAndView fichaPost(@RequestParam Map<String, String> params, ModelMap model) {
        Integer id = null;
        String idStr = params.get("id");
        if (idStr != null && !idStr.isBlank()) id = Integer.parseInt(idStr);

        String observacoes = params.get("observacoes");
        String idConsultaStr = params.get("consultaId");
        Long idConsulta = null;
        if (idConsultaStr != null && !idConsultaStr.isBlank()) idConsulta = Long.parseLong(idConsultaStr);

        String cmd = params.get("botao");

        Ficha ficha = new Ficha();
        List<Ficha> fichas = new ArrayList<>();
        String erro = "";
        String saida = "";

        try {
            if (!"Listar".equalsIgnoreCase(cmd)) {
                ficha.setId(id);
            }

            Consulta consulta = null;
            if (idConsulta != null) {
                consulta = consultaRepository.getReferenceById(idConsulta);
            }

            if ("Inserir".equalsIgnoreCase(cmd)) {
            	if (consulta != null) {
	                ficha.setObservacoes(observacoes);
	                ficha.setConsulta(consulta);                
	                fichaRepository.save(ficha);
	                saida = "Ficha cadastrada com sucesso.";
	                ficha = null;
            	} else erro ="Selecione uma consulta";
            }

            if ("Atualizar".equalsIgnoreCase(cmd)) {
                if (id != null) {
                    Ficha existente = fichaRepository.getReferenceById(id);
                    if (existente != null) {
                        existente.setObservacoes(observacoes);
                        existente.setConsulta(consulta);
                        fichaRepository.save(existente);
                        saida = "Ficha atualizada com sucesso.";
                        ficha = null;
                    } else {
                        erro = "Ficha não encontrada.";
                    }
                } else {
                    erro = "ID obrigatório para atualização.";
                }
            }

            if ("Listar".equalsIgnoreCase(cmd)) {
                fichas = fichaRepository.findAll();
            }

            if ("Buscar".equalsIgnoreCase(cmd)) {
                if (observacoes != null && !observacoes.isBlank()) {
                    // Ex: fichas = fichaRepository.findByObservacoesContainingIgnoreCase(observacoes);
                    erro = "Função de busca ainda não implementada.";
                } else {
                    erro = "Digite uma observação para buscar.";
                }
            }

        } catch (Exception e) {
            erro = "Erro ao processar: " + e.getMessage();
        }

        if (!"Listar".equalsIgnoreCase(cmd) && !"Buscar".equalsIgnoreCase(cmd)) {
            fichas = null;
        }

        model.addAttribute("erro", erro);
        model.addAttribute("saida", saida);
        model.addAttribute("ficha", ficha);
        model.addAttribute("fichas", fichas);
        model.addAttribute("consultas", consultaRepository.findAll());

        return new ModelAndView("formularioFicha");
    }
}
