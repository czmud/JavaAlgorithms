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


    public int[] sortArray2(int[] nums) {
        // flip the sign bit
        for (int i = 0; i < nums.length; i++ ){
            nums[i] ^= -2147483648;
        }

        int[][] radix = {new int[nums.length],new int[nums.length], new int[nums.length],new int[nums.length]}; 
        int[] index = {0,0,0,0};

        int sig_val = 0;
        for ( int p = 0; p < 16; p+=2){
            for ( int i = 0; i < nums.length; i++ ){
                sig_val = ( nums[i] >> p ) & 3;
                radix[sig_val][index[sig_val]] = nums[i];
                index[sig_val]++;
            }
            for ( int j = 0; j < index[0]; j++ ){
                nums[j] = radix[0][j];
            }
            for ( int j = 0; j < index[1]; j++ ){
                nums[ index[0] + j ] = radix[1][j];
            }
            for ( int j = 0; j < index[2]; j++ ){
                nums[ index[0] + index[1] + j ] = radix[2][j];
            }
            for ( int j = 0; j < index[3]; j++ ){
                nums[ index[0] + index[1] + index[2] + j ] = radix[3][j];
            }

            index[0] = 0;
            index[1] = 0;
            index[2] = 0;
            index[3] = 0;
        };

        // flip sign bit back
        for (int i = 0; i < nums.length; i++ ){
            nums[i] ^= -2147483648;
        }

        return nums;
    }

    public int[] sortArrayBuckets(int[] nums) {
        // flip the sign bit
        for (int i = 0; i < nums.length; i++ ){
            nums[i] ^= -2147483648;
        }

        int[][] radix = new int[16][nums.length];
        int[] index = new int[16];

        int sig_val = 0;
        for ( int p = 0; p < 32; p+=4){
            for ( int i = 0; i < nums.length; i++ ){
                sig_val = ( nums[i] >> p ) & 15;
                radix[sig_val][index[sig_val]] = nums[i];
                index[sig_val]++;
            }
            
            int offset = 0;
            for (int b = 0; b < 16; b++ ){
                for ( int j = 0; j < index[b]; j++ ){
                    nums[offset + j] = radix[b][j];
                }
                offset += index[b];
                index[b] = 0;
            }

        };

        // flip sign bit back
        for (int i = 0; i < nums.length; i++ ){
            nums[i] ^= -2147483648;
        }

        return nums;
    }


        public int[] sortArrayErik17(int[] nums) {

        int[][] radix = new int[2][nums.length]; 
        int[] index = new int[2];

        int sig_val = 0;
        for ( int p = 0; p < 16; p++){
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

        // use bit 17 to determine sign
        for ( int i = 0; i < nums.length; i++ ){
            sig_val = ( nums[i] >> 16 ) & 1;
            radix[sig_val][index[sig_val]] = nums[i];
            index[sig_val]++;
        }
        for ( int j = 0; j < index[1]; j++ ){
            nums[j] = radix[1][j];
        }
        for ( int j = 0; j < index[0]; j++ ){
            nums[ index[1] + j ] = radix[0][j];
        }

        return nums;
    }

    public int[] sortArrayCounting(int[] nums) {
        // flip the sign bit
        for (int i = 0; i < nums.length; i++ ){
            nums[i] ^= -2147483648;
        }


        for (int p = 0; p < 32; p+=4){
            nums = countingSort(nums, p);
        }

        // flip sign bit back
        for (int i = 0; i < nums.length; i++ ){
            nums[i] ^= -2147483648;
        }

        return nums;
    }

    public int[] countingSort(int[] nums, int p){
        int[] count = new int[16];
        int[] output = new int[nums.length];

        int j = 0;
        for ( int i = 0; i < nums.length; i++ ){
            j = ( nums[i] >> p ) & 15;
            count[j] += 1;
        }

        for (int i = 1; i<16; i++ ){
            count[i] += count[i-1];
        }

        for (int i = nums.length - 1; i > -1; i--){
            j = ( nums[i] >> p ) & 15;
            count[j] -= 1;
            output[count[j]] = nums[i];
        }

        return output;
    }
}