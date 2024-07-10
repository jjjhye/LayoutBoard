package com.woori.layoutboard.Repository;

import com.woori.layoutboard.Entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity,Integer> {
    //Page<BoardEntity> find
}
