import java.util.ArrayList;
import java.util.Objects;

public class Klienti {
    private String emri;
    private int mosha;
    private char gjinia;
    private ArrayList<Hapsira> hapsiratERezervuara;

    public Klienti(String emri, char gjinia,int mosha) throws RezervimiException {
        RezervimiException.checkErr(emri);
        RezervimiException.checkErr(mosha);
        if(!(gjinia == 'M' || gjinia == 'F'))
            throw new RezervimiException("Gjinia mund te jete vetem \'M\' ose \'F\'.");
        this.emri = emri;
        this.mosha = mosha;
        this.gjinia = gjinia;
        this.hapsiratERezervuara = new ArrayList<Hapsira>();
    }

    public boolean merreRadhen(Hoteli h) throws Exception{
        return !h.getRadha().isLocked();
    }
    public void rezervo(Hoteli h) throws Exception {
        if(merreRadhen(h)) {
            Hapsira hapsira = h.rezervoHapsiren(this);
            if(hapsira == null ) return;
            hapsiratERezervuara.add(hapsira);
            System.out.println(getEmri() + " rezervoj me sukses hapësirën [" + hapsira + "] ne hotelin " + h.getEmri());
        } else
            System.out.println(getEmri()  + " nuk e mori radhën ne hotelin " + h.getEmri());
    }
    public String getEmri() {
        return emri;
    }

    public int getMosha() {
        return mosha;
    }

    public char getGjinia() {
        return gjinia;
    }
    public ArrayList<Hapsira> getHapsiratERezervuara() {
        return hapsiratERezervuara;
    }

    public String toString(){
        return  getEmri() + " - " +(getGjinia() == 'M' ? "Mashkull" : "Femër" ) + " " + getMosha() + " vjec.";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Klienti klienti = (Klienti) o;

        if (mosha != klienti.mosha) return false;
        if (gjinia != klienti.gjinia) return false;
        if (!emri.equals(klienti.getEmri())) return false;
        return Objects.equals(hapsiratERezervuara, klienti.hapsiratERezervuara);
    }
}
