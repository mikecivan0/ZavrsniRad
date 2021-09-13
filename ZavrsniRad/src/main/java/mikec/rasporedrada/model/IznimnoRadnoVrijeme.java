/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.rasporedrada.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "iznimnaRadnaVremena")
public class IznimnoRadnoVrijeme {
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        
        @Column(nullable = false)
	private LocalDate datum;
        
        @Column(nullable = false)
	private LocalDate pocetak;
        
        @Column(nullable = false)
	private LocalDate kraj;
        
        @Column(nullable = false)
	private String napomena;
        
        @Column(nullable = false)
	private int pauza;
	
	public IznimnoRadnoVrijeme() {
		
	}
	
	public IznimnoRadnoVrijeme(LocalDate datum, LocalDate pocetak, LocalDate kraj, String napomena, int pauza) {
		this.datum = datum;
		this.pocetak = pocetak;
		this.kraj = kraj;
		this.napomena = napomena;
		this.pauza = pauza;
	}
	
	public LocalDate getDatum() {
		return datum;
	}
	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}
	public LocalDate getPocetak() {
		return pocetak;
	}
	public void setPocetak(LocalDate pocetak) {
		this.pocetak = pocetak;
	}
	public LocalDate getKraj() {
		return kraj;
	}
	public void setKraj(LocalDate kraj) {
		this.kraj = kraj;
	}
	public String getNapomena() {
		return napomena;
	}
	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}
	public int getPauza() {
		return pauza;
	}
	public void setPauza(int pauza) {
		this.pauza = pauza;
	}
	

}

