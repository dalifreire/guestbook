package br.com.guestbook.data.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.jerimum.fw.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the evento database table.
 * 
 * @author Dali Freire - dalifreire@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "evento")
public class Evento extends AbstractEntity<Integer> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @Column(unique = true, nullable = false, length = 100)
    private String nome;

    @Lob
    private String descricao;

    @Column(length = 450)
    private String local;

    // bi-directional many-to-one association to Cidade
    @ManyToOne
    @JoinColumn(name = "id_cidade", nullable = false)
    private Cidade cidade;

    @Column(name = "data_inicio")
    private Timestamp dataInicio;

    @Column(name = "data_fim")
    private Timestamp dataFim;

    @Lob
    @Column(name = "moldura_foto", nullable = false)
    private byte[] molduraFoto;

    @Override
    public Integer getPK() {
        return this.id;
    }

    @Override
    public String getLabel() {
        return this.nome;
    }

}
