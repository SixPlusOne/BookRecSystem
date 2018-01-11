package cn.zju.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;

import cn.zju.springboot.pojo.BookForm;


public interface BookFormMapper extends Mapper<BookForm>{
	
	BookForm findone(@Param("id") int id);
	BookForm findbyname(@Param("name") String name);
	List<BookForm> findBooksByTagPaged(@Param("tag") String tag);
	List<BookForm> getSimilarBooks(@Param("id") int id);
	List<BookForm> findBooksByWords(@Param("words") String words);
}
