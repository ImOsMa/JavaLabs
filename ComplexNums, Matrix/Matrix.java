package com.company;


public class Matrix {
    private final ComplexNums[][] matrix;
    private final int rows;
    private final int columns;

    public Matrix(int n, int m){
        this.rows = n;
        this.columns = m;
        this.matrix = new ComplexNums[this.rows][this.columns];
        for (int i = 0; i < this.rows; i++){
            for(int j = 0; j < this.columns; j++){
                this.matrix[i][j] = new ComplexNums();
            }
        }
    }

    public Matrix(){
        this.rows = 0;
        this.columns = 0;
        this.matrix = null;
    }

    public int getRows(){
        return this.rows;
    }

    public int getColumns(){
        return this.columns;
    }

    public ComplexNums getElement(int i, int j){
        if(i > this.rows || j > this.columns || i < 0 || j < 0){
            throw new IndexOutOfBoundsException("Incorrect index of the matrix");
        }else{
            return this.matrix[i][j];
        }
    }

    public void setElement(int i, int j, ComplexNums element){
        if(i > this.rows || j > this.columns || i < 0 || j < 0){
            throw new IndexOutOfBoundsException("Incorrect index of the matrix");
        }else{
            this.matrix[i][j] = element;
        }
    }

    public Matrix add(Matrix secondMatrix){
        if(this.getRows() != secondMatrix.getRows() || this.getColumns() != secondMatrix.getColumns()){
            throw new ArithmeticException("Incorrect size of matrix");
        }else{
            Matrix ansMatrix = new Matrix(this.getRows(), this.getColumns());
            for(int i = 0; i < this.getRows(); i++){
                for(int j = 0; j < this.getColumns(); j++){
                    ComplexNums newElement = this.getElement(i, j).add(secondMatrix.getElement(i, j));
                    ansMatrix.setElement(i, j, newElement);
                }
            }
            return ansMatrix;
        }
    }


    public Matrix add(ComplexNums secondNum){
        Matrix ansMatrix = new Matrix(this.getRows(), this.getColumns());
        for(int i = 0; i < this.getRows(); i++){
            for(int j = 0; j < this.getColumns(); j++){
                ComplexNums newEl = this.matrix[i][j].add(secondNum);
                ansMatrix.setElement(i, j, newEl);
            }
        }
        return  ansMatrix;
    }

    public Matrix transpose(){
        Matrix ansMatrix = new Matrix(this.getColumns(), this.getRows());
        for(int i = 0; i < getRows(); i++){
            for(int j = 0; j < getColumns(); j++){
                ansMatrix.setElement(i, j, this.matrix[j][i]);
            }
        }
        return ansMatrix;
    }

    public Matrix multi(Matrix secondMatrix){
        if(this.getColumns() != secondMatrix.getRows()){
            throw  new ArithmeticException("Incorrect size of matrix");
        }else{
            Matrix ansMatrix = new Matrix(this.getRows(), secondMatrix.getColumns());
            for(int i = 0; i < this.getRows(); i++){
                for(int j = 0; j < secondMatrix.getColumns(); j++){
                    ComplexNums multiSum = new ComplexNums();
                    for(int k = 0; k < this.getColumns(); k++){
                        multiSum = multiSum.add(matrix[i][k].multiplication(secondMatrix.getElement(k, j)));
                    }
                    ansMatrix.setElement(i, j, multiSum);
                }
            }
            return ansMatrix;
        }
    }

    public Matrix numMulti(ComplexNums number){
        Matrix ansMatrix = new Matrix(this.getRows(), this.getColumns());
        for(int i = 0; i < this.getRows(); i++){
            for(int j = 0; j < this.getColumns(); j++){
                ComplexNums mul = this.matrix[i][j].multiplication(number);
                ansMatrix.setElement(i, j, mul);
            }
        }
        return ansMatrix;
    }

    public Matrix sub(Matrix secondMatrix){
        if(this.getColumns() != secondMatrix.getColumns() || this.getRows() != secondMatrix.getRows()){
            throw new ArithmeticException("Incorrect size of matrix");
        }else{
            Matrix ansMatrix = new Matrix(this.getRows(), this.getColumns());
            for(int i = 0; i < this.getRows(); i++){
                for(int j = 0; j < this.getColumns(); j++){
                    ComplexNums newElement = this.getElement(i, j).sub(secondMatrix.getElement(i, j));
                    ansMatrix.setElement(i, j, newElement);
                }
            }
            return ansMatrix;
        }
    }

    public Matrix sub(ComplexNums secondNum){
        Matrix ansMatrix = new Matrix(this.getRows(), this.getColumns());
        for(int i = 0; i < this.getRows(); i++){
            for(int j = 0; j < this.getColumns(); j++){
                ComplexNums newEl = this.matrix[i][j].sub(secondNum);
                ansMatrix.setElement(i, j, newEl);
            }
        }
        return  ansMatrix;
    }

    public void printMatrix(){
        for(int i = 0; i < this.getRows(); i++){
            for(int j = 0; j < this.getColumns(); j++){
                System.out.print(this.matrix[i][j].getAlgebraicForm() + " ");
            }
            System.out.println();
        }
    }

    private Matrix SubArray(Matrix currentMatrix, int column){
        Matrix ans = new Matrix(currentMatrix.getRows() - 1, currentMatrix.getRows() - 1);
        int k = 0;
        int t = 0;
        for(int i = 1; i < currentMatrix.getRows(); i++){
            for(int j = 0; j <currentMatrix.getColumns(); j++){
                if(j == column)
                    continue;
                ans.setElement(k, t, currentMatrix.getElement(i, j));
                if(t < ans.getRows() - 1){
                    t++;
                }else{
                    k++;
                    t = 0;
                }
            }
        }
        return ans;
    }

    public ComplexNums Determinant(){
        if(this.getRows() != this.getColumns()){
            throw new ArrayIndexOutOfBoundsException("Incorrect matrix for determinant");
        }
        if (this.getRows() == 1)
            return this.matrix[0][0];

        ComplexNums ans = new ComplexNums();
        for(int i = 0; i < this.getRows(); i++){
            ans = ans.add(matrix[0][i].multiplication
                    (SubArray(this, i).Determinant()).multiplication
                                                                (new ComplexNums(1 + (i % 2) * -2)));
        }
        return ans;
    }
}
