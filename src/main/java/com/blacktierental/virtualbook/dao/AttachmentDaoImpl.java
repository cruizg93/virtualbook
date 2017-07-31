package com.blacktierental.virtualbook.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.blacktierental.virtualbook.exceptions.ObjectNotFoundException;
import com.blacktierental.virtualbook.model.Attachment;

@Repository("attachmentDao")
public class AttachmentDaoImpl extends AbstractDao<Integer, Attachment> implements AttachmentDao{

	static final Logger logger = LoggerFactory.getLogger(AttachmentDaoImpl.class);
	
	@Override
	public Attachment findById(int id) throws ObjectNotFoundException {
		Attachment attachment = getByKey(id);
		if(attachment == null){
			throw new ObjectNotFoundException("Attachment with id: "+id+" was not found");
		}
		return attachment;
	}

	@Override
	public List<Attachment> findAllItemAttachment() {
		Criteria criteria = createEntityCriteria();
		criteria.addOrder(Order.asc("description"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates
		return (List<Attachment>)criteria.list();
	}

	@Override
	public void save(Attachment itemAttachment) {
		persist(itemAttachment);
	}

	@Override
	public void deleteById(int id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id",id));
		Attachment attachment = (Attachment) crit.uniqueResult();
		delete(attachment);
	}

}
