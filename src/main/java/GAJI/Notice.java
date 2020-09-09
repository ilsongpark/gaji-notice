package GAJI;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Notice_table")
public class Notice {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long memberId;
    private String msg;

    @PostPersist
    public void onPostPersist(){
        Sent sent = new Sent();
        BeanUtils.copyProperties(this, sent);
        sent.publishAfterCommit();

        try {
            Thread.currentThread().sleep((long) (400 + Math.random() * 220));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }




}
