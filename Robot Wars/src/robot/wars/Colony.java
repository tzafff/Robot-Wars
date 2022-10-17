/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot.wars;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author chris
 */
public class Colony {
    
    private int totalEnergy;
    private Room[] entryRooms;
    private int numTunnel;
    private int tunnelLength;
    private MasterRoom master;
    private int round;
    
    
    
    
    public Colony(int tunnels, int rooms, int initEnergy)
    {
        this.totalEnergy = initEnergy;
        this.master = new MasterRoom(null,-100);
                                
        
        entryRooms = new Room[rooms];
        this.round = 0;
        for(int i = 0; i< tunnels; i++ )
            this.entryRooms[i] = new Room(null,i);
        Room tempRoom = null;
        Room prev;
        
        
        numTunnel = tunnels;
        tunnelLength = rooms;
        
         for(int i = 0; i< tunnels; i++ )
        {
            prev = entryRooms[i];
            for(int j = 0; j < rooms-1; j++)
            {
                
                tempRoom = new Room(prev,i+(numTunnel*(j+1)));             
                prev.setExit(tempRoom);
                prev = tempRoom;                
            }
            tempRoom.setExit(master);
        }
        
        
        System.out.println("Colony made with "+totalEnergy+" energy "+"and "+tunnels+","+rooms+" tunnels,rooms");
    }
    
    
    
    public int getEnergy()
    {
        return this.totalEnergy;
    }
    
    public void reduceTotalEnergy(int i)
    {
        this.totalEnergy -= i;
    }
    
    public void increaseEnergy(int i)
    {
        this.totalEnergy += i;
    }
    
     public int numTunnels()
    {
        return this.numTunnel;
    }
     
    public int tunnelLength()
    {
        return this.tunnelLength;
    } 
    
    public MasterRoom getMasterRoom()
    {
        return this.master;
    }
    
    public Room getRoom(int idRoom)
    {
        if((idRoom >= (numTunnel*tunnelLength)) || idRoom == -100)  // AN EXOUME PAREI ID MEGALUTERO APO 9 AN numTunnel kai tunnelLENGHT EINAI 3X3
        {
             return master;
        }
        
        if(idRoom < numTunnel)
        {
            return entryRooms[idRoom];
        }
            
        if(idRoom%numTunnel == 0 )          //PRWTO TUNNEL
        {
           int steps = idRoom/numTunnel; 
                                                     //px exei dwsei idRoom 6 tha ginei 6/3=2 ara prepei na kanei 2 steps
           Room temp = entryRooms[0];
           
           for(int i = 0; i<steps; i++)
           {
               temp = temp.getExit();
               
           }
        
           return temp;
        }
        else if(idRoom%numTunnel == 1 )     //DEUTERO TUNNEL
        {

           int steps = idRoom/numTunnel; 
                                                     
           Room temp = entryRooms[1];
           
           for(int i = 0; i<steps; i++)
           {
               temp = temp.getExit();
               
           }
           return temp;
        }
        else 
        {

           int steps = idRoom/numTunnel;    //TRITO TUNNEL
                                                     
           Room temp = entryRooms[2];
           
           for(int i = 0; i<steps; i++)
           {
               temp = temp.getExit();
               
           }

           return temp;
        }
        
        
    }
    
    public boolean checkEnergyNeeded(int idType)
    {
       
        if(idType == 1 || idType == 2) //ENERGY K ARMOR ROBOTS
        {
           return (totalEnergy>=3);
        }
        if(idType == 3 || idType == 4 || idType == 5) // OLA TA ALLA 
        {
             return (totalEnergy>=4);
        }
        
        return false;
    }
    
