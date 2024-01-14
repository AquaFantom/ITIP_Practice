import java.beans.Introspector;
import java.util.*;

public class Test6 {
    public static void main(String[] args) {
        System.out.println("#1: " + hiddenAnagram("My world evolves in a beautiful space called Tesh.",
                "sworn love lived"));
        System.out.println("#2: " + Arrays.toString(collect("intercontinentalisationalism", 6)));
        System.out.println("#3: " + nicoCipher("iloveher", "612345"));
        int[] numArray = {1, 2, -1, 4, 5, 6, 10, 7};
        System.out.println("#4 " + Arrays.toString(twoProduct(numArray, 20)));
        System.out.println("#5 " + Arrays.toString(isExact(720)));
        System.out.println("#6 " + fractions("3.(142857)"));
        System.out.println("#7 " + pilishString("HOWINEEDADRINKALCOHOLICINNATUREAFTERTHEHEAVYLECTURESINVOLVINGQUANTUMMECHANICSANDALLTHESECRETSOFTHEUNIVERSE"));
        System.out.println("#8 " + generateNonconsecutive("3 + 5 / (2 - 6)"));
        System.out.println("#9 " + isValid("aabbcd"));
        System.out.println("#10 " + findLCS("aggtab", "gxtxamb"));
    }

    //1
    public static String hiddenAnagram(String str1, String str2) {
        str1 = str1.toLowerCase().replaceAll("[^a-z]", "");
        str2 = str2.toLowerCase().replaceAll("[^a-z]", "");
        for (int i = 0; i <= str1.length() - str2.length(); i++) {
            String substr = str1.substring(i, i + str2.length());
            if (isAnagram(substr, str2)) {
                return substr;
            }
        }
        return "not found";
    }

    private static boolean isAnagram(String str, String anagram) {
        char[] strChars = str.toCharArray();
        char[] anagramChars = anagram.toCharArray();
        Arrays.sort(strChars);
        Arrays.sort(anagramChars);
        return Arrays.equals(strChars, anagramChars);
    }

    //2
    public static String[] collect(String word, int n) {
        if (word.length() < n) {
            return new String[0];
        }
        ArrayList<String> slices = new ArrayList<>();
        collectRecursive(word, n, slices);
        Collections.sort(slices);
        return slices.toArray(new String[0]);
    }

    private static void collectRecursive(String word, int n, List<String> slices) {
        if (word.length() < n) {
            return;
        }
        String slice = word.substring(0, n);
        slices.add(slice);
        collectRecursive(word.substring(n), n, slices);
    }

