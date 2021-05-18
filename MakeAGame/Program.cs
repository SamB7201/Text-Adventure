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

        static void Main(string[] args)
        {
            StartGame();

            for (int Wave = 1; Wave <= 10; Wave++)
            {
                WaveAttack(Wave);
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

                role = Console.ReadLine();
                if (role == "Tank")
                {
                    playerAttack = tankAttack;
                    playerHealth = tankHealth;
                    skill = "Demolition";
                    cost = tankCost;
                }
                else if (role == "Assualt")
                {
                    playerAttack = assaultAttack;
                    playerHealth = assualtHealth;
                    skill = "Quick Silver";
                    cost = assualtCost;
                }
                else if (role == "Mage")
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
            Console.WriteLine("Dungeon Master: Good Luck in the dungeoun!.... You'll need it.");
            Console.WriteLine("*Door closes behind you, and text appears before your eyes*");
        }

        private static void Fighting()
        {
            while (playerHealth > 0 && monsterHealth > 0)
            {
                Console.WriteLine($"{playerName}: {playerHealth} HP, {playerAttack} ATK, {SkillPoints} SP");
                Console.WriteLine($"{monsterName}: {monsterHealth} HP, {monsterAttack} ATK");

                Console.WriteLine($"\nWill you 'ATK', 'DEF', or use '{skill}' for {cost} SP");
                string Action = Console.ReadLine();

                if (role == "Tank")
                {
                    if (Action == "ATK")
                    {
                        Console.WriteLine($"You and the {monsterName} Attacked each other");
                        monsterHealth -= playerAttack;
                        playerHealth -= monsterAttack;
                    }
                    else if (Action == "DEF")
                    {
                        Console.WriteLine($"You Defeneded against the {monsterName} Attack");
                        playerHealth -= (monsterAttack / 4);
                    }
                    else if (Action == skill)
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

                else if (role == "Assualt")
                {
                    if (Action == "ATK")
                    {
                        Console.WriteLine($"You and the {monsterName} Attacked each other");
                        monsterHealth -= playerAttack;
                        playerHealth -= monsterAttack;
                    }
                    else if (Action == "DEF")
                    {
                        Console.WriteLine($"You Defeneded against the {monsterName} Attack");
                        playerHealth -= (monsterAttack / 2);
                    }
                    else if (Action == skill)
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

                else if (role == "Mage")
                {
                    if (Action == "ATK")
                    {
                        Console.WriteLine($"You and the {monsterName} Attacked each other");
                        monsterHealth -= playerAttack;
                        playerHealth -= monsterAttack;
                    }
                    else if (Action == "DEF")
                    {
                        Console.WriteLine($"You Defeneded against the {monsterName} Attack");
                        playerHealth -= (monsterAttack / 2);
                    }
                    else if (Action == skill)
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
                Console.WriteLine($"{playerName} you have fallen to the Dungeon");
                Console.ReadKey();
                return;
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

        private static void WaveAttack(int wave)
        {
            switch (wave)
            {
                case 1:
                    monsterName = "Goblin";
                    monsterHealth = 30;
                    monsterAttack = 5;

                    Console.ForegroundColor = ConsoleColor.Red;
                    Console.WriteLine("You have entered the Dungeon of Waves. Clear the waves to leave alive!");
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
                    monsterName = "Elder Lich";
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
                    monsterName = "Demon King";
                    monsterHealth = 200;
                    monsterAttack = 60;

                    Console.ForegroundColor = ConsoleColor.Red;
                    Console.WriteLine($"A {monsterName} is approaching, Defend yourself {playerName}!");
                    Console.ForegroundColor = ConsoleColor.Gray;

                    Fighting();
                    break;
            }
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
