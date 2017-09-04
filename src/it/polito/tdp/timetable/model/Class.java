package it.polito.tdp.timetable.model;

public class Class {
	
	private String classID;
	private int grade;
	private String courseID;
	
	public Class(String classID, int grade, String courseID) {
		super();
		this.classID = classID;
		this.grade = grade;
		this.courseID = courseID;
	}

	public String getClassID() {
		return classID;
	}

	public void setClassID(String classID) {
		this.classID = classID;
	}
	
	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}	

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((classID == null) ? 0 : classID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Class other = (Class) obj;
		if (classID == null) {
			if (other.classID != null)
				return false;
		} else if (!classID.equals(other.classID))
			return false;
		return true;
	}
	
	
	

}