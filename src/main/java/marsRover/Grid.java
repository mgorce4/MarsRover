package marsRover;

public class Grid {
	
	private static Integer width;
	private static Integer height;
	
	public Grid(Integer width, Integer height) {
		this.width = width;
		this.height = height;
	}


	public static Integer GridWidth() {
		return width;
	}

	public static Integer GridHeight() {
		return height;
	}

}
