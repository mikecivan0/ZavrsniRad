# ZavrsniRad
raspored rada GUI

Aplikacija za vođenje rasopreda rada po uzoru na rad osoblja za čišćenje u KissSalis Therme Bad Kissingen



Rad sa aplikacijom je podijeljen na javni i korisnički dio. Korisnički dio ima dvije razine ovlasti rada.



Javni dio kojem svatko može pristupiti - nije potrebna prijava u aplikaciju 

	- U ovom dijelu je moguće:
		- otvaranje linka na GitHub repozitorij aplikacije
		- otvaranje linka na ER dijagram baze podataka aplikacije
		- upisivanje pristupnih podataka za prijavu u sustav (korisničko ime i lozinka su obavezni)




Korsninički dio kojem pristupaju korisnici sa razinom ovlasti 1 - potebna je prijava u aplikaciju 

	- U ovom dijelu je moguće:
	
		- pregled plana rada (Ctrl+m)
			- korisnik iz padajućeg izbornika vrši izbor godine i mjeseca za koje želi pogledati raspored
			- prikazuju se samo godine i mjeseci u kojima je prijavljeni korisnik već upisan u raspored
			- nakon odabira godine i mjeseca raspored se prikazuje u tabličnom obliku
			- dvostrukim klikom na bilo koju ćeliju tablice dabija se informacija o radnom vremenu na taj dan
			
		- izmijeniti svoje pristupne podatke (Ctrl+d)
			- vrši se provjera je li je novo korisničko ime zauzeto i poklapaju li se Loznika i Ponovno lozinka
			
		- odjava iz sustava (Ctrl+t), nakon odjave dolazi se opet na prozor za prijavu

Administratorski dio kojem pristupaju korisnici sa razinom ovlasti 2 - potebna je prijava u aplikaciju

	- U ovom dijelu je moguće:
	
		- rad sa rasporedom (Ctrl+s)
		
			- pregled rasporeda po istom sistemu kao i običan korisnik (Ctrl+m)
			
			- uređivanje rasporeda (Ctrl+e)
				- podaci koje je moguće unosti: oznaka, datum, korisnik	(sva polja su obavezna)
				- ukoliko se u padajućem izborniku ne pojavljuje željena godina ili mjesec, klikom na 
				  gumb "Insert into shedule" unosi se novi zapis u raspored. Ovdje je moguće unositi samo 
				  korisnike koji imaju status "aktivan"
				- ukoliko se željena godina i mjesec nalaze u padajućem izborniku, klikom na 
				  gumb "Show shedule" pojavljuje se raspored u  tabličnom obliku
				- na dnu tablice se automatski kalkulira višak/manjak potrebnih radnika na određeni dan
				- u broj radnika na da se računaju samo oznake Work with break(w) i Work without break(w*)
				- klikom na bilo koju ćeliju tablice pojavljuje se novi prozor u kojem se može vršiti izmjena oznake, 
				  ili obrisati sam unos
				- moguće je mijenjati samo oznaku, a ne i korisnika i datum
				
		- rad sa oznakama (Ctrl+l)
			- podaci koje je moguće unositi: ime oznake, kratica (sva polja su obavezna)
			- pri unosu ili izmjeni vrši se provjera postoji li već oznaka sa upisanim imenom ili skraćenicom
			- oznake Work with break(w) i Work without break(w*) se ne mogu izmjenjivati niti brisati
			
		- rad sa osobama (Ctrl+p) 
			- podaci koji se mogu unositi: ime, prezime, telefon, email, adresa (ime i prezime su obavezni)
			- pri unosu i izmjeni vrši se provjera postoji li već u bazi osoba sa istim podacima
			- pri brisanju se vrši provjera je li osoba unešena kao korisnik, ukoliko je - ne može ju se obrisati
			
		- rad sa korisnicima (Ctrl+u)
			- podaci koji se mogu unositi: osoba, korisničko ime, lozinka, osobni broj, razina, aktivan (sva polja su obavezna)
			- pri unosu i izmjeni vrši se provjera jesu li korisničko ime i odobni broj zauzeti, te je li osoba već korisnik
			- pri brisanju se vrši provjera je li korisnik uvršten u raspored, ukoliko je - ne može se obrisati
			
		- broj radnika na određeni dan u tjednu (Ctrl+n)
			- podaci koji se mogu unostiti: datum početka, datum isteka, broj tradnika za svaki dan u tjednu
			- obavezna polja se datum početka i isteka, ukoliko se u polje nekog dana ne unese broj on ne automatski postavlja na 0
			- pri unosu i izmjeni vrši se provjera preklapanja vremenskog intervala sa postojećim zapisima. Npr. ne može
			  se unijeti da pravila vrijede od 15.02.2020. do 15.03.2020. ukoliko već postoji zapis koji vrijedi od 01.03.2020. do 05.03.2020.
			- ovi podaci se uzimanju u obzir kod izmjena radporeda rada i prema njima se kalkulira broj radnka po danu u zadnjem redu tablice
			
		- rad sa redovnim radnim vremenom (Ctrl+r)
			- podaci koji se mogu unostiti: datum početka, datum isteka, početak i kraj radnog vremena za svaki dan u tjednu, trajanje pauze u minutama
			- sva polja su obavezna
 			- pri unosu i izmjeni vrši se provjera preklapanja vremenskog intervala sa postojećim zapisima. Npr. ne može
			  se unijeti da pravila vrijede od 15.02.2020. do 15.03.2020. ukoliko već postoji zapis koji vrijedi od 01.03.2020. do 05.03.2020.
			- ovi podaci se uzimaju u obzir kod prikaza detalja radnog vremena navedenog u radu korisnika sa tablicom rasporeda rada
			
		- rad sa iznimnim radnim vremenom  (Ctrl+w)
			- podaci koji se mogu unositi: datum, pocetak i kraj radnog vremena, opis (zabilješka) i trajanje pauze u minutama (sva polja su obavezna)
			- pri unosu i izmjeni vrši se provjera postojanja zapisa sa upisanim datumom
			- ovi podaci se uzimaju u obzir kod prikaza detalja radnog vremena navedenog u radu korisnika sa tablicom rasporeda rada, i imaju veći 
			  prioritet nad podacima redovnog radnog vremena
			  
		- izmjena pristušnih podataka (Ctrl+d)
			- vrši se provjera je li je novo korisničko ime zauzeto i poklapaju li se Loznika i Ponovno lozinka
			
		- odjava iz sustava (Ctrl+t), nakon odjave dolazi se opet na prozor za prijavu
		
		
		
		
		