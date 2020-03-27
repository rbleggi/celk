package br.com.rogerbleggi.celk.resource.v1;

import br.com.rogerbleggi.celk.model.UnidadeFederativa;
import br.com.rogerbleggi.celk.repository.UnidadeFederativaRepository;
import br.com.rogerbleggi.celk.resource.v1.dto.UnidadeFederativaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.DateUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UnidadeFederativaResourceTest {

    private static final String API_UF_V1 = "/api/uf/v1";
    public static final String AMERICA_SAO_PAULO = "America/Sao_Paulo";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UnidadeFederativaRepository unidadeFederativaRepository;

    @Before
    public void before() {
        unidadeFederativaRepository.deleteAll();
    }

    @Test
    public void deveCadastrarNovoUF() throws Exception {
        MvcResult uf = mockMvc.perform(post(API_UF_V1).param("nome", "teste").param("sigla", "TT"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        UnidadeFederativaDTO unidadeFederativaDTO = new ObjectMapper().readValue(uf.getResponse().getContentAsString(), UnidadeFederativaDTO.class);
        List<UnidadeFederativa> ufs = unidadeFederativaRepository.findAll();

        assertEquals(1, ufs.size());
        assertEquals(ufs.get(0).getId(), unidadeFederativaDTO.getId());
        assertEquals("teste", unidadeFederativaDTO.getNome());
        assertEquals("TT", unidadeFederativaDTO.getSigla());
    }

    @Test
    public void deveAtualizarUF() throws Exception {
        UnidadeFederativa unidadeFederativa = unidadeFederativaRepository.saveAndFlush(UnidadeFederativa.builder()
                .nome("teste")
                .sigla("TT")
                .build());

        MvcResult uf = mockMvc.perform(put(API_UF_V1.concat("/").concat(unidadeFederativa.getId().toString()))
                .param("nome", "teste2").param("sigla", "TT2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        UnidadeFederativaDTO unidadeFederativaDTO = new ObjectMapper().readValue(uf.getResponse().getContentAsString(), UnidadeFederativaDTO.class);
        List<UnidadeFederativa> ufs = unidadeFederativaRepository.findAll();

        assertEquals(1, ufs.size());
        assertEquals(ufs.get(0).getId(), unidadeFederativaDTO.getId());
        assertEquals("teste2", unidadeFederativaDTO.getNome());
        assertEquals("TT2", unidadeFederativaDTO.getSigla());
    }

    @Test
    public void deveRemoverUF() throws Exception {
        UnidadeFederativa unidadeFederativa = unidadeFederativaRepository.saveAndFlush(UnidadeFederativa.builder()
                .nome("teste")
                .sigla("TT")
                .build());

        mockMvc.perform(delete(API_UF_V1.concat("/").concat(unidadeFederativa.getId().toString())))
                .andDo(print())
                .andExpect(status().isOk());

        List<UnidadeFederativa> ufs = unidadeFederativaRepository.findAll();

        assertEquals(0, ufs.size());
    }

    @Test
    public void deveBuscarUFPorId() throws Exception {
        UnidadeFederativa unidadeFederativa = unidadeFederativaRepository.saveAndFlush(UnidadeFederativa.builder()
                .nome("teste")
                .sigla("TT")
                .build());

        mockMvc.perform(get(API_UF_V1.concat("/").concat(unidadeFederativa.getId().toString())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(UnidadeFederativaDTO.builder()
                        .id(unidadeFederativa.getId())
                        .nome("teste")
                        .sigla("TT")
                        .dataHoraCadastro(LocalDateTime.ofInstant(unidadeFederativa.getDataHoraCadastro().toInstant(), ZoneId.of(AMERICA_SAO_PAULO)))
                        .dataHoraAtualizacao(LocalDateTime.ofInstant(unidadeFederativa.getDataHoraAtualizacao().toInstant(), ZoneId.of(AMERICA_SAO_PAULO)))
                        .build())));
    }

    @Test
    public void deveBuscarUFPorNome() throws Exception {
        UnidadeFederativa unidadeFederativa = unidadeFederativaRepository.saveAndFlush(UnidadeFederativa.builder()
                .nome("teste")
                .sigla("TT")
                .build());

        mockMvc.perform(get(API_UF_V1.concat("/query")).param("nome", "teste"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"content\":[{\"id\":"
                        .concat(unidadeFederativa.getId().toString())
                        .concat(",\"nome\":\"teste\",\"sigla\":\"TT\",\"dataHoraCadastro\":\"")
                        .concat(DateUtil.formatAsDatetimeWithMs(unidadeFederativa.getDataHoraCadastro()))
                        .concat("\",\"dataHoraAtualizacao\":\"")
                        .concat(DateUtil.formatAsDatetimeWithMs(unidadeFederativa.getDataHoraAtualizacao())).concat("\"}]}"), false));
    }

    @Test
    public void deveBuscarUFPorSigla() throws Exception {
        UnidadeFederativa unidadeFederativa = unidadeFederativaRepository.saveAndFlush(UnidadeFederativa.builder()
                .nome("teste")
                .sigla("TT")
                .build());

        mockMvc.perform(get(API_UF_V1.concat("/query")).param("sigla", "TT"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"content\":[{\"id\":"
                        .concat(unidadeFederativa.getId().toString())
                        .concat(",\"nome\":\"teste\",\"sigla\":\"TT\",\"dataHoraCadastro\":\"")
                        .concat(DateUtil.formatAsDatetimeWithMs(unidadeFederativa.getDataHoraCadastro()))
                        .concat("\",\"dataHoraAtualizacao\":\"")
                        .concat(DateUtil.formatAsDatetimeWithMs(unidadeFederativa.getDataHoraAtualizacao())).concat("\"}]}"), false));
    }

    @Test
    public void deveLancarExceptionAoCadastrarUFComMesmaSigla() throws Exception {
        unidadeFederativaRepository.saveAndFlush(UnidadeFederativa.builder()
                .nome("teste")
                .sigla("TT")
                .build());

        mockMvc.perform(post(API_UF_V1).param("nome", "teste").param("sigla", "TT"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deveLancarExceptionAoAtualizarUFComMesmaSigla() throws Exception {
        UnidadeFederativa unidadeFederativa = unidadeFederativaRepository.saveAndFlush(UnidadeFederativa.builder()
                .nome("teste")
                .sigla("TT")
                .build());
        unidadeFederativaRepository.saveAndFlush(UnidadeFederativa.builder()
                .nome("teste")
                .sigla("TS")
                .build());

        mockMvc.perform(put(API_UF_V1.concat("/").concat(unidadeFederativa.getId().toString()))
                .param("nome", "teste2").param("sigla", "TS"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}