
package vladimirovski.softcontestapplication.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import vladimirovski.softcontestapplication.dto.CustomerDto;
import vladimirovski.softcontestapplication.exception.NonExistingIdException;
import vladimirovski.softcontestapplication.service.CustomerService;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("customers")
@AllArgsConstructor
public class CustomerRestController {

    private final CustomerService customerService;



    @PostMapping()
    public @ResponseBody ResponseEntity<Object> save(@Valid @RequestBody CustomerDto customer){

        try {
            customerService.save(customer);

            return ResponseEntity.status(HttpStatus.OK).body(customer);
        }catch (ValidationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("{id}")
    public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long id) throws NonExistingIdException {
        Optional<CustomerDto> customer = customerService.findById(id);

        return customer.<ResponseEntity<Object>>map(object ->
                        ResponseEntity.status(HttpStatus.OK).body(object)).
                orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Incorrect ID"));
    }

    @DeleteMapping("{id}")
    public @ResponseBody ResponseEntity<String> delete (@PathVariable Long id) throws NonExistingIdException {

        try {
            customerService.delete(id);

            return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted customer with ID '" + id + "'");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("filter")
    public ResponseEntity<Page<CustomerDto>> findAll(@RequestParam(defaultValue = "0") Integer pageNo,
                                                     @RequestParam(defaultValue = "1") Integer pageSize,
                                                     @RequestParam(defaultValue = "name") String sortBy,
                                                     @RequestParam(defaultValue = "asc") String sortOrder){

        return new ResponseEntity<>
                (customerService.findAll(pageNo, pageSize, sortBy, sortOrder), new HttpHeaders(),
                        HttpStatus.OK);
    }

    @GetMapping("search")
    public List<CustomerDto> findByFirstOrLastName(@RequestParam String name){
        return customerService.findByFirstNameAndLastName(name);
    }

    @GetMapping
    public List<CustomerDto> findAllComputers(){
        return customerService.findAllCustomers();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidation(MethodArgumentNotValidException ex) {
        return getStringStringMap(ex);
    }

    Map<String, String> getStringStringMap(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
