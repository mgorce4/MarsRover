package marsRover;

import java.util.ArrayList;

public class TestMain {
	
	public static void main(String[] args) {
		Grid grid = new Grid(100,100);
		Position position = new Position(0,0);
		
		ArrayList<String> commands1 = new ArrayList<String>();
		commands1.add("f");
		commands1.add("f");
		commands1.add("r");
		commands1.add("f");
		commands1.add("l");
		commands1.add("f");
		commands1.add("l");
		commands1.add("f");

		
		
		
		Obstacle[] obstacles = new Obstacle[] {
			new Obstacle(new Position(0, 3)),
			new Obstacle(new Position(2, 2)),
			new Obstacle(new Position(5, 0))
		};
		
		Rover rover = new Rover("MarsRover", position, Direction.NORTH, obstacles);
		System.out.println("Rover is " + rover.getLocation());
		
		rover.moveAroundTheGrid(commands1);
		
		System.out.println("Rover is " + rover.getLocation());

	}
}