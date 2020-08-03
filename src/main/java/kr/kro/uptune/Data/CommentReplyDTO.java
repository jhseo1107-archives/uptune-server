package kr.kro.uptune.Data;

import java.sql.Timestamp;

public class CommentReplyDTO {
    private int CommentReplyId;
    private String CommentReplyContent;
    private Timestamp CommentReplyTimestamp;
    private int CommentReplyWriter;
    private int CommentReplyParentId;

    public int getCommentReplyId() {
        return CommentReplyId;
    }

    public void setCommentReplyId(int commentReplyId) {
        CommentReplyId = commentReplyId;
    }

    public String getCommentReplyContent() {
        return CommentReplyContent;
    }

    public void setCommentReplyContent(String commentReplyContent) {
        CommentReplyContent = commentReplyContent;
    }

    public Timestamp getCommentReplyTimestamp() {
        return CommentReplyTimestamp;
    }

    public void setCommentReplyTimestamp(Timestamp commentReplyTimestamp) {
        CommentReplyTimestamp = commentReplyTimestamp;
    }

    public int getCommentReplyWriter() {
        return CommentReplyWriter;
    }

    public void setCommentReplyWriter(int commentReplyWriter) {
        CommentReplyWriter = commentReplyWriter;
    }

    public int getCommentReplyParentId() {
        return CommentReplyParentId;
    }

    public void setCommentReplyParentId(int commentReplyParentId) {
        CommentReplyParentId = commentReplyParentId;
    }
}
