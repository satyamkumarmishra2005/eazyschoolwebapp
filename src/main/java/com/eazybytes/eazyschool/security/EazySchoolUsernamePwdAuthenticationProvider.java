package com.eazybytes.eazyschool.security;

import com.eazybytes.eazyschool.model.Person;
import com.eazybytes.eazyschool.model.Roles;
import com.eazybytes.eazyschool.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Profile("prod")
public class EazySchoolUsernamePwdAuthenticationProvider
        implements AuthenticationProvider
    //The user enters their email and password.
        //Spring Security calls authenticate(authentication).
        //The method fetches the user from the database.
        //If the user exists and the password matches, the method returns an authenticated token with user details and roles.
        //If authentication fails, an exception is thrown.
{
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String email = authentication.getName(); // Get the email entered by the user
        String pwd = authentication.getCredentials().toString();// // Get the password entered by the user
        Person person = personRepository.readByEmail(email); // // Fetch user details from DB based on email
        if(null != person && person.getPersonId()>0 && passwordEncoder.matches(pwd,person.getPwd()) ){//If person is not null and has a valid personId, it means the user exists.
           //     Then, it compares the entered password (pwd) with the stored password (person.getPwd()).

            return new UsernamePasswordAuthenticationToken(
                    email, pwd, getGrantedAuthorities(person.getRoles()));  // If the password is correct, it creates an authenticated UsernamePasswordAuthenticationToken object.
//            It passes:
//            person.getName() → User’s name.
//            pwd → Password.
//                    getGrantedAuthorities(person.getRoles()) → The user’s roles (authorities).
        }else{
            throw new BadCredentialsException("Invalid credentials!");
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(Roles roles) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+roles.getRoleName()));
        return grantedAuthorities; //Converts the user's role into Spring Security's GrantedAuthority format.
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class); // Whenever my Authentication object of type UsernamePasswordAuthenticationToken
        // then only Invoke the Authentication logic that is provided
        //This method tells Spring Security that this provider only supports UsernamePasswordAuthenticationToken.
        //This means that Spring Security will only call this provider when username-password authentication is used.
    }
}