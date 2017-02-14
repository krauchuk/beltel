package by.grsu.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reportingnotice")
public class ReportingNotice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "datedone")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDone;

    @Column(name = "dateset")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSet;

    @Column(name = "ipaddr", length = 16)
    private String ipAddr;

    @Column(name = "status")
    private boolean status;

    @Column(name = "userpcname", length = 100)
    private String userPcName;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "employers_id", nullable = false)
    private Employer employers_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateDone() {
        return dateDone;
    }

    public void setDateDone(Date dateDone) {
        this.dateDone = dateDone;
    }

    public Date getDateSet() {
        return dateSet;
    }

    public void setDateSet(Date dateSet) {
        this.dateSet = dateSet;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getUserPcName() {
        return userPcName;
    }

    public void setUserPcName(String userPcName) {
        this.userPcName = userPcName;
    }

    public Employer getEmployers_id() {
        return employers_id;
    }

    public void setEmployers_id(Employer employers_id) {
        this.employers_id = employers_id;
    }
}
