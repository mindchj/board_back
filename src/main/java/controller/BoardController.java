package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import data.BoardDto;
import data.BoardMapper;
import data.MemberDto;
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
		
		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		String ip = req.getHeader("X-FORWARDED-FOR");
		if (ip == null)
			ip = req.getRemoteAddr();
		System.out.println(ip);
		mapper.ip(ip);
		
		return list;
	}
	
	@GetMapping("/idcheck/{id}")
	public int idcheck(@PathVariable String id)
	{
		return mapper.idcheck(id);
	}
	
	@PostMapping("/login")
	public MemberDto login(@RequestBody MemberDto clientDto)
	{
		MemberDto serverDto = mapper.selectMember(clientDto.getId());
		if(serverDto.getPassword().equals(clientDto.getPassword()))
			return serverDto;
		else
			return null;
	}
	
	@PostMapping("/signup")
	public void insertmember(@RequestBody MemberDto dto)
	{
		mapper.insertMember(dto);
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
