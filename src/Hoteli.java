import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Hoteli{
    private String emri;
    private ReentrantLock radha;
    private Vector<Hapsira> hapsirat;
    private Hashtable<Klienti, ArrayList<Hapsira>> rezervimet;
    BufferedWriter bw;


    public Hoteli (String emri) throws RezervimiException{
        RezervimiException.checkErr(emri);
        this.emri = emri;
        hapsirat = new Vector<Hapsira>();
        radha = new ReentrantLock();
        rezervimet = new Hashtable<Klienti,ArrayList<Hapsira>>();
    }
    public void shtoHapesire(Hapsira h) throws RezervimiException{
        RezervimiException.checkErr(h);
        if(hapsirat.contains(h))
            throw new RezervimiException("Hapsira tashme ekziston ne liste.");
        hapsirat.add(h);
    }
    public boolean kaHapsira(){
        return !hapsirat.isEmpty();
    }
    public Hapsira rezervoHapsiren(Klienti k) throws Exception{
        radha.lock();
        try {
            if (!kaHapsira()) return null;
            Hapsira hapsiraPare = hapsirat.remove(0);
            regjistroHapsiren(k, hapsiraPare);
            return hapsiraPare;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                radha.unlock();
            }
        return null;
    }
    public void regjistroHapsiren(Klienti k, Hapsira h) throws RezervimiException{
        RezervimiException.checkErr(k);
        RezervimiException.checkErr(h);
        if(rezervimet.containsKey(k))
            rezervimet.get(k).add(h);
        else {
            ArrayList<Hapsira> hapsiratEReja = new ArrayList<Hapsira>();
            hapsiratEReja.add(h);
            rezervimet.put(k,hapsiratEReja);
        }
    }
    public void faturo() throws Exception{
      while(true){
          if(!kaHapsira()) break;
      }
        for(Map.Entry<Klienti , ArrayList<Hapsira>> entry: rezervimet.entrySet()){
            Klienti k = entry.getKey();
            ArrayList<Hapsira> hapsiratLokale = entry.getValue();
            try {
                bw = new BufferedWriter(new FileWriter("src\\klientet\\"+k.getEmri()+ ".txt"));
                bw.write(getClass().getName() + k);
                linjaRe();
                bw.write("Numri i hapsirave te rezervuara: " + hapsiratLokale.size());
                linjaRe();
                shkruajRezervimet(hapsiratLokale);
                bw.flush();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    private void shkruajRezervimet(ArrayList<Hapsira> listaHapsirav)throws Exception{
        double shuma = 0;
        for (Hapsira h : listaHapsirav) {
            bw.write(h.toString());
            bw.newLine();
            shuma += h.getCmimi();
        }
        bw.write("Totali: " + (shuma));
        linjaRe();
    }
    private void linjaRe()throws Exception{
        bw.newLine(); bw.write( "- ".repeat(25));bw.newLine();
    }
    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public ReentrantLock getRadha() {
        return radha;
    }

    public void setRadha(ReentrantLock radha) {
        this.radha = radha;
    }

    public Vector<Hapsira> getHapsirat() {
        return hapsirat;
    }

    public void setHapsirat(Vector<Hapsira> hapsirat) {
        this.hapsirat = hapsirat;
    }

    public Hashtable<Klienti, ArrayList<Hapsira>> getRezervimet() {
        return rezervimet;
    }

    public void setRezervimet(Hashtable<Klienti, ArrayList<Hapsira>> rezervimet) {
        this.rezervimet = rezervimet;
    }
}

