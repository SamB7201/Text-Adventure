
package textgame;
import java.util.Scanner;

/**
 *
 * @author Sam
 */
public class TextGame {
    static Scanner input = new Scanner(System.in);
    
    static String playerName = "";
    static String role = "";
    static String skill = "";
    static int playerAttack = 0;
    static int playerHealth = 0;
    static int SkillPoints = 50;
    static int cost = 0;

    static String monsterName = "";
    static int monsterHealth = 0;
    static int monsterAttack = 0;
    static String monsterSkill = "";

    static String currentTest = "Beginner";

    static boolean room1_clear = false;
    static boolean room2_clear = false;
    static boolean room3_clear = false;

    public static void main(String[] args)
    {
        StartGame();

        for (int Wave = 1; Wave <= 10; Wave++)
        {
            WaveAttack(Wave);
        }

        for (int run = 1; run <= 3; run++)
        {
            RoomSelect(room1_clear, room2_clear, room3_clear);
        }
        
        System.out.println("\n*After exiting the test room text appears before your eyes.*" +
            "\n" + currentTest + " test completed prepare for 'The King'.");
        
        TheKing();

        EndGame1();

        input.nextLine();
    }

    private static void StartGame()
    {
        int tankHealth = 200;
        int tankAttack = 15;
        int tankCost = 10;

        int assualtHealth = 100;
        int assaultAttack = 20;
        int assualtCost = 10;

        int mageHealth = 75;
        int mageAttack = 30;
        int mageCost = 15;

        System.out.println("Dungeon Master: Welcome Adventurer what is your Name?");
        playerName = input.nextLine();

        System.out.println("\nDungeon Master: " + playerName + " what is your role?");
        do
        {
            System.out.println("\nTank - Health: " + tankHealth+ ", Attack: " + tankAttack + "\n" +
                "Assualt - Health: " + assualtHealth + ", Attack: " + assaultAttack + "\n" +
                "Mage - Health: " + mageHealth + ", Attack: " + mageAttack);

            role = input.nextLine().toLowerCase();
            
            if (null == role)
            {
                System.out.println(role + " does not exist choose one provided!");
                playerAttack = 0;
                playerHealth = 0;
            }
            else switch (role) {
                case "tank":
                    playerAttack = tankAttack;
                    playerHealth = tankHealth;
                    skill = "Demolition";
                    cost = tankCost;
                    break;
                case "assualt":
                    playerAttack = assaultAttack;
                    playerHealth = assualtHealth;
                    skill = "Quick Silver";
                    cost = assualtCost;
                    break;
                case "mage":
                    playerAttack = mageAttack;
                    playerHealth = mageHealth;
                    SkillPoints = 100;
                    skill = "Mirror";
                    cost = mageCost;
                    break;
                default:
                    System.out.println(role + " does not exist choose one provided!");
                    playerAttack = 0;
                    playerHealth = 0;
                    break;
            }

        } while (playerAttack == 0 || playerHealth == 0);

        System.out.println("\nDungeon Master: " + playerName + " you have chosen " + role + " with " + playerAttack + " Attack power, " + playerHealth + " Health," +
            " and " + SkillPoints + " Skill Points");
        System.out.println("Dungeon Master: Good Luck in the dungeoun!... You'll need it.");
        System.out.println("*The large doors closes behind you as you enter a large circular room with blue flames around the edges lighting the room,"
                + "and then suddenly text appears before your eyes*");
    }

    //-----------------------------Fighting Mechanics Section---------------------------------------------------
    
