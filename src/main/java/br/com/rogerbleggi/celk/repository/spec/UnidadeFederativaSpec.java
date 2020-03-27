package br.com.rogerbleggi.celk.repository.spec;

import br.com.rogerbleggi.celk.model.UnidadeFederativa;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class UnidadeFederativaSpec implements Specification<UnidadeFederativa> {

    private String nome;
    private String sigla;
    private Long id;

    public UnidadeFederativaSpec(Long id, String nome, String sigla) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
    }

    @Override
    public Predicate toPredicate(Root<UnidadeFederativa> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predcs = new ArrayList<>();
        if (!ObjectUtils.isEmpty(id)) {
            predcs.add(criteriaBuilder.equal(root.get("id"), id));
        }
        if (!ObjectUtils.isEmpty(nome)) {
            predcs.add(criteriaBuilder.equal(root.get("nome"), nome));
        }
        if (!ObjectUtils.isEmpty(sigla)) {
            predcs.add(criteriaBuilder.equal(root.get("sigla"), sigla));
        }
        return criteriaBuilder.and(predcs.toArray(new Predicate[predcs.size()]));
    }
}
