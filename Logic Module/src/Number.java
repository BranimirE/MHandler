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
	
	private void simplify(){
		long gcd = gcd(Math.max(n, d), Math.min(n, d));
		this.n /= gcd;
		this.d /= gcd;
		
	}
	public String toString(){
		if(d == 0)
			return "oo";
		return n + "/" + d;
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
	private long gcd(long a, long b){
		return b==0?a:gcd(b, a%b);
	}	
}
