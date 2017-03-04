package by.grsu.entity;

import javax.persistence.*;

@Entity
@Table(name = "division")
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "shortname", length = 10)
    private String shortName;

    @Column(name = "divheadname")
    private String divheadName;

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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDivheadName() {
        return divheadName;
    }

    public void setDivheadName(String divheadName) {
        this.divheadName = divheadName;
    }
}
