package kr.kro.uptune.Data;

import java.sql.Timestamp;
import java.util.ArrayList;

public class ClassVideoDTO {
    private int ClassVideoId;
    private String ClassVideoName;
    private int ClassVideoWriter;
    private Timestamp ClassVideoTimeStamp;
    private String ClassVideoLink;

    public int getClassVideoId() {
        return ClassVideoId;
    }

    public void setClassVideoId(int classVideoId) {
        ClassVideoId = classVideoId;
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

    public ArrayList<CommentDTO> getClassVideoReply() {
        return ClassVideoReply;
    }

    public void setClassVideoReply(ArrayList<CommentDTO> classVideoReply) {
        ClassVideoReply = classVideoReply;
    }

    private ArrayList<CommentDTO> ClassVideoReply;
}
