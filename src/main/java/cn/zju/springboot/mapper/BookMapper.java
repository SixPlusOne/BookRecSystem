package cn.zju.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import com.github.abel533.mapper.Mapper;

import cn.zju.springboot.pojo.Book;
//@Mapper
@CacheConfig(cacheNames = "books")
public interface BookMapper extends Mapper<Book>{
	
	List<Book> findBooksByTagPaged(@Param("tag") String tag);
	List<Book> search(@Param("words") List<String> words);
	
	@Select("select name from book where id =#{id}")
    @Cacheable(key ="#p0") 
    String findById(@Param("id") String id);

}
