package io.github.siaust.code_sharer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@SpringBootTest
class CodeSharerApplicationTests {

	@Autowired
	private CodeSharerApplication codeSharerApplication;

	@Test
	void contextLoads() throws Exception {
		assertThat(codeSharerApplication).isNotNull();
	}

}
