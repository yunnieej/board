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

//@RunWith(SpringRunner.class)
//@SpringBootTest
@Transactional
public class BoardServiceTest {

    @Autowired BoardRepository boardRepository;
    @Autowired BoardService boardService;

    @Test(expected = IllegalStateException.class)
    public void 작성자_없는경우_예외() throws Exception{
        //Given
        BoardRequestDto boardRequestDto = new BoardRequestDto();
        boardRequestDto.setTitle("title1");
        boardRequestDto.setContent("content1");

        //when
        boardService.save(boardRequestDto);

        //then
        fail("예외가 발생해야한다.");
    }

    @Test
    public void 제목_없는경우_예외() throws Exception {
        //Given
        BoardRequestDto boardRequestDto = new BoardRequestDto();
        boardRequestDto.setWriter("writer2");
        boardRequestDto.setContent("content2");

        //when
        boardService.save(boardRequestDto);

        //then
        fail("예외가 발생해야한다.");
    }

    @Test
    public void 조회할_게시판_없는경우_예외() throws Exception{

    }

    @Test
    public void 조회_응답_확인(){

    }

    /**
     * 게시판 저장 테스트 코드
     */

    @Test
    public void 저장_응답값(){
        //Given
        BoardRequestDto boardRequestDto = new BoardRequestDto();
        boardRequestDto.setWriter("test_writer");
        boardRequestDto.setTitle("test_title");
        boardRequestDto.setContent("test_content");

        //When
        Long saveId = boardService.save(boardRequestDto);

        //Then
        assertEquals(boardRequestDto, boardRepository.findById(saveId));
    }










}