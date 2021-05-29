package com.nonononoki.alovoa.html;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.nonononoki.alovoa.entity.User;
import com.nonononoki.alovoa.entity.user.UserNotification;
import com.nonononoki.alovoa.repo.ConversationRepository;
import com.nonononoki.alovoa.repo.UserRepository;
import com.nonononoki.alovoa.service.AuthService;
import com.nonononoki.alovoa.service.CaptchaService;
import com.nonononoki.alovoa.service.RegisterService;
import com.nonononoki.alovoa.service.RegisterServiceTest;
import com.nonononoki.alovoa.service.UserService;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class NotificationResourceTest {
	
	@Autowired
	private RegisterService registerService;

	@Autowired
	private CaptchaService captchaService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ConversationRepository conversationRepo;
	
	@Value("${app.first-name.length-max}")
	private int firstNameLengthMax;

	@Value("${app.first-name.length-min}")
	private int firstNameLengthMin;
	
	@Autowired
	private NotificationResource notificationResource;

	@MockBean
	private AuthService authService;
	
	private List<User> testUsers;
	
	@BeforeEach
	public void before() throws Exception {
		testUsers = RegisterServiceTest.getTestUsers(captchaService, registerService, firstNameLengthMax, firstNameLengthMin);
	}
	
	@AfterEach
	public void after() throws Exception {
		RegisterServiceTest.deleteAllUsers(userService, authService, captchaService, conversationRepo, userRepo);
	}
	
	@Test
	public void test() throws Exception {
		Mockito.when(authService.getCurrentUser()).thenReturn(testUsers.get(0));
		
		User user = testUsers.get(2);
		User currUser = testUsers.get(1);
		
		UserNotification not = new UserNotification();
		not.setContent(UserNotification.USER_LIKE);
		not.setDate(new Date());
		not.setUserFrom(currUser);
		not.setUserTo(user);
		currUser.getNotifications().add(not);
		userRepo.saveAndFlush(currUser);
		
		notificationResource.notification();
	}
}
