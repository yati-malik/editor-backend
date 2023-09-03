package com.intuit.editor.rest;

import com.intuit.editor.dto.Response;
import com.intuit.editor.request.CreateContentModel;
import com.intuit.editor.request.EditorContent;
import com.intuit.editor.services.IEditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EditorController {

    @Autowired
    private IEditorService editorService;

    @GetMapping("/getContent")
    public ResponseEntity<Response<EditorContent>> getContent(@RequestParam String id){
        return new ResponseEntity<>(Response.successResponse(this.editorService.getContent(id)), HttpStatus.OK);
    }

    @PostMapping("/createContent")
    public ResponseEntity<Response<Object>> createContent(@RequestBody CreateContentModel editorContent){
        return new ResponseEntity<>(Response.successResponse(this.editorService.createContent(editorContent)), HttpStatus.OK);
    }

    @PostMapping("/updateContent")
    public ResponseEntity<Response<Object>> updateContent(@RequestBody EditorContent editorContent){
        this.editorService.updateContent(editorContent);
        return new ResponseEntity<>(Response.successResponse(), HttpStatus.OK);
    }

    @PostMapping("/getResolvedContent")
    public ResponseEntity<Response<EditorContent>> getResolvedContent(@RequestBody EditorContent editorContent){
        return new ResponseEntity<>(Response.successResponse(this.editorService.getResolvedContent(editorContent)), HttpStatus.OK);
    }

    @GetMapping("/getContentEntries")
    public ResponseEntity<Response<List<EditorContent>>> getContentEntries(){
        return new ResponseEntity<>(Response.successResponse(this.editorService.getContentEntries()), HttpStatus.OK);
    }

}

