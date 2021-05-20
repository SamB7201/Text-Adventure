using System;
using System.Collections.Generic;
using System.ComponentModel.Design;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;

namespace MakeAGame
{
    class Program
    {
        static string playerName = "";
        static string role = "";
        static string skill = "";
        static int playerAttack = 0;
        static int playerHealth = 0;
        static int SkillPoints = 50;
        static int cost = 0;

        static string monsterName = "";
        static int monsterHealth = 0;
        static int monsterAttack = 0;
        static string monsterSkill = "";

        static string currentTest = "Beginner";

        static bool room1_clear = false;
        static bool room2_clear = false;
        static bool room3_clear = false;

        static void Main(string[] args)
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

            EndGame();

            Console.ReadKey();
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

            Console.WriteLine("Dungeon Master: Welcome Adenturer what is your Name?");
            playerName = Console.ReadLine();

            Console.WriteLine($"\nDungeon Master: {playerName} what is your role?");
            do
            {
                Console.WriteLine($"\nTank - Health: {tankHealth}, Attack: {tankAttack}\n" +
                    $"Assualt - Health: {assualtHealth}, Attack: {assaultAttack}\n" +
                    $"Mage - Health: {mageHealth}, Attack: {mageAttack}");

                role = Console.ReadLine().ToLower();
                if (role == "tank")
                {
                    playerAttack = tankAttack;
                    playerHealth = tankHealth;
                    skill = "Demolition";
                    cost = tankCost;
                }
                else if (role == "assualt")
                {
                    playerAttack = assaultAttack;
                    playerHealth = assualtHealth;
                    skill = "Quick Silver";
                    cost = assualtCost;
                }
                else if (role == "mage")
                {
                    playerAttack = mageAttack;
                    playerHealth = mageHealth;
                    SkillPoints = 100;
                    skill = "Mirror";
                    cost = mageCost;
                }
                else
                {
                    Console.WriteLine($"{role} does not exist choose one provided!");
                    playerAttack = 0;
                    playerHealth = 0;
                }

            } while (playerAttack == 0 || playerHealth == 0);

            Console.WriteLine($"\nDungeon Master: {playerName} you have chosen {role} with {playerAttack} Attack power, {playerHealth} Health," +
                $" and {SkillPoints} Skill Points");
            Console.WriteLine("Dungeon Master: Good Luck in the dungeoun!... You'll need it.");
            Console.WriteLine("*The large doors closes behind you, and text appears before your eyes*");
        }

