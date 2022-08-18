package Baxtishod.Book2.mapper;

import Baxtishod.Book2.dto.ResponseDto;

public class ResponseMapper {
    public static ResponseDto getResponseDto(Integer code, Boolean success, String message, Object o) {
        return ResponseDto.builder()
                .code(code)
                .success(success)
                .message(message)
                .data(o)
                .build();
    }
}


