package cn.zju.springboot.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zju.springboot.pojo.Tag;
import cn.zju.springboot.service.TagService;

@Controller
@RequestMapping("tag/update/")
public class TagController {
	
	@Autowired
	private TagService tagService;
	
	
	@RequestMapping(value = "{name}")
	@ResponseBody
	public Object queryTag(@PathVariable("name") String name, HttpServletRequest request) {
//		查询
//		return tagService.getTagByName(name);
		
//		增加
//		Tag tag = new Tag();
//		tag.setName(name);
//		return tagService.insertTag(tag);
		
//		删除
//		return tagService.deleteTagByName(name);
	
//		修改
		String oldName = "t3";
		String newName = name;
		return tagService.updateTag(oldName, newName);
	}
}