    private static void Fighting()
    {
        while (playerHealth > 0 && monsterHealth > 0)
        {
            System.out.println("\n" + playerName + ":" + playerHealth + " HP, " + playerAttack + " ATK, " + SkillPoints + " SP");
            System.out.println(monsterName + ":" + monsterHealth + " HP, " + monsterAttack + " ATK");

            System.out.println("\nWill you 'ATK', 'DEF', or use '" + skill + "' for " + cost + " SP");
            String Action = input.nextLine().toLowerCase();

            if (null == role)
            {
                System.out.println("You stood there like an idiot and got attacked");
                playerHealth -= monsterAttack;
            }

            else switch (role) {
                case "tank":
                    if (Action.equals("atk"))
                    {
                        System.out.println("You and the " + monsterName + " Attacked each other");
                        monsterHealth -= playerAttack;
                        if (monsterHealth > 0)
                        {
                            playerHealth -= monsterAttack;
                        }
                        SkillPoints += 5;
                    }
                    else if (Action.equals("def"))
                    {
                        System.out.println("You Defeneded against the " + monsterName + " Attack");
                        playerHealth -= (monsterAttack / 4);
                        SkillPoints += 10;
                    }
                    else if (Action.equals(skill.toLowerCase()))
                    {
                        if (SkillPoints > cost)
                        {
                            System.out.println("You used " + skill + " against the " + monsterName + " damaging and dazing them");
                            monsterHealth -= 65;
                            SkillPoints -= cost;
                        }
                        else
                        {
                            System.out.println("You do not have enough SkillPoints to use " + skill + " and was Attacked off guard");
                            playerHealth -= monsterAttack;
                        }
                    }  
                    break;
                case "assualt":
                    if (Action.equals("atk"))
                    {
                        System.out.println("You and the " + monsterName + " Attacked each other");
                        monsterHealth -= playerAttack;
                        if (monsterHealth > 0)
                        {
                            playerHealth -= monsterAttack;
                        }
                        SkillPoints += 5;
                    }
                    else if (Action.equals("def"))
                    {
                        System.out.println("You Defeneded against the " + monsterName + " Attack");
                        playerHealth -= (monsterAttack / 2);
                        SkillPoints += 10;
                    }
                    else if (Action.equals(skill.toLowerCase()))
                    {
                        if (SkillPoints > cost)
                        {
                            System.out.println("You used " + skill + " evading the attack and sending Daggers at the " + monsterName);
                            monsterHealth -= 45;
                            SkillPoints -= cost;
                        }
                        else
                        {
                            System.out.println("You do not have enough SkillPoints to use " + skill + " and was Attacked off guard");
                            playerHealth -= monsterAttack;
                        }
                    }   break;
                case "mage":
                    if (Action.equals("atk"))
                    {
                        System.out.println("You and the " + monsterName + " Attacked each other");
                        monsterHealth -= playerAttack;
                        if (monsterHealth > 0)
                        {
                            playerHealth -= monsterAttack;
                        }
                        SkillPoints += 10;
                    }
                    else if (Action.equals("def"))
                    {
                        System.out.println("You Defeneded against the " + monsterName + " Attack");
                        playerHealth -= (monsterAttack / 2);
                        SkillPoints += 20;
                    }
                    else if (Action.equals(skill.toLowerCase()))
                    {
                        if (SkillPoints > cost)
                        {
                            System.out.println("You used " + skill + " blocking the Attack and reflecting Quadruple the damage at the " + monsterName);
                            monsterHealth -= (monsterAttack * 4);
                            SkillPoints -= cost;
                        }
                        else
                        {
                            System.out.println("You do not have enough SkillPoints to use " + skill + " and was Attacked off guard");
                            playerHealth -= monsterAttack;
                        }
                    }   break;
                default:
                    System.out.println("You stood there like an idiot and got attacked");
                    playerHealth -= monsterAttack;
                    break;
            }

        }

        if (playerHealth <= 0 && !currentTest.equals("Strength"))
        {
            System.out.println(playerName + " you have fallen to the Dungeon. Press ENTER to restart.");
            input.nextLine();
            System.exit(0);
        }
        
        else if (playerHealth <= 0 && currentTest.equals("Strength"))
        {
            System.out.println("\nIs that all you amounted to? Jeez and here I was ready for more fun than this."
                    + "\n*You have been crushed by the Strength of the Lord of Destruction. Press ENTER to restart.*");
            input.nextLine();
            System.exit(0);
        }

        else if (monsterHealth <= 0)
        {
            System.out.println(monsterName + " has been slain upgrading stats");
            playerHealth += 35;
            playerAttack += 5;
        }
    }

