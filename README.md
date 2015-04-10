# WordBaseCheat
A runnable jar to help you find words in the mobile game, Word Base.

You have to convert the game board into a txt file similar to some of those in the repo. (See boardp.txt, for example)

To run the jar, open up terminal (for Mac). I am sorry. I am not sure what is the equivalent in Windows.
navigate to the directory where the jar file resides, and type
  ```
  java -jar wordbasecheat.jar
  ```
When you run the jar, enter the gameboard name. The file has to be in the same directory as the jar file.

Then enter the index of the square to take as root, i.e, the 1st letter of the word. 
The index starts from [0][0], which is the top left most letter, and is in the order [up/down][left/right].
It returns a list of up words, each up to 10 letters, that starts with the root letter. 

Enjoy!


