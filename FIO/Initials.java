import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Initials {


    public void PrintChangedInfo(String data) throws Exception {
        String[] info = separateAndCheckData(data);
        System.out.println("Initials: " + getInitials(info));
        String sex;
        if(sexDetermination(info[2])){
            sex = "Male";
        } else {
            sex = "Female";
        }

        System.out.println("Sex: " + sex);
        System.out.println("Age: " + ageDetermination(info[3]));
    }

    public String[] separateAndCheckData(String data) throws Exception {
        String[] info = data.split(" ");
        if (info.length != 4) {
            throw new ArrayIndexOutOfBoundsException("Incorrect amount of data entered");
        }
        Pattern p = Pattern.compile("\\d+");
        if(p.matcher(info[0]).find()) {
            throw new Exception("Name contains number");
        }

        if(p.matcher(info[1]).find()) {
            throw new Exception("Surname contains number");
        }

        if(p.matcher(info[2]).find()) {
            throw new Exception("Patronymic contains number");
        }
        return info;
    }

    private boolean sexDetermination(String patronymic) {
        // Man - true
        // Women - false
        if(patronymic.charAt(patronymic.length() - 1) != 'a') {
            return true;
        }
        return false;
    }

    private int ageDetermination(String date) {
        //System.out.println(date);
        String[] datePiece = date.split("\\.");
        LocalDate birthDate = LocalDate.of(Integer.parseInt(datePiece[2]),
                Integer.parseInt(datePiece[1]),
                Integer.parseInt(datePiece[0]));
        LocalDate now = LocalDate.now();
        return Period.between(birthDate, now).getYears();
    }

    private String getInitials(String[] info) {
        String surname = info[0];
        String inicial = info[1].charAt(0) + ". " + info[2].charAt(0) + ".";
        return surname + " " + inicial;
    }
}
