package project.board.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import project.board.dto.BoardRequestDto;
import project.board.entity.Board;
import project.board.repository.BoardRepository;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BoardServiceTest {

    @Autowired BoardRepository boardRepository;
    @Autowired BoardService boardService;

    @Test
    public void 작성자_없는경우_예외() throws Exception{
        //Given
        BoardRequestDto boardRequestDto = new BoardRequestDto();
        boardRequestDto.setTitle("title1");
        boardRequestDto.setContent("content1");

        //when
        Long saveId = boardService.save(boardRequestDto);

        //then

    }

    @Test
    public void 제목_없는경우_예외() throws Exception{
        //Given

        //When

        //Then

    }




}