package data;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface BoardMapper {
	@Select("select * from board")
	List<BoardDto> select();
	
	@Select("select * from board where sid = #{sid}")
	BoardDto selectOne(@Param("sid") String sid);
	
	@Insert("insert into board (writer, content, password, date) values (#{one.writer}, #{one.content}, #{one.password}, current_timestamp)")
	int insert(@Param("one") BoardDto one);
	
	@Update("update board set writer=#{one.writer}, content=#{one.content} where sid=#{one.sid}")
	int update(@Param("one") BoardDto one);
	
	@Delete("delete from board where sid=#{sid}")
	int delete(@Param("sid") String sid);
	
}
