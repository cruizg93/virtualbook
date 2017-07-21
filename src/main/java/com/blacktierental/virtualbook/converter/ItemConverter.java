package com.blacktierental.virtualbook.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.blacktierental.virtualbook.model.Item;
import com.blacktierental.virtualbook.service.ItemService;

@Component
public class ItemConverter implements Converter<Object, Item>  {
	
	static final Logger logger = LoggerFactory.getLogger(ItemConverter.class);
	
	@Autowired
	ItemService itemService;
	
	/**
     * Gets Item by Id
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
	public Item convert(Object element){
		if(element instanceof Item){
			if( ((Item)element).getId()==null && ((Item)element).getDescription()==null){
				return new Item();
			}else{
				return (Item)element;
			}
		}else{
			Integer id = Integer.parseInt((String)element);
			Item item = itemService.findById(id);
			logger.info("Item:",item);
			return item;
		}
	}
}
