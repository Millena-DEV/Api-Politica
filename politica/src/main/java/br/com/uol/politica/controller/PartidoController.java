package br.com.uol.politica.controller;

import java.net.URI;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.uol.politica.controller.dto.AssociadoDTO;
import br.com.uol.politica.controller.dto.PartidoDTO;
import br.com.uol.politica.controller.form.PartidosAtualizacaoForm;
import br.com.uol.politica.modelo.Associado;
import br.com.uol.politica.modelo.Partido;
import br.com.uol.politica.repository.AssociadosRepository;
import br.com.uol.politica.repository.PartidosRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "apiRest")
@RestController
@RequestMapping("/partidos")
public class PartidoController {

	@Autowired
	private PartidosRepository pr;

	@Autowired
	private AssociadosRepository ar;

	@ApiOperation(value = "Cadastrar partidos ")
	@PostMapping
	public ResponseEntity<PartidoDTO> partidos(@RequestBody @Valid Partido partido, UriComponentsBuilder uriBuilder) {
		pr.save(partido);
		URI uri = uriBuilder.path("/partidos/{id}").buildAndExpand(partido.getId()).toUri();
		return ResponseEntity.created(uri).body(new PartidoDTO(partido));

	}

	@ApiOperation(value = "filtrar partido por ideologia ")

	@GetMapping("/{ideologia}")
	@CacheEvict(value = "filtroIdeologia",allEntries = true)
	public Page<PartidoDTO> partidos(@RequestParam(required = false) String ideologia,
			@PageableDefault(sort = "ideologia", direction = Direction.DESC, page = 0, size = 05) Pageable paginacao) {

		if (ideologia == null) {
			Page<Partido> partido = pr.findAll(paginacao);
			return PartidoDTO.converter(partido);
		} else {
			Page<Partido> partido = pr.findByIdeologia(ideologia, paginacao);
			return PartidoDTO.converter(partido);
		}
	}

	@ApiOperation(value = "filtrar partido por id")

	@GetMapping("/{id}")
	public ResponseEntity<PartidoDTO> partidos(@PathVariable Long id) {
		Optional<Partido> partido = pr.findById(id);
		if (partido.isPresent()) {
			return ResponseEntity.ok(new PartidoDTO(partido.get()));
		}
		return ResponseEntity.notFound().build();

	}

	@ApiOperation(value = "filtrar partido por associados ")
	@GetMapping("/{id}/associados")
	public ResponseEntity<PartidoDTO> prcr(@PathVariable Long id,Associado associados,AssociadoDTO ass) {
		Optional<Partido> partido = pr.findById(id);
		Optional <Associado> associado = ar.findById(id);
		if (partido.isPresent() & associado.isPresent()) {
			return ResponseEntity.ok(new PartidoDTO(partido.get()));
		
				
		}
		return ResponseEntity.notFound().build();

	}

	@ApiOperation(value = "atualizar partido por id ")
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PartidoDTO> partidos(@PathVariable Long id, @RequestBody @Valid PartidosAtualizacaoForm form) {
		Optional<Partido> optional = pr.findById(id);
		if (optional.isPresent()) {
			Partido partido = form.atualizar(id, pr);
			return ResponseEntity.ok(new PartidoDTO(partido));
		}

		return ResponseEntity.notFound().build();
	}
	

	@ApiOperation(value = "Deletar partido por id ")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> partido(@PathVariable Long id) {
		Optional<Partido> optional = pr.findById(id);
		if (optional.isPresent()) {
			pr.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

}
