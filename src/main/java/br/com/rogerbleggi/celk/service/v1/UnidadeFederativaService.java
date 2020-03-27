package br.com.rogerbleggi.celk.service.v1;

import br.com.rogerbleggi.celk.model.UnidadeFederativa;
import br.com.rogerbleggi.celk.repository.UnidadeFederativaRepository;
import br.com.rogerbleggi.celk.repository.spec.UnidadeFederativaSpec;
import br.com.rogerbleggi.celk.resource.v1.dto.UnidadeFederativaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.stream.Collectors;

@Service
public class UnidadeFederativaService {

    public static final String AMERICA_SAO_PAULO = "America/Sao_Paulo";
    private final UnidadeFederativaRepository unidadeFederativaRepository;

    public UnidadeFederativaService(UnidadeFederativaRepository unidadeFederativaRepository) {
        this.unidadeFederativaRepository = unidadeFederativaRepository;
    }

    public Page<UnidadeFederativaDTO> query(Long id, String nome, String sigla, Pageable pageable) {
        Page<UnidadeFederativa> query = unidadeFederativaRepository.findAll(new UnidadeFederativaSpec(id, nome, sigla), pageable);
        return new PageImpl<>(query.stream().map(unidadeFederativa -> UnidadeFederativaDTO.builder()
                .id(unidadeFederativa.getId())
                .nome(unidadeFederativa.getNome())
                .sigla(unidadeFederativa.getSigla())
                .dataHoraAtualizacao(LocalDateTime.ofInstant(unidadeFederativa.getDataHoraAtualizacao().toInstant(), ZoneId.of(AMERICA_SAO_PAULO)))
                .dataHoraCadastro(LocalDateTime.ofInstant(unidadeFederativa.getDataHoraCadastro().toInstant(), ZoneId.of(AMERICA_SAO_PAULO)))
                .build()).collect(Collectors.toList()),pageable, query.getTotalElements());
    }

    public UnidadeFederativaDTO findOne(Long id) {
        UnidadeFederativa unidadeFederativa = unidadeFederativaRepository.findById(id).get();
        return UnidadeFederativaDTO.builder()
                .id(unidadeFederativa.getId())
                .nome(unidadeFederativa.getNome())
                .sigla(unidadeFederativa.getSigla())
                .dataHoraAtualizacao(LocalDateTime.ofInstant(unidadeFederativa.getDataHoraAtualizacao().toInstant(), ZoneId.of(AMERICA_SAO_PAULO)))
                .dataHoraCadastro(LocalDateTime.ofInstant(unidadeFederativa.getDataHoraCadastro().toInstant(), ZoneId.of(AMERICA_SAO_PAULO)))
                .build();
    }

    public UnidadeFederativaDTO save(String nome, String sigla) {
        UnidadeFederativa unidadeFederativa = unidadeFederativaRepository.saveAndFlush(UnidadeFederativa.builder()
                .nome(nome)
                .sigla(sigla)
                .build());
        return UnidadeFederativaDTO.builder()
                .id(unidadeFederativa.getId())
                .sigla(unidadeFederativa.getSigla())
                .nome(unidadeFederativa.getNome())
                .dataHoraAtualizacao(LocalDateTime.ofInstant(unidadeFederativa.getDataHoraAtualizacao().toInstant(), ZoneId.of(AMERICA_SAO_PAULO)))
                .dataHoraCadastro(LocalDateTime.ofInstant(unidadeFederativa.getDataHoraCadastro().toInstant(), ZoneId.of(AMERICA_SAO_PAULO)))
                .build();
    }

    public UnidadeFederativaDTO update(Long id, String nome, String sigla) {
        UnidadeFederativa unidadeFederativa = unidadeFederativaRepository.findById(id).get();
        unidadeFederativa.setNome(nome);
        unidadeFederativa.setSigla(sigla);
        unidadeFederativaRepository.saveAndFlush(unidadeFederativa);
        return UnidadeFederativaDTO.builder()
                .id(unidadeFederativa.getId())
                .sigla(unidadeFederativa.getSigla())
                .nome(unidadeFederativa.getNome())
                .dataHoraAtualizacao(LocalDateTime.ofInstant(unidadeFederativa.getDataHoraAtualizacao().toInstant(), ZoneId.of(AMERICA_SAO_PAULO)))
                .dataHoraCadastro(LocalDateTime.ofInstant(unidadeFederativa.getDataHoraCadastro().toInstant(), ZoneId.of(AMERICA_SAO_PAULO)))
                .build();
    }

    public void delete(Long id) {
        unidadeFederativaRepository.deleteById(id);
    }

}
