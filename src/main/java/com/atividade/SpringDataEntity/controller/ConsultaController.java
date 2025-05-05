package com.atividade.SpringDataEntity.controller;

import java.time.LocalDate;
import java.time.LocalTime;
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

import com.atividade.SpringDataEntity.model.Consulta;
import com.atividade.SpringDataEntity.model.Medico;
import com.atividade.SpringDataEntity.model.Paciente;
import com.atividade.SpringDataEntity.repository.ConsultaRepository;
import com.atividade.SpringDataEntity.repository.MedicoRepository;
import com.atividade.SpringDataEntity.repository.PacienteRepository;

@Controller
public class ConsultaController {

    @Autowired
    private ConsultaRepository consultaRepo;

    @Autowired
    private PacienteRepository pacienteRepo;

    @Autowired
    private MedicoRepository medicoRepo;

    @RequestMapping(name = "formularioConsulta", value = "/formularioConsulta", method = RequestMethod.GET)
    public ModelAndView consultaGet(@RequestParam Map<String, String> params, ModelMap model) {
        String acao = params.get("acao");
        String idConsultaStr = params.get("idConsulta");

        Long idConsulta = null;
        if (idConsultaStr != null && !idConsultaStr.isBlank()) {
            idConsulta = Long.parseLong(idConsultaStr);
        }

        Consulta consulta = new Consulta();
        List<Consulta> consultas = new ArrayList<>();
        String erro = "";
        String saida = "";

        try {
            if (idConsulta != null) {
                consulta = consultaRepo.getReferenceById(idConsulta);
                if ("excluir".equalsIgnoreCase(acao)) {
                    consultaRepo.deleteById(idConsulta);
                    saida = "Consulta excluída com sucesso.";
                    consultas = consultaRepo.findAll();
                    consulta = null;
                } else if ("atualizar".equalsIgnoreCase(acao)) {
                	consulta = consultaRepo.getReferenceById(idConsulta);
                    consultas = null;
                }
            }
        } catch (Exception e) {
            erro = "Erro: " + e.getMessage();
        }

        model.addAttribute("consulta", consulta);
        model.addAttribute("consultas", consultas);
        model.addAttribute("erro", erro);
        model.addAttribute("saida", saida);
        model.addAttribute("medicos", medicoRepo.findAll());
        model.addAttribute("pacientes", pacienteRepo.findAll());

        return new ModelAndView("formularioConsulta");
    }

    @RequestMapping(name = "formularioConsulta", value = "/formularioConsulta", method = RequestMethod.POST)
    public ModelAndView consultaPost(@RequestParam Map<String, String> params, ModelMap model) {
        String cmd = params.get("botao");
        Long consultasDia = null;
        String idConsultaStr = params.get("idConsulta");
        String pacienteIdStr = params.get("pacienteId");
        String medicoIdStr = params.get("medicoCodigo");
        LocalDate data = null;
        if (params.get("data") != null && !params.get("data").isEmpty()) {
        	data = LocalDate.parse(params.get("data"));
        }
        LocalTime hora = null;
        if (params.get("hora") != null && !params.get("hora").isEmpty()) {
        	hora = LocalTime.parse((params.get("hora")));
        }

        Long idConsulta = (idConsultaStr != null && !idConsultaStr.isBlank()) ? Long.parseLong(idConsultaStr) : null;
        Long pacienteId = (pacienteIdStr != null && !pacienteIdStr.isBlank()) ? Long.parseLong(pacienteIdStr) : null;
        Long medicoCodigo = (medicoIdStr != null && !medicoIdStr.isBlank()) ? Long.parseLong(medicoIdStr) : null;

        Consulta consulta = new Consulta();
        List<Consulta> consultas = new ArrayList<>();
        String erro = "";
        String saida = "";

        try {
            if ("Inserir".equalsIgnoreCase(cmd)) {
                consulta.setIdConsulta(idConsulta);
                consulta.setData(data);
                consulta.setHora(hora);
                Paciente paciente = pacienteRepo.getReferenceById(pacienteId);
                Medico medico = medicoRepo.getReferenceById(medicoCodigo);
                consulta.setPaciente(paciente);
                consulta.setMedico(medico);
                consultaRepo.save(consulta);
                saida = "Consulta inserida com sucesso.";
                consulta = null;
            }

            if ("Atualizar".equalsIgnoreCase(cmd)) {
                if (idConsulta != null) {
                    Consulta existente = consultaRepo.getReferenceById(idConsulta);
                    if (existente != null) {
                        existente.setData(data);
                        existente.setHora(hora);
                        Paciente paciente = pacienteRepo.getReferenceById(pacienteId);
                        Medico medico = medicoRepo.getReferenceById(medicoCodigo);
                        existente.setPaciente(paciente);
                        existente.setMedico(medico);
                        consultaRepo.save(existente);
                        saida = "Consulta atualizada com sucesso.";
                        consulta = null;
                    } else {
                        erro = "Consulta não encontrada.";
                    }
                } else {
                    erro = "ID da consulta é obrigatório para atualização.";
                }
            }

            if ("Listar".equalsIgnoreCase(cmd)) {
                consultas = consultaRepo.findAll();
            }
            
            if ("Buscar".equalsIgnoreCase(cmd)) {
            	if (data != null) {
            		consultas = consultaRepo.findConsultaByData(data);
            	} else {
            		erro = "Insira uma data para buscar consultas";
            	}
            }
            if ("QTD".equalsIgnoreCase(cmd)) {
            	if (data != null) {
            		consultas = null;
            		consultasDia = consultaRepo.countConsultasByData(data);
            	} else {
            		erro = "Insira uma data para buscar consultas";
            	}
            }

        } catch (Exception e) {
            erro = "Erro: " + e.getMessage();
        }

        if (!"Listar".equalsIgnoreCase(cmd) && !"Buscar".equalsIgnoreCase(cmd)) {
            consultas = null;
        }

        model.addAttribute("consulta", consulta);
        model.addAttribute("consultas", consultas);
        model.addAttribute("erro", erro);
        model.addAttribute("saida", saida);
        model.addAttribute("medicos", medicoRepo.findAll());
        model.addAttribute("pacientes", pacienteRepo.findAll());
        model.addAttribute("consultasDia", consultasDia);

        return new ModelAndView("formularioConsulta");
    }
}
