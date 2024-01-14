import java.util.Arrays;

public class Test2 {
    public static void main(String[] args) {
        System.out.println("#1 " + duplicateChars("Donald") + " " + duplicateChars("orange"));
        System.out.println("#2 " + getInitials("Ryan Gosling") + " " + getInitials("Barak Obama"));
        int[] array1 = {44, 32, 86, 19};
        int[] array2 = {22, 50, 16, 63, 31, 55};
        System.out.println("#3 " + differenceEvenOdd(array1) + " " + differenceEvenOdd(array2));
        array1 = new int[]{1, 2, 3, 4, 5};
        array2 = new int[]{1, 2, 3, 4, 6};
        System.out.println("#4 " + equalToAvg(array1) + " " + equalToAvg(array2));
        array1 = new int[]{1, 2, 3};
        array2 = new int[]{3, 3, -2, 408, 3, 31};
        System.out.println("#5 " + Arrays.toString(indexMult(array1)) + " " + Arrays.toString(indexMult(array2)));
        System.out.println("#6 " + reverse("Hello World") + " " + reverse(" The quick brown fox."));
        System.out.println("#7 " + Tribonacci(7) + " " + Tribonacci(11));
        System.out.println("#8 " + pseudoHash(5) + " " + pseudoHash(10) + " " + pseudoHash(0));
        System.out.println("#9 " + botHelper("Hello, I’m under the water, please help me") + " " + botHelper("Two pepperoni pizzas please"));
        System.out.println("#10 " + isAnagram("listen", "silent") + " " + isAnagram("eleven plus two", "twelve plus one") + " " + isAnagram("hello", "world"));
    }

    //1
    public static boolean duplicateChars(String word) {
        word = word.toLowerCase();
        for (char element : word.toCharArray()){
            int letterCount = 0;
            for (int i = 0; i < word.length(); i++){
                if (element == word.charAt(i)) letterCount++;
                if (letterCount > 1) return true;
            }
        }
        return false;
    }

    //2
    public static String getInitials(String name) {
        String initials = "";
        for (char element : name.toCharArray()){
            if (Character.isUpperCase(element)){
                initials += String.valueOf(element);
            }
        }
        return initials;
    }

    //3
    public static int differenceEvenOdd(int[] array) {
        int summEven = 0;
        int summOdd = 0;
        for (int i : array){
            if (i % 2 == 0){
                summEven += i;
            } else {
                summOdd += i;
            }
        }
        return (summEven - summOdd >= 0) ? summEven - summOdd : (summEven - summOdd) * (-1);
    }

    //4
    public static boolean equalToAvg(int[] array) {
        int sum = Arrays.stream(array).sum();
        for (int element : array) {
            if ((double) element == (double) (sum) / (double) (array.length)) {
                return true;
            }
        }
        return false;
    }

    //5
    public static int[] indexMult(int[] array) {
        for (int i = 0; i < array.length; i++){
                array[i] = array[i] * i;
        }
        return array;
    }

    //6
    public static String reverse(String word) {
        String reversedWord = "";
        for (int i = word.toCharArray().length - 1; i >= 0; i--) {
            reversedWord += word.charAt(i);
        }
        return reversedWord;
    }

    //7
    public static int Tribonacci(int numIndex){
        int[] trib = new int[numIndex];
        trib[0] = 0;
        trib[1] = 0;
        trib[2] = 1;
        for (int i = 3; i < numIndex; i++) {
            trib[i] = trib[i - 1] + trib[i - 2] + trib[i - 3];
        }
        return trib[numIndex - 1];
    }

    //8
    public static String pseudoHash(int len) {
        String symbols = "abcdef0123456789";
        String hash = "";
        for (int i = 0; i < len; i++) {
            hash += symbols.toCharArray()[(int) (Math.random() * symbols.toCharArray().length)];
        }
        return hash;
    }

    //9
    public static String botHelper(String message) {
        if (message.contains(" help ") || message.contains("Help ")) {
            return "Calling for a staff member";
        } else {
            return "Keep waiting";
        }
    }

    //10
    public static boolean isAnagram(String firstWord, String secondWord) {
        char[] firstChars = firstWord.toCharArray();
        char[] secondChars = secondWord.toCharArray();
        Arrays.sort(firstChars);
        Arrays.sort(secondChars);
        return String.valueOf(firstChars).equals(String.valueOf(secondChars));
    }
}
