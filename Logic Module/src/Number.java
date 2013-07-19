public class Number {
	private long n,d;

	Number(long n){
		this.n = n;
		this.d = 1;
	}

	Number(long n, long d){
		this.n = n;
		this.d = d;
	}

	Number(double number){
		d = 1;
		while( number != (long)number && d <= 1e17){
			number *= 10;
			d *= 10;
		}
		n = (long)number;
	}

	Number(String cad){
		int i = cad.indexOf('/');
		// defect to infinite
		n = 1;
		d = 0;
		try{
			if( i != -1){
				n = Long.parseLong(cad.substring(0, i));
				d = Long.parseLong(cad.substring(i + 1, cad.length()));
			}else{
				n = Long.parseLong(cad);
				d = 1;
			}
		}catch(Exception e){
			System.err.println("Error, convert String to Number failed! (Invalid Format)");
		}
	}

	public void simplify(){
		if(d < 0){
			n = -n;
			d = -d;
		}
		long a = Math.abs(n) , b = Math.abs(d);
		if(b > a){
			long tmp = a;
			a = b;
			b = tmp;
		}
		long gcd = gcd(a,b);
		this.n /= gcd;
		this.d /= gcd;
	}
	
	public Number copy(){
		return new Number(n,d);
	}
	
	public Number negate(){
		long a = -n,b = d;
		Number ans = new Number(a,b);
		ans.simplify();
		return ans;
	}
	public Number add(Number val){
		if(val.getD() == 0 || d == 0)
			return new Number(1, 0);

		long a, b;
		b = val.getD() * d;
		//a/b + c/d = (a*d + c*b)/(b*d);
		a = n*val.getD() + val.getN()*d;
		Number ans = new Number(a,b);
		ans.simplify();
		return ans;
	}
	
	public Number subtract(Number val){
		return this.add(val.negate());
	}

	public Number multiply(Number val){
		long a, b;
		a = val.getN() * n;
		b = val.getD() * d;
		
		Number ans = new Number(a,b);
		ans.simplify();
		return ans;
	}
	public Number divide(Number val){
		return multiply(new Number(val.getD(), val.getN()));
	}
	
	public Number add( long number){
		return add(new Number(number));
	}
	
	public Number subtract( long number){
		return subtract(new Number(number));
	}
	public Number multiply(long number){
		return multiply(new Number(number));
	}
	public Number divide(long number){
		return divide(new Number(number));
	}
	public Number pow(int p){
		long a = (long) Math.pow(n, p);
		long b = (long) Math.pow(d, p);
		Number ans = new Number(a,b);
		ans.simplify();
		return ans;
	}
	public String toString(){
		if(d == 0)
			return "oo";
		simplify();
		String ans = String.valueOf(n);
		if(d != 1)
			ans += "/" + String.valueOf(d);
		return ans;
	}

	public long getN() {
		return n;
	}

	public void setN(long n) {
		this.n = n;
	}

	public long getD() {
		return d;
	}

	public void setD(long d) {
		this.d = d;
	}
	
	public static Number valueOf(long number){
		return new Number(number);
	}

	public static Number valueOf(double number){
		return new Number(number);
	}

	public static Number valueOf(String cad){
		return new Number(cad);
	}
	
	private long gcd(long a, long b){
		return b==0?a:gcd(b, a%b);
	}	
}