        private static void Fighting()
        {
            while (playerHealth > 0 && monsterHealth > 0)
            {
                Console.ForegroundColor = ConsoleColor.Yellow;
                Console.WriteLine($"{playerName}: {playerHealth} HP, {playerAttack} ATK, {SkillPoints} SP");
                Console.WriteLine($"{monsterName}: {monsterHealth} HP, {monsterAttack} ATK");
                Console.ForegroundColor = ConsoleColor.White;

                Console.WriteLine($"\nWill you 'ATK', 'DEF', or use '{skill}' for {cost} SP");
                string Action = Console.ReadLine().ToLower();

                if (role == "tank")
                {
                    if (Action == "atk")
                    {
                        Console.WriteLine($"You and the {monsterName} Attacked each other");
                        monsterHealth -= playerAttack;
                        if (monsterHealth > 0)
                        {
                            playerHealth -= monsterAttack;
                        }
                    }
                    else if (Action == "def")
                    {
                        Console.WriteLine($"You Defeneded against the {monsterName} Attack");
                        playerHealth -= (monsterAttack / 4);
                    }
                    else if (Action == skill.ToLower())
                    {
                        if (SkillPoints > cost)
                        {
                            Console.WriteLine($"You used {skill} against the {monsterName} damging and dazing them");
                            monsterHealth -= 55;
                            SkillPoints -= cost;
                        }
                        else
                        {
                            Console.WriteLine($"You do not have enough SkillPoints to use {skill} and was Attacked off guard");
                            playerHealth -= monsterAttack;
                        }
                    }
                }

                else if (role == "assualt")
                {
                    if (Action == "atk")
                    {
                        Console.WriteLine($"You and the {monsterName} Attacked each other");
                        monsterHealth -= playerAttack;
                        if (monsterHealth > 0)
                        {
                            playerHealth -= monsterAttack;
                        }
                    }
                    else if (Action == "def")
                    {
                        Console.WriteLine($"You Defeneded against the {monsterName} Attack");
                        playerHealth -= (monsterAttack / 2);
                    }
                    else if (Action == skill.ToLower())
                    {
                        if (SkillPoints > cost)
                        {
                            Console.WriteLine($"You used {skill} evading the attack and sending Daggers at the {monsterName}");
                            monsterHealth -= 30;
                            SkillPoints -= cost;
                        }
                        else
                        {
                            Console.WriteLine($"You do not have enough SkillPoints to use {skill} and was Attacked of guard");
                            playerHealth -= monsterAttack;
                        }
                    }
                }

                else if (role == "mage")
                {
                    if (Action == "atk")
                    {
                        Console.WriteLine($"You and the {monsterName} Attacked each other");
                        monsterHealth -= playerAttack;
                        if (monsterHealth > 0)
                        {
                            playerHealth -= monsterAttack;
                        }
                    }
                    else if (Action == "def")
                    {
                        Console.WriteLine($"You Defeneded against the {monsterName} Attack");
                        playerHealth -= (monsterAttack / 2);
                    }
                    else if (Action == skill.ToLower())
                    {
                        if (SkillPoints > cost)
                        {
                            Console.WriteLine($"You used {skill} blocking the Attack and reflecting Quadruple the damage at the {monsterName}");
                            monsterHealth -= (monsterAttack * 4);
                            SkillPoints -= cost;
                        }
                        else
                        {
                            Console.WriteLine($"You do not have enough SkillPoints to use {skill} and was Attacked of guard");
                            playerHealth -= monsterAttack;
                        }
                    }
                }

                else
                {
                    Console.WriteLine("You stood there like an idiot and got attacked");
                    playerHealth -= monsterAttack;
                }

            }

            if (playerHealth <= 0)
            {
                Console.WriteLine($"{playerName} you have fallen to the Dungeon. Press ENTER to restart.");
                Console.ReadKey();
                var info = new System.Diagnostics.ProcessStartInfo(Environment.GetCommandLineArgs()[0]);
                System.Diagnostics.Process.Start(info);
                Environment.Exit(0);
            }

            else if (monsterHealth <= 0)
            {
                Console.ForegroundColor = ConsoleColor.Blue;
                Console.WriteLine($"{monsterName} has been slain Adding SP and upgrading stats");
                Console.ForegroundColor = ConsoleColor.Gray;
                playerHealth += 25;
                playerAttack += 5;
                SkillPoints += 30;
            }
        }

