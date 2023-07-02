package com.astro.ai.astroai.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class AstroAppCustomerPayloadDTO {

    @JsonProperty("customerDetail")
    private AstroAppCustomerDetailDTO customerDetail;

    public AstroAppCustomerPayloadDTO() {
    }

    public AstroAppCustomerDetailDTO getCustomerDetail() {
        return customerDetail;
    }

    public void setCustomerDetail(AstroAppCustomerDetailDTO customerDetail) {
        this.customerDetail = customerDetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AstroAppCustomerPayloadDTO that = (AstroAppCustomerPayloadDTO) o;
        return Objects.equals(customerDetail, that.customerDetail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerDetail);
    }

    @Override
    public String toString() {
        return "AstroAppCustomerPayloadDTO{" +
                "customerDetail=" + customerDetail +
                '}';
    }
}
