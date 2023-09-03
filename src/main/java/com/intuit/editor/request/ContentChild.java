package com.intuit.editor.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContentChild {
    private String text;
    private Boolean bold;
    private Boolean italic;
    private Boolean underline;
    private List<ContentChild> children;
}
