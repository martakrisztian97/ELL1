import java.util.*;

public class ELL1 {

    /**
     * Szabalyok tarolasa szolgalo osztaly.
     */
    public static class Rule {
        private char left;    // szabaly bal oldala
        private String right; // szabaly jobb oldala
        private int serial;   // szabaly sorszama

        public Rule(char left, String right, int serial) {
            this.left = left;
            this.right = right;
            this.serial = serial;
        }

        public char getLeft() {
            return left;
        }

        public String getRight() {
            return right;
        }

        public int getSerial() {
            return serial;
        }

        @Override
        public String toString() {
            return "Rule{" +
                    "left=" + left +
                    ", right='" + right + '\'' +
                    ", serial=" + serial +
                    '}';
        }
    }

    /**
     * Globalis valtozok:
     * - Szabalyokat tartalmazo lista
     * - Terminalisok gyujtemenye
     * - Nem terminalisok gyujtemenye
     * - Elemzo tablazat.
     */
    public static final List<Rule> rules = new ArrayList<>(Arrays.asList(
            new Rule('S', "aS", 1), new Rule('S', "bAc", 2),
            new Rule('A', "bAc", 3), new Rule('A', "d", 4)
    ));
    public static final List<Character> terminals = new ArrayList<>();
    public static final List<Character> nonTerminals = new ArrayList<>();
    public static String[][] parseTable = new String[1][1];

    /**
     * Megvizsgal egy karaktert, hogy az terminalis-e.
     * @param c A vizsgalt karakter.
     * @return Azzal ter vissza, hogy a vizsgalt karakter terminalis-e.
     */
    public static boolean isTerminal(char c) {
        boolean terminal = (c >= 'a' && c <= 'z') ? true : false;
        return terminal;
    }

    /**
     * Nem terminalisok gyujtemenyenek feltoltese.
     */
    public static void setNonTerminals() {
        for (Rule r : rules) {
            if (!nonTerminals.contains(r.left)) {
                nonTerminals.add(r.left);
            }
        }
    }

    /**
     * Terminalisok gyujtemenyenek feltoltese.
     */
    public static void setTerminals() {
        for (Rule r : rules) {
            for (int i = 0; i < r.right.length(); i++) {
                char temp = r.right.charAt(i);
                if (isTerminal(temp) && !terminals.contains(temp)) {
                    terminals.add(temp);
                }
            }
        }
    }

    /**
     * Az elemzo tablazat feltoltese.
     */
    public static void setParseTable() {
        int N = nonTerminals.size()+terminals.size()+2; // sorok szama
        int M = terminals.size()+2;  // oszlopok szama
        parseTable = new String[N][M];
        // Elso sor feltoltese.
        for (int i = 0; i < terminals.size(); i++) {
            parseTable[0][i+1] = terminals.get(i)+"";
        }
        parseTable[0][M-1] = "#";

        // Elso oszlop feltoltese.
        for (int i = 0; i < N-2; i++) {
            if (i < nonTerminals.size()) {
                parseTable[i+1][0] = nonTerminals.get(i)+"";
            } else if (i >= nonTerminals.size()) {
                parseTable[i+1][0] = terminals.get(i-nonTerminals.size())+"";
            }
        }
        parseTable[N-1][0] = "#";

        // Accept, pop es error beszurasa a tablazatba.
        for (int i = 1; i < parseTable.length; i++) { // elso oszlopon megy vegig
            for (int j = 1; j < parseTable[0].length; j++) { // elso soron megy vegig
                if (parseTable[i][0] == "#" && parseTable[0][j] == "#") {
                    parseTable[i][j] = "accept";
                } else if (parseTable[i][0].equals(parseTable[0][j])) {
                    parseTable[i][j] = "pop ";
                } else {
                    parseTable[i][j] = "error";
                }
            }
        }

        // A szabalyok beszurasa a tablaba.
        for (Rule r : rules) {
            int indexI = nonTerminals.indexOf(r.getLeft());
            int indexJ = terminals.indexOf(r.getRight().charAt(0));
            parseTable[indexI+1][indexJ+1] = "("+r.getRight()+","+r.getSerial()+")";
        }
        parseTable[0][0] = "";
    }

    /**
     * Az elemzo tabla kiiratasa.
     */
    public static void printParseTable() {
        for (int i = 0; i < parseTable.length; i++) {
            for (int j = 0; j < parseTable[i].length; j++) {
                if (i == 0) {
                    System.out.print(parseTable[i][j]+"\t\t\t");
                } else {
                    System.out.print(parseTable[i][j]+"\t\t");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        setTerminals();
        setNonTerminals();
        setParseTable();
        printParseTable();
    }
}
