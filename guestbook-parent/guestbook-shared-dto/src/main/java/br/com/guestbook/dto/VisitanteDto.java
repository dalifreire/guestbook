package br.com.guestbook.dto;

import java.sql.Timestamp;

import br.com.jerimum.fw.dto.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, of = "id")
public class VisitanteDto extends AbstractDto {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String email;
    private String telefone;
    private String bairro;
    private Timestamp data;
    private byte[] foto;
    private Integer idCidade;
    private Integer idEspecialidade;
    private Integer idEvento;

}
