package com.woori.layoutboard.Service;

import com.woori.layoutboard.DTO.BoardDTO;
import com.woori.layoutboard.Entity.BoardEntity;
import com.woori.layoutboard.Repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;

    //삽입
    //insert메소드를 이용해서 boardDTO로 값을 입력받아 modelmapper를 entity로 변환 후
    //board레포시토리를 이용해서 저장
    public void insert(BoardDTO boardDTO){
        BoardEntity boardEntity = modelMapper.map(boardDTO, BoardEntity.class);
        boardRepository.save(boardEntity);
    }
    //수정
    //update메소드는 boardDTO로 값을 입력받아
    //boardDTO에 id(기본키)로 board레포시터리에서 조회 후
    //만약에 갑싱 전재하면 boardDTO를 modelmapper로 Entity로 변환하여 저장
    public void update(BoardDTO boardDTO){
        Optional<BoardEntity> temp = boardRepository.findById(boardDTO.getId());
        if(temp.isPresent()){
            BoardEntity boardEntity = modelMapper.map(boardDTO, BoardEntity.class);
            boardRepository.save(boardEntity);
        }
    }
    //삭제
    //delete메소드는 id(기본키)를 받아서
    //boardRepository로 id로 삭제
    public void delete(Integer id){
        boardRepository.deleteById(id);
    }
    //개별조회
    //read메소드는 id(기본키)를 받아서
    //boardRepository를 이용해서 id로 조회하고, boardEntity저장
    //modelmapper를 이용해서 Entity를 DTO로 변환
    //변환한 DTO를 전달
    public BoardDTO read(Integer id){
        Optional<BoardEntity> boardEntity = boardRepository.findById(id);
        BoardDTO boardDTO = modelMapper.map(boardEntity, BoardDTO.class);

        return boardDTO;
    }
    //조회
    //list는 페이지 번호를 받아서
    //id를 기준으로 내림차순으로 10개씩 데이터를 그룹화해서 조회
    //조회한 Entity를 Modelmapper를 이용해서 DTO로 변환
    //DTO(페이지정보)를 전달
    public Page<BoardDTO> list(Pageable page){
        //필요한 값은 변수에 저장해서 변수로 이용
        int currentPage = page.getPageNumber()-1;
        int pageLimit = 10;
        Pageable pageable = PageRequest.of(currentPage, pageLimit,
                Sort.by(Sort.Direction.DESC,"id"));
        Page<BoardEntity> boardEntities = boardRepository.findAll(pageable);

        //->람다식
        Page<BoardDTO> boardDTOS = boardEntities.map(
                data->modelMapper.map(data,BoardDTO.class));
        return boardDTOS;
    }
}