    //3
    public static String nicoCipher(String message, String key) {
        int[] keyNumbers = new int[key.length()];
        for (int i = 0; i < key.length(); i++) {
            keyNumbers[i] = key.charAt(i) - 'a' + 1;
        }
        int columns = key.length();
        int rows = (int) Math.ceil((double) message.length() / columns);
        char[][] encodedMessage = new char[rows][columns];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (index < message.length()) {
                    encodedMessage[i][j] = message.charAt(index);
                    index++;
                } else {
                    encodedMessage[i][j] = ' ';
                }
            }
        }
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < columns - 1; j++) {
                if (keyNumbers[j] > keyNumbers[j + 1]) {
                    swapColumns(encodedMessage, j, j + 1);
                    swap(keyNumbers, j, j + 1);
                }
            }
        }
        StringBuilder encodedString = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                encodedString.append(encodedMessage[i][j]);
            }
        }
        return encodedString.toString();
    }

    private static void swapColumns(char[][] matrix, int col1, int col2) {
        for (int i = 0; i < matrix.length; i++) {
            char temp = matrix[i][col1];
            matrix[i][col1] = matrix[i][col2];
            matrix[i][col2] = temp;
        }
    }

    private static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    //4
    public static int[] twoProduct(int[] arr, int n) {
        int[] result = new int[2];
        int minDiff = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] * arr[j] == n) {
                    int diff = Math.abs(arr[i] - arr[j]);
                    if (diff < minDiff) {
                        minDiff = diff;
                        result[0] = arr[i];
                        result[1] = arr[j];
                    }
                }
            }
        }

        if (minDiff == Integer.MAX_VALUE) {
            return new int[]{};
        }

        return result;
    }

    //5
    public static int[] isExact(int n) {
        int[] result = new int[2];
        int factorial = 1;
        int i = 1;

        while (factorial < n) {
            factorial *= i;
            i++;
        }

        if (factorial == n) {
            result[0] = factorial;
            result[1] = i - 1;
            return result;
        } else {
            return new int[]{};
        }
    }

    //6
    public static String fractions(String str) {
        int[] array = new int[3];
        String[] split = str.split("\\.");
        array[0] = Integer.parseInt(split[0]); //целая часть
        StringBuilder strNum1 = new StringBuilder();
        StringBuilder strNum2 = new StringBuilder();
        char[] chars = split[1].toCharArray();
        boolean flag = true;
        for (int i = 0; i < chars.length; i++) {
            if (flag) {
                if (chars[i] != '(') {
                    strNum1.append(chars[i]); //дробная не периодическая часть
                } else {
                    flag = false;
                    if (!strNum1.isEmpty()) {
                        array[1] = Integer.parseInt(strNum1.toString());
                    }
                }
            } else {
                if(chars[i] != ')') {
                    strNum2.append(chars[i]); //периодическая часть
                } else {
                    if (!strNum2.isEmpty()) {
                        array[2] = Integer.parseInt(strNum2.toString());
                    }
                    break;
                }
            }
        }
        String denominatorStr = "9".repeat(strNum2.length()) +
                "0".repeat(strNum1.length());
        int denominator = Integer.parseInt(denominatorStr);
        int numerator = Integer.parseInt(strNum1.append(strNum2.toString()).toString()) - array[1];
        int[] fraction = simplifyFraction(numerator, denominator);
        fraction[0] += array[0] * fraction[1];
        return fraction[0] + "/" + fraction[1];
    }

    private static int[] simplifyFraction(int numerator, int denominator) {
        for(;;) {
            int nod = findNOD(denominator, numerator);
            if (nod > 1) {
                denominator /= nod;
                numerator /= nod;
            } else {
                return new int[] {numerator, denominator};
            }
        }
    }

    private static int findNOD(int num1, int num2) {
        int max = Integer.max(num1, num2);
        for (int i = max / 2; i > 1; i--) {
            if (num1 % i == 0 && num2 % i == 0) {
                return i;
            }
        }
        return 1;
    }

    //7
    public static String pilishString(String txt) {
        String piDigits = "314159265358979";
        StringBuilder result = new StringBuilder();
        int piIndex = 0;

        if (txt.isEmpty()) {
            return "";
        }

        for (int i = 0; i < txt.length(); i++) {
            if (piIndex >= piDigits.length()) {
                break;
            }

            int wordLength = Character.getNumericValue(piDigits.charAt(piIndex));
            if (i + wordLength > txt.length()) {
                for (int j = 0; j < wordLength; j++){
                    result.append(txt.charAt(i));
                }
                continue;
            }

            String word = txt.substring(i, i + wordLength);
            result.append(word);

            for (int j = word.length(); j < wordLength; j++) {
                result.append(word.charAt(word.length() - 1));
            }

            result.append(" ");
            i += wordLength - 1;
            piIndex++;
        }

        return result.toString().trim();
    }

    //8
    public static String generateNonconsecutive(String str) {
        if (str.isEmpty()) return "Empty input";
        str = str.replaceAll("\\(", "( ").replaceAll("\\)", " )");
        String[] array = str.split(" ");
        ArrayList<String> list = new ArrayList<>(Arrays.asList(array));
        list = generateNonList(list);
        return !list.isEmpty() ? String.valueOf(list.get(0)) : "Division by zero";
    }

    private static ArrayList<String> generateNonList(ArrayList<String> list) {
        boolean flag = false;
        ArrayList<String> subList = new ArrayList<>();
        int first = 0;
        int last;
        while (list.contains("(")) {
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i - 1).equals("(")) {
                    first = i;
                    flag = true;
                    subList = new ArrayList<>();
                }
                if (list.get(i).equals(")")) {
                    last = i - 1;
                    replaceSubList(list, generateNonList(subList).get(0), first, last);
                    flag = false;
                }
                if (flag) {
                    subList.add(list.get(i));
                }
            }
        }
        if (list.contains("*")) {
            countUp(list, "*");
        }
        if (list.contains("/")) {
            countUp(list, "/");
        }
        if (list.contains("+")) {
            countUp(list, "+");
        }
        if (list.contains("-")) {
            countUp(list, "-");
        }
        return list;
    }

    private static void replaceSubList(ArrayList<String> list, String replaceStr, int firstInd, int lastInd) {
        for (int i = firstInd - 1; i < lastInd + 1; i++) {
            list.remove(firstInd);
        }
        list.set(firstInd - 1, replaceStr);
    }

    private static void countUp(ArrayList<String> list, String ch) {
        while (list.contains(String.valueOf(ch))) {
            int index = list.indexOf(ch);
            int first = Integer.parseInt(list.get(index - 1));
            int second = Integer.parseInt(list.get(index + 1));
            if (ch.equals("*")) {
                replaceSubList(list, String.valueOf(first * second), index, index);
            } else if (ch.equals("/")) {
                if (second == 0) {
                    list.clear();
                    return;
                }
                replaceSubList(list, String.valueOf(first / second), index, index);
            } else if (ch.equals("+")) {
                replaceSubList(list, String.valueOf(first + second), index, index);
            } else if (ch.equals("-")) {
                replaceSubList(list, String.valueOf(first - second), index, index);
            }
        }
    }

    //9
    public static String isValid(String str) {
        HashMap<Character, Integer> lettersAmount = new HashMap<>();
        char[] chars = str.toCharArray();
        for (char element : chars) {
            if (lettersAmount.containsKey(element)) {
                lettersAmount.put(element, lettersAmount.get(element) + 1);
            } else {
                lettersAmount.put(element, 1);
            }
        }
        Integer[] nums = lettersAmount.values().toArray(new Integer[0]);
        Arrays.sort(nums);
        Integer first = nums[0];
        int countAdditional = 0;
        for (int i = 0; i < nums.length; i++) {
            if (first.intValue() != nums[i].intValue()) {
                countAdditional++;
            }
            if (countAdditional > 1) return "NO";
        }
        return "YES";
    }

    //10
    public static String findLCS(String str1, String str2) {
        int[][] array = new int[str1.length() + 1][str2.length() + 1];
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        for (int i = 0; i < chars1.length ; i++) {
            for (int j  = 0; j < chars2.length; j++) {
                if (chars1[i] == chars2[j]) {
                    array[i+1][j+1] = array[i][j] + 1;
                } else {
                    array[i+1][j+1] = Integer.max(array[i][j+1], array[i+1][j]);
                }
            }
        }
        StringBuilder result = new StringBuilder();
        int x = chars1.length;
        int y = chars2.length;
        while (x > 0 && y > 0) {
            if (chars1[x - 1] == chars2[y - 1]) {
                result.append(chars1[x - 1]);
                x -= 1;
                y -= 1;
            } else if (array[x-1][y] > array[x][y-1]) {
                x -= 1;
            } else {
                y -= 1;
            }
        }
        return result.reverse().toString();
    }
}
