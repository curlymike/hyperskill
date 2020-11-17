package tictactoe;

public class AI extends AbstractPlayer implements Player {
    private final Level level;
    private final AiStrategy strategy;

    public AI(int[][] field, int playsFor) {
        this(field, playsFor, Level.EASY);
    }

    public AI(int[][] field, int playsFor, Level level) {
        super(field, playsFor);
        this.level = level;
        switch (level) {
            case MEDIUM: strategy = new MediumStrategy(); break;
            case HARD: strategy = new HardStrategy(); break;
            default: strategy = new EasyStrategy();
        }
    }

    @Override
    public void move() {
        if (!Main.canMove(field)) {
            return;
        }
        System.out.printf("Making move level \"%s\"%n", level.name().toLowerCase());
        strategy.move(field, playsFor);
    }

    public static enum Level {
        EASY,
        MEDIUM,
        HARD
    }
}
