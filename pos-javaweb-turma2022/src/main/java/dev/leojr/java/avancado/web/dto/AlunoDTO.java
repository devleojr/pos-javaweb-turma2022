package dev.leojr.java.avancado.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AlunoDTO {

    private String nome;
    private String matricula;

}
