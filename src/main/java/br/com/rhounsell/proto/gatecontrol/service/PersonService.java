package br.com.rhounsell.proto.gatecontrol.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
	private PersonRepository repo;
	@Autowired
	public PersonService(PersonRepository pRepo) {
		this.repo = pRepo;
	}
	
	public Person create(Person p) {
		return this.repo.save(p);
	}
	public Person getPerson(Integer id) {
		return this.repo.getOne(id);
	}
	public Person update(Person p ) {
		Person ap=this.getPerson(p.getId());
		if(ap != null) {
			ap.setAge(p.getAge());
			ap.setId(p.getId());
			ap.setName(p.getName());
			ap.setPhoneNum(p.getPhoneNum());
			ap = this.repo.save(ap);
		}
		return ap;
	}
	public void delete(Integer p) {
		this.repo.delete(this.getPerson(p));
	}
	public Collection<Person> list(){
		return this.repo.findAll();
	}
}
