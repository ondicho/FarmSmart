
package com.moringaschool.farmsmart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links_ {

    @SerializedName("self")
    @Expose
    private String self;
    @SerializedName("first")
    @Expose
    private String first;
    @SerializedName("next")
    @Expose
    private String next;
    @SerializedName("last")
    @Expose
    private String last;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Links_() {
    }

    /**
     * 
     * @param next
     * @param last
     * @param self
     * @param first
     */
    public Links_(String self, String first, String next, String last) {
        super();
        this.self = self;
        this.first = first;
        this.next = next;
        this.last = last;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

}
