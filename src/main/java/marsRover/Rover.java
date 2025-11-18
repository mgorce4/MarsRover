package marsRover;

import java.util.ArrayList;
import java.util.Objects;

public class Rover {
	private final String name;
	private Position position;
	private Direction direction;
	private Obstacle[] obstacles;
	private Obstacle lastObstacleEncountered;
	
	public Rover(String name,Position position, Direction direction) {
		this.name=name;
		this.position=position;
		this.direction=direction;
		this.obstacles = new Obstacle[0];
		this.lastObstacleEncountered = null;
	}
	
	public Rover(String name,Position position, Direction direction, Obstacle[] obstacles) {
		this.name=name;
		this.position=position;
		this.direction=direction;
		this.obstacles = obstacles != null ? obstacles : new Obstacle[0];
		this.lastObstacleEncountered = null;
	}
	
	public Rover(Integer x,Integer y,Direction direction) {
        this("Rover",new Position(x,y),direction);
    }
	
	public Rover() {
		this("Rover", new Position(0,0), Direction.NORTH);
	}

	@Override
	public String toString() {
		return "Rover [name=" + name + ", position=" + position + ", direction=" + direction + "]";
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public String getName() {
		return name;
	}
	
	public void setObstacles(Obstacle[] obstacles) {
		this.obstacles = obstacles != null ? obstacles : new Obstacle[0];
	}
	
	public Obstacle getLastObstacleEncountered() {
		return lastObstacleEncountered;
	}
	
	public String getLocation() {
		return "at " + position +" towards the " + direction;
	}
	
	private boolean isObstacleAt(Position targetPosition) {
		for (Obstacle obstacle : obstacles) {
			if (obstacle.position().equals(targetPosition)) {
				lastObstacleEncountered = obstacle;
				return true;
			}
		}
		return false;
	}
	
	private Position calculateNextPosition(int way) {
		int newX = position.getX();
		int newY = position.getY();
		
		switch(direction) {
			case NORTH:
				newY = (position.getY() + way + Grid.GridHeight()) % Grid.GridHeight();
				break;
			case EAST:
				newX = (position.getX() + way + Grid.GridWidth()) % Grid.GridWidth();
				break;
			case SOUTH:
				newY = (position.getY() - way + Grid.GridHeight()) % Grid.GridHeight();
				break;
			case WEST:
				newX = (position.getX() - way + Grid.GridWidth()) % Grid.GridWidth();
				break;
		}
		
		return new Position(newX, newY);
	}
		
	public boolean move(String letter) {
		int way;
		if("f".equals(letter)) {
			way=1;
		} else {
			way=-1;
		}
		
		Position nextPosition = calculateNextPosition(way);
		
		if (isObstacleAt(nextPosition)) {
			return false; // Movement blocked by obstacle
		}
		
		// No obstacle, proceed with movement
		switch(direction) {
			case NORTH:
				position.setY((position.getY() + way + Grid.GridHeight()) % Grid.GridHeight());
				break;
			case EAST:
				position.setX((position.getX() + way + Grid.GridWidth()) % Grid.GridWidth());
				break;
			case SOUTH:
				position.setY((position.getY() - way + Grid.GridHeight()) % Grid.GridHeight());
				break;
			case WEST:
				position.setX((position.getX() - way + Grid.GridWidth()) % Grid.GridWidth());
				break;
		}
		
		return true; // Movement successful
	}
	
	

	
	public void turnLeft() {
		switch(direction) {
			case NORTH:
				direction = Direction.WEST;
				break;
			case WEST:
				direction = Direction.SOUTH;
				break;
			case SOUTH:
				direction = Direction.EAST;
				break;
			case EAST:
				direction = Direction.NORTH;
	            break;
		}
	}
	
	public void turnRight() {
		switch(direction) {
			case NORTH:
				direction = Direction.EAST;
				break;
			case EAST:
				direction = Direction.SOUTH;
				break;
			case SOUTH:
				direction = Direction.WEST;
				break;
			case WEST:
				direction = Direction.NORTH;
				break;
		}
	}

	public void moveASelectedNumberOfTimes(Integer number, String letter) {
		for (int i = 0; i < number; i++) {
			if (!move(letter)) {
				System.out.println("Obstacle encountered at " + lastObstacleEncountered.position() + 
								   ". Stopped at " + position);
				break;
			}
		}
	}
	
	public void moveAroundTheGrid(ArrayList<String> commands) {
		for ( String command : commands) {
			switch(command) {
				case "f":
					if (!move(command)) {
						System.out.println("Obstacle encountered at " + lastObstacleEncountered.position() + 
										   ". Stopped at " + position);
						return; // Stop processing further commands
					}
					break;
				case "b":
					if (!move(command)) {
						System.out.println("Obstacle encountered at " + lastObstacleEncountered.position() + 
										   ". Stopped at " + position);
						return; // Stop processing further commands
					}
					break;
				case "l":
					turnLeft();
					break;
				case "r":
					turnRight();
					break;
			}
		}
	}
	


	@Override
	public int hashCode() {
		return Objects.hash(direction, name, position);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rover other = (Rover) obj;
		return direction == other.direction && Objects.equals(name, other.name)
				&& Objects.equals(position, other.position);
	}
		
		
}