    private static void BossFight()
    {
        while (playerHealth > 0 && monsterHealth > 0)
        {
            int BossMove = (1+(int)(Math.random()*3));

            System.out.println(playerName + ":" + playerHealth + " HP, " + playerAttack + " ATK, " + SkillPoints + " SP");
            System.out.println(monsterName + ":" + monsterHealth + " HP, " + monsterAttack + " ATK, " + monsterSkill + " SKL");

            System.out.println("\nWill you 'ATK', 'DEF', or use '" + skill + "' for " + cost + " SP");
            String Action = input.nextLine().toLowerCase();

            if (null == role)
            {
                System.out.println("Dont know how but you dont have a role");
                playerHealth -= monsterAttack;
                SkillPoints += 10;
            }

            else switch (role) {
                case "tank":
                    if (Action.equals("atk"))
                    {
                        if (BossMove == 1)
                        {
                            System.out.println("You and the " + monsterName + " Attacked each other");
                            monsterHealth -= playerAttack;
                            if (monsterHealth > 0)
                            {
                                playerHealth -= monsterAttack;
                            }
                            SkillPoints += 5;
                        }
                        else if(BossMove == 2)
                        {
                            System.out.println("The " + monsterName + " Defended against your Attack");
                            monsterHealth -= (playerAttack / 3);
                            SkillPoints += 5;
                        }
                        else if(BossMove == 3 && monsterSkill.equals("Drain"))
                        {
                            System.out.println("The " + monsterName + " used " + monsterSkill + " while you Attacked");
                            monsterHealth -= playerAttack;
                            if (monsterHealth > 0)
                            {
                                SkillPoints -= 30;
                                if(SkillPoints < 0){
                                    SkillPoints = 0;
                                }
                            }
                        }
                        else if(BossMove == 3 && monsterSkill.equals("Shock Wave"))
                        {
                            System.out.println("The " + monsterName + " used " + monsterSkill + " while you Attacked causeing you to stagger and deal only Half Damage");
                            if (monsterHealth > 0)
                            {
                                monsterHealth -= (playerAttack / 2);
                                playerHealth -= 50;
                            }
                        }
                        else if(BossMove == 3 && monsterSkill.equals("Sticky Thread"))
                        {
                            System.out.println("The " + monsterName + " used " + monsterSkill + " while you tried to Attack but you were stopped.");
                        }
                    }
                    else if (Action.equals("def"))
                    {
                        if (BossMove == 1 || BossMove == 2)
                        {
                            System.out.println("You Defeneded against the " + monsterName + " Attack");
                            playerHealth -= (monsterAttack / 4);
                            SkillPoints += 10;
                        }
                        else if(BossMove == 3 && monsterSkill.equals("Drain"))
                        {
                            System.out.println("You Defended against the " + monsterName + "'s " + monsterSkill);
                            SkillPoints -= (30 / 2);
                            if(SkillPoints < 0){
                                SkillPoints = 0;
                            }
                        }
                        else if(BossMove == 3 && monsterSkill.equals("Shock Wave"))
                        {
                            System.out.println("You Defended against the " + monsterName + "'s " + monsterSkill + " with all your strength negating it.");
                        }
                        else if(BossMove == 3 && monsterSkill.equals("Sticky Thread"))
                        {
                            System.out.println("You tried to Defend against the " + monsterName + "'s " + monsterSkill + " but got Attacked from behind while stopped by the Thread");
                            playerHealth -= monsterAttack;
                        }
                    }
                    else if (Action.equals(skill.toLowerCase()))
                    {
                        if (SkillPoints > cost)
                        {
                            System.out.println("You used " + skill + " against the " + monsterName + " damaging and dazing them");
                            monsterHealth -= 65;
                            SkillPoints -= cost;
                        }
                        else
                        {
                            System.out.println("You do not have enough SkillPoints to use " + skill + " and was Attacked off guard");
                            playerHealth -= monsterAttack;
                            SkillPoints += 5;
                        }
                    }   break;
                    
                    
                    
                case "assualt":
                    if (Action.equals("atk"))
                    {
                        if (BossMove == 1)
                        {
                            System.out.println("You and the " + monsterName + " Attacked each other");
                            monsterHealth -= playerAttack;
                            if (monsterHealth > 0)
                            {
                                playerHealth -= monsterAttack;
                            }
                            SkillPoints += 5;
                        }
                        else if (BossMove == 2)
                        {
                            System.out.println("The " + monsterName + " Defended against your Attack");
                            monsterHealth -= (playerAttack / 3);
                            SkillPoints += 5;
                        }
                        else if (BossMove == 3 && monsterSkill.equals("Drain"))
                        {
                            System.out.println("The " + monsterName + " used " + monsterSkill + " while you Attacked");
                            monsterHealth -= playerAttack;
                            if (monsterHealth > 0)
                            {
                                SkillPoints -= 30;
                                if(SkillPoints < 0){
                                    SkillPoints = 0;
                                }
                            }
                        }
                        else if(BossMove == 3 && monsterSkill.equals("Shock Wave"))
                        {
                            System.out.println("The " + monsterName + " used " + monsterSkill + " while you Attacked causeing you to stagger and deal only Half Damage");
                            if (monsterHealth > 0)
                            {
                                monsterHealth -= (playerAttack / 2);
                                playerHealth -= 50;
                            }
                        }
                        else if(BossMove == 3 && monsterSkill.equals("Sticky Thread"))
                        {
                            System.out.println("The " + monsterName + " tried and failed to stop your Attack using " + monsterSkill);
                            if (monsterHealth > 0)
                            {
                                monsterHealth -= playerAttack;
                            }
                        }
                    }
                    else if (Action.equals("def"))
                    {
                        if (BossMove == 1 || BossMove == 2)
                        {
                            System.out.println("You Defeneded against the " + monsterName + " Attack");
                            playerHealth -= (monsterAttack / 2);
                            SkillPoints += 15;
                        }
                        else if (BossMove == 3 && monsterSkill.equals("Drain"))
                        {
                            System.out.println("You Defended against the " + monsterName + "'s " + monsterSkill);
                            SkillPoints -= (30 / 2);
                            if(SkillPoints < 0){
                                SkillPoints = 0;
                            }
                        }
                        else if(BossMove == 3 && monsterSkill.equals("Shock Wave"))
                        {
                            System.out.println("You Defended against the " + monsterName + "'s " + monsterSkill);
                            playerHealth -= (50 / 2);
                        }
                        else if(BossMove == 3 && monsterSkill.equals("Sticky Thread"))
                        {
                            System.out.println("You Defend against the " + monsterName + "'s " + monsterSkill + " and slipped away just in time.");
                        }
                    }
                    else if (Action.equals(skill.toLowerCase()))
                    {
                        if (SkillPoints > cost)
                        {
                            System.out.println("You used " + skill + " evading the attack and throwing Daggers at the " + monsterName);
                            monsterHealth -= 40;
                            SkillPoints -= cost;
                        }
                        else
                        {
                            System.out.println("You do not have enough SkillPoints to use " + skill + " and was Attacked of guard");
                            playerHealth -= monsterAttack;
                            SkillPoints += 5;
                        }
                    }   break;
                    
                    
                    
                case "mage":
                    if (Action.equals("atk"))
                    {
                        if (BossMove == 1)
                        {
                            System.out.println("You and the " + monsterName + " Attacked each other");
                            monsterHealth -= playerAttack;
                            if (monsterHealth > 0)
                            {
                                playerHealth -= monsterAttack;
                            }
                            SkillPoints += 10;
                        }
                        else if (BossMove == 2)
                        {
                            System.out.println("The " + monsterName + " Defended against your Attack");
                            monsterHealth -= (playerAttack / 3);
                            SkillPoints += 10;
                        }
                        else if (BossMove == 3 && monsterSkill.equals("Drain"))
                        {
                            System.out.println("The " + monsterName + " tried to use " + monsterSkill + " while you Attacked but Failed");
                            monsterHealth -= playerAttack;
                        }
                        else if(BossMove == 3 && monsterSkill.equals("Shock Wave"))
                        {
                            System.out.println("The " + monsterName + " used " + monsterSkill + " while you Attacked causeing you to stagger and deal only Half Damage");
                            if (monsterHealth > 0)
                            {
                                monsterHealth -= (playerAttack / 2);
                                playerHealth -= 50;
                            }
                        }
                        else if(BossMove == 3 && monsterSkill.equals("Sticky Thread"))
                        {
                            System.out.println("The " + monsterName + " used " + monsterSkill + " while you tried to Attack but you were stopped.");
                        }
                    }
                    else if (Action.equals("def"))
                    {
                        if (BossMove == 1 || BossMove == 2)
                        {
                            System.out.println("You Defeneded against the " + monsterName + " Attack");
                            playerHealth -= (monsterAttack / 2);
                            SkillPoints += 20;
                        }
                        else if (BossMove == 3 && monsterSkill.equals("Drain"))
                        {
                            System.out.println("You Defended against the " + monsterName + "'s " + monsterSkill + " using a counter spell. SP +30");
                            SkillPoints += 30;
                        }
                        
                        else if(BossMove == 3 && monsterSkill.equals("Shock Wave"))
                        {
                            System.out.println("You Defended against the " + monsterName + "'s " + monsterSkill);
                            playerHealth -= (50 / 2);
                        }
                        else if(BossMove == 3 && monsterSkill.equals("Sticky Thread"))
                        {
                            System.out.println("You tried to Defend against the " + monsterName + "'s " + monsterSkill + " but got Attacked from behind while stopped by the Thread");
                            playerHealth -= monsterAttack;
                        }
                    }
                    else if (Action.equals(skill.toLowerCase()))
                    {
                        if (SkillPoints > cost)
                        {
                            System.out.println("You used " + skill + " blocking the Attack and reflecting Quadruple the damage at the " + monsterName);
                            monsterHealth -= (monsterAttack * 4);
                            SkillPoints -= cost;
                        }
                        else
                        {
                            System.out.println("You do not have enough SkillPoints to use " + skill + " and was Attacked of guard");
                            playerHealth -= monsterAttack;
                            SkillPoints += 10;
                        }
                    }   break;
                default:
                    System.out.println("You stood there like an idiot and got attacked");
                    playerHealth -= monsterAttack;
                    SkillPoints += 10;
                    break;
            }

        }

        if (playerHealth <= 0)
        {
            System.out.println(playerName + " you have fallen to the Dungeon. Press ENTER to restart.");
            input.nextLine();
            System.exit(0);
        }

        else if (monsterHealth <= 0)
        {
            System.out.println(monsterName + " has been slain upgrading stats");
            playerHealth += 120;
            playerAttack += 20;
            SkillPoints += 30;
        }
    }

