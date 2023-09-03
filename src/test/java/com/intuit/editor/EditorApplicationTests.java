package com.intuit.editor;

import com.intuit.editor.entity.ContentEntity;
import com.intuit.editor.repository.ContentRespository;
import com.intuit.editor.request.CreateContentModel;
import com.intuit.editor.request.EditorContent;
import com.intuit.editor.services.IEditorService;
import org.junit.Assert;
import org.mockito.Mock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan(basePackages = "com.intuit.editor")
public class EditorApplicationTests {

	@Autowired
	private IEditorService editorService;

	@Mock
	private ContentRespository contentRespository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testCreateContent(){
		CreateContentModel contentModel = new CreateContentModel();
		contentModel.setTitle("test1");
		EditorContent editorContent =  this.editorService.createContent(contentModel);
		Assert.assertEquals(contentModel.getTitle(), editorContent.getTitle());
	}

	@Test
	public void testUpdateContent(){
		EditorContent editorContent = new EditorContent();
		editorContent.setContentId("test_1");
		editorContent.setTitle("title_1");
		ContentEntity content = editorService.updateContent(editorContent);
		Assert.assertEquals(content.getDTitle(), editorContent.getTitle());
	}

}
