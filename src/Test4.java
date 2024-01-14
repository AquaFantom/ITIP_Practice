import java.util.*;

public class Test4 {
    public static void main(String[] args) {
        System.out.println("1: " + nonRepeatable("paparazzi"));
        System.out.println("2: " + generateBrackets(2));
        System.out.println("3: " + binarySystem(3));
        System.out.println("4: " + alphabeticRow("abcdcjuwxz"));
        System.out.println("5: " + countAndSort("vvvvaajaaaaa"));
        System.out.println("6: " + convertToNum("two hundred"));
        System.out.println("7: " + uniqueSubstring("789934"));
        int[][] numArray = {{1, 3, 1},
                            {1, 5, 1},
                            {4, 2, 1}};
        System.out.println("8: " + shortestWay(numArray));
        System.out.println("9: " + numericOrder("re6sponsibility Wit1h gr5eat power3 4comes g2reat"));
        System.out.println("10: " + switchNums(6274, 71259));
    }


    //1
    public static String nonRepeatable(String string) {
        if (string.length() == 1) {
            return string;
        }
        String finalString = nonRepeatable(string.substring(0, string.length() - 1));

        if (finalString.contains(String.valueOf(string.charAt(string.length() - 1)))) {
            return finalString;
        } else {
            return finalString + String.valueOf(string.charAt(string.length() - 1));
        }
    }

    //2
    public static ArrayList<String> generateBrackets(int n) {
        ArrayList<String> result = new ArrayList<String>();
        String currentBrackets = "";
        generateRecBrackets(n, n, currentBrackets, result);
        return result;
    }

    public static void generateRecBrackets(int leftCount, int rightCount, String thisBracket, ArrayList<String> result) {
        if (leftCount == 0 && rightCount == 0) {
            result.add(thisBracket);
            return;
        }

        if (leftCount > 0) {
            generateRecBrackets(leftCount - 1, rightCount, thisBracket + "(", result);
        }

        if (rightCount > leftCount) {
            generateRecBrackets(leftCount, rightCount - 1, thisBracket + ")", result);
        }

    }

    //3
    public static ArrayList<String> binarySystem(int n) {
        ArrayList<String> result = new ArrayList<>();
        binarySystemRec(n, "", result);
        return result;
    }

    private static void binarySystemRec(int n, String thisNum, ArrayList<String> result) {
        if (thisNum.length() == n) {
            result.add(thisNum);
            return;
        }

        if (thisNum.isEmpty() || thisNum.charAt(thisNum.length() - 1) != '0') {
            binarySystemRec(n, thisNum + "0", result);
        }

        binarySystemRec(n, thisNum + "1", result);
    }

    //4 в джаве следующая буква больше предыдущей на 1
    public static String alphabeticRow(String string) {
        if (string.isEmpty()) {
            return "";
        }

        String longestRow = "";
        String currentRow = String.valueOf(string.charAt(0));
        int isIncrease = -1;

        for (int i = 1; i < string.length(); i++) {
            char currentChar = string.charAt(i);
            char previousChar = string.charAt(i - 1);

            if ((currentChar == previousChar + 1) && (isIncrease != 0)) {
                isIncrease = 1;
                currentRow += currentChar;

            } else if ((currentChar == previousChar - 1) && (isIncrease != 1)) {
                isIncrease = 0;
                currentRow += currentChar;
            } else {
                if (currentRow.length() > longestRow.length()) {
                    longestRow = currentRow;
                }
                currentRow = String.valueOf(currentChar);
            }
        }

        if (currentRow.length() > longestRow.length()) {
            longestRow = currentRow;
        }

        return longestRow;
    }

    //5
    public static String countAndSort(String string) {
        StringBuilder result = new StringBuilder();
        int count = 1;

        for (int i = 1; i < string.length(); i++) {
            if (string.charAt(i) == string.charAt(i - 1)) {
                count++;
            } else {
                result.append(string.charAt(i - 1)).append(count);
                count = 1;
            }
        }

        result.append(string.charAt(string.length() - 1)).append(count);

        ArrayList<String> patterns = new ArrayList<>(Arrays.asList(result.toString().split("(?<=\\d)")));
        char[][] sortingPattern = new char[patterns.size()][2];

        for (int i = 0; i < patterns.size(); i++) {
            sortingPattern[i] = patterns.get(i).toCharArray();
        }

        Arrays.sort(sortingPattern, Comparator.comparingInt(arr -> arr[1]));
        String finalString = "";

        for (int i = 0; i < sortingPattern.length; i++) {
            for (int j = 0; j < 2; j++) {
                finalString += sortingPattern[i][j];
            }
        }

        return finalString;
    }