    //-----------------------------Starting Section-------------------------------------------------------------
    
    private static void WaveAttack(int wave)
    {
        switch (wave)
        {
            case 1:
                monsterName = "Goblin";
                monsterHealth = 30;
                monsterAttack = 5;

                System.out.println("You have entered the Test of the Hero. Clear the Tests to prove yourself!");
                System.out.println("\nA " + monsterName + " is approaching, Defend yourself " + playerName + "!");

                Fighting();
                break;

            case 2:
                monsterName = "Skeleton Soldier";
                monsterHealth = 40;
                monsterAttack = 7;

                System.out.println("A " + monsterName + " is approaching, Defend yourself " + playerName + "!");

                Fighting();
                break;

            case 3:
                monsterName = "Cave Spider";
                monsterHealth = 45;
                monsterAttack = 10;

                System.out.println("A " + monsterName + " is approaching, Defend yourself " + playerName + "!");

                Fighting();
                break;

            case 4:
                monsterName = "Black Serpant";
                monsterHealth = 55;
                monsterAttack = 15;

                System.out.println("A " + monsterName + " is approaching, Defend yourself " + playerName + "!");

                Fighting();
                break;

            case 5:
                monsterName = "Manticore";
                monsterHealth = 65;
                monsterAttack = 20;

                System.out.println("A " + monsterName + " is approaching, Defend yourself " + playerName + "!");

                Fighting();
                break;

            case 6:
                monsterName = "Stone Golem";
                monsterHealth = 100;
                monsterAttack = 25;

                System.out.println("A " + monsterName + " is approaching, Defend yourself " + playerName + "!");

                Fighting();
                break;

            case 7:
                monsterName = "Lich";
                monsterHealth = 70;
                monsterAttack = 40;

                System.out.println("A " + monsterName + " is approaching, Defend yourself " + playerName + "!");

                Fighting();
                break;

            case 8:
                monsterName = "Hell Hound";
                monsterHealth = 90;
                monsterAttack = 40;

                System.out.println("A " + monsterName + " is approaching, Defend yourself " + playerName + "!");

                Fighting();
                break;

            case 9:
                monsterName = "Demon Knight";
                monsterHealth = 110;
                monsterAttack = 45;

                System.out.println("A " + monsterName + " is approaching, Defend yourself " + playerName + "!");

                Fighting();
                break;

            case 10:
                monsterName = "Demon Lord";
                monsterHealth = 200;
                monsterAttack = 60;

                System.out.println("A " + monsterName + " is approaching, Defend yourself " + playerName + "!");

                Fighting();
                break;
        }
    }

    private static void RoomSelect(boolean room1, boolean room2, boolean room3)
    {
        System.out.println("\n*After defeating the " + monsterName + " text appears before your eyes*" +
            "\n" + currentTest + " test completed please select your next test.");

        if (room1 != true)
        {
            System.out.println("\nA door appears and has the depiction of Inteligence.");
            System.out.println("(Type INT to Enter)");
        }
        if (room2 != true)
        {
            System.out.println("\nA door appears and has the depiction of Strength.");
            System.out.println("(Type STR to Enter)");
        }
        if (room3 != true)
        {
            System.out.println("\nA door appears and has the depiction of Agility.");
            System.out.println("(Type AGI to Enter)");
        }

        String Select = input.nextLine().toLowerCase();

        if (room1 != true && Select.equals("int"))
        {
            IntTest();
        }
        if (room2 != true && Select.equals("str"))
        {
            StrTest();
        }
        if (room3 != true && Select.equals("agi"))
        {
            AgiTest();
        }
    }

    //-----------------------------Inteligence Test Section-----------------------------------------------------
    
