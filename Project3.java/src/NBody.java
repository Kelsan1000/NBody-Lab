import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class NBody {

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws FileNotFoundException {

		double big_t = Double.parseDouble(args[0]);
		double delta_t = Double.parseDouble(args[1]);

		String resourceFolder = "resources/";
		String fileName = resourceFolder + args[2];
		FileInputStream fileInputStream = new FileInputStream(fileName);
		System.setIn(fileInputStream);

		// Use StdIn to read from the file.
		int numBodies = StdIn.readInt();
		double universeRadius = StdIn.readDouble();

		// Print out the values read in (remove all this from your final version)
		StdOut.println("big_t          = " + big_t);
		StdOut.println("delta_t        = " + delta_t);
		StdOut.println("numBodies      = " + numBodies);
		StdOut.println("universeRadius = " + universeRadius);
		System.out.println(fileName);

		double[] rx = new double[numBodies];//X Position
		double[] ry = new double[numBodies];//Y Position
		double[] vx = new double[numBodies];//X Velocity
		double[] vy = new double[numBodies];//Y Velocity
		double[] mass = new double[numBodies];
		String[] image = new String[numBodies];
		double G = 6.67 * (Math.pow(10, -11));

		// Takes in the values
		for (int i = 0; i < numBodies; i++) { // start for 1
			rx[i] = StdIn.readDouble();
			ry[i] = StdIn.readDouble();
			vx[i] = StdIn.readDouble();
			vy[i] = StdIn.readDouble();
			mass[i] = StdIn.readDouble();
			image[i] = StdIn.readString();
		} // end for 1

		double[] Fx = new double[numBodies];
		double[] Fy = new double[numBodies];
		double timelapse = 0;

		// The loop for the animation
		while (timelapse < big_t) {// while start
			StdDraw.setXscale(-universeRadius, universeRadius);
			StdDraw.setYscale(-universeRadius, universeRadius);
			StdDraw.picture(0, 0, resourceFolder + "starfield.jpg");

			// Loop for making calculations
			for (int i = 0; i < numBodies; i++) {// start for 2
				Fx[i] = 0;
				Fy[i] = 0;
				for (int j = 0; j < numBodies; j++) {// start for 3
					if (i != j) {// start if
						double delta_x = rx[j] - rx[i];
						double delta_y = ry[j] - ry[i];
						double r = Math.sqrt((Math.pow(delta_x, 2)) + (Math.pow(delta_y, 2)));
						double F = (G * (mass[i] * mass[j])) / (r * r);
						Fx[i] = Fx[i] + (F * (delta_x / r));
						Fy[i] = Fy[i] + (F * (delta_y / r));
					} // end if
				} // end3
			} // end2

			// Loop to apply calculations
			for (int i = 0; i < numBodies; i++) {// start for 4
				double ax = Fx[i] / mass[i];
				double ay = Fy[i] / mass[i];
				vx[i] = vx[i] + (delta_t * ax);
				vy[i] = vy[i] + (delta_t * ay);
				rx[i] = rx[i] + (delta_t * vx[i]);
				ry[i] = ry[i] + (delta_t * vy[i]);
			} // end 4

			// Loop that draws in the images at new positions from for 4
			for (int i = 0; i < numBodies; i++) {// start for 5
				StdDraw.picture(rx[i], ry[i], (resourceFolder + image[i]));

			} // end 5
			StdDraw.show(1);
			timelapse = timelapse + delta_t;

		} // while end

		// Prints the final state of the universe
		for (int i = 0; i < numBodies; i++) {// start for 6
			System.out.println(" ");// Makes space between the output of the final states
			System.out.println(rx[i] + " " + ry[i] + " " + vx[i] + " " + vy[i] + " " + mass[i] + " " + image[i]);

		} // end 6

	} // end main

} // end class
