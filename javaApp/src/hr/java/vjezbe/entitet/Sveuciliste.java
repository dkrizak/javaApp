package hr.java.vjezbe.entitet;

import java.util.ArrayList;
import java.util.List;

public class Sveuciliste<T> extends ObrazovnaUstanova{
	
	private static final long serialVersionUID = 6892306217444221502L;
	private List<T> ustanove;
	
	public Sveuciliste() {
		//super(nazivUstanove, predmeti, profesori, studenti, ispiti);
		super((long) 1, null, null, null, null, null);
		this.ustanove = new ArrayList<>();
	}
	
	public void dodajObrazovnuUstanovu (T ustanova) {
		this.ustanove.add(ustanova);
	}
	
	public T dohvatiObrazovnuUstanovu (int t) {
		return ustanove.get(t);
	}
	
	public List<T> dohvatiUstanove () {
		return ustanove;
	}

	@Override
	public Student odrediNajuspjesnijegStudentaNaGodini(int godina) {
		return null;
	}
	
	
}