    private static void IntTest()
    {
        currentTest = "Inteligence";
        int correct = 0;

        System.out.println("\n*You enter through the test doors then they close soon after, After a short silence a loud voice speaks out.*" +
            "\nOhhh, I knew you'd come here next. This place is rather boring, so shall we play a game?" +
            "\nThe rules are simple I will ask ten questions and if you get the majority correct you win and move on..." +
            "\nBUT if I win this is as far as you go. *That last part was said rather ominously.*");

        System.out.println("\nNOTE: Word answers will be marked with _ for the number of words NOT letters. (ex: _ _ = A Sword)" +
            "\nMultiple choice answers will be answered by entering the letter (ex: a)");

        for (int question = 1; question <= 10; question++)
        {
            correct = IntPart1(question, correct);
        }

        if (correct <= 5) {
            System.out.println("\nHA HAHAHA I new I was a genius but to think these questions were so hard for you I can't hwlp but laugh." +
                "\nWell then I suppose I win... *You can't see the being that is speaking but you can almost feel it smiling from ear to ear.*");
            System.out.println("\nNow then what do I do with you?..");

            System.out.println("\n\n" + playerName + " has been captured by the Game Master of Inteligence. Press ENTER to restart.");

            input.nextLine();
            System.exit(0);
        }
        else
        {
            System.out.println("\nH-How did you.. NO YOU CHEATED!!! There is now way you could have gotten..." +
                "\nI'm the only Second to my lord in terms of Inteligence. THIS WILL NOT STAND!!!" +
                "\n*As the voice grew angrier you could feel the dread in the air as a large portal opened in front of you." +
                "\nStepping through a tall ArchLich walked through starring down at you, you can feel the power emmitting off of it.*");
            
            System.out.println("\nI am going to show you what is done to cheaters!");


            monsterName = "Lord of the Abyss";
            monsterHealth = 350;
            monsterAttack = 80;
            monsterSkill = "Drain";

            System.out.println("The " + monsterName + " is approaching, Defend yourself " + playerName + "!");

            BossFight();

            System.out.println("\nAAAAHHHHHHHH No this is im-possible! *The Lord of the Abyss falls and begins to turn to ash.*" +
                "\nYou will never leave this place. *As he mutters those words the room starts to shake and collapse" +
                "\nYou take this as your queue to leave and run for the door you entered through.* 'The King' will never lose to you!");

            monsterSkill = "";
            room1_clear = true;
        }
    }

    static int IntPart1(int question, int correct)
    {
        String answer;
        switch (question)
        {
            case 1:
                System.out.println("\nI have a Tail, a Head, but no Legs. What am I? _");

                answer = input.nextLine().toLowerCase();
                if (answer.equals("coin"))
                {
                    System.out.println("Correct");
                    correct++;
                }
                else
                {
                    System.out.println("HAHAHA Incorrect I was Coin.");
                }
                break;

            case 2:
                System.out.println("\nI Am The Beginning Of The End, And The End Of Before. What am I? _");

                answer = input.nextLine().toLowerCase();
                if (answer.equals("e"))
                {
                    System.out.println("Correct");
                    correct++;
                }
                else
                {
                    System.out.println("HAHAHA Incorrect I was E");
                }
                break;

            case 3:
                System.out.println("\nI Am An Eye Set In A Blue Face. My Gaze Feeds The World. If I Go Blind So Does The World. _");

                answer = input.nextLine().toLowerCase();
                if (answer.equals("sun"))
                {
                    System.out.println("Correct");
                    correct++;
                }
                else
                {
                    System.out.println("HAHAHA Incorrect it was Sun");
                }
                break;

            case 4:
                System.out.println("\nWhat Breathes, Consumes, And Grows, But Was And Never Will Be Alive?" +
                    "\nA) An Undead." +
                    "\nB) Fire." +
                    "\nC) A Dragon.");

                answer = input.nextLine().toLowerCase();
                if (answer.equals("b"))
                {
                    System.out.println("Correct");
                    correct++;
                }
                else
                {
                    System.out.println("HAHAHA Incorrect it was Fire");
                }
                break;

            case 5:
                System.out.println("\nWhat Falls but never Breaks, and what Breaks but never Falls? _ _ _");

                answer = input.nextLine().toLowerCase();
                if (answer.equals("day and night") || answer.equals("night and day"))
                {
                    System.out.println("WHAT!!! AHHH... Correct");
                    correct++;
                }
                else
                {
                    System.out.println("HAHAHA Incorrect it was Night and Day");
                }
                break;

            case 6:
                System.out.println("\nNo Matter Is Parched, No Matter If Rolled. No Matter If Magic, No Matter How Old. _");

                answer = input.nextLine().toLowerCase();
                if (answer.equals("paper"))
                {
                    System.out.println("Fine Correct");
                    correct++;
                }
                else
                {
                    System.out.println("HAHAHA Incorrect it was Paper");
                }
                break;

            case 7:
                System.out.println("\nThe Rich Want It, The Poor Have It, And Both Will Perish If They Eat It?" +
                    "\nA) Nothing." +
                    "\nB) Death." +
                    "\nC) Everything.");

                answer = input.nextLine().toLowerCase();
                if (answer.equals("a"))
                {
                    System.out.println("Correct");
                    correct++;
                }
                else
                {
                    System.out.println("HAHAHA Incorrect it was Nothing");
                }
                break;

            case 8:
                System.out.println("\nPassed From Father To Son, And Shared Between Brothers, Though It Is Used More By Others. _ _");

                answer = input.nextLine().toLowerCase();
                if (answer.equals("last name"))
                {
                    System.out.println("Correct");
                    correct++;
                }
                else
                {
                    System.out.println("HAHAHA Incorrect it was Last Name");
                }
                break;

            case 9:
                System.out.println("\nName Me And So Ye Shall Break Me. What am I without SPEAKING my name?");

                answer = input.nextLine().toLowerCase();
                if (answer.equals(""))
                {
                    System.out.println("Correct, but I basically gave you that one!");
                    correct++;
                }
                else
                {
                    System.out.println("You fool I was Silence you were not supposed to answer at all.");
                }
                break;

            case 10:
                System.out.println("\nLast question. What happens when an Immovable Object meets an Irresitable Force?" +
                    "\nA) The Object moves." +
                    "\nB) The Force stops." +
                    "\nC) They both move and do NOT move.");

                answer = input.nextLine().toLowerCase();
                if (answer.equals("c"))
                {
                    System.out.println("Correct");
                    correct++;
                }
                else
                {
                    System.out.println("HAHAHA Incorrect");
                }
                break;
        }
        return correct;
    }