        private static void BossFight()
        {
            while (playerHealth > 0 && monsterHealth > 0)
            {
                Random randomNum = new Random();
                int BossMove = randomNum.Next(1, 4);

                Console.ForegroundColor = ConsoleColor.Yellow;
                Console.WriteLine($"{playerName}: {playerHealth} HP, {playerAttack} ATK, {SkillPoints} SP");
                Console.WriteLine($"{monsterName}: {monsterHealth} HP, {monsterAttack} ATK");
                Console.ForegroundColor = ConsoleColor.White;

                Console.WriteLine($"\nWill you 'ATK', 'DEF', or use '{skill}' for {cost} SP");
                string Action = Console.ReadLine().ToLower();

                if (role == "tank")
                {
                    if (Action == "atk")
                    {
                        if (BossMove == 1)
                        {
                            Console.WriteLine($"You and the {monsterName} Attacked each other");
                            monsterHealth -= playerAttack;
                            if (monsterHealth > 0)
                            {
                                playerHealth -= monsterAttack;
                            }
                            SkillPoints += 5;
                        }
                        else if(BossMove == 2)
                        {
                            Console.WriteLine($"The {monsterName} Defended against your Attack");
                            monsterHealth -= (playerAttack / 3);
                            SkillPoints += 5;
                        }
                        else if(BossMove == 3 && monsterSkill.Equals("Drain"))
                        {
                            Console.WriteLine($"The {monsterName} used {monsterSkill} while you Attacked");
                            monsterHealth -= playerAttack;
                            if (monsterHealth > 0)
                            {
                                SkillPoints -= 30;
                            }
                        }
                    }
                    else if (Action == "def")
                    {
                        if (BossMove == 1 || BossMove == 2)
                        {
                            Console.WriteLine($"You Defeneded against the {monsterName} Attack");
                            playerHealth -= (monsterAttack / 4);
                            SkillPoints += 10;
                        }
                        else if(BossMove == 3 && monsterSkill.Equals("Drain"))
                        {
                            Console.WriteLine($"You Defeneded against the {monsterName}'s {monsterSkill}");
                            SkillPoints -= (30 / 3);
                        }
                    }
                    else if (Action == skill.ToLower())
                    {
                        if (SkillPoints > cost)
                        {
                            Console.WriteLine($"You used {skill} against the {monsterName} damging and dazing them");
                            monsterHealth -= 55;
                            SkillPoints -= cost;
                        }
                        else
                        {
                            Console.WriteLine($"You do not have enough SkillPoints to use {skill} and was Attacked off guard");
                            playerHealth -= monsterAttack;
                            SkillPoints += 5;
                        }
                    }
                }



                else if (role == "assualt")
                {
                    if (Action == "atk")
                    {
                        if (BossMove == 1)
                        {
                            Console.WriteLine($"You and the {monsterName} Attacked each other");
                            monsterHealth -= playerAttack;
                            if (monsterHealth > 0)
                            {
                                playerHealth -= monsterAttack;
                            }
                            SkillPoints += 5;
                        }
                        else if (BossMove == 2)
                        {
                            Console.WriteLine($"The {monsterName} Defended against your Attack");
                            monsterHealth -= (playerAttack / 3);
                            SkillPoints += 5;
                        }
                        else if (BossMove == 3 && monsterSkill.Equals("Drain"))
                        {
                            Console.WriteLine($"The {monsterName} used {monsterSkill} while you Attacked");
                            monsterHealth -= playerAttack;
                            if (monsterHealth > 0)
                            {
                                SkillPoints -= 30;
                            }
                        }
                    }
                    else if (Action == "def")
                    {
                        if (BossMove == 1 || BossMove == 2)
                        {
                            Console.WriteLine($"You Defeneded against the {monsterName} Attack");
                            playerHealth -= (monsterAttack / 2);
                            SkillPoints += 15;
                        }
                        else if (BossMove == 3 && monsterSkill.Equals("Drain"))
                        {
                            Console.WriteLine($"You Defeneded against the {monsterName}'s {monsterSkill}");
                            SkillPoints -= (30 / 2);
                        }
                    }
                    else if (Action == skill.ToLower())
                    {
                        if (SkillPoints > cost)
                        {
                            Console.WriteLine($"You used {skill} evading the attack and throwing Daggers at the {monsterName}");
                            monsterHealth -= 30;
                            SkillPoints -= cost;
                        }
                        else
                        {
                            Console.WriteLine($"You do not have enough SkillPoints to use {skill} and was Attacked of guard");
                            playerHealth -= monsterAttack;
                            SkillPoints += 5;
                        }
                    }
                }

                else if (role == "mage")
                {
                    if (Action == "atk")
                    {
                        if (BossMove == 1)
                        {
                            Console.WriteLine($"You and the {monsterName} Attacked each other");
                            monsterHealth -= playerAttack;
                            if (monsterHealth > 0)
                            {
                                playerHealth -= monsterAttack;
                            }
                            SkillPoints += 10;
                        }
                        else if (BossMove == 2)
                        {
                            Console.WriteLine($"The {monsterName} Defended against your Attack");
                            monsterHealth -= (playerAttack / 3);
                            SkillPoints += 10;
                        }
                        else if (BossMove == 3 && monsterSkill.Equals("Drain"))
                        {
                            Console.WriteLine($"The {monsterName} used {monsterSkill} while you Attacked");
                            monsterHealth -= playerAttack;
                            if (monsterHealth > 0)
                            {
                                SkillPoints -= 30;
                            }
                        }
                    }
                    else if (Action == "def")
                    {
                        if (BossMove == 1 || BossMove == 2)
                        {
                            Console.WriteLine($"You Defeneded against the {monsterName} Attack");
                            playerHealth -= (monsterAttack / 2);
                            SkillPoints += 20;
                        }
                        else if (BossMove == 3 && monsterSkill.Equals("Drain"))
                        {
                            Console.WriteLine($"You Defeneded against the {monsterName}'s {monsterSkill}");
                            SkillPoints -= (30 / 3);
                        }
                    }
                    else if (Action == skill.ToLower())
                    {
                        if (SkillPoints > cost)
                        {
                            Console.WriteLine($"You used {skill} blocking the Attack and reflecting Quadruple the damage at the {monsterName}");
                            monsterHealth -= (monsterAttack * 4);
                            SkillPoints -= cost;
                        }
                        else
                        {
                            Console.WriteLine($"You do not have enough SkillPoints to use {skill} and was Attacked of guard");
                            playerHealth -= monsterAttack;
                            SkillPoints += 10;
                        }
                    }
                }

                else
                {
                    Console.WriteLine("You stood there like an idiot and got attacked");
                    playerHealth -= monsterAttack;
                    SkillPoints += 10;
                }

            }

            if (playerHealth <= 0)
            {
                Console.WriteLine($"{playerName} you have fallen to the Dungeon. Press ENTER to restart.");
                Console.ReadKey();
                var info = new System.Diagnostics.ProcessStartInfo(Environment.GetCommandLineArgs()[0]);
                System.Diagnostics.Process.Start(info);
                Environment.Exit(0);
            }

            else if (monsterHealth <= 0)
            {
                Console.ForegroundColor = ConsoleColor.Blue;
                Console.WriteLine($"{monsterName} has been slain Adding SP and upgrading stats");
                Console.ForegroundColor = ConsoleColor.Gray;
                playerHealth += 25;
                playerAttack += 5;
            }
        }

