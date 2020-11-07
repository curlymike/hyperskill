package tictactoe;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Human extends AbstractPlayer implements Player {

    public Human(int[][] field, int playsFor) {
        super(field, playsFor);
        //this.field = field;
        //this.playsFor = playsFor;
    }

    public void move() {
        Pattern ptrn = Pattern.compile("(\\d+)\\s+(\\d+)");
        int x;
        int y;
        while (true) {
            System.out.print("Enter the coordinates: ");
            String input = Main.scanner.nextLine();
            Matcher m = ptrn.matcher(input);
            try {
                if (m.matches()) {
                    x = Integer.parseInt(m.group(1));
                    y = Integer.parseInt(m.group(2));
                    if (Main.isCoordinatesValid(field, x, y)) {
                        x--;
                        y--;
                        if (Main.isValidMove(field, x, y)) {
                            break;
                        }
                        System.out.println("This cell is occupied! Choose another one!");
                        continue;
                    } else {
                        System.out.println("Coordinates should be from 1 to " + field.length + "!");
                        continue;
                    }
                }
            } catch (NumberFormatException e) {
            }
            System.out.println("You should enter numbers!");
        }
        field[y][x] = playsFor;
    }
}
