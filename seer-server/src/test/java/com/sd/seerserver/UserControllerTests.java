package com.sd.seerserver;

import com.sd.seerserver.entity.User;
import com.sd.seerserver.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTests {

    @Autowired
    private UserService userService;

    @Test
    public void testSignup() {
        User user = new User();
        user.setName("hxk");
        userService.signup(user);
    }
}