    //6
    public static int convertToNum(String numString) {
        Map<String, Integer> numList = new HashMap<>();

        numList.put("one", 1);
        numList.put("two", 2);
        numList.put("three", 3);
        numList.put("four", 4);
        numList.put("five", 5);
        numList.put("six", 6);
        numList.put("seven", 7);
        numList.put("eight", 8);
        numList.put("nine", 9);
        numList.put("ten", 10);
        numList.put("eleven", 11);
        numList.put("twelve", 12);
        numList.put("thirteen", 13);
        numList.put("fourteen", 14);
        numList.put("fifteen", 15);
        numList.put("sixteen", 16);
        numList.put("seventeen", 17);
        numList.put("eighteen", 18);
        numList.put("nineteen", 19);
        numList.put("twenty", 20);
        numList.put("thirty", 30);
        numList.put("forty", 40);
        numList.put("fifty", 50);
        numList.put("sixty", 60);
        numList.put("seventy", 70);
        numList.put("eighty", 80);
        numList.put("ninety", 90);
        numList.put("hundred", 100);

        String[] numArray = numString.split(" ");
        int result = 0;

        for (int i = 0; i < numArray.length - 1; i++) {
            if (numArray[1].equals("hundred")) { //так как число меньше 1000, hundred может иметь только индекс 1
                result += numList.get(numArray[i]) * 100;
                i++;
            } else {
                result += numList.get(numArray[i]);
            }
        }

        if (!(numArray[numArray.length - 1].equals("hundred"))) {
            result += numList.get(numArray[numArray.length - 1]);
        }

        return result;
    }

    //7
    public static String uniqueSubstring(String numString) {
        String currentRow = "";
        String finalString = "";

        for (char element : numString.toCharArray()) {
            if (!(currentRow.contains(String.valueOf(element)))) {
                currentRow += element;
            } else if (currentRow.length() > finalString.length()) {
                finalString = currentRow;
            }
        }

        return finalString;
    }

    //8
    public static int shortestWay(int[][] matrix) {
        int n = matrix.length;
        int[][] newMatrix = new int[n][n];
        newMatrix[0][0] = matrix[0][0];

        for (int i = 1; i < n; i++) {
            newMatrix[i][0] = newMatrix[i - 1][0] + matrix[i][0]; //путь сверху вниз
        }

        for (int i = 1; i < n; i++) {
            newMatrix[0][i] = newMatrix[0][i - 1] + matrix[0][i]; //путь слева направо
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                newMatrix[i][j] = Math.min(newMatrix[i - 1][j], newMatrix[i][j - 1]) + matrix[i][j]; //путь до остальных ячеек
            }
        }

        return newMatrix[n - 1][n - 1];
    }

    //9
    public static String numericOrder(String string) {
        String[] stringArray = string.split(" ");
        String[] finalString = new String[stringArray.length];

        for (String element : stringArray) {
            for (int i = 0; i < element.length(); i++) {
                if (Character.isDigit(element.toCharArray()[i])) {
                    int wordPosition = Character.getNumericValue(element.toCharArray()[i]) - 1;
                    finalString[wordPosition] = element.substring(0, i) + element.substring(i + 1);
                    }
                }
            }

        return String.join(" ", finalString);
    }

    //10
    public static int switchNums(int num1, int num2) {
        char[] digits1 = String.valueOf(num1).toCharArray();
        char[] digits2 = String.valueOf(num2).toCharArray();

        for (int i = 0; i < digits2.length; i++) {
            char maxDigit = '0';
            int maxId = 0;

            for (int j = 0; j < digits1.length; j++) {
                if (digits1[j] > maxDigit) {
                    maxDigit = digits1[j];
                    maxId = j;
                }
            }

            if (digits1[maxId] > digits2[i]) {
                digits2[i] = digits1[maxId];
                digits1[maxId] = '0';
            }
        }

        return Integer.parseInt(String.valueOf(digits2));
    }
}