import java.io.*;
import java.util.*;

public class Counter {
    String alph = "abcdefghijklmnopqrstuvwxyz";

    public Map<Character, Integer> CounterRead(String fileName){
       Map<Character, Integer> count = new HashMap<Character, Integer>();
       for(int symbol = 0; symbol < alph.length(); symbol++){
           count.put(alph.charAt(symbol), 0);
       }

       try{
           File file = new File(fileName);
           FileReader fileReader = new FileReader(file);
           BufferedReader reader = new BufferedReader(fileReader);
           String fileLine = reader.readLine();
           while (fileLine != null) {
               fileLine = fileLine.toLowerCase();
               for(int i = 0; i < fileLine.length(); i++){
                   if((int) fileLine.charAt(i) < 123 && (int) fileLine.charAt(i) > 96){
                      int c = count.get(fileLine.charAt(i)) + 1;
                      count.put(fileLine.charAt(i), c);
                   }
               }
               fileLine = reader.readLine();
           }

       }catch (FileNotFoundException e){
           System.out.println("File was not found!!!");
           return null;
       } catch (IOException e){
           e.printStackTrace();
       }
       return count;
   }

   public void CounterWrite (String fileName, Map<Character, Integer> count){
            try(FileWriter writer = new FileWriter(fileName, false)){
                for(int i = 0; i < alph.length(); i++) {
                    String line = alph.charAt(i) + " - " + count.get(alph.charAt(i)) + '\n';
                    writer.write(line);
                }
                writer.flush();
            } catch (IOException e){
                System.out.println(e.getMessage());
            }

   }
}
