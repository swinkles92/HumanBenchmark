Human Benchmark
Author: Samuel Winkles (# 101479375)
        swinkles92@unm.edu
Version: 1.0

Game Descriptions:
 Aim Trainer: 
 A player will be tasked with clicking
 30 targets that spawn in random locations on the game
 board. Upon completion the game will report the user's
 elapsed time.

Chimp Test: 
 A player will be presented with a sequence of
 numbers, assigned to randomly selected rectangles on
 the game board. The player will need to click the
 rectangles in the correct order to proceed. The
 amount of numbers in the sequence will grow after
 each success.

Math Solver:
 A player will be shown an equation with random
 numbers and a random operation. The player will
 need to correctly solve the equation to proceed.
 After each successful solve, the number range
 will grow by a factor of 2^n.

Number Memory:
 A player will be shown a number on screen. After
 a few seconds, the number will disappear. The user
 will need to correctly type the number in the text
 field to proceed. The numbers will get large after
 each success.

Reaction Time:
 A player will be shown a red rectangle. When the
 rectangle turns green, the user will need to quickly
 click on the rectangle. The time it takes a user to
 click on the rectangle after it turns green will be
 shown afterwards.

Sequence Memory:
 A player will be shown a sequence of flashing rectangles.
 The player will need to click these rectangles in the
 correct sequence to proceed. Another rectangle will be
 added to the sequence after each success.

Typing:
 The player will be shown a line of text on the screen
 that they must type as quickly, and correctly, as
 they can. If the player types in a wrong character,
 the text will turn red to denote it. The time it takes
 for the player to correctly type the entire line will
 be reported.

Verbal Memory:
 The player will be shown a variety of words and must
 select "Seen" if they have seen the word before, or
 "new" if they haven't.

Visual Memory:
 The player will be shown a set of flashing rectangles.
 The player must click on all the rectangles in the set
 to proceed. The order of clicking the rectangles does
 not matter. More rectangles will be added to the sequence
 after each success.

Known Bugs/Issues:
 Sequence Memory, Chimp Test, and Visual Memory: Games
 do not stop currently playing animations if user clicks
 retry or completes a level too quickly.

 Verbal Memory: I would have liked to have added a
 weight variable so that seen words were more likely
 to be seen again.
 
 Typing: Instead of only highlighting one character
 that is being inputted incorrectly, the entire line
 will be highlighted. The algorithm is also too slow
 and cannot keep up with a typist's true speed.

I used 3 extension days for this project, 09/28-10/01.