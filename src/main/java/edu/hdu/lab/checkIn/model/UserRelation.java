package edu.hdu.lab.checkIn.model;

public class UserRelation {
    private Integer teacherId;

    private Integer studentId;

    public UserRelation() {
		super();
	}

	public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}