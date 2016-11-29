package com.blacktierental.virtualbook.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.blacktierental.virtualbook.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao{

	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	public User findById(int id) {
		User user = getByKey(id);
		if(user!=null){
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	public User findByUsername(String username) {
		System.out.println("UserName:"+username);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("username",username));
		User user = (User)crit.uniqueResult();
		if(user!=null){
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	public void save(User user) {
		persist(user);
	}

	public void deleteByUsername(String username) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("username",username));
		User user = (User)crit.uniqueResult();
		delete(user);
	}

	public List<User> findAllUsers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("username"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>)criteria.list();
		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
        // Uncomment below lines for eagerly fetching of userProfiles if you want.
        /*
        for(User user : users){
            Hibernate.initialize(user.getUserProfiles());
        }*/
		return users;
	}
}