        private static void WaveAttack(int wave)
        {
            switch (wave)
            {
                case 1:
                    monsterName = "Goblin";
                    monsterHealth = 30;
                    monsterAttack = 5;

                    Console.ForegroundColor = ConsoleColor.Red;
                    Console.WriteLine("You have entered the Test of the Hero. Clear the Tests to prove yourself!");
                    Console.WriteLine($"A {monsterName} is approaching, Defend yourself {playerName}!");
                    Console.ForegroundColor = ConsoleColor.Gray;

                    Fighting();
                    break;

                case 2:
                    monsterName = "Skeleton Knight";
                    monsterHealth = 40;
                    monsterAttack = 7;

                    Console.ForegroundColor = ConsoleColor.Red;
                    Console.WriteLine($"A {monsterName} is approaching, Defend yourself {playerName}!");
                    Console.ForegroundColor = ConsoleColor.Gray;

                    Fighting();
                    break;

                case 3:
                    monsterName = "Cave Spider";
                    monsterHealth = 45;
                    monsterAttack = 10;

                    Console.ForegroundColor = ConsoleColor.Red;
                    Console.WriteLine($"A {monsterName} is approaching, Defend yourself {playerName}!");
                    Console.ForegroundColor = ConsoleColor.Gray;

                    Fighting();
                    break;

                case 4:
                    monsterName = "Black Serpant";
                    monsterHealth = 55;
                    monsterAttack = 15;

                    Console.ForegroundColor = ConsoleColor.Red;
                    Console.WriteLine($"A {monsterName} is approaching, Defend yourself {playerName}!");
                    Console.ForegroundColor = ConsoleColor.Gray;

                    Fighting();
                    break;

                case 5:
                    monsterName = "Manticore";
                    monsterHealth = 65;
                    monsterAttack = 20;

                    Console.ForegroundColor = ConsoleColor.Red;
                    Console.WriteLine($"A {monsterName} is approaching, Defend yourself {playerName}!");
                    Console.ForegroundColor = ConsoleColor.Gray;

                    Fighting();
                    break;

                case 6:
                    monsterName = "Stone Golem";
                    monsterHealth = 100;
                    monsterAttack = 25;

                    Console.ForegroundColor = ConsoleColor.Red;
                    Console.WriteLine($"A {monsterName} is approaching, Defend yourself {playerName}!");
                    Console.ForegroundColor = ConsoleColor.Gray;

                    Fighting();
                    break;

                case 7:
                    monsterName = "Lich";
                    monsterHealth = 70;
                    monsterAttack = 40;

                    Console.ForegroundColor = ConsoleColor.Red;
                    Console.WriteLine($"A {monsterName} is approaching, Defend yourself {playerName}!");
                    Console.ForegroundColor = ConsoleColor.Gray;

                    Fighting();
                    break;

                case 8:
                    monsterName = "Hell Hound";
                    monsterHealth = 90;
                    monsterAttack = 40;

                    Console.ForegroundColor = ConsoleColor.Red;
                    Console.WriteLine($"A {monsterName} is approaching, Defend yourself {playerName}!");
                    Console.ForegroundColor = ConsoleColor.Gray;

                    Fighting();
                    break;

                case 9:
                    monsterName = "Demon Knight";
                    monsterHealth = 110;
                    monsterAttack = 45;

                    Console.ForegroundColor = ConsoleColor.Red;
                    Console.WriteLine($"A {monsterName} is approaching, Defend yourself {playerName}!");
                    Console.ForegroundColor = ConsoleColor.Gray;

                    Fighting();
                    break;

                case 10:
                    monsterName = "Demon Lord";
                    monsterHealth = 200;
                    monsterAttack = 60;

                    Console.ForegroundColor = ConsoleColor.Red;
                    Console.WriteLine($"A {monsterName} is approaching, Defend yourself {playerName}!");
                    Console.ForegroundColor = ConsoleColor.Gray;

                    Fighting();
                    break;
            }
        }

