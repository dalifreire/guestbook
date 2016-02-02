package br.com.guestbook.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.jerimum.fw.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the cidade database table.
 * 
 * @author Dali Freire - dalifreire@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "cidade")
public class Cidade extends AbstractEntity<Integer> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @NotNull(message = "msg.error.constraint.notnull.xxx")
    @Size(min = 1, max = 250, message = "msg.error.constraint.maxsize.xxx")
    @Column(nullable = false, length = 250)
    private String nome;

    @Size(min = 1, max = 450, message = "msg.error.constraint.maxsize.xxx")
    @Column(length = 450)
    private String descricao;

    @NotNull(message = "msg.error.constraint.notnull.xxx")
    @ManyToOne
    @JoinColumn(name = "id_uf", nullable = false)
    private Uf uf;

    @NotNull(message = "msg.error.constraint.notnull.xxx")
    @Column(nullable = false)
    private Boolean ativo;

    @Override
    public Integer getPK() {
        return this.id;
    }

    @Override
    public String getLabel() {
        return this.nome + "(" + this.uf + ")";
    }

}
