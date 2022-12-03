package com.jaddy.calendarresourceoauth.integration;

import com.jaddy.calendarresourceoauth.config.ResourceServerConfig;
import com.jaddy.calendarresourceoauth.config.SecurityConfig;
import com.jaddy.calendarresourceoauth.constants.Role;
import com.jaddy.calendarresourceoauth.dao.ManagerDao;
import com.jaddy.calendarresourceoauth.dao.UsersDao;
import com.jaddy.calendarresourceoauth.ds.users.Manager;
import com.jaddy.calendarresourceoauth.ds.users.Users;
import com.jaddy.calendarresourceoauth.service.userservices.CustomerDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.data.repository.config.BootstrapMode;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import java.util.List;

@TestConfiguration
@SpringBootTest()
class ManagerServiceIntegrationTst {
    @Autowired
    private ManagerDao managerDao;
    @Autowired
    private UsersDao usersDao;
    @Autowired
    private CustomerDetailsService customerDetailsService;

    List<Users> managerList = List.of(new Users(05211L, "dvega", "{noop}password123!", Role.ROLE_MANAGER.name(), Role.ROLE_MANAGER.getAuthorities()),
            new Users(21234L, "testmanager@example.org", "{noop}password123!", Role.ROLE_MANAGER.name(), Role.ROLE_MANAGER.getAuthorities()),
                    new Users(25234L, "testmanager2@example.org", "{noop}password123!", Role.ROLE_MANAGER.name(), Role.ROLE_MANAGER.getAuthorities()));

    @Commit
    @Test
    public void testSaveManagerAndAddSecurityContext(){
            customerDetailsService.saveManager(managerList.get(0));
            customerDetailsService.saveManager(managerList.get(1));
            customerDetailsService.saveManager(managerList.get(2));
            Users tstManager = usersDao.findByUsername(managerList.get(0).getUsername());
            Assertions.assertEquals(managerList.get(0).getUsername(),tstManager.getUsername());
    }




}
