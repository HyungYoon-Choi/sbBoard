package com.yoon.spboard.dto;

import java.time.LocalDateTime;

import com.yoon.spboard.entity.BbsEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor // BbsDto(){}
public class BbsDto {
    private Long num;
    private String bbsWriter;
    private String bbsPass;
    private String bbsTitle;
    private String bbsContents;
    private int bbsHits;
    private LocalDateTime bbsCreatedTime;
    private LocalDateTime bbsUpdatedTime;

    public BbsDto(Long num, String bbsWriter, String bbsTitle, int bbsHits, LocalDateTime bbsCreatedTime) {
        this.num = num;
        this.bbsWriter = bbsWriter;
        this.bbsTitle = bbsTitle;
        this.bbsHits = bbsHits;
        this.bbsCreatedTime = bbsCreatedTime;
    }

    public static BbsDto toSaveDto(BbsEntity bbsEntity) {

        BbsDto bbsDto = new BbsDto();
        bbsDto.setNum(bbsEntity.getNum());
        bbsDto.setBbsWriter(bbsEntity.getBbsWriter());
        bbsDto.setBbsPass(bbsEntity.getBbsPass());
        bbsDto.setBbsTitle(bbsEntity.getBbsTitle());
        bbsDto.setBbsContents(bbsEntity.getBbsContents());
        bbsDto.setBbsHits(bbsEntity.getBbsHits());
        bbsDto.setBbsCreatedTime(bbsEntity.getBbsCreatedTime());
        bbsDto.setBbsUpdatedTime(bbsEntity.getBbsUpdatedTime());
        return bbsDto;
    }
}
