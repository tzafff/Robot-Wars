/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot.wars;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author chris
 */
public class RobotWars {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       
        Scanner input = new Scanner(System.in);
        int level;
        int tunnels;
        int room;
        int winner = 0;
        int numOfnewSoldiers = 0;
        int entry;
        Random rnd = new Random();
        
        while(true){
        System.out.print("Choose level (1-3): ");

            try{
                 level = input.nextInt();
                 if(level >= 1 && level <=3)
                    break;
                 else
                 {
                    System.out.println("Wrong input ");
                   
                 } 
                 
               }
            catch(InputMismatchException e)
            {
                System.out.println("Wrong input ");
            }
            input.nextLine();
        }
        
        
        while(true){
            System.out.print("Choose the number of tunnels you want (1-3): ");

            try{
                 tunnels = input.nextInt();
                 if(tunnels >= 1 && tunnels <=3)
                    break;
                 else
                 {
                    System.out.println("Wrong input ");
                   
                 } 
                 
               }
            catch(InputMismatchException e)
            {
                System.out.println("Wrong input ");
            }
            input.nextLine();
        } 
            
           while(true){
            System.out.print("How many rooms do you want the tunnel to have (3-8): ");

            try{
                 room = input.nextInt();
                 if(room >= 3 && room <=8)
                    break;
                 else
                    System.out.println("Wrong input ");
                    
                 
               }
            catch(InputMismatchException e)
            {
                System.out.println("Wrong input ");
            }
            input.nextLine();
        } 
           
           
        Colony c = new Colony(tunnels,room,20);
        
        //c.printColony();
        int robotChoice = 0;
        
        int tunnelChoice = 0 ;
        int roomChoice = 0;
        c.printColony();    
        
    while(true) {  
        
        robotChoice = 0;
        while(robotChoice != 10)
        {
            
            try{
                System.out.println("Choose the robot you want");
                System.out.println("1 : EnergyProducerRobot");
                System.out.println("2 : ArmoredRobot");
                System.out.println("3 : FighterRobot");
                System.out.println("4 : ShooterRobot");
                System.out.println("5 : FireRobot");
                System.out.println("6 : Save the game");
                System.out.println("10 : Wait for next ROUND");
                
                robotChoice = input.nextInt();
                
                if(robotChoice == 6)
                {
                    c.saveColony(level);
                }
                
                if(robotChoice != 10 && robotChoice >=1 && robotChoice <=5)
                {
                    if(c.checkEnergyNeeded(robotChoice))
                    {
                            while(true)
                           {
                                System.out.println("Choose the tunnel 0-"+(tunnels-1));
                                tunnelChoice = input.nextInt();
                                if(tunnelChoice >= 0 && tunnelChoice < tunnels )
                                    break;

                           }   

                           while(true)
                           {
                               System.out.println("Choose the room 0-"+(room-1));
                               roomChoice = input.nextInt();
                               if(roomChoice >= 0 && roomChoice < room)
                                   break;

                           } 
                           c.creatRobot((tunnelChoice+(roomChoice*tunnels)),robotChoice);        //ARXIKOPOIHSH ROBOT
                    }
                    else 
                        System.out.println("Not Enough Energy");
                }
                
                 if(robotChoice == 10)
                     break;
                 
                 
                 
            }
            catch(InputMismatchException e)
            {
                 System.out.println("Wrong input ");
            }
            input.nextLine();
        
        }
        
         numOfnewSoldiers = rnd.nextInt((tunnels)+1)*level;     //BOREI NA MHN BEI KAI KANENAS
        
        for(int j = 0; j < numOfnewSoldiers; j++)
        {
             entry  = rnd.nextInt(tunnels) ;
             c.creatSoldier(entry);
        }
      
            
            winner = c.roundtoAct();
            c.printColony();
            
            int k = 0;
            
            if(winner == -1 )
            {
                System.out.println("you loose");
                break;
            }
            else if (winner == 1 && k!=0)
            {
                 System.out.println("you win");
                 break;
            }
            k++;
          
       
            
    }           //end of  big while 
            
    }
    
}
