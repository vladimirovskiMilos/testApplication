package vladimirovski.softcontestapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComputerDto implements ApplicationDto{

    private Long id;
    @NotEmpty(message = "Name is required")
    @Size(min = 2, message = "Computer name must have min 2 characters." )
    private String name;
    @NotEmpty(message = "Mark is required")
    @Size(min = 2, message = "Computer mark must have min 2 characters." )
    private String mark;
    @Min(value = 2, message = "Ram memory is required")
    private int ramMemory;
    @Min(value = 1, message = "Hard disc must have minimum 1GB")
    private int hardDisc;

    public ComputerDto(String name, String mark, int ramMemory, int hardDisc) {
        this.name = name;
        this.mark = mark;
        this.ramMemory = ramMemory;
        this.hardDisc = hardDisc;
    }

    @Override
    public String toString() {
        return "ComputerDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mark='" + mark + '\'' +
                ", ramMemory=" + ramMemory +
                ", hardDisc=" + hardDisc +
                '}';
    }
}
