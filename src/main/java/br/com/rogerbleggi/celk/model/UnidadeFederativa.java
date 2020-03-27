package br.com.rogerbleggi.celk.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

@Entity
@Table(name = "unidade_federativa")
public class UnidadeFederativa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotBlank
    @Column(name = "sigla", nullable = false, unique = true)
    private String sigla;

    @Column(name = "data_hora_cadastro", nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraCadastro;

    @Column(name = "data_hora_atualizacao", nullable = false)
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraAtualizacao;

    public UnidadeFederativa() {
    }

    private UnidadeFederativa(Builder builder) {
        this.id = builder.id;
        this.nome = builder.nome;
        this.sigla = builder.sigla;
        this.dataHoraCadastro = builder.dataHoraCadastro;
        this.dataHoraAtualizacao = builder.dataHoraAtualizacao;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Date getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    public void setDataHoraCadastro(Date dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
    }

    public Date getDataHoraAtualizacao() {
        return dataHoraAtualizacao;
    }

    public void setDataHoraAtualizacao(Date dataHoraAtualizacao) {
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

    public static final class Builder {
        private Long id;
        private @NotBlank String nome;
        private @NotBlank String sigla;
        private Date dataHoraCadastro;
        private Date dataHoraAtualizacao;

        private Builder() {
        }

        public UnidadeFederativa build() {
            return new UnidadeFederativa(this);
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder sigla(String sigla) {
            this.sigla = sigla;
            return this;
        }

        public Builder dataHoraCadastro(Date dataHoraCadastro) {
            this.dataHoraCadastro = dataHoraCadastro;
            return this;
        }

        public Builder dataHoraAtualizacao(Date dataHoraAtualizacao) {
            this.dataHoraAtualizacao = dataHoraAtualizacao;
            return this;
        }
    }
}
