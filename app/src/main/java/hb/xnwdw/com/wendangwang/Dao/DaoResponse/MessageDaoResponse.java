package hb.xnwdw.com.wendangwang.Dao.DaoResponse;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/9/14.
 */
@Entity
public class MessageDaoResponse {
    @Id
    private long id;
    private String messageCount;
    public String getMessageCount() {
        return this.messageCount;
    }
    public void setMessageCount(String messageCount) {
        this.messageCount = messageCount;
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    @Generated(hash = 634038651)
    public MessageDaoResponse(long id, String messageCount) {
        this.id = id;
        this.messageCount = messageCount;
    }
    @Generated(hash = 400409758)
    public MessageDaoResponse() {
    }



}
