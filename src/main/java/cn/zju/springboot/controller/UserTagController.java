package cn.zju.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zju.springboot.pojo.UserTag;
import cn.zju.springboot.service.UserTagService;
import cn.zju.springboot.service.UserTagServiceImpl;

@Controller
@RequestMapping("usertag/")
public class UserTagController {
	
	@Autowired
	private UserTagService userTagService;
	UserTag tag;
	

	
	/**
	 * 添加标签
	 * @param tag
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/add/{tagName}")
	@ResponseBody
	public Object addTagToUser(@PathVariable("tagName")String tagName,Integer userId){
		userId=10;
		
		Object re=userTagService.addTagByName(userId, tagName);
		return re;
	}
	
	/**
	 * 删除标签
	 * @param tag
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/del/{tagName}")
	@ResponseBody
	public Object delTagForUser(@PathVariable("tagName")String tag,Integer userId){
		userId=10;
		Object re=userTagService.delTag(userId, tag);
		return re;
	}
	
	
	/**
	 * 修改标签
	 * @param tag
	 * @return
	 */
	@RequestMapping(value = "/update/{tagName}")
	@ResponseBody
	public Object updateTag(@PathVariable("tagName")String tag2,Integer userId,String tag1) {
		userId=55;
		tag1="历史";
		Object re=userTagService.updateTag(userId, tag1, tag2);
		return re;
	}
	
	/**
	 * 查询标签
	 */
	@RequestMapping(value = "/select/{id}")
	@ResponseBody
	public Object selectTag(@PathVariable("id")int id) {
		return userTagService.selTagById(id);
	}
	
}
