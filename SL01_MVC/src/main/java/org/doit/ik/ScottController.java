package org.doit.ik;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.doit.ik.domain.DeptDTO;
import org.doit.ik.domain.EmpDTO;
import org.doit.ik.mapper.scott.DeptMapper;
import org.doit.ik.mapper.scott.EmpMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.Setter;

@Controller
public class ScottController {
	
	private static final Logger logger = LoggerFactory.getLogger(ScottController.class);
	
	// DI
	// @Autowired // 이건 스프링이 제공한다.
	// 롬복으로도 autowired 랑 똑같이 할 수 있다. / 밑은 문법이다.
	@Setter(onMethod=@__({@Autowired}))
	private DeptMapper deptMapper;
	
	@Setter(onMethod=@__({@Autowired}))
	private EmpMapper empMapper;
	
	// @RequestMapping(value = "/scott/dept", method = RequestMethod.GET)
	@GetMapping(value = "/scott/dept")
	public String dept(Locale locale, Model model) {
		logger.info("scottController test"); // console 확인
		
		ArrayList<DeptDTO> list = this.deptMapper.selectDept();
		model.addAttribute("list", list);
		
		return "scott/dept";
	}
		
		// 커맨드 객체 사용
		@PostMapping(value = "/scott/emp")
		public String emp(Locale locale, Model model
				, @RequestParam(value = "deptno") int [] deptnos) {
			logger.info("scottController test"); // console 확인
			
			ArrayList<EmpDTO> list = this.empMapper.selectEmp(deptnos);
			model.addAttribute("list", list);
			
			return "scott/emp";
		}
	
	/* [1] 예전 풀이
	// 405 오류 발생 원인 : post 인데 get 인 경우
	// @RequestMapping(value = "/scott/dept", method = RequestMethod.POST)
	@PostMapping(value = "/scott/emp")
	public String emp(Locale locale, Model model, HttpServletRequest request) {
		logger.info("scottController test"); // console 확인
		
		int [] deptnos = null;
		String [] pdeptnos = request.getParameterValues("deptno");
		deptnos = new int [ pdeptnos.length ];
		for (int i = 0; i < pdeptnos.length; i++) {
			deptnos[i] = Integer.parseInt(pdeptnos[i]);
		} // for
		
		return "scott/emp";
	}
	*/
	
} // class
