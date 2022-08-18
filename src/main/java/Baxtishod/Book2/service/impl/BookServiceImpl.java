package Baxtishod.Book2.service.impl;

import Baxtishod.Book2.dto.BookDto;
import Baxtishod.Book2.dto.ResponseDto;
import Baxtishod.Book2.entity.Book;
import Baxtishod.Book2.mapper.BookMapper;
import Baxtishod.Book2.mapper.ResponseMapper;
import Baxtishod.Book2.repository.BookRepository;
import Baxtishod.Book2.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public ResponseDto getBookById(Integer id) {
        Optional<Book> optional = bookRepository.findById(id);
        if(optional.isPresent()){
            Book book = optional.get();
            BookDto bookDto = bookMapper.convertToDto(book);
            return ResponseMapper.getResponseDto(200,true,"data is found",bookDto);
        }
        return ResponseMapper.getResponseDto(404,false,"data is not found",null);
    }

    @Override
    public ResponseDto getAllBook() {
        List<Book> books = bookRepository.findAll();
        List<BookDto> list = books.stream().map(bookMapper::convertToDto).toList();

        return ResponseMapper.getResponseDto(200,true,"data is found",list);
    }

    @Override
    public ResponseDto updateBook(BookDto bookDto) {
        Optional<Book> optional = bookRepository.findById(bookDto.getId());
        if(optional.isPresent()){
            Book book = optional.get();
            BookDto demo = bookDto.builder()
                    .id(bookDto.getId()!=null? bookDto.getId() : book.getId())
                    .name(bookDto.getName()!=null? bookDto.getName() : book.getName())
                    .author(bookDto.getAuthor()!=null? bookDto.getAuthor() : book.getAuthor())
                    .build();
            return ResponseMapper.getResponseDto(200,true,"data is found",demo);

        }
        return ResponseMapper.getResponseDto(404,false,"data is not found",null);

    }

    @Override
    public ResponseDto deleteBook(Integer id) {
        if(bookRepository.existsById(id)){
            Book book = bookRepository.findById(id).get();
            bookRepository.delete(book);
            return ResponseMapper.getResponseDto(200,true,"data is found",bookMapper.convertToDto(book));
        }
        return ResponseMapper.getResponseDto(404,false,"data is not found",null);
    }

    @Override
    public ResponseDto addBook(BookDto bookDto) {
        if(!bookRepository.existsById(bookDto.getId())){
            Book book = bookRepository.save(bookMapper.convertToEntity(bookDto));
            return ResponseMapper.getResponseDto(200,true,"Successully saved!",bookMapper.convertToDto(book));

        }
        return ResponseMapper.getResponseDto(404,false,"Data is alread exists",null);

    }
}