        private static void RoomSelect(bool room1, bool room2, bool room3)
        {
            Console.WriteLine($"\n*After defeating the {monsterName} text appears before your eyes*" +
                $"\n{currentTest} test finished please select your next test.");

            if (room1 != true)
            {
                Console.WriteLine("\nA door appears and has the depiction of Inteligence.");
                Console.WriteLine("(Type INT to Enter)");
            }
            if (room2 != true)
            {
                Console.WriteLine("\nA door appears and has the depiction of Strength.");
                Console.WriteLine("(Type STR to Enter)");
            }
            if (room3 != true)
            {
                Console.WriteLine("\nA door appears and has the depiction of Agility.");
                Console.WriteLine("(Type AGI to Enter)");
            }

            string Select = Console.ReadLine();

            if (room1 != true && Select.Equals("INT"))
            {
                IntTest();
            }
            if (room2 != true && Select.Equals("STR"))
            {
                StrTest();
            }
            if (room3 != true && Select.Equals("AGI"))
            {
                AgiTest();
            }
        }

        private static void IntTest()
        {
            currentTest = "Inteligence";
            int correct = 0;

            Console.WriteLine("\n*You enter through the test doors then they close soon after, After a short silence a loud voice speaks out.*" +
                "\nOhhh, I knew you'd come here next. This place is rather boring, so shall we play a game?" +
                "\nThe rules are simple I will ask ten questions and if you get the majority correct you win and move on..." +
                "\nBUT if I win this is as far as you go. *That last part was said rather ominously.*");

            Console.ForegroundColor = ConsoleColor.Red;
            Console.WriteLine("\nNOTE: Word answers will be marked with _ for the number of words NOT letters. (ex: _ _ = A Sword)" +
                "\nMultiple choice answers will be answered by entering the letter (ex: a)");
            Console.ForegroundColor = ConsoleColor.White;

            for (int question = 1; question <= 10; question++)
            {
                correct = IntPart1(question, correct);
            }

            if (correct <= 5) {
                Console.WriteLine("\nHA HAHAHA I new I was a genius but to think these questions were so hard for you I can't hwlp but laugh." +
                    "\nWell then I suppose I win... *You can't see the being that is speaking but you can almost feel it smiling from ear to ear.*");
                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine("Now then what do I do with you?..");
                Console.ForegroundColor = ConsoleColor.White;

                Console.ReadKey();
                Console.WriteLine($"\n{playerName} has been captured by the Game Master of Inteligence. Press ENTER to restart.");

                Console.ReadKey();
                var info = new System.Diagnostics.ProcessStartInfo(Environment.GetCommandLineArgs()[0]);
                System.Diagnostics.Process.Start(info);
                Environment.Exit(0);
            }
            else
            {
                Console.WriteLine("\nH-How did you.. NO YOU CHEATED!!! There is now way you could have gotten..." +
                    "\nI'm the only Second to my lord in terms of Inteligence. THIS WILL NOT STAND!!!" +
                    "\n*As the voice grew angrier you could feel the dread in the air as a large portal opened in front of you." +
                    "\nStepping through a tall Elder Lich walked through starring down at you, you can feel the power emmitting off of it.*");
                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine("I am going to show you what is done to cheaters!");
                Console.ForegroundColor = ConsoleColor.White;


                monsterName = "Lord of the Abyss";
                monsterHealth = 350;
                monsterAttack = 70;
                monsterSkill = "Drain";

                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine($"\nThe {monsterName} is approaching, Defend yourself {playerName}!");
                Console.ForegroundColor = ConsoleColor.White;

                BossFight();

                Console.WriteLine($"\nAAAAHHHHHHHH No this is im-possible! *The Lord of the Abyss falls and begins to turn to ash.*" +
                    $"\nYou will never leave this place. *As he mutters those words the room starts to shake and collapse" +
                    $"You take this as your queue to leave and run for the door you entered through.*");

                room1_clear = true;
            }
        }

