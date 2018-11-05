package mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import mvc.model.Member;
import mvc.services.MemberService;

@Controller
@RequestMapping("/member")
@SessionAttributes("user")
public class MemberController {

	private static final String RESULT_CHECK = "check/member";
	
	@Autowired
	private MemberService service;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "member/join";
	}
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(Model model, Member member) {
		
		Object result = service.join(member);
		model.addAttribute("join", result);
		
		return RESULT_CHECK;
	}
	
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "member/login";
	}
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(Model model, Member member) {
		
		Object obj = service.login(member);
		if(obj != null) {
			model.addAttribute("user", obj);
			model.addAttribute("login", 1);
		} else {
			model.addAttribute("login", 0);
		}
		return RESULT_CHECK;
	}
	
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify() {
		return "member/modify";
	}
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(Model model, 
			@ModelAttribute("user") Member user, 
			Member member) {
		
		member.setId(user.getId());
		
		Object obj = service.modify(member);
		if(obj != null) {
			model.addAttribute("user", obj);
			model.addAttribute("modify", 1);
		} else {
			model.addAttribute("modify", 0);
		}
		return RESULT_CHECK;
	}
	

	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(Model model, SessionStatus status) {
		
		status.setComplete();
		model.addAttribute("logout", "1");
		
		return RESULT_CHECK;
	}
}
