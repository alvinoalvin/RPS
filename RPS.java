
 
//       clear; del *.class; javac RPS*.java; java -ea RPSTester

import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;

/**
* The RPS program asks the user for a nubmer of max points
* then it starts playing rock paper scissors with you!
*
* @author  Jeremy Hilliker
* @author  Alvin Ng	
* @version 1.4 The Castle in the Sky
* @since   2017/05/02 
* @see     https://d2l.langara.bc.ca/d2l/lms/dropbox/user/folder_submit_files.d2l?db=50204&grpid=0&isprv=0&bp=0&ou=88736
*/

public class RPS {
   public final static int ROCK = 0;
   public final static int PAPER = 1;
   public final static int SCISSORS = 2;
   private final static String[] CHOICES = {"ROCK", "PAPER", "SCISSORS"};

   private final static Random rand = new Random();
   public static void main(String[] args) {
      if(args.length > 0){
         reset(Integer.parseInt(args[0]));
      }
      char newChar;
         newChar=playGame(new Scanner(System.in));
   }  
   /**
   * This method determines the winner of the round and returns it to the playRound function
   * @param a This is the first paramter the int form of the player's choice
   * @param b  This is the second parameter the int form of the computer's choice
   * @return int the winner of the game if a returns 1, else if b returns -1, else draw returns 0
   */
   public static int determineWinner(int a, int b) {
      // determines the winner of a round of RPS
      // if a beats b, return 1
      // if b beats a, return -1
      // otherwise return 0
         if(a == b){
            return 0;
         }
         else if(a==0 && b==1){
            return -1;
         }
         else if(a==0 && b==2){
            return 1;   
         }
         else if(a==1 && b==2){
            return -1;
         }
         else if(a==2 && b==0){
            return -1;
         }
         else if(a==0 && b==2){
            return 1;
         }
         else if(a==1 && b==0){
            return 1;
         }
         else if(a==2 && b==1){
            return 1;
         }
/*          case (a==0 && b==1): return 1;  */
/*          case (a==0 && b==2): return -1;  */
/*          case (a==1 && b==0): return -1;  */
/*          case (a==1 && b==2): return 1;  */
/*          case (a==2 && b==0): return 1;  */
/*          case (a==2 && b==1): return -1;
 *       }
 */
      return -50; // unreachable
   }
/**
   * This method determines whether char c is an option
   * @param c The character that we are checking for
   * @param a  This is the second parameter an array in which all the choices the player can choose are stored in other than quit
   * @return boolean  Returns True if the character c is an option otherwise returns false
   */
   public static boolean contains(char c, char[] a) {//checks if char c is an option in the game
      for(int i=0; i<a.length; i++){
         if (c==a[i]){//checks if the character is in the array
            return true;
         }
      }
      // determines if a given character is in the array
      return false;
   }
   
   
/**
   * This method gets input from the user and checks it to see if it's proper input
   * @param prompt a prompt that asks the user to select and
   * @param a  This is the second parameter an array in which all the choices the player can choose are stored in other than quit
   * @return Returns True if the character c is an option otherwise returns false
   */
   public static char getInput(String prompt, char[] options, Scanner sc) {//gets input from user and returns it after using the contains function

      String out = prompt + " ( ";
           for(int i=0; i<options.length; i++){
              if(i==options.length-1){
                 out+=options[i];
              }
              else{
                 out+=(options[i]+", ");
              }
           }
           out+=" ):";
      
      do {
/*          System.out.println(prompt+" ( "+options[0]+", "+options[1]+", "+options[2]+" ):");  */
         System.out.println(out);
         String inputString = sc.next();
         if(inputString.length() == 1) {
            char input = inputString.charAt(0);
            if(contains(input, options)) {
               return input;
            }
         }
      } while(true);
   }
   
/**
   * This method uses getInput, makeChoice, printCompChoice, and the determineWinner functions to play a round of rock paper scissors
   * @param Scanner sc a scanner to be passed into getInput
   * @return char tells us who won the round used in playGame returns 'A' if player one wins the round, 'B' if computer wins the round, and nothing if draw
   */
//-------------------------------------------------------------------Plays a round -----------------------------------------------------------------------
   public static char playRound(Scanner sc) {
      char[] choices= {'r','p','s','q'};
      int p1Choice,compChoice,outcome;
      char input;
      String prompt="Choose";
      
      do{
         do{
            input=getInput(prompt,choices, sc);
         }while(input=='\0');
         
         if (input=='q'||input=='Q'){
            System.out.println("Quitting");
            return 'q';
         }
         p1Choice=mapToChoice(input);
         compChoice=makeChoice();
         
         System.out.println("Computer chose: " + CHOICES[compChoice]);
         System.out.println("  Player chose: " + CHOICES[p1Choice]);
         outcome=determineWinner(p1Choice,compChoice);

         if(outcome==1){
            System.out.println("Player scores!");
            return 'p';
         }
         else if(outcome==-1){
            System.out.println("Computer scores!");
            return 'c';   
         }
         else{
            System.out.println("Draw!");   
         }

      }while(outcome==0);// repeat if it was a draw
      return '\0'; // unreachable
   }
   

