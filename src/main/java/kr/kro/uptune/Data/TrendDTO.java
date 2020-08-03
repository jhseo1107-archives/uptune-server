package kr.kro.uptune.Data;

import java.sql.Timestamp;
import java.util.ArrayList;

public class TrendDTO {
    private int trendId;
    private String trendName;
    private Timestamp trendTimeStamp;
    private int trendWriter;
    private int trendLikes;
    private String trendFileExtension;
    private ArrayList<CommentDTO> trendComments;

    public int getTrendId() {
        return trendId;
    }

    public void setTrendId(int trendId) {
        this.trendId = trendId;
    }

    public String getTrendName() {
        return trendName;
    }

    public void setTrendName(String trendName) {
        this.trendName = trendName;
    }

    public Timestamp getTrendTimeStamp() {
        return trendTimeStamp;
    }

    public void setTrendTimeStamp(Timestamp trendTimeStamp) {
        this.trendTimeStamp = trendTimeStamp;
    }

    public int getTrendWriter() {
        return trendWriter;
    }

    public void setTrendWriter(int trendWriter) {
        this.trendWriter = trendWriter;
    }

    public int getTrendLikes() {
        return trendLikes;
    }

    public void setTrendLikes(int trendLikes) {
        this.trendLikes = trendLikes;
    }

    public String getTrendFileExtension() {
        return trendFileExtension;
    }

    public void setTrendFileExtension(String trendFileExtension) {
        this.trendFileExtension = trendFileExtension;
    }

    public ArrayList<CommentDTO> getTrendComments() {
        return trendComments;
    }

    public void setTrendComments(ArrayList<CommentDTO> trendComments) {
        this.trendComments = trendComments;
    }
}
