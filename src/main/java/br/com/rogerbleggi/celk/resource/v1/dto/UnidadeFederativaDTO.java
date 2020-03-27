package br.com.rogerbleggi.celk.resource.v1.dto;

import br.com.rogerbleggi.celk.util.LocalDateTimeDeserializer;
import br.com.rogerbleggi.celk.util.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UnidadeFederativaDTO implements Serializable {

    private Long id;

    private String nome;
    private String sigla;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dataHoraAtualizacao;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dataHoraCadastro;

    public UnidadeFederativaDTO() {
    }

    private UnidadeFederativaDTO(Builder builder) {
        this.id = builder.id;
        this.nome = builder.nome;
        this.sigla = builder.sigla;
        this.dataHoraAtualizacao = builder.dataHoraAtualizacao;
        this.dataHoraCadastro = builder.dataHoraCadastro;
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

    public LocalDateTime getDataHoraAtualizacao() {
        return dataHoraAtualizacao;
    }

    public void setDataHoraAtualizacao(LocalDateTime dataHoraAtualizacao) {
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

    public LocalDateTime getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    public void setDataHoraCadastro(LocalDateTime dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
    }

    public static final class Builder {
        private Long id;
        private String nome;
        private String sigla;
        private LocalDateTime dataHoraAtualizacao;
        private LocalDateTime dataHoraCadastro;

        private Builder() {
        }

        public UnidadeFederativaDTO build() {
            return new UnidadeFederativaDTO(this);
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

        public Builder dataHoraAtualizacao(LocalDateTime dataHoraAtualizacao) {
            this.dataHoraAtualizacao = dataHoraAtualizacao;
            return this;
        }

        public Builder dataHoraCadastro(LocalDateTime dataHoraCadastro) {
            this.dataHoraCadastro = dataHoraCadastro;
            return this;
        }
    }
}
