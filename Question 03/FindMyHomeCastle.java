import java.util.*;

public class FindMyHomeCastle {
    private Set<Coordinate> enemyPositions;
    private Coordinate castlePosition;
    private int gridDimension = 10;
    private List<List<String>> possiblePaths;

    public FindMyHomeCastle(List<Coordinate> enemyCoordinates, Coordinate castleCoord) {
        this.enemyPositions = new HashSet<>(enemyCoordinates);
        this.castlePosition = castleCoord;
        this.possiblePaths = new ArrayList<>();
    }

    public void generatePaths() {
        List<String> currentPath = new ArrayList<>();
        Set<Coordinate> remainingEnemies = new HashSet<>(enemyPositions);
        currentPath.add("Start (" + castlePosition.x + "," + castlePosition.y + ")");
        explorePaths(castlePosition, currentPath, 0, remainingEnemies);
    }

    private void explorePaths(Coordinate currentCoord, List<String> currentPath, int direction, Set<Coordinate> remainingEnemies) {
        if (remainingEnemies.isEmpty() && currentCoord.equals(castlePosition)) {
            possiblePaths.add(new ArrayList<>(currentPath));
            return;
        }

        int x = currentCoord.x;
        int y = currentCoord.y;

        int[][] moveDirections = {
                {0, 1},  
                {1, 0},   
                {0, -1},  
                {-1, 0}   
        };

        int dx = moveDirections[direction][0];
        int dy = moveDirections[direction][1];

        int nextX = x + dx;
        int nextY = y + dy;

        while (1 <= nextX && nextX <= gridDimension && 1 <= nextY && nextY <= gridDimension) {
            Coordinate newCoord = new Coordinate(nextX, nextY);
            if (remainingEnemies.contains(newCoord)) {
                Set<Coordinate> updatedRemainingEnemies = new HashSet<>(remainingEnemies);
                updatedRemainingEnemies.remove(newCoord);
                currentPath.add("Eliminate (" + nextX + "," + nextY + "). Turn Left");
                explorePaths(newCoord, currentPath, (direction + 1) % 4, updatedRemainingEnemies);
                currentPath.remove(currentPath.size() - 1);
            } else {
                currentPath.add("Move (" + nextX + "," + nextY + ")");
            }
            nextX += dx;
            nextY += dy;
        }

        currentPath.remove(currentPath.size() - 1);
    }

    public void displayPaths() {
        System.out.println("\nThere are " + possiblePaths.size() + " unique paths to your castle.");
        for (int i = 0; i < possiblePaths.size(); i++) {
            System.out.println("\nPath " + (i + 1));
            System.out.println("=======");
            for (String step : possiblePaths.get(i)) {
                System.out.println(step);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the no of enemies: ");
        int numEnemies = Integer.parseInt(scanner.nextLine().trim());

        List<Coordinate> enemies = new ArrayList<>();
        for (int i = 1; i <= numEnemies; i++) {
            System.out.print("Enter coordinates for enemy " + i + ": ");
            String[] coordinates = scanner.nextLine().trim().split(",");
            int x = Integer.parseInt(coordinates[0].trim());
            int y = Integer.parseInt(coordinates[1].trim());
            enemies.add(new Coordinate(x, y));
        }

        System.out.print("Enter the coordinates for your castle: ");
        String[] castleCoordinates = scanner.nextLine().trim().split(",");
        int castleX = Integer.parseInt(castleCoordinates[0].trim());
        int castleY = Integer.parseInt(castleCoordinates[1].trim());

        FindMyHomeCastle game = new FindMyHomeCastle(enemies, new Coordinate(castleX, castleY));
        game.generatePaths();
        game.displayPaths();
    }

    static class Coordinate {
        int x, y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate coord = (Coordinate) o;
            return x == coord.x && y == coord.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
