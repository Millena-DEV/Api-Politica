package br.com.uol.politica.repository;





import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.uol.politica.modelo.Partido;


public interface PartidosRepository  extends JpaRepository<Partido, Long> {

	

	Page <Partido> findByIdeologia(String ideologia, Pageable paginacao);

	Partido findById( long id);

	

	
	
	

	



	



}
