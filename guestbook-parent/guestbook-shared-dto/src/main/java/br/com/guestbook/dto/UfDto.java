package br.com.guestbook.dto;

import br.com.jerimum.fw.dto.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, of = "id")
public class UfDto extends AbstractDto {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String sigla;
    private String nome;
    private Boolean ativo;

}
