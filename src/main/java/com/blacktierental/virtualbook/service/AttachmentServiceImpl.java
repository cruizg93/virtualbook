package com.blacktierental.virtualbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blacktierental.virtualbook.model.Attachment;
import com.blacktierental.virtualbook.dao.AttachmentDao;

@Service("attachmentService")
@Transactional
public class AttachmentServiceImpl implements AttachmentService{

	@Autowired
	private AttachmentDao dao;
	
	@Override
	public Attachment findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<Attachment> findAllItemAttachment() {
		return dao.findAllItemAttachment();
	}

	@Override
	public void save(Attachment itemAttachment) {
		dao.save(itemAttachment);
	}

	@Override
	public void deleteById(int id) {
		dao.deleteById(id);
	}

	@Override
	public void updateAttachment(Attachment attachment) {
		Attachment entity = dao.findById(attachment.getId());
		if(entity != null){
			entity.setDescription(attachment.getDescription());
		}
	}

}
