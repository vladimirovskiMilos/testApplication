package vladimirovski.softcontestapplication.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "computer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComputerEntity implements ApplicationEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String mark;
    private int ramMemory;
    private int hardDisc;

    public ComputerEntity(String name, String mark, int ramMemory, int hardDisc) {
        this.name = name;
        this.mark = mark;
        this.ramMemory = ramMemory;
        this.hardDisc = hardDisc;
    }

    @Override
    public String toString() {
        return "ComputerEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mark='" + mark + '\'' +
                ", ramMemory=" + ramMemory +
                ", hardDisc=" + hardDisc +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComputerEntity that = (ComputerEntity) o;
        return ramMemory == that.ramMemory && hardDisc == that.hardDisc && id.equals(that.id) && name.equals(that.name) && Objects.equals(mark, that.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, mark, ramMemory, hardDisc);
    }
}
