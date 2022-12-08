import java.util.Arrays;

public class GuiBinTest {
    //递归
    public static void process(int[] data,int L,int R){

        if(L==R){
            return;
        }

        int mid = L + ((R-L)>>1);

        process(data,L,mid);
        process(data,mid+1,R);
        merge(data,L,mid,R);

    }

    private static void merge(int[] data, int L, int M, int R) {

//        System.out.println("1");
        int[] help = new int[R-L+1];

        int i =0;
        int p1=L;
        int p2=M+1;
        while(p1<=M && p2<=R){
            help[i++] = data[p1]<=data[p2]?data[p1++]:data[p2++];
        }

        while(p1<=M){
            help[i++]=data[p1++];
        }

        while(p2<=R){
            help[i++]=data[p2++];
        }

        for (int j = 0; j < help.length ; j++) {
            data[L+j]=help[j];
        }

    }

    //非递归的写法

    public static void mergeSort2(int[] data) {

        if(data == null || data.length<2){
            return;
        }

        int N = data.length;
        int mergeSize = 1;

        while(mergeSize < N){

            int L = 0;
            while(L< N){
                int M = L+mergeSize-1;
                if(M>=N){
                    break;
                }

                int R = Math.min(N-1,M+mergeSize);
                merge(data,L,M,R);
                L = R+1;
            }

            if(mergeSize>N/2){
                break;
            }

            mergeSize<<=1;

        }

    }


    public static void main(String[] args) {

        int[] data = {3,1,4,1,5,9,2,6};

        process(data,0,data.length-1);

        //mergeSort2(data);

        System.out.println(Arrays.toString(data));


    }

}
