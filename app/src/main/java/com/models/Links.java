
package com.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Links {

    @SerializedName("self")
    @Expose
    private String self;
    @SerializedName("plant")
    @Expose
    private String plant;
    @SerializedName("genus")
    @Expose
    private String genus;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Links() {
    }

    /**
     * 
     * @param genus
     * @param plant
     * @param self
     */
    public Links(String self, String plant, String genus) {
        super();
        this.self = self;
        this.plant = plant;
        this.genus = genus;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

}
