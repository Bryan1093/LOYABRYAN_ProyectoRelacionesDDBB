package ManyToOne.EntityMTO;

import jakarta.persistence.*;

@Entity
public class EmployeeMTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private BossMTO boss;

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

    public BossMTO getBoss() {
        return boss;
    }

    public void setBoss(BossMTO boss) {
        this.boss = boss;
    }
}