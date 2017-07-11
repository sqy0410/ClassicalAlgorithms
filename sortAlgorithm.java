package huawei;

import java.util.ArrayList;

public class sortAlgorithm {
	//************************冒泡排序*********************************************	
	public static ArrayList<Integer> BubbleSort(ArrayList<Integer> input){
		if(input.isEmpty())return input;
		for(int j=0;j<input.size();j++){
			for(int i=0;i<input.size()-1-j;i++){//从左开始比较相邻的两个元素x和y，如果 x > y 就交换两者
				if(input.get(i)>input.get(i+1)){
					Integer temp=input.get(i+1);
					input.set(i+1, input.get(i));
					input.set(i, temp);
				}
			}
		}
		return input;
	}
	//************************插入排序*********************************************		
	public static ArrayList<Integer> InsertSort(ArrayList<Integer> input){
		if(input.isEmpty())return input;
		for(int j=0;j<input.size()-1;j++){
			for(int i=j+1;i>0;i--){  //将第i个元素插入到前面有序位置，保证i前面的数字都有序
				if(input.get(i)<input.get(i-1)){
					Integer temp=input.get(i-1);
					input.set(i-1, input.get(i));
					input.set(i, temp);
				}
			}
		}
		return input;
	}
	//************************选择排序*********************************************		
	public static ArrayList<Integer> SelectSort(ArrayList<Integer> input){
		if(input.isEmpty())return input;
		int min=10000;
		Integer minInt = input.get(0);
		for(int j=0;j<input.size();j++){
			minInt = input.get(j);min=j;
			for(int i=j;i<input.size();i++){  //从左开始，选择后面元素中最小值，和最左元素交换
				if(input.get(i)<minInt){
					minInt=input.get(i);
					min=i;
				}
			}
			input.set(min, input.get(j));
			input.set(j, minInt);
		}
		return input;
	}	
	//************************希尔排序*********************************************		
	public static ArrayList<Integer> ShellSort(ArrayList<Integer> input){
		if(input.isEmpty())return input;
		int step = input.size()/2;
		if (step==0)step=1;
		while(step>0){                       //更新step值
		for(int loop=0;loop<step;loop++){    //分组插入排序
			
			for(int j=loop;j<input.size()-step;j=j+step){  //插入排序
				for(int i=j+step;i-step>=0;i=i-step){  //将第i个元素插入到前面有序位置，保证i前面的数字都有序
					if(input.get(i)<input.get(i-step)){
						Integer temp=input.get(i-step);
						input.set(i-step, input.get(i));
						input.set(i, temp);
					}
				}
			}
			
		}
		step=step/2;
		}
		return input;
	}		
	//************************归并排序*********************************************
	//空间复杂度更高
	public static ArrayList<Integer> MergeSort(ArrayList<Integer> input){ //非递归分解数组
		if(input.isEmpty())return input;
		ArrayList<ArrayList<Integer>> in = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> outtemp = new ArrayList<ArrayList<Integer>>();
		int i=0;
		for(i=0;i<input.size();i++){
			ArrayList<Integer> temp1 = new ArrayList<Integer>();
			temp1.add(input.get(i));
			in.add(temp1);
		}
		while(in.size()>1){
			while(in.size()>1){
				outtemp.add(Merge(in.get(0),in.get(1)));
				in.remove(1);in.remove(0);
			}
			in.addAll(outtemp);
			outtemp.removeAll(outtemp);
		}
		return in.get(0);
	} 
	//递归分解数组
	public static ArrayList<Integer> MergeSort1(ArrayList<Integer> input){ 
		if(input.isEmpty()||input.size()==1)return input;
		ArrayList<Integer> left = new ArrayList<Integer>();
		ArrayList<Integer> right = new ArrayList<Integer>();
		int i=0;
		for(i=0;i<input.size()/2;i++) left.add(input.get(i));	
		for(;i<input.size();i++) right.add(input.get(i));	
		return Merge(MergeSort1(left),MergeSort1(right));
	}
	public static ArrayList<Integer> Merge(ArrayList<Integer> inputa, ArrayList<Integer> inputb){  //合并数组
		ArrayList<Integer> output = new ArrayList<Integer>();
		int i=0,j=0;
		while(i<inputa.size() && j<inputb.size()){
			if(inputa.get(i)<=inputb.get(j)){
				output.add(inputa.get(i));i++;
			}
			else {
				output.add(inputb.get(j));j++;
			}
		}
		while(i<inputa.size()){
			output.add(inputa.get(i));i++;
		}
		while(j<inputb.size()){
			output.add(inputb.get(j));j++;
		}
		return output;
	}
	//空间复杂度O(n)
	public static ArrayList<Integer> MergeSort2(ArrayList<Integer> input){  //递归分解数组
		if(input.isEmpty()||input.size()==1)return input;
		ArrayList<Integer> output = new ArrayList<Integer>();
		output.addAll(input);
		MergeMiddle2(input,0,input.size(),output);	
		return output;
	}
	public static void MergeMiddle2(ArrayList<Integer> input,
			int start, int end, ArrayList<Integer> output){  //递归分解数组
		int middle = (end+start)/2;		
		if(middle!=start){
			//System.out.println("start="+start+",middle="+middle+",end="+end);
			MergeMiddle2(input,start, middle, output);
			MergeMiddle2(input, middle, end, output);
			Merge2(input, start, middle, end, output);			
		}
	}
	public static void Merge2(ArrayList<Integer> input, 
			int start, int middle, int end, ArrayList<Integer> output){  //合并数组
		int i=start,j=middle,z=start;
		//System.out.println("Merge2====="+"start="+start+",middle="+middle+",end="+end);
		while(i<middle && j<end){
			if(input.get(i)<=input.get(j)){
				output.set(z,input.get(i));
				i++;z++;
			}
			else {
				output.set(z,input.get(j));
				j++;z++;
			}
		}
		while(i<middle){
			output.set(z,input.get(i));
			i++;z++;
		}
		while(j<end){
			output.set(z,input.get(j));
			j++;z++;
		}
		for ( i = start;i < end; i++)              //notice here!!!!!!!!
	        input.set(i, output.get(i));
	}
	//************************快速排序*********************************************		
	public static ArrayList<Integer> QuickSort(ArrayList<Integer> input){
		if(input.isEmpty())return input;
		Quick(input, 0, input.size());	
		return input;
	}		
	public static void Quick(ArrayList<Integer> input, int start, int end){
		if(end-start>1){
			Integer base = input.get(start);
			int i=start, j=end-1;
			while(j>i){
				while(input.get(j)>=base && j>i)j--;  //这两只顺序乱了都不行.................连同最后一次交换要和start保持一致
				while(input.get(i)<=base && j>i)i++;				
					Integer temp = input.get(i);
				input.set(i,input.get(j));
				input.set(j, temp);
			}
			Integer temp = input.get(start); //注意这里i指向的数据大于base，需要与base数据进行交换!!!!!!!!!!!!!!!!!
			input.set(start,input.get(i));
			input.set(i, temp);
			Quick(input, start, i);
			Quick(input,  i+1,end);
		}
	}
	//*************************堆排序*********************************************		
	public static ArrayList<Integer> HeapSort(ArrayList<Integer> input){
		if(input.isEmpty())return input;
		//HeapConstruct
		int i=0,j=0;
		for(i=1;i<input.size();i++){
			j=i;
			while((int)(Math.floor((j-1)/2)) >=0){				
				int father=(int)(Math.floor((j-1)/2));
				if(input.get(father)<input.get(j)){
					Integer temp=input.get(j);
					input.set(j, input.get(father));
					input.set(father, temp);
				}
				if(father==0)break;
				j=father;
			}
		}
		// heap sort
		Heap(input,0);
		return input;
	}
	public static void Heap(ArrayList<Integer> input, int finish){
		if(finish<input.size()-1){
		int i=0,size=input.size()-finish-1,left=0,right=0;
		Integer temp1=input.get(size);   //获取第finish个最大值
		input.set(size, input.get(0));
		input.set(0, temp1);
		while(2*i+1 <size){		          //最大堆调整，注意这里的size 比上面size小1
				left=2*i+1;
				if (2*i+2<size) right=2*i+2;
				else  right=2*i+1;				
				if(input.get(left)<=input.get(right) && input.get(right)>input.get(i)){
					Integer temp=input.get(i);
					input.set(i, input.get(right));
					input.set(right, temp);
					i=right;
				}else if(input.get(left)>input.get(right) && input.get(left)>input.get(i)){
					Integer temp=input.get(i);
					input.set(i, input.get(left));
					input.set(left, temp);
					i=left;
				}else break;
		}
		Heap(input,finish+1);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
