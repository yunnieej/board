package project.board.controller;

import jdk.swing.interop.SwingInterOpUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.board.dto.BoardRequestDto;
import project.board.dto.BoardResponseDto;
import project.board.dto.BoardUpdateDto;
import project.board.entity.Board;
import project.board.service.BoardService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/save")
    public Long save(@RequestBody @Validated BoardRequestDto boardRequestDto){
//        log.info("get dto.title = {}", boardRequestDto.getTitle());
//        log.info("get dto.content = {}", boardRequestDto.getContent());
//        log.info("return dto.title = {}", boardRequestDto.getTitle());
//        log.info("return dto.content = {}", boardRequestDto.getContent());
        return boardService.save(boardRequestDto);
    }

    @GetMapping("/list")
    public List<BoardResponseDto> findAll(){
        return boardService.findAll();
    }

    @GetMapping("/{id}")
    public BoardResponseDto findById(@PathVariable("id") Long id){
        BoardResponseDto byId = boardService.findById(id);

        return byId;
    }

    @GetMapping("/w_list/{writer}")
    public List<BoardResponseDto> findByWriter(@PathVariable("writer") String writer){
        List<BoardResponseDto> byWriter = boardService.findByWriter(writer);
        return byWriter;
    }

    @GetMapping("/t_list/{title}")
    public List<BoardResponseDto> findByTitle(@PathVariable("title") String title){
        List<BoardResponseDto> byTitle = boardService.findByTitle(title);
        return byTitle;
    }

    @PutMapping("/update/{id}")
    public Long update(@RequestBody @Validated BoardUpdateDto boardUpdateDto, @PathVariable("id") Long id){
//        log.info("get dto.title = {}", boardUpdateDto.getTitle());
//        log.info("get dto.content = {}", boardUpdateDto.getContent());
//        log.info("return dto.title = {}", boardUpdateDto.getTitle());
//        log.info("return dto.content = {}", boardUpdateDto.getContent());

        return boardService.update(boardUpdateDto, id);
    }

    @GetMapping("/delete/{id}")
    public Long delete(@PathVariable("id") Long id){
        return boardService.delete(id);
    }
}
