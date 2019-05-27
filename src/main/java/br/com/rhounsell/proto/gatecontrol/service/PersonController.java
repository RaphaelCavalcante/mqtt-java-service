package br.com.rhounsell.proto.gatecontrol.service;

import java.util.Collection;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(tags = "person", value = "person")
@RequestMapping("/ws/person")
@RestController
public class PersonController {
	@Autowired
	private PersonService service;
	@PostMapping
	public ResponseEntity<Person> createPerson(@RequestBody Person p) {
		return ResponseEntity.ok(this.service.create(p));
	}
	@GetMapping("/{id}")
	public ResponseEntity<Person> createPerson(@PathParam(value="id") Integer id){
		return ResponseEntity.ok(this.service.getPerson(id));
	}
	@PutMapping
	public ResponseEntity<Person> updatePerson(@RequestBody Person p){
		return ResponseEntity.ok(this.service.update(p));
	}
	@GetMapping
	public ResponseEntity<Collection<Person>> listPerson(){
		return ResponseEntity.ok(this.service.list());
	}
	@DeleteMapping("/{id}")
	public void deletePerson(@PathParam(value = "id") Integer id) {
		this.service.delete(id);
	}
}
