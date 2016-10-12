package cn.com.ddhj;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;   
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

  
public class TestCachedThreadPool{   
	
	public static void main(String[] args) throws InterruptedException, ExecutionException{   
		long start = System.currentTimeMillis();
		
		ExecutorService executor = Executors.newCachedThreadPool();   
  
        Future<String> aqi = executor.submit(new AqiRunable());
        Future<String> wea = executor.submit(new WeatherRunnable());
        Future<String> funk = executor.submit(new FunkRunnable());
        Future<String> bitch = executor.submit(new BiterRunnable());
        executor.shutdown();  
        for(int i = 0 ; i < 10 ; i ++){
        	System.out.println("i = " + i);
        }
        
        System.out.println("\n\n等待程序处理完成... \n" ); 
        
        System.out.println(wea.get());  // 耗时 11 秒
        System.out.println(bitch.get());  // 耗时 12 秒
        System.out.println(funk.get());  // 耗时 14 秒
        System.out.println(aqi.get());  // 耗时 19 秒
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + " 毫秒\n"); 
        
        
        for(int k = 0 ; k < 10 ; k ++){
        	System.out.println("k = " + k); 
        }
         
    }   
	
}   
  

 
class AqiRunable implements Callable<String>{   
    public String  call() throws Exception {   
    	String tname = Thread.currentThread().getName();
    	System.out.println("AqiRunable 线程call()方法被调用 -  " + tname);   
    	Thread.sleep(19000); 
        return "AqiRunable 线程返回结果 - 处理 19 秒";
    }   
} 

class WeatherRunnable implements Callable<String>{   
    public String  call() throws Exception {  
    	String tname = Thread.currentThread().getName();
    	System.out.println("WeatherRunnable 线程call()方法被调用 - " + tname);   
    	Thread.sleep(11000); 
        return "WeatherRunnable 线程返回结果 - 处理 11 秒";
    }   
} 


class FunkRunnable implements Callable<String>{   
    public String  call() throws Exception {   
    	String tname = Thread.currentThread().getName();
    	System.out.println("FunkRunnable 线程call()方法被调用 - " + tname);   
    	Thread.sleep(14000); 
        return "FunkRunnable 线程返回结果 - 处理 14 秒";
    }   
} 

class BiterRunnable implements Callable<String>{   
    public String  call() throws Exception {   
    	String tname = Thread.currentThread().getName();
    	System.out.println("BiterRunnable 线程call()方法被调用 - " + tname);   
    	Thread.sleep(12000); 
        return "BitchRunnable 线程返回结果 - 处理 12 秒";
    }   
} 





/*
public void testa() throws InterruptedException, ExecutionException{
	 ExecutorService executor = Executors.newCachedThreadPool();
    FutureTask<String> aqi = new FutureTask<String>(new AqiRunable());
    FutureTask<String> wea = new FutureTask<String>(new WeatherRunnable());
    FutureTask<String> fuc = new FutureTask<String>(new FuckRunnable());
    FutureTask<String> bit = new FutureTask<String>(new BitchRunnable());
    
    executor.submit(aqi);  
    executor.submit(wea); 
    executor.submit(fuc); 
    executor.submit(bit); 
    
    System.out.println(aqi.get());
    System.out.println(wea.get());
    System.out.println(fuc.get());
    System.out.println(bit.get()); 
    
    
    executor.shutdown();   
}
*/









