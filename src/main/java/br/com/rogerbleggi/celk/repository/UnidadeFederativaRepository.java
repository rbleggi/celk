package br.com.rogerbleggi.celk.repository;

import br.com.rogerbleggi.celk.model.UnidadeFederativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeFederativaRepository extends JpaRepository<UnidadeFederativa, Long>, JpaSpecificationExecutor<UnidadeFederativa> {

}
