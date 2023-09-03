package com.intuit.editor.services.impl;

import com.intuit.editor.dto.MatcherDto;
import com.intuit.editor.services.IPatternMatcher;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PatternMatcher implements IPatternMatcher {
    public MatcherDto matchContentReference(String text){
        MatcherDto matcherDto = new MatcherDto();
        final Pattern pattern = Pattern.compile("\\{\\{contentReference \\[[^\\]]*\\]\\}\\}", Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(text);
        while(matcher.find()){
            matcherDto.setStartIndex(matcher.start());
            matcherDto.setEndIndex(matcher.end());
            String matchedString = text.substring(matcherDto.getStartIndex(), matcherDto.getEndIndex());
            matcherDto.setMatechedContent(matchedString);
        }
        return matcherDto;
    }
}
