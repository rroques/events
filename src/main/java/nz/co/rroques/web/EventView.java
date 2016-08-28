package nz.co.rroques.web;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class EventView {

    @NotNull
    @Pattern(regexp = "[^\\s]+")
    @JsonProperty
    private String name;

    public EventView() {}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
