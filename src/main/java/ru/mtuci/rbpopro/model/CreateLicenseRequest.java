package ru.mtuci.rbpopro.model;

public class CreateLicenseRequest {
    private Long productId;
    private Long ownerId;
    private Long licenceTypeId;
    private Integer deviceCount;

    public Long getProductId() {
        return productId;
    }


    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getLicenceTypeId() {
        return licenceTypeId; // Adjusted to match the field name
    }

    public void setLicenceTypeId(Long licenceTypeId) {
        this.licenceTypeId = licenceTypeId; // Adjusted to match the field name
    }

    public Integer getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(Integer deviceCount) {
        this.deviceCount = deviceCount;
    }
}
