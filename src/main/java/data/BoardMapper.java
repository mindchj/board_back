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
	
	@Insert("insert into board (writer, content, date) values (#{one.writer}, #{one.content}, current_timestamp)")
	int insert(@Param("one") BoardDto one);
	
	@Update("update board set writer=#{one.writer}, content=#{one.content} where sid=#{one.sid}")
	int update(@Param("one") BoardDto one);
	
	@Delete("delete from board where sid=#{sid}")
	int delete(@Param("sid") String sid);
	
}
