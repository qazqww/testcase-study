package study.raptor.junitproject.web.dto;

import lombok.Setter;
import study.raptor.junitproject.domain.Book;

@Setter
public class BookSaveReqDto {
    private String title;
    private String author;

    public Book toEntity() {
        return Book.builder()
            .title(title)
            .author(author)
            .build();
    }
}