    public boolean creatRobot(int idRoom,int idType)
    {
        
         Room r = getRoom(idRoom);
         if(r.hasRobot())
         {
             System.out.println("Room has already robot here");
             return false;
         }
        
        if(idType == 0)
        {
            MasterRobot s = new MasterRobot(idRoom,1);
           
            r.addRobot(s);
        }
        

        if(idType == 1)
        {
            EnergyProducerRobot s = new EnergyProducerRobot(idRoom,1);
            
            r.addRobot(s);
            reduceTotalEnergy(s.getEnergyNeeded());
        }
        if(idType == 2)
        {
            ArmoredRobot s = new ArmoredRobot(idRoom,4);
           
            r.addRobot(s);
             reduceTotalEnergy(s.getEnergyNeeded());
        }
        if(idType == 3)
        {
            FighterRobot s = new FighterRobot(idRoom,1);
           
            r.addRobot(s);
             reduceTotalEnergy(s.getEnergyNeeded());
        }
        if(idType == 4)
        {
            ShooterRobot s = new ShooterRobot(idRoom,1);
           
            r.addRobot(s);
             reduceTotalEnergy(s.getEnergyNeeded());
        }
          if(idType == 5)
        {
            FireRobot s = new FireRobot(idRoom,1);
            
            r.addRobot(s);
             reduceTotalEnergy(s.getEnergyNeeded());
        }
          
         
          return true;
    }
    
    
    public void creatSoldier(int idRoom)
    {
        Soldier s = new Soldier(idRoom,3);
       
        s.setSoldierRound(this.round);
        Room r = getRoom(idRoom);
        r.addSoldier(s);
    }
    
    public void addSoldier(int idRoom, Soldier s)
    {
        
        Room rm;
        rm = getRoom(idRoom);
        
        rm.addSoldier(s);
    }
    
    public Soldier subSoldier(int idRoom,int idSoldier)
    {
        Room rm;
        rm = getRoom(idRoom);
        
        return rm.subSoldier(idSoldier);
        
    }
   
    public int roundtoAct()
    {
             
        if(round == 0)
        {
             creatRobot(-100, 0);    // arxikopoihsh tou master Robot kai topothethsh sto dwmatio tou 
        }

        Soldier soldierToAct;

        for(int i = 0; i < numTunnel; i++)
        {
            for(int j = 0; j < tunnelLength; j++)
            {
                Room presentRoom;
                int idOfpresentRoom = i+(numTunnel*j);
                
                presentRoom = getRoom(idOfpresentRoom);
            }
        
        }
        
        
        for(int i = 0; i < numTunnel; i++)
        {
            for(int j = 0; j < tunnelLength; j++)
            {
      //         System.out.println("FOR  "+i+" "+j);
               
               Room presentRoom;
               int idOfpresentRoom = i+(numTunnel*j);
               presentRoom = getRoom(idOfpresentRoom);
               
               
               for(int k = (presentRoom.numSoldiers()-1); k >= 0; k--)                    //**APO TON TELEUTAIO STO PRWTO GT GINETAI REMOVE OTAN FEUGEI KAPOIOS
               {

                 soldierToAct = presentRoom.getSoldier(k);
                 if(soldierToAct.getRound() != this.round)  //TSEKAREI AN O SOLDIER EXEI HDH PAIKSEI(ACT)
                 {
                    soldierToAct.act(this);
                    presentRoom.checkRobotStamina();
                 }
                 
               }
               
               if(presentRoom.hasRobot())
               {
                   Robot robotToAct;
                   robotToAct = presentRoom.getRobot();
                   robotToAct.act(this);
                   if(presentRoom.numSoldiers() > 0 )
                   {
                     presentRoom.checkSoldierStamina();
                   }
               }
            }
        }   
        if(master.numSoldiers() > 0 )
        {
            this.totalEnergy++; 
            this.round++;
            return -1; //BHKE STRATIWTHS STO MASTER ROOM
        }
         
        this.totalEnergy++; 
        this.round++;
        if(round > 0)
            return gameWinner();  
        
        return 0; 
    }
    
