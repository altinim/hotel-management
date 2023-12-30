import java.io.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;

public class Qyteti {
   private Hoteli hoteli;
   private ArrayList<Klienti> klientet;
   public Qyteti(Hoteli h) throws RezervimiException{
      RezervimiException.checkErr(h);
      hoteli = h;
      klientet = new ArrayList<Klienti>();
   }
   class Rezervimi extends Thread{
      private Hoteli hoteli;
      private Klienti klienti;
      public Rezervimi(Hoteli h, Klienti k) throws RezervimiException{
         RezervimiException.checkErr(h);
         RezervimiException.checkErr(k);
         hoteli = h;
         klienti = k;
      }
      @Override
      public void run(){
         while(hoteli.kaHapsira()){
            try {
               Thread.sleep( new Random().nextInt(1250) + 250);
               klienti.rezervo(hoteli);
            }catch (Exception e) {
               throw new RuntimeException(e);
            }
         }
      }
      public void setHoteli(Hoteli hoteli) throws RezervimiException{
         RezervimiException.checkErr(hoteli);
         this.hoteli = hoteli;
      }
      public Klienti getKlienti() {
         return klienti;
      }

      public void setKlienti(Klienti klienti)throws RezervimiException {
         RezervimiException.checkErr(klienti);
         this.klienti = klienti;
      }
   }
   public void lexoHapsirat(){
      BufferedReader br;
      try {
         br = new BufferedReader(new FileReader("src/hapsirat.txt"));
         String line = null;
         while((line = br.readLine()) != null){
            if(hapsireValide(line)) {
               String[] vlerat = line.split(":");
               krijoHapsirat(vlerat);
            }
         }
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }
   private boolean hapsireValide(String line){
      if(line.trim().isEmpty())
         return false;
      if(!line.contains(":"))
         return false;
      String[] rreshtiArr = line.split(":");
      if(rreshtiArr.length != 5)
         return false;
     try{
        Integer.parseInt(rreshtiArr[1]);
        Double.parseDouble(rreshtiArr[3]);
     }
     catch (NumberFormatException e){
        return false;
     }
     try{
        Integer.parseInt(rreshtiArr[4]);
     }catch (NumberFormatException e){
        if(!(rreshtiArr[4].equalsIgnoreCase("true") ||rreshtiArr[4].equalsIgnoreCase("false")))
            return false;
     }
      for(LlojetEHapsirave s : LlojetEHapsirave.values())
         if (String.valueOf(s).equalsIgnoreCase(rreshtiArr[0]))
            return true;
      return false;
   }
   private void krijoHapsirat(String[] arr)throws RezervimiException{
      int nr = Integer.valueOf(arr[1]);
      String pershkrimi = arr[2];
      Double cmimi = Double.valueOf(arr[3]);
      switch(arr[0]) {
         case "DhomaStandarde":
            hoteli.shtoHapesire(new DhomaStandarde(nr, pershkrimi, cmimi, Boolean.valueOf(arr[4])));
            break;
         case "DhomaVIP":
            hoteli.shtoHapesire(new DhomaVIP(nr, pershkrimi, cmimi, Boolean.valueOf(arr[4])));
            break;
         case "Restoranti":
            hoteli.shtoHapesire(new Restoranti(nr,pershkrimi, cmimi, Integer.valueOf(arr[4])));
            break;
         case "SallaPerKonferenca":
            hoteli.shtoHapesire(new SallaPerKonferenca(nr, pershkrimi, cmimi, Integer.valueOf(arr[4])));
         default:
            return;
      }
   }
   public void shtoKlientin(Klienti k) throws RezervimiException{
      RezervimiException.checkErr(k);
      if(klientet.contains(k))
         throw new RezervimiException("Klienti tashme ekziston.");
       klientet.add(k);
   }  public Hoteli getHoteli() {
      return hoteli;
   }
   public void lexoKlientet() {
      BufferedReader br;
      try {
         br = new BufferedReader(new FileReader("src/klientet.txt"));
         String line = null;
         while ((line = br.readLine()) != null) {
            if(klientValid(line)) {
               String[] arr = line.split(":");
               shtoKlientin(new Klienti(arr[0], arr[1].charAt(0), Integer.valueOf(arr[2])));
            }
         }
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }
   private boolean klientValid(String s){
      if (s.trim().isEmpty())
         return false;
      if(!s.contains(":"))
         return false;
      String[] rreshtiArr = s.split(":");
      if(rreshtiArr.length != 3)
         return false;
      if(!(rreshtiArr[1].equals("M") ||rreshtiArr[1].equals("F")))
         return false;
      try{
         Integer.parseInt(rreshtiArr[2]);
      }catch (NumberFormatException ex){
         return false;
      }
      return true;
   }
   public void filloRezervimet() throws Exception {
      for(Klienti k: klientet)
         new Rezervimi(hoteli, k).start();
   }
   public static void main(String[] args){
      Hoteli h;
      Qyteti q;
      try {
         h = new Hoteli("Grand");
         q  = new Qyteti(h);
         q.lexoHapsirat();
         q.lexoKlientet();
         q.filloRezervimet();
         Thread.sleep(3000);
         h.faturo();
      }
      catch (Exception ex){
         ex.printStackTrace();
      }
   }
}
