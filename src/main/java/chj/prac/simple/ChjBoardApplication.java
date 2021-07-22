package chj.prac.simple;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("controller")
@MapperScan("data")
public class ChjBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChjBoardApplication.class, args);
	}

}
