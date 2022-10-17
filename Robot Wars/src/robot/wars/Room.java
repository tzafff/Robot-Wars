/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot.wars;

import java.util.ArrayList;

/**
 *
 * @author chris
 */
public class Room {
    
    private Room entry;
    private Room exit;
    private int id;
    private Robot rb = null;
    private ArrayList<Soldier> soldiers = new ArrayList<>();
    
    public Room(Room entry,int idRoom)
    {
        this.entry = entry;
        this.id = idRoom;
    }
    
    public void setExit(Room exit)
    {
         this.exit = exit;
    }
    
    
    public Room getExit()
    {
        return exit;
    }
    
    public int getId()
    {
        return this.id;
    }
    
 
    
    public void addSoldier(Soldier s)
    {
        soldiers.add(s);
    }
    
    
    public Soldier subSoldier(int idSoldier)
    {
       for(int i = 0; i < soldiers.size(); i++)
       {
           Soldier sd = soldiers.get(i);
           if(sd.getSoldierId() == idSoldier)
           {
                soldiers.remove(sd);
                return sd;
           }
       } 

        return null;
        
    }
    
    
    public void checkSoldierStamina()
    {
        for(int i = numSoldiers()-1; i >= 0; i--)            //ANAPODA GT NA GINETE SWSTA TO REMOVE 
        {
            if(soldiers.get(i).getStamina() <= 0 )
                soldiers.remove(i);
       }
    }
     
    
     public void checkRobotStamina()
     {
        if(rb == null)  //AN DEN UPARXEI ROBOT STO ROOM 
        {
            return ;
        }   
        else{
            if(rb.getStamina() <= 0)
            {
                rb = null;          //DIWXNW TO ROBOT GT PETHANE 
            }
        }
     }
    
    public void addRobot(Robot r)
    {
         this.rb = r;
         System.out.println(rb.getType());
    }
     
     
     
     
     public Soldier getSoldier(int i)
     {     
         return soldiers.get(i);
     }
     
     public boolean hasRobot()
     {
         if(rb == null)
             return false;
         else
             return true;
     }
     
     public Robot getRobot()
     {
         return this.rb;
     }
     
     
    public int numSoldiers()
    {
        return soldiers.size();
    }
    
    
    
}
