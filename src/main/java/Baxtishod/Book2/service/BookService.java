package Baxtishod.Book2.service;

import Baxtishod.Book2.dto.BookDto;
import Baxtishod.Book2.dto.ResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface BookService {
    ResponseDto getBookById(Integer id);
    ResponseDto getAllBook();
    ResponseDto updateBook(BookDto bookDto);
    ResponseDto deleteBook(Integer id);

    ResponseDto addBook(BookDto bookDto);

}
