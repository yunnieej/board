package project.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.board.dto.BoardRequestDto;
import project.board.service.BoardService;

@RequiredArgsConstructor
@RestController
@Slf4j
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/save")
    public Long save(@RequestBody @Validated BoardRequestDto boardRequestDto){
        return boardService.save(boardRequestDto);
    }
}
