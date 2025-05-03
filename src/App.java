import java.util.Scanner;
import java.util.Random;

public class App {

    //Not converted to actually run in a GUI window yet. Convert by making the window show text at top with input field at the bottom, remove the console
    //print commands
    private static String playerName;
    private static int maxRooms;
    private static int room;
    private static int health;
    private static int healthPotions;
    private static int armorValue;
    private static int weaponDamage;
    private static int treasureValue;
    private static String randomInput;//Used to determine if player wants to continue or for choices
    private static Random randNum = new Random();
    private static Scanner myScanner = new Scanner(System.in);

    private static void monsterFight(int rmNum) {
        int monsterHealth;
        if(rmNum%10 == 0){//Bosses get much higher health
            monsterHealth = 60+rmNum+randNum.nextInt(40);
        }
        else{
            monsterHealth = 30+rmNum+randNum.nextInt(10);
        }
        int monsterAttack = 5+rmNum+randNum.nextInt(20);
        int combatArmorValue = 0; //Used because sometimes armor gets buffed
        while(monsterHealth > 0 && health > 0){
            if(rmNum%10 == 0){//Every 10th room has a boss
                System.out.println("----------------<[[BOSS FIGHT]>-----------------");
            }
            else{
                System.out.println("----------------<[[COMBAT]]>-----------------");
            }
            System.out.println("You have "+String.valueOf(health)+" health. The beast has "+String.valueOf(monsterHealth)+" health.");
            System.out.println("You currently have "+String.valueOf(healthPotions)+" health potions. Your armor gives you "+String.valueOf(armorValue)+" points of protection, and your weapon deals "+String.valueOf(weaponDamage)+" points of damage.");
            System.out.println("Enter in A to attack. Enter D to defend. Enter P to drink a health potion.");
            combatArmorValue = armorValue;
            randomInput = "";
            while(!(randomInput.equals("A") || randomInput.equals("D") || randomInput.equals("P"))){
                randomInput = myScanner.nextLine();
                switch(randomInput){
                    case "A":
                        System.out.println("You attack the terrible beast!");
                        monsterHealth -= weaponDamage;
                        break;
                    case "D":
                        System.out.println("You brace yourself against the foul beast!");
                        combatArmorValue = combatArmorValue*3;
                        break;
                    case "P":
                        if(healthPotions > 0){
                            System.out.println("You drink a potion, and restore 40 health.");
                            healthPotions -= 1;
                            health += 50;
                            if(health > 100){ //Prevents health from exceeding maximum
                                health = 100;
                            }
                            break;
                        }
                        else{
                            System.out.println("You waste time fumbling around with your bag until you realize you don't have any potions to drink.");
                            break;
                        }
                    default:
                        System.out.println("Please enter a proper value: A to attack, D to defend, P for a health potion.");
                        break;
                }
            }
            if(randNum.nextInt(2) == 0){//0 to 1
                int damageDealt = monsterAttack - combatArmorValue;
                if(damageDealt < 0){
                    damageDealt = 0;
                }
                health -= damageDealt;
                System.out.println("The beast attacks! It deals "+String.valueOf(damageDealt)+" points of damage!");
            }
            else{
                System.out.println("The beast studies you...");
            }
            System.out.println("Enter anything to continue");
            myScanner.nextLine();
        }
        if(health < 0){
            System.out.println("You were defeated...");
        }
        else{
            if(rmNum%10 == 0){//Bosses drop loot
                int bossArmor = room+randNum.nextInt(20);
                int bossWeapon = room+randNum.nextInt(20);
                System.out.println("After you defeat the horrible monster, it seemed that it was guarding something...");
                System.out.println("A weapon is left behind. It looks like it does "+String.valueOf(bossWeapon)+" points of damage. Your weapon does "+String.valueOf(weaponDamage)+" points of damage.");
                System.out.println("Enter Y to equip the new weapon, or N to discard it.");
                randomInput = "";
                while(!(randomInput.equals("Y") || randomInput.equals("N"))){
                    randomInput = myScanner.nextLine();
                    if(randomInput.equals("Y")){
                        System.out.println("You replace your current weapon with the new weapon you found. [Replaced weapon]");
                        weaponDamage = bossWeapon;
                    }
                    else if(randomInput.equals("N")){
                        System.out.println("You ignore the weapon and continue on your way.");
                    }
                    else{
                        System.out.println("[Invalid input. Please enter Y to equip the new weapon or N to discard it.]");
                    }
                }
                System.out.println("A suit of armor is left behind. It looks like it has "+String.valueOf(bossArmor)+" points of protection. Your armor has "+String.valueOf(armorValue)+" points of protection.");
                System.out.println("Enter Y to equip the new armor, or N to discard it.");
                randomInput = "";
                while(!(randomInput.equals("Y") || randomInput.equals("N"))){
                    randomInput = myScanner.nextLine();
                    if(randomInput.equals("Y")){
                        System.out.println("You replace your current armor with the new armor you found. [Replaced armor]");
                        armorValue = bossArmor;
                    }
                    else if(randomInput.equals("N")){
                        System.out.println("You ignore the armor and continue on your way.");
                    }
                    else{
                        System.out.println("[Invalid input. Please enter Y to equip the new armor or N to discard it.]");
                    }
                }
                System.out.println("You find a bottle filled with red liquid and pick it up. [+1 health potion]");
                healthPotions += 1;
                int bossTreasure = randNum.nextInt(1000)+500;
                System.out.println("There was also a pile of treasure in the room, and you loot it all. [+"+String.valueOf(bossTreasure)+" coins worth of treasure]");
                treasureValue += bossTreasure;
            }
            else{
                System.out.println("-----------------------------------------");
                System.out.println("You've managed to defeat the terrible beast...");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        //Setting initial values
        room = 1;
        healthPotions = 0;
        armorValue = 10;
        weaponDamage = 10;
        health = 100;
        treasureValue = 0;

        System.out.println("-----------------------------------------");//Splitter for text
        System.out.println("TREASURES OF THE DUNGEON");
        System.out.println("Coded by 4eyedglobule");
        System.out.println("\nEnter anything to begin");
        randomInput = myScanner.nextLine();
        System.out.println("-----------------------------------------");//Splitter for text
        System.out.println("\nEnter the name of your dungeoneer");
        playerName = myScanner.nextLine();
        System.out.println("-----------------------------------------");//Splitter for text

        while(maxRooms < 20) { //Player decides maximum amount of rooms. Minimum is 20
            try {
                System.out.println("\nHow deep do you wish to delve? (Enter a number, minimum 20)");
                maxRooms = myScanner.nextInt();
                myScanner.nextLine();
                if(maxRooms < 20) {
                    System.out.println("[Input a higher number, minimum is 20]");
                }
            } catch (Exception e) {
                System.out.println("[Invalid input, please try again]");
                myScanner.nextLine();
            }
        }

        System.out.println("-----------------------------------------");//Splitter for text
        System.out.println("\nPrepare to enter in search of treasure and peril, "+playerName+"...");
        System.out.println("\nEnter anything to continue");
        randomInput = myScanner.nextLine();
        while(room < maxRooms+1 && health > 0){
            System.out.println("-------------------ROOM "+String.valueOf(room)+"/"+String.valueOf(maxRooms)+"----------------------");//Splitter for text
            System.out.println("You have "+String.valueOf(health)+" health. Your armor gives you "+String.valueOf(armorValue)+" points of protection, and your weapon deals "+String.valueOf(weaponDamage)+" points of damage.");
            System.out.println("You currently have "+String.valueOf(healthPotions)+" health potions.");
            System.out.println("You currently have "+String.valueOf(treasureValue)+" coins worth of treasure looted from the dungeon.");
            if(room%10 == 0){//Every 10th room has bossfight
                System.out.println("The next room you enter is significantly more imposing than the other rooms you've been through so far.");
                System.out.println("You get the feeling that a terrible monster lays in wait. You brace yourself as you enter...");
                System.out.println("Enter anything to fight!");
                randomInput = myScanner.nextLine();
                monsterFight(room);
            }
            else{
                switch(randNum.nextInt(6)) { //Picks random number from 0 to 5
                    case 0://Nothing
                        System.out.println("You enter a completely empty room. All that greets you are cracked walls and broken columns. ");
                        break;
                    case 1://Potion
                        System.out.println("You enter a foul-smelling room. The stench makes your eyes water. In the corner, there is a trickle of a strange red liquid flowing from a crack in the ceiling. ");
                        System.out.println("According to what you read before you entered, that liquid miraculously heals your wounds when drunk. You bottle some. [+1 health potion] ");
                        healthPotions += 1;
                        break;
                    case 2://Monster fight
                        System.out.println("You enter a room and come face to face with a terrible beast! Seems like you need to battle it to continue.");
                        System.out.println("Enter anything to fight!");
                        randomInput = myScanner.nextLine();
                        monsterFight(room);
                        break;
                    case 3://New armor
                        int newArmor = room+randNum.nextInt(20);
                        System.out.println("You enter a room, with a skeleton in the center of it, long dead. Its equipment is in tatters, but it still wears armor that still seems to be usable.");
                        System.out.println("The armor looks like it has "+String.valueOf(newArmor)+" points of protection. Your armor has "+String.valueOf(armorValue)+" points of protection.");
                        System.out.println("Enter Y to equip the new armor, or N to discard it.");
                        randomInput = "";
                        while(!(randomInput.equals("Y") || randomInput.equals("N"))){
                            randomInput = myScanner.nextLine();
                            if(randomInput.equals("Y")){
                                System.out.println("You replace your current armor with the new armor you found. [Replaced armor]");
                                armorValue = newArmor;
                            }
                            else if(randomInput.equals("N")){
                                System.out.println("You ignore the armor and continue on your way.");
                            }
                            else{
                                System.out.println("[Invalid input. Please enter Y to equip the new armor or N to discard it.]");
                            }
                        }
                        break;
                    case 4://New weapon
                        int newWeapon = room+randNum.nextInt(20);
                        System.out.println("You enter a room with a weapon embedded in the ground. The weapon seems to still be usable.");
                        System.out.println("The weapon looks like it does "+String.valueOf(newWeapon)+" points of damage. Your weapon does "+String.valueOf(weaponDamage)+" points of damage.");
                        System.out.println("Enter Y to equip the new weapon, or N to discard it.");
                        randomInput = "";
                        while(!(randomInput.equals("Y") || randomInput.equals("N"))){
                            randomInput = myScanner.nextLine();
                            if(randomInput.equals("Y")){
                                System.out.println("You replace your current weapon with the new weapon you found. [Replaced weapon]");
                                weaponDamage = newWeapon;
                            }
                            else if(randomInput.equals("N")){
                                System.out.println("You ignore the weapon and continue on your way.");
                            }
                            else{
                                System.out.println("[Invalid input. Please enter Y to equip the new weapon or N to discard it.]");
                            }
                        }
                        break;
                    case 5://Find treasures
                        int foundTreasureVal = randNum.nextInt(500)+250;
                        System.out.println("You enter a room with a few piles of treasure. You loot all the treasure you can see. [+"+String.valueOf(foundTreasureVal)+" coins worth of treasure]");
                        treasureValue += foundTreasureVal;
                        break;
                }
            }
            System.out.println("\nEnter anything to continue...");
            randomInput = myScanner.nextLine();
            room += 1;
        }
        myScanner.close();
        if(health < 0){
            System.out.println("You have perished in the dungeon in search of riches, like many before you...");
            System.out.println("==GAME OVER==");
        }
        else{
            System.out.println("You have returned from the dungeon alive...");
            System.out.println("Your name, "+playerName+", was told in the stories of the rare successful dungeoneers, amongst others.");
            System.out.println("With the treasure and riches you gathered from the dungeon, you live in luxury for the rest of your life.");
            System.out.println("==END==");
            System.out.println("Total treasure gathered: "+String.valueOf(treasureValue)+" coins worth");
            System.out.println("Rooms traversed: "+String.valueOf(maxRooms)+" rooms");
        }

    }
}