        static int IntPart1(int question, int correct)
        {
            string answer = "";
            switch (question)
            {
                case 1:
                    Console.WriteLine("\nI have a Tail, a Head, but no Legs. What am I? _");

                    answer = Console.ReadLine().ToLower();
                    if (answer.Equals("coin"))
                    {
                        Console.WriteLine("Correct");
                        correct++;
                    }
                    else
                    {
                        Console.WriteLine("HAHAHA Incorrect I was Coin.");
                    }
                    break;

                case 2:
                    Console.WriteLine("\nI Am The Beginning Of The End, And The End Of Before. What am I? _");

                    answer = Console.ReadLine().ToLower();
                    if (answer.Equals("e"))
                    {
                        Console.WriteLine("Correct");
                        correct++;
                    }
                    else
                    {
                        Console.WriteLine("HAHAHA Incorrect I was E");
                    }
                    break;

                case 3:
                    Console.WriteLine("\nI Am An Eye Set In A Blue Face. My Gaze Feeds The World. If I Go Blind So Does The World. _");

                    answer = Console.ReadLine().ToLower();
                    if (answer.Equals("sun"))
                    {
                        Console.WriteLine("Correct");
                        correct++;
                    }
                    else
                    {
                        Console.WriteLine("HAHAHA Incorrect it was Sun");
                    }
                    break;

                case 4:
                    Console.WriteLine("\nWhat Breathes, Consumes, And Grows, But Was And Never Will Be Alive?" +
                        "\nA) An Undead." +
                        "\nB) Fire." +
                        "\nC) A Dragon.");

                    answer = Console.ReadLine().ToLower();
                    if (answer.Equals("b"))
                    {
                        Console.WriteLine("Correct");
                        correct++;
                    }
                    else
                    {
                        Console.WriteLine("HAHAHA Incorrect it was Fire");
                    }
                    break;

                case 5:
                    Console.WriteLine("\nWhat Falls but never Breaks, and what Breaks but never Falls? _ _ _");

                    answer = Console.ReadLine().ToLower();
                    if (answer.Equals("day and night") || answer.Equals("night and day"))
                    {
                        Console.WriteLine("WHAT!!! AHHH... Correct");
                        correct++;
                    }
                    else
                    {
                        Console.WriteLine("HAHAHA Incorrect it was Night and Day");
                    }
                    break;

                case 6:
                    Console.WriteLine("\nNo Matter Is Parched, No Matter If Rolled. No Matter If Magic, No Matter How Old. _");

                    answer = Console.ReadLine().ToLower();
                    if (answer.Equals("paper"))
                    {
                        Console.WriteLine("Fine Correct");
                        correct++;
                    }
                    else
                    {
                        Console.WriteLine("HAHAHA Incorrect it was Paper");
                    }
                    break;

                case 7:
                    Console.WriteLine("\nThe Rich Want It, The Poor Have It, And Both Will Perish If They Eat It?" +
                        "\nA) Nothing." +
                        "\nB) Death." +
                        "\nC) Everything.");

                    answer = Console.ReadLine().ToLower();
                    if (answer.Equals("a"))
                    {
                        Console.WriteLine("Correct");
                        correct++;
                    }
                    else
                    {
                        Console.WriteLine("HAHAHA Incorrect it was Nothing");
                    }
                    break;

                case 8:
                    Console.WriteLine("\nPassed From Father To Son, And Shared Between Brothers, Though It Is Used More By Others. _ _");

                    answer = Console.ReadLine().ToLower();
                    if (answer.Equals("last name"))
                    {
                        Console.WriteLine("Correct");
                        correct++;
                    }
                    else
                    {
                        Console.WriteLine("HAHAHA Incorrect it was Last Name");
                    }
                    break;

                case 9:
                    Console.WriteLine("\nName Me And So Ye Shall Break Me. What am I without SPEAKING my name?");

                    answer = Console.ReadLine().ToLower();
                    if (answer.Equals(""))
                    {
                        Console.WriteLine("Correct");
                        correct++;
                    }
                    else
                    {
                        Console.WriteLine("You fool I was Silence you were not supposed to answer at all.");
                    }
                    break;

                case 10:
                    Console.WriteLine("\nLast question. What happens when an Immovable Object meets an Irresitable Force?" +
                        "\nA) The Object moves." +
                        "\nB) The Force stops." +
                        "\nC) They both move and do NOT move.");

                    answer = Console.ReadLine().ToLower();
                    if (answer.Equals("c"))
                    {
                        Console.WriteLine("Correct");
                        correct++;
                    }
                    else
                    {
                        Console.WriteLine("HAHAHA Incorrect");
                    }
                    break;
            }
            return correct;
        }

        private static void StrTest()
        {
            currentTest = "Strength";

            room2_clear = true;
        }

        private static void AgiTest()
        {
            currentTest = "Agility";

            room3_clear = true;
        }

        private static void EndGame()
        {
            Console.ForegroundColor = ConsoleColor.Blue;
            Console.WriteLine($"\n*Text appears before your eyes as you slay the {monsterName}*" +
                $"\nThe Waves have concluded the exit will open.");

            Console.WriteLine("\nDungeon Master: That foolish adventurer will never escape my King.");
            Console.WriteLine("*The Massive doors blocking the exit open and you walk out with a crumpled expression.*");
            Console.WriteLine($"\nDungeon Master: W-What how did y-you.. I mean Congragulation Mighty {playerName} you clear..ed th-the");
            Console.WriteLine("Dungeon Master: W-what are you doing.. WAIT!!..");
            Console.WriteLine("\n*Text appears before your eyes* \nYou have cleared the dungeon of the Demon King!!!");

            Console.ForegroundColor = ConsoleColor.Green;
            Console.WriteLine("\n\nSAMUEL BOWES (Creator): Thank you for playing my first game and I hope you enjoyed it :D");
        }
    }
}
