package cn.zju.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;

import cn.zju.springboot.mapper.TagMapper;
import cn.zju.springboot.pojo.Tag;

@Service
public class TagServiceImpl implements TagService{

	@Autowired
	private TagMapper tagMapper;

	/**
	 * 根据tag的name查找tag
	 * @param name
	 * @return
	 */
	@Override
	public Tag getTagByName(String name) {
		return this.tagMapper.selectByPrimaryKey(name);
	}
	
	// c, 增加一条tag
	// 返回值, 不确定是什么, 是插入后的影响记录数么
	/**
	 * 
	 * @param tag
	 * @return
	 */
	@Override
	public int insertTag(Tag tag) {
		return tagMapper.insert(tag);
	}
	
	/**
	 * delete by tag name
	 * @param name
	 * @return
	 */
	@Override
	public int deleteTagByName(String name) {
		return tagMapper.deleteByPrimaryKey(name);
	}
	
	/**
	 * update tag by tag
	 * @param record
	 * @return
	 */
	@Override
	public int updateTag(String oldName, String newName) {
		Example example = new Example(Tag.class);
		example.createCriteria().andEqualTo("name", oldName);
		Tag record = new Tag();
		record.setName(newName);
//		return tagMapper.updateByExampleSelective(record, example);
		return tagMapper.updateByExample(record, example);
	}
}
