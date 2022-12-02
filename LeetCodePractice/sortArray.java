class Solution {
    public int[] sortArray(int[] nums) {
        // flip the sign bit
        for (int i = 0; i < nums.length; i++ ){
            nums[i] ^= -2147483648;
        }

        int[][] radix = {new int[nums.length],new int[nums.length]}; 
        int[] index = {0,0};

        int sig_val = 0;
        for ( int p = 0; p < 32; p++){
            for ( int i = 0; i < nums.length; i++ ){
                sig_val = ( nums[i] >> p ) & 1;
                radix[sig_val][index[sig_val]] = nums[i];
                index[sig_val]++;
            }
            for ( int j = 0; j < index[0]; j++ ){
                nums[j] = radix[0][j];
            }
            for ( int j = 0; j < index[1]; j++ ){
                nums[ index[0] + j ] = radix[1][j];
            }

            index[0] = 0;
            index[1] = 0;
        };

        // flip sign bit back
        for (int i = 0; i < nums.length; i++ ){
            nums[i] ^= -2147483648;
        }

        return nums;
    }
}