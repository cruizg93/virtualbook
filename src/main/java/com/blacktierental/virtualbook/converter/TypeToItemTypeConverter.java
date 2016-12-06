package com.blacktierental.virtualbook.converter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.blacktierental.virtualbook.model.ItemType;
import com.blacktierental.virtualbook.service.ItemTypeService;

@Component
public class TypeToItemTypeConverter implements Converter<Object, ItemType> {

	static final Logger logger = LoggerFactory.getLogger(TypeToItemTypeConverter.class);
	
	@Autowired
	ItemTypeService itemTypeService;
	
	/**
     * Gets ItemType by Id
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
	public ItemType convert(Object element){
		Integer id = Integer.parseInt((String)element);
		ItemType type = itemTypeService.findById(id);
		logger.info("Type:",type);
		return type;
	}
}
