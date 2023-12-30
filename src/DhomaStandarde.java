public class DhomaStandarde extends Hapsira {
    private boolean kaTV;

    public DhomaStandarde(int nr, String pershkrimi, double cmimi, boolean kaTV) throws RezervimiException {
        super(nr, pershkrimi, cmimi);
        this.kaTV = kaTV;
    }
    public String getMonitorimi(){
        return "Kamerat";
    }
    public boolean kaWifi(){
        return false;
    }

    public boolean kaTV() {
        return kaTV;
    }

    public void setKaTV(boolean kaTV) {
        this.kaTV = kaTV;
    }

    public String toString(){
        return  super.toString() + (kaTV ? "" :" nuk") + " ka TV";
    }

}
