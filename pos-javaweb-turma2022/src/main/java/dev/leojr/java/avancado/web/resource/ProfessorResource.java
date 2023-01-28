package dev.leojr.java.avancado.web.resource;

import dev.leojr.java.avancado.web.model.Professor;
import dev.leojr.java.avancado.web.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorResource {

    @Autowired
    private ProfessorService professorService;

    @PostMapping
    public ResponseEntity<Professor> salvar(@RequestBody Professor aluno){
        return ResponseEntity.ok(professorService.salvar(aluno));
    }

   @GetMapping
   public ResponseEntity<List<Professor>> getAlunos(){
        return ResponseEntity.ok(professorService.listarProfessores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> consultaPorId(@PathVariable int id){
        return ResponseEntity.ok(professorService.consultarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Professor> deletePorId(@PathVariable int id){
        professorService.excluir(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Professor> alterar(@RequestBody Professor aluno){
        return ResponseEntity.ok(professorService.alterar(aluno));
    }

    @GetMapping("/like/{nome}")
    public ResponseEntity<List<Professor>> listarPorLike(@PathVariable String nome){
        return ResponseEntity.ok(professorService.buscarAlunoLike(nome));
    }

}
