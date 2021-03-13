import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int n;
	static int money;
	static int pos;
	
	public static void main(String[] args) throws IOException {
		
		long start = System.currentTimeMillis();
		
		String line =br.readLine();
		while(line!=null) {
			String ns = br.readLine();
			//System.out.println("ns: "+ns);
			
			String lInput = br.readLine();
			//System.out.println("lInput: "+lInput);
			
			String m  = br.readLine();
			//System.out.println("m:"+m);
			//String a = br.readLine();
			
			int[] prices = getPrices(ns, m, lInput);
			pos=findIndexBigerThanMoney(money, prices);
			findAnswer(pos, money, prices);
			
			line =br.readLine();
			//System.out.println("**");
		}
		
		long end = System.currentTimeMillis();
		System.out.println("terminated in: "+(end-start)+" milis");
		br.close();
		
	}
	
	public static int[] getPrices(String ns, String m, String lInput) throws IOException {

		//String ns = br.readLine();
		n = Integer.parseInt(ns);
		//String lInput = br.readLine();
		//String m  = br.readLine();
		
		money = Integer.parseInt(m);
		String[] pricesS = lInput.split(" ");
		int[] prices = new int[n];
		
		//String a = br.readLine();
		//String b = br.readLine();
		
		for (int i = 0; i < n; i++) {
			prices[i]=Integer.parseInt(pricesS[i]);
		}
		//organize
		//prices=insertionSort(prices);
		Arrays.sort(prices);
		//System.out.println(Arrays.toString(prices));
		
		return prices;
	}
	
	public static int findIndexBigerThanMoney(int money, int[]prices) {
		int pos = -1;
		int in = 0;
		int fin = prices.length-1;
		
		while(in<=fin && pos<0) {
			int middle = (in+fin)/2;
			
			if(prices[middle]==money) {
				pos = middle-1;
			}
			
			if(in!=fin) {
				if(money-prices[middle]>0) {
				in = middle+1;
				}else {
					fin = middle-1;
				}
			}else {
				if(money-prices[middle]>0) {
					pos = middle;
					}else {
						pos = middle-1;
					}
			}				
		}
		
		return pos;
	}
	
	public static String findAnswer(int pos, int money, int[]prices) {
		String answer="";
		int dif=1000000;
		int tempDif=1000000;
		int indexBi=0;
		int indexBj=0;
		
		for (int i = 0; i < pos && i<=money/2; i++) {
			for (int j = i+1; j < pos+1; j++) {
				if(prices[i]+prices[j]==money) {
					tempDif = prices[j]-prices[i];
				}
				if(tempDif<dif) {
					dif=tempDif;
					indexBi = i;
					indexBj = j;
				}
			}
		}
		
		System.out.println("Peter should buy books whose prices are "+ prices[indexBi] +" and "+prices[indexBj]);
		
		return answer;
	}
	
	/*public static int[] insertionSort(int[] prices) {
		
		for (int i = 1; i < prices.length; i++) {
			for (int j = i; j>0 && prices[j]<prices[j-1] ; j--) {
				int temp = prices[j];
				prices[j] = prices[j-1];
				prices[j-1] = temp;
				//System.out.println(Arrays.toString(prices));
				//iterations++;
			}
		}
		return prices;
	}*/

}
