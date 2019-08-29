package tws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tws.entity.Employee;
import tws.repository.EmployeeMapper;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeMapper employeeMapper;

	@GetMapping("")
	public ResponseEntity<List<Employee>> getAll() {
		return ResponseEntity.ok(employeeMapper.selectAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getById(@PathVariable String id) {
		Employee employee = employeeMapper.selectOne(id);
		return ResponseEntity.ok(employee);
	}

	@PostMapping("")
	public ResponseEntity<List<Employee>> insert(@RequestBody Employee employee) {
		String id = UUID.randomUUID().toString();
		employee.setId(id);
		employeeMapper.insert(employee);
		return ResponseEntity.created(URI.create(id)).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Employee> getById(@PathVariable String id, @RequestBody Employee employee) {
		employeeMapper.updateOne(id, employee);
		return ResponseEntity.ok(employee);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Employee> deleteById(@PathVariable String id) {
		employeeMapper.deleteOne(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
