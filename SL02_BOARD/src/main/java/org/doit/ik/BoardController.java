package org.doit.ik;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j // 밑에 주석이랑 똑같다.
@AllArgsConstructor
@RequestMapping("/board/*")
public class BoardController {
	
	// private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	// @Autowired
	private BoardService boardService;
	
	/*
	 * @GetMapping("/board/list") public String list(Model model) {
	 * 
	 * // 이걸 쓸때 사용한다. log.info("> BoardController.list()");
	 * 
	 * model.addAttribute("list", this.boardService.getList());
	 * 
	 * return "/board/list"; }
	 */
	

	 @GetMapping("/list") // void 줄 때 맵핑 패턴이랑 return이 같으면 생략 가능하다. public
	 public void list(Model model) {
		 log.info("> BoardController.list()");
		 model.addAttribute("list", this.boardService.getList());
	 }

	
	/*
	@GetMapping("/board/list")
	public ModelAndView list(ModelAndView mav) {
		
		// 이걸 쓸때 사용한다.
		log.info("> BoardController.list()");
		
		mav.addObject("list", this.boardService.getList());
		mav.setViewName("/board/list");
		
		// return "/board/list";
		return mav;
	}
	*/
	 
	/* <a href="/board/register">글쓰기</a> */
	 @GetMapping("/register") // void 줄 때 맵핑 패턴이랑 return이 같으면 생략 가능하다. public
	 public void register(  ) {
		 log.info("> BoardController.list()");
	 }
	 
	 @PostMapping("/register") // void 줄 때 맵핑 패턴이랑 return이 같으면 생략 가능하다. public
	 public String register( BoardVO board, RedirectAttributes rttr ) { // RedirectAttributes : 리다이렉트할 때 값을 가져간다.
		 log.info("> BoardController.list() POST");
		 this.boardService.register(board);
		 rttr.addFlashAttribute("result", board.getBno());
		 return "redirect:/board/list";
		 // return "redirect:/board/list?bno=2";
		 // return "redirect:/board/list/2";
	 }
	
	 @PostMapping(value = {"/modify"})
	 public String modify(BoardVO boardVO, RedirectAttributes rttr) {
		 log.info("> BoardController.modify() POST");
		 
		 if ( this.boardService.modify(boardVO) ) {
			rttr.addFlashAttribute("result", "SUCCESS");
		} // if
		 
		 return String.format("redirect:/board/get?bno=%d", boardVO.getBno()); 
	 }
	 
	 @GetMapping(value = {"/get", "/modify"})
	 public void get(@RequestParam("bno") Long bno, Model model) {
		 log.info("> BoardController.get()");
		 model.addAttribute("boardVO", this.boardService.get(bno));
	 }
	 
		/*
		 * @GetMapping(value = {"/get/{bno}"}) public void get(@PathVariable("bno") Long
		 * bno, Model model) { log.info("> BoardController.get()");
		 * model.addAttribute("boardVO", this.boardService.get(bno)); }
		 */
	 
	 @GetMapping(value = {"/delete"})
	 public String delete(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		 log.info("> BoardController.delete()");
		 
		 if ( this.boardService.delete(bno) ) {
				rttr.addFlashAttribute("result", "SUCCESS");
		} // if
		 
		 return "redirect:/board/list";
	 }
	 
} // class
