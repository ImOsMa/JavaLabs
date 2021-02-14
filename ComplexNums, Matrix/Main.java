package com.company;

public class Main {

    public static void main(String[] args) {
        ComplexNums n1 = new ComplexNums(7, 10.5);
        ComplexNums n2 = new ComplexNums(3, 2.1);


        System.out.println("AlgebraicForm: " + n1.getAlgebraicForm());
        System.out.println("TrigonometricForm: " + n1.getTrigonometricForm());
        System.out.println();
        ComplexNums n3;
        n3 = n1.add(n2);
        System.out.println("n1 + n2: " + n3.getAlgebraicForm());
        n3 = n1.multiplication(n2);
        System.out.println("n1 * n2: " + n3.getAlgebraicForm());
        n3 = n1.sub(n2);
        System.out.println("n1 - n2: " + n3.getAlgebraicForm());
        n3 = n1.division(n2);
        System.out.println("n1 '/' n2: " + n3.getAlgebraicForm());


        try {
            Matrix matrix0 = new Matrix(-1,-1);
        }catch (Exception e){
            System.out.println();
            System.out.println("Error matrix size: " + e.toString());
        }
        Matrix matrix1 = new Matrix(2,2);
        matrix1.setElement( 0, 0, new ComplexNums(2, 1));
        matrix1.setElement( 0, 1, new ComplexNums(3, 4));
        matrix1.setElement( 1, 0, new ComplexNums(5, 2));
        matrix1.setElement( 1, 1, new ComplexNums(1, 3));

        Matrix matrix2 = new Matrix(2,2);
        matrix2.setElement( 0, 0, new ComplexNums(5, 5));
        matrix2.setElement( 0, 1, new ComplexNums(2, 8));
        matrix2.setElement( 1, 0, new ComplexNums(3.2, 7.2));
        matrix2.setElement( 1, 1, new ComplexNums(1, 3));

        System.out.println();
        System.out.println("Matrix1:");
        matrix1.printMatrix();

        System.out.println();
        System.out.println("Matrix1 + Matrix2");
        matrix1.add(matrix2).printMatrix();

        System.out.println();
        System.out.println("Matrix1 - Matrix2");
        matrix1.sub(matrix2).printMatrix();

        System.out.println();
        System.out.println("Matrix1 * Matrix2");
        matrix1.multi(matrix2).printMatrix();

        System.out.println();
        System.out.println("Matrix1 * (1 + 1i)");
        matrix1.numMulti(new ComplexNums(1,1)).printMatrix();

        System.out.println();
        System.out.println("Transportation Matrix1:");
        matrix1.transpose().printMatrix();

        System.out.println();
        System.out.println("Determination of Matrix1:");
        System.out.println(matrix1.Determinant().getAlgebraicForm());
    }
}
