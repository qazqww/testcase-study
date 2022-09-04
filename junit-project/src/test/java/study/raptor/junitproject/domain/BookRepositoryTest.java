package study.raptor.junitproject.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        // given (데이터 준비)
        String title = "junit5";
        String author = "랩터코딩";
        Book book = Book.builder()
            .title(title)
            .author(author)
            .build();

        // when (테스트 실행)
        Book bookPersistence = bookRepository.save(book); // DB에 저장되어 영속화된 데이터를 리턴받음

        // then (검증)
        assertEquals(title, bookPersistence.getTitle());
        assertEquals(author, bookPersistence.getAuthor());
    }

    // 2. 책 목록 조회

    // 3. 책 하나 조회

    // 4. 책 수정

    // 5. 책 삭제

}
