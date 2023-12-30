public class DhomaVIP extends Hapsira {
    private boolean kaGjakuzi;

    public DhomaVIP(int nr, String pershkrimi, double cmimi, boolean kaGjakuzi) throws RezervimiException {
        super(nr, pershkrimi, cmimi);
        this.kaGjakuzi = kaGjakuzi;
    }

    public boolean kaGjakuzi() {
        return kaGjakuzi;
    }

    public void setKaGjakuzi(boolean kaGjakuzi) {
        this.kaGjakuzi = kaGjakuzi;
    }

    @Override
    public boolean kaWifi() {
        return true;
    }

    @Override
    public String getMonitorimi() {
        return "Sigurimi Fizik";
    }
    public String toString(){
        return super.toString()  + (kaGjakuzi ? "" :" nuk") + " ka Gjakuzi";
    }

}
