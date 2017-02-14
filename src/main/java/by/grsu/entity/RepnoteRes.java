package by.grsu.entity;

import javax.persistence.*;

@Entity
@Table(name = "repnote_res")
public class RepnoteRes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "reportingnotice", nullable = false)
    private ReportingNotice reportingNotice_id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "subresource", nullable = false)
    private SubResource subResource_id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "regimeaccess", nullable = false)
    private RegimeAccess regimeAccess_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ReportingNotice getReportingNotice_id() {
        return reportingNotice_id;
    }

    public void setReportingNotice_id(ReportingNotice reportingNotice_id) {
        this.reportingNotice_id = reportingNotice_id;
    }

    public SubResource getSubResource_id() {
        return subResource_id;
    }

    public void setSubResource_id(SubResource subResource_id) {
        this.subResource_id = subResource_id;
    }

    public RegimeAccess getRegimeAccess_id() {
        return regimeAccess_id;
    }

    public void setRegimeAccess_id(RegimeAccess regimeAccess_id) {
        this.regimeAccess_id = regimeAccess_id;
    }
}
