package jpabook.jpashop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class AttachIdea {    //부모글이 분류가 없다.  첨부파일별로 Entity만들기

    @Id@GeneratedValue
    @Column(name = "attachIdea_id")

    private Long id; /* 첨부파일 번호(PK) */

    @ManyToOne(fetch = FetchType.LAZY)
    private Idea idea; /* 부모글의 PK */

    private String attachIdeaFileName; /* 실제 저장된 파일명 */
    private String attachIdeaOriginalName; /* 사용자가 올린 원래 파일명 */
    private long attachIdeaFileSize; /* 파일 사이즈   1024,  1024*1024 */
    private String attachIdeaFancySize; /* 팬시 사이즈  : 1KB , 1M */
    private String attachIdeaContentType; /* 컨텐츠 타입 */
    private String attachIdeaPath; /* 저장 경로(board/2020) */
    private int attachIdeaDownHit; /* 다운로드 횟수 */
    private String attachIdeaDelYn; /* 삭제여부(스케쥴에 의해서 파일삭제처리) */




}
