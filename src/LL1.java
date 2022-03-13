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

    public static void main(String[] args) {
    }
}
