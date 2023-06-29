//>>>BaZ<<<//
import java.util.*;
import java.io.*;
import static java.lang.Math.*;
public class Main
{  
    static int dx[] = {-1,1,0,0};
    static int dy[] = {0,0,1,-1};
    static long MOD = 1000000007;
    static int INF = Integer.MAX_VALUE/10;
    static PrintWriter pw;
    static InputReader scan;
    //static MyFileReader1 ss;
    //static MyFileReader scan;
    static int ni() throws IOException{return scan.nextInt();}
    static long nl() throws IOException{return scan.nextLong();}
    static double nd() throws IOException{return scan.nextDouble();}
    static String ne() throws IOException{return scan.next();}
    static void pl() throws IOException {pw.println();}
    static void pl(Object o) throws IOException {pw.println(o);}
    static void p(Object o) throws IOException {pw.print(o+" ");}
    static void psb(StringBuilder sb) throws IOException {pw.print(sb);}
    static long X,Y,GCD;
    public static void main(String[] args) {
        new Thread(null,null,"BaZ",99999999)
        {
            public void run()
            {
                try
                {
                    solve();
                }
                catch(Exception e)
                {  
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        }.start();
    }
    static void solve() throws IOException
    {  
        Calendar CAL1 = Calendar.getInstance();
        CAL1.setTime(new Date());
        scan = new InputReader(System.in);
        pw = new PrintWriter(System.out,true);
        //pw = new PrintWriter(new File("C://Users/Aman deep/Desktop/output.txt"));  
        //ss = new MyFileReader1();
        //scan = new MyFileReader();
        StringBuilder sb = new StringBuilder();
        int t = ni();
        while(t-->0)
        {
                String s = ne();
                int n = s.length();
                int req = ni();
                if(n==2 || n==3)
                {
                    if(req!=n-1)
                        pl("NO");
                    else pl("YES\n0");
                }
                else
                {   
                    n-=2;
                    int min = INF;
                    int len1 = n/2;
                    int len2 = n-len1;
                    int n1 = 0,n2 = 0;
                    for(int i=len1,power=0;i>=1;--i,++power)
                        if(s.charAt(i)=='1')
                            n1+=1<<power;
                    for(int i=n,power=0;i>len1;--i,++power)
                        if(s.charAt(i)=='1')
                            n2+=1<<power;
                    HashMap<Pair,Integer> map = new HashMap();
                    for(int mask=0;mask<(1<<len2);++mask)
                         map.put(calc_back(mask,len2),bc(mask^n2));    
                    for(int mask=0;mask<(1<<len1);++mask)
                    {  
                        int bits = bc(n1^mask);
                        Pair forw = calc_forw(mask,len1);
                        int A = forw.y+1;
                        int B = forw.x+1;
                        int C = req-1-forw.x-forw.y;
                        int G = gcd(A,B);
                        if(C<0 || C%G!=0)
                            continue;
                        A/=G;B/=G;C/=G;
                        int first = (int)(C*Modular_Multiplicative_Inverse(A,B))%B;
                        for(;C-A*first>=0;first+=B)
                        {  
                            int left = C-A*first;
                            int ww = left/B;
                            if(ww*B==left)
                            {  
                                Integer diff = map.get(new Pair(first*G,ww*G));
                                if(diff!=null)
                                  min = min(min,bits+diff);
                            }
                        }
                    }
                    if(min!=INF)
                        pl("YES\n"+min);
                    else pl("NO");
                }
        }
        Calendar CAL2 = Calendar.getInstance();
        CAL2.setTime(new Date());
        double Execution_Time = (double)(CAL2.getTimeInMillis()-CAL1.getTimeInMillis())/1000.000;
        //System.out.println("Execution time : "+Execution_Time+" seconds");
        pw.flush();
        pw.close();
    }
        static void Extended_Euclid(long a,long b)
        {
            if(b==0)
            {
                GCD = a;
                X = 1;
                Y = 0;
                return;
            }
            Extended_Euclid(b,a%b);
            long nx = X;
            long ny = Y;
            X = ny;
            Y = nx-(a/b)*ny;
        }
        static long Modular_Multiplicative_Inverse(long A,long M)  
        {
            Extended_Euclid(A,M);
            return (X+M)%M;   //X can be negative
        }
        static int gcd(int a,int b)
        {
            if(b==0)
                return a;
            return gcd(b,a%b);
        }
        static class Pair
        {
            int x,y;
            Pair(int a,int b){x=a;y=b;}
            public int hashCode(){return (int)((x+y*MOD)%MOD);}
            public boolean equals(Object o){if(o instanceof Pair){Pair ww = (Pair)o;return ww.x== x && ww.y== y;}return false;}
        }
        static Pair calc_forw(int mask,int n)
        {
            int endswith0 = 0;
            int endswith1 = 0;
            int zsum = 0,osum = 0;
            for(int i=n-1;i>=0;--i)
                if((mask&(1<<i))!=0)
                {
                    osum+=zsum+1;
                    endswith1+=zsum+1;
                }
            else
                {
                    endswith0+=osum+1;
                    zsum+=osum+1;
                }
            return new Pair(endswith0,endswith1);
        }
        static Pair calc_back(int mask,int n)
        {
            int startswith0 = 0,startswith1 = 0;
            int zsum = 0,osum = 0;
            for(int i=0;i<n;++i)
                if((mask&(1<<i))!=0)
                {
                    osum+=zsum+1;
                    startswith1+=zsum+1;
                }
                 else
                {
                    zsum+=osum+1;
                    startswith0+=osum+1;
                }
            return new Pair(startswith0,startswith1);
        }
        static int bc(int n)
        {
            int cnt = 0;
            while(n>0)
            {
                n = n&(n-1);
                ++cnt;
            }
            return cnt;
        }
    static class InputReader     //NoSuchElementException -> EOF
	{
		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;
		private SpaceCharFilter filter;
		
		public InputReader(InputStream stream)
		{
			this.stream = stream;
		}
		
		public int read()
		{
			if (numChars==-1) 
				throw new InputMismatchException();
			
			if (curChar >= numChars)
			{
				curChar = 0;
				try 
				{
					numChars = stream.read(buf);
				}
				catch (IOException e)
				{
					throw new InputMismatchException();
				}
				
				if(numChars <= 0)				
					return -1;
			}
			return buf[curChar++];
		}
	 
		public String nextLine()
		{
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			String str = "";
            try
            {
                str = br.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return str;
		}
		public int nextInt()
		{
			int c = read();
			
			while(isSpaceChar(c)) 
				c = read();
			
			int sgn = 1;
			
			if (c == '-') 
			{
				sgn = -1;
				c = read();
			}
			
			int res = 0;
			do 
			{
				if(c<'0'||c>'9') 
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			}
			while (!isSpaceChar(c)); 
			
			return res * sgn;
		}
		
		public long nextLong() 
		{
			int c = read();
			while (isSpaceChar(c))
				c = read();
			int sgn = 1;
			if (c == '-') 
			{
				sgn = -1;
				c = read();
			}
			long res = 0;
			
			do 
			{
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			}
			while (!isSpaceChar(c));
				return res * sgn;
		}
		
		public double nextDouble() 
		{
			int c = read();
			while (isSpaceChar(c))
				c = read();
			int sgn = 1;
			if (c == '-') 
			{
				sgn = -1;
				c = read();
			}
			double res = 0;
			while (!isSpaceChar(c) && c != '.') 
			{
				if (c == 'e' || c == 'E')
					return res * Math.pow(10, nextInt());
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			}
			if (c == '.') 
			{
				c = read();
				double m = 1;
				while (!isSpaceChar(c)) 
				{
					if (c == 'e' || c == 'E')
						return res * Math.pow(10, nextInt());
					if (c < '0' || c > '9')
						throw new InputMismatchException();
					m /= 10;
					res += (c - '0') * m;
					c = read();
				}
			}
			return res * sgn;
		}
		
		public String readString() 
		{
			int c = read();
			while (isSpaceChar(c))
				c = read();
			StringBuilder res = new StringBuilder();
			do 
			{
				res.appendCodePoint(c);
				c = read();
			} 
			while (!isSpaceChar(c));
			
			return res.toString();
		}
	 
		public boolean isSpaceChar(int c) 
		{
			if (filter != null)
				return filter.isSpaceChar(c);
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}
	 
		public String next() 
		{
			return readString();
		}
		
		public interface SpaceCharFilter 
		{
			public boolean isSpaceChar(int ch);
		}
	}
    static class MyFileReader                                          //File input template
    {
        StringTokenizer st;
        BufferedReader br;
        MyFileReader() throws IOException
        {
            br = new BufferedReader(new FileReader("C://Users/Aman deep/Desktop/input.txt"));
        }
        String nextLine() throws IOException
        {
            return br.readLine();
        }
        String next() throws IOException
        {
            if(st==null || !st.hasMoreTokens())
                st = new StringTokenizer(nextLine());
            return st.nextToken();
        }
        int nextInt() throws IOException
        {
            return Integer.parseInt(next());
        }
        long nextLong() throws IOException
        {
            return Long.parseLong(next());
        }
        double nextDouble() throws IOException
        {
            return Double.parseDouble(next());
        }
    }
    static class MyFileReader1                                          //File input template
    {
        StringTokenizer st;
        BufferedReader br;
        MyFileReader1() throws IOException
        {
            br = new BufferedReader(new FileReader("C://Users/Aman deep/Desktop/output.txt"));
        }
        String nextLine() throws IOException
        {
            return br.readLine();
        }
        String next() throws IOException
        {
            if(st==null || !st.hasMoreTokens())
                st = new StringTokenizer(nextLine());
            return st.nextToken();
        }
        int nextInt() throws IOException
        {
            return Integer.parseInt(next());
        }
        long nextLong() throws IOException
        {
            return Long.parseLong(next());
        }
        double nextDouble() throws IOException
        {
            return Double.parseDouble(next());
        }
    }
}