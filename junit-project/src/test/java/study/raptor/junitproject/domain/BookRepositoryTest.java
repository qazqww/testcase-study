package study.raptor.junitproject.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void 데이터준비() {
        String title = "junit";
        String author = "겟인데어";
        Book book = Book.builder()
            .title(title)
            .author(author)
            .build();
        bookRepository.save(book);
    }
    
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
    } // 트랜잭션 종료 (저장된 데이터를 초기화함)

    // 2. 책 목록 조회
    @Test
    public void 책목록조회Test() {
        // given
        String title = "junit";
        String author = "겟인데어";

        // when
        List<Book> booksPS = bookRepository.findAll();

        System.out.println("============= 사이즈 : " + booksPS.size() + " =============");

        // then
        assertEquals(title, booksPS.get(0).getTitle());
        assertEquals(author, booksPS.get(0).getAuthor());
    }

    // 3. 책 하나 조회
    @Sql("classpath:db/tableInit.sql")
    @Test
    public void 책하나조회Test() {
        // given
        String title = "junit";
        String author = "겟인데어";

        // when
        Book bookPS = bookRepository.findById(1L).get();

        // then
        assertEquals(title, bookPS.getTitle());
        assertEquals(author, bookPS.getAuthor());
    }

    // 4. 책 삭제
    @Sql("classpath:db/tableInit.sql")
    @Test
    public void 책삭제Test() {
        // given
        Long id = 1L;

        // when
        bookRepository.deleteById(id);

        // then
        assertFalse(bookRepository.findById(id).isPresent());
    }


    // 5. 책 수정
    @Sql("classpath:db/tableInit.sql")
    @Test
    public void 책수정Test() {
        // given
        Long id = 1L;
        String title = "junit5";
        String author = "랩터코딩";
        Book book = new Book(id, title, author);

        // bookRepository.findAll().stream()
        //     .forEach(b -> {
        //         System.out.println(b.getId());
        //         System.out.println(b.getTitle());
        //         System.out.println(b.getAuthor());
        //         System.out.println("====================");
        //         });

        // when
        Book bookPS = bookRepository.save(book);
        // bookRepository.findAll().stream()
        //     .forEach(b -> {
        //         System.out.println(b.getId());
        //         System.out.println(b.getTitle());
        //         System.out.println(b.getAuthor());
        //         System.out.println("=========");
        //         });
            
        // then
        assertEquals(id, bookPS.getId());
        assertEquals(title, bookPS.getTitle());
        assertEquals(author, bookPS.getAuthor());

    }
}
