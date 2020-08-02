package kr.kro.uptune.Data;

import java.sql.Timestamp;
import java.util.ArrayList;

public class CommentDTO {
    private int CommentId;

    public void setCommentWriter(int commentWriter) {
        CommentWriter = commentWriter;
    }

    private int CommentWriter;
    private Timestamp CommentTimeStamp;
    private String CommentContent;
    private ArrayList<CommentReplyDTO> CommentReply;


    public int getCommentId() {
        return CommentId;
    }

    public void setCommentId(int commentId) {
        CommentId = commentId;
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

    public ArrayList<CommentReplyDTO> getCommentReply() {
        return CommentReply;
    }

    public void setCommentReply(ArrayList<CommentReplyDTO> commentReply) {
        CommentReply = commentReply;
    }
}
