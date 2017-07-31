package com.blacktierental.virtualbook.service;

import java.util.List;

import com.blacktierental.virtualbook.exceptions.ObjectNotFoundException;
import com.blacktierental.virtualbook.model.Attachment;

public interface AttachmentService {

	Attachment findById(int id) throws ObjectNotFoundException;
	List<Attachment> findAllItemAttachment();
	void save(Attachment itemAttachment);
	void deleteById(int id);
	void updateAttachment(Attachment attachment) throws ObjectNotFoundException;
}
