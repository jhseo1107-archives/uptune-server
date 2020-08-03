package kr.kro.uptune.Data;

import java.sql.Timestamp;
import java.util.ArrayList;

public class CommentDTO {
    private int CommentId;
    private int CommentWriter;
    private Timestamp CommentTimeStamp;
    private String CommentContent;
    private int CommentParentId;
    private int CommentParentType;
    private ArrayList<CommentReplyDTO> CommentReply;



    public int getCommentId() {
        return CommentId;
    }

    public void setCommentId(int commentId) {
        CommentId = commentId;
    }

    public int getCommentWriter() {
        return CommentWriter;
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

    public ArrayList<CommentReplyDTO> getCommentReply() {
        return CommentReply;
    }

    public void setCommentReply(ArrayList<CommentReplyDTO> commentReply) {
        CommentReply = commentReply;
    }

    public int getCommentParentId() {
        return CommentParentId;
    }

    public void setCommentParentId(int commentParentId) {
        CommentParentId = commentParentId;
    }

    public int getCommentParentType() {
        return CommentParentType;
    }

    public void setCommentParentType(int commentParentType) {
        CommentParentType = commentParentType;
    }
}
