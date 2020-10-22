
package com.moringaschool.farmsmart;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TreflePlantSearchResponse {

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("links")
    @Expose
    private Links_ links;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TreflePlantSearchResponse() {
    }

    /**
     * 
     * @param data
     * @param meta
     * @param links
     */
    public TreflePlantSearchResponse(List<Datum> data, Links_ links, Meta meta) {
        super();
        this.data = data;
        this.links = links;
        this.meta = meta;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Links_ getLinks() {
        return links;
    }

    public void setLinks(Links_ links) {
        this.links = links;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

}