    //----------------------------Strength Test Section---------------------------------------------------------
    
    private static void StrTest()
    {
        currentTest = "Strength";

        if(role.equals("tank")){
            System.out.println("\n*You walk up to the massive doors that depict Strength press your hands against them, but "
                    + "\nthey are much heavier than expected. Being the Tank though you force it open without much trouble.*"
                    + "\nHa it looks like someone worthy finally showed up?! Most weaklings struggle with that door, but you're not sweating at all."
                    + "\nWell even though you're a cut above the rest I want to see what you've got."
                    + "\n*The voice seems the straight forward type and almost happy to meet you.*");
        }
        else{
            System.out.println("\n*You walk up to the massive doors that depict Strength press your hands against them, but "
                    + "\nthey are much heavier than expected. Being a " + role + " class you don't have much strength so forcing it open is hard.*"
                    + "\nWow a shrimp like you got that open after all that? I was hoping someone stronger would show up but you?"
                    + "\nGuess beggars can't be choosers and I'm bored so lets se what you've got other than strength."
                    + "\n*The voice seems completly uninterested in you.*");
        }
        
        System.out.println("\nSo here's the plan you're going to fight Four of my best and if you win you can fight me."
                + "\nAnd if you lose... well then you weren't that great and will end here. Have fun, and remeber I'll be watching.");
        
        for(int wave = 1; wave <= 4; wave++){
            StrPart1(wave);
        }
        
        System.out.println("\n*As the " + monsterName + " fell after defeating it the room was strangely quiet. Then as if a burst of thunder*"
                + "\nHAHAHA That was amazing! Out of my entire life I have seen few that could handle each of those one after another."
                + "\nWell now that my blood is really pumping...");
        
        monsterName = "Lord of Destruction";
        monsterHealth = 450;
        monsterAttack = 60;
        monsterSkill = "Shock Wave";
        
        System.out.println("\n*A giant Ogre drops down from the shadow filled ceiling and slowly walk towards you.*"
                + "\nI suppose it's my turn to finally have some fun."
                + "\n\nThe " + monsterName + " is approaching, Defend yourself " + playerName + "!");
        
        BossFight();
        
        System.out.println("\n*As you defeat the " + monsterName + " you half expect curses of anger and spite but instead...*"
                + "\nHa..haha. \n*You get struggled laughter* \nWow that was fun. I would like to go a second round but..."
                + "\n*The Ogre pauses and looks at you as he starts to turn to ash.*"
                + "\nYou should know I've never defeated The King."
                + "\n*The rooms starts to tremour and you run for the exit leaving the Ogre.*");
        
        monsterSkill = "";
        room2_clear = true;
    }
    
    private static void StrPart1(int wave){
        switch (wave)
        {
            case 1:
                monsterName = "High Orc";
                monsterHealth = 150;
                monsterAttack = 50;

                System.out.println("\nFirst up is a cut above the average let me see how you handle a " + monsterName);

                Fighting();
                break;

            case 2:
                monsterName = "Minotaur";
                monsterHealth = 200;
                monsterAttack = 70;

                System.out.println("\nOh well looks like you handled that just fine. Next up is a " + monsterName);

                Fighting();
                break;

            case 3:
                monsterName = "Naga";
                monsterHealth = 225;
                monsterAttack = 75;

                System.out.println("\nOKAY! Let see you tackle a " + monsterName + " I'm looking forward to this!");

                Fighting();
                break;

            case 4:
                monsterName = "Skeletal Knight";
                monsterHealth = 275;
                monsterAttack = 90;

                System.out.println("\nHAHAHA alright alright last little guy left, have some fun with this bone head a " + monsterName);

                Fighting();
                break;
        }
    }

    //----------------------------Agility Test Section----------------------------------------------------------
    
