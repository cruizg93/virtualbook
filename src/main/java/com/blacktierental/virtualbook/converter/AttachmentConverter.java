package com.blacktierental.virtualbook.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.blacktierental.virtualbook.exceptions.ObjectNotFoundException;
import com.blacktierental.virtualbook.model.Attachment;
import com.blacktierental.virtualbook.service.AttachmentService;

@Component
public class AttachmentConverter implements Converter<Object,Attachment> {
	static final Logger logger = LoggerFactory.getLogger(AttachmentConverter.class);
	
	@Autowired
	AttachmentService attachmentService;
	
	public Attachment convert(Object element){
		if(element instanceof Attachment){
			return (Attachment)element;
		}else{
			try {
				Integer id = Integer.parseInt((String)element);
				Attachment attachment = attachmentService.findById(id);
				logger.info("Attachment:",attachment);
				return attachment;
			} catch (ObjectNotFoundException e) {
				return null;
			}
		}
	}
}