   /**
   * This method prints out the computer's choice in a string format as opposed ot the int format that it was previously in
   * @param compChoice int version of the choice
   * @return void
   */
//---------------------------------------------------------------------------Plays a game-------------------------------------------------------------------------------
   public static char playGame(Scanner sc) {
      char roundWin;//which player won the round player p1(A) or p2(B)
      
      // a game consists of a number of rounds
      // prompt the user for a maximum score to win
      System.out.println("How many points to win?");
      int max=getIntInput(sc);
      System.out.println("Score: p=0 c=0");
      int p1=0, p2=0;//player totals
      // play rounds until that score is reached (use playRound)
      
      do{
         roundWin=playRound(sc);//calls the playRound function
         if(roundWin=='p'){//checks if p1 is winner then adds 1 pt to p1 if p1 is the winner
            p1+=1;   
         }
         else if(roundWin=='c'){//checks if p2 is the winner then adds 1 pt to p2 if p2 is the winner
            p2+=1;   
         }
         else if(roundWin=='q'){
            return 'q';
         }
         
         if (max!=1 && (p1!=max) && (p2!=max)){
            System.out.println("Score: p="+p1+" c="+p2);           
         }

      }while((p1!=max) && (p2!=max));

      // display & return winner
      if (p1==max){
         System.out.println("Player wins! "+p1+" : "+p2);
         return 'p';
      }
      else if(p2==max){
         System.out.println("Computer wins! "+p1+" : "+p2);
         return 'c';
      }
      
      System.out.println("Play Again?");
      System.out.println("Type 'Y' to play again otherwise press any other key");
      char playAgain=sc.next().charAt(0);
      return playAgain;
   }

   // -----------------------------DONT CHANGE THE METHODS BELOW.  They work fine and are needed by the test suite-----------------------------------
   
   //gets integer input
   public static int getIntInput(Scanner sc){
      if(sc.hasNextInt()){      
         int max=sc.nextInt();
         return max;
      }
      else{
         System.out.println("That was not an integer as punishment program will on quit");
         System.out.println("Quitting Program");
         System.exit(0);
      }   
      return 0; //wont be reached
   }
   public static void reset(int seed) {
      rand.setSeed(seed);
   }

   public static int makeChoice() {    
      return rand.nextInt(CHOICES.length);
   }

   public static String toString(int rps) {
      return CHOICES[rps];
   }

   public static int mapToChoice(char c) {
      switch (c) {
         case 'r': return ROCK;
         case 'p': return PAPER;
         case 's': return SCISSORS;
      }
      assert false;
      return 'x'; // unreachable
   }
}