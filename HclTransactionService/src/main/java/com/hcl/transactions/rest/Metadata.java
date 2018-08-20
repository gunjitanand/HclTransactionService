
package com.hcl.transactions.rest;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "public_alias",
    "private_alias",
    "more_info",
    "URL",
    "image_URL",
    "open_corporates_URL",
    "corporate_location",
    "physical_location"
})
public class Metadata {

    @JsonProperty("public_alias")
    private Object publicAlias;
    @JsonProperty("private_alias")
    private Object privateAlias;
    @JsonProperty("more_info")
    private Object moreInfo;
    @JsonProperty("URL")
    private Object uRL;
    @JsonProperty("image_URL")
    private Object imageURL;
    @JsonProperty("open_corporates_URL")
    private Object openCorporatesURL;
    @JsonProperty("corporate_location")
    private Object corporateLocation;
    @JsonProperty("physical_location")
    private Object physicalLocation;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("public_alias")
    public Object getPublicAlias() {
        return publicAlias;
    }

    @JsonProperty("public_alias")
    public void setPublicAlias(Object publicAlias) {
        this.publicAlias = publicAlias;
    }

    @JsonProperty("private_alias")
    public Object getPrivateAlias() {
        return privateAlias;
    }

    @JsonProperty("private_alias")
    public void setPrivateAlias(Object privateAlias) {
        this.privateAlias = privateAlias;
    }

    @JsonProperty("more_info")
    public Object getMoreInfo() {
        return moreInfo;
    }

    @JsonProperty("more_info")
    public void setMoreInfo(Object moreInfo) {
        this.moreInfo = moreInfo;
    }

    @JsonProperty("URL")
    public Object getURL() {
        return uRL;
    }

    @JsonProperty("URL")
    public void setURL(Object uRL) {
        this.uRL = uRL;
    }

    @JsonProperty("image_URL")
    public Object getImageURL() {
        return imageURL;
    }

    @JsonProperty("image_URL")
    public void setImageURL(Object imageURL) {
        this.imageURL = imageURL;
    }

    @JsonProperty("open_corporates_URL")
    public Object getOpenCorporatesURL() {
        return openCorporatesURL;
    }

    @JsonProperty("open_corporates_URL")
    public void setOpenCorporatesURL(Object openCorporatesURL) {
        this.openCorporatesURL = openCorporatesURL;
    }

    @JsonProperty("corporate_location")
    public Object getCorporateLocation() {
        return corporateLocation;
    }

    @JsonProperty("corporate_location")
    public void setCorporateLocation(Object corporateLocation) {
        this.corporateLocation = corporateLocation;
    }

    @JsonProperty("physical_location")
    public Object getPhysicalLocation() {
        return physicalLocation;
    }

    @JsonProperty("physical_location")
    public void setPhysicalLocation(Object physicalLocation) {
        this.physicalLocation = physicalLocation;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