    public int gameWinner()             //0 AN DEN EXOUME NIKITH 1 AN EXOUME NIKITH ROBOT KAI -1 AN NIKISAN OI STRATIWTES
    {
        //ELEGXW AN KERDISAN OI SOLDIERS
        Robot rb = master.getRobot();
        if(rb.getStamina() <= 0)            
        {
            return -1;                  
        }
        
        //ELEGXW AN KERDISAN TA ROBOT
        for(int i = 0; i < numTunnel*tunnelLength; i++)
        {
            Room presentRoom;
            presentRoom = getRoom(i);
            if(presentRoom.numSoldiers() != 0) 
            {
                return 0;                       //elegxw an se ola ta dwmatia uparxoun stratiwtes
            }     
        }
        
        if(master.numSoldiers() > 0 )
            return 0;
        
        
        return 1;
        
        
    }
    
    
    public void saveColony(int level) throws IOException
    {
        BufferedWriter buffer = new BufferedWriter(new FileWriter("save.txt"));
        
        buffer.write(level+ "\n");
        buffer.write(this.numTunnel+ "\n");
        buffer.write(this.tunnelLength+ "\n");
        buffer.write(this.round+ "\n");
        
        Room presentRoom;
        Soldier presentSol = null;
        Robot presentR = null;
         
        for(int i = 0; i < (numTunnel*tunnelLength); i++)
        {
            presentRoom = getRoom(i);
            buffer.write(i + "\n");
            buffer.write(presentRoom.numSoldiers() + "\n");
            
           
            
            for(int j = 0; j < presentRoom.numSoldiers(); j++)
            {
                presentSol = presentRoom.getSoldier(j);             //thesh stratiwth 
                buffer.write(presentSol.getSoldierId()+ "\n");
                buffer.write(presentSol.getStamina()+ "\n");
                buffer.write(presentSol.getRound()+ "\n");
            }
            
            
            buffer.write(presentRoom.hasRobot()+ "\n");
            if(presentRoom.hasRobot())
            {
                presentR = presentRoom.getRobot();
                buffer.write(presentR.getStamina()+ "\n");
                buffer.write(presentR.getType()+ "\n");
            }
            
        }
        
        buffer.close();
    }
    
    
    public void printColony()
    {
        System.out.println("  ");
        System.out.println("  ");
        if(this.round == 0 )
        {
            System.out.println("******** COLONY ********");
        }
        else
        System.out.println("****** ROUND "+(this.round-1)+"  *******");
        
        System.out.println("  ");
        System.out.println("Total energy of colony "+totalEnergy);
        for(int i = 0; i < numTunnel; i++)
        {
            System.out.println("Tunnel:"+ i );
            for(int j = 0; j < tunnelLength; j++)
            {
               System.out.println("   Room "+j+" has");
               
               Room presentRoom;
               int idOfpresentRoom = i+(numTunnel*j);
               presentRoom = getRoom(idOfpresentRoom);
               
               
               System.out.println("      Number of soldiers "+presentRoom.numSoldiers());
               Soldier soldierToPrint;
               for(int k = 0; k < presentRoom.numSoldiers(); k++)
               {
                 soldierToPrint = presentRoom.getSoldier(k);
                 System.out.println("      Soldier "+soldierToPrint.getSoldierId()+" stamina "+soldierToPrint.getStamina()+" round of soldier "+soldierToPrint.getRound());
               }
               
               if(presentRoom.hasRobot())
               {
                   Robot robotToPrint;
                   robotToPrint = presentRoom.getRobot();
                   System.out.println("      "+robotToPrint.getType()+" with stamina "+robotToPrint.getStamina());
               }
               else
                   System.out.println("      Room has no robot");
            }
        }
        
        //  Print tou master Room
        System.out.println("   Master Room  has");
        Room presentRoom;
        
        presentRoom = getRoom(-100);

        System.out.println("      Number of soldiers "+presentRoom.numSoldiers());
        Soldier soldierToPrint;
        for(int k = 0; k < presentRoom.numSoldiers(); k++)
        {
          soldierToPrint = presentRoom.getSoldier(k);
          System.out.println("      Soldier "+soldierToPrint.getSoldierId()+" stamina "+soldierToPrint.getStamina()+" round of soldier "+soldierToPrint.getRound());
        }

        if(presentRoom.hasRobot())
        {
            Robot robotToPrint;
            robotToPrint = presentRoom.getRobot();
            System.out.println("      "+robotToPrint.getType()+" with stamina "+robotToPrint.getStamina());
        }
        else
            System.out.println("      Room has no robot");
        
        //  Print tou master Room
        
     }

     
}
