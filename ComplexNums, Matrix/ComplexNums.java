package com.company;

public class ComplexNums {
    private double realPart = 0;
    private double imaginaryPart = 0;

    ComplexNums(double realPart){
        this.realPart = realPart;
        this.imaginaryPart = 0.0;
    }

    ComplexNums(double realPart, double imaginaryPart){
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }

    ComplexNums(){
        this.realPart = 0.0;
        this.imaginaryPart = 0.0;
    }

    public double getRealPart() {
        return realPart;
    }

    public double getImaginaryPart() {
        return imaginaryPart;
    }

    public void setRealPart(double realPart) {
        this.realPart = realPart;
    }

    public void setImaginaryPart(double imaginaryPart) {
        this.imaginaryPart = imaginaryPart;
    }

    public ComplexNums add(ComplexNums number){
        ComplexNums finalNum = new ComplexNums(this.realPart + number.realPart,
                                           this.imaginaryPart + number.imaginaryPart);
        return finalNum;
    }

    public ComplexNums sub(ComplexNums number){
        ComplexNums finalNum = new ComplexNums(this.realPart - number.realPart,
                                           this.imaginaryPart - number.imaginaryPart);
        return finalNum;
    }

    public ComplexNums multiplication(ComplexNums number){
        ComplexNums finalNum = new ComplexNums(this.realPart, this.imaginaryPart);
        finalNum.realPart = this.realPart * number.realPart - this.imaginaryPart * number.imaginaryPart;
        finalNum.imaginaryPart = this.realPart * number.imaginaryPart + number.realPart * this.imaginaryPart;
        return finalNum;
    }

    public ComplexNums division(ComplexNums number){
        double divider = Math.pow(number.realPart, 2) + Math.pow(number.imaginaryPart, 2);
        ComplexNums finalNum = new ComplexNums((this.realPart * number.realPart +
                                                       this.imaginaryPart * number.imaginaryPart) / divider,
                                           (this.imaginaryPart * number.realPart -
                                                       this.realPart * number.imaginaryPart) / divider);
        return finalNum;
    }

    public String getAlgebraicForm(){
        String ans = "";
        ans += this.realPart;
        if(this.imaginaryPart > 0){
            ans += " + " + this.imaginaryPart + "i";
            return ans;
        }
        else if (this.imaginaryPart < 0){
            ans += this.imaginaryPart + "i";
            return  ans;
        }
        else{
            return ans;
        }
    }

    public String getTrigonometricForm(){
        double r = Math.sqrt(Math.pow(this.realPart, 2) + Math.pow(this.imaginaryPart, 2));
        double angle;
        try{
            angle = Math.acos(this.realPart / r);
        }catch (Exception e){
            System.out.println("Error: " + e.toString());
            return "0";
        }
        String ans = "";
        ans = ans + r + "(cos(" + angle + ")" + " + "  + "i*sin(" + angle + ")" + ")";
        return ans;
    }
}
