package cn.zju.springboot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zju.springboot.pojo.Tag;
import cn.zju.springboot.service.TagService;

@Controller
@RequestMapping("tag/")
public class TagController {

	@Autowired
	private TagService tagService;

	@RequestMapping(value = "insert/{name}")
	@ResponseBody
	public Object insertTagByName(@PathVariable("name") String name, HttpServletRequest request) {
		Tag tag = new Tag();
		tag.setName(name);
		return tagService.insertTag(tag);
	}

	@RequestMapping("getTagsByUserId")
	@ResponseBody
	public Object getTagsByUserId(HttpServletRequest request,HttpServletResponse response)
	{
		
		response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setDateHeader("Expires", 0); 
		HttpSession session = request.getSession();
		Map<String, Object> result = new HashMap<>();

		Integer userId = (Integer) session.getAttribute("userId");
		System.out.print("in java get tags by userid" + userId);
		//System.out.print(tagService.getTagsByUserId(userId));
		return tagService.getTagsByUserId(userId);
	}
	
	@RequestMapping(value = "delete/{name}")
	@ResponseBody
	public Object deleteTagByName(@PathVariable("name") String name, HttpServletRequest request) {
		return tagService.deleteTagByName(name);
	}

	@RequestMapping(value = "queryByName/{name}")
	@ResponseBody
	public Object getBooksByTagName(@PathVariable("name") String name, HttpServletRequest request) {
		return tagService.getBooksByTagName(name);
	}

	@RequestMapping(value = "queryByNames/{names}")
	@ResponseBody
	public Object getBooksByTagNameList(@PathVariable("names") String names,  HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setDateHeader("Expires", 0); 
        
		String nameArr[] = names.split(",");
		List<String> nameList = new ArrayList<String>();
		for (String item : nameArr) {
			nameList.add(item);
			System.out.println(item);
		}
		return tagService.getBooksByTagNameList(nameList);
	}
}
