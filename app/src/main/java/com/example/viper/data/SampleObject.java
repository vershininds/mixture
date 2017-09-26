package com.example.viper.data;

import org.parceler.Parcel;

@Parcel(Parcel.Serialization.BEAN)
public class SampleObject {
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
