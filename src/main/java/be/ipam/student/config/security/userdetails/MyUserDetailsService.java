package be.ipam.student.config.security.userdetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import be.ipam.student.model.Student;
import be.ipam.student.service.StudentService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

  private static final Logger log = LoggerFactory.getLogger(MyUserDetailsService.class);
	
  @Autowired
  StudentService studentService;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	 log.info("loadUserByUsername "+username);
	  Optional<MyUserDetails> user = Optional.empty();
	  //Ici normalement je récupère le user selon le paramètre login
	  Optional<Student> studentEntity = studentService.findByMail(username);

	  if(studentEntity.isPresent()) {
		  Student se = studentEntity.get();
		  log.info("FOUND " + se.getFirstName());
		  List<String> rolelist = new ArrayList<String>();
		  //se.getRoles().forEach(r -> rolelist.add("ROLE_"+r.getName().toUpperCase()));
		  rolelist.add("ROLE_STUDENT");
		  rolelist.add("ADMIN");
		  //Ici je crée un user spring sur base de mon Student
		  user = Optional.of(new MyUserDetails(se.getMail(),se.getPasswordHash(),rolelist));
	  }

	  if (user.isEmpty()) {
		  throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
	  }

	  //LOG
	  log.info("My pass is "+user.get().getPassword());
	  
	  return user.get();
  }

}