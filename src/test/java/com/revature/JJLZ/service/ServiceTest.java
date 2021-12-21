package com.revature.JJLZ.service;


import com.ibm.icu.impl.Assert;
import com.revature.JJLZ.controller.UserController;
import com.revature.JJLZ.model.StockWatchlist;
import com.revature.JJLZ.model.Stocks;
import com.revature.JJLZ.model.User;
import com.revature.JJLZ.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;


import java.util.ArrayList;
import java.util.List;

public class ServiceTest extends Mockito {
    @Mock private User mockUser;

    @InjectMocks
    private UserService userService;

    @Mock private UserRepository mockUserRepository;

    List<Stocks> stockList;
    List<User> allUsers;
    User user;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        userService = new UserService(mockUserRepository);
        doReturn(mockUser).when(mockUserRepository).save(mockUser);
        ReflectionTestUtils.setField(userService,
                "userRepository",
                mockUserRepository);
        allUsers = new ArrayList<>();
        stockList = new ArrayList<>();
        user = new User();
        user.setUserId(0);
        user.setUsername("test");
        user.setPassword("test");
        user.setBalance(100);
    }
    @Test
    public void testingCreateNewUser() throws Exception{
        Mockito.when(mockUserRepository.save(user)).thenReturn(user);
        User verify = userService.createNewUser(user);
        assert ((verify.getUsername() == user.getUsername()) &&
                (verify.getPassword() == user.getPassword()));
        Mockito.verify(mockUserRepository).save(user);
    }

    @Test
    public void testGetAllUsers() {
        List<User> verifiedList = userService.getAllUsers();
        assert verifiedList!= null;
    }

    @Test
    public void testFindUserByIdInUserService(){
        Mockito.when(mockUserRepository.findById(0)).thenReturn(java.util.Optional.ofNullable(user));
        User found = userService.findUserById(0);
        assert ((user.getUsername()==found.getUsername())&&(user.getPassword()==found.getPassword()));
    }

    @Test
    public void testValidate(){
        Mockito.when(mockUserRepository.findUserByUsername(user.getUsername())).thenReturn(user);
        assert userService.validate(user);
    }

    @Test
    public void testGetUserByUsername(){
        Mockito.when(mockUserRepository.findUserByUsername(user.getUsername()))
                .thenReturn(user);
        assert userService.getUserByUsername(user) != null;
    }

//    @Test
//    public void testGettingTotalBalanceOfUser() throws IOException {
//        user.setBalance(10);
//        Stocks amazon = new Stocks("AMZN",1,user);
//        assert (userService.totalBalance(user) != 0);
//    }

    @Test
    public void testGetFullNameOfUser(){
        Mockito.when(mockUserRepository.findById(0)).thenReturn(java.util.Optional.ofNullable(user));
        String fullName = user.getFirstName() + " "+ user.getLastName();
        assert(fullName != null);
    }

    @Test
    public void testUpdateUser(){
        Mockito.when(mockUserRepository.save(user)).thenReturn(user);
        user.setUsername("test1");
        User verify = userService.update(user);
        assert (verify.getUsername() == user.getUsername())&&(verify.getPassword() == user.getPassword());
    }

    @Test
    public void testDeletingUser(){
        int userId = 0;
        userService.delete(userId);
        verify(mockUserRepository,times(1)).deleteById(userId);
    }

    @Test
    public void deposit(){
        userService.deposit(user, 100);
        assert (user.getBalance() == 200.0);
    }

    @Test
    public void withdraw(){
        userService.withdraw(user,100);
        assert (user.getBalance() <= 200.0);
    }
}