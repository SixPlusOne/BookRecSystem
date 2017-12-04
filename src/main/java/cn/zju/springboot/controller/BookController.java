package cn.zju.springboot.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.zju.springboot.service.BookService;

@Controller
@RequestMapping("book/")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value = "{id}")
	@ResponseBody
	public Object queryCartList(@PathVariable("id") int id,HttpServletRequest request) {
//        ModelAndView mv = new ModelAndView("book");
//        mv.addObject("cartList", bookService.getBookById(id));
        return bookService.getBookById(id);
    }

}
