package com.intuit.editor.services;

import com.intuit.editor.dto.MatcherDto;

public interface IPatternMatcher {
    public MatcherDto matchContentReference(String text);
}
