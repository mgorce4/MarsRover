package marsRover;

public class Rover {
	private final String name;
	private Position position;
	private Direction direction;
	
	public Rover(String name,Position position, Direction direction) {
		this.name=name;
		this.position=position;
		this.direction=direction;
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
	
	public String getLocation() {
		return "at " + position +" towards the " + direction;
	}
		
	public void move() {
		switch(direction) {
			case NORTH:
				position.setY(position.getY() + 1);
				break;
			case EAST:
				position.setX(position.getX() + 1);
				break;
			case SOUTH:
				position.setY(position.getY() - 1);
				break;
			case WEST:
				position.setX(position.getX() - 1);
				break;
			}
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

		public void moveASelectedNumberOfTimes(Integer number) {
			for (int i = 0; i < number; i++) {
				move();
			}
		}
}
