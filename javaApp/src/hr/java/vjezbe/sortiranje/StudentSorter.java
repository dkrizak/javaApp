package hr.java.vjezbe.sortiranje;

import java.util.Comparator;

import hr.java.vjezbe.entitet.Student;

public class StudentSorter implements Comparator<Student> {
	
	@Override
	public int compare (Student st1, Student st2) {
		
		if (st1.getPrezime().compareTo(st2.getPrezime()) == 0) {
			return st1.getIme().compareTo(st2.getIme());
		}
		else return st1.getPrezime().compareTo(st2.getPrezime());
	}

}
