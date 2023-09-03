package com.intuit.editor.dto;

import lombok.Data;

@Data
public class MatcherDto {
    private Boolean isMatched;
    private Integer startIndex;
    private Integer endIndex;
    private String contentId;
    private String matechedContent;
}
