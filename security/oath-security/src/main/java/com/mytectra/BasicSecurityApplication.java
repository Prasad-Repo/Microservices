package com.mytectra;

import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.Data;


public class BasicSecurityApplication {


	
	@Bean 
	public CommandLineRunner commandLineRunner(UserRepository userRepository) {
		return args->Stream.of("mytectra,{noop}mytectra" , "prasad,{noop}prasad")
				.map(u-> u.split(","))
				.forEach(t -> userRepository.save(new UserAccount(t[0],t[1],true)));
				
	}

}


@Service 
class  CustomerUserDetailsService implements UserDetailsService{
	private final UserRepository userRepository;
	
	@Autowired
	public CustomerUserDetailsService(UserRepository userRepository) {
		this.userRepository=userRepository;
	}

	
	  @Override public UserDetails loadUserByUsername(String userName) throws
	  UsernameNotFoundException {
		  return this.userRepository.findByUserName(userName) 
				  .map(u-> new User(u.getUserName(),u.getPassword(),
	  u.isActive(),u.isActive(),u.isActive(),u.isActive(),
	  AuthorityUtils.createAuthorityList("ADMIN","USER"))) .orElseThrow(()->new
	  UsernameNotFoundException("Could not find user :" + userName)); }
	 
	
	
	
}

  interface UserRepository extends JpaRepository<UserAccount,Long>{
  Optional<UserAccount> findByUserName(String userName);
  
  }
 





@Data
@Entity
class UserAccount{
	
	public UserAccount() {
	
	}
	@Id
	@GeneratedValue
	private Long id;
	
	public UserAccount(String userName, String password, boolean active) {
		super();
		this.userName = userName;
		this.password = password;
		this.active = active;
	}
	private String userName;
	private String password;
	private boolean active;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

	
	
	
	
	
}







