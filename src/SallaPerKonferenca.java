public class SallaPerKonferenca extends Hapsira {
    private int kapaciteti;

    public SallaPerKonferenca(int nr, String pershkrimi, double cmimi, int kapaciteti) throws RezervimiException {
        super(nr, pershkrimi, cmimi);
        RezervimiException.checkErr(kapaciteti);
        this.kapaciteti = kapaciteti;
    }

    public int getKapaciteti() {
        return kapaciteti;
    }

    public void setKapaciteti(int kapaciteti) throws RezervimiException{
       RezervimiException.checkErr(kapaciteti);
       this.kapaciteti = kapaciteti;
    }

    @Override
    public boolean kaWifi() {
        return true;
    }

    @Override
    public String getMonitorimi() {
        return "Kamerat";
    }
     public String toString() {
         return super.toString() + " me kapacitet " + kapaciteti;
     }
}
