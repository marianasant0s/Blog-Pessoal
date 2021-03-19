package org.generation.BlogPessoal.Controller;

import java.util.List;

import org.generation.BlogPessoal.Repository.PostagemRepository;
import org.generation.BlogPessoal.model.Postagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/Postagens")
@CrossOrigin ("*")
public class PostagemController {
	
	@Autowired
	private PostagemRepository Repository;
	
	@GetMapping
	public ResponseEntity<List<Postagem>>GetAll(){
		return ResponseEntity.ok(Repository.findAll());
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<Postagem> GetById (@PathVariable long id)
	{
		return Repository.findById(id)
				.map(resp->ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping ("/Titulo/{Titulo}")
		public ResponseEntity<List<Postagem>> GetByTitulo (@PathVariable String Titulo)
		{
			return ResponseEntity.ok(Repository.findALLByTituloContainingIgnoreCase(Titulo)); 
		}
	
	@PostMapping
	public ResponseEntity <Postagem> post (@RequestBody Postagem postagem) {
		return ResponseEntity.status (HttpStatus.CREATED).body(Repository.save(postagem));
	}
	
	@PutMapping
	public ResponseEntity <Postagem> put (@RequestBody Postagem postagem) {
		return ResponseEntity.status (HttpStatus.OK).body(Repository.save(postagem));
	}
	
	@DeleteMapping ("/{id}")
	public void delete(@PathVariable long id) {
		Repository.deleteById(id);
	}
	
}

