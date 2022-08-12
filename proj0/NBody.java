public class NBody{
	public static double readRadius(String planets){
		In in = new In(planets);
		int n = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String planets){		
		In in = new In(planets);
		int num = in.readInt();
		double radius = in.readDouble();		
		Planet[] ans = new Planet[num];
		for(int i = 0; i<num; i += 1){
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			ans[i] = new Planet(xP, yP, xV, yV, m, img);
		}
		return ans;
	}

	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);		
		int num = planets.length;
	
		StdDraw.setXscale(-radius, radius);
		StdDraw.setYscale(-radius, radius);
		StdDraw.enableDoubleBuffering();
		StdDraw.picture(0, 0, "images/starfield.jpg");				

		for(int i = 0; i<planets.length; i += 1){
			planets[i].draw();
		}		
		StdDraw.show();

		double time = 0;
		while(time != T){
			double[] xForce = new double[num];
			double[] yForce = new double[num];
			for(int i = 0; i<num; i += 1){
				xForce[i] = planets[i].calcNetForceExertedByX(planets);
				yForce[i] = planets[i].calcNetForceExertedByY(planets);
			}		
			for(int i = 0; i<num; i += 1){
				planets[i].update(dt, xForce[i], yForce[i]);
			}	

			StdDraw.picture(0, 0, "images/starfield.jpg");					
			for(int i = 0; i<planets.length; i += 1){
				planets[i].draw();
			}	
			StdDraw.show();
			StdDraw.pause(10);
			time += dt;
		}

		StdOut.printf("%d\n", num);		
		StdOut.printf("%.2e\n", radius);
		for(int i = 0; i<num; i += 1){
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            	planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                planets[i].yyVel, planets[i].mass, planets[i].imgFileName); 
		}
	}
}