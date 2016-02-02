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
public class EventoDto extends AbstractDto {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String descricao;
    private String local;
    private Integer idCidade;
    private Timestamp dataInicio;
    private Timestamp dataFim;
    private byte[] molduraFoto;

}
