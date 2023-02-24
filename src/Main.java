import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException{
        System.out.print("Enter phrase: ");
        Scanner scanner = new Scanner(System.in);
        String phrase = scanner.nextLine();
        phrase = phrase.toLowerCase();

        String fileName = "src/google-10000-english.txt";

        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

        Main main = new Main();


        LinkedList<String> returnWord = main.returnWords(bufferedReader, phrase);

        for (int i = 0; i < returnWord.size(); i++) {
            System.out.println(returnWord.get(i));
        }

        scanner.close();
        bufferedReader.close();
    }

    private HashMap<Character, Integer> getLetter(String phrase) throws IOException {
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

        System.out.println("We can: ");
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