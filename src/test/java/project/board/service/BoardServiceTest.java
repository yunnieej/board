package project.board.service;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import project.board.dto.BoardRequestDto;
import project.board.dto.BoardResponseDto;
import project.board.dto.BoardUpdateDto;
import project.board.repository.BoardRepository;

import javax.transaction.Transactional;




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

    @Test //성공
    public void 조회_정보_확인() throws Exception{
        BoardRequestDto boardRequestDto = new BoardRequestDto("33", "33" ,"33");
        Long savedId = boardService.save(boardRequestDto);
        Long findId = boardService.findById(savedId).getId();

        assertThat(savedId).isEqualTo(findId);
    }

    @Test //실패
    public void 작성자_없을경우_예외(){
        if(boardService.findByWriter("gg").isEmpty()){
            throw new NullPointerException("해당 작성자의 게시글이 없습니다.");
        }
    }

    @Test //실패
    public void 제목_없을경우_예외(){
        if(boardService.findByTitle("제목없음").isEmpty()){
            throw new NullPointerException("해당 제목에 맞는 게시글이 없습니다.");
        }
    }

    @Test //실패
    public void 조회_게시판_없을경우_예외() {
        boardService.findById(2L);
//        if(board.findById(2L).isEmpty()){
//            throw new NullPointerException("존재하지 않는 게시판입니다.");
//        }
    }


    /**
     * 저장
     * @throws Exception
     */

    @Test //성공
    public void 저장_정보_확인() throws Exception{
        //Given
        BoardRequestDto boardRequestDto = new BoardRequestDto("writer", "title", "content");

        //when
        Long savedId = boardService.save(boardRequestDto);
//        Board board = boardRepository.findById(savedId).get();
        BoardResponseDto byId = boardService.findById(savedId);
        //then
        assertThat(savedId).isEqualTo(byId.getId());
    }

    String 특수문자제외 = "[ ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9]+";

    @Test //실패
    public void 저장시_필수항목_없는경우() throws Exception{
        BoardRequestDto boardRequestDto = new BoardRequestDto("", "hi", "hello");
        String contents = boardRequestDto.getContent(); // 내용 추출
        System.out.println(contents);
        if (boardRequestDto.getWriter().isBlank() || boardRequestDto.getTitle().isBlank()){
            throw new IllegalStateException("필수 입력값 (작성자, 제목)이 없습니다.");
        }
    }


    @Test //실패
    public void 저장시_특수문자_없는경우() throws Exception{
        BoardRequestDto boardRequestDto = new BoardRequestDto("zz", "hi", "안녕 하새용!!");

        if (boardRequestDto.getContent().matches(특수문자제외) == false){
            throw new IllegalStateException("내용에는 특수문자가 들어갈 수 없습니다.");
        }
    }

    /**
     * 수정
     * @throws Exception
     */
    @Test //성공
    public void 수정_정보_확인() throws Exception{
        BoardUpdateDto boardUpdateDto = new BoardUpdateDto();
        boardUpdateDto.setTitle("수정제목입니다");
        boardUpdateDto.setContent("수정내용입니다");

        Long updateId = boardService.update(boardUpdateDto, 4L);
        BoardResponseDto byId = boardService.findById(updateId);
        assertThat(boardUpdateDto.getContent()).isEqualTo(byId.getContent());

    }

    @Test //실패
    public void 수정_순번_없을경우_예외() throws Exception{
        BoardUpdateDto boardUpdateDto = new BoardUpdateDto();
        BoardResponseDto byId = boardService.findById(2L);

//        if(boardRepository.findById(2L).isEmpty()){
//            throw new NoSuchElementException("해당 순번 x - 수정");
//        }
        boardService.update(boardUpdateDto, 2L);

    }

    /**
     * 삭제
     * @throws Exception
     */
    @Test // 성공
    public void 삭제_정보_확인() throws Exception{
        int size1 = boardService.findAll().size();
        Long deletedId = boardService.delete(13L);
        int size2 = boardService.findAll().size();

        assertThat(deletedId).isEqualTo(13L);
        assertThat(size1).isEqualTo(size2+1);
    }

    @Test //실패
    public void 삭제_순번_없을경우_예외() throws Exception{
        BoardResponseDto byId = boardService.findById(2L);
//        if(boardRepository.findById(2L).isEmpty()){
//            throw new NoSuchElementException("해당 순번 x - 삭제");
//        }
        boardService.delete(2L);
    }
}
