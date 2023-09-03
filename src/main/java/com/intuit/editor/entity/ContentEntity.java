package com.intuit.editor.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.intuit.editor.request.ContentChild;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "editor_content")
public class ContentEntity {
    @Id
    private String id;
    private String dTitle;
    private List<ContentChild> dChildren;
}
