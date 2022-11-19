package com.jaddy.calendarresourceoauth.service.userservices;

import com.jaddy.calendarresourceoauth.constants.Role;
import com.jaddy.calendarresourceoauth.dao.CustomerDao;
import com.jaddy.calendarresourceoauth.dao.ManagerDao;
import com.jaddy.calendarresourceoauth.dao.UsersDao;
import com.jaddy.calendarresourceoauth.ds.users.Customer;
import com.jaddy.calendarresourceoauth.ds.users.Manager;
import com.jaddy.calendarresourceoauth.ds.users.Users;
import com.jaddy.calendarresourceoauth.model.dtos.CustomerPrincipal;
//import com.jaddy.calendarresourceoauth.model.dtos.ManagerPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerDetailsService implements UserDetailsService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private ManagerDao managerDao;

    @Autowired
    private UsersDao usersDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersDao.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomerPrincipal(user);
    }

//    public UserDetails loadManagerByUsername(String username) throws UsernameNotFoundException{
//        Manager manager =  managerDao.findByUsername(username);
//        if(manager == null){
//            throw new UsernameNotFoundException(username);
//        }
//        return new CustomerPrincipal(manager);
//    }

    @Transactional
    public UserDetails saveManager(Users manager) throws RuntimeException{
        Users managerPrincipalEnt = usersDao.findByUsername(manager.getUsername());
        if(managerPrincipalEnt != null){
            throw new RuntimeException("Username taken");
        }
        if(managerPrincipalEnt == null){
            usersDao.save(manager);
            managerDao.save(new Manager(manager.getId()));
        }
        return new CustomerPrincipal(manager);
    }


    @PreAuthorize("isAnonymous()")
    @Transactional
    public UserDetails saveCustomer(Users customer) throws RuntimeException {
        Users customerPrincipalEnt = usersDao.findByUsername(customer.getUsername());

        if(customerPrincipalEnt != null){
            throw new RuntimeException("Username taken");
        }
        if(customerPrincipalEnt == null){
            usersDao.save(customer);
            customerDao.save(new Customer(customer.getId()));
        }
        return new CustomerPrincipal(customer);
    }
}
