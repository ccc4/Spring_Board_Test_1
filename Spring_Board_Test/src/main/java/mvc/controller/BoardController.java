package mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import mvc.model.Board;
import mvc.model.Member;
import mvc.services.BoardService;

@Controller
@RequestMapping("/board")
@SessionAttributes("id")
public class BoardController {
	
	private static final String RESULT_CHECK = "check/board";
	
	@Autowired
	private BoardService service;
	
	@RequestMapping(value="/ ", method=RequestMethod.GET)
	public String list(Model model, 
			@RequestParam(name="page", defaultValue="1", required=false) int page) {
		
		Object result = service.list(page);
		model.addAttribute("p", result);
		
		return "board/main";
	}
	
	@RequestMapping(value="/view/{idx}", method=RequestMethod.GET)
	public String view(Model model, @PathVariable int idx) {
		
		Object result = service.view(idx);
		model.addAttribute("board", result);
		
		return "board/view";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write() {
		return "board/write";
	}
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(Model model, 
			@ModelAttribute("user") Member user, 
			Board board) {
		
		board.setId(user.getId());
		Object result = service.write(board);
		model.addAttribute("write", result);
		
		return RESULT_CHECK;
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify(Model model, @RequestParam int idx) {
		
		Object result = service.view(idx);
		model.addAttribute("board", result);
		
		return "board/modify";
	}
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(Model model, Board board) {
		
		Object result = service.modify(board);
		model.addAttribute("modify", result);
		
		return RESULT_CHECK;
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(Model model, @RequestParam int idx) {
		
		Object result = service.delete(idx);
		model.addAttribute("delete", result);
		
		return RESULT_CHECK;
	}
}
