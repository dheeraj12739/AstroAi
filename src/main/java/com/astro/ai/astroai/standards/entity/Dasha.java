package com.astro.ai.astroai.standards.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Dasha {

    @Id
    @GeneratedValue
    private Long dashaId;

    @Column
    private String dashaStartDate;

    @Column
    private String dashEndDate;

    @Column
    private String antarDasha;


    @OneToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    public Dasha() {
    }

    public Long getDashaId() {
        return dashaId;
    }

    public void setDashaId(Long dashaId) {
        this.dashaId = dashaId;
    }

    public String getDashaStartDate() {
        return dashaStartDate;
    }

    public void setDashaStartDate(String dashaStartDate) {
        this.dashaStartDate = dashaStartDate;
    }

    public String getDashEndDate() {
        return dashEndDate;
    }

    public void setDashEndDate(String dashEndDate) {
        this.dashEndDate = dashEndDate;
    }

    public String getAntarDasha() {
        return antarDasha;
    }

    public void setAntarDasha(String antarDasha) {
        this.antarDasha = antarDasha;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Dasha{" +
                "dashaId=" + dashaId +
                ", dashaStartDate=" + dashaStartDate +
                ", dashEndDate=" + dashEndDate +
                ", antarDasha='" + antarDasha + '\'' +
                ", customer=" + customer +
                '}';
    }
}
