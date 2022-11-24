package vladimirovski.softcontestapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vladimirovski.softcontestapplication.entity.ComputerEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto implements ApplicationDto{


    private Long id;

    @NotEmpty(message = "First name is required")
    @Size(min = 3)
    private String firstName;
    @NotEmpty(message = "Last name is required")
    @Size(min = 3)
    private String lastName;
    @NotEmpty(message = "You must pick minimum of 1 favorite mark")
    private List<ComputerEntity> computers;

    public CustomerDto(String firstName, String lastName, List<ComputerEntity> computers) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.computers = computers;
    }
}
