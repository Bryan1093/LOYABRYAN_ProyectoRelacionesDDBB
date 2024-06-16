package ManyToOne.EntityMTO;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class BossMTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "boss", fetch = FetchType.EAGER)
    private List<EmployeeMTO> employees;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EmployeeMTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeMTO> employees) {
        this.employees = employees;
    }
}

