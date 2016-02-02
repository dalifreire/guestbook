package br.com.guestbook.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.jerimum.fw.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the uf database table.
 * 
 * @author Dali Freire - dalifreire@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "uf")
public class Uf extends AbstractEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @NotNull(message = "msg.error.constraint.notnull.xxx")
    @Size(min = 1, max = 4, message = "msg.error.constraint.maxsize.xxx")
    @Column(unique = true, nullable = false, length = 4)
    private String sigla;

    @NotNull(message = "msg.error.constraint.notnull.xxx")
    @Size(min = 1, max = 250, message = "msg.error.constraint.maxsize.xxx")
    @Column(nullable = false, length = 450)
    private String nome;

    @NotNull(message = "msg.error.constraint.notnull.xxx")
    @Column(nullable = false)
    private Boolean ativo;


    @Override
    public Long getPK() {
        return this.id;
    }

    @Override
    public String getLabel() {
        return this.sigla;
    }

}
