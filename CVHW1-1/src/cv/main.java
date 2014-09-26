package cv;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		 String fileName = "./asset/lena.im";
		 int headerLength = 172;
		 int imageWidth = 512;
		 int imageHeight = 512;
		 int unit = 1;
		 
		 //hw1-a
		 ArrayList<Integer> bytes = GetByteData(fileName);
		 for(int y = 0 ; y<imageHeight / 2; y++)
		 {
			 for(int x = 0; x <imageWidth;x++)
			 {
				 int x1,y1,x2,y2;
				 x1 = x;
				 y1 = y;
				 x2 = x;
				 y2 = (imageHeight-1) - y;
				 Swap(bytes,headerLength+(y1*imageWidth+x1)*unit,headerLength+(y2*imageWidth+x2)*unit,unit);
			 }
		 }
		 WriteOut(bytes,"./asset/hw1-a.im");
		 
		 
		 ArrayList<Integer> bytes2 = GetByteData(fileName);
		 for(int y = 0 ; y<imageHeight; y++)
		 {
			 for(int x = 0; x <imageWidth/2;x++)
			 {
				 int x1,y1,x2,y2;
				 x1 = x;
				 y1 = y;
				 x2 = (imageWidth-1) - x;
				 y2 = y;
				 Swap(bytes2,headerLength+(y1*imageWidth+x1)*unit,headerLength+(y2*imageWidth+x2)*unit,unit);
			 }
		 }
		 WriteOut(bytes2,"./asset/hw1-b.im");
		 
		 ArrayList<Integer> bytes3 = GetByteData(fileName);
		 for(int y = 0 ; y<imageHeight; y++)
		 {
			 for(int x = 0; x <imageWidth;x++)
			 {
				 if(x-y < 0)continue;
				 
				 int x1,y1,x2,y2;
				 x1 = x;
				 y1 = y;
				 x2 = y;
				 y2 = x;
				 Swap(bytes3,headerLength+(y1*imageWidth+x1)*unit,headerLength+(y2*imageWidth+x2)*unit,unit);
			 }
		 }
		 WriteOut(bytes3,"./asset/hw1-c.im");
		
		 
		// TODO Auto-generated method stub

	}
	
	public static void WriteOut(ArrayList<Integer> data,String name) throws IOException
	{
		File f = new File(name);
		
		if(f.exists())f.delete();
		
		FileOutputStream out = null;
		out = new FileOutputStream(name);
		
		for(int i : data)
		{
			out.write((byte)i);
		}
		
		out.flush();
		out.close();
		
	}
	
	public static ArrayList<Integer> GetByteData(String fileName) throws IOException
	{
		 File f = new File(fileName);
		 ArrayList<Integer> bytes = new ArrayList<Integer>();
		
		 //System.out.println("file exist:"+f.exists());
		
		 FileInputStream in = null;
		 in = new FileInputStream(fileName);
		 
		 int c;
		 while ((c = in.read()) != -1) {
			 bytes.add(c);
        }
		 
		 return bytes;
	}
	
	public static void Swap(ArrayList<Integer> list,int index,int index2,int length)
	{
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for(int i = 0; i<length; i++)
		{
			temp.add(list.get(index+i));
			list.set(index+i, list.get(index2+i));
			list.set(index2+i, temp.get(i));
		}
	}
	
	

}
