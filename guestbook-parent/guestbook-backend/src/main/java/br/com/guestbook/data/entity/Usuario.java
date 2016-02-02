package br.com.guestbook.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.jerimum.fw.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the usuario database table.
 * 
 * @author Dali Freire - dalifreire@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "usuario")
public class Usuario extends AbstractEntity<Integer> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @Column(nullable = false, length = 200)
    private String nome;

    @Column(unique = true, nullable = false, length = 20)
    private String login;

    @Column(nullable = false, length = 256)
    private String senha;

    @Column(nullable = false)
    private Boolean administrador;

    @Column(nullable = false)
    private Boolean ativo;

    @Override
    public Integer getPK() {
        return this.id;
    }

    @Override
    public String getLabel() {
        return this.nome;
    }

}
