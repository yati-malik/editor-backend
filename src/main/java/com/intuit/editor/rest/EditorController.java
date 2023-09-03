package com.intuit.editor.rest;

import com.intuit.editor.dto.Response;
import com.intuit.editor.request.CreateContentModel;
import com.intuit.editor.request.EditorContent;
import com.intuit.editor.services.IEditorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class EditorController {

    @Autowired
    private IEditorService editorService;

    @GetMapping("/getContent")
    public ResponseEntity<Response<EditorContent>> getContent(@RequestParam String id){
        log.info("[getContent] request received for id: "+ id);
        return new ResponseEntity<>(Response.successResponse(this.editorService.getContent(id)), HttpStatus.OK);
    }

    @PostMapping("/createContent")
    public ResponseEntity<Response<Object>> createContent(@RequestBody CreateContentModel editorContent){
        log.info("[createContent] request received for title: "+ editorContent.getTitle());
        return new ResponseEntity<>(Response.successResponse(this.editorService.createContent(editorContent)), HttpStatus.OK);
    }

    @PostMapping("/updateContent")
    public ResponseEntity<Response<Object>> updateContent(@RequestBody EditorContent editorContent){
        log.info("[updateContent] request received for id: "+ editorContent.getContentId());
        this.editorService.updateContent(editorContent);
        return new ResponseEntity<>(Response.successResponse(), HttpStatus.OK);
    }

    @PostMapping("/getResolvedContent")
    public ResponseEntity<Response<EditorContent>> getResolvedContent(@RequestBody EditorContent editorContent){
        log.info("[getResolvedContent] request received for id: "+ editorContent.getContentId());
        return new ResponseEntity<>(Response.successResponse(this.editorService.getResolvedContent(editorContent)), HttpStatus.OK);
    }

    @GetMapping("/getContentEntries")
    public ResponseEntity<Response<List<EditorContent>>> getContentEntries(){
        log.info("[getContentEntries] request received");
        return new ResponseEntity<>(Response.successResponse(this.editorService.getContentEntries()), HttpStatus.OK);
    }

}