    private static void AgiTest()
    {
        currentTest = "Agility";
        int tillSafe = 5;
        
        if(role.equals("assualt")){
            System.out.println("*\nAs you walk up to the door it opens on it's own and reveals a cavern with a few jumping stones to get accross.*"
                    + "\n*Since your used to this sort of thing as an Assualt class you stride right accross without a problem.*"
                    + "\nWell look at you having fun like it's a little play ground."
                    + "\n*The voice echos out from the ceiling and sounds feminine and somewhat pleased with you.*"
                    + "\nIf you had fun with that how about this...");
        }
        else{
            System.out.println("\nAs you walk up to the door it opens on it's own and reveals a cavern with a few jumping stones to get accross.*"
                    + "\n*You see a path that goes around though and decide to take it rather than needlessly falling to your death.*"
                    + "\nTook your time didn't you? If your too scared to even try a little jumping you'll survive this place."
                    + "\n*The voice echos out from the ceiling and sounds feminine and completely uninterested in you.*");
        }
        
        System.out.println("\nSo heres the rules in a moment a boulder is going to chase you."
                + "\n*The voice sounds excited and soon after some sort of string wraps around you and pulls you onto a seperate platform.*"
                + "\nAt the end of the strip you will see an exit you have 10 TURNS to get there before the platform collapses. Good luck!");
        
        AgiPart1(tillSafe);
        
        System.out.println("\n*You walk deeper into the cave you escaped into from the boulder which leads you back to the starting platform.*"
                + "\nGood job on getting through that! I gotta say that was quiet impressive."
                + "\n*The feminine voice gets closer as you see a large Arachne lowers down from the ceiling.*"
                + "\nImpressive as that was, this is as far as you go.");
        
        monsterName = "Queen of the Cavern";
        monsterHealth = 400;
        monsterAttack = 70;
        monsterSkill = "Sticky Thread";
        
        System.out.println("\nThe " + monsterName + " is approaching, Defend yourself " + playerName + "!");
        
        BossFight();
        
        System.out.println("\n*As you defeat the " + monsterName + " she lets out a loud screech making the entire Cavern Shake.*"
                + "\nYou.. How dare you do this to me!.."
                + "\n*The Arachne stumbles around in pain and anger then loses her footing on the edge and falls into the Cavern.*"
                + "\nThe King will avenge me!!!"
                + "\n*The Queen of the Cavern screams as she falls, when the Cavern tremors more which makes you leave right away.*");
        
        monsterSkill = "";
        room3_clear = true;
    }

    private static void AgiPart1(int tillSafe){
        boolean safe = true;
        boolean dead = false;
        int turnsLeft = 10;
        String action;
        
        do{
        int tillHit = (1+(int)(Math.random()*3));
        System.out.println("\nA Boulder slams down behind you");
            do{
                switch(tillHit){
                    case 3:
                        System.out.println("\nThe Boulder is rolling towards you from far back."
                                + "\nTurns Left:" + turnsLeft + ", Space Till Exit:" + tillSafe);
                        System.out.println("Actions: 'RUN', 'COVER'");
                        action = input.nextLine().toLowerCase();

                        switch (action) {
                            case "run":
                                System.out.println("You continued to RUN.");
                                tillHit--;
                                tillSafe--;
                                turnsLeft--;
                                break;
                            case "cover":
                                System.out.println("You ducked into cover and let the boulder pass.");
                                tillHit = 0;
                                turnsLeft--;
                                break;
                            default:
                                System.out.println("You just stood there while the boulder got closer?");
                                tillHit--;
                                turnsLeft--;
                                break;
                        }
                        break;
                        
                    case 2:
                        System.out.println("\nThe Boulder is getting closer to you."
                                + "\nTurns Left:" + turnsLeft + ", Space Till Exit:" + tillSafe);
                        System.out.println("Actions: 'RUN', 'COVER'");
                        action = input.nextLine().toLowerCase();

                        switch (action) {
                            case "run":
                                System.out.println("You continued to RUN.");
                                tillHit--;
                                tillSafe--;
                                turnsLeft--;
                                break;
                            case "cover":
                                System.out.println("You ducked into cover and let the boulder pass.");
                                tillHit = 0;
                                turnsLeft--;
                                break;
                            default:
                                System.out.println("You just stood there while the boulder got closer?");
                                tillHit--;
                                turnsLeft--;
                                break;
                        }
                        break;
                        
                    case 1:
                        System.out.println("\nThe Boulder is right on your heels!"
                                + "\nTurns Left:" + turnsLeft + ", Space Till Exit:" + tillSafe);
                        System.out.println("Actions: 'RUN', 'COVER'");
                        action = input.nextLine().toLowerCase();

                        switch (action) {
                            case "run":
                                System.out.println("You continued to RUN.");
                                tillHit--;
                                tillSafe--;
                                safe = false;
                                dead = true;
                                turnsLeft--;
                                break;
                            case "cover":
                                System.out.println("You ducked into cover and let the boulder pass.");
                                tillHit = 0;
                                turnsLeft--;
                                break;
                            default:
                                System.out.println("You just stood there while the boulder got closer and hit you?");
                                tillHit--;
                                safe = false;
                                dead = true;
                                turnsLeft--;
                                break;
                        }
                        break;
                }
                if(safe == false && dead == true){
                    if(tillSafe > 0){
                        System.out.println("Well looks like you got flatened, I guess you weren't all that after all. Press ENTER to restart.");
                        input.nextLine();
                        System.exit(0);
                    }
                    else{
                        System.out.println("*You barely make it throught the stone exit as the Boulder slams into it.*"
                                + "\nWow that was cutting it close.");
                        return;
                    }
                }
            }while(tillHit != 0);
            
            if(tillSafe == 0){
                System.out.println("*You get through the stone exit before the Boulder gets to close*"
                        + "\nWould have been better if you let the boulder get a little close, oh well.");
                return;
            }
            if(turnsLeft == 0){
                System.out.println("*The floor collapses beneath your feet and you fall into the cavern.*"
                        + "\nJeez, your even worse than I expected. Press ENTER to restart.");
                input.nextLine();
                System.exit(0);
            }
            
        }while(tillSafe > 0);
        
        System.out.println("YOU SURVIVED");
    }
    
    //----------------------------End Game Section--------------------------------------------------------------
    
