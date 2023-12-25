public class Game {

    public static void main(String[] args) {
        JangMan jm = new JangMan("Donaudampfschiff");
        System.out.println(jm.getCurrentWord());
        System.out.println(jm.toString());
        System.out.println(jm.isWon());
    }

}
