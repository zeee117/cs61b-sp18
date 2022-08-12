public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static final double G = 6.67e-11;

	public Planet(double xP, double yP, double xV,
					double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet b){
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Planet b){
		return Math.sqrt(Math.pow(b.xxPos - this.xxPos, 2) +
			Math.pow(b.yyPos - this.yyPos, 2));
	}

	public double calcForceExertedBy(Planet b){				
		return G * b.mass * this.mass /
			Math.pow(this.calcDistance(b), 2);
	}

	public double calcForceExertedByX(Planet b){
		return this.calcForceExertedBy(b) * (b.xxPos - this.xxPos) / 
			this.calcDistance(b);
	}

	public double calcForceExertedByY(Planet b){
		return this.calcForceExertedBy(b) * (b.yyPos - this.yyPos) / 
			this.calcDistance(b);
	}

	public double calcNetForceExertedByX(Planet[] b_array){
		double ans = 0;
		for(int i = 0; i<b_array.length; i += 1){
			if(!this.equals(b_array[i])){
				ans += this.calcForceExertedByX(b_array[i]);
			}			
		}
		return ans;
	}

	public double calcNetForceExertedByY(Planet[] b_array){
		double ans = 0;
		for(int i = 0; i<b_array.length; i += 1){
			if(!this.equals(b_array[i])){
				ans += this.calcForceExertedByY(b_array[i]);
			}			
		}
		return ans;
	}

	public void update(double dt, double fX, double fY){
		double aX = fX / this.mass;
		double aY = fY / this.mass;
		this.xxVel = this.xxVel + dt * aX;
		this.yyVel = this.yyVel + dt * aY;
		this.xxPos = this.xxPos + dt * this.xxVel;
		this.yyPos = this.yyPos + dt * this.yyVel;
	}

	public void draw(){
		String img_address = "images/" + imgFileName;
		StdDraw.picture(xxPos, yyPos, img_address);		
	}
}