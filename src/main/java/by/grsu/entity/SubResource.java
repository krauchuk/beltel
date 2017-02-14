package by.grsu.entity;

import javax.persistence.*;

@Entity
@Table(name = "subresource")
public class SubResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "resource_id")
    private Resource resource_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Resource getResource_id() {
        return resource_id;
    }

    public void setResource_id(Resource resource_id) {
        this.resource_id = resource_id;
    }
}
