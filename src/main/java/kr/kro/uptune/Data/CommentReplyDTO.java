package kr.kro.uptune.Data;

import java.sql.Timestamp;

public class CommentReplyDTO {
    private int CommentId;
    private int CommentWriter;
    private Timestamp CommentTimeStamp;
    private String CommentContent;

    public int getCommentId() {
        return CommentId;
    }

    public void setCommentId(int commentId) {
        CommentId = commentId;
    }

    public void setCommentWriter(int commentWriter) {
        CommentWriter = commentWriter;
    }

    public Timestamp getCommentTimeStamp() {
        return CommentTimeStamp;
    }

    public void setCommentTimeStamp(Timestamp commentTimeStamp) {
        CommentTimeStamp = commentTimeStamp;
    }

    public String getCommentContent() {
        return CommentContent;
    }

    public void setCommentContent(String commentContent) {
        CommentContent = commentContent;
    }

}
