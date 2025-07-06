public class FixedSlidingWindow {
    public static void main(String[] args) {

        int arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        int start = 0; 
        int windowSize = 3;
        int windowSum = 0;
        
        for(int end = 0; end < arr.length; end++) {

            windowSum += arr[end];
            
            // window size exceeded
            if(end >= windowSize) {
                windowSum -= arr[start];
                start++;
            } 

            System.out.println("Window sum now is " + windowSum);
        }
    }
}
