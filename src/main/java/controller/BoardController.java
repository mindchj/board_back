package controller;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import data.BoardDto;
import data.BoardMapper;
import data.ReturnDto;

@CrossOrigin
@RestController
@RequestMapping("/")
public class BoardController {
	@Autowired
	BoardMapper mapper;
	
	
	@GetMapping("/board")
	public List<BoardDto> selectboard()
	{
		List<BoardDto> list = mapper.select();
		System.out.println(list);
		return list;
	}
	
	@PostMapping("/insert")
	public ReturnDto insertboard(@RequestBody BoardDto dto)
	{
		System.out.println(dto);
		int k = mapper.insert(dto);
		System.out.println(k);
		ReturnDto rdto = new ReturnDto();
		rdto.setData(dto);
		rdto.setStatus("success");
		return rdto;
	}
	
	@PutMapping("/update")
	public void updateboard(@RequestBody BoardDto dto)
	{
		mapper.update(dto);
	}
	
	@DeleteMapping("/delete/{sid}")
	public void deleteboard(@PathVariable String sid)
	{
		mapper.delete(sid);
	}

}
