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
public class UsuarioDto extends AbstractDto {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String login;
    private String senha;
    private Boolean administrador;
    private Boolean ativo;

}
