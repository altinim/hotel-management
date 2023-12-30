public abstract class Hapsira implements Monitorohet{
    private int nr;
    private String pershkrimi;
    private double cmimi;

    public Hapsira(int nr, String pershkrimi, double cmimi) throws RezervimiException {
        RezervimiException.checkErr(nr);
        RezervimiException.checkErr(pershkrimi);
        RezervimiException.checkErr(cmimi);
        this.nr = nr;
        this.pershkrimi = pershkrimi;
        this.cmimi = cmimi;
    }
    public int getNr() {
        return nr;
    }
    public String getPershkrimi() {
        return pershkrimi;
    }
    public double getCmimi() {
        return cmimi;
    }
    public void setCmimi(double cmimi) throws RezervimiException {
        RezervimiException.checkErr(cmimi);
        this.cmimi = cmimi;
    }

    public abstract boolean kaWifi();
    public String toString(){
        return getClass().getName() + " " + nr + "-" + pershkrimi + ":" + cmimi;
    }
    @Override
    public boolean equals(Object o ){
        return (o  instanceof Hapsira) ? nr == ((Hapsira)o).getNr() : false;
    }
}
