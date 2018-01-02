package cn.zju.springboot.mapper;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;

import cn.zju.springboot.pojo.BookForm;


public interface BookFormMapper extends Mapper<BookForm>{
	
	BookForm findone(@Param("id") int id);

}
