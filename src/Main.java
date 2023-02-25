import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    private static final String fileName = "src/google-10000-english.txt";
    public static final String YELLOW_COLOR = "\u001B[33m";
    public static void main(String[] args) throws IOException{
        System.out.print("Enter phrase: ");
        Scanner scanner = new Scanner(System.in);
        String phrase = scanner.nextLine();
        phrase = phrase.toLowerCase();

        Main main = new Main();

        LinkedList<String> returnWord;



        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            try {
                returnWord = main.returnWords(bufferedReader, phrase);
                if(returnWord.isEmpty()) {
                    System.out.println(YELLOW_COLOR + "Error! The words from file: " + System.clearProperty("user.dir") + "\\" + fileName + "were not found");
                    System.exit(0);
                }
            } finally {
                bufferedReader.close();
            }
        } catch (IOException ex) {
            System.out.println(YELLOW_COLOR +  "Error! reading from file: " + System.clearProperty("user.dir") + "\\" + fileName);
            System.exit(0);
            throw ex;
        }

        System.out.println("We can: ");

        for (int i = 0; i < returnWord.size(); i++) {
            System.out.println(returnWord.get(i));
        }

        scanner.close();
    }

    private HashMap<Character, Integer> getLetter(String phrase){
        HashMap<Character, Integer> hashMap= new HashMap<>();

        for (int i = 0; i < phrase.length(); i++) {
            char letter = phrase.charAt(i);
            if(hashMap.containsKey(letter)){
                int number = hashMap.get(letter);
                int newNumber = number + 1;
                hashMap.put(letter, newNumber);
            } else {
                hashMap.put(letter, 1);
            }
        }
        return hashMap;
    }

    private LinkedList<String> returnWords(BufferedReader bufferedReader, String phrase) throws IOException{

        Main main = new Main();

        String allWords;

        HashMap<Character, Integer> getPhrase = main.getLetter(phrase);
        boolean isTrue;
        int num = 0;

        LinkedList<String> wordsList = new LinkedList<>();

        while((allWords = bufferedReader.readLine()) != null) {
            isTrue = true;
            HashMap<Character, Integer> getAllWords = main.getLetter(allWords);
            for (char letter : getAllWords.keySet()) {
                int getIntWords = getAllWords.get(letter);
                int getIntPhrase = getPhrase.containsKey(letter) ? getPhrase.get(letter) : 0;
                if(getIntWords > getIntPhrase)
                    isTrue = false;
            }
            if(isTrue && allWords.length() > 2) {
                num++;
                wordsList.add(num + ". " + allWords);
            }
        }

        return wordsList;
    }
}