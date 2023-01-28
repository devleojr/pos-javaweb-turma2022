package dev.leojr.java.avancado.web.service;

import dev.leojr.java.avancado.web.dto.CursoDTO;
import dev.leojr.java.avancado.web.model.Curso;
import dev.leojr.java.avancado.web.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private JmsTemplate jmsTemplate;

    public CursoDTO salvar(Curso curso){

        curso = cursoRepository.save(curso);
        jmsTemplate.convertAndSend("curso_queue", curso);
        //return cursoRepository.save(curso);
        return CursoDTO.builder()
                .descricao(curso.getDescricao())
                .build();
    }

    public List<Curso> listarCursos(){
        return cursoRepository.findAll();
    }

    public Curso consultarPorId(int id){
        return cursoRepository.findById(id).orElseThrow();
    }

    public void excluir(int id){
        cursoRepository.deleteById(id);
    }

    public Curso alterar(Curso curso){
        if(Objects.isNull(curso.getId())){
            throw new RuntimeException("ID não preenchido");
        }
        return cursoRepository.save(curso);
    }
    public List<Curso> buscarCursoLike(String descricao){
        return cursoRepository.buscarCursoPorDescricaoLike(descricao);
    }
}
