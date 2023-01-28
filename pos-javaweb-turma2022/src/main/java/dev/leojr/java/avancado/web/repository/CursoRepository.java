package dev.leojr.java.avancado.web.repository;

import dev.leojr.java.avancado.web.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {

    @Query("select c from Curso c order by c.descricao ASC")
    public List<Curso> listarOrdernadoPorNome();
    public List<Curso> findAllByOrderByNomeAsc();

    @Query("select c from Curso a where c.descricao like %:descricao% ")
    public List<Curso> buscarCursoPorDescricaoLike(@Param("descricao") String nome);
    public Curso findByDescricao(String descricao);
    public Curso findByIdAndDescricao(Integer id, String descricao);

}
