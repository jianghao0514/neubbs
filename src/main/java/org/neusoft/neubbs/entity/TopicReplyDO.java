package org.neusoft.neubbs.entity;

import java.util.Date;

/**
 * forum_topic_reply 领域对象
 *
 * @author Suvan
 */
public class TopicReplyDO {

    private Integer id;
    private Integer userid;
    private Integer topicid;
    private String content;
    private Integer agree;
    private Integer oppose;
    private Date createtime;

    /**
     * Getter
     */
    public Integer getId() {
        return id;
    }
    public Integer getUserid() {
        return userid;
    }
    public Integer getTopicid() {
        return topicid;
    }
    public String getContent() {
        return content;
    }
    public Integer getAgree() {
        return agree;
    }
    public Integer getOppose() {
        return oppose;
    }
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * Setter
     */
    public void setId(Integer id) {
        this.id = id;
    }
    public void setUserid(Integer userid) {
        this.userid = userid;
    }
    public void setTopicid(Integer topicid) {
        this.topicid = topicid;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setAgree(Integer agree) {
        this.agree = agree;
    }
    public void setOppose(Integer oppose) {
        this.oppose = oppose;
    }
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "TopicReplyDO{"
                + "id=" + id
                + ", userid=" + userid
                + ", topicid=" + topicid
                + ", content='" + content + '\''
                + ", agree='" + agree + '\''
                + ", oppose='" + oppose + '\''
                + ", createtime=" + createtime
                + '}';
    }
}