    private static void TheKing(){
        String answer;
        
        System.out.println("\n*After the text confirms you compeleted the " + currentTest + " the starting room suddenly feels different*"
                + "\n*The room somehow seems warped and the blue flames that lit the room start turning red one by one.*"
                + "\n*They circle around you till the last two flames change red on the other side of the room revealing a tall figure.*");
        
        System.out.println("\nSo you're the one? The one that has been going around massacring my little subordinates."
                + "\n*The the tall figure strides towards you, but you, who already dealt with everything else head, on move forward meeting him half way.*"
                + "\nOh you're still unaffected by fear when face to face with The Demon King Baran? Impressive."
                + "\n*As you stand in front of The Demon King Baran you need to look up at him and feel his gaze examining you.*"
                + "\nWell I can see how you defeated those weaklings. Now the question is what now?");
        
        System.out.println("\n*After a brief bought of silence* I've got it!"
                + "\n*The Demon King Baran points to the ground and without another moment of pause.* Kneel and you will be my new right hand."
                + "\n\n*Text appears before you. The King offers to make you his subordinate what will you do?*"
                + "\nSay 'YES', or Put this False King 'DOWN'!");
        
        answer = input.nextLine().toLowerCase();
        if(answer.equals("yes")){
            EndGame2();
        }
        else{
            switch (role) {
                case "tank":
                    System.out.println("\n*Become this guy's underling yeah right.*"
                            + "\n*Thinking that alone was enough ball your fist, and throw a punch with everything you got into his solar plexis.*"
                            + "\n*That punch sent The Demon King Baran sliding back a couple feet holding hunched over some.*");
                    break;
                case "assualt":
                    System.out.println("\n*Become this guy's underling yeah right.*"
                            + "\n*As this over confident punk points to the ground you make a single movement of drawing your dagger and cutting of that stupid finger.*"
                            + "\n*Afterwards as he holds his bloody hand you fall back a little distance.*");
                    break;
                case "mage":
                    System.out.println("\n*Become this guy's underling yeah right.*"
                            + "\n*As you look up at this smiling fool you gather all the magic you can into your hand and throw a fire ball right into his face.*"
                            + "\n*Afterwards you retreat back aways before he can attack you close up*");
                    break;
                default:
                    System.out.println("\nIDK HOW YOU GOT THIS FAR WITHOUT A CLASS");
                    break;
            }
        }
        
        System.out.println("\nGAAHHH you insignificant RAT!!! You dare spit in my face while I offer my mercy?!"
                + "\n*He face is filled with rage as his true self is unveiled from the shadows surrounding him.*"
                + "\n*He looks like an upgraded Archdemon rather than a real Demon King. Maybe Archdemon's become Deomn Kings?*");
        
        //FINAL BOSS PHASE 1
        monsterName = "Demon King Baran, Phase I";
        monsterHealth = 350;
        monsterAttack = 80;
        monsterSkill = "Drain";
        
        System.out.println("\nI am going to incinerate you where you stand do you hear me?!"
                + "\nThe " + monsterName + " is approaching, Defend yourself " + playerName + "!");
        BossFight();
        
        //FINAL BOSS PHASE 2
        monsterName = "Demon King Baran, Phase II";
        monsterHealth = 400;
        monsterAttack = 70;
        monsterSkill = "Sticky Thread";
        
        System.out.println("\nGAH Stop resisting it is futile!"
                + "\nYou will not escape me by running!"
                + "\nThe " + monsterName + " is approaching, Defend yourself " + playerName + "!");
        BossFight();
        
        //FINAL BOSS PHASE 3
        monsterName = "Demon King Baran, Phase III";
        monsterHealth = 450;
        monsterAttack = 60;
        monsterSkill = "Shock Wave";
        
        System.out.println("\nWhy won't you just lay down and PERISH"
                + "\nBe crushed under my strangth you MAGGOT!"
                + "\nThe " + monsterName + " is approaching, Defend yourself " + playerName + "!");
        BossFight();
        
        System.out.println("\n\n*After a long fought battle the thing calling itself a King fell over.*"
                + "\nH-How this is im-impossible? *You walk up to the Archdemon standing over it.*"
                + "\nWhat are you? *There is a trace of fear in his voice. In the end you say nothing and simply finish it.*");
    }
    
    private static void EndGame1()
    {
        System.out.println("\n*Text appears before your eyes as you finish the " + monsterName + "*" +
            "\nYou have slayed the " + monsterName);

        System.out.println("\nDungeon Master: That foolish adventurer will never escape my King.");
        System.out.println("*The Massive doors blocking the exit open and you walk out with a crumpled expression.*");
        System.out.println("\nDungeon Master: W-What how did y-you.. I mean Congragulation Mighty " + playerName + " you clear..ed th-the");
        System.out.println("Dungeon Master: W-what are you doing.. WAIT!!..");
        System.out.println("\n*Text appears before your eyes* \nYou have cleared the dungeon of the Demon King!!!");

        System.out.println("\n\nSAMUEL BOWES (Creator): Thank you for playing my first game and I hope you enjoyed it :D");
    }

    private static void EndGame2(){
        System.out.println("\n*You kneel down before The Demon King Baran and face the floor, but you can feel that wicked smile across his face.*"
                + "\nPerfect, you will help me achieve my goals and see MY new world rise from the ashes of YOUR old one!"
                + "\n\n*Years pass by and you've only gotten stronger since that day fighting the enemies of your King.*"
                + "\n*Some of them you knew, in a different life. Now though even with all you have done this is all you get.*"
                + "\n*More strength and not even for your own purpose, eventualy you gave him everything and in the end recieved nothing.*"
                + "\n*But an end met by a Hero... A Hero you could've been.*");
        
        System.out.println("\n\nSAMUEL BOWES (Creator): Thank you for playing my first game and I hope you enjoyed it :D"
                + "Try out the other way to see what happens.");
        
        input.nextLine();
        System.exit(0);
    }
}
