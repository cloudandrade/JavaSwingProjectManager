package test;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;
import dao.UserDAO;
import model.UserModel;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import controller.LoginController;
import util.CacheUtils;
import util.EncryptUtils;
import util.Messages;


public class LoginControllerTest {

    @Mock
    private UserDAO mockUserDAO;
    @Mock
    private UserModel mockUser;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        LoginController.userDao = mockUserDAO; // Injecting mock DAO into controller
    }

    @Test
    public void testValidateLoginUserNotFound() throws Exception {
        when(mockUserDAO.findByEmail(anyString())).thenReturn(null);
        
        ArrayList<String> result = LoginController.validateLogin("nonexistent@example.com", "password");
        
        assertEquals(Messages.USER_EMAIL_NOT_FOUND, result);
    }

    @Test
    public void testValidateLoginIncorrectPassword() throws Exception {
        when(mockUserDAO.findByEmail(anyString())).thenReturn(mockUser);
        when(mockUser.getPassword()).thenReturn("hashedpassword");
        when(EncryptUtils.checkPassword("password", "hashedpassword")).thenReturn(false);
        
        ArrayList<String> result = LoginController.validateLogin("user@example.com", "password");
        
        assertEquals(Messages.PASSWORD_WRONG, result);
    }

    @Test
    public void testValidateLoginSuccess() throws Exception {
        when(mockUserDAO.findByEmail(anyString())).thenReturn(mockUser);
        when(mockUser.getPassword()).thenReturn("hashedpassword");
        when(EncryptUtils.checkPassword("password", "hashedpassword")).thenReturn(true);

        // Mocking static method CacheUtils.putUserOnLocalStorage()
        try (MockedStatic<CacheUtils> mockedCacheUtils = Mockito.mockStatic(CacheUtils.class)) {
            ArrayList<String> result = LoginController.validateLogin("user@example.com", "password");
            assertEquals(Messages.SUCCESS_AUTH, result);
            mockedCacheUtils.verify(() -> CacheUtils.putUserOnLocalStorage(mockUser));
        }
    }

    @Test
    public void testValidateLoginGeneralError() throws Exception {
        when(mockUserDAO.findByEmail(anyString())).thenThrow(new RuntimeException());
        
        ArrayList<String> result = LoginController.validateLogin("user@example.com", "password");
        
        assertEquals(Messages.GENERAL_ERROR, result);
    }

    @Test
    public void testOnLogout() {
        // Mocking static method CacheUtils.deleteUserOnLocalStorage()
        try (MockedStatic<CacheUtils> mockedCacheUtils = Mockito.mockStatic(CacheUtils.class)) {
            LoginController.onLogout();
            mockedCacheUtils.verify(CacheUtils::deleteUserOnLocalStorage);
        }
    }
}