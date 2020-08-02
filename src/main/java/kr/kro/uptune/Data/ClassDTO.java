package kr.kro.uptune.Data;

import java.sql.Timestamp;
import java.util.ArrayList;

public class ClassDTO {
    private int ClassId;
    private String ClassName;
    private int ClassWriter;
    private Timestamp ClassTimeStamp;
    private ArrayList<ClassVideoDTO> ClassVideo;

    public int getClassId() {
        return ClassId;
    }

    public void setClassId(int classId) {
        ClassId = classId;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public int getClassWriter() {
        return ClassWriter;
    }

    public void setClassWriter(int classWriter) {
        ClassWriter = classWriter;
    }

    public Timestamp getClassTimeStamp() {
        return ClassTimeStamp;
    }

    public void setClassTimeStamp(Timestamp classTimeStamp) {
        ClassTimeStamp = classTimeStamp;
    }

    public ArrayList<ClassVideoDTO> getClassVideo() {
        return ClassVideo;
    }

    public void setClassVideo(ArrayList<ClassVideoDTO> classVideo) {
        ClassVideo = classVideo;
    }
}
