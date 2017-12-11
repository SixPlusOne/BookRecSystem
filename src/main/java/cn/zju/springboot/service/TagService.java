package cn.zju.springboot.service;

import cn.zju.springboot.pojo.Tag;

public interface TagService {

	public Tag getTagByName(String name);

	public int insertTag(Tag tag);

	public int deleteTagByName(String name);

	public int updateTag(String oldName, String newName);
}
