package dev.leojr.java.avancado.web.service;

import dev.leojr.java.avancado.web.dto.ProfessorDTO;
import dev.leojr.java.avancado.web.model.Professor;
import dev.leojr.java.avancado.web.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private JmsTemplate jmsTemplate;

    public ProfessorDTO salvar(Professor professor){

        professor = professorRepository.save(professor);
        jmsTemplate.convertAndSend("professor_queue", professor);
        return ProfessorDTO.builder()
                .nome(professor.getNome())
                .build();
        //return professorRepository.save(professor);
    }

    public List<Professor> listarProfessores(){
        return professorRepository.findAll();
    }

    public Professor consultarPorId(int id){
        return professorRepository.findById(id).orElseThrow();
    }

    public void excluir(int id){
        professorRepository.deleteById(id);
    }

    public Professor alterar(Professor professor){
        if(Objects.isNull(professor.getId())){
            throw new RuntimeException("ID não preenchido");
        }
        return professorRepository.save(professor);
    }

    public List<Professor> buscarAlunoLike(String nome){
        return professorRepository.buscarProfessorPorNomeLike(nome);
    }

}
