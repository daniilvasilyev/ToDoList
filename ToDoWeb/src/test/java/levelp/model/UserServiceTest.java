package levelp.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @InjectMocks
    private UserService userService = new UserService();
    @Mock
    private UserRepository accountRepositoryMock;

    @Test
    public void shouldInitializeWithTwoDemoUsers() {
        // act
        userService.initialize();
        // assert
        verify(accountRepositoryMock, times(2)).save(any(User.class));
    }

    @Test
    public void shouldThrowExceptionWhenUserNotFound() {
        // arrange
        thrown.expect(UsernameNotFoundException.class);
        thrown.expectMessage("user not found");

        when(accountRepositoryMock.findByEmail("user@example.com")).thenReturn(null);
        // act
        userService.loadUserByUsername("user@example.com");
    }

    @Test
    public void shouldReturnUserDetails() {
        // arrange
        User demoUser = new User("user@example.com", "demo", "ROLE_USER");
        when(accountRepositoryMock.findByEmail("user@example.com")).thenReturn(demoUser);

        // act
        UserDetails userDetails = userService.loadUserByUsername("user@example.com");

        // assert
        assertEquals(demoUser.getEmail(), userDetails.getUsername());
        assertEquals(demoUser.getPassword(), userDetails.getPassword());
        assertTrue(hasAuthority(userDetails, demoUser.getRole()));
    }

    private boolean hasAuthority(UserDetails userDetails, String role) {
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals(role)) {
                return true;
            }
        }
        return false;
    }
}
