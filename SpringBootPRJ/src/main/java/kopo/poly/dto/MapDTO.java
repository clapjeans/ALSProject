package kopo.poly.dto;

import lombok.Data;

@Data
public class MapDTO {
    private String gu_place; //관리구역 대상지명
    private String gu_name; //시군구명
    private String date; //데이터 기준일자
    private String gu_num; //관리구역명
    private String place_ty; //배출장소유형
    private String place; //배출장소
    private String life_way; //생활쓰레기 배출방법
    private String food_way; //음식물쓰레기 배출방법
    private String rec_way; //재활용쓰레기 배출방법
    private String life_dy; //생활쓰레기 배출요일
    private String food_dy; //음식물쓰레기 배출요일
    private String rec_dy; //재활용쓰레기 배출요일
    private String life_tm1; //생활쓰레기 배출시간
    private String life_tm2; //생활쓰레기 배출 종료시간
    private String food_tm1; //음식물쓰레기 배출 시작시간
    private String food_tm2; //음식물 쓰레기 배출 종료시간
    private String rec_tm1; //재활용쓰레기 배출 종료시간
    private String rec_tm2; //재활용쓰레기 배출 종료시간
    private String dayoff; //휴무일 미수거일
    private String manage;  // 관리부서명
    private String phone; //관리부서전화


}
