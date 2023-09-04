package com.intuit.editor.services.impl;

import com.intuit.editor.exception.ContentErrors;
import com.intuit.editor.exception.ContentExceptions;
import com.intuit.editor.dto.MatcherDto;
import com.intuit.editor.entity.ContentEntity;
import com.intuit.editor.repository.ContentRespository;
import com.intuit.editor.request.ContentChild;
import com.intuit.editor.request.CreateContentModel;
import com.intuit.editor.request.EditorContent;
import com.intuit.editor.services.IEditorService;
import com.intuit.editor.services.IPatternMatcher;
import com.intuit.editor.services.IUniqueValueGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EditorService implements IEditorService {
    @Autowired
    private IUniqueValueGeneratorService _uniqueValueGeneratorService;
    @Autowired
    private ContentRespository _contentRepository;
    @Autowired
    private IPatternMatcher _patternMatcher;
    public EditorContent getContent(String id){
        EditorContent editorContent = null;
        try {
            ContentEntity contentEntity = _contentRepository.findById(id).orElseThrow(() ->
                    new ContentExceptions(ContentErrors.NOT_FOUND));
            editorContent = copyContentEntityProperties(contentEntity);
        }
        catch (Exception ex){
            throw new ContentExceptions(ContentErrors.SOMETHING_WENT_WRONG);
        }
        return editorContent;
    }

    public ContentEntity updateContent(EditorContent editorContent){
        ContentEntity savedContentEntity = _contentRepository.findById(editorContent.getContentId()).orElseThrow(() ->
                new ContentExceptions(ContentErrors.NOT_FOUND));
        savedContentEntity.setDTitle(editorContent.getTitle());
        savedContentEntity.setUpdatedAt(System.currentTimeMillis());
        savedContentEntity.setDChildren(editorContent.getChildren());
        ContentEntity savedEntity = _contentRepository.save(savedContentEntity);
        if(savedEntity == null){
            throw new ContentExceptions(ContentErrors.CONTENT_UPDATION_FAILED);
        }
        return savedEntity;
    }

    public EditorContent createContent(CreateContentModel contentModel){
        EditorContent editorContent =  null;
        try {
            String uniqueId = _uniqueValueGeneratorService.generateUniqueValue();
            ContentEntity contentEntity = new ContentEntity();
            contentEntity.setId(uniqueId);
            contentEntity.setCreatedAt(System.currentTimeMillis());
            contentEntity.setDTitle(contentModel.getTitle());
            _contentRepository.save(contentEntity);
            editorContent = new EditorContent();
            editorContent.setContentId(uniqueId);
            editorContent.setTitle(contentModel.getTitle());
        }
        catch (Exception ex){
            log.error("[createContent] exception occurred for title: "+ contentModel.getTitle(), ex);
            throw ex;
        }
        return editorContent;
    }

    public EditorContent getResolvedContent(EditorContent editorContent){
        updateContent(editorContent);
        List<ContentEntity> idsAndTitles = _contentRepository.findAllTitlesAndIds();
        resolveContent(editorContent.getChildren(), idsAndTitles);
        return editorContent;
    }

    public void resolveContent(List<ContentChild> currContentChilds,List<ContentEntity> allContentEntries){
        for(ContentChild contentChild: currContentChilds){
            if(contentChild.getChildren() != null && contentChild.getChildren().size() > 0){
                resolveContent(contentChild.getChildren(), allContentEntries);
                continue;
            }
            MatcherDto matcherDto = _patternMatcher.matchContentReference(contentChild.getText());
            String matchedText = matcherDto.getMatechedContent();
            if(matchedText != null) {
                String contentId = matchedText.substring(matchedText.indexOf('[') + 1, matchedText.indexOf(']'));
                Optional<ContentEntity> foundItem = allContentEntries.stream()
                        .filter(item -> item.getId().equals(contentId))
                        .findFirst();
                if (foundItem.isPresent()) {
                    contentChild.setText(contentChild.getText().replace(matcherDto.getMatechedContent(), foundItem.get().getDTitle()));
                }
            }
        }
    }

    @Override
    public List<EditorContent> getContentEntries() {
        List<ContentEntity> idsAndTitles = _contentRepository.findAllTitlesAndIds();
        List<EditorContent> editorContents = new ArrayList<>();
        for(ContentEntity contentEntity: idsAndTitles){
            EditorContent editorContent = new EditorContent();
            editorContent.setTitle(contentEntity.getDTitle());
            editorContent.setContentId(contentEntity.getId());
            editorContents.add(editorContent);
        }
        return editorContents;
    }

    public EditorContent copyContentEntityProperties(ContentEntity contentEntity){
        EditorContent editorContent = new EditorContent();
        editorContent.setContentId(contentEntity.getId());
        editorContent.setChildren(contentEntity.getDChildren());
        editorContent.setTitle(contentEntity.getDTitle());
        return editorContent;
    }

    public ContentEntity copyEditorContentProperties(EditorContent editorContent){
        ContentEntity contentEntity = new ContentEntity();
        contentEntity.setId(editorContent.getContentId());
        contentEntity.setDChildren(editorContent.getChildren());
        contentEntity.setDTitle(editorContent.getTitle());
        return contentEntity;
    }
}
