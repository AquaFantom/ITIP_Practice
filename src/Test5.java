import java.util.*;
import java.util.stream.Collectors;

public class Test5 {
    public static void main(String[] args) {
        System.out.println("#1: " + sameLetterPattern("ABCBA", "BCDCB"));
        System.out.println("#2: " + spiderVsFly("H3", "E2"));
        System.out.println("#3: " + digitsCount(3444));
        String[] words = {"dote", "dotes", "toes", "set", "dot", "dots", "sted"};
        System.out.println("#4: " + totalPoints(words, "tossed"));
        int[] nums = {1, 6, 5, 4, 8, 2, 3, 7};
        System.out.println("#5: " + Arrays.deepToString(sumsUp(nums)));
        words = new String[] {"95%", "83%", "90%", "87%", "88%", "93%"};
        System.out.println("#6: " + takeDownAverage(words));
        System.out.println("#7: " + ceaserCipher("decode", "EPQSWX PEWX XEWO!", 4));
        System.out.println("#8: " + setSetup(5, 3));
        System.out.println("#9: " + timeDifference("Los Angeles", "April 1, 2011 23:23", "Canberra"));
        System.out.println("#10: " + isNew(509));
    }

    //1
    public static boolean sameLetterPattern(String pattern1, String pattern2) {
        if (pattern1.length() != pattern2.length()) {
            return false;
        }

        Map<Character, Character> patternMap = new HashMap<>();

        for (int i = 0; i < pattern1.length(); i++) {
            char ch1 = pattern1.charAt(i);
            char ch2 = pattern2.charAt(i);

            if (patternMap.containsKey(ch1)) {
                if (patternMap.get(ch1) != ch2) {
                    return false;
                }
            } else {
                patternMap.put(ch1, ch2);
            }
        }

        return true;
    }


    //2
    public static String spiderVsFly(String spiderPosition, String flyPosition) {
        char[] chars1 = spiderPosition.toCharArray();
        int spiderX = (int) chars1[0] - 65;
        int spiderY = Integer.parseInt(String.valueOf(chars1[1]));
        int[] spiderCoords = new int[] {spiderX, spiderY};

        char[] chars2 = flyPosition.toCharArray();
        int flyX = (int) chars2[0] - 65;
        int flyY = Integer.parseInt(String.valueOf(chars2[1]));
        int[] flyCoords = new int[] {flyX, flyY};

        StringBuilder path = new StringBuilder();
        if (Math.abs(spiderCoords[0] - flyCoords[0]) > 2) { //если линии не находятся рядом
            for (int i = spiderCoords[1]; i > 0; i--) { //движение по линии (к центру)
                path.append((char) (spiderCoords[0] + 65));
                path.append(i);
                path.append('-');
            }
            for (int i = 0; i <= flyCoords[1]; i++) { //движение по линии (от центра до мухи)
                path.append((char) (flyCoords[0] + 65));
                path.append(i);
                path.append('-');
            }
        } else { //если линии рядом или через одну
            if (spiderCoords[1] > flyCoords[1]) { //если паук находится выше мухи
                for (int i = spiderCoords[1]; i >= flyCoords[1]; i--) { //движение по линии (вниз, до кольца с мухой)
                    path.append((char) (spiderCoords[0] + 65));
                    path.append(i);
                    path.append('-');
                }
                for (int i = spiderCoords[0]; i != flyCoords[0];) { //движение по кольцу (к мухе)
                    if (spiderCoords[0] - flyCoords[0] > 0) i--; //если паук правее мухи (движение против часовой)
                    else i++; //если паук левее мухи (движение по часовой)
                    path.append((char) (i + 65));
                    path.append(flyCoords[1]);
                    path.append('-');
                }
            } else { //если пау ниже мухик
                for (int i = spiderCoords[0]; i != flyCoords[0];) { //движение по кольцу (к мухе)
                    path.append((char) (i + 65));
                    path.append(spiderCoords[1]);
                    path.append('-');
                    if (spiderCoords[0] - flyCoords[0] > 0) i--; //если паук правее мухи (движение против часовой)
                    else i++; //если паук находится левее мухи (движение по часовой)
                }
                for (int i = spiderCoords[1]; i <= flyCoords[1]; i++) { //движение по линии (к мухе)
                    path.append((char) (flyCoords[0] + 65));
                    path.append(i);
                    path.append('-');
                }
            }
        }
        path.delete(path.length() - 1, path.length()); //удаление последнего тире
        return path.toString();
    }

