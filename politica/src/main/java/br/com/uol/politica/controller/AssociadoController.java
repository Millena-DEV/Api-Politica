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
import br.com.uol.politica.controller.form.AssociadosAtualizacaoForm;
import br.com.uol.politica.modelo.Associado;
import br.com.uol.politica.modelo.Partido;
import br.com.uol.politica.repository.AssociadosRepository;
import br.com.uol.politica.repository.PartidosRepository;
import io.swagger.annotations.ApiOperation;

public class AssociadoController {

	@RestController
	@RequestMapping("/associados")
	public class PartidoController {

		@Autowired
		private AssociadosRepository ar;
		@Autowired
		private PartidosRepository pr;

		@ApiOperation(value = "Cadastrar dados associados ")
		@PostMapping
		public ResponseEntity<AssociadoDTO> associados(@RequestBody @Valid Associado associado,
				UriComponentsBuilder uriBuilder) {
			ar.save(associado);
			URI uri = uriBuilder.path("/partidos/{id}").buildAndExpand(associado.getId()).toUri();
			return ResponseEntity.created(uri).body(new AssociadoDTO(associado));

		}

		@ApiOperation(value = "Cadastrar associados ao partido")

		@PostMapping ("/partidos")
		public  ResponseEntity<AssociadoDTO> associados(@RequestBody @Valid  Associado associado,Partido partido,
				UriComponentsBuilder uriBuilder) {
			partido.getId();
			associado.setPartido(partido);
			ar.save(associado);
			URI uri = uriBuilder.path("/partidos/{id}/{id}").buildAndExpand(associado.getId()).toUri();
			return ResponseEntity.created(uri).body(new AssociadoDTO(associado));

		}

		@ApiOperation(value = "buscar associados por cargo politico ")
		@GetMapping("/{cargoPolitico}")
		@CacheEvict(value = "filtroCargoPolitico", allEntries = true)
		public Page<AssociadoDTO> associados(@RequestParam(required = false) String cargoPolitico,
				@PageableDefault(sort = "ideologia", direction = Direction.DESC, page = 0, size = 05) Pageable paginacao) {

			if (cargoPolitico == null) {
				Page<Associado> associado = ar.findAll(paginacao);
				return AssociadoDTO.converter(associado);
			} else {
				Page<Associado> associado = ar.findByCargoPolitico(cargoPolitico, paginacao);
				return AssociadoDTO.converter(associado);
			}
		}

		@ApiOperation(value = "buscar associados por id")
		@GetMapping("/{id}")
		public ResponseEntity<AssociadoDTO> associados(@PathVariable Long id) {
			Optional<Associado> partido = ar.findById(id);
			if (partido.isPresent()) {
				return ResponseEntity.ok(new AssociadoDTO(partido.get()));
			}
			return ResponseEntity.notFound().build();

		}

		@ApiOperation(value = "Atualizar associados por id ")
		@PutMapping("/{id}")
		@Transactional
		public ResponseEntity<AssociadoDTO> associados(@PathVariable Long id,
				@RequestBody @Valid AssociadosAtualizacaoForm form) {
			Optional<Associado> optional = ar.findById(id);
			if (optional.isPresent()) {
				Associado associado = form.Atualizar(id, ar);
				return ResponseEntity.ok(new AssociadoDTO(associado));
			}

			return ResponseEntity.notFound().build();
		}

		@ApiOperation(value = "deletar associados por id ")
		@DeleteMapping("/{id}")
		public ResponseEntity<?> associado(@PathVariable Long id) {
			Optional<Associado> optional = ar.findById(id);
			if (optional.isPresent()) {
				ar.deleteById(id);
				return ResponseEntity.ok().build();
			}

			return ResponseEntity.notFound().build();
		}

		@ApiOperation(value = "deletar associado do partido ")

		@DeleteMapping("associados/{id}/partido{id}")
		public ResponseEntity<?> remove(@PathVariable Long id) {
			Optional<Associado> optional = ar.findById(id);
			Optional<Partido> optionals = pr.findById(id);
			if (optional.isPresent() == optionals.isPresent()) {
				ar.deleteById(id);
				return ResponseEntity.ok().build();
			}

			return ResponseEntity.notFound().build();
		}

	}

}
