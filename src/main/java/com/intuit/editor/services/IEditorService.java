package com.intuit.editor.services;

import com.intuit.editor.entity.ContentEntity;
import com.intuit.editor.request.CreateContentModel;
import com.intuit.editor.request.EditorContent;

import java.util.List;

public interface IEditorService {
    public EditorContent getContent(String id);
    public ContentEntity updateContent(EditorContent editorContent);
    public EditorContent createContent(CreateContentModel editorContent);
    public EditorContent getResolvedContent(EditorContent id);
    public List<EditorContent> getContentEntries();
}
