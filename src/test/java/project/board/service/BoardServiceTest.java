package project.board.service;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import project.board.dto.BoardRequestDto;
import project.board.dto.BoardResponseDto;
import project.board.dto.BoardUpdateDto;
import project.board.entity.Board;
import project.board.repository.BoardRepository;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import javax.transaction.Transactional;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;


//@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BoardServiceTest {

    @Autowired BoardRepository boardRepository;
    @Autowired BoardService boardService;

    /**
     * 조회
     * @throws Exception
     */

    @Test
    public void 조회_정보_확인() throws Exception{
        BoardRequestDto boardRequestDto = new BoardRequestDto("33", "33" ,"33");
        Long savedId = boardService.save(boardRequestDto);
        Long findId = boardService.findById(savedId).getId();

        assertThat(savedId).isEqualTo(findId);
    }

    @Test
    public void 조회_게시판_없을경우_예외() {
        if(boardRepository.findById(2L).isEmpty()){
            throw new NullPointerException("존재하지 않는 게시판입니다.");
        }
    }

    @Test
    public void 작성자_없을경우_예외(){
        if(boardService.findByWriter("gg").isEmpty()){
            throw new NullPointerException("해당 작성자의 게시글이 없습니다.");
        }
    }

    @Test
    public void 제목_없을경우_예외(){
        if(boardService.findByTitle("hi").isEmpty()){
            throw new NullPointerException("해당 제목에 맞는 게시글이 없습니다.");
        }
    }

    /**
     * 저장
     * @throws Exception
     */
    @Test
    public void 저장_정보_확인() throws Exception{
        //Given
        BoardRequestDto boardRequestDto = new BoardRequestDto("writer", "title", "content");

        //when
        Long savedId = boardService.save(boardRequestDto);
        Board board = boardRepository.findById(savedId).get();
        //then
        assertThat(savedId).isEqualTo(board.getId());
    }

    /***
    @Test
    public void 저장시_예외처리() throws Exception{
        BoardRequestDto boardRequestDto = new BoardRequestDto("22", "hi", "hello!!");
        String contents = boardRequestDto.getContent(); // 내용 추출
        System.out.println(contents);
        if (boardRequestDto.getWriter().isBlank() || boardRequestDto.getTitle().isBlank()){
            throw new IllegalStateException("필수 입력값 (작성자, 제목)이 없습니다.");
        }
//        else if(boardRequestDto.getContent().matches("[0-9a-zA-Z가-힣]*")){
//        else if(contents.matches("[^a-zA-Z0-9\\\\s]")){
//            throw new IllegalStateException("내용에는 특수문자가 들어갈 수 없습니다.");
//        }
//        boardService.save(boardRequestDto);
    }

    //왜 성공 ..?
    @Test
    public void 저장시_특수문자_없는경우() throws Exception{
        BoardRequestDto boardRequestDto = new BoardRequestDto("zz", "hi", "hello!!!");

//        if (boardRequestDto.getContent().matches("[0-9a-zA-Z가-힣]") == true){
        if(Pattern.matches("[0-9a-zA-Z가-힣]", boardRequestDto.getContent())){
            throw new IllegalStateException("내용에는 특수문자가 들어갈 수 없습니다.");
        }
    }
*/

    @Test
    public void 저장_예외() throws Exception{
        BoardRequestDto boardRequestDto = new BoardRequestDto("xx", "xx", "xx!!");
        boardService.save(boardRequestDto);
    }

    @Test
    void 패턴_테스트_false(){
        String 특수문자제외 = "[^a-zA-Z]";
        String 문자 = "!@#";
        System.out.println(문자.replaceAll(특수문자제외, ""));


        assertFalse(문자.matches(특수문자제외));
    }

    @Test
    void 패턴_테스트_true(){
        String 특수문자제외 = "[^ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9]";
        String 문자 = "!@#$";
        System.out.println(문자.replaceAll(특수문자제외, ""));
        assertTrue(문자.matches(특수문자제외));
    }
    /**
     * 수정
     * @throws Exception
     */
    @Test
    public void 수정_정보_확인() throws Exception{
        BoardUpdateDto boardUpdateDto = new BoardUpdateDto();
        boardUpdateDto.setTitle("수정제목");
        boardUpdateDto.setContent("수정내용");

        Long updateId = boardService.update(boardUpdateDto, 4L);
        String content = boardRepository.findById(updateId).get().getContent();
        assertThat(boardUpdateDto.getContent()).isEqualTo(content);
    }

    @Test
    public void 수정_순번_없을경우_예외() throws Exception{
        BoardUpdateDto boardUpdateDto = new BoardUpdateDto();
        if(boardRepository.findById(2L).isEmpty()){
            throw new NoSuchElementException("해당 순번 x - 수정");
        }
        boardService.update(boardUpdateDto, 2L);

    }

    /**
     * 삭제
     * @throws Exception
     */
    @Test
    public void 삭제_정보_확인() throws Exception{
        int size1 = boardService.findAll().size();
        Long deletedId = boardService.delete(13L);
        int size2 = boardService.findAll().size();

        assertThat(deletedId).isEqualTo(13L);
        assertThat(size1).isEqualTo(size2+1);
    }


//    @Test
//    public void 삭제_순번_없을경우_예외() throws Exception{
//            Long deletedId = boardService.delete(2L);
//        //assertEquals("해당 순번이 존재하지 않습니다.", exception.getMessage());
//    }


    @Test
    public void 삭제_순번_없을경우_예외() throws Exception{
        if(boardRepository.findById(2L).isEmpty()){
            throw new NoSuchElementException("해당 순번 x - 삭제");
        }
        boardService.delete(2L);
    }

}