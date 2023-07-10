package com.yoon.spboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yoon.spboard.entity.BbsEntity;

public interface BbsRepository extends JpaRepository<BbsEntity, Long> {
    // update spb_bbs set bbs_hits = bbs_hits + 1 where num = ?
    @Modifying
    // @Query(value="update spb_bbs b set b.bbs_hits = b.bbs_hits + 1 where b.num =
    // :num", nativeQuery = true)
    @Query(value = "update BbsEntity b set b.bbsHits = b.bbsHits + 1 where b.num = :num")
    void updateHits(@Param("num") Long num);
}
