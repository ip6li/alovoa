package com.nonononoki.alovoa.html;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nonononoki.alovoa.entity.User;
import com.nonononoki.alovoa.service.AuthService;

@Controller
public class IndexResource {

	@Autowired
	private AuthService authService;

	@Autowired
	private ProfileResource profileResource;

	@GetMapping("/")
	public ModelAndView index() throws Exception {

		User user = authService.getCurrentUser();
		if (user != null) {
			return profileResource.profile();
		}

		ModelAndView mav = new ModelAndView("index");
		return mav;
	}
}
