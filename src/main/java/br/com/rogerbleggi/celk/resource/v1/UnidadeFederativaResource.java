package br.com.rogerbleggi.celk.resource.v1;

import br.com.rogerbleggi.celk.resource.v1.dto.UnidadeFederativaDTO;
import br.com.rogerbleggi.celk.service.v1.UnidadeFederativaService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

import static java.text.MessageFormat.*;

@RestController
@CrossOrigin
@RequestMapping("api/uf/v1")
public class UnidadeFederativaResource {

    public static final String NAO_EXISTE_UNIDADE_FEDERATIVA_PARA_O_ID_0 = "Não existe unidade federativa para o id {0}";
    public static final String SIGLA_DA_UNIDADE_FEDERATIVA_JA_CADASTRADA_SIGLA_0 = "Sigla da unidade federativa já cadastrada! Sigla: \"{0}\"";
    private final UnidadeFederativaService unidadeFederativaService;

    public UnidadeFederativaResource(UnidadeFederativaService unidadeFederativaService) {
        this.unidadeFederativaService = unidadeFederativaService;
    }

    @GetMapping(value = "/query", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Page<UnidadeFederativaDTO>> query(@RequestParam(value = "id", required = false) Long id,
                                                            @RequestParam(value = "nome", required = false) String nome,
                                                            @RequestParam(value = "sigla", required = false) String sigla,
                                                            Pageable pageable) {
        return ResponseEntity.ok(unidadeFederativaService.query(id, nome, sigla, pageable));
    }

    @PostMapping
    public ResponseEntity<UnidadeFederativaDTO> create(@RequestParam(value = "nome", required = true) String nome,
                                                       @RequestParam(value = "sigla", required = true) String sigla) {
        try {
            return ResponseEntity.ok(unidadeFederativaService.save(nome, sigla));
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, format(SIGLA_DA_UNIDADE_FEDERATIVA_JA_CADASTRADA_SIGLA_0, sigla), e);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UnidadeFederativaDTO> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(unidadeFederativaService.findOne(id));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, format(NAO_EXISTE_UNIDADE_FEDERATIVA_PARA_O_ID_0, id), e);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UnidadeFederativaDTO> update(@PathVariable Long id,
                                                       @RequestParam(value = "nome", required = false) String nome,
                                                       @RequestParam(value = "sigla", required = false) String sigla) {
        try {
            return ResponseEntity.ok(unidadeFederativaService.update(id, nome, sigla));
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, format(SIGLA_DA_UNIDADE_FEDERATIVA_JA_CADASTRADA_SIGLA_0, sigla), e);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, format(NAO_EXISTE_UNIDADE_FEDERATIVA_PARA_O_ID_0, id), e);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UnidadeFederativaDTO> delete(@PathVariable Long id) {
        try {
            unidadeFederativaService.delete(id);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, format(NAO_EXISTE_UNIDADE_FEDERATIVA_PARA_O_ID_0, id), e);
        }
    }

}