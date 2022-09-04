package study.raptor.junitproject.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    
    // 1. 책 등록
    @Test
    public void 책등록Test() {
        System.out.println("책등록Test 실행");
    }

    // 2. 책 목록 조회

    // 3. 책 하나 조회

    // 4. 책 수정

    // 5. 책 삭제

}
