package vladimirovski.softcontestapplication.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import vladimirovski.softcontestapplication.dto.ComputerDto;
import vladimirovski.softcontestapplication.exception.NonExistingIdException;
import vladimirovski.softcontestapplication.service.ComputerService;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("computers")
@AllArgsConstructor
public class ComputerRestController {

    private final ComputerService computerService;


    @PostMapping()
    public @ResponseBody ResponseEntity<Object> save(@Valid @RequestBody ComputerDto computer){

        try {
            computerService.save(computer);

            return ResponseEntity.status(HttpStatus.OK).body(computer);
        }catch (ValidationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("{id}")
    public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long id) throws NonExistingIdException {
        Optional<ComputerDto> computer = computerService.findById(id);

        return computer.<ResponseEntity<Object>>map(object ->
                    ResponseEntity.status(HttpStatus.OK).body(object)).
                        orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Incorrect ID"));
    }

    @DeleteMapping("{id}")
    public @ResponseBody ResponseEntity<String> delete (@PathVariable Long id) throws NonExistingIdException {
            //Optional<ComputerDto> computerForDelete = computerService.findById(id);
        try {
            computerService.delete(id);

            return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted computer with ID '" + id + "'");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("filter")
    public ResponseEntity<Page<ComputerDto>> findAll(@RequestParam(defaultValue = "0") Integer pageNo,
                                                         @RequestParam(defaultValue = "1") Integer pageSize,
                                                             @RequestParam(defaultValue = "name") String sortBy,
                                                                @RequestParam(defaultValue = "asc") String sortOrder){

        return new ResponseEntity<>
                (computerService.findAll(pageNo, pageSize, sortBy, sortOrder), new HttpHeaders(),
                        HttpStatus.OK);
    }

    @GetMapping
    public List<ComputerDto> findAllComputers(){
        return computerService.findAllComputers();
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidation(MethodArgumentNotValidException ex) {
        return getStringStringMap(ex);
    }
}
