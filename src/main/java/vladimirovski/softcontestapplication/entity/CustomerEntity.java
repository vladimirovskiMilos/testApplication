

package vladimirovski.softcontestapplication.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity implements ApplicationEntity{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "customers_like",
            joinColumns = @JoinColumn(name = "customer_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "computer_id",
                    referencedColumnName = "id"))
    private List<ComputerEntity> computers;

    public CustomerEntity(String firstName, String lastName, List<ComputerEntity> computers) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.computers = computers;
    }
}