    //3
    public static int digitsCount(long number) {
        if (number < 10) {
            return 1;
        }
        return 1 + digitsCount(number / 10);
    }


    //методы для 4 задачи
    public static boolean containsElement(char[] array, char target) {
        for (char element : array) {
            if (element == target) {
                return true;
            }
        }
        return false;
    }

    public static int findCharIndex(char[] array, char target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    //4
    public static int totalPoints(String[] guessedWords, String rightWord) {
        int totalPoints = 0;
        for (String word : guessedWords) checkWord: {
            char[] rightLetters = rightWord.toCharArray();
            char[] guessedLetters = word.toCharArray();
            int wordLength = word.length();
            for (char element : guessedLetters) {
                if(!containsElement(rightLetters, element)) {
                    break checkWord;
                } else {
                    rightLetters[findCharIndex(rightLetters, element)] = ' ';
                }
            }
            if (wordLength == 6) {
                totalPoints += 50;
            }
            totalPoints += wordLength - 2;
        }
        return totalPoints;
    }

    //5
    public static int[][] sumsUp(int[] array) {
        ArrayList<Integer> numList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            numList.add(array[i]);
        }

        ArrayList<int[]> resultList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] + array[i] == 8) {
                    resultList.add(new int[]{Math.min(array[i], array[j]), Math.max(array[i], array[j])});
                }
            }
        }

        for (int i = 0; i < resultList.size() - 1; i++) {
            int[] current = resultList.get(i);
            int[] next = resultList.get(i + 1);

            int diffCurrent = Math.abs(numList.indexOf(current[0]) - numList.indexOf(current[1]));
            int diffNext = Math.abs(numList.indexOf(next[0]) - numList.indexOf(next[1]));

            if (diffCurrent > diffNext) {
                resultList.set(i + 1, current);
                resultList.set(i, next);

            } else if (diffCurrent == diffNext) {
                if (current[0] > next[0]) {
                    resultList.set(i + 1, current);
                    resultList.set(i, next);
                }
            }
        }

        return resultList.toArray(new int[resultList.size()][2]);
    }

    //6
    public static String takeDownAverage(String[] resultsString) {
        double summ = 0;
        double summAverage = 0;
        for (String result : resultsString) {
            summ += Integer.parseInt(result.substring(0, result.length() - 1));
            summAverage = summ/resultsString.length;
        }
        int addedResult = (int) Math.round(summAverage * (resultsString.length + 1) - 5 * (resultsString.length + 1) - summ);
        return addedResult + "%";
    }

    //7
    public static String ceaserCipher(String task, String message, int shift) {
        message = message.toUpperCase();
        char[] symbols = message.toCharArray();
        String newMessage = "";
        if (task.equals("encode")) {
            for (char element : symbols) {
                if (Character.isLetter(element)) {
                    newMessage += (char) ((int) element + shift);
                } else {
                    newMessage += element;
                }
            }
            return newMessage;
        }
        if (task.equals("decode")) {
            for (char element : symbols) {
                if (Character.isLetter(element)) {
                    newMessage += (char) ((int) element - shift);
                } else {
                    newMessage += element;
                }
            }
            return newMessage;
        }
        return null;
    }

    //8
    public static int setSetup(int n, int k) {
        if (k == 0) {
            return 1;
        }
        return n * setSetup(n - 1, k - 1);
    }

    //9
    public static String timeDifference(String originalCity, String originalTime, String neededCity) {
        HashMap<String, int[] > cityTime = new HashMap<>();
        HashMap<String, Integer> months = new HashMap<>();
        HashMap<String, Integer> daysInMonths = new HashMap<>();

        String[] monthsArray = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};

        cityTime.put("Los Angeles", new int[] {-8, 0});
        cityTime.put("New York", new int[] {-5, 0});
        cityTime.put("Caracas", new int[] {-4, 30});
        cityTime.put("Buenos Aires", new int[] {-3, 0});
        cityTime.put("London", new int[] {0, 0});
        cityTime.put("Rome", new int[] {1, 0});
        cityTime.put("Moscow", new int[] {3, 0});
        cityTime.put("Tehran", new int[] {3, 30});
        cityTime.put("New Delhi", new int[] {5, 30});
        cityTime.put("Beijing", new int[] {8, 0});
        cityTime.put("Canberra", new int[] {10, 0});

        daysInMonths.put("January", 31);
        daysInMonths.put("February", 28);
        daysInMonths.put("March", 31);
        daysInMonths.put("April", 30);
        daysInMonths.put("May", 31);
        daysInMonths.put("June", 30);
        daysInMonths.put("July", 31);
        daysInMonths.put("August", 31);
        daysInMonths.put("September", 30);
        daysInMonths.put("October", 31);
        daysInMonths.put("November", 30);
        daysInMonths.put("December", 31);

        months.put("January", 1);
        months.put("February", 2);
        months.put("March", 3);
        months.put("April", 4);
        months.put("May", 5);
        months.put("June", 6);
        months.put("July", 7);
        months.put("August", 8);
        months.put("September", 9);
        months.put("October", 10);
        months.put("November", 11);
        months.put("December", 12);

        String[] time = originalTime.split(" ");
        time[1] = time[1].replaceAll(",", "");

        int[] timeInCityA = cityTime.get(originalCity);
        int[] timeInCityB = cityTime.get(neededCity);

        int[] timeDifference = new int[] {timeInCityB[0] - timeInCityA[0],
                timeInCityB[1] - timeInCityA[1]};

        String[] currentTimeInString = time[3].split(":");
        int[] currentTime = {Integer.parseInt(currentTimeInString[0]),
                Integer.parseInt(currentTimeInString[1])};

        int[] newTime = new int[] {currentTime[0] + timeDifference[0],
                currentTime[1] + timeDifference[1]};

        int month = months.get(time[0]);
        int date = Integer.parseInt(time[1]);
        int year = Integer.parseInt(time[2]);

        if (newTime[1] < 0) {
            newTime[1] = 60 + newTime[1];
            newTime[0] -= 1;
            if (newTime[0] < 0) {
                newTime[0] = 24 - newTime[0];
                date -= 1;
                if (date == 0) {
                    month -= 1;
                    if (month >= 1) {
                        date = daysInMonths.get(monthsArray[month - 1]);
                    }
                    else {
                        year -= 1;
                        month = 12;
                        date = 31;
                    }
                }
            }
        }

        if (newTime[1] >= 60) {
            newTime[1] = newTime[1] % 60;
            newTime[0] += 1;
        }

        if (newTime[0] >= 24) {
            newTime[0] -= 24;
            date += 1;
            if (date > daysInMonths.get(monthsArray[month - 1])) {
                month += 1;
                date = 1;
                if (month > 12) {
                    month = 1;
                    year += 1;
                }
            }
        }

        String result = year + "-" + month + "-" + date + " ";
        if (newTime[0] <= 9) {
            result = result + "0";
        }
        result = result + newTime[0] + ":";
        if (newTime[1] <= 9) {
            result = result + "0";
        }
        result = result + newTime[1];
        return result;
    }

    //10
    public static boolean isNew(int num) {
        ArrayList<Integer> nums = new ArrayList<>();
        int copyNum = num;
        String minimalNum = "";

        while (copyNum != 0) {
            nums.add(copyNum % 10);
            copyNum /= 10;
        }
        if (nums.size() > 1) {
            if (nums.size() == 2 && nums.contains(0)) {
                return true;
            } else {
                Collections.sort(nums);
            }
        } else {
            return true;
        }
        while (nums.get(0) == 0) {
            nums.add(0);
            nums.remove(0);
        }
        for (int i = 1; nums.get(nums.size() - 1) == 0; i++) {
            nums.add(i, 0);
            nums.remove(nums.size() - 1);
        }
        for (int j = 0; j < nums.size(); j++) {
            minimalNum += nums.get(j);
        }
        return Integer.parseInt(minimalNum) == num;
    }
}