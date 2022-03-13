import org.w3c.dom.ls.LSOutput;

import java.util.*;

public class LL1 {

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
     */
    public static final List<Rule> rules = new ArrayList<>(Arrays.asList(
            new Rule('S', "aS", 1), new Rule('S', "bAc", 2),
            new Rule('A', "bAc", 3), new Rule('A', "d", 4)
    ));
    public static final Set<Character> terminals = new TreeSet<>();
    public static final Set<Character> nonTerminals = new TreeSet<>();

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
            nonTerminals.add(r.left);
        }
    }

    /**
     * Terminalisok gyujtemenyenek feltoltese.
     */
    public static void setTerminals() {
        for (Rule r : rules) {
            for (int i = 0; i < r.right.length(); i++) {
                if (isTerminal(r.right.charAt(i))) {
                    terminals.add(r.right.charAt(i));
                }
            }
        }
    }

    public static void main(String[] args) {
    }
}
