package cn.zju.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;

import cn.zju.springboot.pojo.Book;

public interface BookMapper extends Mapper<Book>{
	
	List<Book> findBooksByTagPaged(@Param("tag") String tag);
	List<Book> search(@Param("words") List<String> words);
	

}
