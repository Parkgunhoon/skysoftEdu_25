package egovframework.dev.testStarter;

class Gugudan {
    public static void main(String args[]) {
    	int i, j, b;


		for(b=1;b<10;b+=3){
			for(i=1;i<10;i++){
				for(j=b;j<b+3;j++){
					int sum = j*i;
					System.out.print(j+"X"+i+"="+sum+"\t");
				}
				System.out.println("");
			}
			System.out.println("");
		}
    }
}