/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Restu Riani
 * Release Date : June 13, 2019
 * Placed       : Pekanbaru,Indonesia
 */
public class Test_TicTacToe {
    
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        Test_TicTacToe game = new Test_TicTacToe();
        game.initialize_Table();
        System.out.println("Welcome to Tic Tac Toe Game!");
        
        do{
            System.out.println("Current Layout Table : ");
            game.printTable();
            int row;
            int column;
            
            do{
                System.out.println("Player " + game.getPlayer() + ", Input the Row and Column Number to Place your Symbol : ");
                row = scan.nextInt()-1;
                column = scan.nextInt()-1;                
            }
            
            while (!game.Place_Symbol(row, column));
            game.changePlayer();
        }
        
        while (!game.Winner() && !game.TableFull());
        if (game.TableFull() && !game.Winner()) {
            JOptionPane.showMessageDialog(null, "The Game is Draw!", null, JOptionPane.INFORMATION_MESSAGE);
        }
        
        else {
            System.out.println("Current Table Layout : ");
            game.printTable();
            game.changePlayer();
            JOptionPane.showMessageDialog(null, Character.toUpperCase(game.getPlayer()) + " Wins, Congratulations!", null, JOptionPane.INFORMATION_MESSAGE);
            System.out.println();
            System.out.println(Character.toUpperCase(game.getPlayer()) + " Wins, Congratulations!");
        }
    }
    
    
    private char [][] table;
    private char player;
    
    public Test_TicTacToe() {
        table = new char[3][3];
        player = 'X';
        initialize_Table();
    }
    
    //Give access to player
    public char getPlayer(){
        return player;
    }
    
    //Reset or set all table back to empty values
    public void initialize_Table() {
        
        //Loop rows
        for (int i = 0; i < 3; i++){
            //Loop columns
            for (int j = 0; j < 3; j++){
                table[i][j] = '-';
            }
        }
    }
    
    // Print current table
    public void printTable() {
        System.out.println("-------------");
        
        for (int i = 0; i < 3; i++){
            System.out.print("| ");
            for (int j = 0; j < 3; j++){
                System.out.print(table[i][j] + " | ");
            }
            
            System.out.println();
            System.out.println("-------------");
        }
    }
    
    //Loop all cells of the table 
    //And if found empty (contains char '-') then return false.
    //Otherwise table is full
    public boolean TableFull() {
        boolean Full = true;
        
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (table[i][j] == '-'){
                    Full = false;
                }
            }
        }
        
        return Full;
    }
    
    //Returns true if there is a win
    //And otherwise
    //This call other win functions to check the entire table
    /*
    Note: CC -> Check Columns
          CD -> Check Diagonals
          CR -> Check Rows
          CRC -> Check Rows Columns
    */
    public boolean Winner() {
        return (CR() || CC() || CD());
    }
    
    //Loop rows to see if any are winner
    private boolean CR(){
        for (int i = 0; i < 3; i++){
            if (CRC(table[i][0], table[i][1], table[i][2]) == true){
                return true;
            }
        }
        
        return false;
    }
    
    //Loop columns to see if any are winner
    private boolean CC(){
        for (int i = 0; i < 3; i++){
            if (CRC(table[0][i], table[1][i], table[2][i]) == true){
                return true;
            }
        }
        
        return false;
    }
    
    //Check two diagonals to see if either is a wins
    //Return true if either wins
    private boolean CD(){
        return ((CRC(table[0][0], table[1][1], table[2][2]) == true) || (CRC(table[0][2], table[1][1], table[2][0]) == true)); 
    }
    
    //Check to see if all three values are the same and not empty indicating a win
    private boolean CRC(char c1, char c2, char c3){
        return ((c1 != '-') && (c1 == c2) && (c2 == c3));
    }
    
    //Change player
    public void changePlayer(){
        if (player == 'X'){
            player = 'O';
        }
        
        else {
            player = 'X';
        }
    }
    
    //Places a symbol at the cells specified by row and column with the symbol of the player choice
    public boolean Place_Symbol(int row, int column){
        
        //Make sure that row and column are in bounds of the table
        if ((row >= 0) && (row < 3)) {
            if ((column >= 0) && (column < 3)) {
                
                if (table[row][column] == '-') {
                    table[row][column] = player;
                    return true;
                }
            }
        }
        
        return false;
    }
}
