package br.com.uol.politica.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import br.com.uol.politica.modelo.Associado;

public interface AssociadosRepository  extends JpaRepository<Associado, Long> {
	
	Page <Associado> findByCargoPolitico(String cargoPolitico, Pageable paginacao);

	
}
