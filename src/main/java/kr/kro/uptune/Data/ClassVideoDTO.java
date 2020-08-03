package kr.kro.uptune.Data;

import java.sql.Timestamp;
import java.util.ArrayList;

public class ClassVideoDTO {
    private int ClassVideoId;
    private String ClassVideoName;
    private int ClassVideoWriter;
    private Timestamp ClassVideoTimeStamp;
    private int ClassVideoParentId;

    public int getClassVideoParentId() {
        return ClassVideoParentId;
    }

    public void setClassVideoParentId(int classVideoParentId) {
        ClassVideoParentId = classVideoParentId;
    }

    private String ClassVideoLink;

    public int getClassVideoId() {
        return ClassVideoId;
    }

    public void setClassVideoId(int classVideoId) {
        ClassVideoId = classVideoId;
    }


    public int getClassVideoWriter() {
        return ClassVideoWriter;
    }

    public void setClassVideoWriter(int classVideoWriter) {
        ClassVideoWriter = classVideoWriter;
    }

    public String getClassVideoName() {
        return ClassVideoName;
    }

    public void setClassVideoName(String classVideoName) {
        ClassVideoName = classVideoName;
    }

    public Timestamp getClassVideoTimeStamp() {
        return ClassVideoTimeStamp;
    }

    public void setClassVideoTimeStamp(Timestamp classVideoTimeStamp) {
        ClassVideoTimeStamp = classVideoTimeStamp;
    }

    public String getClassVideoLink() {
        return ClassVideoLink;
    }

    public void setClassVideoLink(String classVideoLink) {
        ClassVideoLink = classVideoLink;
    }

    public ArrayList<CommentDTO> getClassVideoComment() {
        return ClassVideoComment;
    }

    public void setClassVideoComment(ArrayList<CommentDTO> classVideoComment) {
        ClassVideoComment = classVideoComment;
    }

    private ArrayList<CommentDTO> ClassVideoComment;
}
