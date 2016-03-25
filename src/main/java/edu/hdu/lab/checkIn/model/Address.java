package edu.hdu.lab.checkIn.model;

public class Address {
    private Integer id;

    private String location;

    private String note1;

    private String note2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getNote1() {
        return note1;
    }

    public void setNote1(String note1) {
        this.note1 = note1 == null ? null : note1.trim();
    }

    public String getNote2() {
        return note2;
    }

    public void setNote2(String note2) {
        this.note2 = note2 == null ? null : note2.trim();
    }

	@Override
	public String toString() {
		return "Address [id=" + id + ", location=" + location + ", note1="
				+ note1 + ", note2=" + note2 + "]";
	}
    
    
}