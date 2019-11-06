package com.petrochina.e7.monitor.pojo;

public class DeviceDetail {
    private Integer detailId;

    private Integer equipId;

    private Integer locationId;

    private Double designTemp;

    private Double rateCpc;

    private Double burnerThetmal;

    private String heatMeType;

    private Double designThremalEfic;

    private Double rateWorkPre;

    private Double maxWorkPre;

    private String useHeatFurnace;

    private String burnerModel;

    private Double ratedEvaporat;

    private String typeHeatFurnace;

    private Double ratedSteamPress;

    private Double rateFlowMedium;

    private Double ratedFuelConsump;

    private Double fuelConsump;

    private String furnaceStructure;

    private String manufacturer;

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public Integer getEquipId() {
        return equipId;
    }

    public void setEquipId(Integer equipId) {
        this.equipId = equipId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Double getDesignTemp() {
        return designTemp;
    }

    public void setDesignTemp(Double designTemp) {
        this.designTemp = designTemp;
    }

    public Double getRateCpc() {
        return rateCpc;
    }

    public void setRateCpc(Double rateCpc) {
        this.rateCpc = rateCpc;
    }

    public Double getBurnerThetmal() {
        return burnerThetmal;
    }

    public void setBurnerThetmal(Double burnerThetmal) {
        this.burnerThetmal = burnerThetmal;
    }

    public String getHeatMeType() {
        return heatMeType;
    }

    public void setHeatMeType(String heatMeType) {
        this.heatMeType = heatMeType == null ? null : heatMeType.trim();
    }

    public Double getDesignThremalEfic() {
        return designThremalEfic;
    }

    public void setDesignThremalEfic(Double designThremalEfic) {
        this.designThremalEfic = designThremalEfic;
    }

    public Double getRateWorkPre() {
        return rateWorkPre;
    }

    public void setRateWorkPre(Double rateWorkPre) {
        this.rateWorkPre = rateWorkPre;
    }

    public Double getMaxWorkPre() {
        return maxWorkPre;
    }

    public void setMaxWorkPre(Double maxWorkPre) {
        this.maxWorkPre = maxWorkPre;
    }

    public String getUseHeatFurnace() {
        return useHeatFurnace;
    }

    public void setUseHeatFurnace(String useHeatFurnace) {
        this.useHeatFurnace = useHeatFurnace == null ? null : useHeatFurnace.trim();
    }

    public String getBurnerModel() {
        return burnerModel;
    }

    public void setBurnerModel(String burnerModel) {
        this.burnerModel = burnerModel == null ? null : burnerModel.trim();
    }

    public Double getRatedEvaporat() {
        return ratedEvaporat;
    }

    public void setRatedEvaporat(Double ratedEvaporat) {
        this.ratedEvaporat = ratedEvaporat;
    }

    public String getTypeHeatFurnace() {
        return typeHeatFurnace;
    }

    public void setTypeHeatFurnace(String typeHeatFurnace) {
        this.typeHeatFurnace = typeHeatFurnace == null ? null : typeHeatFurnace.trim();
    }

    public Double getRatedSteamPress() {
        return ratedSteamPress;
    }

    public void setRatedSteamPress(Double ratedSteamPress) {
        this.ratedSteamPress = ratedSteamPress;
    }

    public Double getRateFlowMedium() {
        return rateFlowMedium;
    }

    public void setRateFlowMedium(Double rateFlowMedium) {
        this.rateFlowMedium = rateFlowMedium;
    }

    public Double getRatedFuelConsump() {
        return ratedFuelConsump;
    }

    public void setRatedFuelConsump(Double ratedFuelConsump) {
        this.ratedFuelConsump = ratedFuelConsump;
    }

    public Double getFuelConsump() {
        return fuelConsump;
    }

    public void setFuelConsump(Double fuelConsump) {
        this.fuelConsump = fuelConsump;
    }

    public String getFurnaceStructure() {
        return furnaceStructure;
    }

    public void setFurnaceStructure(String furnaceStructure) {
        this.furnaceStructure = furnaceStructure == null ? null : furnaceStructure.trim();
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer == null ? null : manufacturer.trim();
    }
}