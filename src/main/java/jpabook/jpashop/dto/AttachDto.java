package jpabook.jpashop.dto;

import jpabook.jpashop.entity.Idea;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachDto {

    private Long id; /* 첨부파일 번호(PK) */
    private Long attachParentNo; /* 부모글의 PK */
    private String attachCategory; /* 상위글 분류(IDEA,Memmber등) */
    private String attachFileName; /* 실제 저장된 파일명 */
    private String attachOriginalName; /* 사용자가 올린 원래 파일명 */
    private long attachFileSize; /* 파일 사이즈   1024,  1024*1024 */
    private String attachFancySize; /* 팬시 사이즈  : 1KB , 1M */
    private String attachContentType; /* 컨텐츠 타입 */
    private String attachPath; /* 저장 경로(board/2020) */
    private int attachDownHit; /* 다운로드 횟수 */
    private String attachDelYn; /* 삭제여부(스케쥴에 의해서 파일삭제처리) */


}
