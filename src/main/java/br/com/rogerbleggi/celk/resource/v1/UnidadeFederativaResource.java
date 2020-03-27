package br.com.rogerbleggi.celk.resource.v1;

import br.com.rogerbleggi.celk.resource.v1.dto.UnidadeFederativaDTO;
import br.com.rogerbleggi.celk.service.v1.UnidadeFederativaService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@CrossOrigin
@RequestMapping("api/uf/v1")
public class UnidadeFederativaResource {

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
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UnidadeFederativaDTO> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(unidadeFederativaService.findOne(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UnidadeFederativaDTO> update(@PathVariable Long id,
                                                       @RequestParam(value = "nome", required = false) String nome,
                                                       @RequestParam(value = "sigla", required = false) String sigla) {
        try {
            return ResponseEntity.ok(unidadeFederativaService.update(id, nome, sigla));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UnidadeFederativaDTO> delete(@PathVariable Long id) {
        try {
            unidadeFederativaService.delete(id);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

}