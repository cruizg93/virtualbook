package com.blacktierental.virtualbook.service;

import java.util.List;

import com.blacktierental.virtualbook.model.Attachment;
import com.blacktierental.virtualbook.model.Client;

public interface AttachmentService {

	Attachment findById(int id);
	List<Attachment> findAllItemAttachment();
	void save(Attachment itemAttachment);
	void deleteById(int id);
	void updateAttachment(Attachment attachment);
}
